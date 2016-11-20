package datastructure.Enum;

public enum Type{
	
	INT ("int"),
	VOID ("void");
	
	private String type;
	
	Type(String type){
		this.type = type;
	}
 	
	public String toString() {
		return type;
	}
}
