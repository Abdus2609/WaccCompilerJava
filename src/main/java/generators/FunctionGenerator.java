package generators;

import antlr.BasicParser.ParamContext;
import antlr.BasicParser.ReturnStatContext;
import antlr.BasicParser.StatContext;
import java.util.ArrayList;
import java.util.List;
import types.Type;
import visitors.InterpretVisitor;
import visitors.State;
import visitors.VariableVisitor;

public class FunctionGenerator implements Generator {

  private final State state;
  private List<StatContext> statements;
  private List<ParamContext> parameters;
  private String funcName;
  private InterpretVisitor visitor;
  private ReturnStatContext singleReturnStat;
  private List<Type> parameterTypes = new ArrayList<>();
  private Generator stat;
  private VariableVisitor variableVisitor;
  private StatContext statContext;

  public FunctionGenerator(State state) {
    this.state = state;
  }

  public void setStatements(List<StatContext> statements) {
    this.statements = statements;
  }

  public void setParameters(List<ParamContext> parameters) {
    this.parameters = parameters;
  }

  public void addParameterType(Type type) {
    parameterTypes.add(type);
  }

  public void setFuncName(String funcName) {
    this.funcName = funcName;
  }

  public void setVisitor(InterpretVisitor visitor) {
    this.visitor = visitor;
  }

  public void setStat(Generator stat) {
    this.stat = stat;
  }

  public void setVariableVisitor(VariableVisitor variableVisitor) {
    this.variableVisitor = variableVisitor;
  }

  public void setStatContext(StatContext statContext) {
    this.statContext = statContext;
  }

  @Override
  public List<Instruction> generate() {

    List<Instruction> instructions = new ArrayList<>();
    instructions.add(new Instruction("PUSH {lr}"));

    // set the state
    int numVars = variableVisitor.visitStat(statContext);
    state.setNumVars(numVars);
    state.setDeclaredVars(0);
    state.setNumDeclares(numVars);

    // subtract the stack pointer by the number of declared variables
    if (numVars != 0) {
      instructions.add(new Instruction("SUB sp, sp, #" + numVars * 4));
    }

    if (!parameters.isEmpty()) {
      for (ParamContext param : parameters) {
        state.setNumVars(state.getNumVars() + 1);
        int offset = 4 * (state.getNumVars() - state.getDeclaredVars());
        state.addVariable(
            param.IDENT().getText(), offset, parameterTypes.get(parameters.indexOf(param)));
      }
    }

    // add the statement instructions
    if (stat != null) {
      instructions.addAll(stat.generate());
    }

    Instruction pop = new Instruction("POP {pc}");
    instructions.add(pop);

    return instructions;
  }
}
