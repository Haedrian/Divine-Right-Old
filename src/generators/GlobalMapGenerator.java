package generators;

import mathaux.Interpolation;
import objects.common.Coordinate;
import objects.common.GlobalMapItem;
import objects.common.Region;


import java.util.Random;

public class GlobalMapGenerator{ 

protected GlobalMapItem[][] globalmap; 
protected Random random = new Random();
protected Region[] worldregion;
protected int regionflag;
protected int worldSize;


/**
 * Initializes world map The elevation of the edges is set to -100, regions are initialized and populated
 * @author Byron
 */
public GlobalMapGenerator (int regionnumber, int worldSize){
	worldregion = new Region[regionnumber +1];
	this.worldSize = worldSize;
	globalmap =  new GlobalMapItem[worldSize][worldSize];
	for (int i=0; i < worldregion.length; i++)
	{
		worldregion[i] = new Region();
	}
	
	
	for (int i=0; i < globalmap.length; i++)
	{
		for (int j = 0; j < globalmap[0].length; j++)
		{
			globalmap[i][j] = new GlobalMapItem();			
			globalmap[i][j].setPosition(new Coordinate(i,j,0));
			globalmap[i][j].setElevation(40);
		}
	}

	//setting region 0, this is the boarder of the world map
	for (int i=0; i < worldSize; i++)
		for (int j = 0; j < worldSize/30; j++){
	{
		System.out.println("Adding tile "+j+","+i+" to region 0");
		worldregion[0].addToRegion(globalmap[j][i]);
		System.out.println("Adding tile "+i+","+j+" to region 0");
		worldregion[0].addToRegion(globalmap[i][j]);
		System.out.println("Adding tile "+ Integer.toString(worldSize - 1 - j)+","+i+" to region 0");
		worldregion[0].addToRegion(globalmap[worldSize - 1 - j][i]);
		System.out.println("Adding tile "+i+","+ Integer.toString(worldSize	 - 1 -j)+" to region 0");
		worldregion[0].addToRegion(globalmap[i][worldSize-1 - j]);
		worldregion[0].setCenter(new Coordinate(-1,-1,-1));
	}
		}
	    
    //setting region 0s properties. These will correspond to deep oceans surrounding the game map
	for (int i = 0; i < worldregion[0].getRegion().size(); i++ )
	{
		System.out.println("Tile " + worldregion[0].getRegion().get(i).getPosition().getX() + ","+worldregion[0].getRegion().get(i).getPosition().getY() +"Is in region 0");
    	worldregion[0].getRegion().get(i).setElevation(-100);
    }
    
	//setting region centers. Perhaps number of regions will be user defined? working with 1000, region 0 does not count;
	
		for (int i = 1; i <= regionnumber; i++)
		{
			int x = random.nextInt((worldSize-1));
			int y = random.nextInt((worldSize-1));
			if (!globalmap[x][y].getIsInRegion()) {
				worldregion[i].setCenter(new Coordinate(x,y,0));
				System.out.println ("Region "+i+" has center at "+x+","+y);
				}
			else {i = i -1;}
		}
	    //populating regions
		for (int i = 0; i < worldSize; i++){
			for(int j = 0; j < worldSize; j++){
				
				System.out.println("Doing tile : " + i + "," + j);
				
				if (!globalmap[i][j].getIsInRegion()){
					double mindistance = worldSize*worldSize;
					for (int iter = 1; iter<= regionnumber; iter++){
						double distance = globalmap[i][j].getPosition().displacement(worldregion[iter].getCenter());
						if (distance <= mindistance){
							mindistance = distance;
							regionflag = iter;
						}
					 
					}
					worldregion[regionflag].addToRegion(globalmap[i][j]);
				}
             }
		}
		//setting random elevations for different regions
		for (int iter = 1; iter <= regionnumber; iter++){
			int elev = random.nextInt(150) - 50;
			System.out.println("Region " + iter + " has elevation "+ elev );
		    for (int i = 0; i < worldregion[iter].getRegion().size(); i++){
		    	worldregion[iter].getRegion().get(i).setElevation(elev);
		    }
	     }


  }
public GlobalMapItem getGlobalMapItem (int i, int j){
	return globalmap[i][j];
}

//smoothing out the map using inverse distance weighting with exponent p.
  public void smoothMap(int p){
	Interpolation smoother = new Interpolation();
	Coordinate centers[];
	double elevations[];
	centers = new Coordinate[worldregion.length-1];
	elevations = new double[worldregion.length-1];
	System.out.println(worldregion.length-1);
	for (int iter = 1; iter < worldregion.length; iter++){
		centers[iter - 1] = worldregion[iter].getCenter();
		elevations[iter - 1] = worldregion[iter].getRegion().get(0).getElevation();
	}
	for (int i=0; i < globalmap.length; i++)
	  {
		for (int j = 0; j < globalmap[0].length; j++)
		{
			System.out.println("Smoothing tile "+i+","+j);
			globalmap[i][j].setElevation((int)Math.round(smoother.inverseDistanceWeighting(centers, elevations, globalmap[i][j].getPosition(), p, worldSize)));
		}
	  }
     
  }


}