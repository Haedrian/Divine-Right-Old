package objects.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import objects.common.enums.MessageType;
import objects.common.enums.PhysicalActionType;
import objects.common.messages.Message;

/**
 * Represents a STATIC tile.
 * Other (non-tile) items may be placed upon it, but it is not necessarily walkable.
 * @author Llama
 *
 */
public class Tile extends MapItem 
{
    
    List<MapItem> itemsOnTile = new LinkedList<MapItem>();

    /**
     * Returns a list of Items which belong on the Tile
     * @return
     */
    public List<MapItem> getItemsOnTile() 
    {
        return itemsOnTile;
    }
    @Override
    /**
     * In this case, whether a tile is walkable depends on the top item (if present)
     */
    public Boolean getIsWalkable()
    {
    	//Check whether we have something on top
    	MapItem topItem = this.getTopItem();
    	
    	if (topItem == null)
    	{
    		return super.getIsWalkable();
    	}
    	else 
    	{
    		return this.isWalkable;
    	}
    	
    }
    
    @Override
    /**
     * Returns the supported Physical Actions. If there is an item on the tile, will return those instead
     */
    public ArrayList<PhysicalActionType> getSupportedActions(Actor actor) 
    {
		MapItem topItem = this.getTopItem();
		
		if (topItem == null)
		{
			return super.getSupportedActions(actor);
		}
		else 
		{
			//return own
			return this.supportedPhysicalActions;
		}
	}
    
    /**
     * Moves the actor to this tile (if possible). Parameters are reserved for future use
     * @param actor
     * @param parameters
     * @return
     */
    public List<Message> moveTo(Actor actor,Map<String,String> parameters)
    {
    	List<Message> ret = new ArrayList<Message>();
    	
    	//Should be as simple as checking whether the tile is walkable or not
    	
    	if (this.getIsWalkable())
    	{
    		//Then we can do it
    		this.addItemToTile(actor);
    	}
    	else 
    	{
    		//We can't doanything
    		
    	}
    	
    	return ret;
    	
    }
    
    @Override
    /**
     * Performs an Action on the tile if it is possible to do so, or upon the top item if it is not
     */
    public List<Message> performAction(PhysicalActionType action,Actor actor,Map<String,String> parameters)
	{
		List<Message> messages = new ArrayList<Message>();
		
		//we handle move to
		
		if (action == PhysicalActionType.MOVE_TO)
		{
			this.moveTo(actor, parameters);
		}
		
		//otherwise, we treat as usual
		
		//Do we have a top item?
		
		MapItem item = this.getTopItem();
		
		if (item == null)
		{
			//we don't
			return super.performAction(action, actor, parameters);
		}
		else 
		{
			//the item will handle it
			return item.performAction(action, actor, parameters);
		}
	
	}
    
    /**
     * Returns the top item on this tile, or null if there isn't one
     * @return
     */
    public MapItem getTopItem()
    {    	
    	for (int i=0; i < itemsOnTile.size(); i++)
    	{
    		MapItem item = itemsOnTile.get(i);
    		
    		//check whether the item has the right coordinate
    		//This is a lazy way of checking
    		
    		if (item.getPosition().equals(this.getPosition()))
    		{
    			return item;
    		}
    		else 
    		{
    			//delete the item
    			itemsOnTile.remove(i);
    		}
    		
    	}
    	
    		return null;
    	
    }
    /**
     * Adds an item to this tile. Will change the coordinate of the item in question
     * @param item
     */
    public void addItemToTile(MapItem item)
    {
    	item.setPosition(this.getPosition()); //change the position of the item
    	
    	//put the item on the top of the stack
    	
    	this.itemsOnTile.add(0, item);
    }

}
