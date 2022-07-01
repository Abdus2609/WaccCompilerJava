package generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import visitors.State;

public class ExprLiteralGenerator implements Generator {

  private final State state;
  private String value;
  private final Optional<String> availReg;
  private final boolean isBoolean;
  private final boolean isChar;

  public ExprLiteralGenerator(
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

    if (availReg.isPresent()) {
      String resultReg = availReg.get();
      String instruction = (isBoolean || isChar) ? "MOV " : "LDR ";
      String operand = "";
      if (isBoolean) {
        operand = value.equals("true") ? "#1" : "#0";
      } else if (value.equals("'\\0'")) {
        operand = "#0";
      } else {
        if (!isChar && Character.isDigit(value.charAt(0))) {
          value = Integer.toString(Integer.parseInt(value));
        }
        operand = (instruction.equals("MOV ") ? "#" : "=") + value;
      }
      instructions.add(new Instruction(instruction + resultReg + ", " + operand));
      state.allocRegister(resultReg);
      state.setResultRegister(resultReg);
    } else {
      String reg10 = "r10";
      instructions.add(new Instruction("PUSH {" + reg10 + "}"));
      state.addPush();
      state.freeRegister(reg10);
      String instruction = isBoolean ? "MOV " : "LDR ";
      String constant = isBoolean ? ", " + value : ", =" + value;
      instructions.add(new Instruction(instruction + reg10 + constant));
      state.allocRegister(reg10);
      state.setResultRegister(reg10);
    }

    return instructions;
  }
}
