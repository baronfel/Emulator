/**
 * Handles interactions between the MIPS Simulator Model and the Configuration View.
 * @see AbstractController
 * @see Simulation
 * @see ConfigurationView
 */

package controller;

import model.ProcessorConfiguration;
import interfaces.IModel;

public class ConfigurationController extends AbstractController {

	public ConfigurationController(ProcessorConfiguration model) {
		super(model);
	}

	@Override
	public void setModel(IModel aModel) {
		// TODO Auto-generated method stub
		
	}
}