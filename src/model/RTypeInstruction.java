/**
 * R type instructions are MIPS instructions with a Destination Register, Source Register, third 
 * register, shift amount, and function code.
 * @see IInstruction
 */


package model;

import interfaces.IInstruction;
import interfaces.InstructionDoesNotHaveFieldException;

public class RTypeInstruction implements IInstruction {

	private int rs;
	private int rd;
	private String opcode;
	private int funct;
	private int rt;
	private int shamt;
	
	
	/**
	 * The default constructor for the RTypeInstruction. It should be followed by
	 * initialization of all of the fields of the instruction.
	 */
	public RTypeInstruction() {
		super();
	}
	
	/**
	 * The primary constructor for the RTypeInsturction. It includes all fields of the instruction
	 * in the order in which they appear in the assembly code.
	 * @param opc The opcode for the instruction.
	 * @param rd The destination register for the instruction.
	 * @param rs The source register for the instruction.
	 * @param rt The third register for the instruction.
	 * @param shamt The shift amount for the instruction.
	 * @param funct The function code for the instruction.
	 */
	public RTypeInstruction(String opc, int rd, int rs, int rt, int shamt, int funct) {
		opcode = opc;
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
		this.shamt = shamt;
		this.funct = funct;
	}
	
/**
 * Returns the function code of the MIPS instruction.
 */
	public int getFUNCT() {
		return funct;
	}
/**
 * Sets the function code of the MIPS instruction.
 * @param aFUNCT The new function code for the instruction.
 */
	public void setFUNCT(int aFUNCT) {
		funct = aFUNCT;
	}

	public int getImmediate() {
		throw new InstructionDoesNotHaveFieldException();
	}

	public void setImmediate(int aImmediate) {
		throw new InstructionDoesNotHaveFieldException();
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
 * @param aRD The new Destination Register for this instruction.
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
 * @param aRS The new Source Register for this instruction.
 */
	public void setRS(int aRS) {
		rs = aRS;
	}
	/**
	 * Returns the third Register field for this instruction.
	 */
	public int getRT() {
		return rt;
	}
	/**
	 * Sets the third Register field for this instruction.
	 * @param aRS The new Source Register for this instruction.
	 */
	public void setRT(int aRT) {
		rt = aRT;
	}
/**
 * Returns the shift amount for this instruction.
 */
	public int getSHAMT() {
		return shamt;
	}
/**
 * Sets a new shift amount for this instruction.
 * @param aSHAMT The new shift amount for this instruction.
 */
	public void setSHAMT(int aSHAMT) {
		shamt = aSHAMT;
	}
/**
 * Returns the type of instruction, I, J, or R. For this R type, it returns R.
 */
	public InstructionType getType() {
		return InstructionType.R;
	}

	public int getJumpdest() {
		throw new InstructionDoesNotHaveFieldException();
	}

	public void setJumpdest(int aJumpdest) {
		throw new InstructionDoesNotHaveFieldException();
	}
	
	
	
	public String toString() {
		String message = "" + opcode + ", " + rd + ", " + rs + ", " + rt + ", " + shamt + ", " + funct + "\n";
		
		message = opcode + " " + rd + ", " + rs + ", " + rt + "\n";
		switch (opcode.toLowerCase()) {
		case "jr": message = opcode + " " + rs + "\n";
			break;
		case "mul":
			break;
		case "add":
			break;
		case "sub": 
			break;
		case "sll": message = opcode + " " + rd + ", " + rs + ", " + shamt + "\n";
			break;
		case "srl": message = opcode + " " + rd + ", " + rs + ", " + shamt + "\n";
			break;
		case "nop": message = "NOP\n";
			break;
		case "and": 
			break;
		case "or": 
			break;
		case "slt": 
			break;
		case "sltu": 
			break;
		case "nor": 
			break;
		case "div": 
			break;
		default: 
				break;
		}
		
		
		
		return message;
		
	}

}