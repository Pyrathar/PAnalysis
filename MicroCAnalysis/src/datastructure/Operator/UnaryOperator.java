package datastructure.Operator;

import datastructure.Basic.Arithmetic;
import datastructure.Enum.UnaryOp;

public class UnaryOperator extends Arithmetic {
	
	private Arithmetic rightValue;
	
	private UnaryOp operator;
	
	public UnaryOperator(Arithmetic rightValue,UnaryOp operator) {
		this.rightValue = rightValue;
		this.operator = operator;
	}

	public Arithmetic getRightValue() {
		return rightValue;
	}

	public void setRightValue(Arithmetic rightValue) {
		this.rightValue = rightValue;
	}

	public UnaryOp getOperator() {
		return operator;
	}

	public void setOperator(UnaryOp operator) {
		this.operator = operator;
	}

}
