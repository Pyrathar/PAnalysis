package datastructure.Statement.Condition;

import AbstractSyntax.ASTNode;
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
		//TODO Analysis while data
        whileCo = new WhileCondi((Condition)Expression.convertTextToASTElement(texts[1]),
        		Statement.convertTextToASTElement(texts[2]));
		
		return whileCo;
	}
	
	public ASTNode toAST(){

		ASTNode ast = new ASTNode(this);
		ast.addChildren(this.getCondi().toAST());

		ast.addChildren(whileState.toAST());
		//ast.addChildren(elseStatement.toAST());
		
		return ast;
	}

}
