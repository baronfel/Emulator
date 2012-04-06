/**
 * Listens for messages from the model that the model has changed and acts on those changes.
 * 
 */

package interfaces;

import model.ModelEvent;

public interface IModelListener {

	public abstract void modelChanged(ModelEvent aEvent);
}