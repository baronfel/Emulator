package model.test;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


import static org.junit.Assert.*;
import interfaces.IALU;
import interfaces.IInstruction;
import interfaces.IMemoryAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.ALU;
import model.ITypeInstruction;
import model.Issue;
import model.MemoryAccess;
import model.RTypeInstruction;
import model.Registry;


public class IssueTest {
	private static List<IALU> alus = new ArrayList<IALU>();
	private static List<IALU> alus2 = new ArrayList<IALU>();
	private static List<IMemoryAccess> mems = new ArrayList<IMemoryAccess>();
	private static Registry registry = new Registry();
	private static Issue issue = new Issue(alus, mems, registry);
	private static Issue issue2 = new Issue(alus2, mems, registry);
	private static Issue issue3 = new Issue(alus2, mems, registry);
	static IALU alu;
	
	@BeforeClass
	public static void GetEverything() //THIS TEST CLASS SHOULD TEST PUTTING EACH INSTRUCTION IN THE PRE-ALU BUFF
	{
		Map<String, Integer> cycleMap = new HashMap<String, Integer>();
		cycleMap.put("nop", 1);
		cycleMap.put("add", 1);
		cycleMap.put("sub", 1);
		cycleMap.put("and", 1);
		cycleMap.put("or", 1);
		IInstruction instruction = new ITypeInstruction("nop", 0, 0, 0, 0);
		issue.addToPreIssue(instruction);
		issue.addToPreIssue(new RTypeInstruction("add", 1, 2, 3, 4, 5, 6));
		IALU alu = new ALU(1, 4, cycleMap);
		IALU alu2 = new ALU(2, 4, cycleMap);
		IALU alu3 = new ALU(3, 4, cycleMap);
		IALU alu4 = new ALU(4, 4, cycleMap);
		alus2.add(alu2);
		alus2.add(alu3);
		alus2.add(alu4);		
		alus.add(alu);
		
		
		IMemoryAccess mem1 = new MemoryAccess(null, 4, cycleMap);
		IMemoryAccess mem2 = new MemoryAccess(null, 4, cycleMap);
		IMemoryAccess mem3 = new MemoryAccess(null, 4, cycleMap);
		mems.add(mem1);
		mems.add(mem2);
		mems.add(mem3);
		
		issue2.addToPreIssue(new RTypeInstruction("sub", 1, 2, 3, 4, 5, 6));
		issue2.addToPreIssue(new RTypeInstruction("or", 1, 2, 3, 4, 5, 6));
		issue2.addToPreIssue(new RTypeInstruction("and", 1, 2, 3, 4, 5, 6));
		issue3.addToPreIssue(new RTypeInstruction("lw", 1, 2, 3, 4, 5, 6));
		issue3.addToPreIssue(new RTypeInstruction("sw", 1, 2, 3, 4, 5, 6));
		issue3.addToPreIssue(new RTypeInstruction("sb", 1, 2, 3, 4, 5, 6));
		alu2.addToPreALU("add", 0, 0, 0, 0);
		alu2.addToPreALU("add", 0, 0, 0, 0);
		alu3.addToPreALU("add", 0, 0, 0, 0);
		mem2.addToPreMEM("LW", 0, 0, 0, 0);
		mem2.addToPreMEM("SW", 0, 0, 0, 0);
		mem3.addToPreMEM("SW", 0, 0, 0, 0);
	}

	@Test
	public void issueTest() {
		/*
		final int retval = 1;
		final int zeroval = 0;
		final String nop = "nop";
		
		Mockery mocker = new Mockery();
		alu = mocker.mock(IALU.class);
		mocker.checking(new Expectations(){{
		exactly(2).of(alu).getAmountInPreALU();
			will(returnValue(retval));
		oneOf(alu).addToPreALU(nop, zeroval, zeroval, zeroval, zeroval);
		}});
		*/
		assertEquals(issue.getNumInPreIssue(), 2);
		issue.Cycle();
		assertEquals(issue.getNumInPreIssue(), 1);
		issue.Cycle();
		assertEquals(issue.getNumInPreIssue(), 0);
	}

	
	@Test
	public void getFirstALUTest(){
		assertEquals(issue2.getNumInPreIssue(), 3);
		assertEquals(issue2.GetFirstAvailableALU().getALUNumber(), 4);
		issue2.Cycle();
		assertEquals(issue2.getNumInPreIssue(), 2);
		assertEquals(issue2.GetFirstAvailableALU().getALUNumber(), 3);
		issue2.Cycle();
		assertEquals(issue2.getNumInPreIssue(), 1);
		assertEquals(issue2.GetFirstAvailableALU().getALUNumber(), 4);
		issue2.Cycle();
		assertEquals(issue2.getNumInPreIssue(), 0);
		assertEquals(issue2.GetFirstAvailableALU().getALUNumber(), 2);
	}
	
	
	@Test
	@Ignore
	public void getFirstMEMTest(){
		assertEquals(issue3.getNumInPreIssue(), 3);
		assertEquals(issue3.GetFirstAvailableMEM().getMEMNumber(), 4);
		issue3.Cycle();
		assertEquals(issue3.getNumInPreIssue(), 2);
		assertEquals(issue3.GetFirstAvailableMEM().getMEMNumber(), 3);
		issue3.Cycle();
		assertEquals(issue3.getNumInPreIssue(), 1);
		assertEquals(issue3.GetFirstAvailableMEM().getMEMNumber(), 4);
		issue3.Cycle();
		assertEquals(issue3.getNumInPreIssue(), 0);
		assertEquals(issue3.GetFirstAvailableMEM().getMEMNumber(), 2);
		
	}
}
