package generators;

import java.util.ArrayList;
import java.util.List;

public class StatementGenerator implements Generator {
  private final Generator statement1;
  private final Generator statement2;

  public StatementGenerator(Generator statement1, Generator statement2) {
    this.statement1 = statement1;
    this.statement2 = statement2;
  }

  @Override
  public List<Instruction> generate() {

    List<Instruction> instructions =
        (statement1 != null) ? statement1.generate() : new ArrayList<>();
    instructions.addAll((statement2 != null) ? statement2.generate() : new ArrayList<>());

    return instructions;
  }
}
