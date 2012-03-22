package model;

import java.awt.Event;
import java.util.List;

import interfaces.IFetchUnit;
import interfaces.IInstruction;

public class FetchUnit implements IFetchUnit {

	private List<IInstruction> ilist;
	private int index;

	public FetchUnit(List<IInstruction> instructions)
	{
		ilist = instructions;
		index = 0;
	}
	public IInstruction FetchInstruction()
	{
		IInstruction instruction = ilist.get(index);
		index++;
		return instruction;
		
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

	@Override
	public void FetchInstructions() {
		// TODO Auto-generated method stub

	}

}
