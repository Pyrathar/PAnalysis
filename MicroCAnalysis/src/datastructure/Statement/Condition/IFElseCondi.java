package datastructure.Statement.Condition;

import AbstractSyntax.ASTNode;
import datastructure.Basic.Condition;
import datastructure.Basic.Expression;
import datastructure.Statement.Sequence;
import datastructure.Statement.Statement;

public class IFElseCondi extends Control {
	
	private Statement ifState;
	
	private Statement elseState;

	
	public IFElseCondi(Condition condition) {
		super(condition);
	}

	public IFElseCondi(Condition condition,Statement ifState,Statement elseState) {
		super(condition);
		this.ifState = ifState;
		this.elseState = elseState;
	}

	public Statement getIfState() {
		return ifState;
	}

	public void setIfState(Statement ifState) {
		this.ifState = ifState;
	}

	public Statement getElseState() {
		return elseState;
	}

	public void setElseState(Statement elseState) {
		this.elseState = elseState;
	}
	
	public static IFElseCondi convertTextToASTElement(String text){
		IFElseCondi ifElse = null;
		String newTex = text.replaceAll("\\(","").replaceAll("\\)", "");
		String[] partcondition = newTex.split("(if |\\{|\\} |else | \\{\\})");

		String condition = partcondition[1].replaceAll("\\(", "").replaceAll("\\)", "");

		ifElse = new IFElseCondi((Condition) Expression.convertTextToASTElement(condition),
				Sequence.convertTextToASTElement(partcondition[2]),
				Sequence.convertTextToASTElement(partcondition[5]));
		return ifElse;
	}
	
	
	
	public ASTNode toAST(){

		ASTNode ast = new ASTNode(this,"IfElseCondi");
		ast.addChildren(this.getCondi().toAST());

		ast.addChildren(this.ifState.toAST());
		ast.addChildren(this.elseState.toAST());
		
		return ast;
	}

}
