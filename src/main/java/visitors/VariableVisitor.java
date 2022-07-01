package visitors;

import antlr.BasicParser;
import antlr.BasicParserBaseVisitor;
import generators.Generator;

// counts the number of variables declared in a function or in main
public class VariableVisitor extends BasicParserBaseVisitor<Integer> {

  @Override
  public Integer visitFunc(BasicParser.FuncContext ctx) {
    return visitStat(ctx.stat());
  }

  @Override
  public Integer visitStat(BasicParser.StatContext ctx) {
    if (ctx.SKP() != null) {
      return 0;
    }

    if (ctx.SEMICOL() != null) {
      return visitStat(ctx.stat(0)) + visitStat(ctx.stat(1));
    }

    return visitChildren(ctx);
  }

  @Override
  public Integer visitDeclareStat(BasicParser.DeclareStatContext ctx) {
    return 1;
  }

  @Override
  public Integer visitAssignStat(BasicParser.AssignStatContext ctx) {
    return 0;
  }

  @Override
  public Integer visitBeginStat(BasicParser.BeginStatContext ctx) {
    return 0;
  }

  @Override
  public Integer visitExitStat(BasicParser.ExitStatContext ctx) {
    return 0;
  }

  @Override
  public Integer visitIfStat(BasicParser.IfStatContext ctx) {
    return 0;
  }

  @Override
  public Integer visitWhileStat(BasicParser.WhileStatContext ctx) {
    return 0;
  }

  @Override
  public Integer visitFreeStat(BasicParser.FreeStatContext ctx) {
    return 0;
  }

  @Override
  public Integer visitPrintlnStat(BasicParser.PrintlnStatContext ctx) {
    return 0;
  }

  @Override
  public Integer visitPrintStat(BasicParser.PrintStatContext ctx) {
    return 0;
  }

  @Override
  public Integer visitReadStat(BasicParser.ReadStatContext ctx) {
    return 0;
  }

  @Override
  public Integer visitReturnStat(BasicParser.ReturnStatContext ctx) {
    return 0;
  }

  @Override
  public Integer visitVoidFuncCallStat(BasicParser.VoidFuncCallStatContext ctx) {
    return 0;
  }
}
