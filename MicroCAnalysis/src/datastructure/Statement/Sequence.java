package datastructure.Statement;

import java.util.ArrayList;
import java.util.List;

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

}
