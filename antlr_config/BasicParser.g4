parser grammar BasicParser;

options {
	tokenVocab = BasicLexer;
}

mulDivModOper:
  MULT
  | DIVIDE
  | MOD;

plusMinusOper:
  PLUS
  | MINUS;

compOper:
  GT
  | GTE
  | ST
  | STE;

eqOper:
  EQ
  | NEQ;

bitwiseAndOper:
  BITWISE_AND;

bitwiseXorOper:
  BITWISE_XOR;

bitwiseOrOper:
  BITWISE_OR;

andOper:
  AND;

orOper:
  OR;

baseType: INT | BOOL | CHAR | STRING;

pairElem: FST expr | SND expr;

pairElemType:
	baseType
	| type OPEN_BRACKETS CLOSE_BRACKETS
	| PAIR;

pairType:
	PAIR OPEN_PARENTHESES pairElemType COMMA pairElemType CLOSE_PARENTHESES;

type: baseType | type OPEN_BRACKETS CLOSE_BRACKETS | pairType;

arrayElem: IDENT (OPEN_BRACKETS expr CLOSE_BRACKETS)+;

arrayType: type OPEN_BRACKETS CLOSE_BRACKETS;

arrayLiter: OPEN_BRACKETS (expr (COMMA expr)*)? CLOSE_BRACKETS;

unaryOper: NOT | MINUS | LEN | ORD | CHR | BITWISE_NOT;

expr:
	INT_LIT 
	| BIN_INT_LIT 
	| OCT_INT_LIT 
	| HEX_INT_LIT
  | (PLUS | MINUS) INT_LIT
	| BOOL_LIT
	| CHAR_LIT
	| STRING_LIT
	| PAIR_LIT
	| IDENT
	| arrayElem
	| unaryOper expr
  | expr mulDivModOper expr
  | expr plusMinusOper expr
  | expr compOper expr
  | expr eqOper expr
  | expr bitwiseAndOper expr
  | expr bitwiseXorOper expr
  | expr bitwiseOrOper expr
  | expr andOper expr
  | expr orOper expr
	| OPEN_PARENTHESES expr CLOSE_PARENTHESES;

param: type IDENT;

paramList: param (COMMA param)*;

assignLHS: IDENT | arrayElem | pairElem;

argList: expr (COMMA expr)*;

functionCall: CALL IDENT OPEN_PARENTHESES argList? CLOSE_PARENTHESES;
newpair: NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES;

assignRHS:
	expr
	| arrayLiter
	| newpair
	| pairElem
	| functionCall;

declareStat: type IDENT EQUAL assignRHS;
assignStat: assignLHS EQUAL assignRHS;
readStat: READ assignLHS;
freeStat: FREE expr;
returnStat: RETURN expr;
exitStat: EXIT expr;
printStat: PRINT expr;
printlnStat: PRINTLN expr;
ifStat: IF expr THEN stat ELSE stat FI;
whileStat: WHILE expr DO stat DONE;
beginStat: BEGIN stat END;
voidFuncCallStat: functionCall;

stat:
	SKP
	| declareStat
	| assignStat
	| readStat
	| freeStat
	| returnStat
	| exitStat
	| printStat
	| printlnStat
	| ifStat
	| whileStat
	| beginStat
	| voidFuncCallStat
	| stat SEMICOL stat;

func:
	(type | VOID) IDENT OPEN_PARENTHESES paramList? CLOSE_PARENTHESES IS stat END;

// EOF indicates that the program must consume to the end of the input.
prog: BEGIN func* stat END EOF;


