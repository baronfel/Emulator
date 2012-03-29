/**
 * One of the core components of a MIPS machine. Each one corresponds to a stage in the MIPS pipeline.
 * @see ICore
 */

package interfaces;

import java.awt.Event;
import java.util.List;

public interface ICoreComponent{
/**
 * Gets the status of the component.
 * @return The status of the component.
 */
	public String GetStatus();
/**
 * Executes one cycle of the ICoreComponent.
 */
	public void Cycle();

/**
 * A list of the current instructions.
 * @return A list of the current instructions.
 */
	public List<IInstruction> CurrentInstructions();
/**
 * Creates an Event when some component is changed to send to the listeners.
 * @return 
 * @see IModelListener
 */
	public Event PropertyChanged(Object aIn_propertyName);
}
