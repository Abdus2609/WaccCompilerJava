package generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import visitors.State;

public class ExprIdentGenerator implements Generator {

  private final State state;
  private final String value;
  private final Optional<String> availReg;
  private final boolean isBoolean;
  private final boolean isChar;

  public ExprIdentGenerator(
      State state, String value, Optional<String> availReg, boolean isBoolean, boolean isChar) {
    this.state = state;
    this.value = value;
    this.availReg = availReg;
    this.isBoolean = isBoolean;
    this.isChar = isChar;
  }

  @Override
  public List<Instruction> generate() {

    List<Instruction> instructions = new ArrayList<>();

    String instruction = isBoolean || isChar ? "LDRSB " : "LDR ";
    int offset = state.getAddress(value);
    String constant = ", [sp" + (offset == 0 ? "]" : ", #" + offset + "]");
    if (availReg.isPresent()) {
      state.setResultRegister(availReg.get());
      String usedReg = state.getResultRegister();
      instructions.add(new Instruction(instruction + usedReg + constant));
      state.allocRegister(usedReg);
    } else {
      String reg10 = "reg10";
      instructions.add(new Instruction("PUSH {" + reg10 + "}"));
      state.freeRegister(reg10);
      state.addPush();
      instructions.add(new Instruction(instruction + reg10 + constant));
      state.allocRegister(reg10);
      state.setResultRegister(reg10);
    }

    return instructions;
  }
}
