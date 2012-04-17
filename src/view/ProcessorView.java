/**
 * 
 */
package view;

import interfaces.IALU;
import interfaces.IMemoryAccess;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

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
		
		Label nameHeader = new Label(procGroup, SWT.UNDERLINE_SINGLE);
		nameHeader.setText("NAME");
		Label statusHeader = new Label(procGroup, SWT.UNDERLINE_SINGLE);
		statusHeader.setText("STATUS");
		Label instrHeader = new Label(procGroup, SWT.UNDERLINE_SINGLE);
		instrHeader.setText("CURRENT INSTRUCTION");
		
		// We'll create a table-type view for the processor.
		// Component Name - Status - Current Instruction
		new FetchView(procGroup, controller);
		new IssueView(procGroup, controller);
		for(IALU alu : controller.getALUs())
		{
			new ALUView(procGroup, controller, alu.getALUNumber());
		}
		for(IMemoryAccess mem : controller.getMemUnits())
		{
			new MemView(procGroup, controller, mem.getMEMNumber());
		}
		new WriteBackView(procGroup, controller);
	}
}
