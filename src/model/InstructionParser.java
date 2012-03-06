/**
  * Reads in MIPS instructions from a file or from the view and parses them into instructions
 * readable by this simulator.
 * 
 */


package model;

import interfaces.IInstruction;

import java.util.List;

public class InstructionParser {
	public Simulation _unnamed_Simulation_;

	/**
	 * Reads from the indicated location a set of MIPS instructions in the MIPS format and outputs
	 * them as a list of instructions that the simulator can read.
	 * @param aInfilePath The location of the instructions.
	 * @return A list of instructions that the simulation can use.
	 */
	public List<IInstruction> LoadInstructions(String aInfilePath) {
		throw new UnsupportedOperationException();
	}
}