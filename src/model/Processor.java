/**
 * 
 */
package model;

import java.util.Map;

/**
 * @author Chet
 *
 */
public class Processor implements IProcessor {
	
	private int _aluCount;
	private Map<String, Integer> opCycles;

	public Processor(int aluCount, Map<String, Integer> cycleMap) {
		_aluCount = aluCount;
		opCycles = cycleMap;
	}

}
