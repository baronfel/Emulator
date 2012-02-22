/**
 * An Abstract class for all models in the MVC architecture. An intermediate class between the
 * Model interface and all specific models.
 * @see IModel
 */

package model;

import interfaces.IModel;
import javax.swing.event.EventListenerList;

public abstract class AbstractModel implements IModel {
	private EventListenerList _listeners = new EventListenerList();
/**
 * Notifies all listeners that the model has changed.
 */
	public void notifyChanged(ModelEvent aEvent) {
		throw new UnsupportedOperationException();
	}
/**
 * Adds a listener to the list of listeners listening.
 * @param aL The listener to add to the list.
 * @see ModelListener
 */
	public void addModelListener(ModelListener aL) {
		throw new UnsupportedOperationException();
	}
/**
 * Removes a listener from the list of listeners listening
 * @param aL The listener to remove from the list.
 * @see ModelListener
 */
	public void removeModelListener(ModelListener aL) {
		throw new UnsupportedOperationException();
	}
}