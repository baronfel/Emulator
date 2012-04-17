package model;

import interfaces.IALU;
import interfaces.IInstruction;
import interfaces.IMemoryAccess;
import interfaces.IWriteBack;
import interfaces.ProcStatus;

import java.util.ArrayList;
import java.util.List;

public class WriteBack extends AbstractModel implements IWriteBack {
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
				registers.setRegister(alu.getPostALUDestReg(true), alu.getPostALUOpResult());
				if (alu.getPostALUDestReg2(false) >= 0)
					registers.setRegister(alu.getPostALUDestReg2(true), alu.getPostALUOpResult2());
			}
		}

		// check the MEM unit for completed instructions
		if (memUnit.getPostMEMSequenceNum(false) >= 0) {
			progSeqNum = memUnit.getPostMEMSequenceNum(true);
			registers.setRegister(memUnit.getPostMEMDestReg(), memUnit.getPostMEMOpResult());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.ICoreComponent#GetStatus()
	 */
	@Override
	public ProcStatus GetStatus() {
		ProcStatus status = ProcStatus.Inactive;
		for (IALU alu : aluList) {
			if (alu.GetStatus() == ProcStatus.Active) {
				status = ProcStatus.Active;
			}
		}

		if (memUnit.GetStatus() == ProcStatus.Active) {
			status = ProcStatus.Active;
		}
		return status;
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
		return new ArrayList<IInstruction>();
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
