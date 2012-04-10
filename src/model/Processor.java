/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Collection;
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
	private List<IMemoryAccess> mems;
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
		int memcount = 1;
		mems = new ArrayList<IMemoryAccess>(memcount);
		registers = new Registry();
		memoryBanks = new Memory(1000000);
		
		instructions = instrs;
		
		fetch = new FetchUnit(instructions, issue, registers);
		memory = new MemoryAccess(memoryBanks, 1, opCycles);
		mems.add(memory);
		issue = new Issue(alus, mems, registers);
		writeBack = new WriteBack(memory, alus, registers);
	}

	@Override
	public Registry getRegistry() {
		return registers;
	}
	
	@Override
	public void Cycle()
	{
		// We cycle from the end of the pipeline towards the front.
		writeBack.Cycle();
		for(IALU alu : alus)
		{
			alu.Cycle();
		}
		memory.Cycle();
		issue.Cycle();
		fetch.Cycle();
		
	}
	
}

