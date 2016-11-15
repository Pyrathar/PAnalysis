package datastructure.Basic;

public class Constant extends Expression {
	
	private int value;
	
	public Constant(int value) {
		this.value = value;
	}


	public String toString() {
		return value + "";
	}
	
	
	public String getLabel() {
		return value + "";
	}

}
