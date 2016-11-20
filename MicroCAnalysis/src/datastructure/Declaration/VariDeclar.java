package datastructure.Declaration;

import AbstractSyntax.ASTNode;
import datastructure.Basic.Variable;
import datastructure.Enum.Type;

public class VariDeclar extends Declaration {
	
	private Variable value;
	
	private Type type;
	
	public VariDeclar(Type type,Variable value) {
		this.type = type;
		this.value = value;
	}

	public Variable getValue() {
		return value;
	}

	public void setValue(Variable value) {
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public ASTNode toAST() {
		ASTNode ast = new ASTNode(this,"VariableDeclaration");
		ast.addChildren(new ASTNode(type.toString()));
		ast.addChildren(value.toAST());
		return ast;
	}
	public String toString() {
		return type.toString() + " " + value.toString();
	}

}
