package ui.graphics;

import java.util.LinkedList;

public class TileRecycler {
	
	private static TileRecycler instance;
	
	private LinkedList<Tile> unused;
	
	public TileRecycler() {
		unused = new LinkedList<Tile>();
	}
	
	/**
	 * Returns a recycled tile, or if none is available, a new tile.
	 * @return
	 */
	public Tile getTile() {
		if(!unused.isEmpty()) {
			return unused.removeFirst();
		}
		return new Tile();
	}
	
	/**
	 * Adds the tile to the list of recycled tiles.
	 * @param newTile
	 */
	public void recycle(Tile newTile) {
		unused.add(newTile);
	}
	
	/**
	 * Returns the singleton instance of this class.
	 * @return
	 */
	public static TileRecycler getInstance() {
		if(instance == null) {
			instance = new TileRecycler();
		}
		return instance;
	}
}
