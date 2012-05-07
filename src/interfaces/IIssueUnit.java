/**
 * The core component that handles fetching values from registers and passing them to the ALU or Memory Access Units.
 * @see ICoreComponent
 */

package interfaces;

public interface IIssueUnit extends ICoreComponent {


	/**
	 * This method adds an instruction the preIssue Buffer to be issued at the next clock cycle.
	 * @param instruction The instruction added to the buffer.
	 * @return True, if the instruction is added. False if the instruction is not added.
	 */
	public boolean addToPreIssue(IInstruction instruction);
	/**
	 * This method is called from the cycle method. It issues the next instruction in the queue.
	 * @param instruction The instruction to issue.
	 */
	public void IssueInstructions(IInstruction instruction);
	/**
	 * This method gets the Memroy Access Unit with the least instructions waiting in the buffer.
	 * @return The number of the Memory Access Unit with the lowest number of instructions in the buffer.
	 */
	public IMemoryAccess GetFirstAvailableMEM();
	/**
	 * This method gets the ALU with the least instructions waiting in the buffer.
	 * @return The number of the ALU with the lowest number of instructions in the buffer.
	 */
	public IALU GetFirstAvailableALU();
	/**
	 * This method returns the number of instructions waiting in the preIssue Queue.
	 * @return The number of Instructions.
	 */
	public int getNumInPreIssue();
	/**
	 * This method returns the number of no-ops processed by the Issue Unit.
	 * @return The number of no-ops processed by the Issue Unit.
	 */
	public int getNoops();


}
