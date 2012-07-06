package objects.common;

import objects.common.enums.ActionType;

public class Action 
{
	private ActionType type;
	private Actor user;
	private MapItem parameter;
	
	/**
	 * Creates an Action with a particular type and user
	 * @param type
	 * @param user
	 */
	public Action(ActionType type, Actor user)
	{
		this.type = type;
		this.user = user;
		
	}
	
	public Action()
	{
		
	}
	/**
	 * Creates an Action with a particular type, user and parameter
	 * @param type
	 * @param user
	 * @param parameter
	 */
	public Action(ActionType type, Actor user, MapItem parameter)
	{
		this.type = type;
		this.user = user;
		this.parameter = parameter;
	}
	
	/**
	 * Returns the type of the Action to be performed
	 * @return
	 */
	public ActionType getType() {
		return type;
	}
	/**
	 * Sets the type of Action
	 * @param type
	 */
	public void setType(ActionType type) {
		this.type = type;
	}
	/**
	 * Gets the user which performed this action
	 * @return
	 */
	public Actor getUser() {
		return user;
	}
	/**
	 * Sets the user which performed this action
	 * @param user
	 */
	public void setUser(Actor user) {
		this.user = user;
	}
	/**
	 * Gets a Map Item which is to be used as well as part of this action
	 * @return
	 */
	public MapItem getParameter() {
		return parameter;
	}
	/**
	 * Sets a Map Item which is to be used as well as part of this action
	 * @param parameter
	 */
	public void setParameter(MapItem parameter) {
		this.parameter = parameter;
	}
}
