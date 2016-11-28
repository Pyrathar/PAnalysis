package datastructure.Declaration;

import AbstractSyntax.ASTNode;
import datastructure.Basic.ArrVariable;
import datastructure.Enum.Type;

public class ArrayDeclar extends Declaration {
	
	private ArrVariable name;

	private Type type;
	
	public ArrayDeclar(Type type,ArrVariable name){
		this.type = type;
		this.name = name;
	}
	
	public ArrVariable getName() {
		return name;
	}

	public void setName(ArrVariable name) {
		this.name = name;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String toString() {
		return type.toString() + " " + name.toString();
	}


	public ASTNode toAST(){
		
		ASTNode ast = new ASTNode(this,"ArrayDeclaration");
		ast.addChildren(new ASTNode(this.getType().toString()));
		//ast.addChildren(new ASTNode(name.getName()));
		ast.addChildren(this.getName().toAST());
		
		return ast;
	}
//	
//	public String getLabel() {
//		return "int " + name.getLabel();
//	}

}
