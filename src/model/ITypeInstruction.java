/**
 * I type instructions are MIPS instructions with a Destination Register, Source Register, and 
 * immediate operand.
 * @see IInstruction
 */


package model;

import interfaces.IInstruction;

public class ITypeInstruction implements IInstruction {

	public int getFUNCT() {
		throw new UnsupportedOperationException();
	}

	public void setFUNCT(Object aFUNCT) {
		throw new UnsupportedOperationException();
	}

	public int getImmediate() {
		throw new UnsupportedOperationException();
	}

	public void setImmediate(Object aImmediate) {
		throw new UnsupportedOperationException();
	}

	public int getOpcode() {
		throw new UnsupportedOperationException();
	}

	public void setOpcode(int aOpcode) {
		throw new UnsupportedOperationException();
	}

	public int getRD() {
		throw new UnsupportedOperationException();
	}

	public void setRD(Object aRD) {
		throw new UnsupportedOperationException();
	}

	public int getRS() {
		throw new UnsupportedOperationException();
	}

	public void setRS(int aRS) {
		throw new UnsupportedOperationException();
	}

	public int getRT() {
		throw new UnsupportedOperationException();
	}

	public void setRT(int aRT) {
		throw new UnsupportedOperationException();
	}

	public int getSHAMT() {
		throw new UnsupportedOperationException();
	}

	public void setSHAMT(int aSHAMT) {
		throw new UnsupportedOperationException();
	}

	public InstructionType getType() {
		return InstructionType.I;
	}

	public int getJumpdest() {
		throw new UnsupportedOperationException();
	}

	public void setJumpdest(int aJumpdest) {
		throw new UnsupportedOperationException();
	}
}