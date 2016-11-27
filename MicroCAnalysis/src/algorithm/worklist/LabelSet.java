package algorithm.worklist;

import AbstractSyntax.FlowNode;

public class LabelSet {
	private FlowNode label_1;
	private FlowNode label_2;

	public LabelSet(FlowNode l1, FlowNode l2) {
		this.label_1 = l1;
		this.label_2 = l2;
	}

	public FlowNode getLabel_1() {
		return label_1;
	}

	public void setLabel_1(FlowNode label_1) {
		this.label_1 = label_1;
	}

	public FlowNode getLabel_2() {
		return label_2;
	}

	public void setLabel_2(FlowNode label_2) {
		this.label_2 = label_2;
	}
	
	public String toString() {
		return "label1_id="+label_1.getId() + ", label2_id="+label_2.getId();  
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label_1 == null) ? 0 : label_1.hashCode());
		result = prime * result + ((label_2 == null) ? 0 : label_2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LabelSet))
			return false;
		LabelSet other = (LabelSet) obj;
		if (label_1 == null) {
			if (other.label_1 != null)
				return false;
		} else if (label_1.getId() != other.getLabel_1().getId())
			return false;
		if (label_2 == null) {
			if (other.label_2 != null)
				return false;
		} else if (label_2.getId() != other.getLabel_2().getId())
			return false;
		return true;
	}

	
}