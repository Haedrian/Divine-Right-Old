package ui.graphics;

import objects.common.enums.MapType;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import ui.common.Viewport;

/**
 * Offers functions to attach the camera to or detach it from an avatar, and move it around freely.
 * @author Blay09
 *
 */
public class Camera {
	
	private Avatar attachedTo;
	private Viewport viewport;
	private int offsetX;
	private int offsetY;
	private boolean dirtyViewport;
	private MapType mapType;
	
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
		g.translate(offsetX, offsetY);
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
		
		// TODO calculate viewport
		
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
}
