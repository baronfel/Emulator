/**
 * 
 */
package model;

import interfaces.IInstruction;
import interfaces.IMemoryAccess;
import interfaces.ProcStatus;

import java.util.List;
import java.util.Map;

public class MemoryAccess extends AbstractModel implements IMemoryAccess {

	private int bufferSize;
	private int stallCycles; // stalls the MEM unit for the number of cycles
								// specified
	private int cyclesProcessed; // for testing purposes
	private PreMEMBufferEntry[] preMEMBuffer;
	private PostMEMBufferEntry postMEMBuffer;
	private PreMEMBufferEntry currentInstruction;
	private Memory memList;
	private Map<String, Integer> cycleCountByOpname;


	/**
	 * Class constructor takes the processor memory and the pre-MEM buffer size
	 * as parameters
	 * 
	 */
	public MemoryAccess(Memory mem, int buffSize, Map<String, Integer> cycleMap) {
		stallCycles = 0;
		bufferSize = buffSize;
		memList = mem;
		preMEMBuffer = new PreMEMBufferEntry[bufferSize];
		for (int i = 0; i < bufferSize; i++) {
			preMEMBuffer[i] = new PreMEMBufferEntry();
		}
		postMEMBuffer = new PostMEMBufferEntry();
		currentInstruction = new PreMEMBufferEntry();
		cycleCountByOpname = cycleMap;
	}

	/**
	 * Class constructor for testing the WriteBack unit. Creates a skeleton
	 * MemoryAccess with only the post-MEM buffer set
	 */
	public MemoryAccess(int seq, int reg, int value) {
		postMEMBuffer = new PostMEMBufferEntry();
		postMEMBuffer.progSequenceNumber = seq;
		postMEMBuffer.destinationRegister = reg;
		postMEMBuffer.opResult = value;
	}

	/**
	 * Method to process a clock cycle. If stallCycles is greater than 0, this
	 * method will just decrement the stallCycle variable then wait for the next
	 * clock cycle. If 0, this method will perform the operation of the current
	 * instruction
	 */
	public void processClockCycle() {
		// if there is not an instruction being processed, get the next one from
		// the buffer
		if (currentInstruction.opName == "") {
			currentInstruction = getNextInstruction();
			stallCycles = currentInstruction.numCycles - 1;
		}

		if (stallCycles == 0) {
			// go ahead and process the instruction

			switch (currentInstruction.opName) {
			case "lw":
				addToPostMEM(memList.getValueAt(currentInstruction.rs
						+ currentInstruction.immediate));
				break;
			case "sw":
				memList.setValueAt(
						(currentInstruction.rs + currentInstruction.immediate),
						(Integer) currentInstruction.rt);
				break;
			case "lb":
				addToPostMEM(memList.getValueAt(currentInstruction.rs
						+ currentInstruction.immediate));
				break;
			case "sb":
				memList.setValueAt(
						(currentInstruction.rs + currentInstruction.immediate),
						(Integer) currentInstruction.rt);
				break;

			default:
				break;
			}

			// clear the current instruction
			currentInstruction = new PreMEMBufferEntry();

		} else {
			// just decrement the stallCycles and wait for the next clock cycle
			stallCycles--;
		}

		cyclesProcessed++;

	} // end processClockCycle

	/**
	 * Method to add an instruction to the pre-MEM buffer. Returns 0 if the
	 * instruction was successfully added, otherwise returns -1 if buffer is
	 * full
	 */
	public int addToPreMEM(String opName, int seq, int rs, int rt, int imm,
			int cycles) {
		for (int i = 0; i < preMEMBuffer.length; i++) {
			if (preMEMBuffer[i].opName == "") { // then add the new instruction
												// here
				preMEMBuffer[i].opName = opName;
				preMEMBuffer[i].progSequenceNumber = seq;
				preMEMBuffer[i].rs = rs;
				preMEMBuffer[i].rt = rt;
				preMEMBuffer[i].immediate = imm;
				preMEMBuffer[i].numCycles = cycleCountByOpname.get(opName);
				return 0;
			}
		}
		return -1;
	}

	/**
	 * Method to get the next instruction from the pre-MEM buffer
	 */
	private PreMEMBufferEntry getNextInstruction() {
		// take the first entry from the buffer
		PreMEMBufferEntry tmpInstruction = new PreMEMBufferEntry(
				preMEMBuffer[0]);

		// move the remaining buffer entries forward
		for (int i = 0; i < preMEMBuffer.length - 1; i++) {
			preMEMBuffer[i] = preMEMBuffer[i + 1];
		}
		preMEMBuffer[preMEMBuffer.length - 1] = new PreMEMBufferEntry();

		return tmpInstruction;
	}

	/**
	 * Method to add a completed instruction to the post-MEM buffer. Return 0 if
	 * successful, return -1 if the buffer is full
	 */
	private int addToPostMEM(int memValue) {
		if (postMEMBuffer.progSequenceNumber != 0) {
			postMEMBuffer.progSequenceNumber = currentInstruction.progSequenceNumber;
			postMEMBuffer.destinationRegister = currentInstruction.rt;
			postMEMBuffer.opResult = memValue;
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * Method to get the program sequence number from the post-MEM buffer.
	 * Returns the program sequence number of the most recently completed
	 * instruction or if the buffer is empty the method returns -1. The clear
	 * parameter set to true will empty the buffer, false will leave the value
	 * in the buffer Will be used by the Writeback unit
	 */
	public int getPostMEMSequenceNum(boolean clear) {
		int tmpSeqNum = postMEMBuffer.progSequenceNumber;

		if (tmpSeqNum >= 0 && clear == true) {
			postMEMBuffer.progSequenceNumber = -1;
		}

		return tmpSeqNum;
	}

	/**
	 * Method to get the destination register number from the post-MEM buffer.
	 * Will be used by the Writeback unit
	 */
	public int getPostMEMDestReg() {
		return postMEMBuffer.destinationRegister;
	}

	/**
	 * Method to get the operation result from the post-MEM buffer. Will be used
	 * by the Writeback unit
	 */
	public int getPostMEMOpResult() {
		return postMEMBuffer.opResult;
	}

	/**
	 * Method to get the buffer size. Used only for class testing.
	 */
	public int getBufferSize() {
		return this.bufferSize;
	}

	/**
	 * Methods to return contents of the pre MEM buffer. Used only for class
	 * testing.
	 */
	public String getPreMEMOpName(int index) {
		return this.preMEMBuffer[index].opName;
	}

	public int getPreMEMProgSeqNum(int index) {
		return this.preMEMBuffer[index].progSequenceNumber;
	}

	public int getPreMEMrsVal(int index) {
		return this.preMEMBuffer[index].rs;
	}

	public int getPreMEMrtVal(int index) {
		return this.preMEMBuffer[index].rt;
	}

	public int getPreMEMimmVal(int index) {
		return this.preMEMBuffer[index].immediate;
	}

	public int getPreMEMNumCycles(int index) {
		return this.preMEMBuffer[index].numCycles;
	}

	public String getCurrentInstrOpName() {
		return this.currentInstruction.opName;
	}

	public int getCyclesProcessed() {
		return this.cyclesProcessed;
	}

	/**
	 * Class to store pre-MEM instructions
	 */
	private class PreMEMBufferEntry {
		private String opName;
		private int progSequenceNumber;
		private int rs; // for loads, the register number where the data value
						// will be loaded to
						// for stores, the actual value to be stored (passed
						// from the issue unit)
		private int rt; // the base value for the memory address
		private int immediate; // memory offset
		private int numCycles; // the number of clock cycles this instruction
								// takes

		/**
		 * Class constructor
		 */
		private PreMEMBufferEntry() {
			opName = "";
			progSequenceNumber = -1;
			rs = 0;
			rt = 0;
			immediate = 0;
			numCycles = 0;
		}

		/**
		 * Class copy constructor
		 */
		private PreMEMBufferEntry(PreMEMBufferEntry entry) {
			opName = entry.opName;
			progSequenceNumber = entry.progSequenceNumber;
			rs = entry.rs;
			rt = entry.rt;
			immediate = entry.immediate;
			numCycles = entry.numCycles;
		}
	}

	/**
	 * Class to store post-MEM instructions
	 */
	private class PostMEMBufferEntry {
		private int progSequenceNumber;
		private int destinationRegister; // register number to store the result
		private int opResult;

		private PostMEMBufferEntry() {
			progSequenceNumber = -1;
			destinationRegister = 0;
			opResult = 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.ICoreComponent#GetStatus()
	 */
	@Override
	public ProcStatus GetStatus() {
		if (preMEMBuffer.length == 0 && postMEMBuffer == null
				&& currentInstruction == null) {
			return ProcStatus.Inactive;
		} else
			return ProcStatus.Active;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.ICoreComponent#Cycle(int)
	 */
	@Override
	public void Cycle() {
		processClockCycle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.ICoreComponent#CurrentInstructions()
	 */
	@Override
	public List<IInstruction> CurrentInstructions() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.IMemoryAccess#LoadWord(int, int)
	 */
	@Override
	public void LoadWord(int aIn_RD, int aIn_memaddr) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.IMemoryAccess#StoreWord(int, int)
	 */
	@Override
	public void StoreWord(int aIn_RS, int aIn_memaddr) {
		// TODO Auto-generated method stub

	}


	@Override
	public int addToPreMEM(String opName, int seq, int rs, int rt, int cycles) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAmountInPreMEM() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMEMNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

}
