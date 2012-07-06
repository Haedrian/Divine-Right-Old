package objects.common;

import java.util.List;

import objects.common.enums.ActionType;
import objects.common.enums.MessageType;

/**
 * Represents all items which may be displayed on the local or world map
 * @author Llama
 *
 */
public abstract class MapItem 
{
	private int xCoordinate;
	private int yCoordinate;
	private int zCoordinate;
	private String name;
	private String graphic;
	private boolean walkable;
	private MapTile tile;
	
	/**
	 * Performs a Particular Action on this Item and gets a Message
	 * @param action
	 * @return
	 */
	public abstract Message performAction(Action action);
	
	/**
	 * Shows a list of Actions which this Item an support 
	 * @return
	 */
	public abstract List<ActionType> showSupportedActions();
	
	/**
	 * Examines this particular item
	 * @param actor
	 * @return
	 */
	public Message examine ()
	{
		Message message = new Message();
		message.setType(MessageType.TOASTER);
		message.setMessageContents(name);
		
		return message;
	}
	
	/**
	 * Uses the particular item
	 * @return
	 */
	public Message use()
	{
		Message message = new Message();
		message.setType(MessageType.EMPTY);
		return message;
	}
	
	
	
	/***
	 * Returns the X Coordinate of this Item
	 * @return
	 */
	public int getxCoordinate() {
		return xCoordinate;
	}

	/**
	 * Sets the X Coordinate of this item
	 * @param xCoordinate
	 */
	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	/**
	 * Returns the Y Coordinate of this Item
	 * @return
	 */
	public int getyCoordinate() {
		return yCoordinate;
	}

	/**
	 * Sets the Y Coordinate of this Item
	 * @param yCoordinate
	 */
	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	/***
	 * Gets the Z Coordinate of this Item
	 * @return
	 */
	public int getzCoordinate() {
		return zCoordinate;
	}
	/***
	 * Sets the Z Coordinate of this item
	 * @param zCoordinate
	 */
	public void setzCoordinate(int zCoordinate) {
		this.zCoordinate = zCoordinate;
	}

	/**
	 * Returns the Name of the Item
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Name of the Item
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the Graphic which represents the Item
	 * @return
	 */
	public String getGraphic() {
		return graphic;
	}

	/**
	 * Sets the Graphic which will be used to represent the item
	 * @param graphic
	 */
	public void setGraphic(String graphic) {
		this.graphic = graphic;
	}
	/**
	 * Can this item be walked on?
	 * @return
	 */
	public boolean isWalkable() {
		return walkable;
	}
	/**
	 * Sets whether the item can be walked on or not
	 * @param walkable
	 */
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	/**
	 * Represents the tile this item is standing on. If this item is a tile doesn't need to be considered
	 * @return
	 */
	public MapTile getTile() {
		return tile;
	}

	/**
	 * Sets the tile this item is standing on. if this item is a tile it doesn't need to be used.
	 * @param tile
	 */
	public void setTile(MapTile tile) {
		this.tile = tile;
	}
	
	
}
