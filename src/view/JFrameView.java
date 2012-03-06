/**
 * An Abstract implementation of the View in the MVC architecture using JFrame. Serves as an
 * intermediate class between the View interface and all specific views that use JFrame.
 * @see IView
 */


package view;

import interfaces.IController;
import interfaces.IView;
import model.ModelListener;

public abstract class JFrameView implements IView, ModelListener {
	private IController _controller;

	public JFrameView(IController controller) {
		_controller = controller;
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
}