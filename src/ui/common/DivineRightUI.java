package ui.common;

import objects.common.enums.MessageType;
import objects.common.messages.Message;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import ui.graphics.Camera;
import ui.graphics.MapDisplay;
import ui.messages.GUIMessageManager;

/**
 * The basic class of the UI, which combines all elements together and creates the window for the game.
 * @author Blay09
 *
 */
public class DivineRightUI extends BasicGame {

	private static final String WINDOW_TITLE = "Divine Right";
	
	private Camera camera;
	private MapDisplay mapDisplay;
	private GUIMessageManager messageManager;
	
	public DivineRightUI() {
		super(WINDOW_TITLE);
	}
	
	/**
	 * Starts the UI, creating a game window and blocking the caller thread from then on.
	 */
	public static void startUI() {
		try {
			AppGameContainer gc = new AppGameContainer(new DivineRightUI());
			gc.setDisplayMode(800, 600, false);
			gc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initializes the UI components.
	 */
	@Override
	public void init(GameContainer gc) throws SlickException {
		gc.getInput().enableKeyRepeat();
		camera = new Camera(gc.getWidth(), gc.getHeight());
		mapDisplay = new MapDisplay(this);
		messageManager = new GUIMessageManager(gc);
	}

	/**
	 * Forwards the input event to the message manager.
	 */
	@Override
	public void mouseReleased(int mouseButton, int mouseX, int mouseY) {
		messageManager.mouseReleased(mouseButton, mouseX, mouseY);
	}
	
	/**
	 * Forwards the input event to the message manager.
	 */
	@Override
	public void keyReleased(int keyCode, char unicode) {
		if(keyCode == Input.KEY_RETURN) {
			messageManager.addMessage(new Message(MessageType.TOAST, "Test-Toast-Message"));
		} else if(keyCode == Input.KEY_SPACE) {
			messageManager.addMessage(new Message(MessageType.TOAST, "Test-Toast-Message #2"));
		} else if(keyCode == Input.KEY_1) {
			messageManager.addMessage(new Message(MessageType.MODAL, "Testing modal messages is fun. yippieh!\nIt even supports multiline.\n\nAwesome, eh?"));
		} else if(keyCode == Input.KEY_2) {
			messageManager.addMessage(new Message(MessageType.MODAL, "Testing modal messages is extremely boring.\nI hope I can do something else soon.\n\n\n\n\n\nHm..\nadöjhqwökghwekhgklöwehfkwjöqfjqföqjf"));
		} else if(keyCode == Input.KEY_ESCAPE) {
			System.exit(0);
		} else {
			messageManager.keyReleased(keyCode, unicode);
		}
	}
	
	@Override
	public void keyPressed(int keyCode, char unicode) {
		if(keyCode == Input.KEY_LEFT) {
			camera.setOffset(camera.getOffsetX() - 2, camera.getOffsetY());
		} else if(keyCode == Input.KEY_RIGHT) {
			camera.setOffset(camera.getOffsetX() + 2, camera.getOffsetY());
		} else if(keyCode == Input.KEY_UP) {
			camera.setOffset(camera.getOffsetX(), camera.getOffsetY() - 2);
		} else if(keyCode == Input.KEY_DOWN) {
			camera.setOffset(camera.getOffsetX(), camera.getOffsetY() + 2);
		}
	}
	
	/**
	 * Updates all UI elements.
	 */
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		messageManager.update(gc, delta);
		camera.update(gc, delta);
		mapDisplay.update(gc, delta);
	}
	
	/**
	 * Renders all UI elements to the screens.
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		mapDisplay.render(gc, g);
		messageManager.render(gc, g);
	}
	
	public Camera getCamera() {
		return camera;
	}
}
