package ui.graphics;

import objects.common.Coordinate;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import ui.common.UIConstants;
import ui.resources.ImageLoader;

/**
 * This class is used for the rendering of actors.
 * It holds additional information that is required for animations and smooth movement.
 * @author Blay09
 *
 */
public class Avatar extends DisplayItem {
	
	private long id;
	private Image image;

	/**
	 * Creates a new Avatar instance for the actor with the given id.
	 * @param newId
	 */
	public Avatar(long newId) {
		id = newId;
	}
	
	/**
	 * Returns the id of the actor this Avatar instance is rendering.
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * Updates smooth movement and animations.
	 */
	@Override
	public void update(GameContainer gc, int delta) {
		
	}
	
	/**
	 * Renders this Avatar to the screen.
	 * @param gc
	 * @param g
	 */
	@Override
	public void render(GameContainer gc, Graphics g) {
		image.draw(displayX, displayY, UIConstants.TILE_WIDTH, UIConstants.TILE_HEIGHT);
	}

	/**
	 * Do nothing
	 */
	@Override
	public void recycle() {		
	}

	/**
	 * Update this avatar's location, check for movement and start animation if needed.
	 * @param coordinate
	 */
	public void updateLocation(Coordinate coordinate) {
		setDisplayPos(coordinate);
	}

	public void setGraphicName(String newGraphicName) {
		image = ImageLoader.getInstance().getImage(newGraphicName);
	}
	
}
