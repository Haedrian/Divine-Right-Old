package comparators;

import java.util.Comparator;

import objects.common.Region;


public class CompareYCoord implements Comparator<Region> {
 @Override
 public int compare(Region c1, Region c2) {
	        return c1.getCenter().getY() - (c2.getCenter().getY());
	        }
}


