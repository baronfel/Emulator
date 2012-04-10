/**
 * This view will display and accept input dealing with the standard simulation of the 
 * MIPS Simulator program.
 * @see JFrameView
 */


package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import controller.ProcessorController;
import controller.RegisterController;

import model.Simulation;

public class SimulationView{
	
	private Simulation sim;
	
	public SimulationView(Simulation mySim, final Composite parent)
	{
		sim = mySim;
		InitControls(parent);
	}
	
	private void InitControls(final Composite parent)
	{
		Group mainContainer = new Group(parent, SWT.NONE);
		mainContainer.setText("Simulation");
		RegistryView regView = new RegistryView(parent, new RegisterController(sim.getRegistry()));
		ProcessorView procView = new ProcessorView(parent, new ProcessorController(sim.getProcessor()));
	}
	
	
	
}