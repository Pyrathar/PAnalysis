package datastructure.Declaration;

import datastructure.Basic.ArrVariable;
import datastructure.Basic.Constant;
import datastructure.Basic.Variable;

public class Declaration {
	
	private static String astType = "decl";
	
	public Declaration(){
		super();
	}
	
	public static Declaration convertTextToASTElement(String text){
		Declaration decl;
		if(text.indexOf('[') != -1){
			decl = new ArrayDeclar(new ArrVariable(text.substring(6, text.indexOf(';')-1).split(" ")[1],new Constant(Integer.parseInt(text.split(" ")[4]))));
		}else{
			decl = new VariDeclar(new Variable(text.substring(6, text.indexOf(';')-1).split(" ")[1]));
		}
		return decl;
	}
	
	public String toString() {
		return "int";
	}

}
