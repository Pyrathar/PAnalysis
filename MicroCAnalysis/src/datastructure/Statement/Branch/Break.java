package datastructure.Statement.Branch;

import AbstractSyntax.ASTNode;
import datastructure.Statement.Statement;

public class Break extends Statement {
	
	private static String astType = "breakStmt";
	
	public Break(){
	}

	public ASTNode toAST(){
		
		ASTNode ast = new ASTNode(this,"break");
		return ast;
	}

	public String toString() {
		return "break";
	}
	
	public String getLabel() {
		return "break";
	}

}
