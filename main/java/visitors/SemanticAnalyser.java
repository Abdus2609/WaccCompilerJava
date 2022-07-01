package visitors;

import antlr.BasicParser;
import antlr.BasicParser.ArgListContext;
import antlr.BasicParser.ArrayElemContext;
import antlr.BasicParser.ArrayLiterContext;
import antlr.BasicParser.ArrayTypeContext;
import antlr.BasicParser.AssignLHSContext;
import antlr.BasicParser.AssignRHSContext;
import antlr.BasicParser.AssignStatContext;
import antlr.BasicParser.BaseTypeContext;
import antlr.BasicParser.BeginStatContext;
import antlr.BasicParser.DeclareStatContext;
import antlr.BasicParser.ExitStatContext;
import antlr.BasicParser.ExprContext;
import antlr.BasicParser.FreeStatContext;
import antlr.BasicParser.FuncContext;
import antlr.BasicParser.FunctionCallContext;
import antlr.BasicParser.IfStatContext;
import antlr.BasicParser.NewpairContext;
import antlr.BasicParser.PairElemContext;
import antlr.BasicParser.PairElemTypeContext;
import antlr.BasicParser.PairTypeContext;
import antlr.BasicParser.ParamContext;
import antlr.BasicParser.ParamListContext;
import antlr.BasicParser.PrintStatContext;
import antlr.BasicParser.PrintlnStatContext;
import antlr.BasicParser.ProgContext;
import antlr.BasicParser.ReadStatContext;
import antlr.BasicParser.ReturnStatContext;
import antlr.BasicParser.StatContext;
import antlr.BasicParser.TypeContext;
import antlr.BasicParser.UnaryOperContext;
import antlr.BasicParser.VoidFuncCallStatContext;
import antlr.BasicParser.WhileStatContext;
import antlr.BasicParserBaseVisitor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import types.Array;
import types.Function;
import types.Pair;
import types.SymbolTable;
import types.Type;
import types.WaccBool;
import types.WaccChar;
import types.WaccInt;
import types.WaccString;
import types.WaccVoid;

public class SemanticAnalyser extends BasicParserBaseVisitor<Type> {

  private final ErrorHandler errorHandler;

  public SemanticAnalyser(ErrorHandler errorHandler) {
    this.errorHandler = errorHandler;
  }

  // the symbol table that stores all the variables
  private SymbolTable var_st = new SymbolTable(new HashMap<>());

  // the symbol table that stores all the functions
  private final SymbolTable func_st = new SymbolTable(new HashMap<>());

  // list of symbol tables to control the scope
  private final List<SymbolTable> scope = new ArrayList<>();

  // the depth of scope
  private int scopeDepth = 0;

  // the function that's being visited
  private Function currFunction;

  private final Map<String, Integer> arrayBoundsTable = new HashMap<>();

  // enter a new scope
  private void enterScope() {
    scopeDepth++;
    scope.add(new SymbolTable(var_st.getTable()));
  }

  // exit current scope and remove declared identifiers from that scope
  private void exitScope() {
    scopeDepth--;
    var_st = scope.get(scopeDepth);
    scope.remove(scopeDepth);
  }

  @Override
  public Type visitBaseType(BaseTypeContext ctx) {
    // return the corresponding type
    if (ctx.BOOL() != null) {
      return new WaccBool();
    } else if (ctx.INT() != null) {
      return new WaccInt();
    } else if (ctx.CHAR() != null) {
      return new WaccChar();
    } else if (ctx.STRING() != null) {
      return new WaccString();
    }

    // shouldn't reach here
    return null;
  }

  @Override
  public Type visitPairElem(PairElemContext ctx) {

    // if pair elem is null then throw an error
    if (ctx.expr().getText().equals("null")) {
      errorHandler.accessNullPairElemError(ctx);
    }

    if (visit(ctx.expr()) instanceof Pair) {
      Pair ctxPair = (Pair) visit(ctx.expr());
      // return the corresponding type
      if (ctx.FST() != null) {
        return ctxPair.fst();
      } else {
        return ctxPair.snd();
      }
    }
    // calling fst or snd on a type that's not pair
    errorHandler.typeMismatchError(ctx, visit(ctx.expr()), new Pair(null, null));

    // shouldn't reach here
    return null;
  }

  @Override
  public Type visitPairElemType(PairElemTypeContext ctx) {
    // visit the corresponding branch
    if (ctx.baseType() != null) {
      return visit(ctx.baseType());
    } else if (ctx.PAIR() != null) {
      return new Pair(null, null);
    } else {
      return new Array(visit(ctx.type()));
    }
  }

  @Override
  public Type visitPairType(PairTypeContext ctx) {
    // return a pair type according to the elem types
    return new Pair(visit(ctx.pairElemType(0)), visit(ctx.pairElemType(1)));
  }

  @Override
  public Type visitType(TypeContext ctx) {
    // visit the corresponding branch
    if (ctx.baseType() != null) {
      return visit(ctx.baseType());
    } else if (ctx.pairType() != null) {
      return visit(ctx.pairType());
    } else {
      return new Array(visit(ctx.type()));
    }
  }

  @Override
  public Type visitArrayElem(ArrayElemContext ctx) {
    String ident = ctx.IDENT().getText();

    if (var_st.lookUp(ident).isPresent()) {
      // check if variable is an array in symbol table
      if (!(var_st.lookUp(ident).get() instanceof Array)) {
        errorHandler.typeMismatchError(ctx, var_st.lookUp(ident).get(), new Array(null));
      }

      Type arrType = ((Array) var_st.lookUp(ident).get()).getElemType();
      Type indexType = visit(ctx.expr(0));

      if (indexType.toString().equals("int")) {

        if (ctx.expr(0).INT_LIT() != null) {
          // check array bound
          int index = Integer.parseInt(ctx.expr(0).INT_LIT().getText());
          int size = arrayBoundsTable.get(ident);
          if (index >= size) {
            errorHandler.arrayIndexOutOfBoundsError(ctx, index, size);
          }
        }

        return arrType;
      } else {
        errorHandler.typeMismatchError(ctx, indexType, new WaccInt());
      }

    } else {
      errorHandler.variableNotFoundError(ctx, ident);
    }

    // shouldn't reach here
    return null;
  }

  @Override
  public Type visitArrayType(ArrayTypeContext ctx) {
    return new Array(visit(ctx.type()));
  }

  @Override
  public Type visitArrayLiter(ArrayLiterContext ctx) {
    if (ctx.expr().isEmpty()) {
      // empty array
      return new Array(null);
    }

    Type arrType = visit(ctx.expr(0));

    // check if expr are all the same type
    for (ExprContext expr : ctx.expr()) {
      Type exprType = visit(expr);
      if (!arrType.toString().equals(exprType.toString())) {
        errorHandler.typeMismatchError(ctx, exprType, arrType);
      }
    }

    return new Array(arrType);
  }

  @Override
  public Type visitUnaryOper(UnaryOperContext ctx) {
    // get the expression's type
    Type exprType = visit(ctx.getParent().getChild(1));

    if (ctx.NOT() != null) {
      // expr must be a bool
      if (exprType instanceof WaccBool) {
        return exprType;
      } else {
        // type mismatch
        errorHandler.typeMismatchError(ctx, exprType, new WaccBool());
      }
    } else if (ctx.MINUS() != null || ctx.BITWISE_NOT() != null) {
      // expr must be an int
      if (exprType instanceof WaccInt) {
        return exprType;
      } else {
        // type mismatch
        errorHandler.typeMismatchError(ctx, exprType, new WaccInt());
      }
    } else if (ctx.LEN() != null) {
      // expr must be an array
      if (exprType instanceof Array) {
        return new WaccInt();
      } else {
        // type mismatch
        errorHandler.typeMismatchError(ctx, exprType, new Array(null));
      }
    } else if (ctx.ORD() != null) {
      // expr must be a char
      if (exprType instanceof WaccChar) {
        return new WaccInt();
      } else {
        // type mismatch
        errorHandler.typeMismatchError(ctx, exprType, new WaccChar());
      }
    } else if (ctx.CHR() != null) {
      // expr must be an int
      if (exprType instanceof WaccInt) {
        return new WaccChar();
      } else {
        // type mismatch
        errorHandler.typeMismatchError(ctx, exprType, new WaccInt());
      }
    }

    String text = ctx.getText();
    if (text.equals("chr")) {
      return new WaccChar();
    } else {
      return new WaccInt();
    }
  }

  @Override
  public Type visitExitStat(ExitStatContext ctx) {
    Type exitType = visit(ctx.expr());

    if (!(exitType instanceof WaccInt)) {
      // error handler - invalid exit code type expected Int actual exitType
      errorHandler.invalidExitCodeTypeError(ctx, exitType);
    }

    return new WaccInt();
  }

  @Override
  public Type visitParam(ParamContext ctx) {
    return visit(ctx.type());
  }

  @Override
  public Type visitParamList(ParamListContext ctx) {
    // add the parameters to symbol table
    for (ParamContext param : ctx.param()) {
      String paramIdent = param.IDENT().getText();
      Type paramType = visit(param);
      var_st.add(paramIdent, paramType);
      // currFunction.addParameter(paramType);
    }

    return null;
  }

  @Override
  public Type visitAssignLHS(AssignLHSContext ctx) {
    // check if is a variable
    if (ctx.IDENT() != null) {
      String ident = ctx.IDENT().getText();
      // check if can be found in symbol table
      if (var_st.lookUp(ident).isPresent()) {
        return var_st.lookUp(ident).get();
      } else {
        // error Handler: ident not found
        errorHandler.variableNotFoundError(ctx, ident);
      }
    }

    // not identifier, just visit the children
    return visitChildren(ctx);
  }

  @Override
  public Type visitAssignRHS(AssignRHSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Type visitArgList(ArgListContext ctx) {
    // get the function from st
    Function function =
        (Function) func_st.lookUp(((FunctionCallContext) ctx.getParent()).IDENT().getText()).get();

    if (function.numOfParam() != ctx.expr().size()) {
      // error handler - invalid num of args: expected function.numOfParam() actual ctx.expr.size
      errorHandler.invalidNumberOfArgsError(ctx, function.numOfParam(), ctx.expr().size());
    }

    // check if the functions parameters types match the args
    for (int i = 0; i < function.numOfParam(); i++) {
      Type funcParameter = function.getParameters().get(i);
      Type argType = visit(ctx.expr(i));
      if (!funcParameter.toString().equals(argType.toString())) {
        // error handler - invalid argument type: expected function.getparam.get(i) actual
        // visit(ctx.expr(i).tostring()
        errorHandler.invalidArgumentTypeError(ctx, funcParameter, argType);
      }
    }

    // return the function type
    return function.getType();
  }

  @Override
  public Type visitVoidFuncCallStat(VoidFuncCallStatContext ctx) {
    return visit(ctx.functionCall());
  }

  @Override
  public Type visitFunctionCall(FunctionCallContext ctx) {
    String ident = ctx.IDENT().getText();
    if (func_st.lookUp(ident).isEmpty()) {
      // functionNotFound error
      errorHandler.functionNotFoundError(ctx, ident);
    }

    // check if the arguments match the parameters
    if (ctx.argList() != null) {
      visit(ctx.argList());
    } else {
      int numParams = ((Function) func_st.lookUp(ident).get()).getParameters().size();
      if (numParams != 0) {
        errorHandler.invalidNumberOfArgsError(ctx, numParams, 0);
      }
    }

    return ((Function) func_st.lookUp(ident).get()).getType();
  }

  @Override
  public Type visitNewpair(NewpairContext ctx) {
    return new Pair(visit(ctx.expr(0)), visit(ctx.expr(1)));
  }

  @Override
  public Type visitDeclareStat(DeclareStatContext ctx) {

    // we get the type first
    Type declareType = visit(ctx.type());
    String ident = ctx.IDENT().getText();

    // then we check if type is the same as expr
    Type rhsType = visit(ctx.assignRHS());
    if (!declareType.toString().equals(rhsType.toString()) && !rhsType.toString().equals("array")) {
      // errorhandler - type mismatch
      errorHandler.typeMismatchError(ctx, declareType, rhsType);
    }

    // check exists in symbol table
    if (var_st.lookUp(ident).isPresent()) {
      if (scopeDepth > 0 && scope.get(scopeDepth - 1).lookUp(ident).isPresent()) {
        var_st.getTable().put(ident, declareType);
        return declareType;
      } else {
        // errorhandler - redeclarationerror
        errorHandler.variableRedeclarationError(ctx, ident);
      }
    }

    // add to st
    var_st.add(ctx.IDENT().getText(), declareType);

    if (declareType.toString().equals("array")) {
      arrayBoundsTable.put(ident, ctx.assignRHS().arrayLiter().expr().size());
    }

    return declareType;
  }

  @Override
  public Type visitAssignStat(AssignStatContext ctx) {
    // check lhs and rhs have the same type
    Type lhsType = visit(ctx.assignLHS());
    Type rhsType = visit(ctx.assignRHS());
    if (!lhsType.toString().equals(rhsType.toString())
        && !lhsType.toString().equals("array")
        && !rhsType.toString().equals("array")) {
      // type mismatch
      errorHandler.typeMismatchError(ctx, lhsType, rhsType);
    }

    if (lhsType.toString().equals("array")) {
      if (ctx.assignLHS().IDENT() != null && ctx.assignRHS().arrayLiter() != null) {
        arrayBoundsTable.put(
            ctx.assignLHS().IDENT().getText(), ctx.assignRHS().arrayLiter().expr().size());
      }
    }

    return lhsType;
  }

  @Override
  public Type visitReadStat(ReadStatContext ctx) {
    Type lhsType = visit(ctx.assignLHS());

    // type must be char or int
    if (!(lhsType instanceof WaccChar || lhsType instanceof WaccInt)) {
      // type mismatch
      errorHandler.doubleTypeMismatchError(ctx, lhsType, new WaccChar(), new WaccInt());
    }

    return lhsType;
  }

  @Override
  public Type visitFreeStat(FreeStatContext ctx) {
    Type exprType = visit(ctx.expr());
    // type must be array or pair
    if (!(exprType instanceof Array || exprType instanceof Pair)) {
      // type mismatch
      errorHandler.doubleTypeMismatchError(ctx, exprType, new Pair(null, null), new Array(null));
    }
    return exprType;
  }

  @Override
  public Type visitReturnStat(ReturnStatContext ctx) {
    // check if returning inside a function
    if (currFunction == null) {
      errorHandler.globalReturnError(ctx);
    }

    Type functionReturnType = currFunction.getType();
    Type exprType = visit(ctx.expr());

    if (!functionReturnType.toString().equals(exprType.toString())) {
      // error handler - invalid return type error - expected functionrettype actual
      // ctx.expr.tostring
      errorHandler.invalidReturnTypeError(ctx, functionReturnType, exprType);
    }

    return exprType;
  }

  @Override
  public Type visitProg(ProgContext ctx) {

    // add all the functions to the function symbol table
    for (FuncContext func : ctx.func()) {
      String funcName = func.IDENT().getText();
      Type retType = (func.VOID() != null) ? new WaccVoid() : visit(func.type());
      Function function = new Function(retType);
      if (!func_st.add(funcName, function)) {
        // errorhandler - function redeclaration
        errorHandler.functionRedeclarationError(ctx, funcName);
      }
      if (func.paramList() != null) {
        // visit the parameter list beforhand
        for (ParamContext param : func.paramList().param()) {
          function.addParameter(visit(param));
        }
      }
    }

    visitChildren(ctx);
    return null;
  }

  @Override
  public Type visitFunc(FuncContext ctx) {

    String funcName = ctx.IDENT().getText();

    Function preFunction = currFunction;

    // enter new scope
    enterScope();
    currFunction = (Function) func_st.lookUp(funcName).get();

    // visit parameters
    if (ctx.paramList() != null) {
      visit(ctx.paramList());
    }

    // visit the statements
    Type returnType = visit(ctx.stat());

    // exit the scope and restore the global function
    exitScope();
    currFunction = preFunction;

    return returnType;
  }

  @Override
  public Type visitStat(StatContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Type visitBeginStat(BeginStatContext ctx) {
    // enter new scope
    enterScope();

    // visit statements
    Type returnType = visit(ctx.stat());

    // exit the scope
    exitScope();

    return returnType;
  }

  @Override
  public Type visitWhileStat(WhileStatContext ctx) {

    Type cond = visit(ctx.expr());
    if (!(cond instanceof WaccBool)) {
      errorHandler.typeMismatchError(ctx, cond, new WaccBool());
    }

    // enter new scope
    enterScope();

    // visit statements
    Type stat = visit(ctx.stat());

    // exit the scope
    exitScope();

    return stat;
  }

  @Override
  public Type visitIfStat(IfStatContext ctx) {

    Type cond = visit(ctx.expr());
    if (!(cond instanceof WaccBool)) {
      errorHandler.typeMismatchError(ctx, cond, new WaccBool());
    }

    // enter new scope
    enterScope();

    // visit statements for then
    visit(ctx.stat(0));

    // exit the scope
    exitScope();

    // enter new scope
    enterScope();

    // visit statements for else
    visit(ctx.stat(1));

    // exit the scope
    exitScope();

    return null;
  }

  @Override
  public Type visitPrintlnStat(PrintlnStatContext ctx) {

    visit(ctx.expr());

    return null;
  }

  @Override
  public Type visitPrintStat(PrintStatContext ctx) {

    visit(ctx.expr());

    return null;
  }

  @Override
  public Type visitExpr(ExprContext ctx) {
    if (ctx.INT_LIT() != null) {

      long l = Long.parseLong(ctx.INT_LIT().getText());
      if (ctx.MINUS() != null) {
        l = -l;
      }
      if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
        errorHandler.integerOutOfRangeError(ctx);
      }
      return new WaccInt();

    } else if (ctx.BIN_INT_LIT() != null) {

      long l = Long.parseLong(ctx.BIN_INT_LIT().getText().substring(2), 2);
      if (ctx.MINUS() != null) {
        l = -l;
      }
      if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
        errorHandler.integerOutOfRangeError(ctx);
      }

      return new WaccInt();

    } else if (ctx.OCT_INT_LIT() != null) {

      long l = Long.parseLong(ctx.OCT_INT_LIT().getText().substring(2), 8);
      if (ctx.MINUS() != null) {
        l = -l;
      }
      if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
        errorHandler.integerOutOfRangeError(ctx);
      }

      return new WaccInt();

    } else if (ctx.HEX_INT_LIT() != null) {

      long l = Long.parseLong(ctx.HEX_INT_LIT().getText().substring(2), 16);
      if (ctx.MINUS() != null) {
        l = -l;
      }
      if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
        errorHandler.integerOutOfRangeError(ctx);
      }

      return new WaccInt();

    } else if (ctx.BOOL_LIT() != null) {

      return new WaccBool();

    } else if (ctx.CHAR_LIT() != null) {

      int c = ctx.CHAR_LIT().getText().charAt(0);
      if (c > 127) {
        errorHandler.charOutOfRangeError(ctx);
      }
      return new WaccChar();

    } else if (ctx.STRING_LIT() != null) {

      return new WaccString();

    } else if (ctx.mulDivModOper() != null
        || ctx.plusMinusOper() != null
        || ctx.compOper() != null
        || ctx.eqOper() != null
        || ctx.bitwiseAndOper() != null
        || ctx.bitwiseXorOper() != null
        || ctx.bitwiseOrOper() != null) {

      return visitBinOper(ctx);

    } else if (ctx.andOper() != null || ctx.orOper() != null) {

      return visitBoolBinOper(ctx);

    } else if (ctx.unaryOper() != null) {

      return visit(ctx.unaryOper());

    } else if (ctx.PAIR_LIT() != null) {

      return new Pair(null, null);

    } else if (ctx.IDENT() != null) {

      String ident = ctx.IDENT().getText();

      if (ident.equals("true") || ident.equals("false")) {
        return new WaccBool();
      }

      if (ident.equals("null")) {
        return new Pair(null, null);
      }

      if (var_st.lookUp(ident).isPresent()) {
        return var_st.lookUp(ident).get();
      } else {
        // variablenotfounderror
        errorHandler.variableNotFoundError(ctx, ident);
      }

    } else if (ctx.arrayElem() != null) {

      return visit(ctx.arrayElem());

    } else if (ctx.OPEN_PARENTHESES() != null) {

      return visit(ctx.expr(0));
    }

    return null;
  }

  public Type visitBinOper(ExprContext ctx) {
    Type lhs = visit(ctx.expr(0));
    Type rhs = visit(ctx.expr(1));

    // check for all the possible cases
    if (ctx.eqOper() != null) {

      // lhs and rhs must have the same type
      if (lhs.toString().equals(rhs.toString())) {
        return new WaccBool();
      } else {
        errorHandler.typeMismatchError(ctx, lhs, rhs);
      }
    } else if (ctx.compOper() != null) {
      // check if both are int or char
      if ((lhs instanceof WaccInt && rhs instanceof WaccInt)
          || (lhs instanceof WaccChar && rhs instanceof WaccChar)) {
        return new WaccBool();
      } else {
        // type mismatch error
        Type incorrectType = (lhs instanceof WaccInt) ? rhs : lhs;
        errorHandler.typeMismatchError(ctx, incorrectType, new WaccInt());
      }
    } else {
      // check if both are int if operator is + - * / % & ^ |
      if (lhs instanceof WaccInt && rhs instanceof WaccInt) {
        return new WaccInt();
      } else {
        Type incorrectType = (lhs instanceof WaccInt) ? rhs : lhs;
        errorHandler.typeMismatchError(ctx, incorrectType, new WaccInt());
      }
    }
    // shouldn't reach here
    return null;
  }

  public Type visitBoolBinOper(ExprContext ctx) {
    Type lhs = visit(ctx.expr(0));
    Type rhs = visit(ctx.expr(1));

    if (ctx.andOper() != null || ctx.orOper() != null) {
      // boolean binary operators can only have operands of type Bool
      if (lhs instanceof WaccBool && rhs instanceof WaccBool) {
        return new WaccBool();
      } else {
        Type incorrectType = (lhs instanceof WaccBool) ? rhs : lhs;
        // type mismatch error
        errorHandler.typeMismatchError(ctx, incorrectType, new WaccBool());
      }
    }

    return new WaccBool();
  }
}
