/**
 * 
 */

package view;

import model.Simulation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import com.sun.xml.internal.ws.api.pipe.NextAction;

import utility.InstructionParser;


/**
 * @author Chet
 *
 * This class is the entry point for the app.  When this is run, the main UI is set up and shown to the user.
 */
public class StartView {


	
	/**
	 * @param args
	 */
	public StartView(final Shell shell) {
		
		// A display is an SWT session, multiple Screens can be hooked up to one.
		Group host = new Group(shell, SWT.NONE);
		final ConfigurationView cView = new ConfigurationView(host, 0);
		host.setText("Configuration View");
		
		Group nav = new Group(shell, SWT.NONE);
		GridData navLayoutData = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		nav.setLayoutData(navLayoutData);
		nav.setLayout(new GridLayout(1, false));
		
		GridData nextData = new GridData(SWT.CENTER, SWT.CENTER, false, false);
		Button nextButton = new Button(nav, SWT.NONE);
		nextButton.setLayoutData(nextData);
		nextButton.setSize(50, 20);
		nextButton.setText("Next");
		nextButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Shell newShell = new Shell(shell.getDisplay());
				newShell.setText(shell.getText());
				newShell.setLayout(shell.getLayout());
				
				Group newGroup = new Group(shell, SWT.NONE);
				newGroup.setText("Simulation");
				
				Simulation sim = new Simulation(cView.getConfig(), InstructionParser.LoadInstructions(cView.getConfig().GetName()));
				SimulationView simView = new SimulationView(sim, newGroup);
				
				newShell.pack();
				newShell.open();
				
				shell.setVisible(false);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
	}

}
