package ui.graphics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Offers functions to attach the camera to or detach it from an avatar, and move it around freely.
 * @author Blay09
 *
 */
public class Camera {
	
	private Avatar attachedTo;
	private int offsetX;
	private int offsetY;
	
	/**
	 * Takes care of smooth moving for the camera and updates the offset in case it's attached to an avatar.
	 * @param gc
	 * @param delta
	 */
	public void update(GameContainer gc, int delta) {
		if(attachedTo != null) {
			offsetX = attachedTo.getDisplayX();
			offsetY = attachedTo.getDisplayY();
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
}
