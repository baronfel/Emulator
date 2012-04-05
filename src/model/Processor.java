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
public class Processor extends AbstractModel implements IProcessor {
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
		memoryBanks = new Memory(1000000);
		
		instructions = instrs;
		
		fetch = new FetchUnit(instructions, issue);
		issue = new Issue(alus, instructions, registers);
		memory = new MemoryAccess(memoryBanks, 1, opCycles);
		writeBack = new WriteBack(memory, alus, registers);
	}

	@Override
	public Registry getRegistry() {
		return registers;
	}
	
}