package objects.common.messages;

import java.util.ArrayList;

/**
 * Represents a particular choice a player can make, and the consequences which follow
 * @author Llama
 *
 */
public class GUIActionChoice
{
	private String choiceName;
	private ArrayList<ActionAndParameters> consequences = new ArrayList<>();
	
	/**
	 * Gets the name of the Choice. This is displayed as the button
	 * @return
	 */
	public String getChoiceName()
	{
		return choiceName;
	}
	/**
	 * Sets the name of the Choice. This is displayed as the button
	 * @param choiceName
	 */
	public void setChoiceName(String choiceName)
	{
		this.choiceName = choiceName;
	}
	/**
	 * Gets the list of Actions which are to be performed if the user makes this choice
	 * @return
	 */
	public ArrayList<ActionAndParameters> getConsequences()
	{
		return consequences;
	}
	/**
	 * Sets the list of Actions which are to be performed if the user makes this choice
	 * @param consequences
	 */
	public void setConsequences(ArrayList<ActionAndParameters> consequences)
	{
		this.consequences = consequences;
	}
	
	
	
}
