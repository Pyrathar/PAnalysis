package monotone;

import java.util.ArrayList;
import java.util.List;

import AbstractSyntax.ASTNode;
import AbstractSyntax.FlowNode;
import algorithm.worklist.MFPElement;
import algorithm.worklist.Worklist;
import datastructure.Basic.ArrVariable;
import datastructure.Basic.Variable;
import datastructure.Declaration.ArrayDeclar;
import datastructure.Declaration.VariDeclar;

public class ReachingDefinitionAnalysis extends Worklist {
	
	public ReachingDefinitionAnalysis(MonotoneFramework mf) {
		super(mf);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<MFPElement> analysis(FlowNode node){
		List<MFPElement> last = new ArrayList<MFPElement>(super.getAnalysis().getCircle(node.getId()));
		List<MFPElement> killLast = KillRD(node);
		for (MFPElement variable : killLast) {
			last.remove(variable);
		}
		MFPElement tlast = GenRD(node);
		if(tlast != null)
			last.add(tlast);
		return last;
	}
	
	@Override
	public List<MFPElement> getAllElements(FlowNode F){
		List<MFPElement> variables = new ArrayList<MFPElement>();
		
		for (FlowNode node : F.toList()) {
			String nodeName = node.getLeaf().getElement().getClass().toString();
			System.out.println(nodeName);
			if(nodeName.matches(".*VariDeclar")){
				Variable element = ((VariDeclar)node.getLeaf().getElement()).getValue();
				variables.add(new MFPElement(element.getName(),0));
			}
			if(nodeName.matches(".*ArrayDeclar")){
				ArrVariable array = ((ArrayDeclar)node.getLeaf().getElement()).getName();
				variables.add(new MFPElement(array.getName(),0));
			}
		}
		
		return variables;
	}

	@Override
	public boolean listSubset(List<MFPElement> first, List<MFPElement> second){
		if(second.size() == 0)
			return false;
		for(int i = 0; i < first.size(); i++){
			if(!second.contains(first.get(i))){
				return false;
			}
		}
		return true;
	}

	@Override
	public List<MFPElement> copyList(List<MFPElement> toCopy) {
		List<MFPElement> result = new ArrayList<MFPElement>(toCopy.size());
		for (MFPElement element : toCopy) {
			MFPElement e = element;
			MFPElement newE = new MFPElement(e.getName(), e.getLineNumber());
			result.add(newE);
		}
		return result;
	}
	
	public List<MFPElement> KillRD(FlowNode killNode){
		
		List<MFPElement> kill = new ArrayList<MFPElement>();
		String className = killNode.getLeaf().getElement().getClass().toString();
		System.out.println(killNode.getLeaf().getElement().getClass().toString());
		String va = "";
		if(className.matches(".*VariDeclar") ||  
				className.matches(".*ArrayDeclar")) {
			va = killNode.getLeaf().getChildren().get(1).getElement().toString();
		}else if(className.matches(".*VariAssignment") || 
				className.matches(".*ArrAssignment") || 
				className.matches(".*Read")) {
			va = killNode.getLeaf().getChildren().get(0).getElement().toString();
		}
		List<ASTNode> nodeList = killNode.getLeaf().getChildren();
		kill.add(new MFPElement(va, 0));
		for (FlowNode node : super.getMf().getF()) {
			String nodeName = node.getLeaf().getElement().getClass().toString();
			if(nodeName.matches(".*VarAssignment") ||
					nodeName.matches(".*ArrAssignment") || 
					nodeName.matches(".*VariDeclar") || 
					nodeName.matches(".*ArrayDeclar") || 
					nodeName.matches(".*Read")){
				if(node.getLeaf().getChildren().get(1).getElement().toString().equals(va)){
					kill.add(new MFPElement(va, node.getId()));
				}
			}
		}
		return kill;
	}

	public MFPElement GenRD(FlowNode node){
		
		String variable = null;
		String nodeName = node.getLeaf().getElement().getClass().toString();
		if(nodeName.matches(".*VariAssignment")){
			variable = node.getLeaf().getChildren().get(0).getElement().toString();
		}else if(nodeName.matches(".*ArrAssignment")){
			variable = node.getLeaf().getChildren().get(0).getElement().toString();
		}else if(nodeName.matches(".*VarRead")){
			variable = node.getLeaf().getChildren().get(0).getElement().toString();
		}else if(nodeName.matches(".*ArrRead")){
			variable = node.getLeaf().getChildren().get(0).getElement().toString();
		}else if(nodeName.matches(".*VariDeclar")){
			variable = node.getLeaf().getChildren().get(1).getElement().toString();
		}else if(nodeName.matches(".*ArrayDeclar")){
			variable = node.getLeaf().getChildren().get(1).getElement().toString();
		}
		
		if(variable != null)
			return new MFPElement(variable, node.getId());
		else
			return null;
	}

	@Override
	public boolean contains(List<MFPElement> elemList, MFPElement e) {
		boolean result = false;
		for (MFPElement elem : elemList) {
			if(elem.equals(e)) {
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public List<MFPElement> add(List<MFPElement> elemList, MFPElement e) {
		MFPElement newEle = e.clone();
		elemList.add(newEle);
		return elemList;
	}
}
