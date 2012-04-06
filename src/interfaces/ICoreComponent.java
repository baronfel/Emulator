/**
 * One of the core components of a MIPS machine. Each one corresponds to a stage in the MIPS pipeline.
 * @see ICore
 */

package interfaces;

import java.awt.Event;
import java.util.List;

public interface ICoreComponent{
/**
 * Tells whether or not this component is currently in use.
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
