/**
 * 
 */
package model;

import interfaces.IModelListener;

import java.util.ArrayList;

/**
 * @author chusk3
 * This class is used by the Simulation, WriteBack, and Issue units to provide a common access into the registers.
 */
public class Registry extends AbstractModel {
	
	private ArrayList<Register> registers = new ArrayList<Register>(32);

	public Registry()
	{
		super();
	}
	
	/**
	 * gets the value of a given register
	 * @param registerNumber
	 * @return
	 */
	public int getValue(int registerNumber)
	{
		return registers.get(registerNumber).getValue();
	}
	
	/**
	 * sets the register given with the value provided.
	 * @param regNumber
	 * @param value
	 */
	public void setRegister(int regNumber, int value)
	{
		registers.get(regNumber).setValue(value);
	}
	
	public void addListener(int registerToWatch, IModelListener listener)
	{
		registers.get(registerToWatch).addModelListener(listener);
	}
	
	public void removeListener(int registerToWatch, IModelListener listener)
	{
		registers.get(registerToWatch).removeModelListener(listener);
	}
}
