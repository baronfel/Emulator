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
		Shell shell = new Shell(display);
		shell.setText(APPNAME);
		shell.setLayout(new FillLayout(SWT.VERTICAL));
		
		final ProcessorConfiguration editableConfig = new ProcessorConfiguration();
		
		Text configurationName = new Text(shell, SWT.BORDER);
		configurationName.setText(editableConfig.GetName());
		final Combo comboBox = new Combo(shell, SWT.BORDER);
		final Text opCycleCount = new Text(shell, SWT.BORDER);
		
		comboBox.setItems(editableConfig.GetCycleMap().keySet().toArray(new String[editableConfig.GetCycleMap().size()]));
		
		comboBox.addSelectionListener(new SelectionAdapter() {		
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Backup the new value of the selected item
				editableConfig.AddCycleMapping(e.data.toString(), Integer.parseInt((opCycleCount.getText())));
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
