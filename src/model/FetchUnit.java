/**
 *  This class represents the Fetch step of the MIPS pipeline. It handles jump and branch instructions, but mostly just pull instructions from
 *  the list and sends them to the fetch for register values.
 * 
 */
package model;

import interfaces.IFetchUnit;
import interfaces.IInstruction;
import interfaces.IIssueUnit;
import interfaces.ProcStatus;

import java.util.ArrayList;
import java.util.List;

public class FetchUnit extends AbstractModel implements IFetchUnit {

	private List<IInstruction> ilist;
	private int index;
	private Issue issue;
	private Registry registry;

	public FetchUnit(List<IInstruction> instructions, IIssueUnit issue,
			Registry registers) {
		this.issue = (Issue) issue;
		ilist = instructions;
		index = 0;
		registry = registers;
	}

	public void FetchInstruction() {
		IInstruction instruction = ilist.get(index);
		index++;
		switch (instruction.getOpcode().toLowerCase()) {
		case "jr":
			index = registry.getValue(instruction.getRS());
			break;
		case "bne":
			if (registry.getValue(instruction.getRD()) != registry
					.getValue(instruction.getRS()))
				index = instruction.getImmediate();
			break;
		case "j":
			index = instruction.getJumpdest();
			break;
		case "beq":
			if (registry.getValue(instruction.getRD()) == registry
					.getValue(instruction.getRS()))
				index = instruction.getImmediate();
			break;
		case "beqz":
			if (registry.getValue(instruction.getRS()) == 0)
				index = instruction.getImmediate();
		default:
			break;
		}
		issue.addToPreIssue(instruction);

	}

	@Override
	public ProcStatus GetStatus() {
		if (index == ilist.size())
			return ProcStatus.Inactive;
		else

			return ProcStatus.Active;
	}

	@Override
	public void Cycle() {
		FetchInstruction();
	}

	@Override
	public List<IInstruction> CurrentInstructions() {
		return new ArrayList<IInstruction>(ilist);
	}

	public int getPC() {
		return index;
	}

}
