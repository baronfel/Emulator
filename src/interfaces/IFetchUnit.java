/**
 * The core component that handles deciding which instruction should go through the pipeline next.
 * It takes values from registers and passes those directly.
 * This component also handles all jumps and branches.
 * @see ICoreComponent
 */

package interfaces;

public interface IFetchUnit extends ICoreComponent {

	/**
	 * Gets the next instruction to be sent through the pipeline from the list of instructions.
	 */

	public void FetchInstruction();
	public int getPC();
}
