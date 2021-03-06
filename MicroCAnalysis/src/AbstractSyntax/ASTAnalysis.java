package AbstractSyntax;

import java.util.List;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

import datastructure.Assignment.VariAssignment;
import datastructure.Declaration.ArrayDeclar;
import datastructure.Declaration.VariDeclar;
import datastructure.Program.End;
import datastructure.Program.Program;
import datastructure.Statement.Sequence;
import datastructure.Statement.Statement;
import datastructure.Statement.Condition.IFElseCondi;
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
				//System.out.println(classname);
				
				if(element.getClass().toString().matches(".*IFElseCondi")){
					top.addChildren(((IFElseCondi) element).toAST());
				}else if(element.getClass().toString().matches(".*WhileCondi")){
					top.addChildren(((WhileCondi) element).toAST());
				}else if(element.getClass().toString().matches(".*ArrAssignment")){
					//top.addChildren(((ArrayAssignment) element).toAST());
				}else if(element.getClass().toString().matches(".*VariAssignment")){
					top.addChildren(((VariAssignment) element).toAST());
				}else if(element.getClass().toString().matches(".*ArrRead")){
					//top.addChildren(((ArrRead) element).toAST());
				}else if(element.getClass().toString().matches(".*VarRead")){
					//top.addChildren(((VarRead) element).toAST());
				}else if(element.getClass().toString().matches(".*Write")){
					top.addChildren(((Write) element).toAST());
				}else if(element.getClass().toString().matches(".*Skip")){
					//top.addChildren(((Skip) element).toAST());
				}else if(element.getClass().toString().matches(".*Sequence")){
					top.addChildren(((Sequence) element).toAST());
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
		if(node.getName().equals("}")) {
    		level=1;
    		System.out.println(level + ": " + node.getName());
    		return;
    	}else {
    		level++;
    		for (int i = 1; i < level; i++) {
    			System.out.print("    ");
    		}
    		System.out.println(level + ": " + node.getName());
        	for (ASTNode child : node.getChildren()) {
    			showAST(child, level);
    		}
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
				//System.out.println(preFlowNode[0].toString());
				newFlowNode= flowForWhile(leaf,preFlowNode[0]);
				if(preFlowNode[0].getNext() == null || preFlowNode[0].getNext()[0] == null)
					preFlowNode[0].setNextFirst(newFlowNode);
				else
					preFlowNode[0].setNextSecond(newFlowNode);
			}else if(leaf.getElement().getClass().toString().matches(".*IFElseCondi")) {
				newFlowNode = flowForIfelse(leaf,preFlowNode[0]);
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
	
	private FlowNode flowForWhile(ASTNode node,FlowNode previous) {
		ASTNode first = node.getChildren().get(0);
		FlowNode condi = new FlowNode(previous,first,flowId);
		previous.setNextFirst(condi);
		flowForSequence(node.getChildren(),condi,true);
		
		return condi;
	}
	
	public FlowNode flowForIfelse(ASTNode node,FlowNode previous) {
		ASTNode first = node.getChildren().get(0);
		//ASTNode node = new ASTNode(ifelse,"");
		FlowNode newNode = new FlowNode(first,flowId);
		//System.out.println(flowId);
		previous.setNextFirst(newNode);
		FlowNode cureNo = newNode;
		flowForSequence(node.getChildren().get(1).getChildren(),cureNo,false);
		flowForSequence(node.getChildren().get(2).getChildren(),cureNo,false);
		return newNode;
	}
	
	public FlowNode flowForSequence(List<ASTNode> nodelist,FlowNode previous,boolean isWhile) {
		FlowNode curNode = previous;
		if(nodelist != null && nodelist.size() >0) {
			if(nodelist.size() == 1) {
				flowId ++;
				FlowNode flow = new FlowNode(nodelist.get(0),flowId);
				if(curNode.getNext()[0] == null) {
					curNode.setNextFirst(flow);
				}else {
					curNode.setNextSecond(flow);
				}
			}else {
				for(int i=1;i<nodelist.size();i++) {
					ASTNode node = nodelist.get(i);
					flowId ++;
					FlowNode fl = new FlowNode(node,flowId);
					//System.out.println(flowId);
					if(curNode.getNext()[0] == null) {
						curNode.setNextFirst(fl);
					}else {
						curNode.setNextSecond(fl);
					}
					if(nodelist.size() != 1) {
						curNode = fl;
					}
					if(i==nodelist.size()-1 && isWhile) {
						fl.setNextFirst(previous);
					}
				}
			}
		}
		return curNode;
	}
	
	public void showFlow(FlowNode node) {
		flowGraph += node.getId();
		//System.out.println(node.getLeaf().getElement().getClass().toString());
		if(node.getLeaf().getElement().getClass().toString().matches(".*IFElseCondi")) {
			//System.out.println("sssss");
			for(int i=0;i<node.getNext().length;i++) {
				showNextNode(node.getNext()[i]);
			}
		}else {
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
	
	public void showNextNode(FlowNode node) {
		if(node != null) {
			flowGraph += "-->";
			if(!flowGraph.contains(node.getId()+"")) {
				showFlow(node);
			}
		}
	}


}
