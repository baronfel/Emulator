/**
 * 
 */
package view;

import interfaces.IModelListener;
import model.ComponentStatus;
import model.ModelEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import controller.ProcessorController;

/**
 * @author Chester
 *
 */
public class MemView implements IModelListener{

	private ProcessorController controller;
	private Label memStatusLabel, memInstruction;
	private int mem;
	
	public MemView(Composite parent, ProcessorController control, int memNumber)
	{
		controller = control;
		mem = memNumber;
		Label memLabel = new Label(parent, SWT.None);
		ComponentStatus memStatus = controller.getMemStatus(mem);
		memLabel.setText(memStatus.getName());
		memStatusLabel = new Label(parent, SWT.None);
		memStatusLabel.setText(memStatus.getStatus());
		memInstruction = new Label(parent, SWT.None);
		memInstruction.setText(memStatus.getCurrentInstruction());
		
		controller.addIssueListener(this);
	}

	@Override
	public void modelChanged(ModelEvent aEvent) {
		ComponentStatus newStatus = controller.getMemStatus(mem);
		memStatusLabel.setText(newStatus.getStatus());
		memInstruction.setText(newStatus.getCurrentInstruction());
	}

}
