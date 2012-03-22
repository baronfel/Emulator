/**
 * 
 */
package model;

/**
 * @author Chester
 *
 */
public class Register extends AbstractModel {
	private int value = 0;

	public Register(){
		
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int newValue) {
		value = newValue;
		notifyChanged(new ModelEvent(this, 0, "New Register Value", value));
	}

}
