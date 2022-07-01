// import ANTLR's runtime libraries

import antlr.BasicLexer;
import antlr.BasicParser;
import generators.Generator;
import generators.Instruction;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import visitors.ErrorHandler;
import visitors.FuncExecPathVisitor;
import visitors.InterpretVisitor;
import visitors.SemanticAnalyser;

public class Main {
  private static final int SYNTAX_ERROR_CODE = 100;

  public static void main(String[] args) {
    // create a CharStream that reads from arg0
    CharStream input = null;
    try {
      input = CharStreams.fromStream(new FileInputStream(args[0]));
    } catch (IOException e) {
      e.printStackTrace();
    }

    // create a lexer that feeds off input CharStream
    Lexer lexer = new BasicLexer(input);

    // create a buffer of tokens pulled from the lexer
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    // create a parser that feeds off the tokens buffer
    BasicParser parser = new BasicParser(tokens);

    // begin parsing at prog rule
    ParseTree tree = parser.prog();

    // check for basic syntax errors that ANTLR can recognise
    if (parser.getNumberOfSyntaxErrors() >= 1) {
      System.out.println("Syntax errors: " + parser.getNumberOfSyntaxErrors());
      System.exit(SYNTAX_ERROR_CODE);
    }

    // create instance of our visitors.ErrorHandler to pass into both visitors
    ErrorHandler errorHandler = new ErrorHandler();

    // checks every execution path through the body of the function ends with return or exit
    FuncExecPathVisitor funcExecPathVisitor = new FuncExecPathVisitor(errorHandler);

    // checks the program is semantically correct, throwing errors and exiting where necessary
    SemanticAnalyser semanticAnalyser = new SemanticAnalyser(errorHandler);

    try {
      funcExecPathVisitor.visit(tree);
      semanticAnalyser.visit(tree);
    } catch (Exception ignored) {
    }

    // if syntax or semantic error is found
    if (errorHandler.getStatus() != 0) {
      System.err.println(errorHandler.getTriggerMessage());
      System.exit(errorHandler.getStatus());
    }

    // Create visitor that traverses tree and builds up a generator for the entire program
    InterpretVisitor visitor = new InterpretVisitor();
    visitor.setSemanticVisitor(semanticAnalyser);

    // Created generator creates the assembly as list of instructions
    Generator generator = visitor.visit(tree);
    List<Instruction> instructions = generator.generate();

    // Turn list of instructions into .s file
    String file =
        instructions.stream().map(Instruction::toString).collect(Collectors.joining("\n"));

    // Save assembly as .s file in cwd
    String filename =
        args[0].substring(args[0].lastIndexOf("/") + 1, args[0].indexOf(".wacc") + 1) + "s";
    try {
      FileWriter assemblyFileWriter = new FileWriter(filename);
      assemblyFileWriter.write(file + "\n");
      assemblyFileWriter.close();
      System.out.println("\n Saved " + filename + ":");
      System.out.println(file);
      // exit gracefully
      System.exit(0);
    } catch (IOException e) {
      System.out.println("Saving failed, but we generated this:\n");
      e.printStackTrace();
      System.exit(-1);
    }
  }
}
