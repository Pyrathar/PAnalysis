package datastructure.Declaration;

import datastructure.Basic.ArrVariable;

public class ArrayDeclar extends Declaration {
	
	private ArrVariable name;

	public ArrayDeclar(ArrVariable name){
		this.name = name;
	}
	
	public ArrVariable getName() {
		return name;
	}



	public void setName(ArrVariable name) {
		this.name = name;
	}

//
//	public ASTLeaf toAST(){
//		
//		ASTLeaf ast = new ASTLeaf(this);
//		ast.addChildren(name.toAST());
//		
//		return ast;
//	}
//	
//	public String getLabel() {
//		return "int " + name.getLabel();
//	}

}