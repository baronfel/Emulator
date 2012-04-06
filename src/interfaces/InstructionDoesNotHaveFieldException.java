/**
 * This Exception will be thrown when a call is made for a MIPS instruction to get or set a field
 * which it does not contain. Otherwise it is identical to an UnsupportedOperationException.
 * @see UnsupportedOperationException
 */
package interfaces;

/**
 * @author Bob
 *
 */
public class InstructionDoesNotHaveFieldException extends
		UnsupportedOperationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 330655546005050961L;

	/**
	 * Changed to have a default message.
	 */
	public InstructionDoesNotHaveFieldException() {
		// TODO Auto-generated constructor stub
		super("This MIPS instruction does not have the requested field.");
	}

	/**
	 * @param message
	 */
	public InstructionDoesNotHaveFieldException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public InstructionDoesNotHaveFieldException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InstructionDoesNotHaveFieldException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
