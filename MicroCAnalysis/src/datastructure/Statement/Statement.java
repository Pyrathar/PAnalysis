package datastructure.Statement;

import AbstractSyntax.ASTElement;
import AbstractSyntax.ASTNode;
import datastructure.Assignment.Assignment;
import datastructure.Assignment.VariAssignment;
import datastructure.Statement.Branch.Break;
import datastructure.Statement.Branch.Continue;
import datastructure.Statement.Condition.IFElseCondi;
import datastructure.Statement.Condition.WhileCondi;
import datastructure.Statement.Interact.Read;
import datastructure.Statement.Interact.ReadVariable;
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
		text = text.replaceAll("\\(", "").replaceAll("\\)", "");
		
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
			text = text.substring(5, text.length());
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
		System.out.println(this.getClass().toString());
		if(this.getClass().toString().matches("IfElse")){
			//return ((IfElse) this).toAST();
		}else if(this.getClass().toString().matches("While")){
			return ((WhileCondi) this).toAST();
		}else if(this.getClass().toString().matches("ArrAssignment")){
			//return ((ArrAssignment) this).toAST();
		}else if(this.getClass().toString().matches(".*VariAssignment")){
			return ((VariAssignment) this).toAST();
		}else if(this.getClass().toString().matches(".*break")){
			return ((Break) this).toAST();
		}else if(this.getClass().toString().matches(".*ReadVariable")){
			return ((ReadVariable) this).toAST();
		}else if(this.getClass().toString().matches(".*Write")){
			//return ((Write) this).toAST();
		}else if(this.getClass().toString().matches(".*Sequence")){
			return ((Sequence) this).toAST();
		}
		
		return null;
	}

}
