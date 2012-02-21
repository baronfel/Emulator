/**
 * The interface for all views in the MVC architecture.
 * @see Model
 * @see Controller
 */


package view;

import controller.Controller;
import model.Model;

public interface View {
/**
 * Gets the controller associated with this view.
 * @return The controller associated with this view.
 */
	public Controller getController();
/**
 * Sets the controller associated with this view.
 * @param aController The controller associated with this view.
 */
	public void setController(Controller aController);
	/**
	 * Gets the model associated with this view.
	 * @return The model associated with this view.
	 */
	public Model getModel();
	/**
	 * Sets the model associated with this view.
	 * @param aModel The model associated with this view.
	 */
	public void setModel(Model aModel);
}