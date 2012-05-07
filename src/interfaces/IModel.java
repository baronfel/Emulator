/**
 * This is the interface that is assigned to all models in the MVC architecture.
 * @see IView
 * @see IController
 */

package interfaces;

import model.ModelEvent;

public interface IModel {
/**
 * Adds a listener to the list of listeners.
 * @param listener The listener to add to the list.
 */
	public void addListener(IModelListener listener);
	
	/**
	 * Removes a listener from the list of listeners.
	 * @param listener The listener to remove from the list.
	 */
	public void removeListener(IModelListener listener);
	

/**
 * Notifies all listeners that this model has changed.
 * @param aE The event notification to send to all listeneers.
 */
	public void notifyChanged(ModelEvent aE);
}
