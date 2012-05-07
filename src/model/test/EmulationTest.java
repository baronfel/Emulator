/**
 * This test will test the whole program without using a view.
 */
package model.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
	public void printMemory2(Memory mem) {
		for (int i = 0; i < 2000; i++)
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
		for (int i = 0; i < 16; i++) {
			output[i] = (char) emu.getMemory().getValueAt(i);
		}
		String outPut = new String(output);
		assertEquals(outPut, "Lcogu\"ku\"cyguqog");
	}

	@Test
	public void DecodeTest() {
		Simulation emu = CreateDecodeSimulation();
		printMemory(emu.getMemory());
		emu.cycleToEnd();
		printMemory(emu.getMemory());
		char[] output = new char[16];
		for (int i = 0; i < 16; i++) {
			output[i] = (char) emu.getMemory().getValueAt(i);
		}
		String outPut = new String(output);
		assertEquals(outPut, "James is awesome");
	}

	@Test
	public void GenesistTest() {
		Simulation emu = CreateGenesisSimulation();
		printMemory2(emu.getMemory());
		emu.cycleToEnd();
		printMemory2(emu.getMemory());
	}

	private Simulation CreateGenesisSimulation() {
		Simulation emu = new Simulation(new ProcessorConfiguration(4),
				InstructionParser.LoadInstructions("encode.asm"));
		Scanner file = null;
		// emu.getMemory().setMemoryToString("James is awesome");
		try {
			file = new Scanner(new File("genesis"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int counter = 0;
		while (file.hasNextLine()) {
			counter = emu.getMemory().setMemoryToString2(file.nextLine(), counter);
		}
		return emu;
	}

}
