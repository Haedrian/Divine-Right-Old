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



	
}
