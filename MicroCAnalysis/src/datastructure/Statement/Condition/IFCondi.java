package datastructure.Statement.Condition;

import datastructure.Basic.Condition;
import datastructure.Statement.Statement;

public class IFCondi extends Control{
	
	private Statement state;

	public IFCondi(Condition condition, Statement state) {
		super(condition);
		this.state = state;
		// TODO Auto-generated constructor stub
	}
	
	public static IFCondi convertTextToASTElement(String text){
		IFCondi ifElse = null;
		String[] t = text.split("(if |then|else|fi)");
		//TODO
//		ifElse = new IFCondi(Boolean.convertTextToASTElement(t[1]), 
//				Statement.convertTextToASTElement(t[2]));
		
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
