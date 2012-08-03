package ui.graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import ui.common.UIConstants;
import ui.resources.ImageLoader;

/**
 * This class is used for the rendering of tiles.
 * @author Blay09
 *
 */
public class Tile extends DisplayItem {

	private Image image;
	
	public Tile() {
		
	}
	
	@Override
	public void update(GameContainer gc, int delta) {

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		image.draw(displayX, displayY, UIConstants.TILE_WIDTH, UIConstants.TILE_HEIGHT);
//		g.setColor(Color.green);
//		g.fillRect(displayX, displayY, UIConstants.TILE_WIDTH, UIConstants.TILE_HEIGHT);
//		g.setColor(Color.black);
//		g.drawRect(displayX, displayY, UIConstants.TILE_WIDTH, UIConstants.TILE_HEIGHT);
////		g.scale(0.5f, 0.5f);
////		g.drawString(coords.getX() + ",\n" + coords.getY() + ",\n" + coords.getZ(), displayX * 2, displayY * 2);
////		g.scale(2f, 2f);
	}

	@Override
	public void recycle() {
		TileRecycler.getInstance().recycle(this);
	}

	public void setImage(String newTileGraphic) {
		image = ImageLoader.getInstance().getImage(newTileGraphic);
	}

}
