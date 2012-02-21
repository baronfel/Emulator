/**
 * An Abstract implementation of the View in the MVC architecture using JFrame. Serves as an
 * intermediate class between the View interface and all specific views that use JFrame.
 * @see View
 */


package view;

import interfaces.IController;
import interfaces.IModel;
import model.ModelListener;

public abstract class JFrameView implements View, ModelListener {
	private IModel _model;
	private IController _controller;

	public JFrameView(IModel aModel, IController aController) {
		throw new UnsupportedOperationException();
	}
/**
 * Sets up listeners and handlers to make sure the view will be notified by changes in the model.
 * 
 */
	public void registerWithModel() {
		throw new UnsupportedOperationException();
	}

	public IController getController() {
		throw new UnsupportedOperationException();
	}

	public void setController(IController aController) {
		throw new UnsupportedOperationException();
	}

	public IModel getModel() {
		throw new UnsupportedOperationException();
	}

	public void setModel(IModel aModel) {
		throw new UnsupportedOperationException();
	}
}