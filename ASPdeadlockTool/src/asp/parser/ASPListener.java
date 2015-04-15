// Generated from ASP.g4 by ANTLR 4.4
package asp.parser;

import asp.models.*;
import java.util.HashMap;
import java.util.LinkedList; 

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ASPParser}.
 */
public interface ASPListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ASPParser#typeVariable}.
	 * @param ctx the parse tree
	 */
	void enterTypeVariable(@NotNull ASPParser.TypeVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#typeVariable}.
	 * @param ctx the parse tree
	 */
	void exitTypeVariable(@NotNull ASPParser.TypeVariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#valAritExp}.
	 * @param ctx the parse tree
	 */
	void enterValAritExp(@NotNull ASPParser.ValAritExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#valAritExp}.
	 * @param ctx the parse tree
	 */
	void exitValAritExp(@NotNull ASPParser.ValAritExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#rcbrak}.
	 * @param ctx the parse tree
	 */
	void enterRcbrak(@NotNull ASPParser.RcbrakContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#rcbrak}.
	 * @param ctx the parse tree
	 */
	void exitRcbrak(@NotNull ASPParser.RcbrakContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#skipStmt}.
	 * @param ctx the parse tree
	 */
	void enterSkipStmt(@NotNull ASPParser.SkipStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#skipStmt}.
	 * @param ctx the parse tree
	 */
	void exitSkipStmt(@NotNull ASPParser.SkipStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#assignStmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(@NotNull ASPParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#assignStmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(@NotNull ASPParser.AssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#elseStmt}.
	 * @param ctx the parse tree
	 */
	void enterElseStmt(@NotNull ASPParser.ElseStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#elseStmt}.
	 * @param ctx the parse tree
	 */
	void exitElseStmt(@NotNull ASPParser.ElseStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull ASPParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull ASPParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#objDec}.
	 * @param ctx the parse tree
	 */
	void enterObjDec(@NotNull ASPParser.ObjDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#objDec}.
	 * @param ctx the parse tree
	 */
	void exitObjDec(@NotNull ASPParser.ObjDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpression(@NotNull ASPParser.ArithmeticExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpression(@NotNull ASPParser.ArithmeticExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(@NotNull ASPParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(@NotNull ASPParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(@NotNull ASPParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(@NotNull ASPParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#boolVal}.
	 * @param ctx the parse tree
	 */
	void enterBoolVal(@NotNull ASPParser.BoolValContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#boolVal}.
	 * @param ctx the parse tree
	 */
	void exitBoolVal(@NotNull ASPParser.BoolValContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#parDef}.
	 * @param ctx the parse tree
	 */
	void enterParDef(@NotNull ASPParser.ParDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#parDef}.
	 * @param ctx the parse tree
	 */
	void exitParDef(@NotNull ASPParser.ParDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#boolType}.
	 * @param ctx the parse tree
	 */
	void enterBoolType(@NotNull ASPParser.BoolTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#boolType}.
	 * @param ctx the parse tree
	 */
	void exitBoolType(@NotNull ASPParser.BoolTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#parAritExp}.
	 * @param ctx the parse tree
	 */
	void enterParAritExp(@NotNull ASPParser.ParAritExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#parAritExp}.
	 * @param ctx the parse tree
	 */
	void exitParAritExp(@NotNull ASPParser.ParAritExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#stmtblock}.
	 * @param ctx the parse tree
	 */
	void enterStmtblock(@NotNull ASPParser.StmtblockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#stmtblock}.
	 * @param ctx the parse tree
	 */
	void exitStmtblock(@NotNull ASPParser.StmtblockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#methodSignature}.
	 * @param ctx the parse tree
	 */
	void enterMethodSignature(@NotNull ASPParser.MethodSignatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#methodSignature}.
	 * @param ctx the parse tree
	 */
	void exitMethodSignature(@NotNull ASPParser.MethodSignatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#parBoolExp}.
	 * @param ctx the parse tree
	 */
	void enterParBoolExp(@NotNull ASPParser.ParBoolExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#parBoolExp}.
	 * @param ctx the parse tree
	 */
	void exitParBoolExp(@NotNull ASPParser.ParBoolExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#objType}.
	 * @param ctx the parse tree
	 */
	void enterObjType(@NotNull ASPParser.ObjTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#objType}.
	 * @param ctx the parse tree
	 */
	void exitObjType(@NotNull ASPParser.ObjTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull ASPParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull ASPParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#lcbrak}.
	 * @param ctx the parse tree
	 */
	void enterLcbrak(@NotNull ASPParser.LcbrakContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#lcbrak}.
	 * @param ctx the parse tree
	 */
	void exitLcbrak(@NotNull ASPParser.LcbrakContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(@NotNull ASPParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(@NotNull ASPParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#boolDec}.
	 * @param ctx the parse tree
	 */
	void enterBoolDec(@NotNull ASPParser.BoolDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#boolDec}.
	 * @param ctx the parse tree
	 */
	void exitBoolDec(@NotNull ASPParser.BoolDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#intDec}.
	 * @param ctx the parse tree
	 */
	void enterIntDec(@NotNull ASPParser.IntDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#intDec}.
	 * @param ctx the parse tree
	 */
	void exitIntDec(@NotNull ASPParser.IntDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull ASPParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull ASPParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#classSignature}.
	 * @param ctx the parse tree
	 */
	void enterClassSignature(@NotNull ASPParser.ClassSignatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#classSignature}.
	 * @param ctx the parse tree
	 */
	void exitClassSignature(@NotNull ASPParser.ClassSignatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#newExp}.
	 * @param ctx the parse tree
	 */
	void enterNewExp(@NotNull ASPParser.NewExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#newExp}.
	 * @param ctx the parse tree
	 */
	void exitNewExp(@NotNull ASPParser.NewExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#intType}.
	 * @param ctx the parse tree
	 */
	void enterIntType(@NotNull ASPParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#intType}.
	 * @param ctx the parse tree
	 */
	void exitIntType(@NotNull ASPParser.IntTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#variableExp}.
	 * @param ctx the parse tree
	 */
	void enterVariableExp(@NotNull ASPParser.VariableExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#variableExp}.
	 * @param ctx the parse tree
	 */
	void exitVariableExp(@NotNull ASPParser.VariableExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#methodDef}.
	 * @param ctx the parse tree
	 */
	void enterMethodDef(@NotNull ASPParser.MethodDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#methodDef}.
	 * @param ctx the parse tree
	 */
	void exitMethodDef(@NotNull ASPParser.MethodDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(@NotNull ASPParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(@NotNull ASPParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanExpression(@NotNull ASPParser.BooleanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanExpression(@NotNull ASPParser.BooleanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(@NotNull ASPParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(@NotNull ASPParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#expressionSideEffect}.
	 * @param ctx the parse tree
	 */
	void enterExpressionSideEffect(@NotNull ASPParser.ExpressionSideEffectContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#expressionSideEffect}.
	 * @param ctx the parse tree
	 */
	void exitExpressionSideEffect(@NotNull ASPParser.ExpressionSideEffectContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#classDec}.
	 * @param ctx the parse tree
	 */
	void enterClassDec(@NotNull ASPParser.ClassDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#classDec}.
	 * @param ctx the parse tree
	 */
	void exitClassDec(@NotNull ASPParser.ClassDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#secondPair}.
	 * @param ctx the parse tree
	 */
	void enterSecondPair(@NotNull ASPParser.SecondPairContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#secondPair}.
	 * @param ctx the parse tree
	 */
	void exitSecondPair(@NotNull ASPParser.SecondPairContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(@NotNull ASPParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(@NotNull ASPParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull ASPParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull ASPParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#varDec}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(@NotNull ASPParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#varDec}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(@NotNull ASPParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#newActExp}.
	 * @param ctx the parse tree
	 */
	void enterNewActExp(@NotNull ASPParser.NewActExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#newActExp}.
	 * @param ctx the parse tree
	 */
	void exitNewActExp(@NotNull ASPParser.NewActExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(@NotNull ASPParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(@NotNull ASPParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#boolExpOneOp}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpOneOp(@NotNull ASPParser.BoolExpOneOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#boolExpOneOp}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpOneOp(@NotNull ASPParser.BoolExpOneOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ASPParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(@NotNull ASPParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link ASPParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(@NotNull ASPParser.MethodCallContext ctx);
}