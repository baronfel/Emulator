/**
 * 
 */
package model;

import java.awt.Event;
import java.util.List;

import interfaces.IInstruction;
import interfaces.IWriteBack;

/**
 * @author Chester
 *
 */
public class WriteBack implements IWriteBack {

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
	 * @see interfaces.IWriteBack#WriteToRegister(int, int)
	 */
	@Override
	public void WriteToRegister(int aIn_value, int aIn_RD) {
		// TODO Auto-generated method stub

	}

}
