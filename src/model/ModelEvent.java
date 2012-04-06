
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
	
	public ModelEvent(Object sender, int id, String message, int extraData) {
		this.sender = sender;
		this.id = id;
		this.message = message;
		this.extraData = extraData;
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
