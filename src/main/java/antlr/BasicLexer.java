// Generated from ./BasicLexer.g4 by ANTLR 4.9.3
package antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BasicLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"COMMENT", "WS", "EOL", "BEGIN", "END", "IS", "TRUE", "FALSE", "PAIR", 
			"SKP", "READ", "FREE", "RETURN", "EXIT", "PRINT", "PRINTLN", "IF", "THEN", 
			"ELSE", "FI", "FOR", "WHILE", "DO", "DONE", "NULL", "NEWPAIR", "CALL", 
			"FST", "SND", "BOOL", "INT", "CHAR", "STRING", "VOID", "COMMA", "SEMICOL", 
			"SINGLE_QUOTE", "DOUBLE_QUOTE", "UNDERSCORE", "SLASH", "NOT", "LEN", 
			"ORD", "CHR", "BITWISE_NOT", "PLUS", "MINUS", "MULT", "DIVIDE", "MOD", 
			"GT", "ST", "GTE", "STE", "EQ", "NEQ", "AND", "OR", "BITWISE_AND", "BITWISE_XOR", 
			"BITWISE_OR", "OPEN_PARENTHESES", "CLOSE_PARENTHESES", "OPEN_BRACKETS", 
			"CLOSE_BRACKETS", "DIGIT", "BIN_DIGIT", "OCT_DIGIT", "HEX_DIGIT", "BIN_PREFIX", 
			"OCT_PREFIX", "HEX_PREFIX", "ESCAPED_CHAR", "LOWERCASE", "UPPERCASE", 
			"CHARACTER", "IDENT", "EQUAL", "CHAR_LIT", "STRING_LIT", "BOOL_LIT", 
			"BIN_INT_LIT", "OCT_INT_LIT", "HEX_INT_LIT", "INT_LIT", "PAIR_LIT"
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


	public BasicLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BasicLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2F\u020e\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\3\2\3\2\7\2\u00b2\n\2\f\2\16\2\u00b5\13\2\3\2\3\2\3"+
		"\2\3\2\3\3\6\3\u00bc\n\3\r\3\16\3\u00bd\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3"+
		"\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3"+
		"\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 "+
		"\3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3"+
		"$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3+\3+\3,\3,\3,\3,\3-\3-"+
		"\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3"+
		"\65\3\65\3\66\3\66\3\66\3\67\3\67\3\67\38\38\38\39\39\39\3:\3:\3:\3;\3"+
		";\3;\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@\3A\3A\3B\3B\3C\3C\3D\3D\3E\3E\3F\3"+
		"F\5F\u01b2\nF\3G\3G\3G\3G\5G\u01b8\nG\3H\3H\3H\3H\5H\u01be\nH\3I\3I\3"+
		"I\3I\5I\u01c4\nI\3J\3J\3J\3J\5J\u01ca\nJ\3K\3K\3L\3L\3M\3M\3M\5M\u01d3"+
		"\nM\3N\3N\3N\5N\u01d8\nN\3N\3N\3N\3N\7N\u01de\nN\fN\16N\u01e1\13N\3O\3"+
		"O\3P\3P\3P\3P\3Q\3Q\7Q\u01eb\nQ\fQ\16Q\u01ee\13Q\3Q\3Q\3R\3R\5R\u01f4"+
		"\nR\3S\3S\6S\u01f8\nS\rS\16S\u01f9\3T\3T\6T\u01fe\nT\rT\16T\u01ff\3U\3"+
		"U\6U\u0204\nU\rU\16U\u0205\3V\6V\u0209\nV\rV\16V\u020a\3W\3W\2\2X\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\2\21\2\23\t\25\n\27\13\31\f\33\r\35\16\37\17"+
		"!\20#\21%\22\'\23)\24+\25-\26/\27\61\30\63\2\65\31\67\329\33;\34=\35?"+
		"\36A\37C E!G\"I#K\2M\2O\2Q\2S$U%W&Y\'[(])_*a+c,e-g.i/k\60m\61o\62q\63"+
		"s\64u\65w\66y\67{8}9\177:\u0081;\u0083<\u0085\2\u0087\2\u0089\2\u008b"+
		"\2\u008d\2\u008f\2\u0091\2\u0093\2\u0095\2\u0097\2\u0099\2\u009b=\u009d"+
		">\u009f?\u00a1@\u00a3A\u00a5B\u00a7C\u00a9D\u00abE\u00adF\3\2\7\4\2\f"+
		"\f\17\17\5\2\13\f\17\17\"\"\4\2CHch\b\2\62\62ddhhppttvv\5\2$$))^^\2\u0211"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2"+
		"\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\65\3\2\2"+
		"\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2"+
		"\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W"+
		"\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2"+
		"\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2"+
		"\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}"+
		"\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u009b\3\2\2\2"+
		"\2\u009d\3\2\2\2\2\u009f\3\2\2\2\2\u00a1\3\2\2\2\2\u00a3\3\2\2\2\2\u00a5"+
		"\3\2\2\2\2\u00a7\3\2\2\2\2\u00a9\3\2\2\2\2\u00ab\3\2\2\2\2\u00ad\3\2\2"+
		"\2\3\u00af\3\2\2\2\5\u00bb\3\2\2\2\7\u00c1\3\2\2\2\t\u00c3\3\2\2\2\13"+
		"\u00c9\3\2\2\2\r\u00cd\3\2\2\2\17\u00d0\3\2\2\2\21\u00d5\3\2\2\2\23\u00db"+
		"\3\2\2\2\25\u00e0\3\2\2\2\27\u00e5\3\2\2\2\31\u00ea\3\2\2\2\33\u00ef\3"+
		"\2\2\2\35\u00f6\3\2\2\2\37\u00fb\3\2\2\2!\u0101\3\2\2\2#\u0109\3\2\2\2"+
		"%\u010c\3\2\2\2\'\u0111\3\2\2\2)\u0116\3\2\2\2+\u0119\3\2\2\2-\u011d\3"+
		"\2\2\2/\u0123\3\2\2\2\61\u0126\3\2\2\2\63\u012b\3\2\2\2\65\u0130\3\2\2"+
		"\2\67\u0138\3\2\2\29\u013d\3\2\2\2;\u0141\3\2\2\2=\u0145\3\2\2\2?\u014a"+
		"\3\2\2\2A\u014e\3\2\2\2C\u0153\3\2\2\2E\u015a\3\2\2\2G\u015f\3\2\2\2I"+
		"\u0161\3\2\2\2K\u0163\3\2\2\2M\u0165\3\2\2\2O\u0167\3\2\2\2Q\u0169\3\2"+
		"\2\2S\u016b\3\2\2\2U\u016d\3\2\2\2W\u0171\3\2\2\2Y\u0175\3\2\2\2[\u0179"+
		"\3\2\2\2]\u017b\3\2\2\2_\u017d\3\2\2\2a\u017f\3\2\2\2c\u0181\3\2\2\2e"+
		"\u0183\3\2\2\2g\u0185\3\2\2\2i\u0187\3\2\2\2k\u0189\3\2\2\2m\u018c\3\2"+
		"\2\2o\u018f\3\2\2\2q\u0192\3\2\2\2s\u0195\3\2\2\2u\u0198\3\2\2\2w\u019b"+
		"\3\2\2\2y\u019d\3\2\2\2{\u019f\3\2\2\2}\u01a1\3\2\2\2\177\u01a3\3\2\2"+
		"\2\u0081\u01a5\3\2\2\2\u0083\u01a7\3\2\2\2\u0085\u01a9\3\2\2\2\u0087\u01ab"+
		"\3\2\2\2\u0089\u01ad\3\2\2\2\u008b\u01b1\3\2\2\2\u008d\u01b7\3\2\2\2\u008f"+
		"\u01bd\3\2\2\2\u0091\u01c3\3\2\2\2\u0093\u01c9\3\2\2\2\u0095\u01cb\3\2"+
		"\2\2\u0097\u01cd\3\2\2\2\u0099\u01d2\3\2\2\2\u009b\u01d7\3\2\2\2\u009d"+
		"\u01e2\3\2\2\2\u009f\u01e4\3\2\2\2\u00a1\u01e8\3\2\2\2\u00a3\u01f3\3\2"+
		"\2\2\u00a5\u01f5\3\2\2\2\u00a7\u01fb\3\2\2\2\u00a9\u0201\3\2\2\2\u00ab"+
		"\u0208\3\2\2\2\u00ad\u020c\3\2\2\2\u00af\u00b3\7%\2\2\u00b0\u00b2\n\2"+
		"\2\2\u00b1\u00b0\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b4\u00b6\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b7\t\2"+
		"\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\b\2\2\2\u00b9\4\3\2\2\2\u00ba\u00bc"+
		"\t\3\2\2\u00bb\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd"+
		"\u00be\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\b\3\3\2\u00c0\6\3\2\2\2"+
		"\u00c1\u00c2\7\f\2\2\u00c2\b\3\2\2\2\u00c3\u00c4\7d\2\2\u00c4\u00c5\7"+
		"g\2\2\u00c5\u00c6\7i\2\2\u00c6\u00c7\7k\2\2\u00c7\u00c8\7p\2\2\u00c8\n"+
		"\3\2\2\2\u00c9\u00ca\7g\2\2\u00ca\u00cb\7p\2\2\u00cb\u00cc\7f\2\2\u00cc"+
		"\f\3\2\2\2\u00cd\u00ce\7k\2\2\u00ce\u00cf\7u\2\2\u00cf\16\3\2\2\2\u00d0"+
		"\u00d1\7v\2\2\u00d1\u00d2\7t\2\2\u00d2\u00d3\7w\2\2\u00d3\u00d4\7g\2\2"+
		"\u00d4\20\3\2\2\2\u00d5\u00d6\7h\2\2\u00d6\u00d7\7c\2\2\u00d7\u00d8\7"+
		"n\2\2\u00d8\u00d9\7u\2\2\u00d9\u00da\7g\2\2\u00da\22\3\2\2\2\u00db\u00dc"+
		"\7r\2\2\u00dc\u00dd\7c\2\2\u00dd\u00de\7k\2\2\u00de\u00df\7t\2\2\u00df"+
		"\24\3\2\2\2\u00e0\u00e1\7u\2\2\u00e1\u00e2\7m\2\2\u00e2\u00e3\7k\2\2\u00e3"+
		"\u00e4\7r\2\2\u00e4\26\3\2\2\2\u00e5\u00e6\7t\2\2\u00e6\u00e7\7g\2\2\u00e7"+
		"\u00e8\7c\2\2\u00e8\u00e9\7f\2\2\u00e9\30\3\2\2\2\u00ea\u00eb\7h\2\2\u00eb"+
		"\u00ec\7t\2\2\u00ec\u00ed\7g\2\2\u00ed\u00ee\7g\2\2\u00ee\32\3\2\2\2\u00ef"+
		"\u00f0\7t\2\2\u00f0\u00f1\7g\2\2\u00f1\u00f2\7v\2\2\u00f2\u00f3\7w\2\2"+
		"\u00f3\u00f4\7t\2\2\u00f4\u00f5\7p\2\2\u00f5\34\3\2\2\2\u00f6\u00f7\7"+
		"g\2\2\u00f7\u00f8\7z\2\2\u00f8\u00f9\7k\2\2\u00f9\u00fa\7v\2\2\u00fa\36"+
		"\3\2\2\2\u00fb\u00fc\7r\2\2\u00fc\u00fd\7t\2\2\u00fd\u00fe\7k\2\2\u00fe"+
		"\u00ff\7p\2\2\u00ff\u0100\7v\2\2\u0100 \3\2\2\2\u0101\u0102\7r\2\2\u0102"+
		"\u0103\7t\2\2\u0103\u0104\7k\2\2\u0104\u0105\7p\2\2\u0105\u0106\7v\2\2"+
		"\u0106\u0107\7n\2\2\u0107\u0108\7p\2\2\u0108\"\3\2\2\2\u0109\u010a\7k"+
		"\2\2\u010a\u010b\7h\2\2\u010b$\3\2\2\2\u010c\u010d\7v\2\2\u010d\u010e"+
		"\7j\2\2\u010e\u010f\7g\2\2\u010f\u0110\7p\2\2\u0110&\3\2\2\2\u0111\u0112"+
		"\7g\2\2\u0112\u0113\7n\2\2\u0113\u0114\7u\2\2\u0114\u0115\7g\2\2\u0115"+
		"(\3\2\2\2\u0116\u0117\7h\2\2\u0117\u0118\7k\2\2\u0118*\3\2\2\2\u0119\u011a"+
		"\7h\2\2\u011a\u011b\7q\2\2\u011b\u011c\7t\2\2\u011c,\3\2\2\2\u011d\u011e"+
		"\7y\2\2\u011e\u011f\7j\2\2\u011f\u0120\7k\2\2\u0120\u0121\7n\2\2\u0121"+
		"\u0122\7g\2\2\u0122.\3\2\2\2\u0123\u0124\7f\2\2\u0124\u0125\7q\2\2\u0125"+
		"\60\3\2\2\2\u0126\u0127\7f\2\2\u0127\u0128\7q\2\2\u0128\u0129\7p\2\2\u0129"+
		"\u012a\7g\2\2\u012a\62\3\2\2\2\u012b\u012c\7p\2\2\u012c\u012d\7w\2\2\u012d"+
		"\u012e\7n\2\2\u012e\u012f\7n\2\2\u012f\64\3\2\2\2\u0130\u0131\7p\2\2\u0131"+
		"\u0132\7g\2\2\u0132\u0133\7y\2\2\u0133\u0134\7r\2\2\u0134\u0135\7c\2\2"+
		"\u0135\u0136\7k\2\2\u0136\u0137\7t\2\2\u0137\66\3\2\2\2\u0138\u0139\7"+
		"e\2\2\u0139\u013a\7c\2\2\u013a\u013b\7n\2\2\u013b\u013c\7n\2\2\u013c8"+
		"\3\2\2\2\u013d\u013e\7h\2\2\u013e\u013f\7u\2\2\u013f\u0140\7v\2\2\u0140"+
		":\3\2\2\2\u0141\u0142\7u\2\2\u0142\u0143\7p\2\2\u0143\u0144\7f\2\2\u0144"+
		"<\3\2\2\2\u0145\u0146\7d\2\2\u0146\u0147\7q\2\2\u0147\u0148\7q\2\2\u0148"+
		"\u0149\7n\2\2\u0149>\3\2\2\2\u014a\u014b\7k\2\2\u014b\u014c\7p\2\2\u014c"+
		"\u014d\7v\2\2\u014d@\3\2\2\2\u014e\u014f\7e\2\2\u014f\u0150\7j\2\2\u0150"+
		"\u0151\7c\2\2\u0151\u0152\7t\2\2\u0152B\3\2\2\2\u0153\u0154\7u\2\2\u0154"+
		"\u0155\7v\2\2\u0155\u0156\7t\2\2\u0156\u0157\7k\2\2\u0157\u0158\7p\2\2"+
		"\u0158\u0159\7i\2\2\u0159D\3\2\2\2\u015a\u015b\7x\2\2\u015b\u015c\7q\2"+
		"\2\u015c\u015d\7k\2\2\u015d\u015e\7f\2\2\u015eF\3\2\2\2\u015f\u0160\7"+
		".\2\2\u0160H\3\2\2\2\u0161\u0162\7=\2\2\u0162J\3\2\2\2\u0163\u0164\7)"+
		"\2\2\u0164L\3\2\2\2\u0165\u0166\7$\2\2\u0166N\3\2\2\2\u0167\u0168\7a\2"+
		"\2\u0168P\3\2\2\2\u0169\u016a\7^\2\2\u016aR\3\2\2\2\u016b\u016c\7#\2\2"+
		"\u016cT\3\2\2\2\u016d\u016e\7n\2\2\u016e\u016f\7g\2\2\u016f\u0170\7p\2"+
		"\2\u0170V\3\2\2\2\u0171\u0172\7q\2\2\u0172\u0173\7t\2\2\u0173\u0174\7"+
		"f\2\2\u0174X\3\2\2\2\u0175\u0176\7e\2\2\u0176\u0177\7j\2\2\u0177\u0178"+
		"\7t\2\2\u0178Z\3\2\2\2\u0179\u017a\7\u0080\2\2\u017a\\\3\2\2\2\u017b\u017c"+
		"\7-\2\2\u017c^\3\2\2\2\u017d\u017e\7/\2\2\u017e`\3\2\2\2\u017f\u0180\7"+
		",\2\2\u0180b\3\2\2\2\u0181\u0182\7\61\2\2\u0182d\3\2\2\2\u0183\u0184\7"+
		"\'\2\2\u0184f\3\2\2\2\u0185\u0186\7@\2\2\u0186h\3\2\2\2\u0187\u0188\7"+
		">\2\2\u0188j\3\2\2\2\u0189\u018a\7@\2\2\u018a\u018b\7?\2\2\u018bl\3\2"+
		"\2\2\u018c\u018d\7>\2\2\u018d\u018e\7?\2\2\u018en\3\2\2\2\u018f\u0190"+
		"\7?\2\2\u0190\u0191\7?\2\2\u0191p\3\2\2\2\u0192\u0193\7#\2\2\u0193\u0194"+
		"\7?\2\2\u0194r\3\2\2\2\u0195\u0196\7(\2\2\u0196\u0197\7(\2\2\u0197t\3"+
		"\2\2\2\u0198\u0199\7~\2\2\u0199\u019a\7~\2\2\u019av\3\2\2\2\u019b\u019c"+
		"\7(\2\2\u019cx\3\2\2\2\u019d\u019e\7`\2\2\u019ez\3\2\2\2\u019f\u01a0\7"+
		"~\2\2\u01a0|\3\2\2\2\u01a1\u01a2\7*\2\2\u01a2~\3\2\2\2\u01a3\u01a4\7+"+
		"\2\2\u01a4\u0080\3\2\2\2\u01a5\u01a6\7]\2\2\u01a6\u0082\3\2\2\2\u01a7"+
		"\u01a8\7_\2\2\u01a8\u0084\3\2\2\2\u01a9\u01aa\4\62;\2\u01aa\u0086\3\2"+
		"\2\2\u01ab\u01ac\4\62\63\2\u01ac\u0088\3\2\2\2\u01ad\u01ae\4\629\2\u01ae"+
		"\u008a\3\2\2\2\u01af\u01b2\5\u0085C\2\u01b0\u01b2\t\4\2\2\u01b1\u01af"+
		"\3\2\2\2\u01b1\u01b0\3\2\2\2\u01b2\u008c\3\2\2\2\u01b3\u01b4\7\62\2\2"+
		"\u01b4\u01b8\7d\2\2\u01b5\u01b6\7\62\2\2\u01b6\u01b8\7D\2\2\u01b7\u01b3"+
		"\3\2\2\2\u01b7\u01b5\3\2\2\2\u01b8\u008e\3\2\2\2\u01b9\u01ba\7\62\2\2"+
		"\u01ba\u01be\7q\2\2\u01bb\u01bc\7\62\2\2\u01bc\u01be\7Q\2\2\u01bd\u01b9"+
		"\3\2\2\2\u01bd\u01bb\3\2\2\2\u01be\u0090\3\2\2\2\u01bf\u01c0\7\62\2\2"+
		"\u01c0\u01c4\7z\2\2\u01c1\u01c2\7\62\2\2\u01c2\u01c4\7Z\2\2\u01c3\u01bf"+
		"\3\2\2\2\u01c3\u01c1\3\2\2\2\u01c4\u0092\3\2\2\2\u01c5\u01ca\t\5\2\2\u01c6"+
		"\u01ca\5M\'\2\u01c7\u01ca\5K&\2\u01c8\u01ca\5Q)\2\u01c9\u01c5\3\2\2\2"+
		"\u01c9\u01c6\3\2\2\2\u01c9\u01c7\3\2\2\2\u01c9\u01c8\3\2\2\2\u01ca\u0094"+
		"\3\2\2\2\u01cb\u01cc\4c|\2\u01cc\u0096\3\2\2\2\u01cd\u01ce\4C\\\2\u01ce"+
		"\u0098\3\2\2\2\u01cf\u01d3\n\6\2\2\u01d0\u01d1\7^\2\2\u01d1\u01d3\5\u0093"+
		"J\2\u01d2\u01cf\3\2\2\2\u01d2\u01d0\3\2\2\2\u01d3\u009a\3\2\2\2\u01d4"+
		"\u01d8\5O(\2\u01d5\u01d8\5\u0095K\2\u01d6\u01d8\5\u0097L\2\u01d7\u01d4"+
		"\3\2\2\2\u01d7\u01d5\3\2\2\2\u01d7\u01d6\3\2\2\2\u01d8\u01df\3\2\2\2\u01d9"+
		"\u01de\5O(\2\u01da\u01de\5\u0095K\2\u01db\u01de\5\u0097L\2\u01dc\u01de"+
		"\5\u0085C\2\u01dd\u01d9\3\2\2\2\u01dd\u01da\3\2\2\2\u01dd\u01db\3\2\2"+
		"\2\u01dd\u01dc\3\2\2\2\u01de\u01e1\3\2\2\2\u01df\u01dd\3\2\2\2\u01df\u01e0"+
		"\3\2\2\2\u01e0\u009c\3\2\2\2\u01e1\u01df\3\2\2\2\u01e2\u01e3\7?\2\2\u01e3"+
		"\u009e\3\2\2\2\u01e4\u01e5\5K&\2\u01e5\u01e6\5\u0099M\2\u01e6\u01e7\5"+
		"K&\2\u01e7\u00a0\3\2\2\2\u01e8\u01ec\5M\'\2\u01e9\u01eb\5\u0099M\2\u01ea"+
		"\u01e9\3\2\2\2\u01eb\u01ee\3\2\2\2\u01ec\u01ea\3\2\2\2\u01ec\u01ed\3\2"+
		"\2\2\u01ed\u01ef\3\2\2\2\u01ee\u01ec\3\2\2\2\u01ef\u01f0\5M\'\2\u01f0"+
		"\u00a2\3\2\2\2\u01f1\u01f4\5\17\b\2\u01f2\u01f4\5\21\t\2\u01f3\u01f1\3"+
		"\2\2\2\u01f3\u01f2\3\2\2\2\u01f4\u00a4\3\2\2\2\u01f5\u01f7\5\u008dG\2"+
		"\u01f6\u01f8\5\u0087D\2\u01f7\u01f6\3\2\2\2\u01f8\u01f9\3\2\2\2\u01f9"+
		"\u01f7\3\2\2\2\u01f9\u01fa\3\2\2\2\u01fa\u00a6\3\2\2\2\u01fb\u01fd\5\u008f"+
		"H\2\u01fc\u01fe\5\u0089E\2\u01fd\u01fc\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ff"+
		"\u01fd\3\2\2\2\u01ff\u0200\3\2\2\2\u0200\u00a8\3\2\2\2\u0201\u0203\5\u0091"+
		"I\2\u0202\u0204\5\u008bF\2\u0203\u0202\3\2\2\2\u0204\u0205\3\2\2\2\u0205"+
		"\u0203\3\2\2\2\u0205\u0206\3\2\2\2\u0206\u00aa\3\2\2\2\u0207\u0209\5\u0085"+
		"C\2\u0208\u0207\3\2\2\2\u0209\u020a\3\2\2\2\u020a\u0208\3\2\2\2\u020a"+
		"\u020b\3\2\2\2\u020b\u00ac\3\2\2\2\u020c\u020d\5\63\32\2\u020d\u00ae\3"+
		"\2\2\2\24\2\u00b3\u00bd\u01b1\u01b7\u01bd\u01c3\u01c9\u01d2\u01d7\u01dd"+
		"\u01df\u01ec\u01f3\u01f9\u01ff\u0205\u020a\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}