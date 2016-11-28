package datastructure.Statement;

import java.util.ArrayList;
import java.util.List;

import AbstractSyntax.ASTNode;

public class Sequence extends Statement {
	
	private List<Statement> statementList;
	
	public Sequence() {
		
	}
	
	public Sequence(List<Statement> statementList) {
		this.statementList = statementList;
	}
	
	public static Sequence convertTextToASTElement(String text){
		Sequence sequ = new Sequence();
		String[] states = text.split("stmt");
		List<Statement> statementLi = new ArrayList<Statement>();
		if(text.contains("stmt")) {
			for(int i=1;i<states.length;i++){
				statementLi.add(Statement.convertTextToASTElement("(stmt"+states[i]));
			}
		}
		sequ.setStatementList(statementLi);;
		return sequ;
	}

	public List<Statement> getStatementList() {
		return statementList;
	}

	public void setStatementList(List<Statement> statementList) {
		this.statementList = statementList;
	}
	
	public ASTNode toAST() {
		ASTNode node = new ASTNode(this,"sequence");
		for(int i=0;i<this.getStatementList().size();i++) {
			node.addChildren(this.getStatementList().get(i).toAST());
		}
		return node;
	}

}
