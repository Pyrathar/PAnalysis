package datastructure.Statement.Interact;

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
	

//	public ASTLeaf toAST(){
//		
//		ASTLeaf ast = new ASTLeaf(this);
//
//		ast.addChildren(arr.toAST());
//		
//		return ast;
//	}
//	
//
//	public String getLabel(){
//		return super.getLabel() + arr.getLabel();
//	}
}
