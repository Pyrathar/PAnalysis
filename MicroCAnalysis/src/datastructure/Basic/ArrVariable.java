package datastructure.Basic;

public class ArrVariable extends Arithmetic {
	
	private String name;
	
	private Arithmetic index;
	
	public ArrVariable(String name, Arithmetic index) {
		this.name = name;
		this.index = index;
	}

	
	public Arithmetic getIndex() {
		return index;
	}


	public void setIndex(Arithmetic index) {
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
