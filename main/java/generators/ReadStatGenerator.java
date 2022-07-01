package generators;

import antlr.BasicParser;
import antlr.BasicParser.AssignLHSContext;
import antlr.BasicParser.ExprContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import types.Array;
import types.Pair;
import types.Type;
import visitors.InterpretVisitor;
import visitors.State;

public class ReadStatGenerator implements Generator {

  private final AssignLHSContext assignLHSContext;
  private final State state;
  private final InterpretVisitor visitor;

  public ReadStatGenerator(
      AssignLHSContext assignLHSContext, State state, InterpretVisitor visitor) {
    this.assignLHSContext = assignLHSContext;
    this.state = state;
    this.visitor = visitor;
  }

  @Override
  public List<Instruction> generate() {
    List<Instruction> instructions = new ArrayList<>();

    int offset;

    Type type = new Type();

    if (assignLHSContext.arrayElem() != null) {
      offset = state.getAddress(assignLHSContext.arrayElem().IDENT().getText());
      type =
          ((Array) state.getType(assignLHSContext.arrayElem().IDENT().getText()))
              .getBaseArrayType();
      instructions.addAll(getArrayAddress(assignLHSContext.arrayElem(), offset, type));
      instructions.add(new Instruction("MOV r0, " + state.getResultRegister()));
    } else if (assignLHSContext.pairElem() != null) {
      instructions.addAll(getPairAddress(assignLHSContext.pairElem(), type));
      instructions.add(new Instruction("MOV r0, " + state.getResultRegister()));
    } else {
      Optional<String> availReg = state.getAvailableRegister();
      String register;

      if (availReg.isPresent()) {
        register = availReg.get();
      } else {
        register = "r10";
        state.freeRegister(register);
        instructions.add(new Instruction("PUSH {" + register + "}"));
        state.setResultRegister(register);
        state.allocRegister(register);
      }

      offset = state.getAddress(assignLHSContext.IDENT().getText());
      instructions.add(new Instruction("ADD " + register + ", sp, #" + offset));
      instructions.add(new Instruction("MOV r0, " + register));

      type = state.getType(assignLHSContext.IDENT().getText());
    }

    if (type.toString().equals("int")) {
      instructions.add(new Instruction("BL p_read_int"));
      state.addBuiltInFunction("p_read_int");
    } else {
      instructions.add(new Instruction("BL p_read_char"));
      state.addBuiltInFunction("p_read_char");
    }

    return instructions;
  }

  public List<Instruction> getArrayAddress(
      BasicParser.ArrayElemContext arrayElemContext, int offset, Type type) {
    List<Instruction> instructions = new ArrayList<>();
    Optional<String> availReg = state.getAvailableRegister();
    String register;

    if (availReg.isPresent()) {
      register = availReg.get();
    } else {
      register = "r10";
      instructions.add(new Instruction("PUSH {" + register + "}"));
      state.setResultRegister(register);
      state.freeRegister(register);
    }

    state.allocRegister(register);

    instructions.add(new Instruction("ADD " + register + ", sp, #" + offset));

    if (!arrayElemContext.expr().isEmpty()) {
      for (int i = 0; i < arrayElemContext.expr().size() - 1; i++) {
        ExprContext expr = arrayElemContext.expr().get(i);
        instructions.addAll(visitor.visitExpr(expr).generate());
        instructions.add(new Instruction("LDR " + register + ", [" + register + "]"));
        instructions.add(new Instruction("MOV r0, " + state.getResultRegister()));
        instructions.add(new Instruction("MOV r1, " + register));
        instructions.add(new Instruction("BL p_check_array_bounds"));
        state.addErrorFunction("p_check_array_bounds");
        instructions.add(new Instruction("ADD " + register + ", " + register + ", #4"));
        instructions.add(
            new Instruction(
                "ADD "
                    + register
                    + ", "
                    + register
                    + ", "
                    + state.getResultRegister()
                    + ", LSL #2"));
        state.freeRegister(state.getResultRegister());
      }

      ExprContext finalExpr = arrayElemContext.expr().get(arrayElemContext.expr().size() - 1);
      instructions.addAll(visitor.visitExpr(finalExpr).generate());
      instructions.add(new Instruction("LDR " + register + ", [" + register + "]"));
      instructions.add(new Instruction("MOV r0, " + state.getResultRegister()));
      instructions.add(new Instruction("MOV r1, " + register));
      instructions.add(new Instruction("BL p_check_array_bounds"));
      state.addErrorFunction("p_check_array_bounds");
      instructions.add(new Instruction("ADD " + register + ", " + register + ", #4"));
      if (type.toString().equals("char")) {
        instructions.add(
            new Instruction(
                "ADD " + register + ", " + register + ", " + state.getResultRegister()));
      } else {
        instructions.add(
            new Instruction(
                "ADD "
                    + register
                    + ", "
                    + register
                    + ", "
                    + state.getResultRegister()
                    + ", LSL #2"));
      }
    }

    state.freeRegister(state.getResultRegister());

    state.setResultRegister(register);
    return instructions;
  }

  public List<Instruction> getPairAddress(BasicParser.PairElemContext pairElemContext, Type type) {

    List<Instruction> instructions =
        new ArrayList<>(visitor.visitExpr(pairElemContext.expr()).generate());

    String register = state.getResultRegister();

    instructions.add(new Instruction("MOV r0, " + register));
    instructions.add(new Instruction("BL p_check_null_pointer"));
    state.addErrorFunction("p_check_null_pointer");

    if (pairElemContext.FST() != null) {
      instructions.add(new Instruction("LDR " + register + ", [" + register + "]"));
      type = ((Pair) state.getType(pairElemContext.expr().IDENT().getText())).fst();
    } else {
      instructions.add(new Instruction("LDR " + register + ", [" + register + ", #4]"));
      type = ((Pair) state.getType(pairElemContext.expr().IDENT().getText())).snd();
    }

    return instructions;
  }
}
