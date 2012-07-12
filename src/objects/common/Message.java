package objects.common;

import objects.common.enums.MessageType;

public class Message 
{
	private MessageType type;
	private String messageContents;
	
	public Message(MessageType type, String contents)
	{
		this.type = type;
		this.messageContents = contents;
		
	}
	
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
	
	
}
