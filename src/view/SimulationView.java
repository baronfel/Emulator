/**
 * This view will display and accept input dealing with the standard simulation of the 
 * MIPS Simulator program.
 * @see JFrameView
 */

package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import controller.ProcessorController;
import controller.RegisterController;

import model.Registry;
import model.Simulation;

public class SimulationView {

	private Simulation sim;

	public SimulationView(Simulation mySim, final Composite parent) {
		sim = mySim;
		InitControls(parent);
	}

	private void InitControls(final Composite parent) {
		Label mainContainer = new Label(parent, SWT.NONE);
		mainContainer.setText("Simulation");
		makeRegistrySection(parent.getShell(),
				new RegisterController(sim.getRegistry()));
		makeProcessorSection(parent.getShell(),
				new ProcessorController(sim.getProcessor()));
		makePlayBackSection(parent.getShell());
		makeNavigationSection(parent.getShell());
	}

	private void makePlayBackSection(Shell shell) {
		GridLayout gLayout = new GridLayout(2, false);
		Group navGroup = new Group(shell, SWT.None);
		navGroup.setLayout(gLayout);
		navGroup.setText("Play/Pause");

		Button cycleOnce = new Button(navGroup, SWT.None);
		cycleOnce.setText("|>");
		cycleOnce.setToolTipText("Perform one processor cycle.");
		cycleOnce.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				sim.Cycle();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				sim.Cycle();
			}
		});

		Button cycleToEnd = new Button(navGroup, SWT.None);
		cycleToEnd.setText("|>>");
		cycleToEnd.setToolTipText("Run the entire simulation");
		cycleToEnd.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				sim.cycleToEnd();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				sim.cycleToEnd();
			}
		});
	}

	private void makeNavigationSection(final Shell shell) {
		GridLayout gLayout = new GridLayout(2, false);
		Group navGroup = new Group(shell, SWT.None);
		navGroup.setLayout(gLayout);

		Button back = new Button(navGroup, SWT.None);
		back.setText("Back");
		back.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				goToStartView(shell);
				shell.setVisible(false);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

		Button next = new Button(navGroup, SWT.None);
		next.setText("Next");
		next.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				goToResultsView(shell);
				shell.setVisible(false);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});

	}

	private void makeProcessorSection(Shell shell,
			ProcessorController processorController) {
		new ProcessorView(shell, new ProcessorController(sim.getProcessor()));
	}

	private void makeRegistrySection(Composite parent,
			RegisterController registry) {
		Group regGroup = new Group(parent, SWT.NONE);
		regGroup.setText("Registry");
		regGroup.setLayout(new GridLayout(16, false));

		for (int i = 0; i < 32; i++) {
			new RegisterView(regGroup, registry, i);
		}
	}

	private void goToStartView(Shell shell) {
		// TODO Auto-generated method stub

	}

	private void goToResultsView(Shell shell) {
	}

}