/**
 * An abstract class that implements some of the controller interface. An intermediate class
 * between the Controller Interface and a specific controller.
 * 
 * 
 */


package controller;

import interfaces.IController;
import interfaces.IModel;
import view.View;

public abstract class AbstractController implements IController {
	private View _view;
	private IModel _model;
/**
 * Added an exception to the setModel operation.
 * @see IController
 */
	public void setModel(IModel aModel) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Added an exception to the getModel operation.
	 * @see IController
	 */
	public IModel getModel() {
		throw new UnsupportedOperationException();
	}
	/**
	 * Added an exception to the getView operation.
	 * @see IController
	 */
	public View getView() {
		throw new UnsupportedOperationException();
	}
	/**
	 * Added an exception to the setView operation.
	 * @see IController
	 */
	public void setView(View aView) {
		throw new UnsupportedOperationException();
	}
}