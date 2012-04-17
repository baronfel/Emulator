/**
 * 
 */
package controller;

import java.util.List;

import model.ComponentStatus;
import interfaces.IALU;
import interfaces.IInstruction;
import interfaces.IMemoryAccess;
import interfaces.IModel;
import interfaces.IModelListener;
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
		proc = (IProcessor) model;
	}

	@Override
	public void setModel(IModel aModel) {
		if (aModel instanceof IProcessor) {
			proc = (IProcessor) aModel;
		}
	}

	public ComponentStatus GetIssueStatus() {
		List<IInstruction> current = proc.getIssue().CurrentInstructions();
		String returnString = "No Current Instruction";
		if (current.size() > 0) {
			returnString = current.get(0).toString();
		}
		return new ComponentStatus("Issue", proc.getIssue().GetStatus()
				.toString(), returnString);
	}

	public List<IALU> getALUs() {
		return proc.getALUs();
	}

	public List<IMemoryAccess> getMemUnits() {
		return proc.getMemUnits();
	}

	public ComponentStatus getFetchStatus() {
		List<IInstruction> current = proc.getFetchUnit().CurrentInstructions();
		String returnString = "No Current Instruction";
		if (current.size() > 0) {
			returnString = current.get(0).toString();
		}
		return new ComponentStatus("Fetch", proc.getFetchUnit().GetStatus()
				.toString(), returnString);
	}

	public void addFetchListener(IModelListener fetchView) {
		proc.getFetchUnit().addListener(fetchView);
	}

	public void removeFetchListener(IModelListener fetchView) {
		proc.getFetchUnit().removeListener(fetchView);
	}
}
