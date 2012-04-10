/**
 * This component handles writing back a value to the destination register.
 * @see ICoreComponent
 */

package interfaces;

public interface IWriteBack extends ICoreComponent {

	public void WriteToRegister(int aIn_value, int aIn_RD);
	
	
/**
* Process one clock cycle of the Writeback unit
*/	
		public void processClockCycle();
		
		
		
}


