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
import interfaces.ProcStatus;

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

	public Processor(int aluCount, Map<String, Integer> opCycles,
			List<IInstruction> instrs, int memCount) {
		instructions = instrs;
		alus = new ArrayList<IALU>(aluCount);
		//memories = new ArrayList<IMemoryAccess>(memCount);  //This should probably also receive a memCount variable, so the processor knows how many mems it has
		memories = new ArrayList<IMemoryAccess>(1);
		for(int i = 0; i < aluCount; i++)
		{

			alus.add(new ALU(i, 1, opCycles));
		}
		registers = new Registry();
		memoryBanks = new Memory(1000000);
		issue = new Issue(alus, memories, registers);
		fetch = new FetchUnit(instructions, issue, registers);
		for(int i = 0; i < memCount; i++)
		{

			memories.add(new MemoryAccess(memoryBanks, 1, opCycles, i));
		}
		writeBack = new WriteBack(memories, alus, registers);
	}

	@Override
	public Memory getMemory()
	{
		return memoryBanks;
	}
	
	@Override
	public Registry getRegistry() {
		return registers;
	}

	@Override
	public void Cycle() {
		// We cycle from the end of the pipeline towards the front.
		writeBack.Cycle();
		for (IALU alu : alus) {
			alu.Cycle();
		}
		for (IMemoryAccess mem : memories) {
			mem.Cycle();
		}
		issue.Cycle();
		fetch.Cycle();

	}

	@Override
	public ProcStatus getStatus() {
		ProcStatus myStatus = ProcStatus.Inactive;
		if (issue.GetStatus() == ProcStatus.Active) {
			myStatus = ProcStatus.Active;
		}
		if (fetch.GetStatus() == ProcStatus.Active) {
			myStatus = ProcStatus.Active;
		}
		for (IMemoryAccess mem : memories) {
			if (mem.GetStatus() == ProcStatus.Active) {
				myStatus = ProcStatus.Active;
			}
		}
		for (IALU alu : alus) {
			if (alu.GetStatus() == ProcStatus.Active) {
				myStatus = ProcStatus.Active;
			}
		}
		if (writeBack.GetStatus() == ProcStatus.Active) {
			myStatus = ProcStatus.Active;
		}

		return myStatus;
	}

	@Override
	public IIssueUnit getIssue() {
		return issue;
	}

	@Override
	public List<IALU> getALUs() {
		return alus;
	}

	@Override
	public List<IMemoryAccess> getMemUnits() {
		return memories;
	}

	@Override
	public IFetchUnit getFetchUnit() {
		return fetch;
	}

	@Override
	public IALU getALU(int aluToGet) {
		return alus.get(aluToGet);
	}

	@Override
	public IMemoryAccess getMemUnit(int memToGet) {
		return memories.get(memToGet);
	}

	@Override
	public IWriteBack getWriteback() {
		return writeBack;
	}
}
