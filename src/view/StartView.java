/**
 * 
 */

package view;

import model.ProcessorConfiguration;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.*;


/**
 * @author Chet
 *
 * This class is the entry point for the app.  When this is run, the main UI is set up and shown to the user.
 */
public class StartView {

	private static String APPNAME = "MIPS Emulator";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// A display is an SWT session, multiple Screens can be hooked up to one.
		Display display = new Display();
		GridLayout gLayout = new GridLayout();
		gLayout.numColumns = 2;
		
		Shell shell = new Shell(display);
		shell.setText(APPNAME);
		shell.setLayout(gLayout);
		
		final ProcessorConfiguration editableConfig = new ProcessorConfiguration();
		
		Label nameLabel = new Label(shell, SWT.BORDER);
		nameLabel.setText("Configuration Name: ");		
		
		Text configurationName = new Text(shell, SWT.BORDER);
		configurationName.setText(editableConfig.GetName());
		
		Label opLabel = new Label(shell, SWT.BORDER);
		opLabel.setText("Operation name: ");
		
		final Combo comboBox = new Combo(shell, SWT.BORDER);
		comboBox.select(0);
		
		Label cycleCountLabel = new Label(shell, SWT.BORDER);
		cycleCountLabel.setText("Cycles to complete: ");		
		final Text opCycleCount = new Text(shell, SWT.BORDER);
		opCycleCount.setText(Integer.toString(editableConfig.GetCycleMap().get("sub")));
		
		comboBox.setItems(editableConfig.GetCycleMap().keySet().toArray(new String[editableConfig.GetCycleMap().size()]));
		
		comboBox.addSelectionListener(new SelectionAdapter() {		
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Backup the new value of the selected item
				editableConfig.AddCycleMapping(comboBox.getText(), Integer.parseInt((opCycleCount.getText())));
				opCycleCount.setText(editableConfig.GetCycleMap().get(comboBox.getText()).toString());
			}
		});
		
		
		
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
