/**
 * This view will display and accept input dealing with the configuration of the MIPS Simulator
 * program.
 * 
 * It'll have a reference to a ProcessorConfiguration that it will change as necessary, and report when the Simulation is started.
 * It'll also have a textbox and file chooser to choose a pre-existing configuration.
 * It'll also have a save button for the configuration.
 * 
 */


package view;

import model.ProcessorConfiguration;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class ConfigurationView extends Composite {

	private ProcessorConfiguration editableConfig;
	Combo comboBox;
	Text opCycleCount;
	Text configurationName;
	Button chooseConfig;
	Button saveConfig;
	
	public ConfigurationView(Composite parent, int style) {
		super(parent, style);
		editableConfig = new ProcessorConfiguration();
		
		configurationName = new Text(this, SWT.BORDER);
		configurationName.setText(editableConfig.GetName());
		comboBox = new Combo(this, SWT.BORDER);
		opCycleCount = new Text(this, SWT.BORDER);
		
		
		for (String opName : editableConfig.GetCycleMap().keySet())
		{
			comboBox.add(opName);
		}
		
		comboBox.addSelectionListener(new SelectionAdapter() {		
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Backup the new value of the selected item
				editableConfig.AddCycleMapping(e.data.toString(), Integer.parseInt((opCycleCount.getText())));
				opCycleCount.setText(editableConfig.GetCycleMap().get(comboBox.getText()).toString());
			}
		});
	}
	
}