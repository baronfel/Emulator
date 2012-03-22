/**
 * 
 */
package controller;

import interfaces.IModel;
import interfaces.IModelListener;
import model.Registry;

/**
 * @author chusk3
 *
 */
public class RegisterController extends AbstractController {

	private Registry registers;
	
	public RegisterController(IModel model) {
		super(model);
		registers = (Registry)model;
	}

	@Override
	/**
	 * Don't actually need to set a new model for this guy...
	 */
	public void setModel(IModel aModel) {
		return;
	}

	public int getRegisterValue(int watchedRegister) {
		return registers.getValue(watchedRegister);
	}	
	
	public void addListener(int registerToWatch, IModelListener listener)
	{
		registers.addListener(registerToWatch, listener);
	}
	
	public void removeListener(int registerToWatch, IModelListener listener)
	{
		registers.removeListener(registerToWatch, listener);
	}
}
