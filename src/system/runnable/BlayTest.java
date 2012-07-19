package system.runnable;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ui.resources.ImageLoader;

/**
 * 
 * @author Blay09
 *
 */
public class BlayTest extends BasicGame {
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer gc = new AppGameContainer(new BlayTest());
		gc.setDisplayMode(800, 600, false);
		gc.start();
	}
	
	private Image testImage;
	
	public BlayTest() {
		super("Divine Right");
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setColor(Color.black);
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		testImage.draw(gc.getWidth() / 2 - testImage.getWidth() / 2, gc.getHeight() / 2 - testImage.getWidth() / 2);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		testImage = ImageLoader.getInstance().getImage("test");
		testImage.setCenterOfRotation(testImage.getWidth() / 2, testImage.getHeight() / 2);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		testImage.rotate(delta * 0.2f);
	}
}
