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
public class Memory {

	private List<Integer> _memory = new ArrayList<Integer>();
	//private List<Integer> _memory;

	public Memory(int cap)
	{
		Integer tmp = new Integer(0);

		for (int i = 1; i < cap; i++){
			_memory.add(tmp);
		}
	}

	public int getValueAt(int location)
	{
		return _memory.get(location);
	}

	public void setValueAt(int location, Integer value)
	{
		_memory.set(location, value);
	}
}
