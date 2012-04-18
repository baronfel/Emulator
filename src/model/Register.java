/**
 * 
 */
package model;

/**
 * @author Chester
 *
 */
public class Register extends AbstractModel {
	private int value;
	private boolean inUse;

	public Register(){
		value = 0;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int newValue) {
		value = newValue;
		notifyChanged(new ModelEvent(this, 0, "New Register Value", value));
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

}
