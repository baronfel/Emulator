package model;

import java.awt.Event;
import java.util.List;

import interfaces.IFetchUnit;
import interfaces.IInstruction;
import interfaces.IIssueUnit;
import interfaces.ProcStatus;

public class FetchUnit implements IFetchUnit {

	private List<IInstruction> ilist;
	private int index;
	private Issue issue;

	public FetchUnit(List<IInstruction> instructions, IIssueUnit issue) {
		this.issue = (Issue) issue;
		ilist = instructions;
		index = 0;
	}

	public void FetchInstruction() {
		IInstruction instruction = ilist.get(index);
		index++;
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
		// TODO Auto-generated method stub
		return null;
	}
}
