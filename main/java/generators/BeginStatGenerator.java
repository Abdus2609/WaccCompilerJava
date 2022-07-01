package generators;

import java.util.ArrayList;
import java.util.List;
import visitors.State;

public class BeginStatGenerator implements Generator {

  private final State state;
  private final Generator statGenerator;
  private final int numVarsInScope;

  public BeginStatGenerator(State state, Generator statGenerator, int numVarsInScope) {
    this.state = state;
    this.statGenerator = statGenerator;
    this.numVarsInScope = numVarsInScope;
  }

  @Override
  public List<Instruction> generate() {
    List<Instruction> instructions = new ArrayList<>();

    int offset = 4 * numVarsInScope;
    state.updateStackPointer(offset);

    if (numVarsInScope != 0) {
      instructions.add(new Instruction("SUB sp, sp, #" + offset));
    }

    int preDeclared = state.getDeclaredVars();
    int preNumVars = state.getNumVars();
    state.enterScope();

    if (statGenerator != null) {
      instructions.addAll(statGenerator.generate());
    }

    state.exitScope();
    state.updateStackPointer(-offset);
    state.setDeclaredVars(preDeclared);
    state.setNumVars(preNumVars);

    if (numVarsInScope != 0) {
      instructions.add(new Instruction("ADD sp, sp, #" + offset));
    }

    return instructions;
  }
}
