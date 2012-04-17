/**
 * 
 */
package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import controller.ProcessorController;

/**
 * @author Chester
 *
 */
public class ProcessorView {
	
	private ProcessorController controller;
	
	public ProcessorView(final Composite parent, ProcessorController processorController) 
	{
		controller = processorController;
		InitControls(parent);
	}

	private void InitControls(final Composite parent) {
		GridLayout layout = new GridLayout(3, false);
		Group procGroup = new Group(parent, SWT.None);
		procGroup.setLayout(layout);
		procGroup.setText("Processor Status");
		
		// We'll create a table-type view for the processor.
		// Component Name - Status - Current Instruction
		new IssueView(procGroup, controller);
		
	}
}
