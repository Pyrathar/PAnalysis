package datastructure.Operator;

import datastructure.Basic.Condition;
import datastructure.Basic.Expression;
import datastructure.Enum.BinaryOp;

public class BinaryOperator extends Condition {
	
	private Expression leftValue;
	
	private Expression rightValue;
	
	private BinaryOp operator;
	
	public BinaryOperator(Expression leftValue,Expression rightValue,BinaryOp operator) {
		this.leftValue = leftValue;
		this.rightValue = rightValue;
		this.operator = operator;
	}

	public Expression getLeftValue() {
		return leftValue;
	}

	public void setLeftValue(Expression leftValue) {
		this.leftValue = leftValue;
	}

	public Expression getRightValue() {
		return rightValue;
	}

	public void setRightValue(Expression rightValue) {
		this.rightValue = rightValue;
	}

	public BinaryOp getOperator() {
		return operator;
	}

	public void setOperator(BinaryOp operator) {
		this.operator = operator;
	}
	
}
