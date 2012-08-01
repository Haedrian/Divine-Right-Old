package ui.common;

/**
 * Holds information about an area on the map in order to determine what items are currently visible.
 * @author Blay09
 *
 */
public class Viewport {
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private int startZ;
	private int endZ;
	
	public int getStartZ() {
		return startZ;
	}
	
	public int getEndZ() {
		return endZ;
	}
	
	public int getStartX() {
		return startX;
	}
	
	public int getStartY() {
		return startY;
	}
	
	public int getEndX() {
		return endX;
	}
	
	public int getEndY() {
		return endY;
	}
	
	/**
	 * Compares two viewports and returns true if they cover the same area.
	 * @param v
	 * @return
	 */
	public boolean equals(Viewport v) {
		if(startX != v.startX || startY != v.startY || startZ != v.startZ) {
			return false;
		}
		if(endX != v.endX || endY != v.endY || endZ != v.endZ) {
			return false;
		}
		return true;
	}
}
