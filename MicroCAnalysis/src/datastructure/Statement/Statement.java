package datastructure.Statement;

import datastructure.Assignment.Assignment;

public class Statement {
	
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
		
		stmt = new Skip();
		
		String[] t = text.split(" ");
		
		if(t[0].replaceAll("\\(", "").equals("assignStmt")){
			stmt =  Assignment.convertTextToASTElement(text);
		}else if(t[0].replaceAll("\\(", "").equals("readStmt")){
			stmt =  Read.convertTextToASTElement(text);
		}else if(t[0].replaceAll("\\(", "").equals("ifStmt")){
			stmt =  IfElse.convertTextToASTElement(text);
		}else if(t[0].replaceAll("\\(", "").equals("whileStmt")){
			stmt =  While.convertTextToASTElement(text);
		}else if(t[0].replaceAll("\\(", "").equals("writeStmt")){
			stmt =  Write.convertTextToASTElement(text);
		}else if(t[0].replaceAll("\\(", "").equals("stmt")){
			stmt =  Sequence.convertTextToASTElement(text);
		}else if(t[0].replaceAll("\\(", "").equals("skipStmt")){
			stmt =  new Skip();
		}
			
		return stmt;
	}
	
	public ASTLeaf toAST(){
		
		if(this.getClass().toString().matches("IfElse")){
			return ((IfElse) this).toAST();
		}else if(this.getClass().toString().matches("While")){
			return ((While) this).toAST();
		}else if(this.getClass().toString().matches("ArrAssignment")){
			return ((ArrAssignment) this).toAST();
		}else if(this.getClass().toString().matches("VarAssignment")){
			return ((VarAssignment) this).toAST();
		}else if(this.getClass().toString().matches("ArrRead")){
			return ((ArrRead) this).toAST();
		}else if(this.getClass().toString().matches("VarRead")){
			return ((VarRead) this).toAST();
		}else if(this.getClass().toString().matches("Write")){
			return ((Write) this).toAST();
		}else if(this.getClass().toString().matches("Skip")){
			return ((Skip) this).toAST();
		}
		
		return null;
	}

}
