package ui.graphics;

import objects.common.Coordinate;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import ui.common.UIConstants;

/**
 * An item, be it a character, a tile or an object, that is displayed on the map.
 * @author Blay09
 *
 */
public abstract class DisplayItem {
	
	protected Coordinate coords;
	protected int displayX;
	protected int displayY;
	private int type;
	
	public DisplayItem() {
		
	}
	
	/**
	 * Calculates the display coordinates for this item.
	 * @param newCoords
	 */
	public void setDisplayPos(Coordinate newCoords) {
		coords = new Coordinate(newCoords);
		displayX = newCoords.getX() * UIConstants.TILE_WIDTH;
		displayY = newCoords.getY() * UIConstants.TILE_HEIGHT - newCoords.getZ() * UIConstants.Z_LEVEL_OFFSET;
	}
	
	/**
	 * Returns the display coordinates of this Avatar.
	 * @return
	 */
	public int getDisplayX() {
		return displayX;
	}
	
	/**
	 * Returns the display coordinates of this Avatar.
	 * @return
	 */
	public int getDisplayY() {
		return displayY;
	}
	
	public abstract void recycle();
	public abstract void update(GameContainer gc, int delta);
	public abstract void render(GameContainer gc, Graphics g);
}
