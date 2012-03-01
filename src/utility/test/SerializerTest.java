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

	private String defaultConfigString = "<ProcessorConfiguration>\n  <__aluCount>1</__aluCount>\n  <__cyclesRequiredByOpcode>\n    <entry>\n      <string>sub</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>bneq</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>sltu</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>slt</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>stow</string>\n      <int>2</int>\n    </entry>\n    <entry>\n      <string>add</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>ori</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>andi</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>lodw</string>\n      <int>2</int>\n    </entry>\n    <entry>\n      <string>addi</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>shr</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>jmp</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>sltiu</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>slti</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>shl</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>or</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>jmpr</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>beq</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>and</string>\n      <int>1</int>\n    </entry>\n    <entry>\n      <string>nor</string>\n      <int>1</int>\n    </entry>\n  </__cyclesRequiredByOpcode>\n  <__configurationName>New Configuration</__configurationName>\n</ProcessorConfiguration>";
	private String filledConfigurationString = "<ProcessorConfiguration>\n  <__aluCount>10</__aluCount>\n  <__cyclesRequiredByOpcode>\n    <entry>\n      <string>add immediate</string>\n      <int>2</int>\n    </entry>\n  </__cyclesRequiredByOpcode>\n  <__configurationName>Filled Configuration</__configurationName>\n</ProcessorConfiguration>";
	
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
	
	@Test
	public void SerializedDefaultConfigEqualsDefaultConfigObject()
	{
		ProcessorConfiguration newConfig = Serializer.deserializeConfigFromXML(defaultConfigString);
		assertTrue(new ProcessorConfiguration().equals(newConfig));
	}
	
	@Test
	public void SerializedPopulatedConfigEqualsPopulatedConfigString()
	{
		Map<String, Integer> cycleMap = new HashMap<String, Integer>();
		cycleMap.put("add immediate", 2);
		
		ProcessorConfiguration newConfig = Serializer.deserializeConfigFromXML(filledConfigurationString);
		ProcessorConfiguration expected = new ProcessorConfiguration("Filled Configuration", 10, cycleMap);
		
		assertTrue(expected.equals(newConfig));
	}

}
