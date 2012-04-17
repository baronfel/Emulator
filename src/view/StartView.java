/**
 * 
 */

package view;

import model.Simulation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import utility.InstructionParser;

/**
 * @author Chet
 * 
 *         This class is the entry point for the app. When this is run, the main
 *         UI is set up and shown to the user.
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
	public StartView(final Shell shell) {

		// A display is an SWT session, multiple Screens can be hooked up to
		// one.
		Group host = new Group(shell, SWT.NONE);
		final ConfigurationView cView = new ConfigurationView(host, 0);
		host.setText("Configuration View");

		Group programArea = new Group(shell, SWT.NONE);
		GridData programLayoutData = new GridData(SWT.CENTER, SWT.CENTER, true,
				true);
		programArea.setLayoutData(programLayoutData);
		programArea.setLayout(new GridLayout(3, false));
		programArea.setText("Program to Run");

		GridData pathLayoutData = new GridData(SWT.CENTER, SWT.CENTER, false,
				false);
		final Text programPath = new Text(programArea, SWT.BORDER);
		programPath.setLayoutData(pathLayoutData);
		programPath.setText("Please select a program!");

		GridData selectorLayoutData = new GridData(SWT.RIGHT, SWT.CENTER,
				false, false);
		final Button programSelector = new Button(programArea, SWT.NONE);
		programSelector.setLayoutData(selectorLayoutData);
		programSelector.setText("...");
		programSelector.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fileChooseDialog = new FileDialog(shell);
				String selected = fileChooseDialog.open();

				if (selected != null) {
					programPath.setText(selected);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				FileDialog fileChooseDialog = new FileDialog(shell);
				String selected = fileChooseDialog.open();

				if (selected != null) {
					programPath.setText(selected);
				}
			}
		});

		Group nav = new Group(shell, SWT.NONE);
		GridData navLayoutData = new GridData(SWT.CENTER, SWT.CENTER, true,
				true);
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

				Simulation sim = new Simulation(cView.getConfig(),
						InstructionParser.LoadInstructions(programPath
								.getText()));
				simView = new SimulationView(sim, newShell);

				newShell.pack();
				newShell.open();
				shell.setVisible(false);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
	}
}
