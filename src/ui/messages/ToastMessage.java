package ui.messages;

import objects.common.messages.Message;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class ToastMessage extends GUIMessage {

	private static final int LIFETIME_PER_CHAR = 100;
	
	private int displayY;
	private int lifeTime;
	
	public ToastMessage(MessageRenderer newRenderer, Message newMessage) {
		super(newRenderer, newMessage);
		lifeTime = LIFETIME_PER_CHAR * newMessage.getMessageContents().length();
	}
	
	public void setDisplayY(int newY) {
		displayY = newY;
	}
	
	public void addDisplayY(int newY) {
		displayY += newY;
	}
	
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
