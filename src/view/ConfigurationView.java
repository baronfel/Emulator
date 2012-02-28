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

import java.util.ArrayList;

import model.ProcessorConfiguration;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import controller.ConfigurationController;

public class ConfigurationView {

	private ProcessorConfiguration editableConfig;
	private ConfigurationController controller;
	Combo comboBox;
	Text opCycleCount;
	Text configurationName;
	Button chooseConfig;
	Button saveConfig;
	String previousOpName;
	
	public ConfigurationView(final Shell parent, int style) {
		
		editableConfig = new ProcessorConfiguration();
		controller = new ConfigurationController(editableConfig);
		
		initControls(parent);
	}
	
	private void initControls(final Shell parent)
	{
		GridLayout gLayout = new GridLayout();
		gLayout.numColumns = 2;
		parent.setLayout(gLayout);
		
		Label nameLabel = new Label(parent, SWT.BORDER);
		nameLabel.setText("Configuration Name: ");		
		
		Text configurationName = new Text(parent, SWT.BORDER);
		configurationName.setText(editableConfig.GetName());
		
		Label opLabel = new Label(parent, SWT.BORDER);
		opLabel.setText("Operation name: ");
		
		final Combo comboBox = new Combo(parent, SWT.BORDER);
		comboBox.setItems(editableConfig.GetCycleMap().keySet().toArray(new String[editableConfig.GetCycleMap().size()]));
		comboBox.select(0);
		previousOpName = comboBox.getText();
		
		Label cycleCountLabel = new Label(parent, SWT.BORDER);
		cycleCountLabel.setText("Cycles to complete: ");		
		final Text opCycleCount = new Text(parent, SWT.BORDER);
		opCycleCount.setText(Integer.toString(editableConfig.GetCycleMap().get("sub")));
		
		comboBox.addSelectionListener(new SelectionAdapter() {		
			@Override
			public void widgetSelected(SelectionEvent e) {
				editableConfig.AddCycleMapping(previousOpName, Integer.parseInt((opCycleCount.getText())));
				opCycleCount.setText(editableConfig.GetCycleMap().get(comboBox.getText()).toString());
				previousOpName = comboBox.getText();
			}
		});
		
		Button loadConfigBtn = new Button(parent, SWT.BORDER);
		loadConfigBtn.setText("Load Configuration...");
		loadConfigBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SelectConfig(parent);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				SelectConfig(parent);
			}
		});
		
		Button saveConfigBtn = new Button(parent, SWT.BORDER);
		saveConfigBtn.setText("Save Current Configuration");
		saveConfigBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controller.SaveConfig();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				controller.SaveConfig();
			}
		});
	}
	
	/**
	 * Opens a FileDialog to choose a config on the system to load into the simulation.
	 * @param parent
	 */
	public void SelectConfig(Shell parent)
	{
		FileDialog fileChooseDialog = new FileDialog(parent);
		ArrayList<String> validExtensions = new ArrayList<String>(5);
		validExtensions.add(".config");
		fileChooseDialog.setFilterExtensions(validExtensions.toArray(new String[5]));
		String selected = fileChooseDialog.open();
		
		if(selected != null)
		{
			editableConfig = controller.SelectNewConfig(selected);
		}
	}
}