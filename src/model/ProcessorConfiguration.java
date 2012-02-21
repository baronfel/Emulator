/**
 * Holds a configuration of hardware for the MIPS simulator program. The user is able to set up
 * a configuration of components of type this class.
 * @see IProcessor
 * 
 */


package model;

import java.util.HashMap;
import java.util.Map;

public class ProcessorConfiguration {
	public int _aLUCount;
	public Map<String, Integer> _cycleMap = new HashMap<String, Integer>();
	public String _name;
}