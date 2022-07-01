lexer grammar BasicLexer;

//coment and whitespace
COMMENT: '#' ~[\r\n]* [\r\n] -> skip;
WS: [ \n\r\t]+ -> channel(HIDDEN);
EOL: '\n';

//program and function
BEGIN: 'begin';
END: 'end';
IS: 'is';

//bool
fragment TRUE: 'true';
fragment FALSE: 'false';

//pair
PAIR: 'pair';

//statements
SKP: 'skip';
READ: 'read';
FREE: 'free';
RETURN: 'return';
EXIT: 'exit';
PRINT: 'print';
PRINTLN: 'println';
IF: 'if';
THEN: 'then';
ELSE: 'else';
FI: 'fi';
FOR: 'for';
WHILE: 'while';
DO: 'do';
DONE: 'done';
fragment NULL: 'null';

//assign
NEWPAIR: 'newpair';
CALL: 'call';

//pair-elem
FST: 'fst';
SND: 'snd';

//base-type
BOOL: 'bool';
INT: 'int';
CHAR: 'char';
STRING: 'string';
VOID: 'void';

//punctuations
COMMA: ',';
SEMICOL: ';';
fragment SINGLE_QUOTE: '\'';
fragment DOUBLE_QUOTE: '"';
fragment UNDERSCORE: '_';
fragment SLASH: '\\';

//unary-operators
NOT: '!';
LEN: 'len';
ORD: 'ord';
CHR: 'chr';
BITWISE_NOT: '~';

//binary-operators
PLUS: '+';
MINUS: '-';
MULT: '*';
DIVIDE: '/';
MOD: '%';
GT: '>';
ST: '<';
GTE: '>=';
STE: '<=';
EQ: '==';
NEQ: '!=';
AND: '&&';
OR: '||';
BITWISE_AND: '&';
BITWISE_XOR: '^';
BITWISE_OR: '|';

//brackets
OPEN_PARENTHESES: '(';
CLOSE_PARENTHESES: ')';
OPEN_BRACKETS: '[';
CLOSE_BRACKETS: ']';

//numbers
fragment DIGIT: '0' ..'9';
fragment BIN_DIGIT: '0' ..'1';
fragment OCT_DIGIT: '0' ..'7';
fragment HEX_DIGIT: DIGIT | 'a' ..'f' | 'A' ..'F';
fragment BIN_PREFIX: '0b' | '0B';
fragment OCT_PREFIX: '0o' | '0O';
fragment HEX_PREFIX: '0x' | '0X';

//chars
fragment ESCAPED_CHAR:
	'0'
	| 'b'
	| 'n'
	| 'f'
	| 'r'
	| 't'
	| DOUBLE_QUOTE
	| SINGLE_QUOTE
	| SLASH;
fragment LOWERCASE: 'a' ..'z';
fragment UPPERCASE: 'A' ..'Z';
fragment CHARACTER: ~('\'' | '"' | '\\') | '\\' ESCAPED_CHAR;

IDENT: (UNDERSCORE | LOWERCASE | UPPERCASE) (
		UNDERSCORE
		| LOWERCASE
		| UPPERCASE
		| DIGIT
	)*;

//assignments
EQUAL: '=';

//literals
CHAR_LIT: SINGLE_QUOTE CHARACTER SINGLE_QUOTE;
STRING_LIT: DOUBLE_QUOTE (CHARACTER)* DOUBLE_QUOTE;
BOOL_LIT: TRUE | FALSE;
BIN_INT_LIT: BIN_PREFIX BIN_DIGIT+;
OCT_INT_LIT: OCT_PREFIX OCT_DIGIT+;
HEX_INT_LIT: HEX_PREFIX HEX_DIGIT+;
INT_LIT: DIGIT+;
PAIR_LIT: NULL;