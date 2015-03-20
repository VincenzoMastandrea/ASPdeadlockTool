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

@members{
	public HashMap<String, ClassDecl> environment;
	public ClassDecl currentClass;
	public Method currentMethod;
	public Environment scope;
	
}

// A program start with key "module" moduleName semicolon, class definitions and main function
program returns [Program prog]
				@init{HashMap<String, ClassDecl> classMap = new HashMap<>();
					  environment = classMap;
				}
				:   MOD IDUC SEMI (classDec {classMap.put($classDec.classObj.getClassName(),$classDec.classObj);})* main=body  
				    {$prog = new Program(classMap, $main.stb);};
				    
				    

classDec returns [ClassDecl classObj]
				@init{HashMap<String, LinkedList<Method>> methods = new HashMap<>();
					  LinkedList<Method> methodList = null;
					  $classObj = new ClassDecl();
					  currentClass = $classObj;
					  }
				: 	CLASS className=IDUC {$classObj.setClassName($className.text);} 
				    LPAR (parameters=parDef) {$classObj.setParameters($parameters.pars);} RPAR 
				    LCBRACK fields=varDeclaration {$classObj.setFields($fields.vars);} 
				    		(method=methodDef {if (methods.containsKey($method.methodSign.getMethodName()))
				    	                	   { LinkedList<Method> list = methods.get($method.methodSign.getMethodName());
				    	                         if ($method.methodSign.isIn(list))
				    	                         { System.out.println("The method "+ $method.methodSign.getMethodName() +  
				    	                                              " has been defined more than once! Rename it");
				    	                           System.exit(0); }   
				    	                         else
				    	                         { $method.methodSign.setID(methods.get($method.methodSign.getMethodName()).size());
												   methods.get($method.methodSign.getMethodName()).add($method.methodSign);
											     }
 					    					   }
					    					   else
					    					   {  methodList = new LinkedList<>();
					    						  $method.methodSign.setID(0);
					    						  methodList.add($method.methodSign); 
					    	                      methods.put($method.methodSign.getMethodName(),methodList);
					    	                   }
				    	    })* 
				     RCBRACK
				    { $classObj.setMethods(methods);};
				    
methodDef returns [Method methodSign]
				  @init{scope = new Environment();
				  	    scope.putAllInt(currentClass.getParameters());
				  	    scope.putAll(currentClass.getFields());
				  		$methodSign = new Method();
				  	    currentMethod = $methodSign;
				  	    HashMap<Integer,Declaration> parameters = new HashMap<>(); 
					    HashMap<String,Declaration> varDec = new HashMap<>();
						LinkedList<Statement> stmts = new LinkedList<>(); }	
				  :	returnedType=type {$methodSign.setReturnedType($returnedType.varType);} 
				    methodName=IDLC {$methodSign.setMethodName($methodName.text);} 
				    LPAR (p=parDef) {$methodSign.setParameters($p.pars); scope.putAllInt($p.pars);}RPAR 
				    b=body
				    {$methodSign.setBody($b.stb); }
				  ;


body returns [StmtBlock stb]
			 @init{LinkedList<Statement> stmts = new LinkedList<>();
				  	HashMap<String,Declaration> vars = null;}
			 : LCBRACK (v=varDeclaration {vars=$v.vars; scope.putAll(vars); scope.print();}) (st=stmt {stmts.add($st.s);})* RCBRACK {$stb = new StmtBlock(vars,stmts);}
			 ;


varDeclaration returns [HashMap<String,Declaration> vars] 
				       @init{ $vars = new HashMap<>();}
		               : (v=varDec {$vars.put($v.dec.getVar().getName(),$v.dec);})*;

varDec returns [Declaration dec]
			   @init{ TypeBase type = null; 
			   		  Variable var = null; }		
     		: (t=objType v=variable SEMI  { type = $t.varType;
     									    var = $v.var;
     									    var.setType(type);
     									    $dec = new Declaration(type,var);} ) 
     		| (t1=intType v=variable ASSIGN NUMBER SEMI  { type = $t1.varType;
     									  				   var = $v.var;
     									  				   var.setType(type);
     									  				   $dec = new Declaration(type,var);} )
     		| (t2=boolType v=variable ASSIGN (TRUE | FALSE) SEMI  { type = $t2.varType;
     									  				  		    var = $v.var;
     									  				  		    var.setType(type);
     									  				  		    $dec = new Declaration(type,var);} )
     		;

parDef returns [HashMap<Integer,Declaration> pars]
				@init{$pars = new HashMap<>();
					  TypeBase type = null; 
			   		  Variable var = null; 
			   		  int parIndex = 0;}
				: (t=type v=variable { type = $t.varType;
     								   var = $v.var;
     								   var.setType(type);
     								   Declaration dec = new Declaration(type,var);
									   $pars.put((Integer) parIndex,dec); })?
				  ( COMMA t1=type v1=variable {type = $t1.varType;
     								   		   var = $v1.var;
     								   		   var.setType(type);
     								   		   Declaration dec = new Declaration(type,var);
				  							   $pars.put((Integer) (parIndex +1),dec);}   )*;

type returns [TypeBase varType] 
            : t=intType   {$varType = $t.varType;}
            | t1=boolType {$varType = $t1.varType;}
            | t2=objType  {$varType = $t2.varType;};
            
intType returns [TypeBase varType] 
            : INT  {$varType = new TypeInt();};
            
boolType returns [TypeBase varType] 
            : BOOL {$varType = new TypeBool();};
            

objType returns [TypeBase varType] 
            : IDUC   {$varType = new TypeObject($IDUC.text);};
            

variable returns [Variable var]	
				 :   v=IDLC {$var = new Variable($v.text);};

stmt returns [Statement s]
			@init{  Variable var = null;
					ExpressionSideEffects exprse = null;
					Expression cond = null;
					Statement stTrue = null;
					Statement stFalse = null; }
     		: SKIP SEMI {$s = new Skip();}
            | l=IDLC {var = scope.getVar($l.text);} ASSIGN r=expressionSideEffect {exprse = $r.exprse;} SEMI {$s = new Assignment(var,exprse);} 
            | IF LPAR (guard=booleanExpression {cond = $guard.exprBool;}) RPAR  
              (trueSideS=stmt {stTrue = $trueSideS.s;}| trueSideSB=stmtblock {stTrue = $trueSideSB.stb;})   
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
							 | elem=element {obj = scope.getVar($elem.text);}DOT methodName=IDLC LPAR (par=expression {param.add($par.expr);})* RPAR 
							   					{$exprse = new MethodCall(obj,$methodName.text,param);} 
							 | NEW className=IDUC LPAR (par=expression {param.add($par.expr);})* RPAR     
							 					{$exprse= new New($className.text,param);}
							 | NEWACT className=IDUC LPAR (par=expression {param.add($par.expr);})* RPAR  
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
    						| v=IDLC      {$exprArit = scope.getVar($v.text);;}
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
                : IDLC   	 {$elem = scope.getVar($IDLC.text);}
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
ASSIGN	: '='	;
EQUALS  : '=='	;
DISTINCT: '!='	;
NOT     : '!' 	;
SKIP    : 'skip';
COMMA   : ','	;
COLON   : ':'	; 
SEMI    : ';'	;
DOT     : '.'	;
NULL    : 'null';
TRUE    : 'True';
FALSE   : 'False' ;
MOD		: 'module';
CLASS	: 'class' ;
RETURN	: 'return';
THIS	: 'this'  ;
NEW		: 'new'	  ;
NEWACT	: 'newActive' ;
IF		: 'if'	  ;
THEN	: 'then'  ;
ELSE	: 'else'  ;
INT		: 'Int'	  ;
BOOL	: 'Bool'  ;

fragment CHAR 	: 'a'..'z' |'A'..'Z' ;
fragment CHARU  : 'a'..'z' |'A'..'Z' | '_';
fragment CHARUC : 'A'..'Z';
fragment CHARLC : 'a'..'z';



IDUC            : CHARUC (CHARU | DIGIT)* ;
IDLC            : CHARLC (CHARU | DIGIT)* ;


//ID              : (CHARLC | CHARUC | DIGIT | UNDER)* ;
//IDLC            : CHARLC ID ;
//IDUC            : CHARUC ID ;

fragment DIGIT	: '0'..'9';	
NUMBER          : DIGIT+;

//SCAPED SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip;
LINECOMENTS 	: '//' (~('\n'|'\r'))* -> skip;
BLOCKCOMENTS    : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMENTS)* '*/' -> skip;