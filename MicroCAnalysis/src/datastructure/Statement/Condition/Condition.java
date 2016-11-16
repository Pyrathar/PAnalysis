package datastructure.Statement.Condition;

import datastructure.Basic.Expression;
import datastructure.Statement.Statement;

public class Condition extends Statement {
	
	private Expression condition;
	
	public Condition(Expression condition) {
		this.condition = condition;
	}

	public Expression isCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}
}
