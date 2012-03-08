/**
 * A branch instruction is a specific kind of I type instruction that branches to a label.
 * @see IInstruction
 */
package model;

/**
 * @author Bob
 *
 */
public class BranchInstruction extends ITypeInstruction{
	
	private String label;

	public BranchInstruction(){
		super();
	}
	
	public BranchInstruction(String opc, int rd, int rs, int imm) {
		super(opc, rd, rs, imm);
	}
	
	public BranchInstruction(String opc, int rd, int rs, int imm, String label) {
		super(opc, rd, rs, imm);
		this.setLabel(label);
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	public String toString(){
		String message = getOpcode() + " " + getRS() + ", " + getRD() + ", " + label + "\n";
		return message;
	}
}
