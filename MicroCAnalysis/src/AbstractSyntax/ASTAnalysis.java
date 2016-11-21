package AbstractSyntax;

import java.util.List;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

import datastructure.Declaration.ArrayDeclar;
import datastructure.Declaration.VariDeclar;
import datastructure.Program.End;
import datastructure.Program.Program;
import datastructure.Statement.Sequence;
import datastructure.Statement.Statement;
import datastructure.Statement.Condition.WhileCondi;
import datastructure.Statement.Interact.Write;
public class ASTAnalysis {
	
	public int flowId=0;
	
	public String flowGraph = "";
	
	public ASTNode toAST(List<ParseTree> parseTree, Parser parser){
		ASTNode top = null;
		
		for (ParseTree e : parseTree) {
			if(e.toStringTree(parser).startsWith("{")){
				top = new ASTNode(new Program(),"{");
			}else if(e.toStringTree(parser).startsWith("}")){
				top.addChildren(new ASTNode(new End(),"}"));
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
    	System.out.println(level + ": " + node.getName());
    	for (ASTNode child : node.getChildren()) {
			showAST(child, level);
		}
	}
	
	public FlowNode toFlowGraph(ASTNode ast){
		FlowNode first = null;
		FlowNode[] preFlowNode = new FlowNode[2];
		FlowNode newFlowNode = null;
		
		for (ASTNode leaf : ast.getStmtAndDecl()) {
			flowId++;
			if(preFlowNode[0] == null){
				newFlowNode = new FlowNode(leaf,flowId);
				first = newFlowNode;
			}else if(leaf.getElement().getClass().toString().matches(".*WhileCondi")) {
				System.out.println(preFlowNode[0].toString());
				newFlowNode= flowForWhile(leaf.getElement(),preFlowNode[0]);
				if(preFlowNode[0].getNext() == null || preFlowNode[0].getNext()[0] == null)
					preFlowNode[0].setNextFirst(newFlowNode);
				else
					preFlowNode[0].setNextSecond(newFlowNode);
			}else{
				newFlowNode = new FlowNode(preFlowNode[0],leaf,flowId);
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
		}
		return first;
	}
	
	private FlowNode flowForWhile(ASTElement element,FlowNode previous) {
		WhileCondi whileCode = ((WhileCondi)element);
		ASTNode node = new ASTNode(whileCode.getCondi(),"");
		FlowNode condi = new FlowNode(previous,node,flowId);
		flowId++;
		List<Statement> states = whileCode.getWhileState().getStatementList();
		if(states != null && states.size() >0) {
			FlowNode newNode = new FlowNode(previous,new ASTNode(states.get(0),""),flowId);
			condi.setNextFirst(newNode);
			previous = newNode;
			for(int i=1;i<states.size();i++) {
				flowId++;
				if(i!=states.size()-1)
				{
					FlowNode whileFirst = new FlowNode(previous,new ASTNode(states.get(i),""),flowId);
					previous.setNextFirst(whileFirst);
					previous = whileFirst;
				}else {
					FlowNode lastNode = new FlowNode(previous,new ASTNode(states.get(i),""),flowId);
					previous.setNextFirst(lastNode);
					lastNode.setNextFirst(condi);
					if(condi.getPrevious()[0] == null) {
						condi.setPreviousFirst(lastNode);
					}else {
						condi.setPreviousSecond(lastNode);
					}
				}
			}
		}
		return condi;
	}
	
	public void showFlow(FlowNode node) {
		flowGraph += node.getId();
		if(node.getNext()[0] != null) {
			flowGraph += "-->";
			if(!flowGraph.contains(node.getNext()[0].getId()+"")) {
				showFlow(node.getNext()[0]);
			}else {
				FlowNode nex = node.getNext()[0];
				flowGraph += nex.getId()+"";
				if(nex.getNext()[1] != null) {
					flowGraph += "-->";
					showFlow(nex.getNext()[1]);
				}
			}
		}
	}


}
