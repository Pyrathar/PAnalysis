package AbstractSyntax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlowNode {
	
	private int id;
	private FlowNode[] previous = null;
	private ASTNode leaf;
	private FlowNode[] next = null;

	public FlowNode(ASTNode leaf, int id) {
		this.previous = new FlowNode[2];
		this.next = new FlowNode[2];
		this.leaf = leaf;
		this.id = id;
	}

	public FlowNode(FlowNode previous, ASTNode leaf, int id) {
		if(this.previous == null) 
			this.previous = new FlowNode[2];
		this.next = new FlowNode[2];
		this.previous[0] = previous;
		this.leaf = leaf;
		this.id = id;
	}

	public FlowNode[] getPrevious() {
		return previous;
	}

	public void setPreviousFirst(FlowNode previous) {
		if(this.previous == null) 
			this.previous = new FlowNode[2];
		this.previous[0] = previous;
	}

	public void setPreviousSecond(FlowNode previous) {
		if(this.previous == null) 
			this.previous = new FlowNode[2];
		this.previous[1] = previous;
	}

	public FlowNode[] getNext() {
		return this.next;
	}

	public void setNextFirst(FlowNode next) {
		if(this.next == null) 
			this.next = new FlowNode[2];
		this.next[0] = next;
	}

	public void setNextSecond(FlowNode next) {
		if(this.next == null) 
			this.next = new FlowNode[2];
		this.next[1] = next;
	}

	public ASTNode getLeaf() {
		return leaf;
	}

	public void setLeaf(ASTNode leaf) {
		this.leaf = leaf;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FlowNode getEnd() {
		FlowNode end = this;
		if (next != null && next[0] != null) {
			end = next[0].getEnd();
			if (end == null) {
				end = next[0];
			}
		}
		return end;
	}

	public boolean isInitNode() {
		return (previous == null || (previous[0] == null && previous[1] == null));
	}
	
	public List<FlowNode> toList(){
		List<FlowNode> nodeList = new ArrayList<FlowNode>();
		
		FlowNode node = this;
		
		while(node != null){
			if(node.getNext()[1] == null){
				nodeList.add(node);
				if(node.getNext()[0] != null){
					if(node.getId() < node.getNext()[0].getId()){
						node = node.getNext()[0];
						continue;
					}
				}
				break;
			}
			//System.out.println(node.getLine().getElement());
			List<FlowNode> next = node.getNext()[1].toList();
			nodeList.add(node);
			nodeList.addAll(node.getNext()[0].toList());
			nodeList.addAll(next);
			node = next.get(next.size()-1).getNext()[0];
		}
		return nodeList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlowNode other = (FlowNode) obj;
		if (id != other.id)
			return false;
		if (leaf == null) {
			if (other.leaf != null)
				return false;
		} else if (!leaf.equals(other.leaf))
			return false;
		if (!Arrays.equals(next, other.next))
			return false;
		if (!Arrays.equals(previous, other.previous))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + "]";
	}
	
}
