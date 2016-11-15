package datastructure.Assignment;

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

}
