/**
 * 
 */
package view;

import interfaces.IModelListener;
import model.ComponentStatus;
import model.ModelEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import controller.ProcessorController;

/**
 * @author Chester
 *
 */
public class WriteBackView implements IModelListener {
	
	private ProcessorController controller;
	private Label writebackStatusLabel, writebackInstruction;
	
	public WriteBackView(Composite parent, ProcessorController control)
	{
		controller = control;
		Label writebackLabel = new Label(parent, SWT.None);
		ComponentStatus writebackStatus = controller.getWritebackStatus();
		writebackLabel.setText(writebackStatus.getName());
		writebackStatusLabel = new Label(parent, SWT.None);
		writebackStatusLabel.setText(writebackStatus.getStatus());
		writebackInstruction = new Label(parent, SWT.None);
		writebackInstruction.setText(writebackStatus.getCurrentInstruction());
		
		controller.addIssueListener(this);
	}

	@Override
	public void modelChanged(ModelEvent aEvent) {
		ComponentStatus newStatus = controller.getWritebackStatus();
		writebackStatusLabel.setText(newStatus.getStatus());
		writebackInstruction.setText(newStatus.getCurrentInstruction());
	}
}
