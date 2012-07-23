package ui.messages;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import objects.common.messages.Message;

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
	
	public abstract void update(GameContainer gc, int delta);
	public abstract void render(GameContainer gc, Graphics g);
}
