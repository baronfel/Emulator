/**
 * Listens for messages from the model that the model has changed and acts on those changes.
 * 
 */

package model;

public interface ModelListener {

	public void modelChanged(ModelEvent aEvent);
}