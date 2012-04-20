/**
 * This test will test the whole program without using a view.
 */
package model.test;

import static org.junit.Assert.*;

import model.Memory;
import model.ProcessorConfiguration;
import model.Simulation;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import utility.InstructionParser;

/**
 * @author Bob
 * 
 */
public class EmulationTest {
	static Simulation emu;
	static Memory mem;

	/**
	 * 
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		emu = new Simulation(new ProcessorConfiguration(4),
				InstructionParser.LoadInstructions("EmuTestFile"));
		mem = emu.getMemory();
		mem.setMemoryToString("James is awesome");
	}

	public void printMemory() {
		for (int i = 0; i < 16; i++)
			System.out.print((char) mem.getValueAt(i));
	}

	@Test
	public void FullTest() {
		emu.cycleToEnd();
		printMemory();
		char[] output = new char[16];
		for(int i = 0; i < 16; i++)
		{
			output[i] = (char) mem.getValueAt(i);
		}
		String outPut = new String(output);
		assertEquals(outPut, "James is awesome");
	}

}
