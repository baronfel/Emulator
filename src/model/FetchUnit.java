package model;

import java.awt.Event;
import java.util.List;

import interfaces.IFetchUnit;
import interfaces.IInstruction;
import interfaces.IIssueUnit;

public class FetchUnit implements IFetchUnit {

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
			if (registry.getValue(instruction.getRD()) != registry.getValue(instruction.getRS()))
				index = instruction.getImmediate();
			break;
		case "j":
			index = instruction.getJumpdest();
			break;
		case "beq":
			if (registry.getValue(instruction.getRD()) == registry.getValue(instruction.getRS()))
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
	public boolean GetStatus() {
		if(index >= ilist.size())
			return false;
		return true;
	}

	@Override
	public void Cycle() {
		FetchInstruction();

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
	
	public int getPC(){
		return index;
	}

}
