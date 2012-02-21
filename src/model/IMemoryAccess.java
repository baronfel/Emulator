/**
 * This is a component that allows for memory to be accessed.
 * @see ICoreComponent
 */

package model;

public interface IMemoryAccess extends ICoreComponent {
/**
 * Loads a 32-bit word from memory to the destination register.
 * @param aIn_RD The destination register.
 * @param aIn_memaddr The address of the word in memory.
 */
	public void LoadWord(int aIn_RD, int aIn_memaddr);
/**
 * Stores a 32-bit word from a source register into memory.
 * @param aIn_RS The source register.
 * @param aIn_memaddr The address to store the word in memory.
 */
	public void StoreWord(int aIn_RS, int aIn_memaddr);
}