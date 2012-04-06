/**
 * 
 */
package utility.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import model.ProcessorConfiguration;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import utility.Serializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author chusk3
 *
 */

public class SerializerTest {

	private static String defaultConfigString;
	private static String filledConfigurationString;
	
	@BeforeClass
	public static void prepareStrings()
	{
		try{
			defaultConfigString = getFileContents("DefaultConfig.test");
			filledConfigurationString = getFileContents("FilledConfig.test");
		} catch (Exception e){
			
		}
	}
	
	private static String getFileContents(String fileName)
	{
		FileInputStream fin;
		try {
			fin = new FileInputStream(fileName);
			FileChannel fch = fin.getChannel();
			// map the contents of the file into ByteBuffer
			ByteBuffer byteBuff;
			try {
				byteBuff = fch.map(FileChannel.MapMode.READ_ONLY, 0, fch.size());
				// convert ByteBuffer to CharBuffer
				// CharBuffer chBuff = Charset.defaultCharset().decode(byteBuff);
				CharBuffer chBuff = Charset.forName("UTF-8").decode(byteBuff);
				return chBuff.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@Test
	public void DefaultConfigSerializesCorrectly() {
		ProcessorConfiguration config = new ProcessorConfiguration();
		String actual = Serializer.serializeConfiguration(config);
		assertEquals(defaultConfigString, actual);
	}
	
	@Test
	@Ignore
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
