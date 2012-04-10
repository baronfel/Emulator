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
	public int getPostALUDestReg();

	/**
	 * Get the op result from the post ALU access buffer.
	 */
	public double getPostALUOpResult();

	public int getAmountInPreALU();

	public int addToPreALU(String opName, int seq, int op1, int op2, int dest);

	public int getALUNumber();

}