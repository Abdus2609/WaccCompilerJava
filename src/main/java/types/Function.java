package types;

import java.util.ArrayList;
import java.util.List;

public class Function extends Type {

  private final Type returnType;
  private final List<Type> parameters = new ArrayList<>();

  public Function(Type returnType) {
    this.returnType = returnType;
  }

  public void addParameter(Type parameter) {
    parameters.add(parameter);
  }

  public List<Type> getParameters() {
    return parameters;
  }

  public int numOfParam() {
    return parameters.size();
  }

  public Type getType() {
    return returnType;
  }
}
