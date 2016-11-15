package datastructure.Statement.Condition;

import datastructure.Statement.Statement;

public class Condition extends Statement {
	
	private boolean condition;
	
	public Condition(boolean condition) {
		this.condition = condition;
	}

	public boolean isCondition() {
		return condition;
	}

	public void setCondition(boolean condition) {
		this.condition = condition;
	}
}
