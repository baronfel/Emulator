package model;

import interfaces.IALU;
import interfaces.IInstruction;
import interfaces.ProcStatus;

import java.awt.Event;
import java.util.List;
import java.util.Map;

public class ALU implements IALU {
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
			// String tmpStr = currentInstruction.opName;
			int operationResult = 0;

			switch (currentInstruction.opName) {
			case "mul":
				operationResult = mult(currentInstruction.op1Value,
						currentInstruction.op2Value);
				break;
			case "div":
				operationResult = div(currentInstruction.op1Value,
						currentInstruction.op2Value);
				break;
			case "add":
				operationResult = add(currentInstruction.op1Value,
						currentInstruction.op2Value);
				break;
			case "sub":
				operationResult = sub(currentInstruction.op1Value,
						currentInstruction.op2Value);
				break;
			case "addi":
				operationResult = add(currentInstruction.op1Value,
						currentInstruction.op2Value);
				break;
			case "addiu":
				operationResult = add(currentInstruction.op1Value,
						currentInstruction.op2Value);
				break;
			case "la":
				operationResult = currentInstruction.op2Value;
				break;
			case "li":
				operationResult = currentInstruction.op2Value;
				break;
			case "sll":
				operationResult = sll(currentInstruction.op1Value,
						currentInstruction.op2Value);
				break;
			case "srl":
				operationResult = srl(currentInstruction.op1Value,
						currentInstruction.op2Value);
				break;
			case "and":
				operationResult = and(currentInstruction.op1Value,
						currentInstruction.op2Value);
				break;
			case "or":
				operationResult = or(currentInstruction.op1Value,
						currentInstruction.op2Value);
				break;
			case "nor":
				operationResult = nor(currentInstruction.op1Value,
						currentInstruction.op2Value);
				break;
			case "beq":
				// TODO- how will this work?
				break;
			case "bne":
				// TODO- how will this work?
				break;
			case "slt":
				operationResult = slt(currentInstruction.op1Value,
						currentInstruction.op2Value);
				break;
			case "slti":
				operationResult = slt(currentInstruction.op1Value,
						currentInstruction.op2Value);
				break;
			case "sltu":
				operationResult = slt(currentInstruction.op1Value,
						currentInstruction.op2Value);
				break;
			case "sltiu":
				operationResult = slt(currentInstruction.op1Value,
						currentInstruction.op2Value);
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
	 * Method to perform an or operation
	 */
	private int slt(int op1, int op2) {
		if (op1 < op2)
			return 1;
		else
			return 0;
	}

	/**
	 * Method to perform an or operation
	 */
	private int nor(int op1, int op2) {
		return ~(op1 | op2);
	}

	/**
	 * Method to perform an or operation
	 */
	private int or(int op1, int op2) {
		return (op1 | op2);
	}

	/**
	 * Method to perform an and operation
	 */
	private int and(int op1, int op2) {
		return (op1 & op2);
	}

	/**
	 * Method to perform a shift right logical operation
	 */
	private int srl(int op1, int op2) {
		return (op1 >> op2);
	}

	/**
	 * Method to perform a shift left logical operation
	 */
	private int sll(int op1, int op2) {
		return (op1 << op2);
	}

	/**
	 * Method to perform an addition operation
	 */
	private int add(int op1, int op2) {
		return (op1 + op2);
	}

	/**
	 * Method to perform a subtraction operation
	 */
	private int sub(int op1, int op2) {
		return (op1 - op2);
	}

	/**
	 * Method to perform a division operation
	 */
	private int div(int op1, int op2) {
		if (op2 == 0) {
			// return an error code

			// add code
			return -1;
		} else {
			return (int) (op1 / op2);
		}
	}

	/**
	 * Method to perform a multiplication operation
	 */
	private int mult(int op1, int op2) {
		return (op1 * op2);
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
				preALUBuffer[i].numCycles = cycleCountByOpname.get(opName);
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
	private int addToPostALU(int result) {
		if (postALUBuffer.progSequenceNumber != 0) {
			postALUBuffer.progSequenceNumber = currentInstruction.progSequenceNumber;
			postALUBuffer.destinationRegister = currentInstruction.destinationRegister;
			postALUBuffer.opResult = result;
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
	public int getPostALUDestReg() {
		return postALUBuffer.destinationRegister;
	}

	/**
	 * Method to get the operation result from the post-ALU buffer. Will be used
	 * by the Writeback unit
	 */
	public int getPostALUOpResult() {
		return postALUBuffer.opResult;
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
			numCycles = entry.numCycles;
		}
	}

	/**
	 * Class to store post-ALU instructions
	 */
	private class PostALUBufferEntry {
		private int progSequenceNumber;
		private int destinationRegister; // register number to store the result
		private int opResult;

		private PostALUBufferEntry() {
			progSequenceNumber = -1;
			destinationRegister = 0;
			opResult = 0;
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
