/**
 * An Abstract implementation of the View in the MVC architecture using JFrame. Serves as an
 * intermediate class between the View interface and all specific views that use JFrame.
 * @see View
 */


package view;

import model.Model;
import controller.Controller;
import model.ModelListener;

public abstract class JFrameView implements View, ModelListener {
	private Model _model;
	private Controller _controller;

	public JFrameView(Model aModel, Controller aController) {
		throw new UnsupportedOperationException();
	}
/**
 * Sets up listeners and handlers to make sure the view will be notified by changes in the model.
 * 
 */
	public void registerWithModel() {
		throw new UnsupportedOperationException();
	}

	public Controller getController() {
		throw new UnsupportedOperationException();
	}

	public void setController(Controller aController) {
		throw new UnsupportedOperationException();
	}

	public Model getModel() {
		throw new UnsupportedOperationException();
	}

	public void setModel(Model aModel) {
		throw new UnsupportedOperationException();
	}
}