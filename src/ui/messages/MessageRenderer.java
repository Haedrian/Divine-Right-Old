package ui.messages;

import java.util.Vector;

import objects.common.messages.Message;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Abstract class for the different message renderers, providing common functionality.
 * @author Blay09
 *
 */
public abstract class MessageRenderer {

	protected GameContainer gc;
	protected Vector<GUIMessage> messages;
	protected Vector<GUIMessage> removeList;
	
	/**
	 * Creates a new empty instance of a message renderer.
	 * @param newGC
	 */
	public MessageRenderer(GameContainer newGC) {
		gc = newGC;
		messages = new Vector<GUIMessage>();
		removeList = new Vector<GUIMessage>();
	}
	
	/**
	 * Add a message to this renderer. 
	 * This is where the message gets converted into a GUIMessage that can be rendered.
	 * @param newMessage
	 */
	public void addMessage(Message newMessage) {
		GUIMessage newGUIMessage = getGUIMessage(this, newMessage);
		newGUIMessage.init(gc);
		messages.add(newGUIMessage);
	}
	
	/**
	 * Mark the GUIMessage as dead so it gets removed in the next update call.
	 * @param newMessage
	 */
	public void removeMessage(GUIMessage newMessage) {
		removeList.add(newMessage);
	}

	/**
	 * Create a graphical representation for the received Message in form of a GUIMessage.
	 * @param newRenderer
	 * @param newMessage
	 * @return
	 */
	public abstract GUIMessage getGUIMessage(MessageRenderer newRenderer, Message newMessage);
	
	/**
	 * Remove all messages that are marked dead and forward the update call to all other messages.
	 * @param gc
	 * @param delta
	 */
	public void update(GameContainer gc, int delta) {
		for(int i = 0; i < removeList.size(); i++) {
			messages.remove(removeList.get(i));
		}
		for(int i = 0; i < messages.size(); i++) {
			messages.get(i).update(gc, delta);
		}
	}
	
	/**
	 * Render the messages on the screen.
	 * @param gc
	 * @param g
	 */
	public void render(GameContainer gc, Graphics g) {
		for(int i = 0; i < messages.size(); i++) {
			messages.get(i).render(gc, g);
		}
	}
	
	public void keyReleased(int keyCode, char unicode) {
	}
	
	public void mouseReleased(int mouseButton, int mouseX, int mouseY) {
	}
}
