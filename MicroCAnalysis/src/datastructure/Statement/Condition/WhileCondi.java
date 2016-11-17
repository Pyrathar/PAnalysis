package datastructure.Statement.Condition;

import datastructure.Basic.Condition;
import datastructure.Basic.Expression;
import datastructure.Statement.Statement;

public class WhileCondi extends Control {
	
	private Statement whileState;
	
	public WhileCondi(Condition condition,Statement whileState) {
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
		WhileCondi whileCo = null;
		String[] texts = text.split("(while |\\{|\\})");
		String[] t = text.split("(if |then|else|fi)");
		//TODO Analysis while data
        whileCo = new WhileCondi((Condition)Condition.convertTextToASTElement(texts[1]),
        		Statement.convertTextToASTElement(texts[2]));
		
		return whileCo;
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
