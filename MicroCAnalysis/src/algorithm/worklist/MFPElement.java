package algorithm.worklist;

public class MFPElement {
	
	private String name;
	private int lineNumber;
	
	public MFPElement(String name, int lineNumber){
		this.name = name;
		this.lineNumber = lineNumber;
	}
	
	public MFPElement(int lineNumber){
		this.lineNumber = lineNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	@Override
	public MFPElement clone(){
		return new MFPElement(name, lineNumber);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MFPElement other = (MFPElement) obj;
		if (name.equals(other.name) && lineNumber == other.lineNumber)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "label: " + lineNumber + " name: " + name;
	}

}
