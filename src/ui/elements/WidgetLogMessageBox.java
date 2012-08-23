package ui.elements;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import objects.common.messages.Message;

import ui.elements.basic.GuiLabel;

/**
 * The display for messages of the type LOG.
 * @author Blay09
 *
 */
public class WidgetLogMessageBox extends GuiWidget {
	
	private static final int MAX_STORED_MESSAGES = 5;
	private static final Color TEXT_COLOR = Color.white;
	private static final Color BG_COLOR = new Color(0, 0, 0, 128);
	
	private Font logFont;
	private LinkedList<GuiLabel> messages;
	
	public WidgetLogMessageBox(GameContainer gc) {
		messages = new LinkedList<GuiLabel>();
		logFont = gc.getDefaultFont();
		
		setSize(300, 100);
	}
	
	/**
	 * Add a log message to this display.
	 * TODO: remove the ugly positioning and put the entries into a scrollpane later
	 * @param newMessage
	 */
	public void addMessage(Message newMessage) {
		GuiLabel newLabel = new GuiLabel(newMessage.getMessageContents(), TEXT_COLOR, logFont);
		newLabel.setSizeToLabel();
		if(messages.size() >= MAX_STORED_MESSAGES) {
			GuiLabel removed = messages.removeFirst();
			removeChild(removed);
		}
		for(GuiLabel message : messages) {
			message.setY(message.getY() - newLabel.getHeight());
		}
		newLabel.setY(getHeight() - newLabel.getHeight());
		newLabel.show();
		addChild(newLabel);
		messages.add(newLabel);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		if(isVisible()) {
			g.setColor(BG_COLOR);
			g.fillRect(getAbsoluteX(), getAbsoluteY(), getWidth(), getHeight());
			super.render(gc, g);
		}
	}
}
