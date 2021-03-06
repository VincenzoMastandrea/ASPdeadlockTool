/*
 * @Author Vincenzo Mastandrea
 * Grammar definition for ASP
 */
 
grammar ASP;
 
@header{
import asp.models.*;
import java.util.HashMap;
import java.util.LinkedList; 
}

prog		:   (classDec)* body;

methodSign	:	type ID LPAR (parDef)* RPAR;

methodDef	:	methodSign body;

classDec	: 	CLASS ID LPAR (parDef)* RPAR LCBRACK (varDec)* (methodDef)* RCBRACK;

body		:   LCBRACK (varDec)* (stmt)* RCBRACK;

varDec		:	type listvar SEMI;

parDef		:	parDef COMMA parDef | type listvar;

type		:	ID;

listvar		:	listvar COMMA listvar 
				| ID;

stmt		: SKIP SEMI
            | ID EQUALS exprse SEMI
            | IF (exprb)  THEN (stmt | stmtblock)  (ELSE (stmt | stmtblock) )?
            | RETURN expr SEMI
            ;
            
stmtblock	: LCBRACK (stmt)* RCBRACK;
		

exprse		: expr 
			| elem DOT ID LPAR (expr)* RPAR 
			| NEW ID LPAR (expr)* RPAR 
			| NEWACT ID LPAR (expr)* RPAR;

expr		: val 
			| elem 
			| expra 
			| exprb	;   

elem		: ID | THIS;

val			: NULL | NUMBER | TRUE | FALSE;


expra		: expra ('*'|'/') expra
    		| expra ('+'|'-') expra
    		| NUMBER	| ID
    		| '(' expra ')'
    		;
    	
exprb		: NOT exprb	
			| exprb (OR | AND | EQUALS | DISTINCT | GT | LT | GEG | LEG) exprb    		
    		| expra | val | elem  
    		| '(' exprb ')'
    		;
    	
lcbrak		: LCBRACK	;
rcbrak		: RCBRACK	;

//TOKENS
LPAR  	: '('	;
RPAR  	: ')'	;
RSBRACK : ']'	;
LSBRACK : '['	;
RCBRACK : '}'	;
LCBRACK : '{'	;
PLUS    : '+' 	;
MINUS   : '-' 	;
MULT    : '*' 	;
DIV     : '/' 	;
AND     : '&&' 	;
OR      : '||'	;
GT      : '>' 	;
LT      : '<' 	;
GEG     : '>='	;
LEG     : '<=' 	;
EQUALS  : '='	;
DISTINCT: '!='	;
NOT     : '!' 	;
SKIP    : 'skip';
COMMA   : ','	;
COLON   : ':'	;
SEMI    : ';'	;
DOT     : '.'	;
NULL    : 'null';
TRUE    : 'true';
FALSE   : 'false' ;
CLASS	: 'class' ;
RETURN	: 'return';
THIS	: 'this'  ;
NEW		: 'new'	  ;
NEWACT	: 'newActive' ;
IF		: 'if'	  ;
THEN	: 'then'  ;
ELSE	: 'else'  ;


fragment CHAR 	: 'a'..'z' |'A'..'Z' ;
ID              : CHAR (CHAR | DIGIT)* ;

fragment DIGIT	: '0'..'9';	
NUMBER          : DIGIT+;

//SCAPED SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip;
LINECOMENTS 	: '//' (~('\n'|'\r'))* -> skip;
BLOCKCOMENTS    : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMENTS)* '*/' -> skip;