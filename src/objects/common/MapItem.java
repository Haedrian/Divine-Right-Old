package objects.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import objects.common.enums.PhysicalActionType;
import objects.common.enums.MapItemType;
import objects.common.enums.MessageType;
import objects.common.exceptions.ActionTypeMismatchException;

/**
 * Represents an item displayed on the map.
 * Unless there is the express need to override the functions, use this class for all map items.
 * @author Llama
 *
 */
public class MapItem 
{
	protected String name;
	protected String graphic;
	protected MapItemType itemType;
	protected long globalId;
	protected long localId;
	protected Coordinate position;	
	protected ArrayList<PhysicalActionType> supportedPhysicalActions = new ArrayList<PhysicalActionType>();
	protected Boolean isWalkable;
	
	/**
	 * Gets the Name of this Map Item. This is what is shown when you examine it
	 * @return
	 */
	public String getName() {
		return name;
	}
	/***
	 * Sets the name of this Map Item. This is what is shown when you examine it.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets the Graphic of this Map Item. This is what is displayed by the Interface
	 * @return
	 */
	public String getGraphic() {
		return graphic;
	}
	/**
	 * Sets the Graphic of the Map Item. This is what is displayed by the Interface
	 * @param graphic
	 */
	public void setGraphic(String graphic) {
		this.graphic = graphic;
	}
	/**
	 * Determines the type of the Item. 
	 * @return
	 */
	public MapItemType getItemType() {
		return itemType;
	}
	/**
	 * Sets the type of the Item
	 * @param itemType
	 */
	public void setItemType(MapItemType itemType) {
		this.itemType = itemType;
	}
	/**
	 * The Global Id for this item. This is useful for Global Map locations and Actors
	 * @return
	 */
	public long getGlobalId() {
		return globalId;
	}
	/**
	 * Sets the Global Id for this item. This is useful for Global Map locations and Actors.
	 * @param globalId
	 */
	public void setGlobalId(long globalId) {
		this.globalId = globalId;
	}
	/**
	 * Sets the Local Id for this item. This is how the item will be referred to within the map.
	 * @return
	 */
	public long getLocalId() {
		return localId;
	}
	/**
	 * Sets the Local Id for this item. This is how the item will be referred to within the map.
	 * @param localId
	 */
	public void setLocalId(long localId) {
		this.localId = localId;
	}
	/**
	 * Determines the exact position on the map for this item
	 * @return
	 */
	public Coordinate getPosition() {
		return position;
	}
	/**
	 * Determines the exact position on the map for this item
	 * @param position
	 */
	public void setPosition(Coordinate position) {
		this.position = position;
	}
	/**
	 * Gets the list of Supported Actions. Default implementation makes no distinction for the actual actor
	 * @return
	 */
	public ArrayList<PhysicalActionType> getSupportedActions(Actor actor) {
		return supportedPhysicalActions;
	}
	/**
	 * Sets the list of Supported Actions which may be done upon this item.
	 * @param supportedPhysicalActions
	 */
	public void setSupportedPhysicalActions(ArrayList<PhysicalActionType> supportedPhysicalActions) {
		this.supportedPhysicalActions = supportedPhysicalActions;
	}
	/**
	 * Determines whether this item may be walked upon
	 * @return
	 */
	public Boolean getIsWalkable() {
		return isWalkable;
	}
	/**
	 * Sets whether this item may be walked upon
	 * @param isWalkable
	 */
	public void setIsWalkable(Boolean isWalkable) {
		this.isWalkable = isWalkable;
	}
	
	/**
	 * Performs a particular action upon this item. Note that not all items support all action types, and you should check using getSupportedActions
	 * @param physicalActionType The type of action to be performed
	 * @param actor The actor which is performing this action
	 * @param parameters Action-specific parameters. See documentation for each PhysicalActionType for more details
	 * @return A list of messages (possibly an empty list)
	 */
	public List<Message> performAction(PhysicalActionType physicalActionType,Actor actor,Map<String,String> parameters)
	{
		List<Message> messages = new ArrayList<Message>();
		
		if (!supportedPhysicalActions.contains(physicalActionType))
		{
			//Error message
			messages.add(new Message(MessageType.MODAL,"Error: Action not supported on this type"));
			return messages;
		}


		//TODO: Expand for the rest
        try {
            switch (physicalActionType) {
                case EXAMINE:
                            messages = examine(physicalActionType,actor,parameters);
                
                case WALK:
                            
            
            }
        } catch (ActionTypeMismatchException atme) {
            
            //do nothing;
        }
		
		return messages;
	}

    protected void logException(Exception e) 
    {
        System.out.println(e.toString());
        
        for (StackTraceElement stackTraceElement: e.getStackTrace())
        {
            System.out.println(stackTraceElement.toString());
        }
    }

    protected List<Message> examine (PhysicalActionType physicalActionType,Actor actor,Map<String,String> parameters) throws ActionTypeMismatchException {
        if (!physicalActionType.equals(PhysicalActionType.EXAMINE)) {
            throw new ActionTypeMismatchException("action should be " + physicalActionType.toString());
        }
        List<Message> ret = new ArrayList<Message>();
        ret.add(new Message(MessageType.TOAST,getName()));
        return ret;
    }
    
    protected List<Message> walkOn(PhysicalActionType physicalActionType,Actor actor,Map<String,String> parameters) throws ActionTypeMismatchException
    {
    	ArrayList<Message> ret = new ArrayList<Message>();
    	
    	//first, is it possible to walk upon this item?
        if (getIsWalkable())
        {
            //Are they 1 square away ? (Diagonals included)
            if (getPosition().displacement(actor.getPosition())<1.5)
            {
            	//TODO: USE MANAGER
                actor.setPosition(this.getPosition());
            }
            
        }
        else 
        {	//can't walk there
            
        }
    	
        return ret;
    }
	
	
	
	
	
	
}
