package objects.common;

import java.util.*;

public class Region {

protected ArrayList<GlobalMapItem> region = new ArrayList<GlobalMapItem>();
protected Coordinate center;


/**
 * @return the GlobalMapItems belonging to a region
 */
public ArrayList<GlobalMapItem> getRegion() {
	return region;
}
/**
 * @param region the region to set
 */
public void setRegion(ArrayList<GlobalMapItem> region) {
	this.region = region;
}
/**
 * @return the center of region
 */

public void addToRegion(GlobalMapItem newitem) {
	region.add(newitem);
	newitem.setIsInRegion();
}

/**
 * @param globalmapitem
 * @return True if globalmapitem is in region, false otherwise
 */
public boolean checkIfInRegion (GlobalMapItem globalmapitem){
	return region.contains(globalmapitem);
}

public Coordinate getCenter() {
	return center;
}
/**
 * @param center sets the center of the region
 */
public void setCenter(Coordinate center) {
	this.center = center;
}
 
//check if a tile is adjacent to a region. A tile contained in the region is considered adjacent to the region.
public boolean isANeighbor(GlobalMapItem gmi){
     return gmi.isANeighbor(this);
  }

//check if a region is adjacent to a region, i.e. they have at least 1 pair of adjacent tiles.
public boolean isANeighbor(Region region){
	boolean flag = false;
	for (int i = 0; i < region.getRegion().size(); i++){
	   flag = flag || this.isANeighbor(region.getRegion().get(i));
	   if (flag) {break;}
	}
	return flag;	
  }
}
