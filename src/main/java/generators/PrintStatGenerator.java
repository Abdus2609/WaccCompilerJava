package generators;

import antlr.BasicParser;
import antlr.BasicParser.ExprContext;
import java.util.List;
import types.Pair;
import types.Array;
import types.Type;
import types.WaccBool;
import visitors.SemanticAnalyser;
import visitors.State;

public class PrintStatGenerator implements Generator {

  private final boolean println;
  private final Generator expr;
  private final ExprContext exprContext;
  private final State state;
  private final SemanticAnalyser semanticVisitor;

  public PrintStatGenerator(
      boolean println,
      Generator expr,
      ExprContext exprContext,
      State state,
      SemanticAnalyser semanticVisitor) {
    this.println = println;
    this.expr = expr;
    this.exprContext = exprContext;
    this.state = state;
    this.semanticVisitor = semanticVisitor;
  }

  @Override
  public List<Instruction> generate() {

    Type type;

    if (exprContext.IDENT() != null) {
      if (exprContext.IDENT().getText().equals("true")
          || exprContext.IDENT().getText().equals("false")) {
        type = new WaccBool();
      } else if (exprContext.IDENT().getText().equals("null")) {
        type = new Pair(null, null);
      } else {
        type = state.getType(exprContext.IDENT().getText());
      }
    } else {
      type = semanticVisitor.visitExpr(exprContext);
    }

    if (exprContext.arrayElem() != null && type.toString().equals("array")) {
      type = ((Array) type).getBaseArrayType();
    }

    List<Instruction> instructions = expr.generate();
    String register = state.getResultRegister();

    instructions.add(new Instruction("MOV r0, " + register));

    state.freeRegister(register);

    if (type.toString().equals("int")) {
      instructions.add(new Instruction("BL p_print_int"));
      state.addBuiltInFunction("p_print_int");
    } else if (type.toString().equals("bool")) {
      instructions.add(new Instruction("BL p_print_bool"));
      state.addBuiltInFunction("p_print_bool");
    } else if (type.toString().equals("string")
        || (type.toString().equals("array")
            && ((Array) type).getBaseArrayType().toString().equals("char"))) {
      instructions.add(new Instruction("BL p_print_string"));
      state.addBuiltInFunction("p_print_string");
    } else if (type.toString().equals("char")) {
      instructions.add(new Instruction("BL putchar"));
    } else {
      instructions.add(new Instruction("BL p_print_reference"));
      state.addBuiltInFunction("p_print_reference");
    }

    if (println) {
      instructions.add(new Instruction("BL p_print_ln"));
      state.addBuiltInFunction("p_print_ln");
    }

    return instructions;
  }
}
