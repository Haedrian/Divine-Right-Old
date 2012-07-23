package ui.messages;

import org.newdawn.slick.GameContainer;

import objects.common.messages.Message;

public class ToastMessageRenderer extends MessageRenderer {

	private int startY;
	
	public ToastMessageRenderer(GameContainer newGC) {
		super(newGC);
		startY = gc.getWidth() / 2;
	}

	@Override
	public GUIMessage getGUIMessage(MessageRenderer newRenderer, Message newMessage) {
		for(int i = 0; i < messages.size(); i++) {
			((ToastMessage)messages.get(i)).addDisplayY(-gc.getDefaultFont().getHeight(newMessage.getMessageContents()));
		}
		ToastMessage newGUIMessage = new ToastMessage(this, newMessage);
		newGUIMessage.setDisplayY(startY);
		return newGUIMessage;
	}
	
}
