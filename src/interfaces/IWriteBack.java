/**
 * This component handles writing back a value to the destination register.
 * @see ICoreComponent
 */

package interfaces;

public interface IWriteBack extends ICoreComponent {
/**
 * This method is used to write a value to a particular register.
 * @param aIn_value The value to write to the register.
 * @param aIn_RD The register to write the value to.
 */
	public void WriteToRegister(int aIn_value, int aIn_RD);
	
	
/**
* Process one clock cycle of the Writeback unit
*/	
		public void processClockCycle();
		
		
		
}


