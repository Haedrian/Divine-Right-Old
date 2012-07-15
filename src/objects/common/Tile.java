package objects.common;

import objects.common.enums.MoveMode;

import java.util.HashMap;
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
    List<MoveMode> forbiddenTileModes = new LinkedList<MoveMode>(); //modes forbidden by tile type
    HashMap<MoveMode,List<MapItem>> forbiddenMoveModes = new HashMap<MoveMode, List<MapItem>>(); //walking modes forbidden by items
    
    public List<MapItem> getItemsOnTile() {
        return itemsOnTile;
    }

    public boolean isMoveModeAllowed(MoveMode mode) {
        boolean ret = forbiddenTileModes.contains(mode);
        if (ret) {
            ret = forbiddenMoveModes.containsKey(mode);
            if (ret) {
                ret = forbiddenMoveModes.get(mode).size() > 0;
            }
        }
        return !ret;
    }
}
