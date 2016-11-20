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
		String[] ts = text.replaceAll("\\(", "").replaceAll("\\)", "").split("=");
		
		Variable vari = null;
		String[] list = ts[0].split(" ");
		if(list[1].equals("identifier")) {
			vari = new Variable(list[2]);
		}
		
		if(text.lastIndexOf("[")!= -1){
			String[] val = text.split("\\[ | \\]");
			assign = new ArrayAssignment(
					new ArrVariable(list[2], Expression.convertTextToASTElement(val[1])),
					Expression.convertTextToASTElement(ts[1]));
		}else{
			assign = new VariAssignment(vari,
					Expression.convertTextToASTElement(ts[1]));
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
