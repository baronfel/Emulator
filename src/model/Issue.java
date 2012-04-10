/**
 * 	This class represents the fetch and issue steps of the pipeline. For now, it just converts from the instruction list
 * to the pre-ALU buffer. Later, it might do hazards too!
 */
package model;

import interfaces.IALU;
import interfaces.IInstruction;
import interfaces.IIssueUnit;
import interfaces.IMemoryAccess;

import java.awt.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Bob
 * 
 */
public class Issue implements IIssueUnit {
	private List<IALU> alus;
	private Registry registry;
	private int buffSize = 4;
	private ArrayBlockingQueue<IInstruction> PreIssueBuffer = new ArrayBlockingQueue<IInstruction>(
			buffSize);
	private int numInPreIssue = 0;
	private List<IMemoryAccess> mems;
	
	public Issue(List<IALU> alus, List<IMemoryAccess>  mems, Registry registry) {
		this.alus = alus;
		this.registry = registry;
		this.mems = mems;
	}

	/**
	 * This method issues an instruction from the list of waiting instructions
	 * to the ALU or MEM.
	 */
	public void IssueInstructions(IInstruction instruction) {
		int op1 = 0;
		int op2 = 0;
		int dst = 0;
		switch (instruction.getOpcode().toLowerCase()) {
		case "lw":
			int val = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD()) + instruction.getImmediate();
			GetFirstAvailableMEM().addToPreMEM(instruction.getOpcode(),
					instruction.getSeqNum(), val, dst, 1);
			break;
		case "addi":
			op1 = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD());
			op2 = instruction.getImmediate();
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		case "sw":
			int reg = instruction.getRS();
			dst = registry.getValue(instruction.getRD()) + instruction.getImmediate();
			GetFirstAvailableMEM().addToPreMEM(instruction.getOpcode(),
					instruction.getSeqNum(), reg, dst, 1);
			break;
		case "mul":
			op1 = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD());
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		case "add":
			op1 = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD());
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		case "sub":
			op1 = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD());
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		case "sll":
			op1 = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD());
			op2 = registry.getValue(instruction.getSHAMT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		case "srl":
			op1 = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD());
			op2 = registry.getValue(instruction.getSHAMT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
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
			dst = registry.getValue(instruction.getRD());
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		case "or":
			op1 = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD());
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		case "slt":
			op1 = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD());
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		case "slti":
			op1 = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD());
			op2 = instruction.getImmediate();
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		case "sltu":
			op1 = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD());
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		case "sltiu":
			op1 = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD());
			op2 = instruction.getImmediate();
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		case "nor":
			op1 = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD());
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		case "div":
			op1 = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD());
			op2 = registry.getValue(instruction.getRT());
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		default:
			break;
		}
		numInPreIssue--;

		// GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
		// instruction.getSeqNum(), op1, op2, dst);
	}

	private IMemoryAccess GetFirstAvailableMEM() {
		for(int i = 0; i < mems.size(); i++)
			if(!mems.get(i).GetStatus())
				return mems.get(i);
		int PreALUQueueSize = Integer.MAX_VALUE;
		IMemoryAccess memToUse = mems.get(0);
		for (int i = 1; i < mems.size(); i++) {
			if (( mems.get(i)).getAmountInPreMEM() < PreALUQueueSize) {
				PreALUQueueSize = (int) ( mems.get(i)).getAmountInPreMEM();
				memToUse =  mems.get(i);
			}

		}
		return memToUse;
	}

	private IALU GetFirstAvailableALU() {
		for(int i = 0; i < alus.size(); i++)
			if(!alus.get(i).GetStatus())
				return alus.get(i);
		int PreALUQueueSize = Integer.MAX_VALUE;
		IALU aluToUse = alus.get(0);
		for (int i = 1; i < alus.size(); i++) {
			if (( alus.get(i)).getAmountInPreALU() < PreALUQueueSize) {
				PreALUQueueSize = (int) ( alus.get(i)).getAmountInPreALU();
				aluToUse =  alus.get(i);
			}

		}
		return aluToUse;
	}

	@Override
	public boolean GetStatus() {
		if(PreIssueBuffer.size() == 0)
			return false;
		return true;
	}

	@Override
	public void Cycle() {
		IssueInstructions(PreIssueBuffer.poll());

	}

	@Override
	public List<IInstruction> CurrentInstructions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event PropertyChanged(Object aIn_propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addToPreIssue(IInstruction instruction) {
		if (PreIssueBuffer.offer(instruction))
		{
			numInPreIssue++;
			return true;
		}
		return false;
	}
	
	public int getNumInPreIssue(){
		return numInPreIssue;
	}

}
