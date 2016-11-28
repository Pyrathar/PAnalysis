package datastructure.Statement.Condition;

import AbstractSyntax.ASTNode;
import datastructure.Basic.Condition;
import datastructure.Basic.Expression;
import datastructure.Statement.Sequence;
import datastructure.Statement.Statement;

public class IFElseCondi extends Control {
	
	private Sequence ifState;
	
	private Sequence elseState;

	
	public IFElseCondi(Condition condition) {
		super(condition);
	}

	public IFElseCondi(Condition condition,Sequence ifState,Sequence elseState) {
		super(condition);
		this.ifState = ifState;
		this.elseState = elseState;
	}

	public Sequence getIfState() {
		return ifState;
	}

	public void setIfState(Sequence ifState) {
		this.ifState = ifState;
	}

	public Sequence getElseState() {
		return elseState;
	}

	public void setElseState(Sequence elseState) {
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

		ast.addChildren(this.getIfState().toAST());
		ast.addChildren(this.getElseState().toAST());
		
		return ast;
	}

}
