package datastructure.Statement.Condition;

import datastructure.Basic.Condition;
import datastructure.Statement.Statement;

public class Control extends Statement {
	
	private Condition condi;
	
	public Control(Condition condi) {
		this.condi = condi;
	}

	public Condition getCondi() {
		return condi;
	}

	public void setCondi(Condition condi) {
		this.condi = condi;
	}
	
}
