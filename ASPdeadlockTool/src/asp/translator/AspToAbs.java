package asp.translator;

import java.util.HashMap;
import java.util.LinkedList;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import asp.models.ClassDecl;
import asp.models.Declaration;
import asp.models.Method;
import asp.parser.ASPBaseListener;
import asp.parser.ASPParser;

public class AspToAbs extends ASPBaseListener {
	
	private static final String SP = " ";
	private static HashMap<String, ClassDecl> classTable;
	
	
	public AspToAbs(HashMap<String, ClassDecl> classTable){
		this.classTable = classTable;
	}
	
	
	public static LinkedList<String> buildInterface()
	{   
		LinkedList<String> interfaces = new LinkedList<>();
		for(String className : classTable.keySet())
		{
			ClassDecl c = classTable.get(className);
			String interf = "interface" + SP + c.getClassName() + "{";
			LinkedList<Method> methods = c.getMethodList();
			for(Method method : methods)
			{
				interf += method.getReturnedType().getType() + SP + 
						  method.getMethodName() + SP + "(";
				HashMap<Integer,Declaration> parameters = method.getParameters();
				for(Integer key : parameters.keySet())
				{
					int count = 0;
					count++;
					if(count == parameters.size())
						interf += parameters.get(key).toString();
					else
						interf += parameters.get(key).toString() + ", ";
				}
				interf += "); ";
			}
			interf += "}";
			interfaces.add(interf);
		}
		return interfaces;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterProgram(@NotNull ASPParser.ProgramContext ctx) { String interf = "";
																				for(String s : buildInterface())
																				{
																					interf = interf + s + "\n"; 
																				}	
																				System.out.println(ctx.MOD().toString() + SP + 
																								   ctx.IDUC().toString() + ctx.SEMI().toString() +
																								   "\n" + interf ); }
	
	
	/*****************************
	*							 *
	*   	  CLASS DEF			 *
	*							 *
	*****************************/
	
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterClassSignature(@NotNull ASPParser.ClassSignatureContext ctx) {System.out.print(ctx.CLASS().toString()    + SP +
			 																						            ctx.className.getText() + SP +
			 																						            ctx.LPAR().toString()
			 																						         ); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitClassSignature(@NotNull ASPParser.ClassSignatureContext ctx) {System.out.println(ctx.RPAR().toString() + SP +
																											   "implements" + SP + 
																											    ctx.className.getText() + "{"); }
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitClassDec(@NotNull ASPParser.ClassDecContext ctx) {System.out.println(ctx.RCBRACK().getText());}
	
	
	/* ***************************
	*   	  PARAMETERS		 *
	*****************************/
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitTypeVariable(@NotNull ASPParser.TypeVariableContext ctx) {System.out.print(ctx.t.getText() + SP + ctx.v.getText()); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterSecondPair(@NotNull ASPParser.SecondPairContext ctx) { System.out.print(ctx.COMMA().getText() + SP); }
	
	
	
	/*****************************
	*							 *
	*      METHOD SIGNATURE	     *
	*							 *
	*****************************/
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterMethodSignature(@NotNull ASPParser.MethodSignatureContext ctx) {System.out.print( ctx.returnedType.getText() + SP +
																							    				 ctx.methodName.getText()   +
																							    				 ctx.LPAR().getText()
																							    			   ); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitMethodSignature(@NotNull ASPParser.MethodSignatureContext ctx)  { System.out.print(ctx.RPAR().getText()); }
	
	
	/*****************************
	*							 *
	*      		BODY		     *
	*							 *
	*****************************/
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterBody(@NotNull ASPParser.BodyContext ctx) { System.out.println(ctx.LCBRACK().getText());}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitBody(@NotNull ASPParser.BodyContext ctx) { System.out.println(ctx.RCBRACK().getText());}
	
	/* ***************************
	*    VARIABLE DECLARATION	 *
	******************************/
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitObjDec(@NotNull ASPParser.ObjDecContext ctx) {System.out.println(ctx.t.getText() + SP + 
																							 ctx.v.getText() + 
																							 ctx.SEMI().getText()); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitIntDec(@NotNull ASPParser.IntDecContext ctx) {System.out.println(ctx.t.getText() + SP + 
			 																				 ctx.v.getText() + SP +
			 																				 ctx.ASSIGN().getText() + SP +
			 																				 ctx.NUMBER().getText() +
			 																				 ctx.SEMI().getText()); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitBoolDec(@NotNull ASPParser.BoolDecContext ctx) {System.out.println(ctx.t.getText() + SP + 
			 																				   ctx.v.getText() + SP +
			 																				   ctx.ASSIGN().getText() + SP +
			 																				   ctx.val.getText() +
			 																				   ctx.SEMI().getText()); }
	
	
	/* ****************************
	*   	  STATEMENT			 *
	*****************************/
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterSkipStmt(@NotNull ASPParser.SkipStmtContext ctx) { System.out.println(ctx.SKIP().toString() + ctx.SEMI().toString()); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterAssignStmt(@NotNull ASPParser.AssignStmtContext ctx) { System.out.print(ctx.l.getText() + SP + 
																									   ctx.ASSIGN().toString() + SP); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterMethodCall(@NotNull ASPParser.MethodCallContext ctx) {System.out.print(ctx.elem.getText()   +
								 													 				  ctx.DOT().getText()  + 
								 													 				  ctx.methodName.getText() +
								 													 				  ctx.LPAR().getText()
								 													 				 );}
	@Override public void exitMethodCall(@NotNull ASPParser.MethodCallContext ctx) {System.out.print( ctx.RPAR().getText());}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitExpression(@NotNull ASPParser.ExpressionContext ctx) {System.out.print(ctx.getText());}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterNewExp(@NotNull ASPParser.NewExpContext ctx) {System.out.print(ctx.NEW().getText() + SP + 
																							  "local" + SP +
																							  ctx.className.getText() +
																							  ctx.LPAR().getText());}
	@Override public void exitNewExp(@NotNull ASPParser.NewExpContext ctx) {System.out.print(ctx.RPAR().getText());}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterNewActExp(@NotNull ASPParser.NewActExpContext ctx) {System.out.print("new" + SP + 
																									ctx.className.getText() + 
																									ctx.LPAR().getText()); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitNewActExp(@NotNull ASPParser.NewActExpContext ctx) {System.out.print(ctx.RPAR().getText()); }
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterStmtblock(@NotNull ASPParser.StmtblockContext ctx) { System.out.print(ctx.LCBRACK().getText());}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitStmtblock(@NotNull ASPParser.StmtblockContext ctx) { System.out.print(ctx.RCBRACK().getText());}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterReturnStmt(@NotNull ASPParser.ReturnStmtContext ctx) { System.out.print(ctx.RETURN().getText() + SP);}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitReturnStmt(@NotNull ASPParser.ReturnStmtContext ctx) { System.out.print(";");}
	
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterIfStmt(@NotNull ASPParser.IfStmtContext ctx) { System.out.println(ctx.IF().getText() + SP + 
																							   ctx.LPAR().getText() + 
																							   ctx.guard.getText()  +
																							   ctx.RPAR().getText()); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitIfStmt(@NotNull ASPParser.IfStmtContext ctx) { }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterElseStmt(@NotNull ASPParser.ElseStmtContext ctx) {System.out.println(ctx.ELSE().getText() + SP ); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitAssignStmt(@NotNull ASPParser.AssignStmtContext ctx) {System.out.println(";"); }
	


	
	
	
	
	
	

}