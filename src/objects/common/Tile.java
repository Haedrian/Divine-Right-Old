package objects.common;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a STATIC tile.
 * Other (non-tile) items may be placed upon it, but it is not necessarily walkable.
 * @author Llama
 *
 */
public class Tile extends MapItem {
    
    List<MapItem> itemsOnTile = new LinkedList<MapItem>();
    
    public List<MapItem> getItemsOnTile() {
        return itemsOnTile;
    }

}
