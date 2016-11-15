package datastructure.Statement.Interact;

import datastructure.Basic.Expression;

public class Write {
	
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
//	
//	public ASTLeaf toAST(){
//		
//		ASTLeaf ast = new ASTLeaf(this);
//
//		ast.addChildren(a.toAST());
//		
//		return ast;
//	}
	
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
