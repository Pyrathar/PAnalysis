package datastructure.Enum;

public enum BinaryOp {

	GT 		(">"), 
	GE		(">="),
	LT		("<"),
	LE		("<="),
	EQ		("="),
	NEQ		("!="),
	
	ADD		("+"), 
	MINUS   ("-"),
	MUL	    ("*"),
	DIV	    ("/"),
	AND     ("&"),
	OR      ("|");
	
	private String operator;
	
	BinaryOp(String operator){
		this.operator = operator;
	}
	
	public String getOperator(){
		return operator;
	}
	
	public String toString(){
		return operator;
	}
}
