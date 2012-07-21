package factory;

import java.util.ArrayList;

import objects.common.MapItem;
import objects.common.Tile;
import objects.common.enums.MapItemType;
import objects.common.enums.PhysicalActionType;
import objects.common.exceptions.ItemNotFoundException;

/**
 * Converts ItemType into actual items
 * @author Llama
 *
 */
public class ItemFactory
{
	/**
	 * Produces a default map item from its type
	 * @param type
	 * @return
	 * @throws ItemNotFoundException If the item type isn't found
	 */
	public static MapItem CreateMapItem(MapItemType type) throws ItemNotFoundException
	{ 
		//Check the actual type
		
		//Its an ugly solution but nothing to do 
		
		if (type.toString().contains("LOCAL_TILE"))
		{
			return CreateMapItemLocalTile(type);
		}
		
		throw new ItemNotFoundException(type.toString() + " is not implemented yet");
		
	}
	/**
	 * Create a Map Item of Local Tile
	 * @param type 
	 * @return
	 * @throws ItemNotFoundException
	 */
	private static Tile CreateMapItemLocalTile(MapItemType type) throws ItemNotFoundException
	{
		Tile ret = new Tile();
		
		switch (type)
		{
		case LOCAL_TILE_AIR:
			ret.setGraphic("none");
			ret.setIsWalkable(true);
			ret.setName("");
			ret.setSupportedPhysicalActions(new ArrayList<PhysicalActionType>()); //no actions supported
			return ret;
							
		}
		
		throw new ItemNotFoundException(type.toString() + " is not implemented yet");
	}

}
