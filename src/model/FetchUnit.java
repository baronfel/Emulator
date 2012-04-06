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

	public FetchUnit(List<IInstruction> instructions, IIssueUnit issue)
	{
		this.issue = (Issue) issue;
		ilist = instructions;
		index = 0;
	}
	public void FetchInstruction()
	{
		IInstruction instruction = ilist.get(index);
		index++;
		issue.addToPreIssue(instruction);
		
	}
	
	@Override
	public String GetStatus() {
		// TODO Auto-generated method stub
		return null;
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

}
