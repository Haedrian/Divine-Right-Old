package objects.common.messages;

import java.util.LinkedList;

import objects.common.enums.MessageType;
/**
 * A message which is sent to the user
 * @author Llama
 *
 */
public class Message 
{
	private MessageType type;
	private String messageContents;
	private LinkedList<GUIActionChoice> consequences = new LinkedList<GUIActionChoice>();
	
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
	/**
	 * Gets the consequences. This is to be used for CHOICE messages only
	 */
	public LinkedList<GUIActionChoice> getConsequences()
	{
		return consequences;
	}

	/**
	 * Sets the consequences. This is to be used for CHOICE messages only
	 * @param consequences
	 */
	public void setConsequences(LinkedList<GUIActionChoice> consequences)
	{
		this.consequences = consequences;
	}
	
	
}
