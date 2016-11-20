package datastructure.Assignment;

import datastructure.Basic.Expression;
import datastructure.Enum.BinaryOp;
import AbstractSyntax.ASTNode;
import datastructure.Basic.ArrVariable;

public class ArrayAssignment extends Assignment {

	private ArrVariable value;

	public ArrayAssignment(ArrVariable value,Expression assignment) {
		super(assignment);
		this.value = value;
	}

	public ArrVariable getValue() {
		return value;
	}

	public void setValue(ArrVariable value) {
		this.value = value;
	}
	
	public ASTNode toAST(){
		
		ASTNode ast = new ASTNode(this,"ArrayAssignment");

		ast.addChildren(this.value.toAST());
		ast.addChildren(new ASTNode(BinaryOp.EQ.toString()));
		ast.addChildren(this.getAssignment().toAST());
		
		return ast;
	}
	
	public String toString() {
		return value.toString() + " = " + this.getAssignment().toString();
	}
	
}
