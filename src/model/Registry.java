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
	
	private ArrayList<Register> registers = new ArrayList<Register>(35);

	public Registry()
	{
		super();
		InitRegisters();
	}
	
	private void InitRegisters()
	{
		for(int i = 0; i < 35; i++)
		{
			registers.add(new Register());
		}
	}
	
	/**
	 * gets the value of a given register
	 * @param registerNumber
	 * @return the value of the register to return
	 */
	public int getValue(int registerNumber)
	{
		return registers.get(registerNumber).getValue();
	}
	
	public void setValue(int registerToUpdate, int value)
	{
		registers.get(registerToUpdate).setValue(value);
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
	
	/**
	 * Tells weather the specified register is in use
	 * @param registerNumber The number of the register to check
	 */
	public boolean isRegisterInUse(int registerNumber)
	{
		if(registerNumber == 0)
			return false;
		return registers.get(registerNumber).isInUse();
	}
	
	/**
	 * Sets a register to be in use.
	 * @param registerNumber The register to set to be in use.
	 */
	public void setRegisterToInUse(int registerNumber)
	{
		registers.get(registerNumber).setInUse(true);
	}
	
	public void addListener(int registerToWatch, IModelListener listener)
	{

		registers.get(registerToWatch).addListener(listener);
	}
	
	public void removeListener(int registerToWatch, IModelListener listener)
	{
		registers.get(registerToWatch).removeListener(listener);
	}
	
	/**
	 * Sets a register to not be in use.
	 * @param registerNumber The register to set to not be in use.
	 */
	public void setRegisterToNotInUse(int registerNumber) {
		// TODO Auto-generated method stub
		registers.get(registerNumber).setInUse(false);
		
	}
}
