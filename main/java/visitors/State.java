package visitors;

import generators.Generator;
import generators.Instruction;
import types.Type;

import java.util.*;

public class State {

  // true means the register is available
  private Map<String, Boolean> registers =
      new HashMap<>() {
        {
          put("r4", true);
          put("r5", true);
          put("r6", true);
          put("r7", true);
          put("r8", true);
          put("r9", true);
          put("r10", true);
        }
      };

  private List<String> registerList =
      new ArrayList<>() {
        {
          add("r4");
          add("r5");
          add("r6");
          add("r7");
          add("r8");
          add("r9");
          add("r10");
        }
      };

  // register that stores the latest result calculated
  private String resultRegister;

  private int stackPointer = 0;

  private int numDeclares;

  private int numBranches = 0;

  private int stringCounter = 0;

  private int pushCounter = 0;

  private Map<Integer, String> messages = new HashMap<>();
  private Map<String, List<Instruction>> functions = new HashMap<>();

  private Map<String, Integer> offSetTable = Map.of("int", 4, "char", 1, "bool", 1, "string", 4);

  // number of variables declared in a group of statements(function or main)
  private int numVars;
  // number of variables already declares in previous instructions
  private int declaredVars;

  private List<String> stack = new ArrayList<>();

  private HashMap<String, Integer> symbolTable = new HashMap<>();
  private HashMap<String, Type> typeTable = new HashMap<>();

  private List<HashMap<String, Integer>> symbolTables = new ArrayList<>();
  private List<HashMap<String, Type>> typeTables = new ArrayList<>();

  // the depth of scope
  private int scopeDepth = 0;

  // enter a new scope
  public void enterScope() {
    scopeDepth++;
    symbolTables.add((HashMap<String, Integer>) symbolTable.clone());
    typeTables.add((HashMap<String, Type>) typeTable.clone());
  }

  // exit current scope and remove declared identifiers from that scope
  public void exitScope() {
    scopeDepth--;
    symbolTable = symbolTables.get(scopeDepth);
    typeTable = typeTables.get(scopeDepth);
    symbolTables.remove(scopeDepth);
    typeTables.remove(scopeDepth);
  }

  public Optional<String> getAvailableRegister() {
    for (String reg : registerList) {
      if (registers.get(reg)) {
        return Optional.of(reg);
      }
    }
    return Optional.empty();
  }

  public int getStringCounter() {
    return stringCounter;
  }

  public Map<Integer, String> getMessages() {
    return messages;
  }

  public Map<String, List<Instruction>> getFunctions() {
    return functions;
  }

  public void addMessage(String message) {
    messages.put(getStringCounter(), message);
    stringCounter++;
  }

  public void addPush() {
    pushCounter++;
  }

  public void minusPush() {
    pushCounter--;
  }

  public int getPushCounter() {
    return pushCounter;
  }

  public void addFunction(String funcName, Generator functionGenerator) {
    functions.put(funcName, functionGenerator.generate());
  }

  public Map<String, Integer> getSymbolTable() {
    return symbolTable;
  }

  public Map<String, Type> getTypeTable() {
    return typeTable;
  }

  public int getAddress(String ident) {
    return symbolTable.get(ident) + stackPointer;
  }

  public void allocRegister(String register) {
    registers.put(register, false);
  }

  public int getStackPointer() {
    return stackPointer;
  }

  public void updateStackPointer(int offset) {
    stackPointer += offset;
  }

  public void freeRegister(String register) {
    registers.put(register, true);
  }

  public void setResultRegister(String register) {
    resultRegister = register;
  }

  public String getResultRegister() {
    return resultRegister;
  }

  public void setNumVars(int numVars) {
    this.numVars = numVars;
  }

  public int getNumVars() {
    return numVars;
  }

  public void setDeclaredVars(int declaredVars) {
    this.declaredVars = declaredVars;
  }

  public int getDeclaredVars() {
    return declaredVars;
  }

  public Type getType(String ident) {
    return typeTable.get(ident);
  }

  public void addDeclaredVar() {
    declaredVars++;
  }

  public String getNewBranchName() {
    String name = "L" + numBranches;
    numBranches++;
    return name;
  }

  public int lookUpOffset(String type) {
    return offSetTable.get(type);
  }

  public void addVariable(String ident, int offset, Type type) {
    symbolTable.put(ident, offset);
    typeTable.put(ident, type);
  }

  private Map<String, Boolean> builtInFunctions =
      new HashMap<>() {
        {
          put("p_print_ln", false);
          put("p_print_string", false);
          put("p_print_int", false);
          put("p_print_bool", false);
          put("p_print_reference", false);
          put("p_throw_overflow_error", false);
          put("p_throw_runtime_error", false);
          put("p_free_pair", false);
          put("p_check_divide_by_zero", false);
          put("p_check_null_pointer", false);
          put("p_read_int", false);
          put("p_read_char", false);
          put("p_check_array_bounds", false);
        }
      };

  public void addBuiltInFunction(String name) {
    builtInFunctions.put(name, true);
  }

  public void addErrorFunction(String name) {
    builtInFunctions.put("p_print_string", true);
    builtInFunctions.put("p_throw_runtime_error", true);
    builtInFunctions.put(name, true);
  }

  public void addAllExtraFunctions(List<Instruction> instructions) {
    if (builtInFunctions.get("p_print_ln")) {
      addPrintlnFunction(instructions);
    }

    if (builtInFunctions.get("p_print_string")) {
      addPrintStringFunction(instructions);
    }

    if (builtInFunctions.get("p_print_int")) {
      addPrintIntFunction(instructions);
    }

    if (builtInFunctions.get("p_print_bool")) {
      addPrintBoolFunction(instructions);
    }

    if (builtInFunctions.get("p_print_reference")) {
      addPrintReferenceFunction(instructions);
    }

    if (builtInFunctions.get("p_throw_overflow_error")) {
      addThrowOverflowError(instructions);
    }

    if (builtInFunctions.get("p_throw_runtime_error")) {
      addThrowRuntimeError(instructions);
    }

    if (builtInFunctions.get("p_free_pair")) {
      addFreePairFunction(instructions);
    }

    if (builtInFunctions.get("p_check_divide_by_zero")) {
      addCheckDivideFunction(instructions);
    }

    if (builtInFunctions.get("p_check_null_pointer")) {
      addCheckNullPointerFunction(instructions);
    }

    if (builtInFunctions.get("p_read_int")) {
      addReadIntFunction(instructions);
    }

    if (builtInFunctions.get("p_read_char")) {
      addReadCharFunction(instructions);
    }

    if (builtInFunctions.get("p_check_array_bounds")) {
      addCheckArrayBoundsFunction(instructions);
    }
  }

  private void addCheckArrayBoundsFunction(List<Instruction> instructions) {
    instructions.add(new Instruction("p_check_array_bounds:"));
    instructions.add(new Instruction("PUSH {lr}"));
    instructions.add(new Instruction("CMP r0, #0"));
    instructions.add(new Instruction("LDRLT r0, =msg_" + getStringCounter()));
    addMessage("\"ArrayIndexOutOfBoundsError: negative index\\n\\0\"");
    instructions.add(new Instruction("BLLT p_throw_runtime_error"));
    instructions.add(new Instruction("LDR r1, [r1]"));
    instructions.add(new Instruction("CMP r0, r1"));
    instructions.add(new Instruction("LDRCS r0, =msg_" + getStringCounter()));
    addMessage("\"ArrayIndexOutOfBoundsError: index too large\\n\\0\"");
    instructions.add(new Instruction("BLCS p_throw_runtime_error"));
    instructions.add(new Instruction("POP {pc}"));
  }

  private void addReadCharFunction(List<Instruction> instructions) {
    instructions.add(new Instruction("p_read_char:"));
    instructions.add(new Instruction("PUSH {lr}"));
    instructions.add(new Instruction("MOV r1, r0"));
    instructions.add(new Instruction("LDR r0, =msg_" + getStringCounter()));
    addMessage("\" %c\\0\"");
    instructions.add(new Instruction("ADD r0, r0, #4"));
    instructions.add(new Instruction("BL scanf"));
    instructions.add(new Instruction("POP {pc}"));
  }

  private void addReadIntFunction(List<Instruction> instructions) {
    instructions.add(new Instruction("p_read_int:"));
    instructions.add(new Instruction("PUSH {lr}"));
    instructions.add(new Instruction("MOV r1, r0"));
    instructions.add(new Instruction("LDR r0, =msg_" + getStringCounter()));
    addMessage("\"%d\\0\"");
    instructions.add(new Instruction("ADD r0, r0, #4"));
    instructions.add(new Instruction("BL scanf"));
    instructions.add(new Instruction("POP {pc}"));
  }

  private void addCheckNullPointerFunction(List<Instruction> instructions) {
    instructions.add(new Instruction("p_check_null_pointer:"));
    instructions.add(new Instruction("PUSH {lr}"));
    instructions.add(new Instruction("CMP r0, #0"));
    instructions.add(new Instruction("LDREQ r0, =msg_" + getStringCounter()));
    addMessage("\"NullReferenceError: dereference a null reference\\n\\0\"");
    instructions.add(new Instruction("BLEQ p_throw_runtime_error"));
    instructions.add(new Instruction("POP {pc}"));
  }

  private void addCheckDivideFunction(List<Instruction> instructions) {
    instructions.add(new Instruction("p_check_divide_by_zero:"));
    instructions.add(new Instruction("PUSH {lr}"));
    instructions.add(new Instruction("CMP r1, #0"));
    instructions.add(new Instruction("LDREQ r0, =msg_" + getStringCounter()));
    addMessage("\"DivideByZeroError: divide or modulo by zero\\n\\0\"");
    instructions.add(new Instruction("BLEQ p_throw_runtime_error"));
    instructions.add(new Instruction("POP {pc}"));
  }

  private void addFreePairFunction(List<Instruction> instructions) {
    instructions.add(new Instruction("p_free_pair:"));
    instructions.add(new Instruction("PUSH {lr}"));
    instructions.add(new Instruction("CMP r0, #0"));
    instructions.add(new Instruction("LDREQ r0, =msg_" + getStringCounter()));
    addMessage("\"NullReferenceError: dereference a null reference\\n\\0\"");
    instructions.add(new Instruction("BEQ p_throw_runtime_error"));
    instructions.add(new Instruction("PUSH {r0}"));
    instructions.add(new Instruction("LDR r0, [r0]"));
    instructions.add(new Instruction("BL free"));
    instructions.add(new Instruction("LDR r0, [sp]"));
    instructions.add(new Instruction("LDR r0, [r0, #4]"));
    instructions.add(new Instruction("BL free"));
    instructions.add(new Instruction("POP {r0}"));
    instructions.add(new Instruction("BL free"));
    instructions.add(new Instruction("POP {pc}"));
  }

  private void addThrowRuntimeError(List<Instruction> instructions) {
    instructions.add(new Instruction("p_throw_runtime_error:"));
    instructions.add(new Instruction("BL p_print_string"));
    instructions.add(new Instruction("MOV r0, #-1"));
    instructions.add(new Instruction("BL exit"));
  }

  private void addThrowOverflowError(List<Instruction> instructions) {
    instructions.add(new Instruction("p_throw_overflow_error:"));
    instructions.add(new Instruction("LDR r0, =msg_" + getStringCounter()));
    addMessage(
        "\"OverflowError: the result is too small/large to store in a 4-byte signed-integer.\\n\\0\"");
    instructions.add(new Instruction("BL p_throw_runtime_error"));
  }

  private void addPrintReferenceFunction(List<Instruction> instructions) {
    instructions.add(new Instruction("p_print_reference:"));
    instructions.add(new Instruction("PUSH {lr}"));
    instructions.add(new Instruction("MOV r1, r0"));
    instructions.add(new Instruction("LDR r0, =msg_" + getStringCounter()));
    addMessage("\"%p\\0\"");
    instructions.add(new Instruction("ADD r0, r0, #4"));
    instructions.add(new Instruction("BL printf"));
    instructions.add(new Instruction("MOV r0, #0"));
    instructions.add(new Instruction("BL fflush"));
    instructions.add(new Instruction("POP {pc}"));
  }

  private void addPrintBoolFunction(List<Instruction> instructions) {
    instructions.add(new Instruction("p_print_bool:"));
    instructions.add(new Instruction("PUSH {lr}"));
    instructions.add(new Instruction("CMP r0, #0"));
    instructions.add(new Instruction("LDRNE r0, =msg_" + getStringCounter()));
    addMessage("\"true\\0\"");
    instructions.add(new Instruction("LDREQ r0, =msg_" + getStringCounter()));
    addMessage("\"false\\0\"");
    instructions.add(new Instruction("ADD r0, r0, #4"));
    instructions.add(new Instruction("BL printf"));
    instructions.add(new Instruction("MOV r0, #0"));
    instructions.add(new Instruction("BL fflush"));
    instructions.add(new Instruction("POP {pc}"));
  }

  private void addPrintIntFunction(List<Instruction> instructions) {
    instructions.add(new Instruction("p_print_int:"));
    instructions.add(new Instruction("PUSH {lr}"));
    instructions.add(new Instruction("MOV r1, r0"));
    instructions.add(new Instruction("LDR r0, =msg_" + getStringCounter()));
    addMessage("\"%d\\0\"");
    instructions.add(new Instruction("ADD r0, r0, #4"));
    instructions.add(new Instruction("BL printf"));
    instructions.add(new Instruction("MOV r0, #0"));
    instructions.add(new Instruction("BL fflush"));
    instructions.add(new Instruction("POP {pc}"));
  }

  private void addPrintStringFunction(List<Instruction> instructions) {
    instructions.add(new Instruction("p_print_string:"));
    instructions.add(new Instruction("PUSH {lr}"));
    instructions.add(new Instruction("LDR r1, [r0]"));
    instructions.add(new Instruction("ADD r2, r0, #4"));
    instructions.add(new Instruction("LDR r0, =msg_" + getStringCounter()));
    addMessage("\"%.*s\\0\"");
    instructions.add(new Instruction("ADD r0, r0, #4"));
    instructions.add(new Instruction("BL printf"));
    instructions.add(new Instruction("MOV r0, #0"));
    instructions.add(new Instruction("BL fflush"));
    instructions.add(new Instruction("POP {pc}"));
  }

  private void addPrintlnFunction(List<Instruction> instructions) {
    instructions.add(new Instruction("p_print_ln:"));
    instructions.add(new Instruction("PUSH {lr}"));
    instructions.add(new Instruction("LDR r0, =msg_" + getStringCounter()));
    addMessage("\"\\0\"");
    instructions.add(new Instruction("ADD r0, r0, #4"));
    instructions.add(new Instruction("BL puts"));
    instructions.add(new Instruction("MOV r0, #0"));
    instructions.add(new Instruction("BL fflush"));
    instructions.add(new Instruction("POP {pc}"));
  }

  public int getNumDeclares() {
    return numDeclares;
  }

  public void setNumDeclares(int numDeclares) {
    this.numDeclares = numDeclares;
  }
}
