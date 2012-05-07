/**
 * The component that handles all ALU operations.
 * @see ICoreComponent
 */

package interfaces;

public interface IALU extends ICoreComponent {
	/**
	 * This operation adds either two registers or one register and an immediate
	 * operator.
	 * 
	 * @param aIn_RD
	 *            The location to put the result.
	 * @param aIn_RS
	 *            One value to be added.
	 * @param aIn_RT
	 *            The other value to be added.
	 * @param aIn_Immediate
	 *            The other value to be added.
	 */
	public void Add(int aIn_RD, int aIn_RS, int aIn_RT, int aIn_Immediate);

	/**
	 * Process one clock cycle of the ALU
	 */
	public void processClockCycle();

	/**
	 * Add an instruction to the pre-ALU buffer
	 * 
	 * @param opName
	 *            the name of the operation.
	 * @param seq
	 *            The program sequence number of the instruction.
	 * @param op1
	 *            The first operator for the instruction.
	 * @param op2
	 *            The second operator for the instruction.
	 * @param dest
	 *            The destination register for the operation result.
	 */
	public int addToPreALU(String opName, int seq, int op1, int op2, int dest);

	/**
	 * Get the number of instructions in the pre-ALU.
	 */
	public int getAmountInPreALU();

	/**
	 * Get the instruction sequence number from the post ALU access buffer.
	 * 
	 * @param clear
	 *            Clears the sequence number in the post-ALU buffer if True.
	 */
	public int getPostALUSequenceNum(boolean clear);

	/**
	 * Get the destination register for the op result from the post ALU access
	 * buffer and possibly clears the buffer.
	 * @param clear True if the buffer will be cleared, false if the buffer will stay.
	 * @return The destination register for the result.
	 */
	public int getPostALUDestReg(boolean clear);
	
	/**
	 * Get the destination register 2 for the op result from the post ALU access
	 * buffer and possibly clears the buffer. For ops that require multiple destination registers
	 * @param clear True if the buffer will be cleared, false if the buffer will stay.
	 * @return The other destination register for the result.
	 */
	public int getPostALUDestReg2(boolean clear);

	/**
	 * Get the op result from the post ALU access buffer.
	 * @return The result of the operation.
	 */
	public int getPostALUOpResult();
	
	/**
	 * Get the op result 2 from the post ALU access buffer.
	 *  For ops that require multiple destination registers
	 *  @return The other result of the operation.
	 */
	public int getPostALUOpResult2();

	/**
	 * * Method to get the ALU number. Used only for class testing.
	 * @return The ID number of the ALU.
	 */
	public int getALUNumber();

	/**
	 * Method to get the buffer size. Used only for class testing.
	 * @return The Size of the ALU's buffer.
	 */
	public int getBufferSize();

	/**
	 * Method to return the op code of an instruction waiting in the preALU.
	 * @param index The index of the instruction in the preALU.
	 * @return The op code of the specified instruction.
	 */
	public String getPreALUOpName(int index);
	/**
	 * Method to return the sequence number of an instruction waiting in the preALU.
	 * @param index The index of the instruction in the preALU.
	 * @return The sequence number of the specified instruction.
	 */

	public int getPreALUProgSeqNum(int index);
	/**
	 * Method to return the a value of an instruction waiting in the preALU.
	 * @param index The index of the instruction in the preALU.
	 * @return The a value of the specified instruction.
	 */

	public int getPreALUOp1Val(int index);
	/**
	 * Method to return the other value of an instruction waiting in the preALU.
	 * @param index The index of the instruction in the preALU.
	 * @return The other value of the specified instruction.
	 */

	public int getPreALUOp2Val(int index);
	/**
	 * Method to return the destination register of an instruction waiting in the preALU.
	 * @param index The index of the instruction in the preALU.
	 * @return The destination register of the specified instruction.
	 */

	public int getPreALUDestReg(int index);
	/**
	 * Method to return the number of cycles an instruction waiting in the preALU takes.
	 * @param index The index of the instruction in the preALU.
	 * @return The number of cycles the specified instruction takes.
	 */
	public int getPreALUNumCycles(int index);
/**
 * Method to get the op code of the current instruction.
 * @return The op code of the current instruction.
 */
	public String getCurrentInstrOpName();
/**
 * Method to get the number of cycles this component has been in use.
 * @return The number of cycles this component has been in use.
 */
	public int getCyclesProcessed();

}