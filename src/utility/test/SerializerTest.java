/**
 * 
 */
package utility.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import model.ProcessorConfiguration;

import org.junit.Test;

import utility.Serializer;

/**
 * @author chusk3
 *
 */
public class SerializerTest {

	private String defaultConfigString = "";
	private String filledConfigurationString = "";
	
	@Test
	public void DefaultConfigSerializesCorrectly() {
		ProcessorConfiguration config = new ProcessorConfiguration();
		String actual = Serializer.serializeConfiguration(config);
		assertEquals(defaultConfigString, actual);
	}
	
	@Test
	public void PopulatedConfigSerializesCorrectly()
	{
		Map<String, Integer> cycleMap = new HashMap<String, Integer>();
		cycleMap.put("add immediate", 2);
		ProcessorConfiguration config = new ProcessorConfiguration("Filled Configuration", 10, cycleMap);
		String actual = Serializer.serializeConfiguration(config);
		assertEquals(filledConfigurationString, actual);
	}

}
