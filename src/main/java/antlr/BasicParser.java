// Generated from ./BasicParser.g4 by ANTLR 4.9.3
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BasicParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, WS=2, EOL=3, BEGIN=4, END=5, IS=6, PAIR=7, SKP=8, READ=9, FREE=10, 
		RETURN=11, EXIT=12, PRINT=13, PRINTLN=14, IF=15, THEN=16, ELSE=17, FI=18, 
		FOR=19, WHILE=20, DO=21, DONE=22, NEWPAIR=23, CALL=24, FST=25, SND=26, 
		BOOL=27, INT=28, CHAR=29, STRING=30, VOID=31, COMMA=32, SEMICOL=33, NOT=34, 
		LEN=35, ORD=36, CHR=37, BITWISE_NOT=38, PLUS=39, MINUS=40, MULT=41, DIVIDE=42, 
		MOD=43, GT=44, ST=45, GTE=46, STE=47, EQ=48, NEQ=49, AND=50, OR=51, BITWISE_AND=52, 
		BITWISE_XOR=53, BITWISE_OR=54, OPEN_PARENTHESES=55, CLOSE_PARENTHESES=56, 
		OPEN_BRACKETS=57, CLOSE_BRACKETS=58, IDENT=59, EQUAL=60, CHAR_LIT=61, 
		STRING_LIT=62, BOOL_LIT=63, BIN_INT_LIT=64, OCT_INT_LIT=65, HEX_INT_LIT=66, 
		INT_LIT=67, PAIR_LIT=68;
	public static final int
		RULE_mulDivModOper = 0, RULE_plusMinusOper = 1, RULE_compOper = 2, RULE_eqOper = 3, 
		RULE_bitwiseAndOper = 4, RULE_bitwiseXorOper = 5, RULE_bitwiseOrOper = 6, 
		RULE_andOper = 7, RULE_orOper = 8, RULE_baseType = 9, RULE_pairElem = 10, 
		RULE_pairElemType = 11, RULE_pairType = 12, RULE_type = 13, RULE_arrayElem = 14, 
		RULE_arrayType = 15, RULE_arrayLiter = 16, RULE_unaryOper = 17, RULE_expr = 18, 
		RULE_param = 19, RULE_paramList = 20, RULE_assignLHS = 21, RULE_argList = 22, 
		RULE_functionCall = 23, RULE_newpair = 24, RULE_assignRHS = 25, RULE_declareStat = 26, 
		RULE_assignStat = 27, RULE_readStat = 28, RULE_freeStat = 29, RULE_returnStat = 30, 
		RULE_exitStat = 31, RULE_printStat = 32, RULE_printlnStat = 33, RULE_ifStat = 34, 
		RULE_whileStat = 35, RULE_beginStat = 36, RULE_voidFuncCallStat = 37, 
		RULE_stat = 38, RULE_func = 39, RULE_prog = 40;
	private static String[] makeRuleNames() {
		return new String[] {
			"mulDivModOper", "plusMinusOper", "compOper", "eqOper", "bitwiseAndOper", 
			"bitwiseXorOper", "bitwiseOrOper", "andOper", "orOper", "baseType", "pairElem", 
			"pairElemType", "pairType", "type", "arrayElem", "arrayType", "arrayLiter", 
			"unaryOper", "expr", "param", "paramList", "assignLHS", "argList", "functionCall", 
			"newpair", "assignRHS", "declareStat", "assignStat", "readStat", "freeStat", 
			"returnStat", "exitStat", "printStat", "printlnStat", "ifStat", "whileStat", 
			"beginStat", "voidFuncCallStat", "stat", "func", "prog"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'\n'", "'begin'", "'end'", "'is'", "'pair'", "'skip'", 
			"'read'", "'free'", "'return'", "'exit'", "'print'", "'println'", "'if'", 
			"'then'", "'else'", "'fi'", "'for'", "'while'", "'do'", "'done'", "'newpair'", 
			"'call'", "'fst'", "'snd'", "'bool'", "'int'", "'char'", "'string'", 
			"'void'", "','", "';'", "'!'", "'len'", "'ord'", "'chr'", "'~'", "'+'", 
			"'-'", "'*'", "'/'", "'%'", "'>'", "'<'", "'>='", "'<='", "'=='", "'!='", 
			"'&&'", "'||'", "'&'", "'^'", "'|'", "'('", "')'", "'['", "']'", null, 
			"'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COMMENT", "WS", "EOL", "BEGIN", "END", "IS", "PAIR", "SKP", "READ", 
			"FREE", "RETURN", "EXIT", "PRINT", "PRINTLN", "IF", "THEN", "ELSE", "FI", 
			"FOR", "WHILE", "DO", "DONE", "NEWPAIR", "CALL", "FST", "SND", "BOOL", 
			"INT", "CHAR", "STRING", "VOID", "COMMA", "SEMICOL", "NOT", "LEN", "ORD", 
			"CHR", "BITWISE_NOT", "PLUS", "MINUS", "MULT", "DIVIDE", "MOD", "GT", 
			"ST", "GTE", "STE", "EQ", "NEQ", "AND", "OR", "BITWISE_AND", "BITWISE_XOR", 
			"BITWISE_OR", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", "OPEN_BRACKETS", 
			"CLOSE_BRACKETS", "IDENT", "EQUAL", "CHAR_LIT", "STRING_LIT", "BOOL_LIT", 
			"BIN_INT_LIT", "OCT_INT_LIT", "HEX_INT_LIT", "INT_LIT", "PAIR_LIT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "BasicParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BasicParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class MulDivModOperContext extends ParserRuleContext {
		public TerminalNode MULT() { return getToken(BasicParser.MULT, 0); }
		public TerminalNode DIVIDE() { return getToken(BasicParser.DIVIDE, 0); }
		public TerminalNode MOD() { return getToken(BasicParser.MOD, 0); }
		public MulDivModOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulDivModOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitMulDivModOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulDivModOperContext mulDivModOper() throws RecognitionException {
		MulDivModOperContext _localctx = new MulDivModOperContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_mulDivModOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULT) | (1L << DIVIDE) | (1L << MOD))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PlusMinusOperContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(BasicParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public PlusMinusOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plusMinusOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPlusMinusOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlusMinusOperContext plusMinusOper() throws RecognitionException {
		PlusMinusOperContext _localctx = new PlusMinusOperContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_plusMinusOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompOperContext extends ParserRuleContext {
		public TerminalNode GT() { return getToken(BasicParser.GT, 0); }
		public TerminalNode GTE() { return getToken(BasicParser.GTE, 0); }
		public TerminalNode ST() { return getToken(BasicParser.ST, 0); }
		public TerminalNode STE() { return getToken(BasicParser.STE, 0); }
		public CompOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitCompOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompOperContext compOper() throws RecognitionException {
		CompOperContext _localctx = new CompOperContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_compOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << ST) | (1L << GTE) | (1L << STE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqOperContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(BasicParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(BasicParser.NEQ, 0); }
		public EqOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eqOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitEqOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqOperContext eqOper() throws RecognitionException {
		EqOperContext _localctx = new EqOperContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_eqOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			_la = _input.LA(1);
			if ( !(_la==EQ || _la==NEQ) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BitwiseAndOperContext extends ParserRuleContext {
		public TerminalNode BITWISE_AND() { return getToken(BasicParser.BITWISE_AND, 0); }
		public BitwiseAndOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitwiseAndOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBitwiseAndOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BitwiseAndOperContext bitwiseAndOper() throws RecognitionException {
		BitwiseAndOperContext _localctx = new BitwiseAndOperContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bitwiseAndOper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(BITWISE_AND);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BitwiseXorOperContext extends ParserRuleContext {
		public TerminalNode BITWISE_XOR() { return getToken(BasicParser.BITWISE_XOR, 0); }
		public BitwiseXorOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitwiseXorOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBitwiseXorOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BitwiseXorOperContext bitwiseXorOper() throws RecognitionException {
		BitwiseXorOperContext _localctx = new BitwiseXorOperContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_bitwiseXorOper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(BITWISE_XOR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BitwiseOrOperContext extends ParserRuleContext {
		public TerminalNode BITWISE_OR() { return getToken(BasicParser.BITWISE_OR, 0); }
		public BitwiseOrOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitwiseOrOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBitwiseOrOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BitwiseOrOperContext bitwiseOrOper() throws RecognitionException {
		BitwiseOrOperContext _localctx = new BitwiseOrOperContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_bitwiseOrOper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(BITWISE_OR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndOperContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(BasicParser.AND, 0); }
		public AndOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAndOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndOperContext andOper() throws RecognitionException {
		AndOperContext _localctx = new AndOperContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_andOper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(AND);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrOperContext extends ParserRuleContext {
		public TerminalNode OR() { return getToken(BasicParser.OR, 0); }
		public OrOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitOrOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrOperContext orOper() throws RecognitionException {
		OrOperContext _localctx = new OrOperContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_orOper);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(OR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BaseTypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(BasicParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(BasicParser.BOOL, 0); }
		public TerminalNode CHAR() { return getToken(BasicParser.CHAR, 0); }
		public TerminalNode STRING() { return getToken(BasicParser.STRING, 0); }
		public BaseTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBaseType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BaseTypeContext baseType() throws RecognitionException {
		BaseTypeContext _localctx = new BaseTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_baseType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << INT) | (1L << CHAR) | (1L << STRING))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemContext extends ParserRuleContext {
		public TerminalNode FST() { return getToken(BasicParser.FST, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SND() { return getToken(BasicParser.SND, 0); }
		public PairElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemContext pairElem() throws RecognitionException {
		PairElemContext _localctx = new PairElemContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_pairElem);
		try {
			setState(106);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FST:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				match(FST);
				setState(103);
				expr(0);
				}
				break;
			case SND:
				enterOuterAlt(_localctx, 2);
				{
				setState(104);
				match(SND);
				setState(105);
				expr(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairElemTypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode OPEN_BRACKETS() { return getToken(BasicParser.OPEN_BRACKETS, 0); }
		public TerminalNode CLOSE_BRACKETS() { return getToken(BasicParser.CLOSE_BRACKETS, 0); }
		public TerminalNode PAIR() { return getToken(BasicParser.PAIR, 0); }
		public PairElemTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairElemType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairElemType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairElemTypeContext pairElemType() throws RecognitionException {
		PairElemTypeContext _localctx = new PairElemTypeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_pairElemType);
		try {
			setState(114);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				baseType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				type(0);
				setState(110);
				match(OPEN_BRACKETS);
				setState(111);
				match(CLOSE_BRACKETS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(113);
				match(PAIR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairTypeContext extends ParserRuleContext {
		public TerminalNode PAIR() { return getToken(BasicParser.PAIR, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public List<PairElemTypeContext> pairElemType() {
			return getRuleContexts(PairElemTypeContext.class);
		}
		public PairElemTypeContext pairElemType(int i) {
			return getRuleContext(PairElemTypeContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public PairTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pairType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPairType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairTypeContext pairType() throws RecognitionException {
		PairTypeContext _localctx = new PairTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pairType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(PAIR);
			setState(117);
			match(OPEN_PARENTHESES);
			setState(118);
			pairElemType();
			setState(119);
			match(COMMA);
			setState(120);
			pairElemType();
			setState(121);
			match(CLOSE_PARENTHESES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public BaseTypeContext baseType() {
			return getRuleContext(BaseTypeContext.class,0);
		}
		public PairTypeContext pairType() {
			return getRuleContext(PairTypeContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode OPEN_BRACKETS() { return getToken(BasicParser.OPEN_BRACKETS, 0); }
		public TerminalNode CLOSE_BRACKETS() { return getToken(BasicParser.CLOSE_BRACKETS, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case INT:
			case CHAR:
			case STRING:
				{
				setState(124);
				baseType();
				}
				break;
			case PAIR:
				{
				setState(125);
				pairType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(133);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(128);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(129);
					match(OPEN_BRACKETS);
					setState(130);
					match(CLOSE_BRACKETS);
					}
					} 
				}
				setState(135);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArrayElemContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public List<TerminalNode> OPEN_BRACKETS() { return getTokens(BasicParser.OPEN_BRACKETS); }
		public TerminalNode OPEN_BRACKETS(int i) {
			return getToken(BasicParser.OPEN_BRACKETS, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> CLOSE_BRACKETS() { return getTokens(BasicParser.CLOSE_BRACKETS); }
		public TerminalNode CLOSE_BRACKETS(int i) {
			return getToken(BasicParser.CLOSE_BRACKETS, i);
		}
		public ArrayElemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayElem; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArrayElem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayElemContext arrayElem() throws RecognitionException {
		ArrayElemContext _localctx = new ArrayElemContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_arrayElem);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(IDENT);
			setState(141); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(137);
					match(OPEN_BRACKETS);
					setState(138);
					expr(0);
					setState(139);
					match(CLOSE_BRACKETS);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(143); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayTypeContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode OPEN_BRACKETS() { return getToken(BasicParser.OPEN_BRACKETS, 0); }
		public TerminalNode CLOSE_BRACKETS() { return getToken(BasicParser.CLOSE_BRACKETS, 0); }
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_arrayType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			type(0);
			setState(146);
			match(OPEN_BRACKETS);
			setState(147);
			match(CLOSE_BRACKETS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayLiterContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKETS() { return getToken(BasicParser.OPEN_BRACKETS, 0); }
		public TerminalNode CLOSE_BRACKETS() { return getToken(BasicParser.CLOSE_BRACKETS, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public ArrayLiterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiter; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArrayLiter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiterContext arrayLiter() throws RecognitionException {
		ArrayLiterContext _localctx = new ArrayLiterContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_arrayLiter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(OPEN_BRACKETS);
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 34)) & ~0x3f) == 0 && ((1L << (_la - 34)) & ((1L << (NOT - 34)) | (1L << (LEN - 34)) | (1L << (ORD - 34)) | (1L << (CHR - 34)) | (1L << (BITWISE_NOT - 34)) | (1L << (PLUS - 34)) | (1L << (MINUS - 34)) | (1L << (OPEN_PARENTHESES - 34)) | (1L << (IDENT - 34)) | (1L << (CHAR_LIT - 34)) | (1L << (STRING_LIT - 34)) | (1L << (BOOL_LIT - 34)) | (1L << (BIN_INT_LIT - 34)) | (1L << (OCT_INT_LIT - 34)) | (1L << (HEX_INT_LIT - 34)) | (1L << (INT_LIT - 34)) | (1L << (PAIR_LIT - 34)))) != 0)) {
				{
				setState(150);
				expr(0);
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(151);
					match(COMMA);
					setState(152);
					expr(0);
					}
					}
					setState(157);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(160);
			match(CLOSE_BRACKETS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryOperContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(BasicParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public TerminalNode LEN() { return getToken(BasicParser.LEN, 0); }
		public TerminalNode ORD() { return getToken(BasicParser.ORD, 0); }
		public TerminalNode CHR() { return getToken(BasicParser.CHR, 0); }
		public TerminalNode BITWISE_NOT() { return getToken(BasicParser.BITWISE_NOT, 0); }
		public UnaryOperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOper; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitUnaryOper(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryOperContext unaryOper() throws RecognitionException {
		UnaryOperContext _localctx = new UnaryOperContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_unaryOper);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << LEN) | (1L << ORD) | (1L << CHR) | (1L << BITWISE_NOT) | (1L << MINUS))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public TerminalNode INT_LIT() { return getToken(BasicParser.INT_LIT, 0); }
		public TerminalNode BIN_INT_LIT() { return getToken(BasicParser.BIN_INT_LIT, 0); }
		public TerminalNode OCT_INT_LIT() { return getToken(BasicParser.OCT_INT_LIT, 0); }
		public TerminalNode HEX_INT_LIT() { return getToken(BasicParser.HEX_INT_LIT, 0); }
		public TerminalNode PLUS() { return getToken(BasicParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(BasicParser.MINUS, 0); }
		public TerminalNode BOOL_LIT() { return getToken(BasicParser.BOOL_LIT, 0); }
		public TerminalNode CHAR_LIT() { return getToken(BasicParser.CHAR_LIT, 0); }
		public TerminalNode STRING_LIT() { return getToken(BasicParser.STRING_LIT, 0); }
		public TerminalNode PAIR_LIT() { return getToken(BasicParser.PAIR_LIT, 0); }
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public UnaryOperContext unaryOper() {
			return getRuleContext(UnaryOperContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public MulDivModOperContext mulDivModOper() {
			return getRuleContext(MulDivModOperContext.class,0);
		}
		public PlusMinusOperContext plusMinusOper() {
			return getRuleContext(PlusMinusOperContext.class,0);
		}
		public CompOperContext compOper() {
			return getRuleContext(CompOperContext.class,0);
		}
		public EqOperContext eqOper() {
			return getRuleContext(EqOperContext.class,0);
		}
		public BitwiseAndOperContext bitwiseAndOper() {
			return getRuleContext(BitwiseAndOperContext.class,0);
		}
		public BitwiseXorOperContext bitwiseXorOper() {
			return getRuleContext(BitwiseXorOperContext.class,0);
		}
		public BitwiseOrOperContext bitwiseOrOper() {
			return getRuleContext(BitwiseOrOperContext.class,0);
		}
		public AndOperContext andOper() {
			return getRuleContext(AndOperContext.class,0);
		}
		public OrOperContext orOper() {
			return getRuleContext(OrOperContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(165);
				match(INT_LIT);
				}
				break;
			case 2:
				{
				setState(166);
				match(BIN_INT_LIT);
				}
				break;
			case 3:
				{
				setState(167);
				match(OCT_INT_LIT);
				}
				break;
			case 4:
				{
				setState(168);
				match(HEX_INT_LIT);
				}
				break;
			case 5:
				{
				setState(169);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(170);
				match(INT_LIT);
				}
				break;
			case 6:
				{
				setState(171);
				match(BOOL_LIT);
				}
				break;
			case 7:
				{
				setState(172);
				match(CHAR_LIT);
				}
				break;
			case 8:
				{
				setState(173);
				match(STRING_LIT);
				}
				break;
			case 9:
				{
				setState(174);
				match(PAIR_LIT);
				}
				break;
			case 10:
				{
				setState(175);
				match(IDENT);
				}
				break;
			case 11:
				{
				setState(176);
				arrayElem();
				}
				break;
			case 12:
				{
				setState(177);
				unaryOper();
				setState(178);
				expr(11);
				}
				break;
			case 13:
				{
				setState(180);
				match(OPEN_PARENTHESES);
				setState(181);
				expr(0);
				setState(182);
				match(CLOSE_PARENTHESES);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(224);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(222);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(186);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(187);
						mulDivModOper();
						setState(188);
						expr(11);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(190);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(191);
						plusMinusOper();
						setState(192);
						expr(10);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(194);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(195);
						compOper();
						setState(196);
						expr(9);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(198);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(199);
						eqOper();
						setState(200);
						expr(8);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(202);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(203);
						bitwiseAndOper();
						setState(204);
						expr(7);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(206);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(207);
						bitwiseXorOper();
						setState(208);
						expr(6);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(210);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(211);
						bitwiseOrOper();
						setState(212);
						expr(5);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(214);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(215);
						andOper();
						setState(216);
						expr(4);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(218);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(219);
						orOper();
						setState(220);
						expr(3);
						}
						break;
					}
					} 
				}
				setState(226);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			type(0);
			setState(228);
			match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			param();
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(231);
				match(COMMA);
				setState(232);
				param();
				}
				}
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignLHSContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public ArrayElemContext arrayElem() {
			return getRuleContext(ArrayElemContext.class,0);
		}
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public AssignLHSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignLHS; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignLHS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignLHSContext assignLHS() throws RecognitionException {
		AssignLHSContext _localctx = new AssignLHSContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_assignLHS);
		try {
			setState(241);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(238);
				match(IDENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				arrayElem();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(240);
				pairElem();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(BasicParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(BasicParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitArgList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			expr(0);
			setState(248);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(244);
				match(COMMA);
				setState(245);
				expr(0);
				}
				}
				setState(250);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public TerminalNode CALL() { return getToken(BasicParser.CALL, 0); }
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(CALL);
			setState(252);
			match(IDENT);
			setState(253);
			match(OPEN_PARENTHESES);
			setState(255);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 34)) & ~0x3f) == 0 && ((1L << (_la - 34)) & ((1L << (NOT - 34)) | (1L << (LEN - 34)) | (1L << (ORD - 34)) | (1L << (CHR - 34)) | (1L << (BITWISE_NOT - 34)) | (1L << (PLUS - 34)) | (1L << (MINUS - 34)) | (1L << (OPEN_PARENTHESES - 34)) | (1L << (IDENT - 34)) | (1L << (CHAR_LIT - 34)) | (1L << (STRING_LIT - 34)) | (1L << (BOOL_LIT - 34)) | (1L << (BIN_INT_LIT - 34)) | (1L << (OCT_INT_LIT - 34)) | (1L << (HEX_INT_LIT - 34)) | (1L << (INT_LIT - 34)) | (1L << (PAIR_LIT - 34)))) != 0)) {
				{
				setState(254);
				argList();
				}
			}

			setState(257);
			match(CLOSE_PARENTHESES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NewpairContext extends ParserRuleContext {
		public TerminalNode NEWPAIR() { return getToken(BasicParser.NEWPAIR, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(BasicParser.COMMA, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public NewpairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newpair; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitNewpair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewpairContext newpair() throws RecognitionException {
		NewpairContext _localctx = new NewpairContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_newpair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			match(NEWPAIR);
			setState(260);
			match(OPEN_PARENTHESES);
			setState(261);
			expr(0);
			setState(262);
			match(COMMA);
			setState(263);
			expr(0);
			setState(264);
			match(CLOSE_PARENTHESES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignRHSContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArrayLiterContext arrayLiter() {
			return getRuleContext(ArrayLiterContext.class,0);
		}
		public NewpairContext newpair() {
			return getRuleContext(NewpairContext.class,0);
		}
		public PairElemContext pairElem() {
			return getRuleContext(PairElemContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public AssignRHSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignRHS; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignRHS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignRHSContext assignRHS() throws RecognitionException {
		AssignRHSContext _localctx = new AssignRHSContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_assignRHS);
		try {
			setState(271);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
			case LEN:
			case ORD:
			case CHR:
			case BITWISE_NOT:
			case PLUS:
			case MINUS:
			case OPEN_PARENTHESES:
			case IDENT:
			case CHAR_LIT:
			case STRING_LIT:
			case BOOL_LIT:
			case BIN_INT_LIT:
			case OCT_INT_LIT:
			case HEX_INT_LIT:
			case INT_LIT:
			case PAIR_LIT:
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				expr(0);
				}
				break;
			case OPEN_BRACKETS:
				enterOuterAlt(_localctx, 2);
				{
				setState(267);
				arrayLiter();
				}
				break;
			case NEWPAIR:
				enterOuterAlt(_localctx, 3);
				{
				setState(268);
				newpair();
				}
				break;
			case FST:
			case SND:
				enterOuterAlt(_localctx, 4);
				{
				setState(269);
				pairElem();
				}
				break;
			case CALL:
				enterOuterAlt(_localctx, 5);
				{
				setState(270);
				functionCall();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclareStatContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode EQUAL() { return getToken(BasicParser.EQUAL, 0); }
		public AssignRHSContext assignRHS() {
			return getRuleContext(AssignRHSContext.class,0);
		}
		public DeclareStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declareStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitDeclareStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclareStatContext declareStat() throws RecognitionException {
		DeclareStatContext _localctx = new DeclareStatContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_declareStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			type(0);
			setState(274);
			match(IDENT);
			setState(275);
			match(EQUAL);
			setState(276);
			assignRHS();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignStatContext extends ParserRuleContext {
		public AssignLHSContext assignLHS() {
			return getRuleContext(AssignLHSContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(BasicParser.EQUAL, 0); }
		public AssignRHSContext assignRHS() {
			return getRuleContext(AssignRHSContext.class,0);
		}
		public AssignStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitAssignStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignStatContext assignStat() throws RecognitionException {
		AssignStatContext _localctx = new AssignStatContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_assignStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			assignLHS();
			setState(279);
			match(EQUAL);
			setState(280);
			assignRHS();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReadStatContext extends ParserRuleContext {
		public TerminalNode READ() { return getToken(BasicParser.READ, 0); }
		public AssignLHSContext assignLHS() {
			return getRuleContext(AssignLHSContext.class,0);
		}
		public ReadStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_readStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitReadStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReadStatContext readStat() throws RecognitionException {
		ReadStatContext _localctx = new ReadStatContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_readStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			match(READ);
			setState(283);
			assignLHS();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FreeStatContext extends ParserRuleContext {
		public TerminalNode FREE() { return getToken(BasicParser.FREE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FreeStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_freeStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitFreeStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FreeStatContext freeStat() throws RecognitionException {
		FreeStatContext _localctx = new FreeStatContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_freeStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(FREE);
			setState(286);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(BasicParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitReturnStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatContext returnStat() throws RecognitionException {
		ReturnStatContext _localctx = new ReturnStatContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_returnStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			match(RETURN);
			setState(289);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExitStatContext extends ParserRuleContext {
		public TerminalNode EXIT() { return getToken(BasicParser.EXIT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExitStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exitStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitExitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExitStatContext exitStat() throws RecognitionException {
		ExitStatContext _localctx = new ExitStatContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_exitStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(EXIT);
			setState(292);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrintStatContext extends ParserRuleContext {
		public TerminalNode PRINT() { return getToken(BasicParser.PRINT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrintStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPrintStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintStatContext printStat() throws RecognitionException {
		PrintStatContext _localctx = new PrintStatContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_printStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			match(PRINT);
			setState(295);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrintlnStatContext extends ParserRuleContext {
		public TerminalNode PRINTLN() { return getToken(BasicParser.PRINTLN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrintlnStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printlnStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitPrintlnStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintlnStatContext printlnStat() throws RecognitionException {
		PrintlnStatContext _localctx = new PrintlnStatContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_printlnStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			match(PRINTLN);
			setState(298);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(BasicParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode THEN() { return getToken(BasicParser.THEN, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(BasicParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(BasicParser.FI, 0); }
		public IfStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitIfStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatContext ifStat() throws RecognitionException {
		IfStatContext _localctx = new IfStatContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_ifStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			match(IF);
			setState(301);
			expr(0);
			setState(302);
			match(THEN);
			setState(303);
			stat(0);
			setState(304);
			match(ELSE);
			setState(305);
			stat(0);
			setState(306);
			match(FI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStatContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(BasicParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DO() { return getToken(BasicParser.DO, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode DONE() { return getToken(BasicParser.DONE, 0); }
		public WhileStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitWhileStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatContext whileStat() throws RecognitionException {
		WhileStatContext _localctx = new WhileStatContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_whileStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			match(WHILE);
			setState(309);
			expr(0);
			setState(310);
			match(DO);
			setState(311);
			stat(0);
			setState(312);
			match(DONE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BeginStatContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(BasicParser.BEGIN, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public BeginStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_beginStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitBeginStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BeginStatContext beginStat() throws RecognitionException {
		BeginStatContext _localctx = new BeginStatContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_beginStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			match(BEGIN);
			setState(315);
			stat(0);
			setState(316);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VoidFuncCallStatContext extends ParserRuleContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public VoidFuncCallStatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_voidFuncCallStat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitVoidFuncCallStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VoidFuncCallStatContext voidFuncCallStat() throws RecognitionException {
		VoidFuncCallStatContext _localctx = new VoidFuncCallStatContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_voidFuncCallStat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			functionCall();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public TerminalNode SKP() { return getToken(BasicParser.SKP, 0); }
		public DeclareStatContext declareStat() {
			return getRuleContext(DeclareStatContext.class,0);
		}
		public AssignStatContext assignStat() {
			return getRuleContext(AssignStatContext.class,0);
		}
		public ReadStatContext readStat() {
			return getRuleContext(ReadStatContext.class,0);
		}
		public FreeStatContext freeStat() {
			return getRuleContext(FreeStatContext.class,0);
		}
		public ReturnStatContext returnStat() {
			return getRuleContext(ReturnStatContext.class,0);
		}
		public ExitStatContext exitStat() {
			return getRuleContext(ExitStatContext.class,0);
		}
		public PrintStatContext printStat() {
			return getRuleContext(PrintStatContext.class,0);
		}
		public PrintlnStatContext printlnStat() {
			return getRuleContext(PrintlnStatContext.class,0);
		}
		public IfStatContext ifStat() {
			return getRuleContext(IfStatContext.class,0);
		}
		public WhileStatContext whileStat() {
			return getRuleContext(WhileStatContext.class,0);
		}
		public BeginStatContext beginStat() {
			return getRuleContext(BeginStatContext.class,0);
		}
		public VoidFuncCallStatContext voidFuncCallStat() {
			return getRuleContext(VoidFuncCallStatContext.class,0);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TerminalNode SEMICOL() { return getToken(BasicParser.SEMICOL, 0); }
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		return stat(0);
	}

	private StatContext stat(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatContext _localctx = new StatContext(_ctx, _parentState);
		StatContext _prevctx = _localctx;
		int _startState = 76;
		enterRecursionRule(_localctx, 76, RULE_stat, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SKP:
				{
				setState(321);
				match(SKP);
				}
				break;
			case PAIR:
			case BOOL:
			case INT:
			case CHAR:
			case STRING:
				{
				setState(322);
				declareStat();
				}
				break;
			case FST:
			case SND:
			case IDENT:
				{
				setState(323);
				assignStat();
				}
				break;
			case READ:
				{
				setState(324);
				readStat();
				}
				break;
			case FREE:
				{
				setState(325);
				freeStat();
				}
				break;
			case RETURN:
				{
				setState(326);
				returnStat();
				}
				break;
			case EXIT:
				{
				setState(327);
				exitStat();
				}
				break;
			case PRINT:
				{
				setState(328);
				printStat();
				}
				break;
			case PRINTLN:
				{
				setState(329);
				printlnStat();
				}
				break;
			case IF:
				{
				setState(330);
				ifStat();
				}
				break;
			case WHILE:
				{
				setState(331);
				whileStat();
				}
				break;
			case BEGIN:
				{
				setState(332);
				beginStat();
				}
				break;
			case CALL:
				{
				setState(333);
				voidFuncCallStat();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(341);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_stat);
					setState(336);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(337);
					match(SEMICOL);
					setState(338);
					stat(2);
					}
					} 
				}
				setState(343);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FuncContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(BasicParser.IDENT, 0); }
		public TerminalNode OPEN_PARENTHESES() { return getToken(BasicParser.OPEN_PARENTHESES, 0); }
		public TerminalNode CLOSE_PARENTHESES() { return getToken(BasicParser.CLOSE_PARENTHESES, 0); }
		public TerminalNode IS() { return getToken(BasicParser.IS, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode VOID() { return getToken(BasicParser.VOID, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PAIR:
			case BOOL:
			case INT:
			case CHAR:
			case STRING:
				{
				setState(344);
				type(0);
				}
				break;
			case VOID:
				{
				setState(345);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(348);
			match(IDENT);
			setState(349);
			match(OPEN_PARENTHESES);
			setState(351);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PAIR) | (1L << BOOL) | (1L << INT) | (1L << CHAR) | (1L << STRING))) != 0)) {
				{
				setState(350);
				paramList();
				}
			}

			setState(353);
			match(CLOSE_PARENTHESES);
			setState(354);
			match(IS);
			setState(355);
			stat(0);
			setState(356);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(BasicParser.BEGIN, 0); }
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public TerminalNode END() { return getToken(BasicParser.END, 0); }
		public TerminalNode EOF() { return getToken(BasicParser.EOF, 0); }
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicParserVisitor ) return ((BasicParserVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_prog);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
			match(BEGIN);
			setState(362);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(359);
					func();
					}
					} 
				}
				setState(364);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			setState(365);
			stat(0);
			setState(366);
			match(END);
			setState(367);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 13:
			return type_sempred((TypeContext)_localctx, predIndex);
		case 18:
			return expr_sempred((ExprContext)_localctx, predIndex);
		case 38:
			return stat_sempred((StatContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean stat_sempred(StatContext _localctx, int predIndex) {
		switch (predIndex) {
		case 10:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3F\u0174\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\5\fm\n\f\3\r\3\r\3\r\3\r\3\r\3\r\5\ru\n\r\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\5\17\u0081\n\17\3\17\3\17"+
		"\3\17\7\17\u0086\n\17\f\17\16\17\u0089\13\17\3\20\3\20\3\20\3\20\3\20"+
		"\6\20\u0090\n\20\r\20\16\20\u0091\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\7\22\u009c\n\22\f\22\16\22\u009f\13\22\5\22\u00a1\n\22\3\22\3\22\3"+
		"\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u00bb\n\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\7\24\u00e1\n\24\f\24\16\24\u00e4\13\24\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\7\26\u00ec\n\26\f\26\16\26\u00ef\13\26\3\27"+
		"\3\27\3\27\5\27\u00f4\n\27\3\30\3\30\3\30\7\30\u00f9\n\30\f\30\16\30\u00fc"+
		"\13\30\3\31\3\31\3\31\3\31\5\31\u0102\n\31\3\31\3\31\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\5\33\u0112\n\33\3\34\3\34"+
		"\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3"+
		" \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3"+
		"%\3%\3%\3&\3&\3&\3&\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3("+
		"\5(\u0151\n(\3(\3(\3(\7(\u0156\n(\f(\16(\u0159\13(\3)\3)\5)\u015d\n)\3"+
		")\3)\3)\5)\u0162\n)\3)\3)\3)\3)\3)\3*\3*\7*\u016b\n*\f*\16*\u016e\13*"+
		"\3*\3*\3*\3*\3*\2\5\34&N+\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&"+
		"(*,.\60\62\64\668:<>@BDFHJLNPR\2\b\3\2+-\3\2)*\3\2.\61\3\2\62\63\3\2\35"+
		" \4\2$(**\2\u0180\2T\3\2\2\2\4V\3\2\2\2\6X\3\2\2\2\bZ\3\2\2\2\n\\\3\2"+
		"\2\2\f^\3\2\2\2\16`\3\2\2\2\20b\3\2\2\2\22d\3\2\2\2\24f\3\2\2\2\26l\3"+
		"\2\2\2\30t\3\2\2\2\32v\3\2\2\2\34\u0080\3\2\2\2\36\u008a\3\2\2\2 \u0093"+
		"\3\2\2\2\"\u0097\3\2\2\2$\u00a4\3\2\2\2&\u00ba\3\2\2\2(\u00e5\3\2\2\2"+
		"*\u00e8\3\2\2\2,\u00f3\3\2\2\2.\u00f5\3\2\2\2\60\u00fd\3\2\2\2\62\u0105"+
		"\3\2\2\2\64\u0111\3\2\2\2\66\u0113\3\2\2\28\u0118\3\2\2\2:\u011c\3\2\2"+
		"\2<\u011f\3\2\2\2>\u0122\3\2\2\2@\u0125\3\2\2\2B\u0128\3\2\2\2D\u012b"+
		"\3\2\2\2F\u012e\3\2\2\2H\u0136\3\2\2\2J\u013c\3\2\2\2L\u0140\3\2\2\2N"+
		"\u0150\3\2\2\2P\u015c\3\2\2\2R\u0168\3\2\2\2TU\t\2\2\2U\3\3\2\2\2VW\t"+
		"\3\2\2W\5\3\2\2\2XY\t\4\2\2Y\7\3\2\2\2Z[\t\5\2\2[\t\3\2\2\2\\]\7\66\2"+
		"\2]\13\3\2\2\2^_\7\67\2\2_\r\3\2\2\2`a\78\2\2a\17\3\2\2\2bc\7\64\2\2c"+
		"\21\3\2\2\2de\7\65\2\2e\23\3\2\2\2fg\t\6\2\2g\25\3\2\2\2hi\7\33\2\2im"+
		"\5&\24\2jk\7\34\2\2km\5&\24\2lh\3\2\2\2lj\3\2\2\2m\27\3\2\2\2nu\5\24\13"+
		"\2op\5\34\17\2pq\7;\2\2qr\7<\2\2ru\3\2\2\2su\7\t\2\2tn\3\2\2\2to\3\2\2"+
		"\2ts\3\2\2\2u\31\3\2\2\2vw\7\t\2\2wx\79\2\2xy\5\30\r\2yz\7\"\2\2z{\5\30"+
		"\r\2{|\7:\2\2|\33\3\2\2\2}~\b\17\1\2~\u0081\5\24\13\2\177\u0081\5\32\16"+
		"\2\u0080}\3\2\2\2\u0080\177\3\2\2\2\u0081\u0087\3\2\2\2\u0082\u0083\f"+
		"\4\2\2\u0083\u0084\7;\2\2\u0084\u0086\7<\2\2\u0085\u0082\3\2\2\2\u0086"+
		"\u0089\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\35\3\2\2"+
		"\2\u0089\u0087\3\2\2\2\u008a\u008f\7=\2\2\u008b\u008c\7;\2\2\u008c\u008d"+
		"\5&\24\2\u008d\u008e\7<\2\2\u008e\u0090\3\2\2\2\u008f\u008b\3\2\2\2\u0090"+
		"\u0091\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\37\3\2\2"+
		"\2\u0093\u0094\5\34\17\2\u0094\u0095\7;\2\2\u0095\u0096\7<\2\2\u0096!"+
		"\3\2\2\2\u0097\u00a0\7;\2\2\u0098\u009d\5&\24\2\u0099\u009a\7\"\2\2\u009a"+
		"\u009c\5&\24\2\u009b\u0099\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2"+
		"\2\2\u009d\u009e\3\2\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2\2\2\u00a0"+
		"\u0098\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\7<"+
		"\2\2\u00a3#\3\2\2\2\u00a4\u00a5\t\7\2\2\u00a5%\3\2\2\2\u00a6\u00a7\b\24"+
		"\1\2\u00a7\u00bb\7E\2\2\u00a8\u00bb\7B\2\2\u00a9\u00bb\7C\2\2\u00aa\u00bb"+
		"\7D\2\2\u00ab\u00ac\t\3\2\2\u00ac\u00bb\7E\2\2\u00ad\u00bb\7A\2\2\u00ae"+
		"\u00bb\7?\2\2\u00af\u00bb\7@\2\2\u00b0\u00bb\7F\2\2\u00b1\u00bb\7=\2\2"+
		"\u00b2\u00bb\5\36\20\2\u00b3\u00b4\5$\23\2\u00b4\u00b5\5&\24\r\u00b5\u00bb"+
		"\3\2\2\2\u00b6\u00b7\79\2\2\u00b7\u00b8\5&\24\2\u00b8\u00b9\7:\2\2\u00b9"+
		"\u00bb\3\2\2\2\u00ba\u00a6\3\2\2\2\u00ba\u00a8\3\2\2\2\u00ba\u00a9\3\2"+
		"\2\2\u00ba\u00aa\3\2\2\2\u00ba\u00ab\3\2\2\2\u00ba\u00ad\3\2\2\2\u00ba"+
		"\u00ae\3\2\2\2\u00ba\u00af\3\2\2\2\u00ba\u00b0\3\2\2\2\u00ba\u00b1\3\2"+
		"\2\2\u00ba\u00b2\3\2\2\2\u00ba\u00b3\3\2\2\2\u00ba\u00b6\3\2\2\2\u00bb"+
		"\u00e2\3\2\2\2\u00bc\u00bd\f\f\2\2\u00bd\u00be\5\2\2\2\u00be\u00bf\5&"+
		"\24\r\u00bf\u00e1\3\2\2\2\u00c0\u00c1\f\13\2\2\u00c1\u00c2\5\4\3\2\u00c2"+
		"\u00c3\5&\24\f\u00c3\u00e1\3\2\2\2\u00c4\u00c5\f\n\2\2\u00c5\u00c6\5\6"+
		"\4\2\u00c6\u00c7\5&\24\13\u00c7\u00e1\3\2\2\2\u00c8\u00c9\f\t\2\2\u00c9"+
		"\u00ca\5\b\5\2\u00ca\u00cb\5&\24\n\u00cb\u00e1\3\2\2\2\u00cc\u00cd\f\b"+
		"\2\2\u00cd\u00ce\5\n\6\2\u00ce\u00cf\5&\24\t\u00cf\u00e1\3\2\2\2\u00d0"+
		"\u00d1\f\7\2\2\u00d1\u00d2\5\f\7\2\u00d2\u00d3\5&\24\b\u00d3\u00e1\3\2"+
		"\2\2\u00d4\u00d5\f\6\2\2\u00d5\u00d6\5\16\b\2\u00d6\u00d7\5&\24\7\u00d7"+
		"\u00e1\3\2\2\2\u00d8\u00d9\f\5\2\2\u00d9\u00da\5\20\t\2\u00da\u00db\5"+
		"&\24\6\u00db\u00e1\3\2\2\2\u00dc\u00dd\f\4\2\2\u00dd\u00de\5\22\n\2\u00de"+
		"\u00df\5&\24\5\u00df\u00e1\3\2\2\2\u00e0\u00bc\3\2\2\2\u00e0\u00c0\3\2"+
		"\2\2\u00e0\u00c4\3\2\2\2\u00e0\u00c8\3\2\2\2\u00e0\u00cc\3\2\2\2\u00e0"+
		"\u00d0\3\2\2\2\u00e0\u00d4\3\2\2\2\u00e0\u00d8\3\2\2\2\u00e0\u00dc\3\2"+
		"\2\2\u00e1\u00e4\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3"+
		"\'\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e5\u00e6\5\34\17\2\u00e6\u00e7\7=\2"+
		"\2\u00e7)\3\2\2\2\u00e8\u00ed\5(\25\2\u00e9\u00ea\7\"\2\2\u00ea\u00ec"+
		"\5(\25\2\u00eb\u00e9\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed"+
		"\u00ee\3\2\2\2\u00ee+\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f4\7=\2\2\u00f1"+
		"\u00f4\5\36\20\2\u00f2\u00f4\5\26\f\2\u00f3\u00f0\3\2\2\2\u00f3\u00f1"+
		"\3\2\2\2\u00f3\u00f2\3\2\2\2\u00f4-\3\2\2\2\u00f5\u00fa\5&\24\2\u00f6"+
		"\u00f7\7\"\2\2\u00f7\u00f9\5&\24\2\u00f8\u00f6\3\2\2\2\u00f9\u00fc\3\2"+
		"\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb/\3\2\2\2\u00fc\u00fa"+
		"\3\2\2\2\u00fd\u00fe\7\32\2\2\u00fe\u00ff\7=\2\2\u00ff\u0101\79\2\2\u0100"+
		"\u0102\5.\30\2\u0101\u0100\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0103\3\2"+
		"\2\2\u0103\u0104\7:\2\2\u0104\61\3\2\2\2\u0105\u0106\7\31\2\2\u0106\u0107"+
		"\79\2\2\u0107\u0108\5&\24\2\u0108\u0109\7\"\2\2\u0109\u010a\5&\24\2\u010a"+
		"\u010b\7:\2\2\u010b\63\3\2\2\2\u010c\u0112\5&\24\2\u010d\u0112\5\"\22"+
		"\2\u010e\u0112\5\62\32\2\u010f\u0112\5\26\f\2\u0110\u0112\5\60\31\2\u0111"+
		"\u010c\3\2\2\2\u0111\u010d\3\2\2\2\u0111\u010e\3\2\2\2\u0111\u010f\3\2"+
		"\2\2\u0111\u0110\3\2\2\2\u0112\65\3\2\2\2\u0113\u0114\5\34\17\2\u0114"+
		"\u0115\7=\2\2\u0115\u0116\7>\2\2\u0116\u0117\5\64\33\2\u0117\67\3\2\2"+
		"\2\u0118\u0119\5,\27\2\u0119\u011a\7>\2\2\u011a\u011b\5\64\33\2\u011b"+
		"9\3\2\2\2\u011c\u011d\7\13\2\2\u011d\u011e\5,\27\2\u011e;\3\2\2\2\u011f"+
		"\u0120\7\f\2\2\u0120\u0121\5&\24\2\u0121=\3\2\2\2\u0122\u0123\7\r\2\2"+
		"\u0123\u0124\5&\24\2\u0124?\3\2\2\2\u0125\u0126\7\16\2\2\u0126\u0127\5"+
		"&\24\2\u0127A\3\2\2\2\u0128\u0129\7\17\2\2\u0129\u012a\5&\24\2\u012aC"+
		"\3\2\2\2\u012b\u012c\7\20\2\2\u012c\u012d\5&\24\2\u012dE\3\2\2\2\u012e"+
		"\u012f\7\21\2\2\u012f\u0130\5&\24\2\u0130\u0131\7\22\2\2\u0131\u0132\5"+
		"N(\2\u0132\u0133\7\23\2\2\u0133\u0134\5N(\2\u0134\u0135\7\24\2\2\u0135"+
		"G\3\2\2\2\u0136\u0137\7\26\2\2\u0137\u0138\5&\24\2\u0138\u0139\7\27\2"+
		"\2\u0139\u013a\5N(\2\u013a\u013b\7\30\2\2\u013bI\3\2\2\2\u013c\u013d\7"+
		"\6\2\2\u013d\u013e\5N(\2\u013e\u013f\7\7\2\2\u013fK\3\2\2\2\u0140\u0141"+
		"\5\60\31\2\u0141M\3\2\2\2\u0142\u0143\b(\1\2\u0143\u0151\7\n\2\2\u0144"+
		"\u0151\5\66\34\2\u0145\u0151\58\35\2\u0146\u0151\5:\36\2\u0147\u0151\5"+
		"<\37\2\u0148\u0151\5> \2\u0149\u0151\5@!\2\u014a\u0151\5B\"\2\u014b\u0151"+
		"\5D#\2\u014c\u0151\5F$\2\u014d\u0151\5H%\2\u014e\u0151\5J&\2\u014f\u0151"+
		"\5L\'\2\u0150\u0142\3\2\2\2\u0150\u0144\3\2\2\2\u0150\u0145\3\2\2\2\u0150"+
		"\u0146\3\2\2\2\u0150\u0147\3\2\2\2\u0150\u0148\3\2\2\2\u0150\u0149\3\2"+
		"\2\2\u0150\u014a\3\2\2\2\u0150\u014b\3\2\2\2\u0150\u014c\3\2\2\2\u0150"+
		"\u014d\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u014f\3\2\2\2\u0151\u0157\3\2"+
		"\2\2\u0152\u0153\f\3\2\2\u0153\u0154\7#\2\2\u0154\u0156\5N(\4\u0155\u0152"+
		"\3\2\2\2\u0156\u0159\3\2\2\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158"+
		"O\3\2\2\2\u0159\u0157\3\2\2\2\u015a\u015d\5\34\17\2\u015b\u015d\7!\2\2"+
		"\u015c\u015a\3\2\2\2\u015c\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u015f"+
		"\7=\2\2\u015f\u0161\79\2\2\u0160\u0162\5*\26\2\u0161\u0160\3\2\2\2\u0161"+
		"\u0162\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0164\7:\2\2\u0164\u0165\7\b"+
		"\2\2\u0165\u0166\5N(\2\u0166\u0167\7\7\2\2\u0167Q\3\2\2\2\u0168\u016c"+
		"\7\6\2\2\u0169\u016b\5P)\2\u016a\u0169\3\2\2\2\u016b\u016e\3\2\2\2\u016c"+
		"\u016a\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016f\3\2\2\2\u016e\u016c\3\2"+
		"\2\2\u016f\u0170\5N(\2\u0170\u0171\7\7\2\2\u0171\u0172\7\2\2\3\u0172S"+
		"\3\2\2\2\26lt\u0080\u0087\u0091\u009d\u00a0\u00ba\u00e0\u00e2\u00ed\u00f3"+
		"\u00fa\u0101\u0111\u0150\u0157\u015c\u0161\u016c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}