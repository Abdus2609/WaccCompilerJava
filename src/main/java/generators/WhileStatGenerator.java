package generators;

import visitors.State;

import java.util.ArrayList;
import java.util.List;

public class WhileStatGenerator implements Generator {

  private Generator expr;
  private Generator stat;
  private State state;
  private int numVarsInScope;

  public WhileStatGenerator(Generator expr, Generator stat, State state, int numVarsInScope) {
    this.expr = expr;
    this.stat = stat;
    this.state = state;
    this.numVarsInScope = numVarsInScope;
  }

  @Override
  public List<Instruction> generate() {
    String branch1 = state.getNewBranchName();
    String branch2 = state.getNewBranchName();
    List<Instruction> instructions = new ArrayList<>();

    instructions.add(new Instruction("B " + branch1));

    instructions.add(new Instruction(branch2 + ":"));

    if (stat != null) {
      int offset = 4 * numVarsInScope;
      state.updateStackPointer(offset);

      if (numVarsInScope != 0) {
        instructions.add(new Instruction("SUB sp, sp, #" + offset));
      }

      int preDeclared = state.getDeclaredVars();
      int preNumVars = state.getNumVars();
      state.enterScope();

      instructions.addAll(stat.generate());

      state.exitScope();
      state.updateStackPointer(-offset);
      state.setDeclaredVars(preDeclared);
      state.setNumVars(preNumVars);

      if (numVarsInScope != 0) {
        instructions.add(new Instruction("ADD sp, sp, #" + offset));
      }
    }

    instructions.add(new Instruction(branch1 + ":"));

    instructions.addAll(expr.generate());

    instructions.add(new Instruction("CMP " + state.getResultRegister() + ", #1"));
    state.freeRegister(state.getResultRegister());

    instructions.add(new Instruction("BEQ " + branch2));

    return instructions;
  }
}
