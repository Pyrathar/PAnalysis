package monotone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import AbstractSyntax.ASTElement;
import AbstractSyntax.ASTNode;
import AbstractSyntax.FlowNode;
import algorithm.worklist.MFPElement;
import algorithm.worklist.Worklist;
import datastructure.Assignment.ArrayAssignment;
import datastructure.Assignment.VariAssignment;
import datastructure.Basic.ArrVariable;
import datastructure.Basic.Constant;
import datastructure.Basic.Variable;
import datastructure.Operator.BinaryOperator;
import datastructure.Statement.Condition.WhileCondi;
import datastructure.Statement.Interact.Read;
import datastructure.Statement.Interact.Write;

public class SignDetectionAnalysis extends Worklist{
	
	private Set<Character> negative = new HashSet<Character>();
	private Set<Character> zero = new HashSet<Character>();

	public SignDetectionAnalysis(MonotoneFramework mf) {
		super(mf);
		negative.add('-');
		zero.add('0');
	}

	@Override
	public void printList(List<MFPElement> list) {
		for (MFPElement element : list) {
			MFPElement_Signs signEle = (MFPElement_Signs) element;
			System.out.print("(" + signEle.getName() + "," + Arrays.toString(signEle.getSigns().toArray()) + ")");
		}
		if (list.size() > 4)
			System.out.print("\t");
		else
			System.out.print("\t\t");
	}

	@Override
	public List<MFPElement> analysis(FlowNode node) {
		List<MFPElement> last = new ArrayList<MFPElement>(super.getAnalysis().getCircle(node.getId()));
		List<MFPElement> result = copyList(transferFunction(node, last));
		return result;
	}

	@Override
	public List<MFPElement> getAllElements(FlowNode F) {
		List<MFPElement> elements = new ArrayList<MFPElement>();
		List<String> addedVariables = new ArrayList<String>();
		for (FlowNode n : F.toList()) {
			String varName = n.getLeaf().getChildren().get(1).getElement().toString();
			if (!(n.getLeaf().getElement() instanceof WhileCondi) && !addedVariables.contains(varName)) {
				MFPElement_Signs ele = new MFPElement_Signs(0, varName, new HashSet<Character>());
				ele.getSigns().add('0');
				elements.add(ele);
				addedVariables.add(varName);
			}
		}
		return elements;
	}

	@Override
	public boolean listSubset(List<MFPElement> first, List<MFPElement> second) {
		if (second.size() == 0)
			return false;
		boolean found = false;
		for (int i = 0; i < first.size(); i++) {
			MFPElement_Signs firstEle = (MFPElement_Signs) first.get(i);
			for (int j = 0; j < second.size(); j++) {
				MFPElement_Signs secondEle = (MFPElement_Signs) second.get(j);
				if (secondEle.equals(firstEle)) {
					found = true;
					break;
				}
			}
			if (!found)
				return false;
			found = false;
		}
		return true;
	}

	@Override
	public List<MFPElement> copyList(List<MFPElement> toCopy) {
		List<MFPElement> result = new ArrayList<MFPElement>(toCopy.size());
		for (MFPElement element : toCopy) {
			MFPElement_Signs elementSigns = (MFPElement_Signs) element;
			MFPElement_Signs newElement = new MFPElement_Signs(elementSigns.getLineNumber(), elementSigns.getName(),
					new HashSet<Character>());
			newElement.getSigns().addAll(elementSigns.getSigns());
			result.add(newElement);
		}
		return result;
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

	public List<MFPElement> transferFunction(FlowNode node, List<MFPElement> last) {
		String name = node.getLeaf().getChildren().get(0).getElement().toString();

		ASTElement element = node.getLeaf().getElement();
		MFPElement_Signs elem = null;
		if (element instanceof VariAssignment) {
			Set<Character> signSet = determineArithmeticSign(node.getLeaf().getChildren().get(1), last);
			elem = new MFPElement_Signs(node.getId(), name, signSet);
		} else if (element instanceof ArrayAssignment) {
			Set<Character> signSet_a1 = determineArithmeticSign(node.getLeaf().getChildren().get(0), last);
			Set<Character> signSet_a2 = determineArithmeticSign(node.getLeaf().getChildren().get(1), last);
			if (signSet_a1.isEmpty() || signSet_a1.equals(negative) || signSet_a2.isEmpty()) {
				elem = new MFPElement_Signs(node.getId(), name, new HashSet<Character>());
			} else {
				elem = new MFPElement_Signs(node.getId(), name, signSet_a2);
			}
		} else if (element instanceof Read) {
			elem = new MFPElement_Signs(node.getId(), name, new HashSet<Character>());
			elem.getSigns().addAll(Arrays.asList(new Character[] { '+', '0', '-' }));
		}else if (element instanceof Write) {
			if (node.getLeaf().getChildren().get(0).getElement() instanceof ArrVariable) {
				Set<Character> signSet = determineArithmeticSign(node.getLeaf().getChildren().get(1), last);
				if (signSet.isEmpty() || signSet.equals(negative)) {
					elem = new MFPElement_Signs(node.getId(), name, new HashSet<Character>());
				} else {
					for (MFPElement lastElem : last) {
						MFPElement_Signs lastElemSigns = (MFPElement_Signs) lastElem;
						if (elem.getName().equals(lastElemSigns.getName())) {
							elem = lastElemSigns;
						}
					}
				}
			}
		} else if (element instanceof ArrVariable) {
			Set<Character> signSet = determineArithmeticSign(node.getLeaf().getChildren().get(0), last);
			elem = new MFPElement_Signs(node.getId(), name, new HashSet<Character>());
			if (!signSet.equals(negative) && !signSet.equals(zero)) {
				elem.getSigns().add('0');
			}
		} else if (element instanceof Variable) {
			elem = new MFPElement_Signs(node.getId(), name, new HashSet<Character>());
			elem.getSigns().add('0');
		} else {
			return last;
		}
		last = copyList(last);
		MFPElement_Signs curVariable = ((MFPElement_Signs) elem);
		for (MFPElement eleLast : last) {
			MFPElement_Signs lastVariable = ((MFPElement_Signs) eleLast);
			if (curVariable.getName().equals(lastVariable.getName())) {
				lastVariable.setLineNumber(node.getId());
				lastVariable.getSigns().addAll(curVariable.getSigns());
			} else {
				lastVariable.setLineNumber(node.getId());
			}
		}
		return last;
	}

	public Set<Character> determineArithmeticSign(ASTNode leaf, List<MFPElement> last) {
		Set<Character> result = new HashSet<Character>();
		ASTElement element = leaf.getElement();
		if (element instanceof ArrVariable) {
			List<ASTNode> children = leaf.getChildren();
			if (children != null) {
				for (ASTNode astLeaf : children) {
					result.addAll(determineArithmeticSign(astLeaf, last));
				}
			}
		} else if (element instanceof Variable) {
			for (MFPElement lastElem : last) {
				MFPElement_Signs lastElemSigns = (MFPElement_Signs) lastElem;
				if (lastElemSigns != null) {
					if (lastElemSigns.getName().equals(element.toString())) {
						result = ((MFPElement_Signs) lastElem).getSigns();
					}
				}
			}
		} else if (element instanceof Constant) {
			int value = Integer.valueOf(element.toString());
			if (value == 0)
				result.add('0');
			else if (value > 0)
				result.add('+');
			else
				result.add('-');
		} else if (element instanceof BinaryOperator) {
			List<ASTNode> children = leaf.getChildren();
			List<Set<Character>> signSets = new ArrayList<Set<Character>>();
			for (int i = 0; i < children.size(); i++) {
				ASTNode child = children.get(i);
				signSets.add(determineArithmeticSign(child, last));
			}
			Set<Character> signSet_1 = signSets.get(0);
			Set<Character> signSet_2 = signSets.get(1);
			BinaryOperator opA = (BinaryOperator) element;
//			OpA op = opA.getOperator();
//			for (Character sign1 : signSet_1) {
//				for (Character sign2 : signSet_2) {
//					if (op == OpA.ADDITION) {
//						result.addAll(add(sign1, sign2));
//					} else if (op == OpA.SUBTRACTION) {
//						result.addAll(sub(sign1, sign2));
//					} else if (op == OpA.MULIPLICATION) {
//						result.addAll(mult(sign1, sign2));
//					} else if (op == OpA.DIVISION) {
//						result.addAll(div(sign1, sign2));
//					}
//				}
//			}
		}
		return result;
	}

	public Set<Character> add(Character c1, Character c2) {
		Set<Character> result = new HashSet<Character>();
		if (c1 == '-' && c2 == '-') {
			result.add('-');
		} else if (c1 == '-' && c2 == '0') {
			result.add('-');
		} else if (c1 == '-' && c2 == '+') {
			result.add('-');
			result.add('+');
			result.add('0');
		} else if (c1 == '0' && c2 == '-') {
			result.add('-');
		} else if (c1 == '0' && c2 == '0') {
			result.add('0');
		} else if (c1 == '0' && c2 == '+') {
			result.add('+');
		} else if (c1 == '+' && c2 == '-') {
			result.add('+');
			result.add('0');
			result.add('-');
		} else if (c1 == '+' && c2 == '0') {
			result.add('+');
		} else if (c1 == '+' && c2 == '+') {
			result.add('+');
		}
		return result;
	}

	public Set<Character> sub(Character c1, Character c2) {
		Set<Character> result = new HashSet<Character>();
		if (c1 == '-' && c2 == '-') {
			result.add('+');
			result.add('0');
			result.add('-');
		} else if (c1 == '-' && c2 == '0') {
			result.add('-');
		} else if (c1 == '-' && c2 == '+') {
			result.add('-');
		} else if (c1 == '0' && c2 == '-') {
			result.add('+');
		} else if (c1 == '0' && c2 == '0') {
			result.add('0');
		} else if (c1 == '0' && c2 == '+') {
			result.add('-');
		} else if (c1 == '+' && c2 == '-') {
			result.add('+');
		} else if (c1 == '+' && c2 == '0')
			result.add('+');
		else if (c1 == '+' && c2 == '+') {
			result.add('0');
			result.add('-');
			result.add('+');
		}
		return result;
	}

	public Set<Character> div(Character c1, Character c2) {
		Set<Character> result = new HashSet<Character>();
		if (c1 == '-' && c2 == '-') {
			result.add('+');
		} else if (c1 == '-' && c2 == '0') {
			result.add('0');
		} else if (c1 == '-' && c2 == '+') {
			result.add('-');
		} else if (c1 == '0' && c2 == '-') {
			result.add('0');
		} else if (c1 == '0' && c2 == '0') {
			result.add('0');
		} else if (c1 == '0' && c2 == '+') {
			result.add('0');
		} else if (c1 == '+' && c2 == '-') {
			result.add('-');
		} else if (c1 == '+' && c2 == '0')
			result.add('0');
		else if (c1 == '+' && c2 == '+') {
			result.add('+');
		}
		return result;
	}

	public Set<Character> mult(Character c1, Character c2) {
		Set<Character> result = new HashSet<Character>();
		if (c1 == '-' && c2 == '-') {
			result.add('+');
		} else if (c1 == '-' && c2 == '0') {
			result.add('0');
		} else if (c1 == '-' && c2 == '+') {
			result.add('-');
		} else if (c1 == '0' && c2 == '-') {
			result.add('0');
		} else if (c1 == '0' && c2 == '0') {
			result.add('0');
		} else if (c1 == '0' && c2 == '+') {
			result.add('0');
		} else if (c1 == '+' && c2 == '-') {
			result.add('-');
		} else if (c1 == '+' && c2 == '0')
			result.add('0');
		else if (c1 == '+' && c2 == '+') {
			result.add('+');
		}
		return result;
	}

	public boolean containsVariable(List<MFPElement> elemList, MFPElement e) {
		boolean result = false;
		for (MFPElement elem : elemList) {
			if(elem.getName().equals(e.getName())) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	@Override
	public List<MFPElement> add(List<MFPElement> elemList, MFPElement e) {
		if(containsVariable(elemList, e)){
			for (MFPElement mfpElement : elemList) {
				if(mfpElement.getName().equals(e.getName())){
					for (Character c : ((MFPElement_Signs) e).getSigns()) {
						((MFPElement_Signs) mfpElement).getSigns().add(c);
					}
				}
			}
		}
		else{
			MFPElement newEle = e.clone();
			elemList.add(newEle);
		}
		return elemList;
	}

}
