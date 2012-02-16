/**
 * 
 */
package model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chusk3
 *
 * This class encapsulates the variables that are needed to create a processor.
 * As of 16 February, 2012 this includes:
 * 		1) a name - string
 * 		2) a map of ALU function names and the number of cycles each takes - Map<String, int>
 * 		3) the numbers of ALUs in the processor - int
 */
public class ProcessorConfiguration {
	
	private int _aluCount = 0;
	private Map<String, Integer> _cyclesRequiredByOpcode = new HashMap<String, Integer>(50);
	private String _configurationName = "Unknown";
	
	
	/* Constructors */
	public ProcessorConfiguration( String name, int aluCount, Map<String, Integer> cycleMap)
	{
		_configurationName = name;
		_cyclesRequiredByOpcode = cycleMap;
		_aluCount = aluCount;
	}
	
	public ProcessorConfiguration(int aluCount, Map<String, Integer> cycleMap)
	{
		this("New Configuration", aluCount, cycleMap);
	}
	
	public ProcessorConfiguration(){}
	
	/* Getters/Setters */
	
	/**
	 * Sets the number of ALUs that instances of this Configuration will contain.
	 * @param count
	 */
	public void SetALUCount(int count)
	{
		_aluCount = count;
	}
	
	/**
	 * Gets the number of ALUs that instances of this Configuration will contain.
	 * @return
	 */
	public int GetALUCount()
	{
		return _aluCount;
	}
	
	/**
	 * Sets the name of this Configuration
	 * @param newName
	 */
	public void SetName(String newName)
	{
		_configurationName = newName;
	}
	
	/**
	 * Gets the name of this Configuration
	 * @return
	 */
	public String GetName()
	{
		return _configurationName;
	}
	
	/**
	 * Returns the current Mapping of ALU Opcades to Cycles Required.
	 * @return
	 */
	public Map<String, Integer> GetCycleMap()
	{
		return _cyclesRequiredByOpcode;
	}
	
	
	/* Public Methods */
	
	/**
	 * This is the desired method for adding a new cycle mapping to the Configuration.
	 * We don't want to wholesale replace the Map, as that could lead to losing some data
	 * in the previous map.
	 * 
	 * @param opName
	 * @param cyclesRequired
	 */
	public void AddCycleMapping(String opName, int cyclesRequired)
	{
			_cyclesRequiredByOpcode.put(opName, cyclesRequired);
	}
}
