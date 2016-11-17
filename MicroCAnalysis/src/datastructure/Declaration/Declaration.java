package datastructure.Declaration;

import AbstractSyntax.ASTElement;
import datastructure.Basic.ArrVariable;
import datastructure.Basic.Constant;
import datastructure.Basic.Expression;
import datastructure.Basic.Variable;
import datastructure.Enum.Type;

public class Declaration extends ASTElement {
	
	private static String astType = "decl";
	
	public Declaration(){
		super();
	}
	
	public static Declaration convertTextToASTElement(String text){
		Declaration decl = null;
		String[] values = text.split(" ");
		String typeTex = values[2].replaceAll("\\)", "");
		Type type;
		if(typeTex.equals("int")){
			type = Type.INT;
		}else{
			type = Type.VOID;
		}
		String value= null;
		if(values[3].contains("identifier"))
		{
			value = values[4].substring(0, values[4].length()-1);
		}
		if(text.indexOf('[') != -1){
			String index = values[7].replaceAll("\\)", "");
			decl = new ArrayDeclar(new ArrVariable(value,new Constant(Integer.parseInt(index))));
		}else{
			decl = new VariDeclar(type,new Variable(value));
		}
		return decl;
	}
	
	public String toString() {
		return "int";
	}

}
