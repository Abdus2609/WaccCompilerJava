package visitors;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import antlr.BasicParser.ArrayElemContext;
import types.Type;

public class ErrorHandler {

  private static final int SEMANTIC_ERR_CODE = 200;
  private static final int SYNTAX_ERR_CODE = 100;
  private static final int MIN_INT = Integer.MIN_VALUE;
  private static final int MAX_INT = Integer.MAX_VALUE;
  private static final char MIN_CHAR = 0;
  private static final char MAX_CHAR = 127;
  private static int exitStatus = 0;
  private static List<String> messages = new ArrayList<>();

  public ErrorHandler() {
    exitStatus = 0;
    messages = new ArrayList<>();
  }

  // exits due to error at position, with status code and prints message to System.err
  private static void errorExit(String position, String message, int code) {
    messages.add("line " + position + " " + message);
    if (exitStatus == 0) {
      exitStatus = code;
    }
  }

  // return exit status
  public int getStatus() {
    return exitStatus;
  }

  // return error message, if any
  public String getTriggerMessage() {
    if (messages.isEmpty()) {
      return null;
    } else {
      return messages.get(0);
    }
  }

  // get the specific line and char position of the error
  private static String getLinePos(ParseTree tree) {
    if (tree instanceof TerminalNode) {
      TerminalNode node = (TerminalNode) tree;
      return node.getSymbol().getLine() + ":" + node.getSymbol().getCharPositionInLine();
    }

    if (tree instanceof ParserRuleContext) {
      ParserRuleContext branch = (ParserRuleContext) tree;
      return branch.getStart().getLine() + ":" + branch.getStart().getCharPositionInLine();
    }

    return "0:0";
  }

  // types are not matching where they should be
  public void typeMismatchError(ParseTree errorContext, Type actual, Type expected) {
    String message = "mismatched type - expected: " + expected + ", actual: " + actual;
    errorExit(getLinePos(errorContext), message, SEMANTIC_ERR_CODE);
  }

  // type of actual does not match with either expected type
  public void doubleTypeMismatchError(
      ParseTree errorContext, Type actual, Type expected1, Type expected2) {
    String message =
        "mismatched read type - expected: "
            + expected1
            + " or "
            + expected2
            + ", actual: "
            + actual;
    errorExit(getLinePos(errorContext), message, SEMANTIC_ERR_CODE);
  }

  // integer is out of range
  public void integerOutOfRangeError(ParseTree errorContext) {
    String message = "integer out of range - Int should be between " + MIN_INT + " and " + MAX_INT;
    errorExit(getLinePos(errorContext), message, SYNTAX_ERR_CODE);
  }

  // character is out of range
  public void charOutOfRangeError(ParseTree errorContext) {
    String message = "char out of range - Char should be between " + MIN_CHAR + " and " + MAX_CHAR;
    errorExit(getLinePos(errorContext), message, SYNTAX_ERR_CODE);
  }

  // function redeclared - attempt to define an already defined function
  public void functionRedeclarationError(ParseTree errorContext, String functionName) {
    String message = "function name '" + functionName + "' is already in use";
    errorExit(getLinePos(errorContext), message, SEMANTIC_ERR_CODE);
  }

  // function is missing a return statement
  public void missingReturnStatementError(ParseTree errorContext, String functionName) {
    String message = "function '" + functionName + "' is missing a return statement";
    errorExit(getLinePos(errorContext), message, SYNTAX_ERR_CODE);
  }

  // unreachable code found - code found after the final return statement of a function
  public void unreachableCodeError(ParseTree errorContext, String functionName) {
    String message = "function '" + functionName + "' has unreachable code";
    errorExit(getLinePos(errorContext), message, SEMANTIC_ERR_CODE);
  }

  // variable not found in symbol table
  public void variableNotFoundError(ParseTree errorContext, String variableName) {
    String message = "variable '" + variableName + "' not found";
    errorExit(getLinePos(errorContext), message, SEMANTIC_ERR_CODE);
  }

  // attempt to exit with non-integer exit code
  public void invalidExitCodeTypeError(ParseTree errorContext, Type invalidType) {
    String message = "invalid exit code type - expected: Int, actual: " + invalidType;
    errorExit(getLinePos(errorContext), message, SEMANTIC_ERR_CODE);
  }

  // invalid number of arguments passed into function
  public void invalidNumberOfArgsError(ParseTree errorContext, int numParams, int invalidNumArgs) {
    String message =
        "invalid number of arguments - expected: " + numParams + ", actual: " + invalidNumArgs;
    errorExit(getLinePos(errorContext), message, SEMANTIC_ERR_CODE);
  }

  // invalid argument type passed into function
  public void invalidArgumentTypeError(ParseTree errorContext, Type actual, Type expected) {
    String message = "invalid argument type - expected: " + expected + ", actual: " + actual;
    errorExit(getLinePos(errorContext), message, SEMANTIC_ERR_CODE);
  }

  // function not found in symbol table
  public void functionNotFoundError(ParseTree errorContext, String functionName) {
    String message = "function '" + functionName + "' not found";
    errorExit(getLinePos(errorContext), message, SEMANTIC_ERR_CODE);
  }

  // attempt to declare already defined variable in situation where semantically incorrect
  public void variableRedeclarationError(ParseTree errorContext, String variableName) {
    String message = "variable name '" + variableName + "' is already in use.";
    errorExit(getLinePos(errorContext), message, SEMANTIC_ERR_CODE);
  }

  // function does not return with its expected return type
  public void invalidReturnTypeError(ParseTree errorContext, Type actual, Type expected) {
    String message = "invalid return type - expected: " + expected + ", actual: " + actual;
    errorExit(getLinePos(errorContext), message, SEMANTIC_ERR_CODE);
  }

  // attempt to return outside of any function
  public void globalReturnError(ParseTree errorContext) {
    String message = "return outside of function";
    errorExit(getLinePos(errorContext), message, SEMANTIC_ERR_CODE);
  }

  // function does not terminate with exit or return
  public void functionNotTerminatedError(ParseTree errorContext) {
    String message = "function body not terminated with return or exit statement";
    errorExit(getLinePos(errorContext), message, SYNTAX_ERR_CODE);
  }

  // attempt to access a null pair element
  public void accessNullPairElemError(ParseTree errorContext) {
    String message = "accessing a null pair elem";
    errorExit(getLinePos(errorContext), message, SEMANTIC_ERR_CODE);
  }

  // attempt to access a array with index out of bounds
  public void arrayIndexOutOfBoundsError(ParseTree errorContext, int index, int size) {
    String message = "array index out of bounds, index: " + index + " array size: " + size;
    // in order to pass the front-end tests we don't throw an error code
    errorExit(getLinePos(errorContext), message, SYNTAX_ERR_CODE);
  }

  // ensuring a void function does not return anything
  public void invalidVoidReturnError(ParseTree errorContext) {
    String message = "void functions cannot contain a return statement";
    errorExit(getLinePos(errorContext), message, SYNTAX_ERR_CODE);
  }
}
