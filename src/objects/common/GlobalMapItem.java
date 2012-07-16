package objects.common;

public class GlobalMapItem extends MapItem {

	protected int elevation;
	protected boolean IsInRegion = false;
	//protected ClimateType climatetype;
	//protected int climate;
	
	
	/**
	 * @return the elevation of global maptile
	 */
	public int getElevation() {
		return elevation;
	}
	/**
	 * @param elevation the elevation to set
	 */
	
	public void setElevation(int elevation) {
		this.elevation = elevation;
	} 
	
	public void setIsInRegion(){
		this.IsInRegion = true;
	}
	
	public boolean getIsInRegion(){
		return this.IsInRegion;
	}
}
