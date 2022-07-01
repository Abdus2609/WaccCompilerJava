package types;

public class Pair extends Type {

  private final Type lhsType;
  private final Type rhsType;

  public Pair(Type lhsType, Type rhsType) {
    this.lhsType = lhsType;
    this.rhsType = rhsType;
  }

  public Type fst() {
    return lhsType;
  }

  public Type snd() {
    return rhsType;
  }

  @Override
  public String toString() {
    return "pair";
  }
}
