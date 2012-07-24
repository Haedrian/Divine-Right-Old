package ui.messages;

import objects.common.messages.Message;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Represents a message that pops up and blocks the screen.
 * @author Blay09
 *
 */
public class ModalMessage extends GUIMessage {

	private static final Color BACKGROUND_COLOR = new Color(0, 0, 0, 128);
	private static final int BORDER_SIZE = 5;
	
	private int displayX;
	private int displayY;
	private int width;
	private int height;
	
	public ModalMessage(MessageRenderer newRenderer, Message newMessage) {
		super(newRenderer, newMessage);
	}

	/**
	 * Setup the values for the background of this modal message.
	 * @see ui.messages.GUIMessage#init(org.newdawn.slick.GameContainer)
	 */
	@Override
	public void init(GameContainer gc) {
		width = gc.getDefaultFont().getWidth(message.getMessageContents()) + BORDER_SIZE * 2;
		height = gc.getDefaultFont().getHeight(message.getMessageContents()) + BORDER_SIZE * 2;
		displayX = gc.getWidth() / 2 - width / 2;
		displayY = gc.getHeight() / 2 - height / 2;
	}
	
	@Override
	public void update(GameContainer gc, int delta) {
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(displayX, displayY, width, height);
		g.setColor(Color.white);
		g.drawString(message.getMessageContents(), displayX + BORDER_SIZE, displayY + BORDER_SIZE);
	}

}
