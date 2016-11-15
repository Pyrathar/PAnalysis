package datastructure.Statement.Branch;

import datastructure.Statement.Statement;

public class Continue extends Statement {
	
	
	private static String astType = "breakStmt";
	
	public Continue(){
	}

	
//	public ASTLeaf toAST(){
//		
//		ASTLeaf ast = new ASTLeaf(this);
//		
//		return ast;
//	}

	public String toString() {
		return "continue";
	}
	
	public String getLabel() {
		return "continue";
	}


}
