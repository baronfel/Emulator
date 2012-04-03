/**
 * 	This class represents the fetch and issue steps of the pipeline. For now, it just converts from the instruction list
 * to the pre-ALU buffer. Later, it might do hazards too!
 */
package model;

import interfaces.IALU;
import interfaces.IInstruction;
import interfaces.IIssueUnit;

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

	public Issue(List<IALU> alus, Registry registry) {
		this.alus = alus;
		this.registry = registry;
	}

	/**
	 * This method issues an instruction from the list of waiting instructions
	 * to the ALU.
	 */
	public void IssueInstructions(IInstruction instruction) {
		int op1 = 0;
		int op2 = 0;
		int dst = 0;
		switch (instruction.getOpcode().toLowerCase()) {
		case "jr": // JR does not use ALU
			break;
		case "bne":
			op1 = registry.getValue(instruction.getRS());
			op2 = registry.getValue(instruction.getRD());
			dst = instruction.getImmediate();
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		case "j": // J does not use ALU
			break;
		case "lw":
			op1 = registry.getValue(instruction.getRS());
			op2 = instruction.getImmediate();
			dst = registry.getValue(instruction.getRD());
			GetFirstAvailableMEM().addToPreMEM(instruction.getOpcode(),
					instruction.getSeqNum(), op1, dst, op2, 1);
			break;
		case "beq":
			op1 = registry.getValue(instruction.getRS());
			op2 = registry.getValue(instruction.getRD());
			dst = instruction.getImmediate();
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		case "addi":
			op1 = registry.getValue(instruction.getRS());
			dst = registry.getValue(instruction.getRD());
			op2 = instruction.getImmediate();
			GetFirstAvailableALU().addToPreALU(instruction.getOpcode(),
					instruction.getSeqNum(), op1, op2, dst);
			break;
		case "sw":
			op1 = registry.getValue(instruction.getRS());
			op2 = instruction.getImmediate();
			dst = registry.getValue(instruction.getRD());
			GetFirstAvailableMEM().addToPreMEM(instruction.getOpcode(),
					instruction.getSeqNum(), op1, dst, op2, 1);
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

	private MEM GetFirstAvailableMEM() {
		// TODO Auto-generated method stub
		return null;
	}

	private ALU GetFirstAvailableALU() {
		int PreALUQueueSize = Integer.MAX_VALUE;
		ALU aluToUse = (ALU) alus.get(0);
		for (int i = 0; i < alus.size(); i++) {
			if (((ALU) alus.get(i)).getAmountInPreALU() < PreALUQueueSize) {
				PreALUQueueSize = (int) ((ALU) alus.get(i)).getAmountInPreALU();
				aluToUse = (ALU) alus.get(i);
			}

		}
		return aluToUse;
	}

	@Override
	public String GetStatus() {
		// TODO Auto-generated method stub
		return null;
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
