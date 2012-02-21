/**
 * An abstract class that implements some of the controller interface. An intermediate class
 * between the Controller Interface and a specific controller.
 * 
 * 
 */


package controller;

import view.View;
import model.Model;

public abstract class AbstractController implements Controller {
	private View _view;
	private Model _model;
/**
 * Added an exception to the setModel operation.
 * @see Controller
 */
	public void setModel(Model aModel) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Added an exception to the getModel operation.
	 * @see Controller
	 */
	public Model getModel() {
		throw new UnsupportedOperationException();
	}
	/**
	 * Added an exception to the getView operation.
	 * @see Controller
	 */
	public View getView() {
		throw new UnsupportedOperationException();
	}
	/**
	 * Added an exception to the setView operation.
	 * @see Controller
	 */
	public void setView(View aView) {
		throw new UnsupportedOperationException();
	}
}