package datastructure.Statement.Interact;

import AbstractSyntax.ASTNode;
import datastructure.Basic.Expression;
import datastructure.Statement.Statement;

public class Write extends Statement{
	
	private Expression value;
	private static String astType = "writeStmt";
	
	public Write(Expression value){
		this.value= value;
	}
	
	public static Write convertTextToASTElement(String text){
		Write w = null;
		String[] t = text.split("(write |;)");
		
		w = new Write(Expression.convertTextToASTElement(t[1]));
		
		return w;
	}
	
	public ASTNode toAST(){
		
		ASTNode ast = new ASTNode(this,"write");

		ast.addChildren(this.getValue().toAST());
		
		return ast;
	}
	
	public String toString() {
		return "write";
	}

	public Expression getValue() {
		return value;
	}

	public void setValue(Expression value) {
		this.value = value;
	}
	
//	public String getLabel() {
//		return "write " + value.getLabel();
//	}

}
