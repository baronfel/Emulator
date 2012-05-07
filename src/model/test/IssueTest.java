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
import model.BranchInstruction;
import model.ITypeInstruction;
import model.Issue;
import model.MemoryAccess;
import model.RTypeInstruction;
import model.Registry;


public class IssueTest {
	private static List<IALU> alus = new ArrayList<IALU>();
	private static List<IALU> alus2 = new ArrayList<IALU>();
	private static List<IALU> alus3 = new ArrayList<IALU>();
	private static List<IMemoryAccess> mems = new ArrayList<IMemoryAccess>();
	private static List<IMemoryAccess> mems2 = new ArrayList<IMemoryAccess>();
	private static Registry registry = new Registry();
	private static Issue issue = new Issue(alus, mems, registry);
	private static Issue issue2 = new Issue(alus2, mems, registry);
	private static Issue issue3 = new Issue(alus2, mems, registry);
	private static Issue issue4 = new Issue(alus3, mems2, registry);
	static IALU alu;
	private static Map<String, Integer> _cyclesRequiredByOpcode = new HashMap<String, Integer>();
	
	@BeforeClass
	public static void GetEverything() //THIS TEST CLASS SHOULD TEST PUTTING EACH INSTRUCTION IN THE PRE-ALU BUFF
	{
		Map<String, Integer> cycleMap = new HashMap<String, Integer>();
		cycleMap.put("nop", 1);
		cycleMap.put("add", 1);
		cycleMap.put("sub", 1);
		cycleMap.put("and", 1);
		cycleMap.put("or", 1);
		cycleMap.put("lw", 1);
		cycleMap.put("sw", 1);
		cycleMap.put("sb", 1);
		_cyclesRequiredByOpcode.put("add", 1);
		_cyclesRequiredByOpcode.put("sub", 1);
		_cyclesRequiredByOpcode.put("addi", 1);
		_cyclesRequiredByOpcode.put("lw", 2);
		_cyclesRequiredByOpcode.put("sw", 2);
		_cyclesRequiredByOpcode.put("and", 1);
		_cyclesRequiredByOpcode.put("or", 1);
		_cyclesRequiredByOpcode.put("nor", 1);
		_cyclesRequiredByOpcode.put("andi", 1);
		_cyclesRequiredByOpcode.put("ori", 1);
		_cyclesRequiredByOpcode.put("sll", 1);
		_cyclesRequiredByOpcode.put("srl", 1);
		_cyclesRequiredByOpcode.put("beq", 1);
		_cyclesRequiredByOpcode.put("bne", 1);
		_cyclesRequiredByOpcode.put("slt", 1);
		_cyclesRequiredByOpcode.put("sltu", 1);
		_cyclesRequiredByOpcode.put("slti", 1);
		_cyclesRequiredByOpcode.put("sltiu", 1);
		_cyclesRequiredByOpcode.put("j", 1);
		_cyclesRequiredByOpcode.put("jr", 1);
		_cyclesRequiredByOpcode.put("la", 1);
		_cyclesRequiredByOpcode.put("li", 1);
		_cyclesRequiredByOpcode.put("lb", 1);
		_cyclesRequiredByOpcode.put("sb", 1);
		_cyclesRequiredByOpcode.put("andi", 1);
		_cyclesRequiredByOpcode.put("ori", 1);
		_cyclesRequiredByOpcode.put("addiu", 1);
		_cyclesRequiredByOpcode.put("beqz", 1);
		_cyclesRequiredByOpcode.put("bge", 1);
		_cyclesRequiredByOpcode.put("move", 1);
		_cyclesRequiredByOpcode.put("bnez", 1);
		_cyclesRequiredByOpcode.put("div", 1);
		_cyclesRequiredByOpcode.put("mul", 1);
		_cyclesRequiredByOpcode.put("nop", 1);
		IInstruction instruction = new ITypeInstruction("nop", 0, 0, 0, 0);
		issue.addToPreIssue(instruction);
		issue.addToPreIssue(new RTypeInstruction("add", 19, 20, 21, 22, 23, 24));
		IALU alu = new ALU(1, 4, cycleMap);
		IALU alu2 = new ALU(2, 4, cycleMap);
		IALU alu3 = new ALU(3, 4, cycleMap);
		IALU alu4 = new ALU(4, 4, cycleMap);
		IALU alu5 = new ALU(4, 40, _cyclesRequiredByOpcode);
		alus2.add(alu2);
		alus2.add(alu3);
		alus2.add(alu4);		
		alus.add(alu);
		alus3.add(alu5);
		
		
		
		IMemoryAccess mem1 = new MemoryAccess(null, 4, cycleMap,0);
		IMemoryAccess mem2 = new MemoryAccess(null, 4, cycleMap,0);
		IMemoryAccess mem3 = new MemoryAccess(null, 4, cycleMap,0);
		IMemoryAccess mem4 = new MemoryAccess(null, 40, _cyclesRequiredByOpcode,0);
		mems.add(mem1);
		mems.add(mem2);
		mems.add(mem3);
		mems2.add(mem4);
		
		issue2.addToPreIssue(new RTypeInstruction("sub", 1, 2, 3, 4, 5, 6));
		issue2.addToPreIssue(new RTypeInstruction("or", 7, 8, 9, 10, 11, 12));
		issue2.addToPreIssue(new RTypeInstruction("and", 13, 14, 15, 16, 17, 18));
		issue3.addToPreIssue(new RTypeInstruction("lw", 1, 2, 3, 4, 5, 6));
		issue3.addToPreIssue(new RTypeInstruction("sw", 7, 8, 9, 10, 11, 12));
		issue3.addToPreIssue(new RTypeInstruction("sb", 13, 14, 15, 16, 17, 18));
		alu2.addToPreALU("add", 0, 0, 0, 0);
		alu2.addToPreALU("add", 0, 0, 0, 0);
		alu3.addToPreALU("add", 0, 0, 0, 0);
		mem2.addToPreMEM("LW", 0, 0, 0);
		mem2.addToPreMEM("SW", 0, 0, 0);
		mem3.addToPreMEM("SW", 0, 0, 0);
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
	public void fullInstructionsTest()
	{
		int rd = 0;
		int rs = 0;
		int imm = 0;
		int lineCounter = 0;
		int rt = 0;
		int sa = 0;
		int funct = 0;
		String label = "LOL";
		issue4.addToPreIssue(new ITypeInstruction("MOVE", rd, rs, imm, lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new ITypeInstruction("ORI", rd, rs, imm, lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue(new ITypeInstruction("LI", rd, 0, imm,
				lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new BranchInstruction("LA", rd, 0, imm,
				lineCounter, label));
		issue4.Cycle();
		issue4.addToPreIssue( new ITypeInstruction("SB", rd, rs, imm,
				lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new ITypeInstruction("LB", rd, rs, imm,
				lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new ITypeInstruction("ANDI", rd, rs, imm,
				lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new ITypeInstruction("ADDIU", rd, rs, imm,
				lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new RTypeInstruction("DIV", rd, rs, rt, sa,
				funct, lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new RTypeInstruction("NOR", rd, rs, rt, sa,
				funct, lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new RTypeInstruction("SLTU", rd, rs, rt, sa,
				funct, lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new ITypeInstruction("SLTIU", rd, rs, imm,
				lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new ITypeInstruction("SLTI", rd, rs, imm,
				lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue(new RTypeInstruction("SLT", rd, rs, rt, sa,
				funct, lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new RTypeInstruction("OR", rd, rs, rt, sa,
				funct, lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new RTypeInstruction("AND", rd, rs, rt, sa,
				funct, lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new RTypeInstruction("NOP", rd, rs, rt, sa,
				funct, lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue(  new RTypeInstruction("SRL", rd, rs, rt, sa,
				funct, lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue(  new RTypeInstruction("SLL", rd, rs, rt, sa,
				funct, lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new RTypeInstruction("SUB", rd, rs, rt, sa,
				funct, lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new RTypeInstruction("ADD", rd, rs, rt, sa,
				funct, lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue(  new RTypeInstruction("MUL", rd, rs, rt, sa,
				funct, lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue(  new ITypeInstruction("SW", rd, rs, imm,
				lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new ITypeInstruction("ADDI", rd, rs, imm,
				lineCounter));
		issue4.Cycle();
		issue4.addToPreIssue( new ITypeInstruction("LW", rd, rs, imm,
				lineCounter));
		issue4.Cycle();
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
