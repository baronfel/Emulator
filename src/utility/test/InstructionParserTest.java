/**
 * 
 */
package utility.test;

import static org.junit.Assert.*;
import interfaces.IInstruction;

import java.util.ArrayList;

import model.InvalidInstructionException;

import org.junit.BeforeClass;
import org.junit.Test;

import utility.InstructionParser;
import utility.Package;


/**
 * @author Bob
 *
 */
public class InstructionParserTest {
	private static InstructionParser testParser = new InstructionParser();
	private static ArrayList<IInstruction> ilist = new ArrayList<IInstruction>();
	private static ArrayList<String> invalidlist = new ArrayList<String>();
	private static Package pckg;

	
	@BeforeClass
	public static void GetInstructionsAndInvalids(){
			pckg = testParser.LoadInstructions("ParserTestFile");
			ilist = (ArrayList<IInstruction>) pckg.getIlist();
			invalidlist = (ArrayList<String>) pckg.getInvalidlist();
			
	}
	
	
	@Test
	public void InstructionTest(){
		assertTrue(ilist.get(0).toString().equals("JR 7\n"));
		assertTrue(ilist.get(1).toString().equals("BNE 2, 3, LBL1\n"));
		assertTrue(ilist.get(2).toString().equals("J 16\n"));
		assertTrue(ilist.get(3).toString().equals("LW 12, 4(5)\n"));
		assertTrue(ilist.get(4).toString().equals("BEQ 0, 1, LBL1\n"));
		assertTrue(ilist.get(5).toString().equals("ADDI 6, 7, 1337\n"));
		assertTrue(ilist.get(6).toString().equals("SW 13, 8(9)\n"));
		assertTrue(ilist.get(7).toString().equals("MUL 1, 2, 3\n"));
		assertTrue(ilist.get(8).toString().equals("ADD 4, 5, 6\n"));
		assertTrue(ilist.get(9).toString().equals("SUB 8, 9, 10\n"));
		assertTrue(ilist.get(10).toString().equals("SLL 11, 12, 2\n"));
		assertTrue(ilist.get(11).toString().equals("SRL 8, 9, 1\n"));
		assertTrue(ilist.get(12).toString().equals("NOP\n"));
		assertTrue(ilist.get(13).toString().equals("AND 2, 6, 7\n"));
		assertTrue(ilist.get(14).toString().equals("OR 3, 2, 1\n"));
		assertTrue(ilist.get(15).toString().equals("SLT 5, 8, 13\n"));
		assertTrue(ilist.get(16).toString().equals("SLTI 2, 3, 5\n"));
		assertTrue(ilist.get(17).toString().equals("SLTU 1, 3, 2\n"));
		assertTrue(ilist.get(18).toString().equals("SLTIU 13, 21, 34\n"));
		assertTrue(ilist.get(19).toString().equals("NOR 8, 6, 5\n"));
		assertTrue(ilist.get(20).toString().equals("DIV 15, 14, 2\n"));
		
	}
	
	@Test
	public void InvalidTest(){
		assertTrue(invalidlist.get(0).equals("Line: 6\tAND1"));
		assertTrue(invalidlist.get(1).equals("Line: 11\tLBL1:"));
		assertTrue(invalidlist.get(2).equals("Line: 15\tSERIAL"));
		assertTrue(invalidlist.get(3).equals("Line: 22\tSUPER CERIAL"));
	}

}
