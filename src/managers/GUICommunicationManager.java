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
	public static GUIObject GetGUIObject(Coordinate coordinate,MapOverlay overlay,MapType mapType)
	{
		if (mapType == MapType.LOCAL)
		{
			return LocalMapManager.getTileAsGUIObject(coordinate);
		}
		else if (mapType == MapType.GLOBAL)
		{
			return GlobalMapManager.getTileAsGUIObject(coordinate);
		}
		
		return null;
	}
	
	/**
	 * Lets the user perform a particular action upon an item belonging on that particular coordinate
	 * The parameter to be passed depend on the ActionType. PLEASE READ INDIVIDUAL DOCUMENTATION TO DETERMINE WHAT NEEDS TO BE PASSED
	 * @param tileCoordinate The coordinate of the tile on which the object is (or the tile itself)
	 * @param action The action to perform upon the item
	 * @param parameters The parameters which determien the details of the action. PLEASE READ INDIVIDUAL DOCUMENTATION TO DETERMINE WHAT NEEDS TO BE PASSED
	 * @param mapType The map we're talking about
	 * @return A list of messages to be displayed to the user
	 */
	public static List<Message> PerformAction(Coordinate tileCoordinate,PhysicalActionType action,Map<String,String> parameters,MapType mapType)
	{
		if (mapType == MapType.LOCAL)
		{
			//use local manager
			return LocalMapManager.performAction(tileCoordinate, LocalMapManager.getPlayerCharacter() , action, parameters);
		}
		else if (mapType == MapType.GLOBAL)
		{
			return GlobalMapManager.performAction(tileCoordinate, GlobalMapManager.getPlayerCharacter(), action, parameters);
		}
		
		return null; //something went wrong
	}
	
	/**
	 * Returns the local coordinates of the player. May return null if he is not on a local map
	 * @return
	 */
	public static Coordinate getPlayerLocalCoordinates()
	{
		return LocalMapManager.getPlayerCoordinate();
	}
	
	public static Coordinate getPlayerGlobalCoordinates()
	{
		return GlobalMapManager.getPlayerCoordinate();
	}
	
}
