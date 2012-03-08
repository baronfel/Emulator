/**
 * The basic interface for all MIPS instructions.
 * @see ITypeInstruction
 * @see JTypeInstruction
 * @see RTypeInstruction
 */

package interfaces;

import model.InstructionType;

public interface IInstruction {
/**
 * Gets the FUNCT value of the instruction, the last six bits.
 * @return The FUNCT value of the instruction.
 */
	public int getFUNCT();
/**
 * Sets the FUNCT value of the instruction, the last six bits.
 * @param aFUNCT The FUNCT value of the instruction.
 */
	public void setFUNCT(int aFUNCT);
/**
 * Gets the immediate value of the instruction, the last sixteen bits.
 * @return The immediate value of the instruction, the last sixteen bits.
 */
	public int getImmediate();
	/**
	 * Sets the immediate value of the instruction, the last sixteen bits.
	 * @param aImmediate The immediate value of the instruction, the last sixteen bits.
	 */
	public void setImmediate(int aImmediate);
	/**
	 * Gets the Opcode value of the instruction, the first six bits.
	 * @return The Opcode value of the instruction, the first six bits.
	 */
	public String getOpcode();
	/**
	 * Sets the Opcode value of the instruction, the first six bits.
	 * @param aOpcode The Opcode value of the instruction, the first six bits.
	 */
	public void setOpcode(String aOpcode);
	/**
	 * Gets the Destination Register of the instruction, the bits 16-20 for R type or 11-15 for I type.
	 * @return The Destination Register of the instruction, the bits 16-20 for R type or 11-15 for I type.
	 */
	public int getRD();
	/**
	 * Sets the Destination Register of the instruction, the bits 16-20 for R type or 11-15 for I type.
	 * @param aRD The Destination Register of the instruction, the bits 16-20 for R type or 11-15 for I type.
	 */

	public void setRD(int aRD);
	/**
	 * Gets the Source Register of the instruction, the bits 6-10.
	 * @return The Source Register of the instruction, the bits 6-10.
	 */

	public int getRS();
	/**
	 * Sets the Source Register of the instruction, the bits 6-10.
	 * @param aRS The Source Register of the instruction, the bits 6-10.
	 */

	public void setRS(int aRS);
	/**
	 * Gets the Third Register of the instruction, the bits 11-15.
	 * @return The Third Register of the instruction, the bits 11-15.
	 */

	public int getRT();
	/**
	 * Gets the Third Register of the instruction, the bits 11-15.
	 * @param aRT The Third Register of the instruction, the bits 11-15.
	 */

	public void setRT(int aRT);
	/**
	 * Gets the shift amount of the instruction, the bits 21-25.
	 * @return The shift amount of the instruction, the bits 21-25.
	 */

	public int getSHAMT();
	/**
	 * Sets the shift amount of the instruction, the bits 21-25.
	 * @param aSHAMT The shift amount of the instruction, the bits 21-25.
	 */

	public void setSHAMT(int aSHAMT);
	
	/**
	 * Gets the type of the instruction, I for I type, J for J type, or R for R type.
	 * @return The type of the instruction, I for I type, J for J type, or R for R type.
	 */

	public InstructionType getType();
	/**
	 * Sets the type of the instruction, I for I type, J for J type, or R for R type.
	 * @param aType The type of the instruction, I for I type, J for J type, or R for R type.
	 */
	
	public int getJumpdest();
	/**
	 * Gets the jump destination of the instruction, bits 6-31.
	 * @param aJumpdest The jump destination of the instruction, bits 6-31.
	 */

	public void setJumpdest(int aJumpdest);
}