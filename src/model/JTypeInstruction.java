/**
 * J type instructions are MIPS instructions with a jump destination.
 * @see IInstruction
 */


package model;

import interfaces.IInstruction;
import interfaces.InstructionDoesNotHaveFieldException;

public class JTypeInstruction implements IInstruction {

	private int jumpdest;
	private int seqNum;
	
	/**
	 * A default constructor for the JTypeInstruction. It should be followed by setting the jump
	 * destination.
	 */
	public JTypeInstruction() {
		super();
	}
	/**
	 * The primary constructor for the JTypeInsturction.
	 * @param jdst The jump destination for the instruction.
	 */
	public JTypeInstruction(int jdst, int seqnum) {
		jumpdest = jdst;
		seqNum = seqnum;
	}
	
	public int getFUNCT() {
		throw new InstructionDoesNotHaveFieldException();
	}

	public void setFUNCT(int aFUNCT) {
		throw new InstructionDoesNotHaveFieldException();
	}

	public int getImmediate() {
		throw new InstructionDoesNotHaveFieldException();
	}

	public void setImmediate(int aImmediate) {
		throw new InstructionDoesNotHaveFieldException();
	}

	public String getOpcode() {
		return "J";
	}

	public void setOpcode(String aOpcode) {
		
	}

	public int getRD() {
		throw new InstructionDoesNotHaveFieldException();
	}

	public void setRD(int aRD) {
		throw new InstructionDoesNotHaveFieldException();
	}

	public int getRS() {
		throw new InstructionDoesNotHaveFieldException();
	}

	public void setRS(int aRS) {
		throw new UnsupportedOperationException();
	}

	public int getRT() {
		throw new InstructionDoesNotHaveFieldException();
	}

	public void setRT(int aRT) {
		throw new InstructionDoesNotHaveFieldException();
	}

	public int getSHAMT() {
		throw new InstructionDoesNotHaveFieldException();
	}

	public void setSHAMT(int aSHAMT) {
		throw new InstructionDoesNotHaveFieldException();
	}
	/**
	 * Returns the type of instruction, I, J, or R. For this J type, it returns J.
	 */

	public InstructionType getType() {
		return InstructionType.J;
	}
	/**
	 * Returns the destination the jump instruction will jump to.
	 */
	public int getJumpdest() {
		return jumpdest;
	}
/**
 * Sets the destination the jump instruction will jump to.
 * @param aJumpdest The new destination for the jump instruction.
 */
	public void setJumpdest(int aJumpdest) {
		jumpdest = aJumpdest;
	}
	
	
	public String toString() {
		String message = "J " + jumpdest + "\n";
		
		return message;
		
	}
	@Override
	public int getSeqNum() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}

}