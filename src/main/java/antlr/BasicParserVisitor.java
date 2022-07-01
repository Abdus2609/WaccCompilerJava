// Generated from ./BasicParser.g4 by ANTLR 4.9.3
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BasicParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BasicParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BasicParser#mulDivModOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivModOper(BasicParser.MulDivModOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#plusMinusOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusMinusOper(BasicParser.PlusMinusOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#compOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompOper(BasicParser.CompOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#eqOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqOper(BasicParser.EqOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#bitwiseAndOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseAndOper(BasicParser.BitwiseAndOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#bitwiseXorOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseXorOper(BasicParser.BitwiseXorOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#bitwiseOrOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseOrOper(BasicParser.BitwiseOrOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#andOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOper(BasicParser.AndOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#orOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrOper(BasicParser.OrOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#baseType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseType(BasicParser.BaseTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#pairElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElem(BasicParser.PairElemContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#pairElemType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairElemType(BasicParser.PairElemTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#pairType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPairType(BasicParser.PairTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(BasicParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#arrayElem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayElem(BasicParser.ArrayElemContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(BasicParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#arrayLiter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiter(BasicParser.ArrayLiterContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#unaryOper}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOper(BasicParser.UnaryOperContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(BasicParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(BasicParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(BasicParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#assignLHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignLHS(BasicParser.AssignLHSContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(BasicParser.ArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(BasicParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#newpair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewpair(BasicParser.NewpairContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#assignRHS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignRHS(BasicParser.AssignRHSContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#declareStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclareStat(BasicParser.DeclareStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#assignStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStat(BasicParser.AssignStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#readStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadStat(BasicParser.ReadStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#freeStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFreeStat(BasicParser.FreeStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#returnStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStat(BasicParser.ReturnStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#exitStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExitStat(BasicParser.ExitStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#printStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStat(BasicParser.PrintStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#printlnStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintlnStat(BasicParser.PrintlnStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#ifStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStat(BasicParser.IfStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#whileStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStat(BasicParser.WhileStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#beginStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeginStat(BasicParser.BeginStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#voidFuncCallStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidFuncCallStat(BasicParser.VoidFuncCallStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(BasicParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(BasicParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link BasicParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(BasicParser.ProgContext ctx);
}