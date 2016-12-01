package AbstractSyntax;

import java.util.ArrayList;
import java.util.List;

public class ASTNode {
	
	private ASTNode parent = null;
	private ASTElement element;
	private List<ASTNode> children = new ArrayList<ASTNode>();
	private String name;

	public ASTNode(ASTElement element,String name){
		this.name = name;
		this.element = element;
	}
	
	public ASTNode(String name) {
		this.name = name;
	}

	public ASTNode getParent() {
		return parent;
	}

	public void setParent(ASTNode parent) {
		this.parent = parent;
	}

	public ASTElement getElement() {
		return element;
	}

	public void setElement(ASTElement element) {
		this.element = element;
	}

	public List<ASTNode> getChildren() {
		return children;
	}

	public void setChildren(List<ASTNode> children) {
		this.children = children;
	}

	public void addChildren(ASTNode child) {
		this.children.add(child);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ASTNode> getStmtAndDecl(){
		List<ASTNode> stmtAndDecl = new ArrayList<ASTNode>();
		
		for (ASTNode astLeaf : this.getChildren()) {
			if(astLeaf != null) {
				//System.out.println(astLeaf.element.getClass().toString());
				if(astLeaf.element.getClass().toString().matches("(.*datastructure\\.Declaration.*|.*datastructure\\.Statement.*)")
						||astLeaf.element.getClass().toString().matches(".*datastructure\\.Assignment.*")){
					stmtAndDecl.add(astLeaf);
				}
			}
		}
		
		return stmtAndDecl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
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
		ASTNode other = (ASTNode) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
	}

}
