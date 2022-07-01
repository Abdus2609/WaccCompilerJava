package generators;

import antlr.BasicParser.StatContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import visitors.State;
import visitors.VariableVisitor;

public class ProgramGenerator implements Generator {

  private final Generator statement;
  private final State state;
  private final VariableVisitor variableVisitor = new VariableVisitor();
  private final StatContext statContext;

  private static final int BYTE_LIMIT = 1024;

  public ProgramGenerator(
      List<Generator> functions, Generator statement, State state, StatContext statContext) {
    this.statement = statement;
    this.state = state;
    this.statContext = statContext;
  }

  @Override
  public List<Instruction> generate() {
    List<Instruction> functionInstructions = new ArrayList<>();
    functionInstructions.add(new Instruction(".global main"));

    for (String funcName : state.getFunctions().keySet()) {
      StringBuilder sb = new StringBuilder("\n");
      sb.append("f_").append(funcName).append(":\n");
      for (Instruction instruction : state.getFunctions().get(funcName)) {
        sb.append(instruction).append("\n");
      }
      functionInstructions.add(new Instruction(sb.toString()));
    }

    functionInstructions.add(new Instruction(".ltorg"));

    // add the main function
    functionInstructions.add(new Instruction("main:"));
    functionInstructions.add(new Instruction("PUSH {lr}"));

    // set the state for main
    int numVars = variableVisitor.visitStat(statContext);
    state.setNumVars(numVars);
    state.setDeclaredVars(0);

    int totalSpace = numVars * 4;

    // subtract the stack pointer by the number of declared variables
    if (numVars != 0) {
      while (totalSpace > BYTE_LIMIT) {
        functionInstructions.add(new Instruction("SUB sp, sp, #1024"));
        totalSpace -= BYTE_LIMIT;
      }
      functionInstructions.add(new Instruction("SUB sp, sp, #" + totalSpace));
    }
    // add the statement instructions
    if (statement != null) {
      functionInstructions.addAll(statement.generate());
    }

    totalSpace = numVars * 4;
    if (numVars != 0) {
      while (totalSpace > BYTE_LIMIT) {
        functionInstructions.add(new Instruction("ADD sp, sp, #1024"));
        totalSpace -= BYTE_LIMIT;
      }
      functionInstructions.add(new Instruction("ADD sp, sp, #" + totalSpace));
    }

    functionInstructions.add(new Instruction("LDR r0, =0"));
    functionInstructions.add(new Instruction("POP {pc}"));
    functionInstructions.add(new Instruction(".ltorg"));

    // add the built-in functions and error functions
    state.addAllExtraFunctions(functionInstructions);

    // generate the messages
    List<Instruction> messages = new ArrayList<>();
    if (!state.getMessages().isEmpty()) {
      messages.add(new Instruction(".data"));
    }
    for (Map.Entry<Integer, String> entry : state.getMessages().entrySet()) {
      StringBuilder sb = new StringBuilder("\n");
      sb.append("msg_").append(entry.getKey()).append(":");
      sb.append("\n\n");
      int numBackSlashes = (entry.getValue().split(Pattern.quote("\\"), -1).length) - 1;
      sb.append(".word ").append(entry.getValue().length() - 2 - numBackSlashes);
      sb.append("\n\n");
      sb.append(".ascii ").append(entry.getValue());
      sb.append("\n");
      messages.add(new Instruction(sb.toString()));
    }
    messages.add(new Instruction(".text\n"));

    // add everything together
    List<Instruction> programInstructions = new ArrayList<>();
    programInstructions.addAll(messages);
    programInstructions.addAll(functionInstructions);

    return programInstructions;
  }
}
