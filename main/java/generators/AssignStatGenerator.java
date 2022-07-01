package generators;

import antlr.BasicParser;
import antlr.BasicParser.AssignLHSContext;
import antlr.BasicParser.ExprContext;
import antlr.BasicParser.PairElemContext;
import types.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import visitors.InterpretVisitor;
import visitors.State;

public class AssignStatGenerator implements Generator {

  private final AssignLHSContext assignLHSContext;
  private final Generator assignRHS;
  private final State state;
  private final InterpretVisitor visitor;
  private final Type type;

  public AssignStatGenerator(
      AssignLHSContext assignLHSContext,
      Generator assignRHS,
      State state,
      InterpretVisitor visitor,
      Type type) {
    this.assignLHSContext = assignLHSContext;
    this.assignRHS = assignRHS;
    this.state = state;
    this.visitor = visitor;
    this.type = type;
  }

  @Override
  public List<Instruction> generate() {
    List<Instruction> instructions = assignRHS.generate();

    int offset;
    String resultReg = state.getResultRegister();

    // check if lhs is arrayElem, pairElem or IDENT
    if (assignLHSContext.arrayElem() != null) {
      offset = state.getAddress(assignLHSContext.arrayElem().IDENT().getText());
      instructions.addAll(getArrayAddress(assignLHSContext.arrayElem(), offset));
    } else if (assignLHSContext.pairElem() != null) {
      instructions.addAll(getPairAddress(assignLHSContext.pairElem()));
    } else {
      offset = state.getAddress(assignLHSContext.IDENT().getText());
      if (offset == 0) {
        instructions.add(new Instruction("STR " + resultReg + ", [sp]"));
      } else {
        instructions.add(new Instruction("STR " + resultReg + ", [sp, #" + offset + "]"));
      }

      state.freeRegister(resultReg);
      state.freeRegister(state.getResultRegister());
      return instructions;
    }

    // store variable in a register
    if (type.toString().equals("char") || type.toString().equals("bool")) {
      instructions.add(
          new Instruction("STRB " + resultReg + ", [" + state.getResultRegister() + "]"));
    } else {
      instructions.add(
          new Instruction("STR " + resultReg + ", [" + state.getResultRegister() + "]"));
    }

    state.freeRegister(resultReg);
    state.freeRegister(state.getResultRegister());

    return instructions;
  }

  // get the pointer to the array, stored in resultRegister
  public List<Instruction> getArrayAddress(
      BasicParser.ArrayElemContext arrayElemContext, int offset) {
    List<Instruction> instructions = new ArrayList<>();
    Optional<String> availReg = state.getAvailableRegister();
    String register;

    if (availReg.isPresent()) {
      register = availReg.get();
    } else {
      register = "r10";
      instructions.add(new Instruction("PUSH {" + register + "}"));
      state.setResultRegister("r10");
    }

    state.allocRegister(register);

    instructions.add(new Instruction("ADD " + register + ", sp, #" + offset));

    if (!arrayElemContext.expr().isEmpty()) {
      // iterate through the pointers for nested arrays
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

      state.freeRegister(state.getResultRegister());
    }

    state.setResultRegister(register);
    return instructions;
  }

  // get the pointer to the pair, stored in resultRegister
  public List<Instruction> getPairAddress(PairElemContext pairElemContext) {

    List<Instruction> instructions =
        new ArrayList<>(visitor.visitExpr(pairElemContext.expr()).generate());

    String register = state.getResultRegister();

    instructions.add(new Instruction("MOV r0, " + register));
    instructions.add(new Instruction("BL p_check_null_pointer"));
    state.addErrorFunction("p_check_null_pointer");

    if (pairElemContext.FST() != null) {
      instructions.add(new Instruction("LDR " + register + ", [" + register + "]"));
    } else {
      instructions.add(new Instruction("LDR " + register + ", [" + register + ", #4]"));
    }

    return instructions;
  }
}
