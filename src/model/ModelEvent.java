/**
 * An Event that is sent to all listeners, such as those in the views, when some part of the
 * model is changed.
 * 
 */


package model;

public class ModelEvent {

	private Object sender;
	private int id;
	private String message;
	private int extraData;
	
	public ModelEvent(Object aObj, int aId, String aMessage, int aAmount) {
		sender = aObj;
		id = aId;
		message = aMessage;
		extraData = aAmount;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public int getExtraData()
	{
		return extraData;
	}
	
	public Object getSender()
	{
		return sender;
	}
	
	public int getID()
	{
		return id;
	}
}