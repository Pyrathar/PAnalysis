package datastructure.Operator;

import datastructure.Basic.Arithmetic;
import datastructure.Enum.BinaryOp;

public class BinaryOperator extends Arithmetic {
	
	private Arithmetic leftValue;
	
	private Arithmetic rightValue;
	
	private BinaryOp operator;
	
	public BinaryOperator(Arithmetic leftValue,Arithmetic rightValue,BinaryOp operator) {
		this.leftValue = leftValue;
		this.rightValue = rightValue;
		this.operator = operator;
	}

	public Arithmetic getLeftValue() {
		return leftValue;
	}

	public void setLeftValue(Arithmetic leftValue) {
		this.leftValue = leftValue;
	}

	public Arithmetic getRightValue() {
		return rightValue;
	}

	public void setRightValue(Arithmetic rightValue) {
		this.rightValue = rightValue;
	}

	public BinaryOp getOperator() {
		return operator;
	}

	public void setOperator(BinaryOp operator) {
		this.operator = operator;
	}
	
}
