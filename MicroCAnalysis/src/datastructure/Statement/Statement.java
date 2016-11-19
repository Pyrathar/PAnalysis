package datastructure.Statement;

import AbstractSyntax.ASTElement;
import AbstractSyntax.ASTNode;
import datastructure.Assignment.Assignment;
import datastructure.Statement.Branch.Break;
import datastructure.Statement.Branch.Continue;
import datastructure.Statement.Condition.IFElseCondi;
import datastructure.Statement.Condition.WhileCondi;
import datastructure.Statement.Interact.Read;
import datastructure.Statement.Interact.Write;

public class Statement extends ASTElement {
	
	private static String astType = "stmt";
	
	private String text;

	public Statement(){
		
	}
	
	public Statement(String text){
		//super.setText(text);
	}
	
	public static Statement convertTextToASTElement(String text){
		Statement stmt;
		text = text.substring(text.indexOf("(", 1),text.lastIndexOf(")"));
		
		stmt = new Break();
		
		String[] t = text.split(" ");
		
		if(t[0].replaceAll("\\(", "").equals("assignStmt")){
			stmt =  Assignment.convertTextToASTElement(text);
		}else if(t[0].replaceAll("\\(", "").equals("readStmt")){
			stmt =  Read.convertTextToASTElement(text);
		}else if(t[0].replaceAll("\\(", "").equals("ifelseStmt")){
			stmt =  IFElseCondi.convertTextToASTElement(text);
		}else if(t[0].replaceAll("\\(", "").equals("whileStmt")){
			stmt =  WhileCondi.convertTextToASTElement(text);
		}else if(t[0].replaceAll("\\(", "").equals("writeStmt")){
			//TODO
			stmt =  Write.convertTextToASTElement(text);
		}else if(t[0].replaceAll("\\(", "").equals("stmt")){
			//TODO
			stmt =  Statement.convertTextToASTElement(text);
		}else if(t[0].replaceAll("\\(", "").equals("breakStmt")){
			stmt =  new Break();
		}else if(t[0].replaceAll("\\(", "").equals("continueStmt")){
			stmt =  new Continue();
		}
			
		return stmt;
	}
	
	//TODO Convert to ASTTree
	public ASTNode toAST(){
		
		if(this.getClass().toString().matches("IfElse")){
			//return ((IfElse) this).toAST();
		}else if(this.getClass().toString().matches("While")){
			return ((WhileCondi) this).toAST();
		}else if(this.getClass().toString().matches("ArrAssignment")){
			//return ((ArrAssignment) this).toAST();
		}else if(this.getClass().toString().matches("VarAssignment")){
			//return ((VarAssignment) this).toAST();
		}else if(this.getClass().toString().matches("ArrRead")){
			//return ((ArrRead) this).toAST();
		}else if(this.getClass().toString().matches("VarRead")){
		//	return ((VarRead) this).toAST();
		}else if(this.getClass().toString().matches("Write")){
			//return ((Write) this).toAST();
		}else if(this.getClass().toString().matches("Skip")){
			//return ((Skip) this).toAST();
		}
		
		return null;
	}

}
