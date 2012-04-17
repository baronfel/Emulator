package model;

import interfaces.IALU;
import interfaces.IInstruction;
import interfaces.ProcStatus;

//import java.awt.Event;
import java.util.List;
import java.util.Map;

public class ALU extends AbstractModel implements IALU{
	private int bufferSize;
	private int aluNumber; // which ALU within the core is it
	private int stallCycles; // stalls the ALU for the number of cycles
	// specified
	private int cyclesProcessed; // for testing purposes
	// private boolean isPreBuffFull;
	// private int currentInstrIndex; //holds the index in the preALUBuffer of
	// the current instruction
	private PreALUBufferEntry[] preALUBuffer;
	private PostALUBufferEntry postALUBuffer;
	private PreALUBufferEntry currentInstruction;
	private Map<String, Integer> cycleCountByOpname;

	/**
	 * Class constructor that takes the alu number within the core and the
	 * pre-ALU buffer size as parameters
	 */
	public ALU(int alu, int buffSize, Map<String, Integer> cycleMap) {
		aluNumber = alu;
		stallCycles = 0;
		cyclesProcessed = 0;
		bufferSize = buffSize;
		// isPreBuffFull = false;
		// currentInstrIndex = 0;
		preALUBuffer = new PreALUBufferEntry[bufferSize];
		for (int i = 0; i < bufferSize; i++) {
			preALUBuffer[i] = new PreALUBufferEntry();
		}
		postALUBuffer = new PostALUBufferEntry();
		currentInstruction = new PreALUBufferEntry();
		cycleCountByOpname = cycleMap;
	}

	/**
	 * Class constructor for testing the WriteBack unit. Creates a skeleton ALU
	 * with only the post-ALU buffer set
	 */
	public ALU(int seq, int reg, int value) {
		postALUBuffer = new PostALUBufferEntry();
		postALUBuffer.progSequenceNumber = seq;
		postALUBuffer.destinationRegister = reg;
		postALUBuffer.opResult = value;
	}

	/**
	 * Method to process a clock cycle. If stallCycles is greater than 0, this
	 * method will just decrement the stallCycle variable then wait for the next
	 * clock cycle. If 0, this method will perform the operation of the current
	 * instruction and deposit the result in the post ALU buffer
	 */
	public void processClockCycle() {
		// if there is not an instruction being processed, get the next one from
		// the buffer
		if (currentInstruction.opName == ""
				|| currentInstruction.opName == "nop") {
			currentInstruction = getNextInstruction();
			stallCycles = currentInstruction.numCycles - 1;
		}

		if (stallCycles == 0) {
			// go ahead and process the instruction
			int[] operationResult = {0,0};

			switch (currentInstruction.opName) {
			case "mul":
				operationResult = mult();
				break;
			case "div":
				operationResult = div();
				break;
			case "add":
				operationResult = add();
				break;
			case "sub":
				operationResult = sub();
				break;
			case "addi":
				operationResult = add();
				break;
			case "addiu":
				operationResult = add();
				break;
			case "la":
				operationResult[0] = currentInstruction.op2Value;
				break;
			case "li":
				operationResult[0] = currentInstruction.op2Value;
				break;
			case "sll":
				operationResult = sll();
				break;
			case "srl":
				operationResult = srl();
				break;
			case "and":
				operationResult = and();
				break;
			case "or":
				operationResult = or();
				break;
			case "nor":
				operationResult = nor();
				break;
			case "slt":
				operationResult = slt();
				break;
			case "slti":
				operationResult = slt();
				break;
			case "sltu":
				operationResult = slt();
				break;
			case "sltiu":
				operationResult = slt();
				break;

			default:
				break;
			}

			// add the result to the post ALU buffer
			addToPostALU(operationResult);

			// clear the current instruction
			currentInstruction = new PreALUBufferEntry();

		} else {
			// just decrement the stallCycles and wait for the next clock cycle
			stallCycles--;
		}

		cyclesProcessed++;

	} // end processClockCycle

	/**
	 * Method to perform an slt operation
	 */
	private int[] slt() {
		int[] ret = {0,0};
		if (currentInstruction.op1Value < currentInstruction.op2Value)
			ret[0] = 1;
		else
			ret[0] = 0;
		
		return (ret);
	}

	/**
	 * Method to perform a nor operation
	 */
	private int[] nor() {
		int[] ret = {0,0};
		ret[0] = ~(currentInstruction.op1Value | currentInstruction.op2Value);
		return (ret);
	}

	/**
	 * Method to perform an or operation
	 */
	private int[] or() {
		int[] ret = {0,0};
		ret[0] = currentInstruction.op1Value | currentInstruction.op2Value;
		return (ret);
	}

	/**
	 * Method to perform an and operation
	 */
	private int[] and() {
		int[] ret = {0,0};
		ret[0] = currentInstruction.op1Value & currentInstruction.op2Value;
		return (ret);
	}

	/**
	 * Method to perform a shift right logical operation
	 */
	private int[] srl() {
		int[] ret = {0,0};
		ret[0] = currentInstruction.op1Value >> currentInstruction.op2Value;
		return (ret);
	}

	/**
	 * Method to perform a shift left logical operation
	 */
	private int[] sll() {
		int[] ret = {0,0};
		ret[0] = currentInstruction.op1Value << currentInstruction.op2Value;
		return (ret);
	}

	/**
	 * Method to perform an addition operation
	 */
	private int[] add() {
		int[] ret = {0,0};
		ret[0] = currentInstruction.op1Value + currentInstruction.op2Value;
		return (ret);
	}

	/**
	 * Method to perform a subtraction operation
	 */
	private int[] sub() {
		int[] ret = {0,0};
		ret[0] = currentInstruction.op1Value - currentInstruction.op2Value;
		return (ret);
	}

	/**
	 * Method to perform a division operation
	 */
	private int[] div() {
		int[] ret = {0,0};
		if (currentInstruction.op2Value == 0) {
			// return an error code?
		} else {
			ret[0] = (int) (currentInstruction.op1Value / currentInstruction.op2Value);
			ret[1] = (currentInstruction.op1Value % currentInstruction.op2Value);
		}
		currentInstruction.destinationRegister = 33;	//Lo register for the quotient
		currentInstruction.destinationRegister2 = 32;	//Hi register for the remainder
		
		return (ret);
	}

	/**
	 * Method to perform a multiplication operation
	 */
	private int[] mult() {
		int[] ret = {0,0};
		ret[0] = currentInstruction.op1Value * currentInstruction.op2Value;
		return (ret);
	}

	/**
	 * Method to add an instruction to the pre-ALU buffer. Returns 0 if the
	 * instruction was successfully added, otherwise returns -1 if buffer is
	 * full
	 */
	public int addToPreALU(String opName, int seq, int op1, int op2, int dest) {
		for (int i = 0; i < preALUBuffer.length; i++) {
			if (preALUBuffer[i].opName == "") { // then add the new instruction
				// here
				preALUBuffer[i].opName = opName;
				preALUBuffer[i].progSequenceNumber = seq;
				preALUBuffer[i].op1Value = op1;
				preALUBuffer[i].op2Value = op2;
				preALUBuffer[i].destinationRegister = dest;
				preALUBuffer[i].numCycles = cycleCountByOpname.get(opName.toLowerCase());
				return 0;
			}
		}
		return -1;
	}

	public int getAmountInPreALU() {
		int count = 0;
		for (int i = 0; i < preALUBuffer.length; i++) {
			if (!(preALUBuffer[i].opName == ""))
				count++;
		}
		return count;
	}

	/**
	 * Method to get the next instruction from the pre-ALU buffer
	 */
	private PreALUBufferEntry getNextInstruction() {
		// take the first entry from the buffer
		PreALUBufferEntry tmpInstruction = new PreALUBufferEntry(
				preALUBuffer[0]);

		// move the remaining buffer entries forward
		for (int i = 0; i < preALUBuffer.length - 1; i++) {
			preALUBuffer[i] = preALUBuffer[i + 1];
		}
		preALUBuffer[preALUBuffer.length - 1] = new PreALUBufferEntry();

		return tmpInstruction;
	}

	/**
	 * Method to add a completed instruction to the post-ALU buffer. Return 0 if
	 * successful, return -1 if the buffer is full
	 */
	private int addToPostALU(int[] result) {
		if (postALUBuffer.progSequenceNumber != 0) {
			postALUBuffer.progSequenceNumber = currentInstruction.progSequenceNumber;
			postALUBuffer.destinationRegister = currentInstruction.destinationRegister;
			postALUBuffer.destinationRegister2 = currentInstruction.destinationRegister2;
			postALUBuffer.opResult = result[0];		//goes to destRegister
			postALUBuffer.opResult2 = result[1];	//goes to destRegister2
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * Method to get the program sequence number from the post-ALU buffer.
	 * Returns the program sequence number of the most recently completed
	 * instruction or if the buffer is empty the method returns -1. The clear
	 * parameter set to true will empty the buffer, false will leave the value
	 * in the buffer Will be used by the Writeback unit
	 */
	public int getPostALUSequenceNum(boolean clear) {
		int tmpSeqNum = postALUBuffer.progSequenceNumber;

		if (tmpSeqNum >= 0 && clear == true) {
			postALUBuffer.progSequenceNumber = -1;
		}

		return tmpSeqNum;
	}

	/**
	 * Method to get the destination register number from the post-ALU buffer.
	 * Will be used by the Writeback unit
	 */
	public int getPostALUDestReg(boolean clear) {
		int tmpDestReg = postALUBuffer.destinationRegister;

		if (tmpDestReg >= 0 && clear == true) {
			postALUBuffer.destinationRegister = -1;
		}

		return tmpDestReg;
	}
	
	/**
	 * Method to get the 2nd destination register number from the post-ALU buffer.
	 * Stores the "Hi" (remainder) value of a div instruction. Will be used by the Writeback unit.
	 */
	public int getPostALUDestReg2(boolean clear) {
		int tmpDestReg = postALUBuffer.destinationRegister2;

		if (tmpDestReg >= 0 && clear == true) {
			postALUBuffer.destinationRegister2 = -1;
		}

		return tmpDestReg;
	}

	/**
	 * Method to get the operation result from the post-ALU buffer. Will be used
	 * by the Writeback unit
	 */
	public int getPostALUOpResult() {
		return postALUBuffer.opResult;
	}

	/**
	 * Method to get the operation result from the post-ALU buffer. Will be used
	 * by the Writeback unit
	 */
	public int getPostALUOpResult2() {
		return postALUBuffer.opResult2;
	}
	
	/**
	 * Method to get the ALU number. Used only for class testing.
	 */
	public int getALUNumber() {
		return this.aluNumber;
	}

	/**
	 * Method to get the buffer size. Used only for class testing.
	 */
	public int getBufferSize() {
		return this.bufferSize;
	}

	/**
	 * Methods to get/set contents of the ALU buffers. Used only for class
	 * testing.
	 */
	public String getPreALUOpName(int index) {
		return this.preALUBuffer[index].opName;
	}

	public int getPreALUProgSeqNum(int index) {
		return this.preALUBuffer[index].progSequenceNumber;
	}

	public int getPreALUOp1Val(int index) {
		return this.preALUBuffer[index].op1Value;
	}

	public int getPreALUOp2Val(int index) {
		return this.preALUBuffer[index].op2Value;
	}

	public int getPreALUDestReg(int index) {
		return this.preALUBuffer[index].destinationRegister;
	}

	public int getPreALUNumCycles(int index) {
		return this.preALUBuffer[index].numCycles;
	}

	public String getCurrentInstrOpName() {
		return this.currentInstruction.opName;
	}

	public int getCyclesProcessed() {
		return this.cyclesProcessed;
	}

	/**
	 * Class to store pre-ALU instructions
	 */
	private class PreALUBufferEntry {
		private String opName;
		private int progSequenceNumber;
		private int op1Value; // first operand value (rs)
		private int op2Value; // second operand value (either rt or immediate)
		private int destinationRegister; // destination register
		private int destinationRegister2; // 2nd destination register if needed
		private int numCycles; // the number of clock cycles this instruction
								// takes

		/**
		 * Class constructor
		 */
		private PreALUBufferEntry() {
			opName = "";
			progSequenceNumber = -1;
			op1Value = 0;
			op2Value = 0;
			destinationRegister = 0;
			destinationRegister2 = 0;
			numCycles = 0;
		}

		/**
		 * Class copy constructor
		 */
		private PreALUBufferEntry(PreALUBufferEntry entry) {
			opName = entry.opName;
			progSequenceNumber = entry.progSequenceNumber;
			op1Value = entry.op1Value;
			op2Value = entry.op2Value;
			destinationRegister = entry.destinationRegister;
			destinationRegister2 = entry.destinationRegister2;
			numCycles = entry.numCycles;
		}
	}

	/**
	 * Class to store post-ALU instructions
	 */
	private class PostALUBufferEntry {
		private int progSequenceNumber;
		private int destinationRegister; // register number to store the result (Lo for div quotient)
		private int destinationRegister2; // register of Hi register (for div instruction remainder)
		private int opResult;
		private int opResult2;         //for the remainder in a div instruction

		private PostALUBufferEntry() {
			progSequenceNumber = -1;
			destinationRegister = 0;
			destinationRegister2 = -1;
			opResult = 0;
			opResult2 = 0;
		}
	}

	@Override
	public ProcStatus GetStatus() {
		if (preALUBuffer.length == 0 && postALUBuffer == null
				&& currentInstruction == null) {
			return ProcStatus.Inactive;
		} else {
			return ProcStatus.Active;
		}
	}

	@Override
	public void Cycle() {
		processClockCycle();
	}

	@Override
	public List<IInstruction> CurrentInstructions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Add(int aIn_RD, int aIn_RS, int aIn_RT, int aIn_Immediate) {
		// TODO Auto-generated method stub
	}

} // end of class ALU
