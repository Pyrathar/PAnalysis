package datastructure.Statement.Branch;

import AbstractSyntax.ASTNode;
import datastructure.Statement.Statement;

public class Continue extends Statement {
	
	
	private static String astType = "continueStmt";
	
	public Continue(){
	}

	
	public ASTNode toAST(){
		
		ASTNode ast = new ASTNode(this,"continue");
		
		return ast;
	}

	public String toString() {
		return "continue";
	}
	
	public String getLabel() {
		return "continue";
	}


}
