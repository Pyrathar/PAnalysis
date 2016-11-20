package datastructure.Statement.Interact;

import AbstractSyntax.ASTNode;
import datastructure.Basic.ArrVariable;

public class ReadArray extends Read {
	
	private ArrVariable value;
	
	public ReadArray(ArrVariable value) {
		this.value = value;
	}

	public ArrVariable getValue() {
		return value;
	}

	public void setValue(ArrVariable value) {
		this.value = value;
	}
	
	public String toString() {
		return "read "+value.toString();
	}
	

	public ASTNode toAST(){
		
		ASTNode ast = new ASTNode(this,"");

		ast.addChildren(value.toAST());
		
		return ast;
	}
//	
//
//	public String getLabel(){
//		return super.getLabel() + arr.getLabel();
//	}
}
