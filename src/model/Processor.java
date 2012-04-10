
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
	private List<IMemoryAccess> memories;
	
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
		
		fetch = new FetchUnit(instructions, issue, registers);
		memory = new MemoryAccess(memoryBanks, 1, opCycles);
		memories.add(memory);
		issue = new Issue(alus, memories, registers);
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
