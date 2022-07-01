package generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import visitors.State;

public class PairElemGenerator implements Generator {
  private final State state;
  private final String ident;
  private final boolean fst;

  public PairElemGenerator(State state, String ident, boolean fst) {
    this.state = state;
    this.ident = ident;
    this.fst = fst;
  }

  @Override
  public List<Instruction> generate() {
    List<Instruction> instructions = new ArrayList<>();
    int offset = state.getAddress(ident);
    StringBuilder ldrInstr = new StringBuilder();
    ldrInstr.append("LDR ");

    Optional<String> availReg = state.getAvailableRegister();

    String pairPointer;
    if (availReg.isPresent()) {
      pairPointer = availReg.get();
      ldrInstr.append(pairPointer);
      state.allocRegister(pairPointer);
      state.setResultRegister(pairPointer);
    } else {
      pairPointer = "r10";
      instructions.add(new Instruction("PUSH {" + pairPointer + "}"));
      state.freeRegister(pairPointer);
      ldrInstr.append(pairPointer);
      state.allocRegister(pairPointer);
      // TODO : pushed stack pointer
      state.setResultRegister(pairPointer);
    }

    ldrInstr.append(", [sp, #").append(offset).append("]");

    instructions.add(new Instruction(ldrInstr.toString()));

    instructions.add(new Instruction("MOV r0, " + pairPointer));
    instructions.add(new Instruction("BL p_check_null_pointer"));
    state.addErrorFunction("p_check_null_pointer");

    if (fst) {
      instructions.add(new Instruction("LDR " + pairPointer + ", [" + pairPointer + "]"));
    } else {
      instructions.add(new Instruction("LDR " + pairPointer + ", [" + pairPointer + ", #4]"));
    }

    instructions.add(new Instruction("LDR " + pairPointer + ", [" + pairPointer + "]"));
    state.setResultRegister(pairPointer);

    return instructions;
  }
}
