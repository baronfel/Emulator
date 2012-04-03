/**
 * 
 */
package view;

import org.eclipse.swt.widgets.Composite;

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
		// TODO Auto-generated method stub
		
	}
}
