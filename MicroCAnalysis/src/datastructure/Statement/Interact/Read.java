package datastructure.Statement.Interact;

import datastructure.Basic.Arithmetic;
import datastructure.Basic.ArrVariable;
import datastructure.Basic.Variable;
import datastructure.Statement.Statement;

public class Read extends Statement {

	private static String astType = "readStmt";
	
	public Read(){
	}
	
	public static Read convertTextToASTElement(String text){
		Read read = null;
		String[] t = text.split(" ");
		if(t[3].equals("[")){
			String index = text.substring(text.indexOf("[", 1)+2,text.indexOf("]")-1);
			read = new ReadArray (new ArrVariable(t[2], 
					Arithmetic.convertTextToASTElement(index)));
		}else{
			read = new ReadVariable(new Variable(t[2]));
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
