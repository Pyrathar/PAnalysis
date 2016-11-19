package datastructure.Basic;

import datastructure.Enum.BinaryOp;
import datastructure.Operator.BinaryOperator;

public class Condition extends Expression {
		
	public Condition() {
		super();
	}
	
	public static Condition convertTextToASTElement(String text){
		if(text.contains("opr")) {
			String[] sta = text.split("expr2 ");
			String[] expr = sta[1].split(" ");
			if(expr.length == 6){
				Variable vari = null;
				if(expr[0].equals("identifier")){
					vari = new Variable(expr[1]);
				}
				if(expr[2].equals("opr")){
					if(expr[3].equals("<")){
						Constant cons = new Constant(Integer.parseInt(sta[2].split(" ")[1]));
						return new BinaryOperator(vari,cons,BinaryOp.LT);
					}else if(expr[3].equals(">")){
						Constant cons = new Constant(Integer.parseInt(sta[2].split(" ")[1]));
						return new BinaryOperator(vari,cons,BinaryOp.GT);
					}
				}
			}
		}else {
			String[] sta = text.split(" expr1 expr2 ");
			if(sta.length >= 1) {
				String left[] = sta[1].split(" ");
				Expression exp = Expression.convertTextToASTElement(sta[1]);
				Expression exp1 = Expression.convertTextToASTElement(sta[2]);
				if(sta[1].split(" ")[2].equals("+")) {
					return new BinaryOperator(exp,exp1,BinaryOp.ADD);
				}
			}
		}
		return null;
	}
}
