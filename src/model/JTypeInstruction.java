/**
 * J type instructions are MIPS instructions with a jump destination.
 * @see IInstruction
 */


package model;

import interfaces.IInstruction;
import interfaces.InstructionDoesNotHaveFieldException;

public class JTypeInstruction implements IInstruction {

	private int jumpdest;
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

	public int getOpcode() {
		throw new UnsupportedOperationException();
	}

	public void setOpcode(int aOpcode) {
		throw new UnsupportedOperationException();
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
}