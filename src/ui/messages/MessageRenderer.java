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
	private Vector<GUIMessage> removeList;
	
	public MessageRenderer(GameContainer newGC) {
		gc = newGC;
		messages = new Vector<GUIMessage>();
		removeList = new Vector<GUIMessage>();
	}
	
	public void addMessage(Message newMessage) {
		messages.add(getGUIMessage(this, newMessage));
	}
	
	public void removeMessage(GUIMessage newMessage) {
		removeList.add(newMessage);
	}

	public abstract GUIMessage getGUIMessage(MessageRenderer newRenderer, Message newMessage);
	
	public void update(GameContainer gc, int delta) {
		for(int i = 0; i < removeList.size(); i++) {
			messages.remove(removeList.get(i));
		}
		for(int i = 0; i < messages.size(); i++) {
			messages.get(i).update(gc, delta);
		}
	}
	
	public void render(GameContainer gc, Graphics g) {
		for(int i = 0; i < messages.size(); i++) {
			messages.get(i).render(gc, g);
		}
	}
}
