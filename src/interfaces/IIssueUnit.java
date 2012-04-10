/**
 * The core component that handles deciding which instruction should go through the pipeline next.
 * NO IT DOESN'T THAT'S WHAT FETCH DOES. WHAT DOES THIS DO?
 * @see ICoreComponent
 */

package interfaces;

public interface IIssueUnit extends ICoreComponent {


	public boolean addToPreIssue(IInstruction instruction);
	public void IssueInstructions(IInstruction instruction);
	public IMemoryAccess GetFirstAvailableMEM();
	public IALU GetFirstAvailableALU();
	public int getNumInPreIssue();


}
