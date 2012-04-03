/**
 * An abstract class that implements some of the controller interface. An intermediate class
 * between the Controller Interface and a specific controller.
 */


package controller;

import interfaces.IController;
import interfaces.IModel;
import interfaces.IModelListener;

public abstract class AbstractController implements IController {
	private IModel _model;
	
	public AbstractController(IModel model)
	{
		this._model = model;
	}

	/**
	 * Gets the IModel that this IController operates on.
	 * @see IController
	 */
	public IModel getModel() {
		return _model;
	}
	
	public void removeListener(IModelListener listener)
	{
		_model.removeListener(listener);
	}
	
	public void addListener(IModelListener listener)
	{
		_model.addListener(listener);
	}
}