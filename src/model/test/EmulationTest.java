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

	public static Simulation CreateEncodeSimulation() {
		Simulation emu = new Simulation(new ProcessorConfiguration(4),
				InstructionParser.LoadInstructions("encode.asm"));
		emu.getMemory().setMemoryToString("James is awesome");
		return emu;
	}
	
	public static Simulation CreateDecodeSimulation() {
		Simulation emu = new Simulation(new ProcessorConfiguration(4),
				InstructionParser.LoadInstructions("decode.asm"));
		emu.getMemory().setMemoryToString("Lcogu\"ku\"cyguqog");
		return emu;
	}

	public void printMemory(Memory mem) {
		for (int i = 0; i < 16; i++)
			System.out.print((char) mem.getValueAt(i));
		System.out.println();
	}

	@Test
	public void EncodeTest() {
		Simulation emu = CreateEncodeSimulation();
		printMemory(emu.getMemory());
		emu.cycleToEnd();
		printMemory(emu.getMemory());
		char[] output = new char[16];
		for(int i = 0; i < 16; i++)
		{
			output[i] = (char) emu.getMemory().getValueAt(i);
		}
		String outPut = new String(output);
		assertEquals(outPut, "Lcogu\"ku\"cyguqog");
	}
	
	@Test
	public void DecodeTest()
	{
		Simulation emu = CreateDecodeSimulation();
		printMemory(emu.getMemory());
		emu.cycleToEnd();
		printMemory(emu.getMemory());
		char[] output = new char[16];
		for(int i = 0; i < 16; i++)
		{
			output[i] = (char) emu.getMemory().getValueAt(i);
		}
		String outPut = new String(output);
		assertEquals(outPut, "James is awesome");
	}

}
