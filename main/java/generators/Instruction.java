package generators;

public class Instruction {
  private final String instruction;

  public Instruction(String instruction) {
    this.instruction = instruction;
  }

  @Override
  public String toString() {
    return instruction;
  }
}
