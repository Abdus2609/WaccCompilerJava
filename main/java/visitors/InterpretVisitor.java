package visitors;

import static generators.ExpressionType.ARRAY_ELEMENT;
import static generators.ExpressionType.BINOP_EXPR;
import static generators.ExpressionType.BOOL_LITERAL;
import static generators.ExpressionType.CHAR_LITERAL;
import static generators.ExpressionType.IDENTIFIER;
import static generators.ExpressionType.PAIR_LITERAL;
import static generators.ExpressionType.SIGNED_INT_LITERAL;
import static generators.ExpressionType.STRING_LITERAL;
import static generators.ExpressionType.UNOP_EXPR;
import static generators.ExpressionType.INT_LITERAL;
import static generators.OperatorType.AND;
import static generators.OperatorType.BINARY_MINUS;
import static generators.OperatorType.BITWISE_AND;
import static generators.OperatorType.BITWISE_NOT;
import static generators.OperatorType.BITWISE_OR;
import static generators.OperatorType.BITWISE_XOR;
import static generators.OperatorType.CHR;
import static generators.OperatorType.DIVIDE;
import static generators.OperatorType.EQ;
import static generators.OperatorType.GREATER_THAN;
import static generators.OperatorType.GREATER_THAN_EQ;
import static generators.OperatorType.LEN;
import static generators.OperatorType.MOD;
import static generators.OperatorType.MULT;
import static generators.OperatorType.NOT;
import static generators.OperatorType.NOT_EQ;
import static generators.OperatorType.OR;
import static generators.OperatorType.ORD;
import static generators.OperatorType.PLUS;
import static generators.OperatorType.SMALLER_THAN;
import static generators.OperatorType.SMALLER_THAN_EQ;
import static generators.OperatorType.UNARY_MINUS;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import antlr.BasicParser.*;
import antlr.BasicParserBaseVisitor;
import generators.*;
import types.Array;
import types.Type;

// iterate through the AST to generate the list of instructions
public class InterpretVisitor extends BasicParserBaseVisitor<Generator> {

  State state = new State();

  private SemanticAnalyser semanticVisitor;

  public void setSemanticVisitor(SemanticAnalyser semanticVisitor) {
    this.semanticVisitor = semanticVisitor;
  }

  @Override
  public Generator visitProg(ProgContext ctx) {

    List<Generator> functions = new ArrayList<>();
    if (ctx.func() != null) {
      for (FuncContext func : ctx.func()) {
        functions.add(visitFunc(func));
      }
    }

    VariableVisitor variableVisitor = new VariableVisitor();
    int numVars = variableVisitor.visitStat(ctx.stat());
    state.setNumVars(numVars);
    state.setDeclaredVars(0);

    return new ProgramGenerator(functions, visitStat(ctx.stat()), state, ctx.stat());
  }

  @Override
  public Generator visitFunc(FuncContext ctx) {

    String funcName = ctx.IDENT().getText();
    List<ParamContext> parameters = new ArrayList<>();
    List<StatContext> statements = new ArrayList<>();

    FunctionGenerator functionGenerator = new FunctionGenerator(state);
    functionGenerator.setVisitor(this);

    if (ctx.paramList() != null) {
      parameters = ctx.paramList().param();
      for (ParamContext p : parameters) {
        Type type = semanticVisitor.visit(p);
        functionGenerator.addParameterType(type);
      }
    }

    functionGenerator.setStat(visitStat(ctx.stat()));

    functionGenerator.setFuncName(funcName);
    functionGenerator.setParameters(parameters);
    functionGenerator.setStatements(statements);
    functionGenerator.setVariableVisitor(new VariableVisitor());
    functionGenerator.setStatContext(ctx.stat());
    state.addFunction(funcName, functionGenerator);

    return functionGenerator;
  }

  @Override
  public Generator visitFunctionCall(FunctionCallContext ctx) {
    FunctionCallGenerator callGenerator = new FunctionCallGenerator(state);
    callGenerator.setFuncName(ctx.IDENT().getText());
    if (ctx.argList() != null) {
      callGenerator.setArgList(ctx.argList().expr());
    } else {
      callGenerator.setArgList(null);
    }

    callGenerator.setVisitor(this);

    return callGenerator;
  }

  @Override
  public Generator visitStat(StatContext ctx) {
    if (ctx.SEMICOL() != null) {
      return new StatementGenerator(visit(ctx.stat(0)), visit(ctx.stat(1)));
    } else {
      return visitChildren(ctx);
    }
  }

  @Override
  public Generator visitDeclareStat(DeclareStatContext ctx) {

    int offset = 4 * (state.getNumVars() - state.getDeclaredVars() - 1);
    Type type = semanticVisitor.visitType(ctx.type());
    String ident = ctx.IDENT().getText();

    state.addDeclaredVar();

    return new DeclareStatGenerator(visitAssignRHS(ctx.assignRHS()), state, type, offset, ident);
  }

  @Override
  public Generator visitAssignStat(AssignStatContext ctx) {
    return new AssignStatGenerator(
        ctx.assignLHS(),
        visitAssignRHS(ctx.assignRHS()),
        state,
        this,
        semanticVisitor.visit(ctx.assignLHS()));
  }

  @Override
  public Generator visitAssignRHS(AssignRHSContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public Generator visitArrayLiter(ArrayLiterContext ctx) {
    List<Generator> expressions = new ArrayList<>();
    for (ExprContext expr : ctx.expr()) {
      expressions.add(visitExpr(expr));
    }

    ParserRuleContext grandParent = ctx.getParent().getParent();

    Array array;

    if (grandParent instanceof DeclareStatContext) {
      array = (Array) semanticVisitor.visit(((DeclareStatContext) grandParent).type());
    } else {
      array = (Array) semanticVisitor.visit(((AssignStatContext) grandParent).assignLHS());
    }

    Type arrType = array.getElemType();

    return new ArrayLiteralGenerator(expressions, state, arrType);
  }

  @Override
  public Generator visitNewpair(NewpairContext ctx) {
    return new NewPairGenerator(state, visitExpr(ctx.expr(0)), visitExpr(ctx.expr(1)));
  }

  @Override
  public Generator visitPairElem(PairElemContext ctx) {
    return new PairElemGenerator(state, ctx.expr().IDENT().getText(), ctx.FST() != null);
  }

  @Override
  public Generator visitReadStat(ReadStatContext ctx) {
    return new ReadStatGenerator(ctx.assignLHS(), state, this);
  }

  @Override
  public Generator visitExitStat(ExitStatContext ctx) {
    return new ExitStatGenerator(this.visitExpr(ctx.expr()), state);
  }

  @Override
  public Generator visitReturnStat(ReturnStatContext ctx) {
    return new ReturnStatGenerator(this.visitExpr(ctx.expr()), state);
  }

  @Override
  public Generator visitFreeStat(FreeStatContext ctx) {
    return new FreeStatGenerator(visitExpr(ctx.expr()), state);
  }

  @Override
  public Generator visitPrintStat(PrintStatContext ctx) {
    ExprContext expr = ctx.expr();
    return new PrintStatGenerator(false, visitExpr(expr), expr, state, semanticVisitor);
  }

  @Override
  public Generator visitPrintlnStat(PrintlnStatContext ctx) {
    ExprContext expr = ctx.expr();
    return new PrintStatGenerator(true, visitExpr(expr), expr, state, semanticVisitor);
  }

  @Override
  public Generator visitBeginStat(BeginStatContext ctx) {
    Generator statGenerator = visit(ctx.stat());
    VariableVisitor variableVisitor = new VariableVisitor();
    int numVarsInScope = variableVisitor.visit(ctx.stat());

    return new BeginStatGenerator(state, statGenerator, numVarsInScope);
  }

  @Override
  public Generator visitIfStat(IfStatContext ctx) {
    VariableVisitor variableVisitor = new VariableVisitor();
    int numVarsInThenScope = variableVisitor.visit(ctx.stat(0));
    int numVarsInElseScope = variableVisitor.visit(ctx.stat(1));

    return new IfStatGenerator(
        visitExpr(ctx.expr()),
        visitStat(ctx.stat(0)),
        visitStat(ctx.stat(1)),
        state,
        numVarsInThenScope,
        numVarsInElseScope);
  }

  @Override
  public Generator visitWhileStat(WhileStatContext ctx) {
    VariableVisitor variableVisitor = new VariableVisitor();
    int numVarsInScope = variableVisitor.visit(ctx.stat());

    return new WhileStatGenerator(
        visitExpr(ctx.expr()), visitStat(ctx.stat()), state, numVarsInScope);
  }

  @Override
  public Generator visitExpr(ExprContext ctx) {
    ExpressionGenerator expressionGenerator = new ExpressionGenerator(state);

    if (ctx.OPEN_PARENTHESES() != null) {
      return visit(ctx.expr(0));
    }

    if (ctx.INT_LIT() != null) {
      if (ctx.PLUS() != null || ctx.MINUS() != null) {
        expressionGenerator.setExprType(SIGNED_INT_LITERAL);
        String sign = ctx.PLUS() != null ? "+" : "-";
        expressionGenerator.setOperand1(sign + ctx.INT_LIT().getText());
      } else {
        expressionGenerator.setExprType(INT_LITERAL);
        expressionGenerator.setOperand1(ctx.INT_LIT().getText());
      }
    } else if (ctx.BIN_INT_LIT() != null) {
      expressionGenerator.setExprType(INT_LITERAL);
      expressionGenerator.setOperand1(
          String.valueOf(Integer.parseInt(ctx.BIN_INT_LIT().getText().substring(2), 2)));
    } else if (ctx.OCT_INT_LIT() != null) {
      expressionGenerator.setExprType(INT_LITERAL);
      expressionGenerator.setOperand1(
          String.valueOf(Integer.parseInt(ctx.OCT_INT_LIT().getText().substring(2), 8)));
    } else if (ctx.HEX_INT_LIT() != null) {
      expressionGenerator.setExprType(INT_LITERAL);
      expressionGenerator.setOperand1(
          String.valueOf(Integer.parseInt(ctx.HEX_INT_LIT().getText().substring(2), 16)));
    } else if (ctx.BOOL_LIT() != null) {
      expressionGenerator.setExprType(BOOL_LITERAL);
      expressionGenerator.setOperand1(ctx.BOOL_LIT().getText());
    } else if (ctx.PAIR_LIT() != null) {
      expressionGenerator.setExprType(PAIR_LITERAL);
      expressionGenerator.setOperand1(ctx.PAIR_LIT().getText());
    } else if (ctx.CHAR_LIT() != null) {
      expressionGenerator.setExprType(CHAR_LITERAL);
      expressionGenerator.setOperand1(ctx.CHAR_LIT().getText());
    } else if (ctx.STRING_LIT() != null) {

      String msgNumber = "msg_" + state.getStringCounter();
      expressionGenerator.setExprType(STRING_LITERAL);
      expressionGenerator.setOperand1(msgNumber);
      state.addMessage(ctx.STRING_LIT().getText());

    } else if (ctx.IDENT() != null) {
      String ident = ctx.IDENT().getText();
      switch (ident) {
        case "true":
        case "false":
          expressionGenerator.setExprType(BOOL_LITERAL);
          break;
        case "null":
          expressionGenerator.setExprType(PAIR_LITERAL);
          break;
        default:
          expressionGenerator.setExprType(IDENTIFIER);
          expressionGenerator.setOp1IsIdent(true);
          break;
      }

      expressionGenerator.setOperand1(ident);
    } else if (ctx.arrayElem() != null) {
      expressionGenerator.setExprType(ARRAY_ELEMENT);
      expressionGenerator.setOperand1(ctx.arrayElem().IDENT().getText());
      expressionGenerator.setOperand2(ctx.arrayElem().expr(0).getText());
      expressionGenerator.setOp1IsIdent(true);
      expressionGenerator.setVisitor(this);
      expressionGenerator.setTree(ctx.arrayElem());
    } else if (ctx.unaryOper() != null) {
      expressionGenerator.setExprType(UNOP_EXPR);
      ExprContext expr = ctx.expr(0);

      UnaryOperContext unOp = ctx.unaryOper();
      OperatorType opType;

      if (unOp.NOT() != null) {
        opType = NOT;
      } else if (unOp.MINUS() != null) {
        opType = UNARY_MINUS;
      } else if (unOp.BITWISE_NOT() != null) {
        opType = BITWISE_NOT;
      } else if (unOp.LEN() != null) {
        opType = LEN;
      } else if (unOp.ORD() != null) {
        opType = ORD;
      } else {
        opType = CHR;
      }

      expressionGenerator.setOperatorType(opType);
      expressionGenerator.setOperand1(expr.getText());
      expressionGenerator.setOp1IsIdent(expr.IDENT() != null && isTrueIdent(expr));

    } else if (ctx.mulDivModOper() != null) {

      expressionGenerator.setExprType(BINOP_EXPR);
      MulDivModOperContext binOp = ctx.mulDivModOper();
      OperatorType opType;

      if (binOp.MULT() != null) {
        opType = MULT;
      } else if (binOp.DIVIDE() != null) {
        opType = DIVIDE;
      } else {
        opType = MOD;
      }

      // extension - constant evaluation
      if (ctx.expr(0).INT_LIT() != null
          && ctx.expr(1).INT_LIT() != null
          && !((opType == DIVIDE || opType == MOD)
              && Integer.parseInt(ctx.expr(1).getText()) == 0)) {
        int lhs = Integer.parseInt(ctx.expr(0).getText());
        int rhs = Integer.parseInt(ctx.expr(1).getText());
        int eval = 0;

        switch (opType) {
          case MULT:
            eval = lhs * rhs;
            break;
          case DIVIDE:
            eval = lhs / rhs;
            break;
          case MOD:
            eval = lhs % rhs;
            break;
        }

        expressionGenerator.setExprType(INT_LITERAL);
        expressionGenerator.setOperand1(Integer.toString(eval));
        return expressionGenerator;
      }

      expressionGenerator.setOperatorType(opType);

      visitBinOp(expressionGenerator, ctx);
    } else if (ctx.plusMinusOper() != null) {

      expressionGenerator.setExprType(BINOP_EXPR);
      PlusMinusOperContext binOp = ctx.plusMinusOper();
      OperatorType opType;

      if (binOp.PLUS() != null) {
        opType = PLUS;
      } else {
        opType = BINARY_MINUS;
      }

      // extension - constant evaluation
      if (ctx.expr(0).INT_LIT() != null && ctx.expr(1).INT_LIT() != null) {
        int lhs = Integer.parseInt(ctx.expr(0).getText());
        int rhs = Integer.parseInt(ctx.expr(1).getText());
        int eval = 0;

        switch (opType) {
          case PLUS:
            eval = lhs + rhs;
            break;
          case BINARY_MINUS:
            eval = lhs - rhs;
            break;
        }

        expressionGenerator.setExprType(INT_LITERAL);
        expressionGenerator.setOperand1(Integer.toString(eval));
        return expressionGenerator;
      }

      expressionGenerator.setOperatorType(opType);

      visitBinOp(expressionGenerator, ctx);
    } else if (ctx.compOper() != null) {

      expressionGenerator.setExprType(BINOP_EXPR);
      CompOperContext binOp = ctx.compOper();
      OperatorType opType;

      if (binOp.GT() != null) {
        opType = GREATER_THAN;
      } else if (binOp.GTE() != null) {
        opType = GREATER_THAN_EQ;
      } else if (binOp.ST() != null) {
        opType = SMALLER_THAN;
      } else {
        opType = SMALLER_THAN_EQ;
      }

      // extension - constant evaluation
      if (ctx.expr(0).INT_LIT() != null && ctx.expr(1).INT_LIT() != null) {
        int lhs = Integer.parseInt(ctx.expr(0).getText());
        int rhs = Integer.parseInt(ctx.expr(1).getText());
        boolean eval = false;

        switch (opType) {
          case GREATER_THAN:
            eval = lhs > rhs;
            break;
          case GREATER_THAN_EQ:
            eval = lhs >= rhs;
            break;
          case SMALLER_THAN:
            eval = lhs < rhs;
            break;
          case SMALLER_THAN_EQ:
            eval = lhs <= rhs;
            break;
        }

        expressionGenerator.setExprType(BOOL_LITERAL);
        expressionGenerator.setOperand1(Boolean.toString(eval));
        return expressionGenerator;
      }

      expressionGenerator.setOperatorType(opType);

      visitBinOp(expressionGenerator, ctx);
    } else if (ctx.eqOper() != null) {
      expressionGenerator.setExprType(BINOP_EXPR);
      EqOperContext binOp = ctx.eqOper();
      OperatorType opType;

      if (binOp.EQ() != null) {
        opType = EQ;
      } else {
        opType = NOT_EQ;
      }

      // extension - constant evaluation
      if (ctx.expr(0).INT_LIT() != null && ctx.expr(1).INT_LIT() != null) {
        int lhs = Integer.parseInt(ctx.expr(0).getText());
        int rhs = Integer.parseInt(ctx.expr(1).getText());
        boolean eval = false;

        switch (opType) {
          case EQ:
            eval = lhs == rhs;
            break;
          case NOT_EQ:
            eval = lhs != rhs;
            break;
        }

        expressionGenerator.setExprType(BOOL_LITERAL);
        expressionGenerator.setOperand1(Boolean.toString(eval));
        return expressionGenerator;
      }

      expressionGenerator.setOperatorType(opType);

      visitBinOp(expressionGenerator, ctx);

      // extension - bitwise operator
    } else if (ctx.bitwiseAndOper() != null) {

      // extension - constant evaluation
      if (ctx.expr(0).INT_LIT() != null && ctx.expr(1).INT_LIT() != null) {
        int lhs = Integer.parseInt(ctx.expr(0).getText());
        int rhs = Integer.parseInt(ctx.expr(1).getText());
        int eval = lhs & rhs;

        expressionGenerator.setExprType(INT_LITERAL);
        expressionGenerator.setOperand1(Integer.toString(eval));
        return expressionGenerator;
      }

      expressionGenerator.setExprType(BINOP_EXPR);
      expressionGenerator.setOperatorType(BITWISE_AND);

      visitBinOp(expressionGenerator, ctx);

      // extension - bitwise operator
    } else if (ctx.bitwiseXorOper() != null) {

      // extension - constant evaluation
      if (ctx.expr(0).INT_LIT() != null && ctx.expr(1).INT_LIT() != null) {
        int lhs = Integer.parseInt(ctx.expr(0).getText());
        int rhs = Integer.parseInt(ctx.expr(1).getText());
        int eval = lhs ^ rhs;

        expressionGenerator.setExprType(INT_LITERAL);
        expressionGenerator.setOperand1(Integer.toString(eval));
        return expressionGenerator;
      }

      expressionGenerator.setExprType(BINOP_EXPR);
      expressionGenerator.setOperatorType(BITWISE_XOR);

      visitBinOp(expressionGenerator, ctx);

      // extension - bitwise operator
    } else if (ctx.bitwiseOrOper() != null) {

      // extension - constant evaluation
      if (ctx.expr(0).INT_LIT() != null && ctx.expr(1).INT_LIT() != null) {
        int lhs = Integer.parseInt(ctx.expr(0).getText());
        int rhs = Integer.parseInt(ctx.expr(1).getText());
        int eval = lhs | rhs;

        expressionGenerator.setExprType(INT_LITERAL);
        expressionGenerator.setOperand1(Integer.toString(eval));
        return expressionGenerator;
      }

      expressionGenerator.setExprType(BINOP_EXPR);
      expressionGenerator.setOperatorType(BITWISE_OR);

      visitBinOp(expressionGenerator, ctx);
    } else if (ctx.andOper() != null) {

      // extension - lazy boolean evaluation
      if (ctx.expr(0).getText().equals("false") || ctx.expr(1).getText().equals("false")) {
        expressionGenerator.setExprType(BOOL_LITERAL);
        expressionGenerator.setOperand1("false");
        return expressionGenerator;
      }

      // extension - constant evaluation
      if ((ctx.expr(0).BOOL_LIT() != null && ctx.expr(1).BOOL_LIT() != null)
          || !(isTrueIdent(ctx.expr(0)) || isTrueIdent(ctx.expr(1)))) {
        boolean lhs = Boolean.parseBoolean(ctx.expr(0).getText());
        boolean rhs = Boolean.parseBoolean(ctx.expr(1).getText());
        boolean eval = lhs && rhs;

        expressionGenerator.setExprType(BOOL_LITERAL);
        expressionGenerator.setOperand1(Boolean.toString(eval));
        return expressionGenerator;
      }

      expressionGenerator.setExprType(BINOP_EXPR);
      expressionGenerator.setOperatorType(AND);

      visitBinOp(expressionGenerator, ctx);
    } else if (ctx.orOper() != null) {

      // extension - lazy boolean evaluation
      if (ctx.expr(0).getText().equals("true") || ctx.expr(1).getText().equals("true")) {
        expressionGenerator.setExprType(BOOL_LITERAL);
        expressionGenerator.setOperand1("true");
        return expressionGenerator;
      }

      // extension - constant evaluation
      if ((ctx.expr(0).BOOL_LIT() != null && ctx.expr(1).BOOL_LIT() != null)
          || !(isTrueIdent(ctx.expr(0)) || isTrueIdent(ctx.expr(1)))) {
        boolean lhs = Boolean.parseBoolean(ctx.expr(0).getText());
        boolean rhs = Boolean.parseBoolean(ctx.expr(1).getText());
        boolean eval = lhs || rhs;

        expressionGenerator.setExprType(BOOL_LITERAL);
        expressionGenerator.setOperand1(Boolean.toString(eval));
        return expressionGenerator;
      }

      expressionGenerator.setExprType(BINOP_EXPR);
      expressionGenerator.setOperatorType(OR);

      visitBinOp(expressionGenerator, ctx);
    }

    return expressionGenerator;
  }

  public void visitBinOp(ExpressionGenerator expressionGenerator, ExprContext ctx) {

    String operand1 = ctx.expr(0).getText();
    String operand2 = ctx.expr(1).getText();

    if (ctx.expr(0).getChildCount() == 1) {
      expressionGenerator.setOperand1(operand1);
    } else {
      state.freeRegister(state.getResultRegister());
      Generator innerGenerator = visit(ctx.expr(0));
      expressionGenerator.setLhsInnerGenerator(innerGenerator);
    }

    if (ctx.expr(1).getChildCount() == 1) {
      expressionGenerator.setOperand2(operand2);
    } else {
      state.freeRegister(state.getResultRegister());
      Generator innerGenerator = visit(ctx.expr(1));
      expressionGenerator.setRhsInnerGenerator(innerGenerator);
    }

    ExprContext lhs = ctx.expr(0);
    ExprContext rhs = ctx.expr(1);

    if (lhs.OPEN_PARENTHESES() != null) {
      ExpressionGenerator left = (ExpressionGenerator) visit(lhs.expr(0));
      expressionGenerator.setLhsBracketGen(left);
      expressionGenerator.setOp1IsBracket(true);
      if (rhs.OPEN_PARENTHESES() != null) {
        ExpressionGenerator right = (ExpressionGenerator) visit(rhs.expr(0));
        expressionGenerator.setRhsBracketGen(right);
        expressionGenerator.setOp2IsBracket(true);
      }
    } else if (rhs.OPEN_PARENTHESES() != null) {
      ExpressionGenerator right = (ExpressionGenerator) visit(rhs.expr(0));
      expressionGenerator.setRhsBracketGen(right);
      expressionGenerator.setOp2IsBracket(true);
    }

    expressionGenerator.setOp1IsIdent(lhs.IDENT() != null && isTrueIdent(lhs));
    expressionGenerator.setOp2IsIdent(rhs.IDENT() != null && isTrueIdent(rhs));

    expressionGenerator.setOp1Type(semanticVisitor.visit(lhs));
    expressionGenerator.setOp2Type(semanticVisitor.visit(rhs));
  }

  public boolean isTrueIdent(ExprContext ctx) {
    return !(ctx.getText().equals("true")
        || ctx.getText().equals("false")
        || ctx.getText().equals("null"));
  }

  // extension - void function returns
  @Override
  public Generator visitVoidFuncCallStat(VoidFuncCallStatContext ctx) {
    return visit(ctx.functionCall());
  }
}
