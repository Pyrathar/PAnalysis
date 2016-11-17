package datastructure.Basic;

public class Condition extends Expression {
	
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
