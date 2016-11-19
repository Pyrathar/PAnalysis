package datastructure.Basic;

import datastructure.Enum.BinaryOp;
import datastructure.Operator.BinaryOperator;

public class Condition extends Expression {
		
	public Condition() {
		super();
	}
	
	public static Condition convertTextToASTElement(String text){
		if(text.contains("opr")) {
			String[] sta = text.split("expr1 expr2 | opr");
			if(sta.length >= 5) {
				String[] exprs = sta[1].split(" ");
				if(exprs.length == 3) {
					BinaryOp op=null;
					if(exprs[2].equals("+")) {
						op = BinaryOp.ADD;
					}
					Expression exps1 = new BinaryOperator(Expression.convertTextToASTElement(sta[1]),
							Expression.convertTextToASTElement(sta[2]),op);
					if(sta[3].replaceAll(" ", "").equals("<")) {
						return new BinaryOperator(exps1,Expression.convertTextToASTElement(sta[4]),BinaryOp.LT);
					}
				}
			}else {
				Expression exp = Expression.convertTextToASTElement(sta[1]);
				Expression exp1 = Expression.convertTextToASTElement(sta[3]);
				if(sta[2].replaceAll(" ", "").equals("<")) {
					return new BinaryOperator(exp,exp1,BinaryOp.LT);
				}
			}
			
//			
//			if(expr.length == 6){
//				Variable vari = null;
//				if(expr[0].equals("identifier")){
//					vari = new Variable(expr[1]);
//				}
//				if(expr[2].equals("opr")){
//					if(expr[3].equals("<")){
//						Constant cons = new Constant(Integer.parseInt(sta[2].split(" ")[1]));
//						return new BinaryOperator(vari,cons,BinaryOp.LT);
//					}else if(expr[3].equals(">")){
//						Constant cons = new Constant(Integer.parseInt(sta[2].split(" ")[1]));
//						return new BinaryOperator(vari,cons,BinaryOp.GT);
//					}
//				}
//			}
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
	
	public static String[] combineArray(String[] arrays) {
		String result = "";
		for(int i=0;i<arrays.length;i++) {
			result += arrays[i];
		}
		
		String[] sizes = result.split(" ");
		String[] news = new String[sizes.length/2];
		for(int j=0;j<sizes.length/2;j+=2){
			news[j] = sizes[j]+" "+sizes[j+1];
		}
		return news;
	}
}
