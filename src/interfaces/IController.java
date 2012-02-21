/**
 * Basic interface for a controller in the MVC architecture.
 * @see View
 * @see IModel
 */


package interfaces;

import view.View;

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
/**
 * Gets the view associated with this controller.
 * @return the view associated with this controller.
 */
	public View getView();
/**
	 * Sets a view to be associated with this controller.
	 * @param aView The view associated with this controller.
 */
	public void setView(View aView);
}