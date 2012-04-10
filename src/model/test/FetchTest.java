/**
 * 
 */
package model.test;

import static org.junit.Assert.*;

import interfaces.IInstruction;

import java.util.ArrayList;
import java.util.List;



import model.BranchInstruction;
import model.FetchUnit;
import model.ITypeInstruction;
import model.Issue;
import model.JTypeInstruction;
import model.RTypeInstruction;
import model.Registry;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Bob
 *
 */
public class FetchTest {
	private static List <IInstruction> ilist6 = new ArrayList<IInstruction>();
	private static List <IInstruction> ilist = new ArrayList<IInstruction>();
	private static List <IInstruction> ilist2 = new ArrayList<IInstruction>();
	private static List <IInstruction> ilist3 = new ArrayList<IInstruction>();
	private static List <IInstruction> ilist4 = new ArrayList<IInstruction>();
	private static List <IInstruction> ilist5 = new ArrayList<IInstruction>();
	private static Issue issue;
	private static Issue issue2;
	private static FetchUnit fetch;
	private static Registry registers;
	
	@BeforeClass
	public static void GetEverything()
	{
		registers = new Registry();
		IInstruction instruction = new ITypeInstruction("nop", 0, 0, 0, 0);
		ilist6.add(instruction);
		ilist.add(new JTypeInstruction(5, 0));
		ilist2.add(new RTypeInstruction("jr", 0, 5, 0, 0, 0, 0));
		registers.setRegister(5, 8);
		ilist3.add(new BranchInstruction("bne", 5, 6, 12, 0, ""));
		registers.setRegister(6, 8);
		registers.setRegister(7, 9);
		ilist3.add(new BranchInstruction("bne", 5, 7, 12, 0, ""));
		ilist4.add(new BranchInstruction("beq", 5, 7, 18, 0, ""));
		ilist4.add(new BranchInstruction("beq", 5, 6, 18, 0, ""));
		ilist5.add(new BranchInstruction("beqz", 0, 4, 25, 0, ""));
		ilist5.add(new BranchInstruction("beqz", 0, 3, 25, 0, ""));
		registers.setRegister(4, 8);
		registers.setRegister(3, 0);

		issue = new Issue(null, null, null);
		issue2 = new Issue(null, null, null);
		fetch = new FetchUnit(ilist, issue, registers);
	}
	
	@Test
	public void BasicFetchWorks() {
		fetch = new FetchUnit(ilist6, issue2, registers);
		assertEquals(issue2.getNumInPreIssue(), 0);
		fetch.Cycle();
		assertEquals(issue2.getNumInPreIssue(), 1);
	}
	
	@Test
	public void JumpTest(){
		fetch = new FetchUnit(ilist, issue, registers);
		assertEquals(0, fetch.getPC());
		fetch.Cycle();
		assertEquals(5, fetch.getPC());
	}

	@Test
	public void JRTest(){
		fetch = new FetchUnit(ilist2, issue, registers);
		assertEquals(0, fetch.getPC());
		fetch.Cycle();
		assertEquals(8, fetch.getPC());
	}
	
	@Test
	public void BNETest(){
		fetch = new FetchUnit(ilist3, issue, registers);
		assertEquals(0, fetch.getPC());
		fetch.Cycle();
		assertEquals(1, fetch.getPC());
		fetch.Cycle();
		assertEquals(12, fetch.getPC());
	}
	
	@Test
	public void BEQTest(){
		fetch = new FetchUnit(ilist4, issue, registers);
		assertEquals(0, fetch.getPC());
		fetch.Cycle();
		assertEquals(1, fetch.getPC());
		fetch.Cycle();
		assertEquals(18, fetch.getPC());
	}

	@Test
	public void BEQZTest(){
		fetch = new FetchUnit(ilist5, issue, registers);
		assertEquals(0, fetch.getPC());
		fetch.Cycle();
		assertEquals(1, fetch.getPC());
		fetch.Cycle();
		assertEquals(25, fetch.getPC());
	}
}
