/**
 * The interface for all views in the MVC architecture.
 * @see IModel
 * @see IController
 */


package view;

import interfaces.IController;
import interfaces.IModel;

public interface View {
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
	/**
	 * Gets the model associated with this view.
	 * @return The model associated with this view.
	 */
	public IModel getModel();
	/**
	 * Sets the model associated with this view.
	 * @param aModel The model associated with this view.
	 */
	public void setModel(IModel aModel);
}