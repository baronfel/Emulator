package model;

import java.awt.Event;
import java.util.List;

import interfaces.IALU;
import interfaces.IInstruction;
import interfaces.IWriteBack;
import interfaces.IMemoryAccess;

public class WriteBack implements IWriteBack {
	private Registry registers;
	private List<IALU> aluList;
	private IMemoryAccess memUnit;

	public WriteBack(IMemoryAccess memAccess, List<IALU> alus, Registry regs) {
		memUnit = memAccess;
		aluList = alus;
		registers = regs;
	}

	public void processClockCycle() {
		int progSeqNum;

		// run through ALU array and process completed instructions
		for (IALU alu : aluList) {

			if (alu.getPostALUSequenceNum(false) >= 0) {
				progSeqNum = alu.getPostALUSequenceNum(true);
				registers.setRegister(alu.getPostALUDestReg(),
						(int) alu.getPostALUOpResult());

				// TODO add issue unit notification with progSeqNum
			}
		}

		// check the MEM unit for completed instructions
		if (memUnit.getPostMEMSequenceNum(false) >= 0) {
			progSeqNum = memUnit.getPostMEMSequenceNum(true);
			registers.setRegister(memUnit.getPostMEMDestReg(),
					(int) memUnit.getPostMEMOpResult());

			// TODO add issue unit notification with progSeqNum
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.ICoreComponent#GetStatus()
	 */
	@Override
	public String GetStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.ICoreComponent#Cycle(int)
	 */
	@Override
	public void Cycle() {
		// TODO Auto-generated method stub

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
	 * @see interfaces.ICoreComponent#PropertyChanged(java.lang.Object)
	 */
	@Override
	public Event PropertyChanged(Object aIn_propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.IWriteBack#WriteToRegister(int, int)
	 */
	@Override
	public void WriteToRegister(int aIn_value, int aIn_RD) {
		// TODO Auto-generated method stub

	}

}
