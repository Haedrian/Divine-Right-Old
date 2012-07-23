package objects.common;

/**
 * Represents a Tile on a Global Map.
 * @author 
 *
 */
public class GlobalTile extends Tile
{
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
	public boolean isANeighbor(GlobalTile globalTile){
	   if (Math.abs(this.getPosition().getX() - globalTile.getPosition().getX()) <= 1 && Math.abs(this.getPosition().getY() - globalTile.getPosition().getY()) <= 1)
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

