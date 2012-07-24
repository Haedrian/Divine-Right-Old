package ui.messages;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import objects.common.enums.MessageType;
import objects.common.messages.Message;

/**
 * This class holds the different renderers and forwards messages to them.
 * @author Blay09
 *
 */
public class GUIMessageManager {
	
	private ToastMessageRenderer toastRenderer;
	private ModalMessageRenderer modalRenderer;
	
	/**
	 * Creates a new instance of the manager, and with that also initializes the different message renderers.
	 */
	public GUIMessageManager(GameContainer newGC) {
		toastRenderer = new ToastMessageRenderer(newGC);
		modalRenderer = new ModalMessageRenderer(newGC);
	}
	
	/**
	 * Adds the message to the appropriate message renderer.
	 * @param newMessage
	 */
	public void addMessage(Message newMessage) {
		MessageType mt = newMessage.getType();
		switch(mt) {
		case EMPTY:
			return;
		case TOAST:
			toastRenderer.addMessage(newMessage);
			break;
		case LOG:
			
			break;
		case MODAL:
			modalRenderer.addMessage(newMessage);
			break;
		case CHOICE:
			
			break;
		}
	}
	
	/**
	 * Forwards the update call to all message renderers.
	 * @param gc
	 * @param g
	 */
	public void update(GameContainer gc, int delta) {
		toastRenderer.update(gc, delta);
		modalRenderer.update(gc, delta);
	}
	
	/**
	 * Forwards the render call to all message renderers.
	 * @param gc
	 * @param g
	 */
	public void render(GameContainer gc, Graphics g) {
		toastRenderer.render(gc, g);
		modalRenderer.render(gc, g);
	}

	/**
	 * Forward the input event to the message renderers.
	 * @param mouseButton
	 * @param mouseX
	 * @param mouseY
	 */
	public void mouseReleased(int mouseButton, int mouseX, int mouseY) {
		modalRenderer.mouseReleased(mouseButton, mouseX, mouseY);
	}
	
	/**
	 * Forward the input event to the message renderers.
	 * @param mouseButton
	 * @param mouseX
	 * @param mouseY
	 */
	public void keyReleased(int keyCode, char unicode) {
		modalRenderer.keyReleased(keyCode, unicode);
	}
	
}
