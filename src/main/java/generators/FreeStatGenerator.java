package generators;

import java.util.List;
import visitors.State;

public class FreeStatGenerator implements Generator {

  private final Generator expr;
  private final State state;

  public FreeStatGenerator(Generator expr, State state) {
    this.expr = expr;
    this.state = state;
  }

  @Override
  public List<Instruction> generate() {
    // free the pair retrieved from the expression
    List<Instruction> instructions = expr.generate();

    instructions.add(new Instruction("MOV r0, " + state.getResultRegister()));

    instructions.add(new Instruction("BL p_free_pair"));
    state.addErrorFunction("p_free_pair");

    state.freeRegister(state.getResultRegister());

    return instructions;
  }
}
