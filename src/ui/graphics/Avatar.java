package ui.graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * This class is used for the rendering of actors.
 * It holds additional information that is required for animations and smooth movement.
 * @author Blay09
 *
 */
public class Avatar extends DisplayItem {
	
	private long id;

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
		g.setColor(Color.blue);
		g.fillRect(displayX, displayY, 30, 30);
	}
	
}
