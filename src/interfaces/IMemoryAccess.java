/**
 * This is a component that allows for memory to be accessed.
 * @see ICoreComponent
 */

package interfaces;

public interface IMemoryAccess extends ICoreComponent {
	/**
	 * Loads a 32-bit word from memory to the destination register.
	 * 
	 * @param aIn_RD
	 *            The destination register.
	 * @param aIn_memaddr
	 *            The address of the word in memory.
	 */
	public void LoadWord(int aIn_RD, int aIn_memaddr);

	/**
	 * Stores a 32-bit word from a source register into memory.
	 * 
	 * @param aIn_RS
	 *            The source register.
	 * @param aIn_memaddr
	 *            The address to store the word in memory.
	 */
	public void StoreWord(int aIn_RS, int aIn_memaddr);

	/**
	 * Method to process a clock cycle. If stallCycles is greater than 0, this
	 * method will just decrement the stallCycle variable then wait for the next
	 * clock cycle. If 0, this method will perform the operation of the current
	 * instruction
	 */
	public void processClockCycle();

	/**
	 * Method to add an instruction to the pre-MEM buffer. Returns 0 if the
	 * instruction was successfully added, otherwise returns -1 if buffer is
	 * full
	 * 
	 * @param opName
	 *            the name of the operation.
	 * @param seq
	 *            The program sequence number of the instruction.
	 * @param rs
	 *            The first operator for the instruction.
	 * @param rt
	 *            The second operator for the instruction.
	 */
	public int addToPreMEM(String opName, int seq, int rs, int rt);

	/**
	 * Get the instruction sequence number from the post memory access buffer.
	 * 
	 * @param clear
	 *            Clears the sequence number in the post-MEM buffer if True.
	 */
	public int getPostMEMSequenceNum(boolean clear);

	/**
	 * Get the destination register for the op result from the post memory
	 * access buffer.
	 * @return The Destination register for the operation.
	 */
	public int getPostMEMDestReg();

	/**
	 * Get the op result from the post memory access buffer.
	 * @return The result of the operation.
	 */
	public int getPostMEMOpResult();

	/**
	 * Method to get the buffer size. Used only for class testing.
	 * @return The buffer size.
	 */
	public int getBufferSize();

	/**
	 * Methods to return contents of the pre MEM buffer. Used only for class
	 * testing.
	 * @param index The index of the operation in the preMEM buffer.
	 * @return The OP code of the operation in the preMEM buffer at the specified location.
	 */
	public String getPreMEMOpName(int index);
/**
 * A method to return the sequence number of an operation at a particular place in the preMEM buffer.
 * @param index The index of the operation in the preMEM buffer.
 * @return The sequence number of the operation a the specified location.
 */
	public int getPreMEMProgSeqNum(int index);
/**
 * A method to return one of the operands of an operation at a particular place in the preMEM buffer.
 * @param index The index of the operation in the preMEM buffer.
 * @return An operand of the operation a the specified location.
 */
	public int getPreMEMrsVal(int index);
	/**
	 * A method to return another one of the operands of an operation at a particular place in the preMEM buffer.
	 * @param index The index of the operation in the preMEM buffer.
	 * @return Another operand of the operation a the specified location.
	 */

	public int getPreMEMrtVal(int index);
	/**
	 * A method to return another one of the operands of an operation at a particular place in the preMEM buffer.
	 * @param index The index of the operation in the preMEM buffer.
	 * @return Another operand of the operation a the specified location.
	 */

	public int getPreMEMimmVal(int index);
	/**
	 * A method to return the number of cycles an operation at a particular place in the preMEM buffer takes.
	 * @param index The index of the operation in the preMEM buffer.
	 * @return The number of cycles the operation a the specified location takes.
	 */

	public int getPreMEMNumCycles(int index);
/**
 * A method to return the op code of the current instruction.
 * @return The op code of the current instruction.
 */
	public String getCurrentInstrOpName();

	/**
	 * A method to return the number of cycles the Memory Access unit has been in use.
	 * @return The number of Cycles.
	 */
	public int getCyclesProcessed();

	/**
	 * A method to return the number of instructions waiting in the preMEM.
	 * @return The number of waiting instructions.
	 */
	public int getAmountInPreMEM();

	/**
	 * A method to return the Memory Access Unit's ID number.
	 * @return The ID number.
	 */
	public int getMEMNumber();

	/**
	 * A method to get the destination value from a postMEM buffer and possible clear the buffer.
	 * @param b Boolean that decides whether the buffer will be cleared or not. The buffer is cleared if b is true.
	 * @return The destination value from the postMEM buffer.
	 */
	public int getPostMEMDestReg2(boolean b);
}