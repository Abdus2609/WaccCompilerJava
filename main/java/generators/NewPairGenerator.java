package generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import visitors.State;

public class NewPairGenerator implements Generator {
  private final State state;
  private final Generator expr1;
  private final Generator expr2;

  public NewPairGenerator(State state, Generator expr1, Generator expr2) {
    this.state = state;
    this.expr1 = expr1;
    this.expr2 = expr2;
  }

  @Override
  public List<Instruction> generate() {
    List<Instruction> instructions = new ArrayList<>();

    instructions.add(new Instruction("LDR r0, =8"));
    instructions.add(new Instruction("BL malloc"));

    Optional<String> availReg = state.getAvailableRegister();
    String pairPointer;
    if (availReg.isPresent()) {
      pairPointer = availReg.get();
      instructions.add(new Instruction("MOV " + pairPointer + ", r0"));
      state.allocRegister(pairPointer);
    } else {
      String reg10 = "r10";
      pairPointer = reg10;
      instructions.add(new Instruction("PUSH {" + pairPointer + "}"));
      state.freeRegister(pairPointer);
      instructions.add(new Instruction("MOV " + pairPointer + ", r0"));
      state.allocRegister(pairPointer);
      state.setResultRegister(reg10);
    }

    // store the first value
    instructions.addAll(expr1.generate());

    instructions.add(new Instruction("LDR r0, =4"));
    instructions.add(new Instruction("BL malloc"));

    instructions.add(new Instruction("STR " + state.getResultRegister() + ", [r0]"));
    state.freeRegister(state.getResultRegister());
    instructions.add(new Instruction("STR r0, [" + pairPointer + "]"));

    // store the second value
    instructions.addAll(expr2.generate());

    instructions.add(new Instruction("LDR r0, =4"));
    instructions.add(new Instruction("BL malloc"));

    instructions.add(new Instruction("STR " + state.getResultRegister() + ", [r0]"));
    state.freeRegister(state.getResultRegister());
    instructions.add(new Instruction("STR r0, [" + pairPointer + ", #4]"));

    state.setResultRegister(pairPointer);

    return instructions;
  }
}
