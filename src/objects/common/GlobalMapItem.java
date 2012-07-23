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
	
	//check if two tiles are adjacent to eachother. A tile is a neighbor of itself
	public boolean isANeighbor(GlobalMapItem gmi2){
	   if (Math.abs(this.getPosition().getX() - gmi2.getPosition().getX()) <= 1 && Math.abs(this.getPosition().getY() - gmi2.getPosition().getY()) <= 1)
	   {return true;} 
	   else {return false;}
	}
	//check if a tile is adjacent to a region. A tile contained in the region is considered adjacent to the region.
	public boolean isANeighbor(Region region){
		boolean flag = false;
		for (int i = 0; i < region.getRegion().size(); i++){
		   flag = flag || this.isANeighbor(region.getRegion().get(i));
		   if (flag) {break;}
		}
		return flag;
	}
}
