/**
 * 
 */
package model;

import java.awt.Event;
import java.util.List;

import interfaces.IInstruction;
import interfaces.IMemoryAccess;

/**
 * @author Chester
 *
 */
public class MemoryAccess implements IMemoryAccess {

	/* (non-Javadoc)
	 * @see interfaces.ICoreComponent#GetStatus()
	 */
	@Override
	public String GetStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see interfaces.ICoreComponent#Cycle(int)
	 */
	@Override
	public void Cycle(int aIn_numToCycle) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see interfaces.ICoreComponent#CurrentInstructions()
	 */
	@Override
	public List<IInstruction> CurrentInstructions() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see interfaces.ICoreComponent#PropertyChanged(java.lang.Object)
	 */
	@Override
	public Event PropertyChanged(Object aIn_propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see interfaces.IMemoryAccess#LoadWord(int, int)
	 */
	@Override
	public void LoadWord(int aIn_RD, int aIn_memaddr) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see interfaces.IMemoryAccess#StoreWord(int, int)
	 */
	@Override
	public void StoreWord(int aIn_RS, int aIn_memaddr) {
		// TODO Auto-generated method stub

	}

}
