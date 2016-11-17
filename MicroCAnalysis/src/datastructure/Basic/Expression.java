package datastructure.Basic;

import AbstractSyntax.ASTNode;
import datastructure.Enum.BinaryOp;
import datastructure.Operator.BinaryOperator;
import datastructure.Statement.Statement;

public class Expression extends Statement {
	
private static String astType = "aexpr";
	
	public Expression(){
		super();
	}
	
	public static Expression convertTextToASTElement(String text){
		text = text.replaceAll("\\(", "").replaceAll("\\)", "");
		String[] expressions = text.split(astType);
		int i = 0;
		for (String s : expressions) {
			String[] a = s.trim().split(" ");
			if(a.length == 2 || a.length == 3 && a[2].equals("]")){
				if(a[1].equals("-")){
					//return new Negate(Arithmetic.convertTextToASTElement(astType+expressions[i+1]));
				}else if(a[1].matches("^-?\\d+$")){
					return new Constant(Integer.parseInt(a[1]));
				}else if(a[1].matches("[A-Za-z]")){
					return new Variable(a[1]);
				}
			}else if(a.length == 3){
				if(a[2].equals("[")){
					return new ArrVariable(
							a[1],
							Expression.convertTextToASTElement(text.split(astType,i+2)[i+1]));
				}else if(a[2].matches(".*[+-/*//].*")){
					String[] b = text.split("[+-/*//]",2);
					if(a[2].equals("+")){
						return new BinaryOperator(
								Expression.convertTextToASTElement(b[0]), 
								Expression.convertTextToASTElement(b[1]), 
								BinaryOp.ADD);	
					}else if(a[2].equals("-")){
						return new BinaryOperator(
								Expression.convertTextToASTElement(b[0]), 
								Expression.convertTextToASTElement(b[1]), 
								BinaryOp.MINUS);	
					}else if(a[2].equals("*")){
						return new BinaryOperator(
								Expression.convertTextToASTElement(b[0]), 
								Expression.convertTextToASTElement(b[1]), 
								BinaryOp.MUL);	
					}else if(a[2].equals("/")){
						return new BinaryOperator(
								Expression.convertTextToASTElement(b[0]), 
								Expression.convertTextToASTElement(b[1]), 
								BinaryOp.DIV);	
					}
				}
			}
			i++;
		}
		return null;
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
}
