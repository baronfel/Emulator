/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import interfaces.IALU;
import interfaces.IFetchUnit;
import interfaces.IIssueUnit;
import interfaces.IMemoryAccess;
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
	
	public Processor(int aluCount, Map<String, Integer> opCycles)
	{
		alus = new ArrayList<IALU>(aluCount);
		for(int i = 0; i < aluCount; i++)
		{
			alus.add(new ALU(i, 1, opCycles));
		}
	}
	
}
