/**
 * This view will display and accept input dealing with the standard simulation of the 
 * MIPS Simulator program.
 * @see JFrameView
 */


package view;

import interfaces.IController;

import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import model.ModelEvent;
import model.Simulation;
import controller.StepByStepController;

public class SimulationView extends JFrameView {
	public SimulationView(IController aController) {
		super(aController);
		// TODO Auto-generated constructor stub
	}
	private JTextField _textField = new JTextField();
	public view.SimulationView.Handler _unnamed_Handler_;

	public void MIPSSIMView(Simulation aModel, StepByStepController aController) {
		throw new UnsupportedOperationException();
	}


	/**
	 * This method handles events that the model sends to the view and updates the view.
	 */
	public void modelChanged(ModelEvent aEvent) {
		throw new UnsupportedOperationException();
	}

	/**
	 * This is the first function to be called when you run the program. 
	 * It should probably do something.
	 */
	public static void main(String[] aArgs) {
		throw new UnsupportedOperationException();
	}
	/**
	 * This is the handler class that accepts user input onto a view and notifies a controller.
	 */
	class Handler {
		public StepByStepView _unnamed_StepByStepView_;
		public ConfigurationView _unnamed_ConfigurationView_;
		public SimulationView _unnamed_SimulationView_;

		/**
		 * This method does something whenever the user gives input.
		 */
		public void actionPerformed(ActionEvent aE) {
			throw new UnsupportedOperationException();
		}
	}
}