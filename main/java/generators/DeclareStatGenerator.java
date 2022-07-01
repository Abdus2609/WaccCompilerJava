package generators;

import java.util.List;
import types.Type;
import visitors.State;

public class DeclareStatGenerator implements Generator {

  private int offset;
  private Generator assignRHS;
  private State state;
  private Type type;
  private String ident;

  public DeclareStatGenerator(
      Generator assignRHS, State state, Type type, int offset, String ident) {
    this.assignRHS = assignRHS;
    this.state = state;
    this.type = type;
    this.offset = offset;
    this.ident = ident;
  }

  @Override
  public List<Instruction> generate() {
    List<Instruction> assignInstructions = assignRHS.generate();
    String result = state.getResultRegister();

    state.addVariable(ident, offset, type);

    // store the result of assignRHS in the requested address
    StringBuilder sb = new StringBuilder();
    String instruction =
        (type.toString().equals("bool") || type.toString().equals("char")) ? "STRB " : "STR ";
    sb.append(instruction).append(result).append(", ");

    if (offset + state.getStackPointer() == 0) {
      sb.append("[sp]");
    } else {
      sb.append("[sp, #").append(offset + state.getStackPointer()).append("]");
    }

    state.freeRegister(result);

    assignInstructions.add(new Instruction(sb.toString()));

    return assignInstructions;
  }
}
