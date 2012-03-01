/**
 * 
 */
package utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.ProcessorConfiguration;
import com.thoughtworks.xstream.XStream;

/**
 * @author chusk3
 *
 * This class serializes objects, primarily the Processor Configuration, via the xstream library.
 */
public class Serializer {

	protected static XStream initXStream()
	{
		XStream streamer = new XStream();
		
		// Doing this makes the XML not have to worry with namespaces.
		streamer.alias("ProcessorConfiguration", ProcessorConfiguration.class);
		return streamer;
	}
	
	/**
	 * Given a processor configuration, returns the xml string that describes that configuration.
	 * @param config
	 * @return
	 */
	public static String serializeConfiguration(ProcessorConfiguration config)
	{
		return initXStream().toXML(config);
	}
	
	/**
	 * Writes a config to a specified File.
	 * @param config
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static void serializeConfigTo(File file, ProcessorConfiguration config) throws IOException {
		FileWriter writer = new FileWriter(file);
		writer.write(initXStream().toXML(config));
		writer.close();
	}

	/**
	 * Writes a config to a specified file Path.
	 * @param config
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static void serializeConfigTo(String filePath, ProcessorConfiguration config) throws IOException {
		FileWriter writer = new FileWriter(filePath);
		writer.write(initXStream().toXML(config));
		writer.close();
	}

	/**
	 * Given an xml string that describes a Processor Configuration, returns that configuration.
	 * @param xml
	 * @return
	 */
	public static ProcessorConfiguration deserializeMapFrom(String xml){
		return (ProcessorConfiguration) initXStream().fromXML(xml);
	}

	/**
	 * Given a File, returns the ProcessorConfiguration described by that File.
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static ProcessorConfiguration deserializeConfigFrom(File file) throws IOException {
		FileReader reader = new FileReader(file);
		ProcessorConfiguration result = (ProcessorConfiguration)initXStream().fromXML(reader);
		reader.close();
		return result;
	}

	/**
	 * Given a file path, returns the ProcessorConfiguration described at that path.
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static ProcessorConfiguration deserializeConfigFrom(String filePath) throws IOException {
		FileReader reader = new FileReader(filePath);
		ProcessorConfiguration result = (ProcessorConfiguration)initXStream().fromXML(reader);
		reader.close();
		return result;
	}
}
