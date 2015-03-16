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
program returns [Program prog]
				@init{HashMap<String, ClassDecl> classMap = new HashMap<>();}
				:   (classDec {classMap.put($classDec.classObj.getClassName(),$classDec.classObj);})* main=body  
				    {$prog = new Program(classMap, $main.stb);};
				    
				    

classDec returns [ClassDecl classObj]
				@init{HashMap<String, LinkedList<Method>> methods = new HashMap<>();
					  LinkedList<Method> methodList = null;}
				: 	CLASS className=ID LPAR (parameters=parDef) RPAR 
				    LCBRACK fields=varDec (method=methodDef {  if (methods.containsValue($method.methodSign.getMethodName()))
				    	                                       {
				    	                                       	  LinkedList<Method> list = methods.get($method.methodSign.getMethodName());
				    	                                       	  for (Method m : list)
				    	                                       	  { 
				    	                                       	  	if (m.isEqual($method.methodSign))
				    	                                       	  	   System.out.println("Errore!");
				    	                                       	  	else
				    													methods.get($method.methodSign.getMethodName()).add($method.methodSign);
				    											  }
				    										   }
				    										   else
				    										   {  methodList = new LinkedList<>();
				    										   	  methodList.add($method.methodSign); 
				    	                                          methods.put($method.methodSign.getMethodName(),methodList);
				    	                                       }
				    	                                     })* RCBRACK
				    { 
				    	$classObj = new ClassDecl( $className.text,
				    	 					    $parameters.pars, 
				    	 					    $fields.vars,
				    	 					    methods
				    	 					   );
				    };
				    
methodDef returns [Method methodSign]
				  @init{HashMap<Integer,Declaration> parameters = new HashMap<>(); 
					    HashMap<String,Declaration> varDec = new HashMap<>();
						LinkedList<Statement> stmts = new LinkedList<>(); }	
				  :	returnedType=type methodName=ID LPAR (p=parDef) RPAR b=body
				    {$methodSign = new Method($returnedType.varType, $methodName.text, $p.pars,$b.stb); }
				  ;


body returns [StmtBlock stb]
			 @init{LinkedList<Statement> stmts = new LinkedList<>();
				  	HashMap<String,Declaration> vars = null;}
			 : LCBRACK (v=varDec {vars=$v.vars;}) (st=stmt {stmts.add($st.s);})* RCBRACK {$stb = new StmtBlock(vars,stmts);}
			 ;

varDec returns [HashMap<String,Declaration> vars]
			   @init{ $vars = new HashMap<>();
			   		  TypeBase type = null; 
			   		  Variable var = null; }		
     		:  (t=type v=variable SEMI  { type = $t.varType;
     									  var = $v.var;
     									  Declaration dec = new Declaration(type,var);
     								      $vars.put(var.getName(),dec);}    )*;

parDef returns [HashMap<Integer,Declaration> pars]
				@init{$pars = new HashMap<>();
					  TypeBase type = null; 
			   		  Variable var = null; 
			   		  int parIndex = 0;}
				: (t=type v=variable { type = $t.varType;
     								   var = $v.var;
     								   Declaration dec = new Declaration(type,var);
									   $pars.put((Integer) parIndex,dec); })?
				  ( COMMA t1=type v1=variable {type = $t1.varType;
     								   		   var = $v1.var;
     								   		   Declaration dec = new Declaration(type,var);
				  							   $pars.put((Integer) (parIndex +1),dec);}   )*;

type returns [TypeBase varType] 
            : ID   {$varType = new TypeObject($ID.text);}
            | INT  {$varType = new TypeInt();}
            | BOOL {$varType = new TypeBool();};

variable returns [Variable var]	
				 :   v=ID {$var = new Variable($v.text);};

stmt returns [Statement s]
			@init{  Variable var = null;
					ExpressionSideEffects exprse = null;
					Expression cond = null;
					Statement stTrue = null;
					Statement stFalse = null; }
     		: SKIP SEMI {$s = new Skip();}
            | l=ID {var = new Variable($l.text);} EQUALS r=expressionSideEffect {exprse = $r.exprse;} SEMI {$s = new Assignment(var,exprse);} 
            | IF LPAR (guard=booleanExpression {cond = $guard.exprBool;}) RPAR  
              THEN (trueSideS=stmt {stTrue = $trueSideS.s;}| trueSideSB=stmtblock {stTrue = $trueSideSB.stb;})   
              (ELSE (falseSideS=stmt {stFalse = $falseSideS.s;}| falseSideSB=stmtblock {stFalse = $falseSideSB.stb;}) )? 
              {$s = new IfThenElse(cond,stTrue,stFalse);}
            | RETURN e=expression SEMI {$s = new Return($e.expr);}
            ;
            
stmtblock returns [StmtBlock stb]
				  @init{$stb = new StmtBlock();
				  		LinkedList<Statement> stmts = new LinkedList<>();}
			      : LCBRACK (st=stmt {stmts.add($st.s);})* RCBRACK {$stb.setStmts(stmts);}
			      ;
		
expressionSideEffect returns [ExpressionSideEffects exprse]	
							 @init{LinkedList<Expression> param = new LinkedList<>();
							 	   Variable obj = null;}	
							 : exp=expression	{$exprse=$exp.expr;} 
							 | elem=element {obj = new Variable($elem.text);}DOT methodName=ID LPAR (par=expression {param.add($par.expr);})* RPAR 
							   					{$exprse = new MethodCall(obj,$methodName.text,param);} 
							 | NEW className=ID LPAR (par=expression {param.add($par.expr);})* RPAR     
							 					{$exprse= new New($className.text,param);}
							 | NEWACT className=ID LPAR (par=expression {param.add($par.expr);})* RPAR  
							 					{$exprse= new NewActive($className.text,param);} 
							 ;

expression returns [Expression expr]
   		    	   : v=value      				{$expr = $v.val;} 
				   | el=element 				{$expr = $el.elem;} 
				   | expra=arithmeticExpression	{$expr = $expra.exprArit;}
				   | exprb=booleanExpression	{$expr = $exprb.exprBool;}
				   ;    

arithmeticExpression returns [Expression exprArit]
	 			     		: l=arithmeticExpression o=('*'|'/') r=arithmeticExpression 
	 			     					{$exprArit = new ExpressionBinaryOperation($l.exprArit,$o.text,$r.exprArit);}
    						| l=arithmeticExpression o=('+'|'-') r=arithmeticExpression 
    									{$exprArit = new ExpressionBinaryOperation($l.exprArit,$o.text,$r.exprArit);}
    						| n=NUMBER    {$exprArit = new ExpressionIntValue(Integer.parseInt($n.text));}
    						| v=ID      {$exprArit = new Variable($v.text);}
    						| '(' arithmeticExpression ')'			
    						;
    	
booleanExpression returns [Expression exprBool]
     				      : n=NOT exb=booleanExpression		{$exprBool= new ExpressionUnaryOperation($n.text,$exb.exprBool);}
						  | l=booleanExpression o=(OR | AND | EQUALS | DISTINCT | GT | LT | GEG | LEG) r=booleanExpression  
						    								{$exprBool = new ExpressionBinaryOperation($l.exprBool,$o.text,$r.exprBool);}   		
    					  | exa=arithmeticExpression 		{$exprBool=$exa.exprArit;}
    					  | v=value 						{$exprBool=$v.val;} 
    				 	  | e=element  						{$exprBool=$e.elem;}	
    					  | '(' booleanExpression ')' 
    					  ;
    		
value returns [ExpressionValue val]
			  : NULL   	 {$val = new ExpressionNullValue();}
			  | n=NUMBER {$val = new ExpressionIntValue(Integer.parseInt($n.text));} 
			  | t=TRUE   {$val = new ExpressionBoolValue(Boolean.parseBoolean($t.text));}
			  | f=FALSE  {$val = new ExpressionBoolValue(Boolean.parseBoolean($f.text));}
			  ;
    		
element returns [Element elem]
                : ID   	 {$elem = new Variable($ID.text);}
                | t=THIS {$elem = new ExpressionValue($t.text);}
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
INT		: 'int'	  ;
BOOL	: 'bool'  ;

fragment CHAR 	: 'a'..'z' |'A'..'Z' ;
ID              : CHAR (CHAR | DIGIT)* ;

fragment DIGIT	: '0'..'9';	
NUMBER          : DIGIT+;

//SCAPED SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip;
LINECOMENTS 	: '//' (~('\n'|'\r'))* -> skip;
BLOCKCOMENTS    : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMENTS)* '*/' -> skip;