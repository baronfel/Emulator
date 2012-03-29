/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chusk3
 * This class is used by the Simulation and MEmory Write Back classes to provide a common reference to the data section of the program.
 */

public class Memory extends AbstractModel {
	
	private List<Integer> _memory = new ArrayList<Integer>();
	
	public Memory()
	{
		
	}
	
	public int getValueAt(int location)
	{
		return _memory.get(location);
	}
	
	/**
	 * Sets the value of a particular location in memory to a given integer. Notifies any listeners at the end.
	 * 
	 * ModelEvent.ExtraData contains the location that was modified, which can be retrieved by listeners.
	 * @param location the location in memory to set.
	 * @param value the value to set at the previously mentioned location.
	 */
	public void setValueAt(int location, int value)
	{
		_memory.set(location, value);
		notifyChanged(new ModelEvent(this, 0, "Updated memory at location", location));

	}

}
