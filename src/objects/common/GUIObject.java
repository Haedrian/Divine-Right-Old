package objects.common;

import java.util.List;

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
	protected long id;
	protected List<PhysicalActionType> possibleActions;
	protected String description = "";

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
	 * Returns the Graphic of the MapItem to be placed on top of the tile
	 * @return
	 */
	public String getTopObjectGraphic()
	{
		return topMapItemGraphic;
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


}
