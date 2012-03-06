/**
 * The interface for all views in the MVC architecture.
 * @see IModel
 * @see IController
 */


package interfaces;


public interface IView {
/**
 * Gets the controller associated with this view.
 * @return The controller associated with this view.
 */
	public IController getController();

}