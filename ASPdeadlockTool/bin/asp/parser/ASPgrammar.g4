/*
 * @Author Vincenzo Mastandrea
 * Grammar definition for ASP
 */
 
grammar ASPgrammar;
 
@header{
import asp.models.*;
import java.util.HashMap;
import java.util.LinkedList; 
}

//TYPES
elem returns [TypeBase t] 
            : ID {$t= new Variable($ID.text);}
            | THIS {$t= new TypeObject();};


program returns [Program prog]
				@init{HashMap<String, Class> classMap = new HashMap<>();
					  StmtBlock main = new StmtBlock();}
				:   (classDec {classMap.add($classDec.className,$classDec.classObj)})* 
				    (body {main.setVarDec($body.varDec);
				    	   main.setStmts($body.stmts);}) 
				    {$prog = new Program(classMap, body)};

classDec returns [Class classObj]
				@init{LinkedList<TypeBase> fields = new LinkedList<>;
					  LinkedList<TypeBase> parameters = new LinkedList<>; 
					  HashMap<String, Method> methods = new LinkedList<>;}	
				: 	CLASS className=ID LPAR (parDef{parameters.add()})* RPAR LCBRACK (varDec)* (methodDef)* RCBRACK
				    {
				    	 $classObj = new Class($className,
				    	 					   fields, 
				    	 					   parameters, 
				    	 					   methods
				    	 );
				    };


methodSign	:	type ID LPAR (parDef)* RPAR;

methodDef	:	methodSign body;


body		:   LCBRACK (varDec)* (stmt)* RCBRACK;

varDec		:	type var SEMI;

parDef		:	parDef COMMA parDef | type var;

type		:	ID;

var			:   ID;

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