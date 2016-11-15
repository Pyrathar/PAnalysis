package datastructure.Assignment;

import datastructure.Basic.Expression;
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
	
}
