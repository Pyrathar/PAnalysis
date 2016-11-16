package datastructure.Statement.Condition;

import datastructure.Basic.Expression;
import datastructure.Statement.Statement;

public class IFElseCondi extends Condition {
	
	private Statement ifState;
	
	private Statement elseState;

	public IFElseCondi(Expression condition,Statement ifState,Statement elseState) {
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
		String[] t = text.split("(if |then|else|fi)");
		//TODO Analysis boolean data
//		ifElse = new IFElseCondi(Bool.convertTextToASTElement(t[1]), 
//				Statement.convertTextToASTElement(t[2]), 
//				Statement.convertTextToASTElement(t[3]));
		
		return ifElse;
	}
	
//	public ASTLeaf toAST(){
//
//		ASTLeaf ast = new ASTLeaf(this);
//		ast.addChildren(this.getCondition().toAST());
//
//		ast.addChildren(ifStatement.toAST());
//		ast.addChildren(elseStatement.toAST());
//		
//		return ast;
//	}

}
