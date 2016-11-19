package datastructure.Statement.Condition;

import AbstractSyntax.ASTNode;
import datastructure.Basic.Condition;
import datastructure.Basic.Expression;
import datastructure.Statement.Sequence;
import datastructure.Statement.Statement;

public class WhileCondi extends Control {
	
	private Sequence whileState;
	
	public WhileCondi(Condition condition,Sequence whileState) {
		super(condition);
		this.whileState = whileState;
	}
	
	public Statement getWhileState() {
		return whileState;
	}

	public void setWhileState(Sequence whileState) {
		this.whileState = whileState;
	}

	public static WhileCondi convertTextToASTElement(String text){
		WhileCondi whileCo = null;
		String[] texts = text.split("(while |\\{|\\})");
		//TODO Analysis while data
        whileCo = new WhileCondi((Condition)Expression.convertTextToASTElement(texts[1]),
        		Sequence.convertTextToASTElement(texts[2]));
		
		return whileCo;
	}
	
	public ASTNode toAST(){

		ASTNode ast = new ASTNode(this);
		ast.addChildren(this.getCondi().toAST());

		for(int i=0;i<whileState.getStatementList().size();i++){
			ast.addChildren(whileState.getStatementList().get(i).toAST());
		}
		return ast;
	}

}
