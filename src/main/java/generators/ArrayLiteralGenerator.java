package generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import types.Type;
import visitors.State;

public class ArrayLiteralGenerator implements Generator {

  private final State state;
  private final List<Generator> expressions;
  private Type arrType;
  private static final int INT_OFFSET = 4;
  private static final int CHAR_OFFSET = 1;

  public ArrayLiteralGenerator(List<Generator> expressions, State state, Type arrType) {
    this.expressions = expressions;
    this.state = state;
    this.arrType = arrType;
  }

  @Override
  public List<Instruction> generate() {
    List<Instruction> instructions = new ArrayList<>();

    int offset;

    if (arrType.toString().equals("char")) {
      offset = CHAR_OFFSET;
    } else {
      offset = INT_OFFSET;
    }

    instructions.add(new Instruction("LDR r0, =" + ((expressions.size()) * offset + INT_OFFSET)));
    instructions.add(new Instruction("BL malloc"));

    Optional<String> availReg = state.getAvailableRegister();
    String arrayPointer = "";

    // use a register to store the pointer to the array
    if (availReg.isPresent()) {
      instructions.add(new Instruction("MOV " + availReg.get() + ", r0"));
      state.allocRegister(availReg.get());
      state.setResultRegister(availReg.get());
      arrayPointer = availReg.get();
    } else {
      String reg10 = "r10";
      instructions.add(new Instruction("PUSH {" + reg10 + "}"));
      instructions.add(new Instruction("MOV " + reg10 + ", r0"));

      state.setResultRegister(reg10);
    }

    // store each of the expressions
    for (int i = 0; i < expressions.size(); i++) {
      Generator expr = expressions.get(i);
      instructions.addAll(expr.generate());
      instructions.add(
          new Instruction(
              "STR "
                  + state.getResultRegister()
                  + ", ["
                  + arrayPointer
                  + ", #"
                  + (i * offset + INT_OFFSET)
                  + "]"));
      state.freeRegister(state.getResultRegister());
    }

    // add the length of the array to arr[0]
    if (expressions.isEmpty()) {
      instructions.add(
          new Instruction(
              "LDR " + state.getAvailableRegister().get() + ", =" + expressions.size()));
      instructions.add(
          new Instruction(
              "STR " + state.getAvailableRegister().get() + ", [" + arrayPointer + "]"));
    } else {
      instructions.add(
          new Instruction("LDR " + state.getResultRegister() + ", =" + expressions.size()));
      instructions.add(
          new Instruction("STR " + state.getResultRegister() + ", [" + arrayPointer + "]"));
    }

    state.setResultRegister(arrayPointer);

    return instructions;
  }
}
