/**
 * Listens for messages from the model that the model has changed and acts on those changes.
 * 
 */

package interfaces;

import model.ModelEvent;

public interface IModelListener {

	/**
	 * This method is called whenever a model event is thrown. The listener may take actions when it receives a particular model event.
	 * @param aEvent The model event that the listener receives.
	 */
	public abstract void modelChanged(ModelEvent aEvent);
}