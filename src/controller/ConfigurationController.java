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
	}

	@Override
	public void setModel(IModel aModel) {
		model = (ProcessorConfiguration) aModel;
	}
	
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

	public ProcessorConfiguration SelectNewConfig(String selected) {
		try {
			model = Serializer.deserializeConfigFrom(selected);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
}