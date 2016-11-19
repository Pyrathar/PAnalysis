package AbstractSyntax;

import java.util.List;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

import datastructure.Declaration.ArrayDeclar;
import datastructure.Declaration.VariDeclar;
import datastructure.Program.End;
import datastructure.Program.Program;
import datastructure.Statement.Condition.WhileCondi;

public class ASTAnalysis {
	
	public ASTNode toAST(List<ParseTree> parseTree, Parser parser){
		ASTNode top = null;
		
		for (ParseTree e : parseTree) {
			if(e.toStringTree(parser).startsWith("{")){
				top = new ASTNode(new Program());
			}else if(e.toStringTree(parser).startsWith("end")){
				top.addChildren(new ASTNode(new End()));
			}
			else{
				ASTElement element = ASTElement.convertTextToASTElement(e.toStringTree(parser));
				//TODO Please modify this part to complete the analysis process
				String classname = element.getClass().toString();
				System.out.println(classname);
				
				if(element.getClass().toString().matches(".*IfElse")){
					//top.addChildren(((IFElseCondi) element).toAST());
				}else if(element.getClass().toString().matches(".*WhileCondi")){
					top.addChildren(((WhileCondi) element).toAST());
				}else if(element.getClass().toString().matches(".*ArrAssignment")){
					//top.addChildren(((ArrayAssignment) element).toAST());
				}else if(element.getClass().toString().matches(".*VarAssignment")){
					//top.addChildren(((VarAssignment) element).toAST());
				}else if(element.getClass().toString().matches(".*ArrRead")){
					//top.addChildren(((ArrRead) element).toAST());
				}else if(element.getClass().toString().matches(".*VarRead")){
					//top.addChildren(((VarRead) element).toAST());
				}else if(element.getClass().toString().matches(".*Write")){
					//top.addChildren(((Write) element).toAST());
				}else if(element.getClass().toString().matches(".*Skip")){
					//top.addChildren(((Skip) element).toAST());
				}else if(element.getClass().toString().matches(".*Sequence")){
					//top.addChildren(((Sequence) element).toAST());
				}else if(element.getClass().toString().matches(".*VariDeclar")){
					top.addChildren(((VariDeclar) element).toAST());
				}else if(element.getClass().toString().matches(".*ArrayDeclar")){
					top.addChildren(((ArrayDeclar) element).toAST());
				}

			}
		}
		
		return top;
	}

}
