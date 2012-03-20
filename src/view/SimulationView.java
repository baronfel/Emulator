/**
 * This view will display and accept input dealing with the standard simulation of the 
 * MIPS Simulator program.
 * @see JFrameView
 */


package view;

import model.ProcessorConfiguration;
import model.Simulation;

import org.eclipse.swt.widgets.Composite;

import utility.Package;

public class SimulationView {
	
	private Simulation controller;

	public SimulationView(final Composite group, int i) {
		// TODO Auto-generated constructor stub
	}

	public SimulationView(final Composite group, int i,	ProcessorConfiguration processorConfig, Package instructions) {
		controller = new Simulation(processorConfig, instructions);
	}
	
	public SimulationView(final Composite group, int i, Simulation sim)
	{
		controller = sim;
	}

	public Simulation getController() {
		return controller;
	}
}