package generators;

import java.util.List;
import visitors.State;

public class ExitStatGenerator implements Generator {

  private Generator expr;
  private State state;

  public ExitStatGenerator(Generator expr, State state) {
    this.expr = expr;
    this.state = state;
  }

  @Override
  public List<Instruction> generate() {
    // exit using the generated expressions
    List<Instruction> instructions = expr.generate();
    instructions.add(new Instruction("MOV r0, " + state.getResultRegister()));
    state.freeRegister(state.getResultRegister());
    instructions.add(new Instruction("BL exit"));

    return instructions;
  }
}
