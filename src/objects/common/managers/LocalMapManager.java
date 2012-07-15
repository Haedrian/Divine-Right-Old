package objects.common.managers;

import objects.common.Coordinate;
import objects.common.MapItem;
import objects.common.Tile;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * User: Yury
 * Date: 14/07/12
 * Time: 13:45
 */
public class LocalMapManager {

    protected static HashMap<Coordinate,Tile> localMap = new HashMap<Coordinate, Tile>();
    


    public static HashMap<Coordinate, Tile> getLocalMap() {
        return localMap;
    }

    public static void setLocalMap(HashMap<Coordinate, Tile> localMap) {
        LocalMapManager.localMap = localMap;
    }
    
    public static Tile getTileAt(Coordinate coor) {
        return localMap.get(coor);
    }

    /**
     * NullPointer-safe way of retrieeing items on tile;
     * @param coor
     * @return
     */
    public static List<MapItem> getMapItemsAt(Coordinate coor) {
        Tile tile = getTileAt(coor);
        if (tile != null) {
            return tile.getItemsOnTile();
        }
        return new LinkedList<MapItem>();
    }
}
