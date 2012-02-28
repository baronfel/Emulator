/**
 * Handles interactions between the MIPS Simulator Model and the Configuration View.
 * @see AbstractController
 * @see Simulation
 * @see ConfigurationView
 */

package controller;

import java.io.IOException;

import utility.Serializer;
import model.ProcessorConfiguration;
import interfaces.IModel;

public class ConfigurationController extends AbstractController {

	private ProcessorConfiguration model;
	
	public ConfigurationController(ProcessorConfiguration model) {
		super(model);
		this.model = model;
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
		String pathToSave = ".\\" + model.GetName() + ".config";
		try {
			Serializer.serializeConfigTo(pathToSave, model);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Loads the configuration located at the given path.
	 * @param selected
	 * @return
	 */
	public ProcessorConfiguration SelectNewConfig(String path) {
		try {
			model = Serializer.deserializeConfigFrom(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
}