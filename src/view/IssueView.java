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
public class IssueView implements IModelListener {
	
	private ProcessorController controller;
	private Label issueStatusLabel, issueInstruction;
	
	public IssueView(Composite parent, ProcessorController control)
	{
		controller = control;
		Label issueLabel = new Label(parent, SWT.None);
		ComponentStatus issueStatus = controller.getIssueStatus();
		issueLabel.setText(issueStatus.getName());
		issueStatusLabel = new Label(parent, SWT.None);
		issueStatusLabel.setText(issueStatus.getStatus());
		issueInstruction = new Label(parent, SWT.None);
		issueInstruction.setText(issueStatus.getCurrentInstruction());
		
		controller.addIssueListener(this);
	}

	@Override
	public void modelChanged(ModelEvent aEvent) {
		ComponentStatus newStatus = controller.getIssueStatus();
		issueStatusLabel.setText(newStatus.getStatus());
		issueInstruction.setText(newStatus.getCurrentInstruction());
	}
}
