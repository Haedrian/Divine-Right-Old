package ui.graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import ui.common.UIConstants;

/**
 * This class is used for the rendering of tiles.
 * @author Blay09
 *
 */
public class Tile extends DisplayItem {

	public Tile() {
		
	}
	
	@Override
	public void update(GameContainer gc, int delta) {

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.green);
		g.fillRect(displayX, displayY, UIConstants.TILE_WIDTH, UIConstants.TILE_HEIGHT);
	}

}
