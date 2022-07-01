package types;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SymbolTable {

  private final Map<String, Type> table;

  public SymbolTable(Map<String, Type> table) {
    this.table = new HashMap<String, Type>();
    this.table.putAll(table);
  }

  public boolean add(String name, Type type) {
    if (this.table.containsKey(name)) {
      return false;
    } else {
      this.table.put(name, type);
      return true;
    }
  }

  public Optional<Type> lookUp(String name) {
    if (this.table.containsKey(name)) {
      return Optional.of(this.table.get(name));
    } else {
      return Optional.empty();
    }
  }

  public Map<String, Type> getTable() {
    return table;
  }
}
