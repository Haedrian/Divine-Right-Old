package objects.common.messages;

import java.util.Map;

import objects.common.enums.GUIActionType;
import objects.common.enums.MapType;

/**
 * Represents an Action and the Parameters it takes.
 * Not intented to be used outside of messaging
 * @author Llama
 *
 */
public class ActionAndParameters
{
	private GUIActionType action;
	private long itemId;
	private Map<String,String> parameters;
	private MapType mapType;
	
	public ActionAndParameters()
	{
		
	}
	
	public ActionAndParameters(GUIActionType action,long itemId, Map<String,String> parameters,MapType mapType)
	{
		this.action = action;
		this.itemId = itemId;
		this.parameters = parameters;
		this.setMapType(mapType);
	}
	/**
	 * Gets the Action which is to be performed
	 * @return
	 */
	public GUIActionType getAction()
	{
		return action;
	}
	/**
	 * Sets the Action which is to be performed
	 * @param action
	 */
	public void setAction(GUIActionType action)
	{
		this.action = action;
	}
	/**
	 * Gets the id of the item this action will effect
	 * @return
	 */
	public long getItemId()
	{
		return itemId;
	}
	/**
	 * Sets the id of the item this action will effect
	 * @param itemId
	 */
	public void setItemId(long itemId)
	{
		this.itemId = itemId;
	}
	/**
	 * Gets the parameters which are used by the Action
	 * @return
	 */
	public Map<String, String> getParameters()
	{
		return parameters;
	}
	/**
	 * Sets the parameters which are used by the Action
	 * @param parameters
	 */
	public void setParameters(Map<String, String> parameters)
	{
		this.parameters = parameters;
	}
	
	/**
	 * Gets the type of the Map the item belongs in.
	 * @return
	 */
	public MapType getMapType()
	{
		return mapType;
	}

	/**
	 * Sets the type of the Map the item belongs in
	 * @param mapType
	 */
	public void setMapType(MapType mapType)
	{
		this.mapType = mapType;
	}
	
	public String toString()
	{
		return action + " upon " + this.itemId + " on " + this.mapType + " with " + this.parameters.size() + " parameters";
	}
	
}
