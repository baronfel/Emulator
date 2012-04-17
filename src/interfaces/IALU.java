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
	 * buffer.
	 */
	public int getPostALUDestReg(boolean clear);
	
	/**
	 * Get the destination register 2 for the op result from the post ALU access
	 * buffer. For ops that require multiple destination registers
	 */
	public int getPostALUDestReg2(boolean clear);

	/**
	 * Get the op result from the post ALU access buffer.
	 */
	public int getPostALUOpResult();
	
	/**
	 * Get the op result 2 from the post ALU access buffer.
	 *  For ops that require multiple destination registers
	 */
	public int getPostALUOpResult2();

	/**
	 * * Method to get the ALU number. Used only for class testing.
	 */
	public int getALUNumber();

	/**
	 * Method to get the buffer size. Used only for class testing.
	 */
	public int getBufferSize();

	/**
	 * Methods to get/set contents of the ALU buffers. Used only for class
	 * testing.
	 */
	public String getPreALUOpName(int index);

	public int getPreALUProgSeqNum(int index);

	public int getPreALUOp1Val(int index);

	public int getPreALUOp2Val(int index);

	public int getPreALUDestReg(int index);

	public int getPreALUNumCycles(int index);

	public String getCurrentInstrOpName();

	public int getCyclesProcessed();

}