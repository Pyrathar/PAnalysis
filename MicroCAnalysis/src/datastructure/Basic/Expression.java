package datastructure.Basic;

import AbstractSyntax.ASTNode;
import datastructure.Operator.BinaryOperator;
import datastructure.Statement.Statement;

public class Expression extends Statement {
	
private static String astType = "aexpr";
	
	public Expression(){
		super();
	}
	
	public static Expression convertTextToASTElement(String text){
		text = text.replaceAll("\\(", "").replaceAll("\\)", "");
		if(text.contains("[")) {
			String[] expressions= text.split("\\[ | \\]");
			if(expressions.length != 0) {
				String name = expressions[0].replaceAll(" ", "").split("identifier")[1];
				return new ArrVariable(name,Expression.convertTextToASTElement(expressions[1]));
			}
		}else {
			String[] expressions = text.split(astType);
			if(expressions.length == 1) {
				String[] val = text.split(" ");
				if(val[0].equals("identifier")){
					return new Variable(val[1]);
				}else {
					return new Constant(Integer.parseInt(val[1]));
				}
			}else if(expressions.length == 2){
				//read or write statement or array defi
				if(expressions[1].contains("identifier") || expressions[1].contains("integer")) {
					String[] sple = expressions[1].split(" ");
					if(sple[3].equals("identifier")){
						return new Variable(sple[4]);
					}else {
						return new Constant(Integer.parseInt(sple[4]));
					}
				}
			}
			for (String s : expressions) {
				String[] a = s.trim().split(" ");
				if(a.length > 3){
					return Condition.convertTextToASTElement(removeFirstEle(expressions));
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
		
		ASTNode ast = new ASTNode(this,"");
		System.out.println(this.getClass().toString());
		if(this.getClass().toString().matches(".*BinaryOperator")){
			ast.setName("BinaryOperator");
			ast.addChildren(((BinaryOperator) this).getLeftValue().toAST());
			ast.addChildren((new ASTNode(((BinaryOperator) this).getOperator().toString())));
			ast.addChildren(((BinaryOperator) this).getRightValue().toAST());
		}else if(this.getClass().toString().matches(".*Negate")){
			//ast.addChildren(((Negate) this).getValue().toAST());
		}else if(this.getClass().toString().matches(".*ArrVariable")){
			String name =((ArrVariable)this).getName();
			ast.setName(name);
			ast.addChildren(((ArrVariable) this).getIndex().toAST());
		}else if(this.getClass().toString().matches(".*Variable")) {
			ast.setName(((Variable) this).getName());
		}else if(this.getClass().toString().matches(".*WhileCondi")) {
			ast.setName("While");
			ast.addChildren(((Condition)this).toAST());
		}else if(this.getClass().toString().matches(".*Constant")) {
			ast.setName(((Constant)this).getValue()+"");
		}else if(this.getClass().toString().matches(".*Read")) {
			
		}
		
		return ast;
	}
	
	public String toString() {
		return "";
	}
}
