package datastructure.Enum;

public enum UnaryOp {
	
	MINUS     ("-"),
	NOTEUQAL     ("!");
	
	private String operator;
	
	UnaryOp(String operator){
	this.operator = operator;
	}
	
	public String getOperator(){
		return operator;
	}

}
