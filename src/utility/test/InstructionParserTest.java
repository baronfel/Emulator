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


/**
 * @author Bob
 *
 */
public class InstructionParserTest {
	private static InstructionParser testParser;
	private static ArrayList<IInstruction> ilist;
	private static ArrayList<String> invalidlist;

	
	@BeforeClass
	public static void GetInstructionsAndInvalids(){
		try {
			ilist = (ArrayList<IInstruction>) testParser.LoadInstructions("ParserTestFile");
		} catch (InvalidInstructionException e) {
			invalidlist = (ArrayList<String>) e.getInvalidList();
		}
	}
	
	@Test
	public void InstructionTest(){
		assertTrue(ilist.get(0).equals("JR 7\n"));
		assertTrue(ilist.get(1).equals("BNE 2, 3, LBL1\n"));
		assertTrue(ilist.get(2).equals("J 16\n"));
		assertTrue(ilist.get(3).equals("LW 12, 4(5)\n"));
		assertTrue(ilist.get(4).equals("BEQ 0, 1 LBL1\n"));
		assertTrue(ilist.get(5).equals("ADDI 6, 7, 1337\n"));
		assertTrue(ilist.get(6).equals("SW 13, 8(9)\n"));
		assertTrue(ilist.get(7).equals("MUL 1, 2, 3\n"));
		assertTrue(ilist.get(8).equals("ADD 4, 5, 6\n"));
		assertTrue(ilist.get(9).equals("SUB 8, 9, 10\n"));
		assertTrue(ilist.get(10).equals("SLL 11, 12, 2\n"));
		assertTrue(ilist.get(11).equals("SRL 8, 9, 1\n"));
		assertTrue(ilist.get(12).equals("NOP\n"));
		assertTrue(ilist.get(13).equals("AND 2, 6, 7\n"));
		assertTrue(ilist.get(14).equals("OR 3, 2, 1\n"));
		assertTrue(ilist.get(15).equals("SLT 5, 8, 13\n"));
		assertTrue(ilist.get(16).equals("SLTI 2, 3, 5\n"));
		assertTrue(ilist.get(17).equals("SLTU 1, 3, 2\n"));
		assertTrue(ilist.get(18).equals("SLTIU 13, 21, 34\n"));
		assertTrue(ilist.get(19).equals("NOR 8, 6, 5\n"));
		assertTrue(ilist.get(20).equals("DIV 15, 14, 2\n"));
		
	}
	
	@Test
	public void InvalidTest(){
		assertTrue(invalidlist.get(0).equals("Line: 6\tAND1"));
		assertTrue(invalidlist.get(1).equals("Line: 15\tSERIAL"));
		assertTrue(invalidlist.get(2).equals("Line: 22\tSUPER CERIAL"));
	}
}
