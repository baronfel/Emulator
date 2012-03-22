/**
 * Basic interface for a controller in the MVC architecture.
 * @see IView
 * @see IModel
 */


package interfaces;


public interface IController {

	/**
	 * Sets a model to be associated with this controller.
	 * @param aModel The model associated with this controller.
	 */
	public void setModel(IModel aModel);
/**
 * Gets the model associated with this controller.
 * @return the model associated with this controller.
 */
	public IModel getModel();
}