package model.test;

import static org.junit.Assert.*;

import model.Memory;
import model.ProcessorConfiguration;
import model.Simulation;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import utility.InstructionParser;

public class HelloWorldTest {

	public static Simulation CreateHWSimulation() {
		Simulation emu = new Simulation(new ProcessorConfiguration(4),
				InstructionParser.LoadInstructions("helloagainworld.asm"));
		emu.getMemory().setMemoryToString("Hello World");
		return emu;
	}
	
	public void printMemory(Memory mem) {
		for (int i = 0; i < 16; i++)
			System.out.print((char) mem.getValueAt(i));
		System.out.println();
	}

	@Test
	public void EncodeTest() {
		Simulation emu = CreateHWSimulation();
		printMemory(emu.getMemory());
		emu.cycleToEnd();
		printMemory(emu.getMemory());
		char[] output = new char[11];
		for(int i = 0; i < 11; i++)
		{
			output[i] = (char) emu.getMemory().getValueAt(i);
		}
		String outPut = new String(output);
		assertEquals(outPut, "Hello World");
	}

}   //end class