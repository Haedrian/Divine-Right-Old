package objects.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import objects.common.StringParameters.EngineParameters;
import objects.common.enums.*;
import objects.common.exceptions.ActionTypeMismatchException;
import objects.common.managers.LocalMapManager;
import objects.common.messages.Message;

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
	 * @param action The type of action to be performed
	 * @param actor The actor which is performing this action
	 * @param parameters Action-specific parameters. See documentation for each PhysicalActionType for more details
	 * @return A list of messages (possibly an empty list)
	 */
	public List<Message> performAction(PhysicalActionType action,Actor actor,Map<String,String> parameters)
	{
		List<Message> messages = new ArrayList<Message>();
		if (!supportedPhysicalActions.contains(action)) {
			//Error message
			messages.add(new Message(MessageType.MODAL,"Error: Action not supported on this type"));
			return messages;
		}
		//TODO: Expand for the rest
        try {
            switch (action) {
                case MOVE_X_PLUS:  moveOnto(action,actor,parameters); break;
                case MOVE_X_MINUS: moveOnto(action,actor,parameters); break;
                case MOVE_Y_PLUS:  moveOnto(action,actor,parameters); break;
                case MOVE_Y_MINUS: moveOnto(action,actor,parameters); break;
                case MOVE_Z_PLUS:  moveOnto(action,actor,parameters); break;
                case MOVE_Z_MINUS: moveOnto(action,actor,parameters); break;
                case TELEPORT:     moveOnto(action,actor,parameters); break;
            }
        } catch (ActionTypeMismatchException atme) {
            //do nothing;
        }
		return messages;
	}

    /**
     * this action should act onto actor moving
     * @param physicalActionType
     * @param actor
     * @param parameters
     * @return
     * @throws ActionTypeMismatchException
     */
    protected MethodResultType moveOnto(PhysicalActionType physicalActionType,Actor actor,Map<String,String> parameters) throws ActionTypeMismatchException {
        int x = this.position.getX();
        int y = this.position.getY();
        int z = this.position.getZ();
        switch (physicalActionType) {
            case MOVE_X_PLUS:   return moveCore(x+1,y,z,extractMoveMode(parameters));
            case MOVE_X_MINUS:  return moveCore(x-1,y,z,extractMoveMode(parameters));
            case MOVE_Y_PLUS:   return moveCore(x,y+1,z,extractMoveMode(parameters));
            case MOVE_Y_MINUS:  return moveCore(x,y-1,z,extractMoveMode(parameters));
            case MOVE_Z_PLUS:   return moveCore(x,y,z+1,extractMoveMode(parameters));
            case MOVE_Z_MINUS:  return moveCore(x,y,z-1,extractMoveMode(parameters));
            case TELEPORT:      int[] onto = extractTeleportTarget(parameters);
                                return moveCore(onto[0],onto[1],onto[3],extractMoveMode(parameters));
        }
        return MethodResultType.MOVE_IMPOSSIBLE;
    }

    protected MethodResultType moveCore(int newX, int newY, int newZ, MoveMode mode) {
        //you can walk out of inaccessible tile
        if ((!LocalMapManager.getTileAt(this.position).isMoveModeAllowed(mode)) && (!mode.equals(MoveMode.MOVE_WALK))) {
            return MethodResultType.MOVE_IMPOSSIBLE;
        }
        Coordinate newCoor = new Coordinate(newX,newY,newZ);
        //you cant move if target does not support your motion mode
        if (!LocalMapManager.getTileAt(newCoor).isMoveModeAllowed(mode)) {
            return MethodResultType.MOVE_IMPOSSIBLE;
        }
        //you are clear to move
        Coordinate oldPos = this.position;
        this.position = newCoor;
        registerNewPosition(oldPos);
        return MethodResultType.ACTION_SUCCESSFUL;
    }
    
    protected MoveMode extractMoveMode(Map<String, String> parameters) {
        MoveMode ret = MoveMode.MOVE_WALK;
        String retStr = "";
        if (parameters.containsKey(EngineParameters.MOVE_MODE)){
            retStr = parameters.get(EngineParameters.MOVE_MODE);
            try {
                ret = MoveMode.valueOf(retStr);
            } catch (Exception e) {
                ret = MoveMode.MOVE_WALK;
            }
        }
        return ret;
    }
    
    protected int[] extractTeleportTarget(Map<String, String> parameters) {
        int[] ret = new int[3];
        if (parameters.containsKey(EngineParameters.MOVE_TELEPORTDESTINATION)) {
            try {
                String ints = parameters.get(EngineParameters.MOVE_TELEPORTDESTINATION);
                String[] splitInts = ints.split(EngineParameters.PARAMETER_DELIMITER);
                for (int i = 0; i < ret.length; i++){
                    ret[i] = new Integer(splitInts[i]);
                }
            } catch (Exception e) {
                ret = new int[] {this.position.getX(),this.position.getY(),this.position.getZ()};
            }
        }
        return ret;
    }
    
    protected void registerNewPosition(Coordinate oldPosition) {
        //TODO: once map storage methods develop, add position re-registration;
    }

    protected void logException(Exception e) {
        System.out.println(e.toString());
        for (StackTraceElement stackTraceElement: e.getStackTrace()) {
            System.out.println(stackTraceElement.toString());
        }
    }
	
	
	
	
	
}
