package ui.messages;

import org.newdawn.slick.GameContainer;

import objects.common.messages.Message;

/**
 * The renderer for messages of the type MessageType.TOAST.
 * @author Blay09
 *
 */
public class ToastMessageRenderer extends MessageRenderer {
	
	public ToastMessageRenderer(GameContainer newGC) {
		super(newGC);
	}

	/**
	 * Moves all other messages upwards to make space for the new one.
	 * @see ui.messages.MessageRenderer#getGUIMessage(ui.messages.MessageRenderer, objects.common.messages.Message)
	 */
	@Override
	public GUIMessage getGUIMessage(MessageRenderer newRenderer, Message newMessage) {
		for(int i = 0; i < messages.size(); i++) {
			((ToastMessage)messages.get(i)).addDisplayY(-gc.getDefaultFont().getHeight(newMessage.getMessageContents()));
		}
		return new ToastMessage(this, newMessage);
	}
	
}
