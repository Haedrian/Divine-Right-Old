package objects.common;

import java.util.List;

import objects.common.enums.ActionType;

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
	protected List<ActionType> possibleActions;
	
	
	
	public GUIObject(Coordinate coordinate, long id, String tileGraphic, String topMapItemGraphic,List<ActionType> possibleActions)
	{
		this.coordinate = coordinate;
		this.id = id;
		this.tileGraphic = tileGraphic;
		this.topMapItemGraphic = topMapItemGraphic;
		
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
	public List<ActionType> getPossibleActions()
	{
		return possibleActions;
	}


}
