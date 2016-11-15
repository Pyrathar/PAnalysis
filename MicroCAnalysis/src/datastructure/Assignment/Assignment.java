package datastructure.Assignment;

import datastructure.Basic.Expression;
import datastructure.Basic.ArrVariable;
import datastructure.Basic.Variable;
import datastructure.Statement.Statement;

public class Assignment extends Statement {
	
	private Expression assignment;
	
	private static String astType = "assignStmt";

	public Assignment(Expression assignment){
		super();
		this.assignment = assignment;
	}
	
	public static Assignment convertTextToASTElement(String text){
		Assignment assign = null;
		String[] t = text.split(" ");
		String assignment = text.substring(text.indexOf(":=", 1)+3,text.indexOf(";")-1);
		if(t[2].equals("[")){
			String index = text.substring(text.indexOf("[", 1)+2,text.indexOf("]")-1);
			assign = new ArrayAssignment(
					new ArrVariable(t[1], Expression.convertTextToASTElement(index)),
					Expression.convertTextToASTElement(assignment));
		}else{
			assign = new VariAssignment(new Variable(t[1]),
					Expression.convertTextToASTElement(assignment));
		}
		
		return assign;
	}

	public Expression getAssignment() {
		return assignment;
	}

	public void setAssignment(Expression assignment) {
		this.assignment = assignment;
	}
	
	public String getLabel(){
		//return " := " + assignment.getLabel();
		return "";
	}
	
	public String toString() {
		return ":=";
	}
	

}
