/**
 * This is the interface that is assigned to all models in the MVC architecture.
 * @see IView
 * @see IController
 */

package interfaces;

import model.ModelEvent;

public interface IModel {
/**
 * Notifies all listeners that this model has changed.
 * @param aE The event notification to send to all listeneers.
 */
	public void notifyChanged(ModelEvent aE);
}