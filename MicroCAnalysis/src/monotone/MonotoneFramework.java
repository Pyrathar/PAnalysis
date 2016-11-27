package monotone;

import java.util.List;

import AbstractSyntax.FlowNode;

public class MonotoneFramework {
	private FlowNode E;
	private List<FlowNode> F;
	private Character iota;
	
	public MonotoneFramework(FlowNode E, List<FlowNode> F) {
		this.E = E;
		this.F = F;
	}
	
	public MonotoneFramework(FlowNode E, List<FlowNode> F, Character iota) {
		this.E = E;
		this.F = F;
		this.iota = iota;
	}
	
	public Character getIota() {
		return iota;
	}

	public void setIota(Character iota) {
		this.iota = iota;
	}

	public FlowNode getE() {
		return E;
	}
	
	public void setE(FlowNode E) {
		this.E = E;
	}
	
	public List<FlowNode> getF() {
		return F;
	}
	
	public void setF(List<FlowNode> F) {
		this.F = F;
	}
}
