package datastructure.Basic;

import AbstractSyntax.ASTNode;
import datastructure.Statement.Statement;

public class Expression extends Statement {
	
private static String astType = "aexpr";
	
	public Expression(){
		super();
	}
	
	public static Expression convertTextToASTElement(String text){
		text = text.replaceAll("\\(", "").replaceAll("\\)", "");
		String[] expressions = text.split(astType);
		if(expressions.length == 1) {
			String[] val = text.split(" ");
			if(val[0].equals("identifier")){
				return new Variable(val[1]);
			}else {
				return new Constant(Integer.parseInt(val[1]));
			}
		}else if(expressions.length == 2){
			//read or write statement
			String[] sple = expressions[1].split(" ");
			if(sple[3].equals("identifier")){
				return new Variable(sple[4]);
			}else {
				return new Constant(Integer.parseInt(sple[4]));
			}
		}
		for (String s : expressions) {
			String[] a = s.trim().split(" ");
			if(a.length > 3){
				return Condition.convertTextToASTElement(removeFirstEle(expressions));
			}
			if(a.length == 2 || a.length == 3 && a[2].equals("]")){
				if(a[1].equals("-")){
					//return new Negate(Arithmetic.convertTextToASTElement(astType+expressions[i+1]));
				}else if(a[1].matches("^-?\\d+$")){
					return new Constant(Integer.parseInt(a[1]));
				}else if(a[1].matches("[A-Za-z]")){
					return new Variable(a[1]);
				}
			}
		}
		return null;
	}
	
	public static String removeFirstEle(String[] array) {
		String result = "";
		if(array.length > 1){
			for(int i=1;i<array.length;i++){
				result += array[i];
			}
		}
		return result;
	}
	
	public ASTNode toAST(){
		
		ASTNode ast = new ASTNode(this);
		
		if(this.getClass().toString().matches(".*BinaryOperator")){
			//ast.addChildren(((BinaryOperator) this).getFirstValue().toAST());
			//ast.addChildren(((BinaryOperator) this).getSecondValue().toAST());
		}else if(this.getClass().toString().matches(".*Negate")){
			//ast.addChildren(((Negate) this).getValue().toAST());
		}else if(this.getClass().toString().matches(".*ArrVariable")){
			ast.addChildren(((ArrVariable) this).getIndex().toAST());
		}
		
		return ast;
	}
	
	public String toString() {
		return "";
	}
}
