/**
 * The component that handles all ALU operations.
 * @see ICoreComponent
 */


package model;

public interface IALU extends ICoreComponent {
/**
 * This operation adds either two registers or one register and an immediate operator.
 * @param aIn_RD The location to put the result.
 * @param aIn_RS One value to be added.
 * @param aIn_RT The other value to be added.
 * @param aIn_Immediate The other value to be added.
 */
	public void Add(int aIn_RD, int aIn_RS, int aIn_RT, int aIn_Immediate);
}