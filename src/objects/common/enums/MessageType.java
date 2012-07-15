package objects.common.enums;

/**
 * Represents the type of Message to show to the user
 * @author Llama
 *
 */
public enum MessageType 
{
	EMPTY, //Represents no message at all
	TOAST, //A quick message which appears and disappears
	LOG,  //A message placed in the user's log
	MODAL, //A message which popsup and blocks the screen
	CHOICE //A modal message which offers multiple choices for the user to take
}
