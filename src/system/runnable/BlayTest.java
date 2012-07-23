package system.runnable;

import objects.common.enums.MessageType;
import objects.common.messages.Message;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import ui.graphics.Camera;
import ui.graphics.MapDisplay;
import ui.messages.GUIMessageManager;

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
	
	private Camera camera;
	private MapDisplay mapDisplay;
	private GUIMessageManager messageManager;
	
	public BlayTest() {
		super("Divine Right");
	}
	

	@Override
	public void init(GameContainer gc) throws SlickException {
		camera = new Camera();
		mapDisplay = new MapDisplay();
		messageManager = new GUIMessageManager(gc);
	}

	@Override
	public void keyReleased(int keyCode, char unicode) {
		if(keyCode == Input.KEY_RETURN) {
			messageManager.addMessage(new Message(MessageType.TOAST, "Test-Toast-Message"));
		} else if(keyCode == Input.KEY_SPACE) {
			messageManager.addMessage(new Message(MessageType.TOAST, "Test-Toast-Message #2"));
		}
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		messageManager.update(gc, delta);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		camera.applyOffset(g);
		mapDisplay.render(gc, g);
		camera.resetOffset(g);
		messageManager.render(gc, g);
	}
}
