/**
 * An Abstract class for all models in the MVC architecture. An intermediate class between the
 * Model interface and all specific models.
 * @see IModel
 */

package model;

import interfaces.IModel;
import interfaces.IModelListener;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModel implements IModel {
	private transient List<IModelListener> _listeners = new ArrayList<IModelListener>();
/**
 * Notifies all listeners that the model has changed.
 */
	public void notifyChanged(ModelEvent aEvent) {
		for(IModelListener listener : _listeners)
		{
			listener.modelChanged(aEvent);
		}
	}
/**
 * Adds a listener to the list of listeners listening.
 * @param aL The listener to add to the list.
 * @see IModelListener
 */
	public void addListener(IModelListener newGuy) {
		_listeners.add(newGuy);
	}
/**
 * Removes a listener from the list of listeners listening
 * @param aL The listener to remove from the list.
 * @see IModelListener
 */
	public void removeListener(IModelListener aL) {
		_listeners.remove(aL);
	}
}