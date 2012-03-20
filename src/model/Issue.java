/**
 * 	This class represents the fetch and issue steps of the pipeline. For now, it just converts from the instruction list
 * to the pre-ALU buffer. Later, it might do hazards too!
 */
package model;

import interfaces.IInstruction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bob
 *
 */
public class Issue {
	private int index = 0;
	private List<IInstruction> ilist = new ArrayList<IInstruction>();// THIS SHOULD BE SET IN A CONSTRUCTOR
	private ALU alu = new ALU(1); // THIS SHOULD BE SET IN A CONSTRUCTOR
	
	
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
		case "bne": op1 = instruction.getRS(); op2 = instruction.getRD(); dst = instruction.getImmediate();
			break;
		case "j": //J does not use ALU
			break;
		case "lw": //LW does not use ALU
			break;
		case "beq": op1 = instruction.getRS(); op2 = instruction.getRD(); dst = instruction.getImmediate();
			break;
		case "addi": op1 = instruction.getRS(); dst = instruction.getRD(); op2 = instruction.getImmediate();
			break;
		case "sw": //SW does not use ALU
			break;
		case "mul": op1 = instruction.getRS(); dst = instruction.getRD(); op2 = instruction.getRT();
			break;
		case "add": op1 = instruction.getRS(); dst = instruction.getRD(); op2 = instruction.getRT();
			break;
		case "sub": op1 = instruction.getRS(); dst = instruction.getRD(); op2 = instruction.getRT();
			break;
		case "sll": op1 = instruction.getRS(); dst = instruction.getRD(); op2 = instruction.getSHAMT();
			break;
		case "srl": op1 = instruction.getRS(); dst = instruction.getRD(); op2 = instruction.getSHAMT();
			break;
		case "nop": op1 = 0; dst = 0; op2 = 0;
			break;
		case "and": op1 = instruction.getRS(); dst = instruction.getRD(); op2 = instruction.getRT();
			break;
		case "or": op1 = instruction.getRS(); dst = instruction.getRD(); op2 = instruction.getRT();
			break;
		case "slt": op1 = instruction.getRS(); dst = instruction.getRD(); op2 = instruction.getRT();
			break;
		case "slti": op1 = instruction.getRS(); dst = instruction.getRD(); op2 = instruction.getImmediate();
			break;
		case "sltu": op1 = instruction.getRS(); dst = instruction.getRD(); op2 = instruction.getRT();
			break;
		case "sltiu": op1 = instruction.getRS(); dst = instruction.getRD(); op2 = instruction.getImmediate();
			break;
		case "nor": op1 = instruction.getRS(); dst = instruction.getRD(); op2 = instruction.getRT();
			break;
		case "div": op1 = instruction.getRS(); dst = instruction.getRD(); op2 = instruction.getRT();
			break;
			default: 
				break;
		}

		alu.addToPreALU(instruction.getOpcode(), instruction.getSeqNum(), op1, op2, dst);
	}

}
