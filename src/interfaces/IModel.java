/**
 * This is the interface that is assigned to all models in the MVC architecture.
 * @see IView
 * @see IController
 */

package interfaces;

import model.ModelEvent;

public interface IModel {

	public void addListener(IModelListener listener);
	public void removeListener(IModelListener listener);
	

/**
 * Notifies all listeners that this model has changed.
 * @param aE The event notification to send to all listeneers.
 */
	public void notifyChanged(ModelEvent aE);
}
