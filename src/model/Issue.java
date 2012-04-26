/**
 * 	This class represents the issue step of the pipeline. For now, it just converts from the instruction list
 *  to the pre-ALU buffer or pre-MEM buffer. It decides which ALU or MEM to use based on which one has less to do.
 *  Later, it might do hazards too!
 */
package model;

import interfaces.IALU;
import interfaces.IInstruction;
import interfaces.IIssueUnit;
import interfaces.IMemoryAccess;
import interfaces.ProcStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Bob
 * 
 */
public class Issue extends AbstractModel implements IIssueUnit {
	private List<IALU> alus;
	private Registry registry;
	private int buffSize = 4;
	private ArrayBlockingQueue<IInstruction> PreIssueBuffer = new ArrayBlockingQueue<IInstruction>(
			buffSize);
	private int numInPreIssue = 0;
	private List<IMemoryAccess> mems;
	private int _noops;

	public Issue(List<IALU> alus, List<IMemoryAccess> mems, Registry registry) {
		this.alus = alus;
		this.registry = registry;
		this.mems = mems;
		_noops = 0;
	}

	/**
	 * This method issues an instruction from the list of waiting instructions
	 * to the ALU or MEM.
	 */
	public void IssueInstructions(IInstruction instruction) {
		if (instruction == null) {
			_noops++;
			return;
		}
		if (registry.isRegisterInUse(instruction.getRS())
				|| registry.isRegisterInUse(instruction.getRD())) {
			_noops++;
			return;
		}
		if (instruction.getType() == InstructionType.R)
			if (registry.isRegisterInUse(instruction.getRT())) {
				_noops++;
				return;
			}
		int op1 = 0;
		int op2 = 0;
		int dst = 0;
		int val = 0;
		int reg = 0;
		switch (instruction.getOpcode().toLowerCase()) {
		case "lw":
			reg = instruction.getRD();
			dst = registry.getValue(instruction.getRS())
					+ instruction.getImmediate();
			GetFirstAvailableMEM().addToPreMEM(instruction.getOpcode(),
					instruction.getSeqNum(), dst, reg);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "addi":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = instruction.getImmediate();
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "addiu":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = instruction.getImmediate();
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "andi":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = instruction.getImmediate();
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "ori":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = instruction.getImmediate();
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "sw":
			val = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD())
					+ instruction.getImmediate();
			GetFirstAvailableMEM().addToPreMEM(instruction.getOpcode(),
					instruction.getSeqNum(), dst, val);
			break;
		case "lb":
			reg = instruction.getRD();
			dst = registry.getValue(instruction.getRS())
					+ instruction.getImmediate();
			GetFirstAvailableMEM().addToPreMEM(instruction.getOpcode(),
					instruction.getSeqNum(), dst, reg);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "la":
			// NOT SURE IF HERE OR WRITE BACK
			// registry.setRegister(instruction.getRD(),
			// instruction.getImmediate());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), instruction.getImmediate(), 0,
					instruction.getRD());
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "li":
			// NOT SURE IF HERE OR WRITE BACK
			// registry.setRegister(instruction.getRD(),
			// instruction.getImmediate());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), instruction.getImmediate(), 0,
					instruction.getRD());
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "sb":
			val = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD())
					+ instruction.getImmediate();
			GetFirstAvailableMEM().addToPreMEM(instruction.getOpcode(),
					instruction.getSeqNum(), dst, val);
			break;
		case "mul":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "add":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "sub":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "sll":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = registry.getValue(instruction.getSHAMT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "srl":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = registry.getValue(instruction.getSHAMT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "nop":
			op1 = 0;
			dst = 0;
			op2 = 0;
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		case "and":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "or":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "slt":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "slti":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = instruction.getImmediate();
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "sltu":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "sltiu":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = instruction.getImmediate();
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "nor":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "div":
			op1 = registry.getValue(instruction.getRS());
			dst = instruction.getRD();
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		case "move":
			dst = instruction.getRD();
			op1 = registry.getValue(instruction.getRS());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, 0, dst);
			registry.setRegisterToInUse(instruction.getRD());
			break;
		default:
			_noops++;
			break;
		}
		PreIssueBuffer.poll();
		numInPreIssue--;
		notifyChanged(new ModelEvent(this, new Random().nextInt(),
				"Issued an instruction", 0));
		// GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
		// instruction.getSeqNum(), op1, op2, dst);
	}

	public IMemoryAccess GetFirstAvailableMEM() {
		for (int i = 0; i < mems.size(); i++)

			if (mems.get(i).GetStatus() == ProcStatus.Inactive)
				return mems.get(i);
		int PreMEMQueueSize = Integer.MAX_VALUE;
		IMemoryAccess memToUse = mems.get(0);
		for (int i = 1; i < mems.size(); i++) {
			if ((mems.get(i)).getAmountInPreMEM() < PreMEMQueueSize) {
				PreMEMQueueSize = (int) (mems.get(i)).getAmountInPreMEM();
				memToUse = mems.get(i);
			}
		}
		return memToUse;
	}

	public IALU GetFirstAvailableALU() {
		for (int i = 0; i < alus.size(); i++)
			if (alus.get(i).GetStatus() == ProcStatus.Inactive)
				return alus.get(i);
		IALU aluToUse = alus.get(0);
		int PreALUQueueSize = alus.get(0).getAmountInPreALU();
		for (int i = 1; i < alus.size(); i++) {
			if ((alus.get(i)).getAmountInPreALU() < PreALUQueueSize) {
				PreALUQueueSize = (int) (alus.get(i)).getAmountInPreALU();
				aluToUse = alus.get(i);
			}
		}
		return aluToUse;
	}

	@Override
	public ProcStatus GetStatus() {
		if (PreIssueBuffer.isEmpty()) {
			return ProcStatus.Inactive;
		} else
			return ProcStatus.Active;
	}

	@Override
	public void Cycle() {
		IssueInstructions(PreIssueBuffer.peek());
	}

	@Override
	public List<IInstruction> CurrentInstructions() {
		if (numInPreIssue > 0)
			return new ArrayList<IInstruction>(PreIssueBuffer);
		return new ArrayList<IInstruction>();
	}

	public boolean addToPreIssue(IInstruction instruction) {
		if (PreIssueBuffer.offer(instruction)) {
			numInPreIssue++;
			return true;
		}
		return false;
	}

	public int getNumInPreIssue() {
		return numInPreIssue;
	}

	@Override
	public int getNoops() {
		return _noops;
	}

}
