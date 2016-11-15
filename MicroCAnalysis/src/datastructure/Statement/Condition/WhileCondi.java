package datastructure.Statement.Condition;

import datastructure.Statement.Statement;

public class WhileCondi extends Condition {
	
	
	private Statement whileState;
	
	public WhileCondi(boolean condition,Statement whileState) {
		super(condition);
		this.whileState = whileState;
	}
	
	public Statement getWhileState() {
		return whileState;
	}

	public void setWhileState(Statement whileState) {
		this.whileState = whileState;
	}

	public static WhileCondi convertTextToASTElement(String text){
		WhileCondi ifElse = null;
		String[] t = text.split("(if |then|else|fi)");
		//TODO Analysis while data

		
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
