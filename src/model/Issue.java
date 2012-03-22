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

/**
 * @author Bob
 *
 */
public class Issue implements IIssueUnit{
	private int index = 0;
	private List<IInstruction> ilist = new ArrayList<IInstruction>();// THIS SHOULD BE SET IN A CONSTRUCTOR
	private List<IALU> alus; // THIS SHOULD BE SET IN A CONSTRUCTOR
	private Registry registry;
	
	
	public Issue(List<IALU> alus, List<IInstruction> instructionList, Registry registry)
	{
		this.alus = alus;
		this.ilist = instructionList;
		this.registry = registry;
	}
	
	/**
	 * This method issues an instruction from the list of waiting instructions to the ALU.
	 */
	public void IssueInstruction()
	{
		IInstruction instruction = ilist.get(index);
		index++;
		int op1 = 0;
		int op2 = 0;
		int dst = 0;
		switch (instruction.getOpcode().toLowerCase()) {
		case "jr": //JR does not use ALU
			break;
		case "bne": op1 = registry.getValue(instruction.getRS()); op2 = registry.getValue(instruction.getRD()); dst = instruction.getImmediate();
			break;
		case "j": //J does not use ALU
			break;
		case "lw": //LW does not use ALU
			break;
		case "beq": op1 = registry.getValue(instruction.getRS()); op2 = registry.getValue(instruction.getRD()); dst = instruction.getImmediate();
			break;
		case "addi": op1 = registry.getValue(instruction.getRS()); dst = registry.getValue(instruction.getRD()); op2 = instruction.getImmediate();
			break;
		case "sw": //SW does not use ALU
			break;
		case "mul": op1 = registry.getValue(instruction.getRS()); dst = registry.getValue(instruction.getRD()); op2 = registry.getValue(instruction.getRT());
			break;
		case "add": op1 = registry.getValue(instruction.getRS()); dst = registry.getValue(instruction.getRD()); op2 = registry.getValue(instruction.getRT());
			break;
		case "sub": op1 = registry.getValue(instruction.getRS()); dst = registry.getValue(instruction.getRD()); op2 = registry.getValue(instruction.getRT());
			break;
		case "sll": op1 = registry.getValue(instruction.getRS()); dst = registry.getValue(instruction.getRD()); op2 = registry.getValue(instruction.getSHAMT());
			break;
		case "srl": op1 = registry.getValue(instruction.getRS()); dst = registry.getValue(instruction.getRD()); op2 = registry.getValue(instruction.getSHAMT());
			break;
		case "nop": op1 = 0; dst = 0; op2 = 0;
			break;
		case "and": op1 = registry.getValue(instruction.getRS()); dst = registry.getValue(instruction.getRD()); op2 = registry.getValue(instruction.getRT());
			break;
		case "or": op1 = registry.getValue(instruction.getRS()); dst = registry.getValue(instruction.getRD()); op2 = registry.getValue(instruction.getRT());
			break;
		case "slt": op1 = registry.getValue(instruction.getRS()); dst = registry.getValue(instruction.getRD()); op2 = registry.getValue(instruction.getRT());
			break;
		case "slti": op1 = registry.getValue(instruction.getRS()); dst = registry.getValue(instruction.getRD()); op2 = instruction.getImmediate();
			break;
		case "sltu": op1 = registry.getValue(instruction.getRS()); dst = registry.getValue(instruction.getRD()); op2 = registry.getValue(instruction.getRT());
			break;
		case "sltiu": op1 = registry.getValue(instruction.getRS()); dst = registry.getValue(instruction.getRD()); op2 = instruction.getImmediate();
			break;
		case "nor": op1 = registry.getValue(instruction.getRS()); dst = registry.getValue(instruction.getRD()); op2 = registry.getValue(instruction.getRT());
			break;
		case "div": op1 = registry.getValue(instruction.getRS()); dst = registry.getValue(instruction.getRD()); op2 = registry.getValue(instruction.getRT());
			break;
			default: 
				break;
		}

		GetFirstAvailableALU().addToPreALU(instruction.getOpcode(), instruction.getSeqNum(), op1, op2, dst);
	}

	private ALU GetFirstAvailableALU() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String GetStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Cycle(int aIn_numToCycle) {
		// TODO Auto-generated method stub
		
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

}
