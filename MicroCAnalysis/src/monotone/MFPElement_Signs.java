package monotone;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import algorithm.worklist.MFPElement;

public class MFPElement_Signs extends algorithm.worklist.MFPElement {
	private Set<Character> signs = new HashSet<Character>();

	public MFPElement_Signs(int lineNumber, String variableName, Set<Character> signs) {
		super(variableName, lineNumber);
		this.signs.addAll(signs);
	}

	public Set<Character> getSigns() {
		return signs;
	}

	public void setSigns(Set<Character> signs) {
		this.signs = signs;
	}

	@Override
	public String toString() {
		return "label: " + super.getLineNumber() + " " + getName() + " =[" + Arrays.toString(signs.toArray()) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((signs == null) ? 0 : signs.hashCode());
		return result;
	}

	@Override
	public MFPElement clone() {
		MFPElement_Signs res = new MFPElement_Signs(getLineNumber(), getName(), new HashSet<Character>());
		res.getSigns().addAll(getSigns());
		return res;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof MFPElement_Signs))
			return false;
		MFPElement_Signs other = (MFPElement_Signs) obj;
		if (!(getName().equals(other.getName())))
			return false;
		if (!(getLineNumber() == other.getLineNumber()))
			return false;
		if (signs.size() != other.getSigns().size())
			return false;
		if (!getSigns().equals(other.getSigns()))
			return false;
		return true;
	}
}
