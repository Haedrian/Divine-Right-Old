package managers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import factory.ItemFactory;

import objects.common.Actor;
import objects.common.Coordinate;
import objects.common.GUIObject;
import objects.common.GlobalTile;
import objects.common.MapItem;
import objects.common.Party;
import objects.common.Tile;
import objects.common.enums.MapItemType;
import objects.common.enums.MapObjectGroupType;
import objects.common.enums.PhysicalActionType;
import objects.common.exceptions.ItemNotFoundException;
import objects.common.messages.Message;

/**
 * Represents what is displayed and stored on the Global Map
 * @author Llama
 *
 */
public class GlobalMapManager
{

	private static HashMap<Coordinate,GlobalTile> globalMap = new HashMap<Coordinate,GlobalTile>();
	private static ArrayList<Party> parties = new ArrayList<Party>();
	private static Party playerParty = null;
	
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
    	ret.setPossibleActions(tile.getSupportedActions(playerParty));
    	ret.setTileGraphic(tile.getGraphic());
    	
    	MapItem top = tile.getTopItem();
    	
    	if (top == null)
    	{
    		ret.setTopMapItemGraphic("");
    		ret.setDescription(tile.getName());
    		ret.setId(-1);
    		ret.setTopItemType(tile.getItemType());
    		ret.setTopItemGroup(new ArrayList<MapObjectGroupType>());
    	}
    	else 
    	{
    		ret.setTopMapItemGraphic(top.getGraphic());
    		ret.setDescription(top.getName());
    		ret.setId(top.getLocalId());
    		ret.setTopItemType(top.getItemType());
    		ret.setTopItemGroup(top.getTypeGroups());
    	}
    	
    	//get the list of map items upon the tile
    	
    	List<MapItem> items = tile.getItems();
    	ret.setItemsGraphics(new ArrayList<String>());
    	
    	for(MapItem item: items)
    	{
    		ret.getItemsGraphics().add(item.getGraphic());
    		
    	}
    	
    	return ret;    	
    }
    
    public static Tile getTileAt(Coordinate coor) 
    {    	
    	if (!globalMap.containsKey(coor))
    	{
    		//not found
    		//create a global void tile
    		try
			{
				Tile t = (Tile) ItemFactory.CreateMapItem(MapItemType.GLOBAL_TILE_VOID);
				t.setPosition(coor);
				
				return t;
			} 
    		catch (ItemNotFoundException e)
			{
    			return new Tile(); //yeah its broken
			}
    		
    		
    	}
    	
        return globalMap.get(coor);
    }
    /**
     * Returns the Coordinate of the player character's party
     * @return
     */
    public static Coordinate getPlayerCoordinate()
    {
    	if (playerParty == null)
    	{
    		return null;
    	}
    	else 
    	{
    		return playerParty.getPosition();
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
     * Gets the party which refers to the player character.
     * Can be null.
     * @return
     */
    public static Party getPlayerCharacter()
    {
    	return playerParty;
    }
	
}
