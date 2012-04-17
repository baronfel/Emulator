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
	 * @param imm
	 *            The immediate value for the operation.
	 */
	public int addToPreMEM(String opName, int seq, int rs, int rt,
			int cycles);

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
	 */
	public int getPostMEMDestReg();

	/**
	 * Get the op result from the post memory access buffer.
	 */
	public int getPostMEMOpResult();

	/**
	 * Method to get the buffer size. Used only for class testing.
	 */
	public int getBufferSize();

	/**
	 * Methods to return contents of the pre MEM buffer. Used only for class
	 * testing.
	 */
	public String getPreMEMOpName(int index);

	public int getPreMEMProgSeqNum(int index);

	public int getPreMEMrsVal(int index);

	public int getPreMEMrtVal(int index);

	public int getPreMEMimmVal(int index);

	public int getPreMEMNumCycles(int index);

	public String getCurrentInstrOpName();

	public int getCyclesProcessed();

	public int getAmountInPreMEM();

	public int getMEMNumber();
}