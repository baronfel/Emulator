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
	private List<IMemoryAccess> memUnits;

	public WriteBack(List<IMemoryAccess> memories, List<IALU> alus,
			Registry regs) {
		memUnits = memories;
		aluList = alus;
		registers = regs;
	}

	public void processClockCycle() {
		int progSeqNum;

		// run through ALU array and process completed instructions
		for (IALU alu : aluList) {

			if (alu.getPostALUSequenceNum(false) >= 0) {
				progSeqNum = alu.getPostALUSequenceNum(true);
				int dstReg = alu.getPostALUDestReg(true);
				registers.setRegister(dstReg,
						alu.getPostALUOpResult());
				if (alu.getPostALUDestReg2(false) >= 0)
					registers.setRegister(alu.getPostALUDestReg2(true),
							alu.getPostALUOpResult2());
				registers.setRegisterToNotInUse(dstReg);
			}
		}

		// check the MEM unit for completed instructions
		for (IMemoryAccess mem : memUnits) {

			if (mem.getPostMEMSequenceNum(false) >= 0) {
				progSeqNum = mem.getPostMEMSequenceNum(true);
				int dstReg = mem.getPostMEMDestReg();
				registers.setRegister(dstReg,
						mem.getPostMEMOpResult());
				if (mem.getPostMEMDestReg2(false) >= 0)
					registers.setRegister(mem.getPostMEMDestReg2(true),
							mem.getPostMEMOpResult());
				registers.setRegisterToNotInUse(dstReg);
			}
		}
	}

	/*
	 * //RELIC FROM WHEN WE ONLY HAD ONE MEM if
	 * (memUnit.getPostMEMSequenceNum(false) >= 0) { progSeqNum =
	 * memUnit.getPostMEMSequenceNum(true);
	 * registers.setRegister(memUnit.getPostMEMDestReg(),
	 * memUnit.getPostMEMOpResult()); } }
	 */
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

		for (IMemoryAccess mem : memUnits) {
			if (mem.GetStatus() == ProcStatus.Active) {
				status = ProcStatus.Active;
			}
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
