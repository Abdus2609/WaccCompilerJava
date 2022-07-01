package types;

public class Array extends Type {

  private final Type elemType;

  public Array(Type elemType) {
    this.elemType = elemType;
  }

  public Type getElemType() {
    return elemType;
  }

  public Type getBaseArrayType() {
    if(elemType instanceof Array) {
      return ((Array) elemType).getBaseArrayType();
    }
    return elemType;
  }

  @Override
  public String toString() {
    return "array";
  }
}
