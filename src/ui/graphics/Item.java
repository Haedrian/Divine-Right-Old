package ui.graphics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import ui.common.UIConstants;
import ui.resources.ImageLoader;

/**
 * This class is used for the rendering of items.
 * @author Blay09
 *
 */
public class Item extends DisplayItem {

	private Image image;
	
	public Item() {
		
	}
	
	@Override
	public void update(GameContainer gc, int delta) {

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		image.draw(displayX, displayY, UIConstants.TILE_WIDTH, UIConstants.TILE_HEIGHT);
	}

	public void setImage(String newItemGraphic) {
		image = ImageLoader.getInstance().getImage(newItemGraphic);
	}

	@Override
	public void recycle() {
	}

}
