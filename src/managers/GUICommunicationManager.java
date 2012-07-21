package managers;

import java.util.List;
import java.util.Map;

import objects.common.Coordinate;
import objects.common.GUIObject;
import objects.common.MapItem;
import objects.common.enums.MapOverlay;
import objects.common.enums.MapType;
import objects.common.enums.PhysicalActionType;
import objects.common.exceptions.ItemNotFoundException;
import objects.common.messages.Message;

/**
 * This class handles all communication between the GUI and the engine. All GUI requests and responses must pass through it
 * @author Llama
 *
 */
public class GUICommunicationManager
{

	/**
	 * Returns a GUI Object which exists at that particular Coordinate.
	 * It will return an empty tiled object if there is no object at that coordinate
	 * @param coordinate The Coordinate of the object to fetch
	 * @param overlay Which overlay to apply on top of the map
	 * @param mapType Which type of map the coordinate system refers to
	 * @return
	 */
	public GUIObject GetGUIObject(Coordinate coordinate,MapOverlay overlay,MapType mapType)
	{
		if (mapType == MapType.LOCAL)
		{
			//TODO: USE LOCAL MAP MANAGER
		}
		else if (mapType == MapType.GLOBAL)
		{
			//TODO: USE GLOBAL MAP MANAGER
		}
		
		return null; //TODO: IMPLEMENT
	}
	
	/**
	 * Returns a GUI object which has a particular id.
	 * @param id
	 * @param mapType
	 * @return
	 * @throws ItemNotFoundException If the item could not be found
	 */
	public GUIObject GetObjectById(long id,MapType mapType) throws ItemNotFoundException
	{
		//TODO: IMPLEMENT
	
		throw new ItemNotFoundException("Item with id : " + id + " for Map " + mapType.toString() + " was not found");
		
	}
	
	/**
	 * Lets the user perform a particular action upon an item with id "TargetId".
	 * The parameter to be passed depend on the ActionType. PLEASE READ INDIVIDUAL DOCUMENTATION TO DETERMINE WHAT NEEDS TO BE PASSED
	 * @param targetId The id of the item which the action is occuring upon. It could be the player itself for certain things
	 * @param action The action to perform upon the item
	 * @param parameters The parameters which determien the details of the action. PLEASE READ INDIVIDUAL DOCUMENTATION TO DETERMINE WHAT NEEDS TO BE PASSED
	 * @param mapType The map we're talking about
	 * @return A list of messages to be displayed to the user
	 */
	public List<Message> PerformAction(long targetId,PhysicalActionType action,Map<String,String> parameters,MapType mapType)
	{
		try
		{
			MapItem item = GetMapItem(targetId,mapType);
			
			item.performAction(action, null, parameters);
			
			//TODO: ACTOR SHOULD BE PLAYER CHARACTER
			
		} 
		catch (ItemNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

		return null;
	}
	/**
	 * Gets a particular Map item using the id and a maptype
	 * @param id
	 * @param mapType
	 * @return
	 */
	private MapItem GetMapItem(long id,MapType mapType) throws ItemNotFoundException
	{
		//TODO: IMPLEMENT
		
		if (mapType == MapType.LOCAL)
		{
			//use local manager
		}
		else if (mapType == MapType.GLOBAL)
		{
			//use global manager
		}
		
		throw new ItemNotFoundException("Item with id : " + id + " on maptype " + mapType.toString() + " was not found" );
	}
}
