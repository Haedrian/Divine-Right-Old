package managers;

import objects.common.Actor;
import objects.common.Coordinate;
import objects.common.MapItem;
import objects.common.Tile;
import objects.common.enums.MapItemType;
import objects.common.exceptions.ItemNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import factory.ItemFactory;

/**
 * A manager which holds the entirety of the Local Map and anything else it needs to keep track of
 * @author Llama
 *
 */
public class LocalMapManager 
{

    protected static HashMap<Coordinate,Tile> localMap = new HashMap<Coordinate, Tile>();
    protected static HashMap<Long,MapItem> localMapById = new HashMap<Long,MapItem>();
    
    protected static ArrayList<Actor> actors = new ArrayList<Actor>();


    public static HashMap<Coordinate, Tile> getLocalMap() {
        return localMap;
    }

    /**
     * Sets the local map. Also assigns the items to the other list and supplies them an id if there is not one already
     * @param localMap
     */
    public static void setLocalMap(HashMap<Coordinate, Tile> localMap) {
        LocalMapManager.localMap = localMap;
        
        localMapById = new HashMap<Long,MapItem>();
        
        //go through all of them as key-value pairs
        
        for (MapItem item: localMap.values())
        {
        	long value = 0;
        	
        	if (item.getLocalId() == -1)
        	{
        		value++;
        		//assign one yourself
        		while (localMapById.containsKey(value))
        		{
        			//increment it
        			value++;
        		}
        		
        		item.setLocalId(value);
        		
        		localMapById.put(value, item);
        		
        	}
        	else 
        	{
        		localMapById.put(item.getLocalId(), item);
        	}
        	
        }
        
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
     * NullPointer-safe way of retrieeing items on tile;
     * @param coor
     * @return
     */
    public static List<MapItem> getMapItemsAt(Coordinate coor) 
    {
        Tile tile = getTileAt(coor);
        return tile.getItemsOnTile();
        
    }
}
