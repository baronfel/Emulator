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
	
	public Memory(int cap, String Input)
	{
		this(cap);
		char[] input = Input.toCharArray();
		for(int i = 0; i < Input.length(); i++)
		{
			setValueAt(i, (int) input[i]);
		}
	}
	
	public void setMemoryToString(String Input)
	{
		char[] input = Input.toCharArray();
		for(int i = 0; i < Input.length(); i++)
		{
			setValueAt(i, (int) input[i]);
		}
	}	
	
	public int setMemoryToString2(String Input, int j)
	{
		int i;
		char[] input = Input.toCharArray();
		for(i = j; i < j + Input.length(); i++)
		{
			setValueAt(i, (int) input[i - j]);
		}
		
		
		return i;
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
