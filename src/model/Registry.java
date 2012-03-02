/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author chusk3
 * This class is used by the Simulation, WriteBack, and Issue units to provide a common access into the registers.
 */
public class Registry {
	
	private ArrayList<Integer> registers = new ArrayList<Integer>(32);

	public Registry()
	{
		
	}
	
	/**
	 * gets the value of a given register
	 * @param registerNumber
	 * @return
	 */
	public int getValue(int registerNumber)
	{
		return registers.get(registerNumber);
	}
	
	/**
	 * sets the register given with the value provided.
	 * @param regNumber
	 * @param value
	 */
	public void setRegister(int regNumber, int value)
	{
		registers.set(regNumber, value);
	}
	
	
}
