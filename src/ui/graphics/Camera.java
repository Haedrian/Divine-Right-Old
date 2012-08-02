package ui.graphics;

import objects.common.enums.MapType;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import ui.common.UIConstants;
import ui.common.Viewport;

/**
 * Offers functions to attach the camera to or detach it from an avatar, and move it around freely.
 * @author Blay09
 *
 */
public class Camera {
	
	private int width;
	private int height;
	private int tilesOnScreenX;
	private int tilesOnScreenY;
	private int levelsOnScreen;
	private Avatar attachedTo;
	private Viewport viewport;
	private int offsetX;
	private int offsetY;
	private int currentZ;
	private boolean dirtyViewport;
	private MapType mapType;
	
	public Camera(int newWidth, int newHeight) {
		width = newWidth;
		height = newHeight;
		tilesOnScreenX = (int) Math.ceil(width / UIConstants.TILE_WIDTH) + 1;
		tilesOnScreenY = (int) Math.ceil(height / UIConstants.TILE_HEIGHT);
		levelsOnScreen = (int) Math.ceil(height / UIConstants.Z_LEVEL_OFFSET) / 2;
		viewport = new Viewport();
		recalculateViewport();
	}
	
	/**
	 * Takes care of smooth moving for the camera and updates the offset in case it's attached to an avatar.
	 * @param gc
	 * @param delta
	 */
	public void update(GameContainer gc, int delta) {
		if(attachedTo != null) {
			int oldOffsetX = offsetX;
			int oldOffsetY = offsetY;
			offsetX = attachedTo.getDisplayX();
			offsetY = attachedTo.getDisplayY();
			// TODO update currentZ
			currentZ = 0;
			if(oldOffsetX != offsetX || oldOffsetY != offsetY) {
				recalculateViewport();
			}
		}
	}
	
	/**
	 * Returns the offset on the x axis in pixels that is applied to the renderer.
	 * @return
	 */
	public int getOffsetX() {
		return offsetX;
	}
	
	/**
	 * Returns the offset on the y axis in pixels that is applied to the renderer.
	 * @return
	 */
	public int getOffsetY() {
		return offsetY;
	}
	
	/**
	 * Applies the camera's offset to the given graphics instance.
	 * @param g
	 */
	public void applyOffset(Graphics g) {
		g.translate(-offsetX, -offsetY);
	}
	
	/**
	 * Resets the offset of the graphics instance to allow rendering of UI elements above the map.
	 * @param g
	 */
	public void resetOffset(Graphics g) {
		g.resetTransform();
	}
	
	/**
	 * Attaches the camera to a specific Avatar.
	 * @param newAvatar
	 */
	public void attachTo(Avatar newAvatar) {
		attachedTo = newAvatar;
	}
	
	/**
	 * Detachs the camera from whatever Avatar it is following right now.
	 */
	public void detachAvatar() {
		attachedTo = null;
	}

	public MapType getMapType() {
		return mapType;
	}
	
	/**
	 * Returns true if the camera moved since the last update call.
	 * @return
	 */
	public boolean viewportChanged() {
		return dirtyViewport;
	}
	
	private void recalculateViewport() {
		Viewport oldViewport = viewport;
		viewport = new Viewport();
		
		int startX = (int) Math.floor(offsetX / UIConstants.TILE_WIDTH);
		int startY = (int) Math.floor(offsetY / UIConstants.TILE_HEIGHT);
		int startZ = currentZ - levelsOnScreen;
		int endX = startX + tilesOnScreenX;
		int endY = startY + tilesOnScreenY;
		int endZ = currentZ + levelsOnScreen;
		viewport.set(startX, startY, startZ, endX, endY, endZ);
		
		if(!viewport.equals(oldViewport)) {
			dirtyViewport = true;
		}
	}

	/**
	 * Returns the area that is currently visible on the map.
	 * @return
	 */
	public Viewport getViewport() {
		return viewport;
	}

	/**
	 * Resets the dirty state of the viewport.
	 */
	public void resetViewportState() {
		dirtyViewport = false;
	}
	
	public void setOffset(int newOffsetX, int newOffsetY) {
		offsetX = newOffsetX;
		offsetY = newOffsetY;
		recalculateViewport();
	}

}
