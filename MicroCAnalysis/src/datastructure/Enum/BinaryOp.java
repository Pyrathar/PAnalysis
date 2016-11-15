package datastructure.Enum;

public enum BinaryOp {

	L 		(">"), 
	LEQ		(">="),
	S		("<"),
	SEQ		("<="),
	EQ		("="),
	NEQ		("!="),
	
	ADD		("+"), 
	SUB		("-"),
	MULIP	("*"),
	DIVI	("/"),
	AND     ("&"),
	OR      ("|");
	
	private String operator;
	
	BinaryOp(String operator){
	this.operator = operator;
	}
	
	public String getOperator(){
		return operator;
	}
}
