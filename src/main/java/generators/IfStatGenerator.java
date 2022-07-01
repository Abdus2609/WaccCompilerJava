package generators;

import visitors.State;

import java.util.List;

public class IfStatGenerator implements Generator {
  private Generator expr;
  private Generator stat1;
  private Generator stat2;
  private State state;
  private int numVarsInThenScope;
  private int numVarsInElseScope;

  private static final int INT_OFFSET = 4;

  public IfStatGenerator(
      Generator expr,
      Generator stat1,
      Generator stat2,
      State state,
      int numVarsInThenScope,
      int numVarsInElseScope) {
    this.expr = expr;
    this.stat1 = stat1;
    this.stat2 = stat2;
    this.state = state;
    this.numVarsInThenScope = numVarsInThenScope;
    this.numVarsInElseScope = numVarsInElseScope;
  }

  @Override
  public List<Instruction> generate() {
    List<Instruction> instructions = expr.generate();

    int offset = 0;

    instructions.add(new Instruction("CMP " + state.getResultRegister() + ", #0"));
    state.freeRegister(state.getResultRegister());

    String branch1 = state.getNewBranchName();
    instructions.add(new Instruction("BEQ " + branch1));

    if (stat1 != null) {
      offset = numVarsInThenScope * INT_OFFSET;
      state.updateStackPointer(offset);

      if (offset != 0) {
        instructions.add(new Instruction("SUB sp, sp, #" + offset));
      }

      int preDeclared = state.getDeclaredVars();
      int preNumVars = state.getNumVars();
      state.enterScope();

      instructions.addAll(stat1.generate());

      state.exitScope();
      state.updateStackPointer(-offset);
      state.setDeclaredVars(preDeclared);
      state.setNumVars(preNumVars);

      if (numVarsInThenScope != 0) {
        instructions.add(new Instruction("ADD sp, sp, #" + offset));
      }
    }

    String branch2 = state.getNewBranchName();
    instructions.add(new Instruction("B " + branch2));

    instructions.add(new Instruction(branch1 + ":"));

    if (stat2 != null) {
      offset = numVarsInElseScope * INT_OFFSET;
      state.updateStackPointer(offset);

      if (offset != 0) {
        instructions.add(new Instruction("SUB sp, sp, #" + offset));
      }

      int preDeclared = state.getDeclaredVars();
      int preNumVars = state.getNumVars();
      state.enterScope();

      instructions.addAll(stat2.generate());

      state.exitScope();
      state.updateStackPointer(-offset);
      state.setDeclaredVars(preDeclared);
      state.setNumVars(preNumVars);

      if (numVarsInElseScope != 0) {
        instructions.add(new Instruction("ADD sp, sp, #" + offset));
      }
    }

    instructions.add(new Instruction(branch2 + ":"));

    return instructions;
  }
}
