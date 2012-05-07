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
	 * The default form of this exception, with a default message.
	 * Changed to have a default message.
	 */
	public InstructionDoesNotHaveFieldException() {
		// TODO Auto-generated constructor stub
		super("This MIPS instruction does not have the requested field.");
	}

	/**
	 * A form of this exception with a customizable message.
	 * @param message The message you want this instruction to carry.
	 */
	public InstructionDoesNotHaveFieldException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * A form of this exception with a customizable cause;
	 * @param cause The cause you want this exception to carry.
	 */
	public InstructionDoesNotHaveFieldException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * A form of this exception with a customizable message and cause.
	 * @param message The message you want this instruction to carry.
	 * @param cause The cause you want this exception to carry.
	 */
	public InstructionDoesNotHaveFieldException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
