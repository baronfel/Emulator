/**
 * Handles interactions between the MIPS Simulator Model and the Simulation View.
 * @see AbstractController
 * @see Simulation
 * @see SimulationView
 */

package controller;

import interfaces.IModel;

public class SimulationController extends AbstractController {

	public SimulationController(IModel model) {
		super(model);
	}

	public void operation(String aOption) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setModel(IModel aModel) {
		// TODO Auto-generated method stub
		
	}
}