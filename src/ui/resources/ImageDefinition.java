package ui.resources;

/**
 * Holds additional information for an image.
 * @author Blay09
 *
 */
public class ImageDefinition {

	private int offsetX;
	private int offsetY;
	private int frameWidth;
	private int frameHeight;
	
	public ImageDefinition() {
		
	}

	/**
	 * Sets the x value for the offset on the map.
	 * @param newOffsetX
	 */
	public void setOffsetX(int newOffsetX) {
		offsetX = newOffsetX;
	}
	
	/**
	 * Sets the y value for the offset on the map.
	 * @param newOffsetY
	 */
	public void setOffsetY(int newOffsetY) {
		offsetY = newOffsetY;
	}
	
	/**
	 * Sets the width of one frame in case the image is animated.
	 * @param newFrameWidth
	 */
	public void setFrameWidth(int newFrameWidth) {
		frameWidth = newFrameWidth;
	}
	
	/**
	 * Sets the height of one frame in case the image is animated.
	 * @param newFrameHeight
	 */
	public void setFrameHeight(int newFrameHeight) {
		frameHeight = newFrameHeight;
	}
	
	/**
	 * Returns the offset in X direction when this image is displayed on the map.
	 * @return
	 */
	public int getOffsetX() {
		return offsetX;
	}
	
	/**
	 * Returns the offset in Y direction when this image is displayed on the map.
	 * @return
	 */
	public int getOffsetY() {
		return offsetY;
	}
	
	/**
	 * Returns width of a frame or 0 if not animated.
	 * @return
	 */
	public int getFrameWidth() {
		return frameWidth;
	}
	
	/**
	 * Returns height of a frame or 0 if not animated.
	 * @return
	 */
	public int getFrameHeight() {
		return frameHeight;
	}
}
