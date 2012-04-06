/**
 * Handles interactions between the MIPS Simulator Model and the Configuration View.
 * @see AbstractController
 * @see Simulation
 * @see ConfigurationView
 */

package controller;

import interfaces.IModel;

import java.io.IOException;

import model.ProcessorConfiguration;
import utility.Serializer;

public class ConfigurationController extends AbstractController {

	private static ProcessorConfiguration model = new ProcessorConfiguration();
	
	public ConfigurationController() {
		super(model);
	}
	
	public ConfigurationController(ProcessorConfiguration config)
	{
		super(config);
		model = config;
	}

	@Override
	public void setModel(IModel aModel) {
		model = (ProcessorConfiguration) aModel;
	}
	
	/**
	 * Saves the current internal configuration to the default config directory.
	 */
	public void SaveConfig()
	{
		String pathToSave = model.GetName() + ".config";
		try {
			Serializer.serializeConfigTo(pathToSave, model);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads the configuration located at the given path.
	 * @param selected
	 * @return
	 */
	public void SelectNewConfig(String path) {
		try {
			
			model = Serializer.deserializeConfigFrom(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the ALU count for the underlying model.
	 * @param count
	 */
	public void setAluCount(int count) {
		model.SetALUCount(count);
	}

	/**
	 * Sets the Configuration name for the underlying model
	 * @param newName
	 */
	public void setName(String newName) {
		model.SetName(newName);
	}

	/**
	 * Accessor for underlying model alu count
	 * @return
	 */
	public int getAluCount() {
		return model.GetALUCount();
	}

	/**
	 * Lets views add a new cycle count mapping
	 * @param opName
	 * @param cycles
	 */
	public void addCycleMapping(String opName, int cycles) {
		model.AddCycleMapping(opName, cycles);
	}
	
	
	/**
	 * View accessor for the Config name.
	 * @return
	 */
	public String getName() {
		return model.GetName();
	}

	/**
	 * Returns the list of operations this config supports.
	 * @return
	 */
	public String[] getItemArray() {
		return model.GetCycleMap().keySet().toArray(new String[model.GetCycleMap().size()]);
	}

	public Integer getCycleCountFor(String opName) {
		return (Integer)model.GetCycleMap().get(opName);
	}
	
	public ProcessorConfiguration getConfig()
	{
		return model;
	}
}