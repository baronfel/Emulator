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
public class ALUView implements IModelListener {

	private ProcessorController controller;
	private Label aluStatusLabel, aluInstruction;
	private int alu;
	
	public ALUView(Composite parent, ProcessorController control, int aluNumber)
	{
		controller = control;
		alu = aluNumber;
		Label aluLabel = new Label(parent, SWT.None);
		ComponentStatus aluStatus = controller.getALUStatus(alu);
		aluLabel.setText(aluStatus.getName());
		aluStatusLabel = new Label(parent, SWT.None);
		aluStatusLabel.setText(aluStatus.getStatus());
		aluInstruction = new Label(parent, SWT.None);
		aluInstruction.setText(aluStatus.getCurrentInstruction());
		
		controller.addIssueListener(this);
	}

	@Override
	public void modelChanged(ModelEvent aEvent) {
		ComponentStatus newStatus = controller.getALUStatus(alu);
		aluStatusLabel.setText(newStatus.getStatus());
		aluInstruction.setText(newStatus.getCurrentInstruction());
	}
}
