package datastructure.Basic;

public class ArrVariable extends Expression {
	
	private String name;
	
	private Expression index;
	
	public ArrVariable(String name, Expression index) {
		this.name = name;
		this.index = index;
	}

	
	public Expression getIndex() {
		return index;
	}


	public void setIndex(Expression index) {
		this.index = index;
	}


	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
