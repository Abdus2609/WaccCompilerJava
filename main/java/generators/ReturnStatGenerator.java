package generators;

import visitors.State;

import java.util.List;

public class ReturnStatGenerator implements Generator {
  private Generator expr;
  private State state;

  public ReturnStatGenerator(Generator expr, State state) {
    this.expr = expr;
    this.state = state;
  }

  @Override
  public List<Instruction> generate() {
    List<Instruction> instructions = expr.generate();
    instructions.add(new Instruction("MOV r0, " + state.getResultRegister()));
    state.freeRegister(state.getResultRegister());
    if (state.getNumDeclares() != 0) {
      instructions.add(new Instruction("ADD sp, sp, #" + state.getNumDeclares() * 4));
    }

    Instruction pop = new Instruction("POP {pc}");
    instructions.add(pop);
    return instructions;
  }
}
