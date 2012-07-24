package ui.messages;

import objects.common.messages.Message;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Represents a message that appears and dissappears again after a bit.
 * @author Blay09
 *
 */
public class ToastMessage extends GUIMessage {

	private static final int LIFETIME_PER_CHAR = 100;
	
	private int displayY;
	private int lifeTime;
	
	/**
	 * Set the lifetime of this message depending on the length of the text.
	 * @param newRenderer
	 * @param newMessage
	 */
	public ToastMessage(MessageRenderer newRenderer, Message newMessage) {
		super(newRenderer, newMessage);
		lifeTime = LIFETIME_PER_CHAR * newMessage.getMessageContents().length();
	}
	
	/**
	 * Moves this message on the Y scala.
	 * @param newY
	 */
	public void addDisplayY(int newY) {
		displayY += newY;
	}
	
	/**
	 * Set initial Y coordinate of this message to the center of the screen.
	 * @see ui.messages.GUIMessage#init(org.newdawn.slick.GameContainer)
	 */
	@Override
	public void init(GameContainer gc) {
		displayY = gc.getHeight() / 2;
	}
	
	/**
	 * Decrease the lifetime of this message and mark it as dead when the time comes.
	 * @see ui.messages.GUIMessage#update(org.newdawn.slick.GameContainer, int)
	 */
	@Override
	public void update(GameContainer gc, int delta) {
		lifeTime -= delta;
		if(lifeTime <= 0) {
			renderer.removeMessage(this);
			return;
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.white);
		g.drawString(message.getMessageContents(), 10, displayY);
	}

}
