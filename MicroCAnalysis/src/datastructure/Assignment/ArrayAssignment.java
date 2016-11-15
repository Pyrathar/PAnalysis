package datastructure.Assignment;

import datastructure.Basic.Arithmetic;
import datastructure.Basic.ArrVariable;

public class ArrayAssignment extends Assignment {

	private ArrVariable value;

	public ArrayAssignment(ArrVariable value,Arithmetic assignment) {
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
