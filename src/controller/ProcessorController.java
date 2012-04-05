/**
 * 
 */
package controller;

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

}
