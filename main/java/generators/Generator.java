package generators;

import java.util.List;

// take in the parameters and generates assembly instructions
public interface Generator {
  public List<Instruction> generate();
}
