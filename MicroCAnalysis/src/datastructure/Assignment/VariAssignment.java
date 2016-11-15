package datastructure.Assignment;

import datastructure.Basic.Arithmetic;
import datastructure.Basic.Variable;

public class VariAssignment extends Assignment {
	
	private Variable value;
	
	public VariAssignment(Variable value,Arithmetic assignment) {
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

}
