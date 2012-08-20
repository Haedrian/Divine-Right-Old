package objects.common;

import java.util.List;

import objects.common.enums.MapItemType;
import objects.common.enums.MapObjectGroupType;
import objects.common.enums.PhysicalActionType;

/**
 * Represents an object which is to be displayed by the GUI
 * @author Llama
 *
 */
public class GUIObject
{
	protected Coordinate coordinate = new Coordinate();
	protected String tileGraphic = "";
	protected String topMapItemGraphic = "";
	protected List<String> itemsGraphics;
	protected long topMapItemId;
	protected MapItemType topItemType;
	protected long id;
	protected List<PhysicalActionType> possibleActions;
	protected String description = "";
	protected List<MapObjectGroupType> topItemGroup;
	
	/**
	 * Returns the GroupType list of the top item.
	 * @return The GroupType list of the top item, can be empty but will not be null
	 */
	public List<MapObjectGroupType> getTopItemGroup()
	{
		return topItemGroup;
	}
	
	/**
	 * Sets the GroupType list of the top Item.
	 * @param topItemGroup
	 */
	public void setTopItemGroup(List<MapObjectGroupType> topItemGroup)
	{
		this.topItemGroup = topItemGroup;
	}

	public GUIObject()
	{
		
	}
	
	/**
	 * Gets the Coordinate of the tile
	 * @return
	 */
	public Coordinate getCoordinate()
	{
		return coordinate;
	}
	/**
	 * Returns the Graphic of the Tile to be displayed
	 * @return
	 */
	public String getTileGraphic()
	{
		return tileGraphic;
	}
	/**
	 * Returns the unique id which this tile (and the objects upon it) use to refer to themselves.
	 * @return
	 */
	public long getId()
	{
		return id;
	}
	
	@Override
	/**
	 * Returns data for debugging purposes
	 */
	public String toString()
	{
		return id + "->" + coordinate.toString() + " Tile :" + this.tileGraphic + " Object: " + this.topMapItemGraphic;
	
	}
	/**
	 * Returns the list of possible actions to be performed upon this Object by the player character
	 * @return
	 */
	public List<PhysicalActionType> getPossibleActions()
	{
		return possibleActions;
	}

	/**
	 * Returns the string representing the top map item
	 * @return
	 */
	public String getTopMapItemGraphic()
	{
		return topMapItemGraphic;
	}

	/**
	 * Sets the top map item graphic
	 * @param topMapItemGraphic
	 */
	public void setTopMapItemGraphic(String topMapItemGraphic)
	{
		this.topMapItemGraphic = topMapItemGraphic;
	}

	/**
	 * Sets the coordinate of this Object
	 * @param coordinate
	 */
	public void setCoordinate(Coordinate coordinate)
	{
		this.coordinate = coordinate;
	}

	/**
	 * Sets the graphic for the tile
	 * @param tileGraphic
	 */
	public void setTileGraphic(String tileGraphic)
	{
		this.tileGraphic = tileGraphic;
	}

	/**
	 * Sets a unique id for this object
	 * @param id
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * Sets the list of possible actions which may be performed on this item
	 * @param possibleActions
	 */
	public void setPossibleActions(List<PhysicalActionType> possibleActions)
	{
		this.possibleActions = possibleActions;
	}
	/**
	 * Gets the description - that is the text to show when the user examines the item
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Sets the description - this is the text to show the user when he examines the item
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
/**
 * Gets the list of item graphics for all the items upon this tile
 * @return
 */
	public List<String> getItemsGraphics()
	{
		return itemsGraphics;
	}

	/**
	 * Sets the item graphics list for all items upon this tile
	 * @param itemsGraphics
	 */
	public void setItemsGraphics(List<String> itemsGraphics)
	{
		this.itemsGraphics = itemsGraphics;
	}

	/**
	 * Gets the id of the top map item. May be -1 if it has none
	 * @return
	 */
	public long getTopMapItemId()
	{
		return topMapItemId;
	}

	public void setTopMapItemId(long topMapItemId)
	{
		this.topMapItemId = topMapItemId;
	}

	/**
	 * Gets the type of the top Item on this tile. If there is no top item - will return the type of the tile
	 * @return
	 */
	public MapItemType getTopItemType()
	{
		return topItemType;
	}

	public void setTopItemType(MapItemType topItemType)
	{
		this.topItemType = topItemType;
	}


}
