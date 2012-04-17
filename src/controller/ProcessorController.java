/**
 * 
 */
package controller;

import model.ComponentStatus;
import interfaces.IModel;
import interfaces.IProcessor;

/**
 * @author Chester
 *
 */
public class ProcessorController extends AbstractController {

	IProcessor proc;
	/**
	 * @param model
	 */
	public ProcessorController(IProcessor model) {
		super(model);
		proc = (IProcessor)model;
	}

	@Override
	public void setModel(IModel aModel) {
		if(aModel instanceof IProcessor)
		{
			proc = (IProcessor) aModel;
		}
	}

	public ComponentStatus GetIssueStatus() {
		// TODO Auto-generated method stub
		return new ComponentStatus("Issue", proc.getIssue().GetStatus().toString(), proc.getIssue().CurrentInstructions().get(0).toString());
	}

}
