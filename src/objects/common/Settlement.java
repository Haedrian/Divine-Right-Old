package objects.common;

public abstract class Settlement extends MapItem 
{
	private int id;

	private String mapLocation;
	
	///TODO:
	private int population;
	private int income;
	private Settlement linkedTo;
	
	/**
	 * Represent a Unique Id for this Settlement
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets a Unique Id for this Settlement
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Represent the filename the system will look to find this Settlement's map
	 * @return
	 */
	public String getMapLocation() {
		return mapLocation;
	}

	/**
	 * Sets the filename the system will use to find this Settlement's map
	 * @param mapLocation
	 */
	public void setMapLocation(String mapLocation) {
		this.mapLocation = mapLocation;
	}
	
}
