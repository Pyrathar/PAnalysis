package algorithm.worklist;

import java.util.ArrayList;
import java.util.List;

import AbstractSyntax.FlowNode;
import monotone.MonotoneFramework;
public abstract class Worklist {

	private MFP analysis;
	private MonotoneFramework mf;
	private List<LabelSet> flow;
	
	public Worklist(MonotoneFramework mf){
		this.mf = mf;
		this.analysis = new MFP(mf.getF().size());
		this.flow = new ArrayList<LabelSet>();
	}
	
	public MFP getAnalysis() {
		return analysis;
	}

	public MonotoneFramework getMf() {
		return mf;
	}

	public List<LabelSet> getFlow() {
		return flow;
	}
	
	public MFP worklistAlgorithm() {
		w(mf.getE());
		analysis.setCircleLabel(1, getAllElements(mf.getE()));
		
		List<LabelSet> wFlow = new ArrayList<LabelSet>(flow);
		
		LabelSet l = null;
		while (!wFlow.isEmpty()) {
			l = wFlow.remove(0);
			List<MFPElement> e = analysis(l.getLabel_1());
			if (!listSubset(e, analysis.getCircle(l.getLabel_2().getId()))) {
				analysis.setBulletLabel(l.getLabel_1().getId(), copyList(e));
				List<LabelSet> lTemp = new ArrayList<LabelSet>();
				for (LabelSet labelSet : wFlow) {
					if(labelSet.getLabel_1().equals(l.getLabel_2()))
						lTemp.add(labelSet);
				}
				wFlow.addAll(lTemp);

				List<MFPElement> c = new ArrayList<MFPElement>();
				for (LabelSet labelSet : flow) {
					if (labelSet.getLabel_2().getId() == l.getLabel_2().getId()) {
						if (analysis.getBullet(labelSet.getLabel_1().getId()) != null) {
							for (MFPElement element : analysis.getBullet(labelSet.getLabel_1().getId())) {
								if (!contains(c, element)) {
									c = add(c,element);
								}
							}
						}
					}
				}
				if (e != null && e.size() > 0) {
					analysis.setCircleLabel(l.getLabel_2().getId(), copyList(c));
					c = copyList(c);
//					printList(analysis.getCircle(l.getLabel_2().getId()));
//					System.out.println();
				}
				continue;
			}
			if (analysis.getBullet(l.getLabel_2().getId()) == null) {
				analysis.setBulletLabel(l.getLabel_2().getId(),
						copyList(analysis(l.getLabel_2())));
			}
		}
		return analysis;
	}
	
	private void w(FlowNode node){
		if (node == null) {
			return;
		}
		FlowNode[] next = node.getNext();
		if (next != null) {
			if (next[0] != null) {
				LabelSet ls = new LabelSet(node, next[0]);
				if (flow.contains(ls))
					return;
				flow.add(ls);
				w(next[0]);
			}
			if (next[1] != null) {
				LabelSet ls = new LabelSet(node, next[1]);
				if (flow.contains(ls))
					return;
				flow.add(ls);
				w(next[1]);
			}
		}
	}
	
	public abstract boolean listSubset(List<MFPElement> first, List<MFPElement> second);

	public void printList(List<MFPElement> list){
		for (MFPElement element : list) {
			System.out.print("("+element.getName() + "," + element.getLineNumber() + ")");
		}
		if(list.size() > 4)
			System.out.print("\t");
		else
			System.out.print("\t\t");
	}
	
	public abstract List<MFPElement> analysis(FlowNode node);
	
	public abstract List<MFPElement> getAllElements(FlowNode F);

	public abstract List<MFPElement> copyList(List<MFPElement> toCopy) ;

	public abstract boolean contains(List<MFPElement> elemList, MFPElement e) ;
	
	public abstract List<MFPElement> add(List<MFPElement> elemList, MFPElement e) ;

}
