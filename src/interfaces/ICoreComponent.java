/**
 * One of the core components of a MIPS machine. Each one corresponds to a stage in the MIPS pipeline.
 * @see ICore
 */

package interfaces;

import java.util.List;

public interface ICoreComponent extends IModel{
/**
 * Gets the status of the component.
 * @return The status of the component.
 */
	public ProcStatus GetStatus();
/**
 * Executes one cycle of the ICoreComponent.
 */
	public void Cycle();

/**
 * A list of the current instructions.
 * @return A list of the current instructions.
 */
	public List<IInstruction> CurrentInstructions();
}
