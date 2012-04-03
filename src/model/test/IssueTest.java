package model.test;

import static org.junit.Assert.*;
import interfaces.IALU;
import interfaces.IInstruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.ALU;
import model.ITypeInstruction;
import model.Issue;

import org.junit.BeforeClass;
import org.junit.Test;

public class IssueTest {
	private static List<IALU> alus = new ArrayList<IALU>();
	private static Issue issue = new Issue(alus, null, null);
	
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
		assertEquals(issue.getNumInPreIssue(), 1);
		issue.Cycle();
		assertEquals(issue.getNumInPreIssue(), 0);
	}

}
