/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import interfaces.IALU;
import interfaces.IFetchUnit;
import interfaces.IInstruction;
import interfaces.IIssueUnit;
import interfaces.IMemoryAccess;
import interfaces.IProcessor;
import interfaces.IWriteBack;

/**
 * @author Chester
 *
 */
public class Processor implements IProcessor {
	private List<IALU> alus;
	private IFetchUnit fetch;
	private IIssueUnit issue;
	private IMemoryAccess memory;
	private IWriteBack writeBack;
	
	private Memory memoryBanks;
	private Registry registers;
	
	private Map<String, Integer> cycleMapping;
	private List<IInstruction> instructions;
	
	public Processor(int aluCount, Map<String, Integer> opCycles, List<IInstruction> instrs)
	{
		alus = new ArrayList<IALU>(aluCount);
		for(int i = 0; i < aluCount; i++)
		{
			alus.add(new ALU(i, 1, opCycles));
		}
		registers = new Registry();
		memoryBanks = new Memory();
		
		instructions = instrs;
		
		fetch = new FetchUnit();
		issue = new Issue(alus, instructions, registers);
		memory = new MemoryAccess();
		writeBack = new WriteBack();
		
	}

	@Override
	public Registry getRegistry() {
		return registers;
	}

	@Override
	public void notifyChanged(ModelEvent aE) {
		// TODO Auto-generated method stub
		
	}
}