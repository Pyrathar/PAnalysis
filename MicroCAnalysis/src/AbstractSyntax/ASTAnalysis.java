package AbstractSyntax;

import java.util.List;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

import datastructure.Declaration.ArrayDeclar;
import datastructure.Declaration.VariDeclar;
import datastructure.Program.End;
import datastructure.Program.Program;
import datastructure.Statement.Condition.WhileCondi;
import datastructure.Statement.Interact.Write;
public class ASTAnalysis {
	
	public ASTNode toAST(List<ParseTree> parseTree, Parser parser){
		ASTNode top = null;
		
		for (ParseTree e : parseTree) {
			if(e.toStringTree(parser).startsWith("{")){
				top = new ASTNode(new Program());
			}else if(e.toStringTree(parser).startsWith("}")){
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
					top.addChildren(((Write) element).toAST());
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
	
	public void showAST(ASTNode node, int level){
		level++;
		for (int i = 1; i < level; i++) {
			System.out.print("    ");
		}
    	System.out.println(level + ": " + node.getElement());
    	for (ASTNode child : node.getChildren()) {
			showAST(child, level);
		}
	}
	
	public FlowNode toFlowGraph(ASTNode ast, int id){
		FlowNode first = null;
		FlowNode[] preFlowNode = new FlowNode[2];
		FlowNode newFlowNode = null;
		
		for (ASTNode leaf : ast.getStmtAndDecl()) {
			id++;
			if(preFlowNode[0] == null){
				newFlowNode = new FlowNode(leaf,id);
				first = newFlowNode;
			}
			else{
				newFlowNode = new FlowNode(preFlowNode[0],leaf,id);
				if(preFlowNode[1] != null){
					newFlowNode.setPreviousSecond(preFlowNode[1]);
					if(preFlowNode[1].getNext()[0] == null)
						preFlowNode[1].setNextFirst(newFlowNode);
					else
						preFlowNode[1].setNextSecond(newFlowNode);
					preFlowNode[1] = null;
				}
					
				if(preFlowNode[0].getNext() == null || preFlowNode[0].getNext()[0] == null)
					preFlowNode[0].setNextFirst(newFlowNode);
				else
					preFlowNode[0].setNextSecond(newFlowNode);
			}
			preFlowNode[0] = newFlowNode;
			int i = 0;
			for (ASTNode child : leaf.getStmtAndDecl()) {
				System.out.println(leaf.getElement().getClass().toString());
				if(leaf.getElement().getClass().toString().matches(".*WhileCondi")){
					FlowNode temp = toFlowGraph(child,id);
					temp.setPreviousFirst(preFlowNode[0]);
					newFlowNode.setNextFirst(temp);
					FlowNode temp2 = temp.getEnd();
					temp2.setNextFirst(preFlowNode[0]);
					preFlowNode[0].setPreviousSecond(temp2);
					id = temp2.getId();
				}
				else{
					FlowNode temp = toFlowGraph(child,id);
					if(i == 0)
						newFlowNode.setNextFirst(temp);
					else{
						newFlowNode.setNextSecond(temp);
					}
					FlowNode temp2 = temp.getEnd();
					preFlowNode[i] = temp2;
					id = temp2.getId();
				}
				i++;
			}
		}

		return first;
	}

}
