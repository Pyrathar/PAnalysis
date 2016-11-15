package datastructure.Statement.Branch;

import datastructure.Statement.Statement;

public class Break extends Statement {
	
	private static String astType = "breakStmt";
	
	public Break(){
	}

	
//	public ASTLeaf toAST(){
//		
//		ASTLeaf ast = new ASTLeaf(this);
//		
//		return ast;
//	}

	public String toString() {
		return "break";
	}
	
	public String getLabel() {
		return "break";
	}

}
