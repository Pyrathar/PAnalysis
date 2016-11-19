package datastructure.Statement.Interact;

import datastructure.Basic.ArrVariable;
import datastructure.Basic.Expression;
import datastructure.Basic.Variable;
import datastructure.Statement.Statement;

public class Read extends Statement {

	private static String astType = "readStmt";
	
	public Read(){
	}
	
	public static Read convertTextToASTElement(String text){
		Read read = null;
		String[] t = text.split("read | ;");
		String index[] = t[1].replaceAll("\\)", "").split(" ");
		if(t[1].contains("[")){
			read = new ReadArray (new ArrVariable(index[1], 
					Expression.convertTextToASTElement(t[1])));
		}else{
			read = new ReadVariable(new Variable(index[1]));
		}
		
		return read;
	}
	
	public String toString() {
		return "read";
	}
	

	public String getLabel(){
		return "read ";
	}
}
