/**
 * 
 */
package view;

import model.BenchmarkResult;
import model.Simulation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import controller.ResultsController;

/**
 * @author Chet
 *
 */
public class ResultsView {

	protected ResultsController controller;

	public ResultsView(final Composite parent, int i) {
		controller = new ResultsController();
	}
	
	public ResultsView(final Composite parent, int i, BenchmarkResult result)
	{
		controller = new ResultsController(result);
		InitControls(parent);
	}

	private void InitControls(final Composite parent) {
		Label mainContainer = new Label(parent, SWT.NONE);
		mainContainer.setText("Results");
		
		Label cycleText = new Label(parent, SWT.None);
		cycleText.setText("Cycles to Finish: " + controller.getCyclesToFinish());
		

		Label noopText = new Label(parent, SWT.None);
		noopText.setText("Noops during execution: " + controller.getNoops());
		
		Label memoryLabel = new Label(parent, SWT.None);
		memoryLabel.setText("Memory at finish (First 20 positions)");
		
		for(int i = 0; i <20; i++)
		{
			Label l = new Label(parent, SWT.None);
			l.setText(String.valueOf(controller.getMemoryValueAt(i)));
		}
		
		Button back = new Button(parent, SWT.None);
		back.setText("Back");
		back.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				goToSimView(parent.getShell());
				parent.getShell().setVisible(false);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
	}

	protected void goToSimView(Shell shell) {
		Shell newShell = new Shell(shell.getDisplay());
		newShell.setText(shell.getText());
		newShell.setLayout(shell.getLayout());
		
		SimulationView view = new SimulationView(controller.getSimulation(), newShell);
		
		newShell.pack();
		newShell.open();
	}

	public ResultsController getController() {
		return controller;
	}

}
