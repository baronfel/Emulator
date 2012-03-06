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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import controller.ConfigurationController;

public class ConfigurationView{
	
	private ConfigurationController controller;
	Combo comboBox;
	Text opCycleCount;
	Text configurationName;
	Button chooseConfig;
	Button saveConfig;
	String previousOpName;
	
	public ConfigurationView(final Composite parent, int style) {
		controller = new ConfigurationController();
		initControls(parent);
	}
	
	private void initControls(final Composite parent)
	{
		GridLayout gLayout = new GridLayout();
		gLayout.numColumns = 2;
		parent.setLayout(gLayout);
		
		
		// Name Controls
		Label nameLabel = new Label(parent, SWT.BORDER);
		nameLabel.setText("Configuration Name: ");		
		
		final Text configurationName = new Text(parent, SWT.BORDER);
		configurationName.setText(controller.getName());
		configurationName.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				controller.setName(configurationName.getText());
			}
		});
		
		// Operation Cycle Count Controls
		Label opLabel = new Label(parent, SWT.BORDER);
		opLabel.setText("Operation name: ");
		
		final Combo comboBox = new Combo(parent, SWT.BORDER);
		comboBox.setItems(controller.getItemArray());
		comboBox.select(0);
		previousOpName = comboBox.getText();
		
		Label cycleCountLabel = new Label(parent, SWT.BORDER);
		cycleCountLabel.setText("Cycles to complete: ");		
		final Text opCycleCount = new Text(parent, SWT.BORDER);
		opCycleCount.setText(Integer.toString(controller.getCycleCountFor("sub")));
		
		comboBox.addSelectionListener(new SelectionAdapter() {		
			@Override
			public void widgetSelected(SelectionEvent e) {
				controller.addCycleMapping(previousOpName, Integer.parseInt((opCycleCount.getText())));
				opCycleCount.setText(controller.getCycleCountFor(comboBox.getText()).toString());
				previousOpName = comboBox.getText();
			}
		});
		
		// ALU Count Controls
		Label aluCountLabel = new Label(parent, SWT.BORDER);
		aluCountLabel.setText("Processor ALU Count: ");		
		final Text aluCount = new Text(parent, SWT.BORDER);
		aluCount.setText(Integer.toString(controller.getAluCount()));
		aluCount.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				int possible = controller.getAluCount();
				try
				{
					possible = Integer.parseInt(aluCount.getText());
				}
				finally{}
				controller.setAluCount(possible);
			}
		});
		
		// Save/Load Controls
		Button loadConfigBtn = new Button(parent, SWT.BORDER);
		loadConfigBtn.setText("Load Configuration...");
		loadConfigBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SelectConfig(parent.getShell());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				SelectConfig(parent.getShell());
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
			controller.SelectNewConfig(selected);
		}
	}
}