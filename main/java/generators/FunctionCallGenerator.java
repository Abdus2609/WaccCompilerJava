package generators;

import antlr.BasicParser.ExprContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import visitors.InterpretVisitor;
import visitors.State;

public class FunctionCallGenerator implements Generator {

  private final State state;
  private String funcName;
  private List<ExprContext> argList;
  private InterpretVisitor visitor;

  private static final int INT_OFFSET = 4;
  private static final int STACK_DECREMENT = -4;

  public FunctionCallGenerator(State state) {
    this.state = state;
  }

  public void setFuncName(String funcName) {
    this.funcName = funcName;
  }

  public void setArgList(List<ExprContext> argList) {
    this.argList = argList;
  }

  public void setVisitor(InterpretVisitor visitor) {
    this.visitor = visitor;
  }

  @Override
  public List<Instruction> generate() {
    List<Instruction> instructions = new ArrayList<>();
    int combinedOffset = 0;

    // push the parameters to the stack
    if (argList != null) {
      Collections.reverse(argList);
      for (ExprContext expr : argList) {
        instructions.addAll(visitor.visit(expr).generate());
        instructions.add(new Instruction("STR " + state.getResultRegister() + ", [sp, #-4]!"));
        state.updateStackPointer(STACK_DECREMENT);
        combinedOffset += INT_OFFSET;
        state.freeRegister(state.getResultRegister());
      }
    }

    // branch to the function
    instructions.add(new Instruction("BL f_" + funcName));
    instructions.add(new Instruction("ADD sp, sp, #" + combinedOffset));
    state.updateStackPointer(combinedOffset);
    instructions.add(new Instruction("MOV " + state.getResultRegister() + ", r0"));

    return instructions;
  }
}
