package visitors;

import antlr.BasicParser.AssignStatContext;
import antlr.BasicParser.BeginStatContext;
import antlr.BasicParser.DeclareStatContext;
import antlr.BasicParser.ExitStatContext;
import antlr.BasicParser.FreeStatContext;
import antlr.BasicParser.FuncContext;
import antlr.BasicParser.IfStatContext;
import antlr.BasicParser.PrintStatContext;
import antlr.BasicParser.PrintlnStatContext;
import antlr.BasicParser.ProgContext;
import antlr.BasicParser.ReadStatContext;
import antlr.BasicParser.ReturnStatContext;
import antlr.BasicParser.StatContext;
import antlr.BasicParser.WhileStatContext;
import antlr.BasicParserBaseVisitor;
import visitors.ErrorHandler;

public class FuncExecPathVisitor extends BasicParserBaseVisitor<Boolean> {

  // flag to keep track if visiting branch inside a while of if statement
  private boolean insideIfWhile = false;
  private final ErrorHandler errorHandler;

  public FuncExecPathVisitor(ErrorHandler errorHandler) {
    this.errorHandler = errorHandler;
  }

  @Override
  public Boolean visitProg(ProgContext ctx) {
    if (ctx.func().isEmpty()) {
      return true;
    } else {
      // visit all the functions in the program
      for (FuncContext func : ctx.func()) {
        if (!visit(func) && func.VOID() == null) {
          // the function is missing return or exit statement
          errorHandler.missingReturnStatementError(ctx, func.IDENT().getText());
        }
      }
    }
    return true;
  }

  @Override
  public Boolean visitFunc(FuncContext ctx) {
    // extension - void function return
    boolean containsReturn = visitStat(ctx.stat());

    if (ctx.VOID() != null && containsReturn) {
      errorHandler.invalidVoidReturnError(ctx);
    }

    return containsReturn;
  }

  @Override
  public Boolean visitStat(StatContext ctx) {
    // check if one statment or two
    if (ctx.SEMICOL() != null) {
      boolean fst = visit(ctx.stat(0));
      boolean snd = visit(ctx.stat(1));

      // if the first statement returns, the second must be returning
      if (fst) {
        if (!snd && !insideIfWhile) {
          errorHandler.functionNotTerminatedError(ctx);
        } else {
          return true;
        }
      }
    }

    // skip statement doesn't return
    if (ctx.SKP() != null) {
      return false;
    }

    return visitChildren(ctx);
  }

  @Override
  public Boolean visitIfStat(IfStatContext ctx) {
    // both branches must return
    insideIfWhile = true;
    boolean retBool = visit(ctx.stat(0)) && visit(ctx.stat(1));
    insideIfWhile = false;
    return retBool;
  }

  @Override
  public Boolean visitWhileStat(WhileStatContext ctx) {
    // only "while true ...return" returns
    boolean exprBool =
        ctx.expr().BOOL_LIT() != null && ctx.expr().BOOL_LIT().getText().equals("true");
    boolean identBool = ctx.expr().IDENT() != null && ctx.expr().IDENT().getText().equals("true");

    boolean retBool;

    if (exprBool || identBool) {
      insideIfWhile = true;
      retBool = visit(ctx.stat());
      insideIfWhile = false;
    } else {
      return false;
    }

    return retBool;
  }

  @Override
  public Boolean visitBeginStat(BeginStatContext ctx) {
    return false;
  }

  @Override
  public Boolean visitPrintlnStat(PrintlnStatContext ctx) {
    return false;
  }

  @Override
  public Boolean visitPrintStat(PrintStatContext ctx) {
    return false;
  }

  @Override
  public Boolean visitExitStat(ExitStatContext ctx) {
    return true;
  }

  @Override
  public Boolean visitReturnStat(ReturnStatContext ctx) {
    return true;
  }

  @Override
  public Boolean visitFreeStat(FreeStatContext ctx) {
    return false;
  }

  @Override
  public Boolean visitReadStat(ReadStatContext ctx) {
    return false;
  }

  @Override
  public Boolean visitAssignStat(AssignStatContext ctx) {
    return false;
  }

  @Override
  public Boolean visitDeclareStat(DeclareStatContext ctx) {
    return false;
  }
}
