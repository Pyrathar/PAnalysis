package datastructure.Operator;

import datastructure.Basic.Expression;
import datastructure.Enum.UnaryOp;

public class UnaryOperator extends Expression {
	
	private Expression rightValue;
	
	private UnaryOp operator;
	
	public UnaryOperator(Expression rightValue,UnaryOp operator) {
		this.rightValue = rightValue;
		this.operator = operator;
	}

	public Expression getRightValue() {
		return rightValue;
	}

	public void setRightValue(Expression rightValue) {
		this.rightValue = rightValue;
	}

	public UnaryOp getOperator() {
		return operator;
	}

	public void setOperator(UnaryOp operator) {
		this.operator = operator;
	}

}
