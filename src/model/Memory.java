/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author chusk3
 * This class is used by the Simulation and MEmory Write Back classes to provide a common reference to the data section of the program.
 */
public class Memory {
	
	private ArrayList<Integer> _memory = new ArrayList<Integer>();
	
	public Memory()
	{
		
	}
	
	public int getValueAt(int location)
	{
		return _memory.get(location);
	}
	
	public void setValueAt(int location, int value)
	{
		_memory.set(location, value);
	}

}
