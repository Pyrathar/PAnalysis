package datastructure.Statement.Interact;

import AbstractSyntax.ASTNode;
import datastructure.Basic.Variable;

public class ReadVariable extends Read{
	
	private Variable value;
	
	public ReadVariable(Variable value) {
		this.value = value;
	}

	public Variable getValue() {
		return value;
	}

	public void setValue(Variable value) {
		this.value = value;
	}
	
	public String toString() {
		return "read " + value.toString();
	}
	
	public ASTNode toAST(){
		
		ASTNode ast = new ASTNode(this);

		ast.addChildren(new ASTNode(this.value));
		
		return ast;
	}

}
