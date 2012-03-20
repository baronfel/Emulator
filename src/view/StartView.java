/**
 * 
 */

package view;

import java.util.Arrays;

import model.Simulation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import utility.InstructionParser;


/**
 * @author Chet
 *
 * This class is the entry point for the app.  When this is run, the main UI is set up and shown to the user.
 */
public class StartView {

	private static String APPNAME = "MIPS Emulator";
	private static ConfigurationView cView;
	private static SimulationView simView;
	private static ResultsView resultsView;
	private static Object currentView;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// A display is an SWT session, multiple Screens can be hooked up to one.
		Display display = new Display();
		
		// Grid with two columns, and all equal width
		GridLayout startViewLayout = new GridLayout(2, true);
				
		final Shell shell = new Shell(display);
		shell.setText(APPNAME);
		shell.setLayout(startViewLayout);
		
		final Group cGroup = new Group(shell, SWT.NONE);
		cGroup.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 2, 1));
		cGroup.setText("Emulator Configuration");
		
		final Group sGroup = new Group(shell, SWT.NONE);
		sGroup.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 2, 1));
		sGroup.setVisible(false);

		sGroup.setText("Simulation");
		
		final Group rGroup = new Group(shell, SWT.NONE);
		rGroup.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 2, 1));
		rGroup.setVisible(false);
		rGroup.setText("Results");
		
		final Button back = new Button(shell, SWT.NONE);
		back.setText("Back");
		// the back button is disabled when on the first page.
		back.setEnabled(false);
		back.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		
		final Button next = new Button(shell, SWT.NONE);
		next.setText("Next");
		next.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

		
		// Going to do a Wizard type view
		// Stage 1
		cView = new ConfigurationView(cGroup, 0);
		currentView = cView;
		
		back.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// if we are in the simulationView we need to go back to the config view
				if(currentView == simView)
				{
					cView = new ConfigurationView(cGroup, 0, simView.getController().getProcessorConfig());
					sGroup.setVisible(false);
					cGroup.setVisible(true);
					currentView = cView;
					back.setEnabled(false);
				} else // we must be in results view
				{
					simView = new SimulationView(sGroup, 0, resultsView.getController().getProcessorConfig(), resultsView.getController().getProgram());
					rGroup.setVisible(false);
					sGroup.setVisible(true);
					currentView = simView;
					next.setEnabled(true);
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		
		next.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(currentView == cView)
				{
					InstructionParser parser = new InstructionParser();
					simView = new SimulationView(sGroup, 0, new Simulation(cView.getConfig(), parser.LoadInstructions(cView.getConfig().GetName())));
					cGroup.setVisible(false);
					sGroup.setVisible(true);
					currentView = simView;
					back.setEnabled(true);
				} else
				{
					resultsView = new ResultsView(rGroup, 0, simView.getController().GetBenchmarkResult());
					sGroup.setVisible(false);
					rGroup.setVisible(true);
					currentView = resultsView;
					next.setEnabled(false);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		
		shell.pack();
		shell.open();
		while(!shell.isDisposed())
		{
			if(!display.readAndDispatch())
			{
				display.sleep();
			}
		}
		display.dispose();
	}

}
