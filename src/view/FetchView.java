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
public class FetchView implements IModelListener{

	Label issueLabel, issueStatusLabel, issueInstruction;
	ProcessorController control;
	
	public FetchView(Composite parent, ProcessorController controller) {
		control = controller;
		issueLabel = new Label(parent, SWT.None);
		ComponentStatus fetchStatus = control.getFetchStatus();
		issueLabel.setText(fetchStatus.getName());
		issueStatusLabel = new Label(parent, SWT.None);
		issueStatusLabel.setText(fetchStatus.getStatus());
		issueInstruction = new Label(parent, SWT.None);
		issueInstruction.setText(fetchStatus.getCurrentInstruction());
		
		controller.addFetchListener(this);
	}

	@Override
	public void modelChanged(ModelEvent aEvent) {
		ComponentStatus newStatus = control.getFetchStatus();
		issueStatusLabel.setText(newStatus.getStatus());
		issueInstruction.setText(newStatus.getCurrentInstruction());
	}
	
	public void unhook()
	{
		control.removeFetchListener(this);
	}

}
