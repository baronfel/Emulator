/**
 * I type instructions are MIPS instructions with a Destination Register, Source Register, and 
 * immediate operand.
 * @see IInstruction
 */


package model;

import interfaces.IInstruction;
import interfaces.InstructionDoesNotHaveFieldException;

public class ITypeInstruction implements IInstruction {

	private int immediate;
	private String opcode;
	private int rd;
	private int rs;

	/**
	 * The default constructor. It should be followed by initialization of the instruction.
	 */
	public ITypeInstruction() {
		super();
	}
	
	/**
	 * The primary constructor for the IType Instructions, including all of the fields.
	 * @param opc The Opcode of the Instruction.
	 * @param rd The Destination Register for the instruction.
	 * @param rs The Source Register for the instruction.
	 * @param imm The immediate value of the instruction.
	 */
	public ITypeInstruction(String opc, int rd, int rs, int imm) {
		super();
		immediate = imm;
		opcode = opc;
		this.rd = rd;
		this.rs = rs;
	}
	
	public int getFUNCT() {
		throw new InstructionDoesNotHaveFieldException();
	}

	public void setFUNCT(int aFUNCT) {
		throw new InstructionDoesNotHaveFieldException();
	}
/**
 * Returns the immediate field for this instruction.
 */
	public int getImmediate() {
		return immediate;
	}
	/**
	 * Sets the immediate field for this instruction.
	 * @param aImmediate The new immediate value for this function.
	 */

	public void setImmediate(int aImmediate) {
		immediate = aImmediate;
	}
	/**
	 * Returns the OP Code field for this instruction.
	 */

	public String getOpcode() {
		return opcode;
	}
	/**
	 * Sets the OP code field for this instruction.
	 * @param aOpcode The new OP code for this instruction.
	 */

	public void setOpcode(String aOpcode) {
		opcode = aOpcode;
	}
/**
 * Returns the Destination Register field for this instruction.
 */
	public int getRD() {
		return rd;
	}
/**
 * Sets the Destination Register field for this instruction.
 * @param aRD The new Destination Register field for the instruction.
 */
	public void setRD(int aRD) {
		rd = aRD;
	}
/**
 * Returns the Source Register field for this instruction.
 */
	public int getRS() {
		return rs;
	}
/**
 * Sets the Source Register field for this instruction.
 * @param aRS the new Source Register for the instruction.
 */
	public void setRS(int aRS) {
		rs = aRS;
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
	 * Returns the type of instruction, I, J, or R. For this I type, it returns I.
	 */

	public InstructionType getType() {
		return InstructionType.I;
	}

	public int getJumpdest() {
		throw new InstructionDoesNotHaveFieldException();
	}

	public void setJumpdest(int aJumpdest) {
		throw new InstructionDoesNotHaveFieldException();
	}
	
	
	public String toString() {
		String message = "" + opcode + " " + rd + ", " + rs + ", " + immediate + "\n";
		
		message = opcode + " " + rd + ", " + rs + ", " + immediate + "\n";
		switch (opcode.toLowerCase()) {
		case "lw": message = opcode + " " + rd + ", " + immediate + "(" + rs + ")\n";
			break;
		case "addi":
			break;
		case "sw": message = opcode + " " + rs + ", " + immediate + "(" + rd + ")\n";
			break;
		case "slti": 
			break;
		case "sltiu":
			break;
			default: 
				break;
		}
		

		return message;
		
	}
}