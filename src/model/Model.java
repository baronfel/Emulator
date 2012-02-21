/**
 * This is the interface that is assigned to all models in the MVC architecture.
 * @see View
 * @see Controller
 */

package model;

public interface Model {
/**
 * Notifies all listeners that this model has changed.
 * @param aE The event notification to send to all listeneers.
 */
	public void notifyChanged(ModelEvent aE);
}