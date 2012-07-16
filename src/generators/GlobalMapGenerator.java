package generators;

import objects.common.Coordinate;
import objects.common.GlobalMapItem;
import objects.common.Region;

import java.util.Random;
public class GlobalMapGenerator{ 

protected final int WORLDSIZE = 1000;
protected GlobalMapItem[][] globalmap = new GlobalMapItem[WORLDSIZE][WORLDSIZE];
protected Random random = new Random();
protected Region[] worldregion;
protected int regionflag;


/**
 * Inialises world map The elevation of the edges is set to -100, regions are initialized and populated
 * @author Byron
 */
public GlobalMapGenerator (int regionnumber){
	regionnumber = 1000;
	worldregion = new Region[regionnumber +1];
	for (GlobalMapItem[] mapItemRow : globalmap )
	{
		for (GlobalMapItem mapItem : mapItemRow)
		{
			mapItem = new GlobalMapItem();
			mapItem.setElevation(0);
			//TODO set climate parameters later
		}
		
	}
   //setting coordinates for GlobalMapItems
	for (int i = 0; i < WORLDSIZE; i++){
		for (int j = 0; j < WORLDSIZE; j++){
			globalmap[i][j].setPosition(new Coordinate(i, j, 0));
		}
	}
	//setting region 0, this is the boarder of the world map
	for (int i=0; i < WORLDSIZE; i++)
	{
		worldregion[0].addToRegion(globalmap[0][i]);
		worldregion[0].addToRegion(globalmap[i][0]);
		worldregion[0].addToRegion(globalmap[WORLDSIZE - 1][i]);
		worldregion[0].addToRegion(globalmap[i][WORLDSIZE-1]);
		worldregion[0].getCenter().setX(-1);
		worldregion[0].getCenter().setY(-1);
		worldregion[0].getCenter().setZ(-1);
	}
	    
    //setting region 0s properties. These will correspond to deep oceans surrounding the game map
	for (int i = 0; i < worldregion[0].getRegion().size(); i++ )
	{
    	worldregion[0].getRegion().get(i).setElevation(-100);
    }
    
	//setting region centers. Perhaps number of regions will be user defined? working with 1000, region 0 does not count;
	
	for (int i = 1; i <= regionnumber; i++)
	{
	    worldregion[i].setCenter(new Coordinate(random.nextInt(800)+100, random.nextInt(800)+100, 0));

	}
    //populating regions
	for (int i = 0; i < WORLDSIZE; i++){
		for(int j = 0; j < WORLDSIZE; j++){
			if (!globalmap[i][j].getIsInRegion()){
				double mindistance = WORLDSIZE*WORLDSIZE;
				for (int iter = 1; i<= regionnumber; iter++){
					double distance = globalmap[i][j].getPosition().displacement(worldregion[iter].getCenter());
					if (distance <= mindistance){
						mindistance = distance;
						regionflag = iter;
					}
				 
				}
			}
		  worldregion[regionflag].addToRegion(globalmap[i][j]);
		}
	}
    //setting random elevations for different regions
	for (int iter = 0; iter <= regionnumber; iter++){
		int elev = random.nextInt(200) - 100;
	    for (int i = 0; i < worldregion[iter].getRegion().size(); i++){
	    	worldregion[iter].getRegion().get(i).setElevation(elev);
	    }
     }
   //TODO Smoothing
  }
public GlobalMapItem getGlobalMapItem (int i, int j){
	return globalmap[i][j];
}

}