package model.test;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.BeforeClass;
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


public class IssueTest {
	private static List<IALU> alus = new ArrayList<IALU>();
	private static List<IMemoryAccess> mems = new ArrayList<IMemoryAccess>();
	private static Issue issue = new Issue(alus, mems, null);
	static IALU alu;
	
	@BeforeClass
	public static void GetEverything()
	{
		Map<String, Integer> cycleMap = new HashMap<String, Integer>();
		cycleMap.put("nop", 1);
		IInstruction instruction = new ITypeInstruction("nop", 0, 0, 0, 0);
		issue.addToPreIssue(instruction);
		IALU alu = new ALU(0, 4, cycleMap);
		
		
		
		alus.add(alu);
	}

	@Test
	public void test() {
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
		assertEquals(issue.getNumInPreIssue(), 1);
		issue.Cycle();
		assertEquals(issue.getNumInPreIssue(), 0);
	}

}
