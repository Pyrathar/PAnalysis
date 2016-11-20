package datastructure.Assignment;

import AbstractSyntax.ASTNode;
import datastructure.Basic.Expression;
import datastructure.Basic.Variable;

public class VariAssignment extends Assignment {
	
	private Variable value;
	
	public VariAssignment(Variable value,Expression assignment) {
		super(assignment);
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	public Variable getValue() {
		return value;
	}

	public void setValue(Variable value) {
		this.value = value;
	}
	
	public String toString(){
		return value + " =" + this.getAssignment().toString();
	}

	public ASTNode toAST(){
		
		ASTNode ast = new ASTNode(this);

		ast.addChildren(new ASTNode(this.value));
		ast.addChildren(this.getAssignment().toAST());
		
		return ast;
	}
}
