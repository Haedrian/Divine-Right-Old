package ui.messages;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import objects.common.messages.Message;

/**
 * The graphical representation of a Message received by the engine.
 * Inherited by specific classes for the different message types.
 * @author Blay09
 *
 */
public abstract class GUIMessage {
	
	protected MessageRenderer renderer;
	protected Message message;
	
	/**
	 * Creates a new GUIMessage instance based on the given Message.
	 * @param newMessage
	 */
	public GUIMessage(MessageRenderer newRenderer, Message newMessage) {
		renderer = newRenderer;
		message = newMessage;
	}
	
	/**
	 * Called once when the message is added to a renderer.
	 * Prepare the message to be rendered.
	 * @param gc
	 */
	public abstract void init(GameContainer gc);
	
	/**
	 * Perform movements, fading, lifetime checks and things like that.
	 * @param gc
	 * @param delta
	 */
	public abstract void update(GameContainer gc, int delta);
	
	/**
	 * Render the message to the screen.
	 * @param gc
	 * @param g
	 */
	public abstract void render(GameContainer gc, Graphics g);
}
