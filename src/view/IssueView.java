/**
 * 
 */
package view;

import model.ComponentStatus;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import controller.ProcessorController;

/**
 * @author Chester
 *
 */
public class IssueView {
	public IssueView(Composite parent, ProcessorController controller)
	{
		Label issueLabel = new Label(parent, SWT.None);
		ComponentStatus issueStatus = controller.GetIssueStatus();
		issueLabel.setText(issueStatus.getName());
		Label issueStatusLabel = new Label(parent, SWT.None);
		issueStatusLabel.setText(issueStatus.getStatus());
		Label issueInstruction = new Label(parent, SWT.None);
		issueInstruction.setText(issueStatus.getCurrentInstruction());
	}
	
}
