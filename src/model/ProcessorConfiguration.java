/**
 * 
 */
package model;

import java.io.Serializable;
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
public class ProcessorConfiguration extends AbstractModel implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4578090939379015303L;
	private int _aluCount = 1;
	private int _memCount = 1;
	private Map<String, Integer> _cyclesRequiredByOpcode = new HashMap<String, Integer>(50);
	private String _configurationName = "Unknown Configuration";
	
	
	/* Constructors */
	public ProcessorConfiguration( String name, int aluCount, Map<String, Integer> cycleMap, int memCount)
	{
		_configurationName = name;
		_cyclesRequiredByOpcode = cycleMap;
		_aluCount = aluCount;
		_memCount = memCount;
	}

	public ProcessorConfiguration(int aluCount)
	{
		this("New Configuration", aluCount);
	}
	
	public ProcessorConfiguration(){
		this("New Configuration", 1);
	}
	
	public ProcessorConfiguration(String name, int aluCount) {
		_configurationName = name;
		_aluCount = aluCount;
		initHashMap();
	}
	
	private void initHashMap() {
		_cyclesRequiredByOpcode.put("add", 1);
		_cyclesRequiredByOpcode.put("sub", 1);
		_cyclesRequiredByOpcode.put("addi", 1);
		_cyclesRequiredByOpcode.put("lw", 2);
		_cyclesRequiredByOpcode.put("sw", 2);
		_cyclesRequiredByOpcode.put("and", 1);
		_cyclesRequiredByOpcode.put("or", 1);
		_cyclesRequiredByOpcode.put("nor", 1);
		_cyclesRequiredByOpcode.put("andi", 1);
		_cyclesRequiredByOpcode.put("ori", 1);
		_cyclesRequiredByOpcode.put("sll", 1);
		_cyclesRequiredByOpcode.put("srl", 1);
		_cyclesRequiredByOpcode.put("beq", 1);
		_cyclesRequiredByOpcode.put("bne", 1);
		_cyclesRequiredByOpcode.put("slt", 1);
		_cyclesRequiredByOpcode.put("sltu", 1);
		_cyclesRequiredByOpcode.put("slti", 1);
		_cyclesRequiredByOpcode.put("sltiu", 1);
		_cyclesRequiredByOpcode.put("j", 1);
		_cyclesRequiredByOpcode.put("jr", 1);
		_cyclesRequiredByOpcode.put("la", 1);
		_cyclesRequiredByOpcode.put("li", 1);
		_cyclesRequiredByOpcode.put("lb", 1);
		_cyclesRequiredByOpcode.put("sb", 1);
		_cyclesRequiredByOpcode.put("andi", 1);
		_cyclesRequiredByOpcode.put("ori", 1);
		_cyclesRequiredByOpcode.put("addiu", 1);
		_cyclesRequiredByOpcode.put("beqz", 1);
	}

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
	 * Returns the current Mapping of ALU Opcodes to Cycles Required.
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
	
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if(!(obj instanceof ProcessorConfiguration)) return false;
		ProcessorConfiguration other = (ProcessorConfiguration)obj;
		
		return _configurationName.equalsIgnoreCase(other.GetName()) && 
				 other.GetALUCount() == _aluCount &&
				other.GetCycleMap().equals(_cyclesRequiredByOpcode);
	}

	public int getMEMCount() {
		// TODO Auto-generated method stub
		return _memCount;
	}
}
