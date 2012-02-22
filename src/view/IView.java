/**
 * The interface for all views in the MVC architecture.
 * @see IModel
 * @see IController
 */


package view;

import interfaces.IController;


public interface IView {
/**
 * Gets the controller associated with this view.
 * @return The controller associated with this view.
 */
	public IController getController();
/**
 * Sets the controller associated with this view.
 * @param aController The controller associated with this view.
 */
	public void setController(IController aController);
}