/**
 * This view will display and accept input dealing with the standard simulation of the 
 * MIPS Simulator program.
 * @see JFrameView
 */


package view;

import org.eclipse.swt.widgets.Composite;

import model.Simulation;

public class SimulationView{
	
	private Simulation sim;
	
	public SimulationView(Simulation mySim, final Composite parent)
	{
		sim = mySim;
	}
	
}