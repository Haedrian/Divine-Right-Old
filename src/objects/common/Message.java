package objects.common;

import objects.common.enums.MessageType;

public class Message 
{
	private MessageType type;
	private String messageContents;
	private Action confimAction;
	
	/**
	 * Represents the type of the Message
	 * @return
	 */
	public MessageType getType() {
		return type;
	}
	/**
	 * Sets the type of the message
	 * @param type
	 */
	public void setType(MessageType type) {
		this.type = type;
	}
	/**
	 * Gets the text which the message will display
	 * @return
	 */
	public String getMessageContents() {
		return messageContents;
	}
	/**
	 * Sets the text which the message will display
	 * @param messageContents
	 */
	public void setMessageContents(String messageContents) {
		this.messageContents = messageContents;
	}
	/**
	 * For Messages which require a confirmation, this is the action which will occur if yes is chosen
	 * @return
	 */
	public Action getConfimAction() {
		return confimAction;
	}
	/**
	 * For Messages which require a confirmation, sets the action which will occur if yes is chosen
	 * @param confimAction
	 */
	public void setConfimAction(Action confimAction) {
		this.confimAction = confimAction;
	}
	
	
	
}
