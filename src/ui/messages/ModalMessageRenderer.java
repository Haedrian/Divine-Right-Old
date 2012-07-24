package ui.messages;

import objects.common.messages.Message;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * The renderer for messages of the type MessageType.MODAL.
 * @author Blay09
 *
 */
public class ModalMessageRenderer extends MessageRenderer {
	
	public ModalMessageRenderer(GameContainer newGC) {
		super(newGC);
	}

	@Override
	public GUIMessage getGUIMessage(MessageRenderer newRenderer, Message newMessage) {
		return new ModalMessage(newRenderer, newMessage);
	}

	/**
	 * Overridden so only one message is updated at a time.
	 * @see ui.messages.MessageRenderer#update(org.newdawn.slick.GameContainer, int)
	 */
	@Override
	public void update(GameContainer gc, int delta) {
		for(int i = 0; i < removeList.size(); i++) {
			messages.remove(removeList.get(i));
		}
		if(!messages.isEmpty()) {
			messages.get(0).update(gc, delta);
		}
	}
	
	/**
	 * Overridden so only one message is shown at a time.
	 * @see ui.messages.MessageRenderer#render(org.newdawn.slick.GameContainer, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer gc, Graphics g) {
		if(!messages.isEmpty()) {
			messages.get(0).render(gc, g);
		}
	}
	
	@Override
	public void keyReleased(int keyCode, char unicode) {
		closeMessage();
	}
	
	@Override
	public void mouseReleased(int mouseButton, int mouseX, int mouseY) {
		closeMessage();
	}
	
	private void closeMessage() {
		if(!messages.isEmpty()) {
			removeMessage(messages.get(0));
		}
	}
}
