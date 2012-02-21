/**
 * This component handles writing back a value to the destination register.
 * @see ICoreComponent
 */

package model;

public interface IWriteBack extends ICoreComponent {

	public void WriteToRegister(int aIn_value, int aIn_RD);
}