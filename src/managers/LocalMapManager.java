package managers;

import objects.common.Actor;
import objects.common.Coordinate;
import objects.common.GUIObject;
import objects.common.MapItem;
import objects.common.Tile;
import objects.common.enums.MapItemType;
import objects.common.enums.PhysicalActionType;
import objects.common.exceptions.ItemNotFoundException;
import objects.common.messages.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import factory.ItemFactory;

/**
 * A manager which holds the entirety of the Local Map and anything else it needs to keep track of
 * @author Llama
 *
 */
public class LocalMapManager 
{

    protected static HashMap<Coordinate,Tile> localMap = new HashMap<Coordinate, Tile>();
    protected static ArrayList<Actor> actors = new ArrayList<Actor>();
    protected static Actor player = null;
   
    public static HashMap<Coordinate, Tile> getLocalMap() {
        return localMap;
    }

    /**
     * Sets the local map.
     * @param localMap
     */
    public static void setLocalMap(HashMap<Coordinate, Tile> localMap) 
    {
        LocalMapManager.localMap = localMap;
    }
    
    /**
     * Loads the map belonging to the map with that global id
     * @param globalId
     */
    public static void loadMap(int globalId)
    {
    	
    }
    
    /**
     * Returns the Tile at the Coordinate as a GUI Object.
     * If there is no tile at that location - will return an empty 'air' tile
     * @param coor The coordinate
     * @return
     */
    public static GUIObject getTileAsGUIObject(Coordinate coor)
    {
    	Tile tile = getTileAt(coor); //get the tile
    	
    	//convert the tile to its GUI object representation
    	
    	GUIObject ret  = new GUIObject();
    	ret.setCoordinate(tile.getPosition());
    	ret.setId(tile.getLocalId());
    	ret.setPossibleActions(tile.getSupportedActions(player));
    	ret.setTileGraphic(tile.getGraphic());
    	
    	MapItem top = tile.getTopItem();
    	
    	if (top == null)
    	{
    		ret.setTopMapItemGraphic("");
    		ret.setDescription(tile.getName());
    	}
    	else 
    	{
    		ret.setTopMapItemGraphic(top.getGraphic());
    		ret.setDescription(top.getName());
    	}
    	
    	return ret;
    	
    }
    
    public static Tile getTileAt(Coordinate coor) 
    {    	
    	if (!localMap.containsKey(coor))
    	{
    		//not found
    		//create an empty air tile
    		try
			{
				Tile t = (Tile) ItemFactory.CreateMapItem(MapItemType.LOCAL_TILE_AIR);
				t.setPosition(coor);
				
				return t;
			} 
    		catch (ItemNotFoundException e)
			{
    			return new Tile(); //yeah its broken
			}
    		
    		
    	}
    	
        return localMap.get(coor);
    }
    /**
     * Returns the Coordinate of the player character, or null if there is no player character
     * @return
     */
    public static Coordinate getPlayerCoordinate()
    {
    	if (player == null)
    	{
    		return null;
    	}
    	else 
    	{
    		return player.getPosition();
    	}
    }
    /**
     * Attempts to perform an action on the tile or item at a particular coordinate 
     * @param coordinate The coordinates where the item or tile belongs to
     * @param actor The actor performing the action
     * @param actionType The action to be performed
     * @param parameters A map with particular parameters. PLEASE READ INDIVIDUAL DOCUMENTATION FOR DETAILS!
     * @return
     */
    public static List<Message> performAction(Coordinate coordinate, Actor actor, PhysicalActionType actionType,Map<String,String> parameters )
    {
    	//Get the tile belonging at the coordinate, and perform the action upon it
    	
    	Tile tile = getTileAt(coordinate);
    	return tile.performAction(actionType, actor, parameters);	
    }
    
    /**
     * Gets the actor which refers to the player character.
     * Can be null.
     * @return
     */
    public static Actor getPlayerCharacter()
    {
    	return player;
    }

}
