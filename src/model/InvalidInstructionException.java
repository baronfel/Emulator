/**
 * This exception will be thrown when the Instruction Parser tries to read in an instruction and
 * that instruction does not match the format for any currently accepted instructions.
 * @see IInstruction
 * @see Exception
 */
package model;

import java.util.List;

/**
 * @author Bob
 *
 */
public class InvalidInstructionException extends Exception{

	private List<String> invalidlist;

	public InvalidInstructionException(){
		super();
	}
	
	public InvalidInstructionException(List<String> list){
		setInvalidList(list);
	}

	public void setInvalidList(List<String> invalidlist) {
		this.invalidlist = invalidlist;
	}

	public List<String> getInvalidList() {
		return invalidlist;
	}
}
