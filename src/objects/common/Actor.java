package objects.common;

public abstract class Actor extends MapItem 
{
	private int id;
	
	/**
	 * Represent a Unique Id for this Actor
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets a Unique Id for this Actor
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
}
