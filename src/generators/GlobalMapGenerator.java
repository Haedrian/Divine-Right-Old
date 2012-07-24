package generators;

import mathaux.Interpolation;
import objects.common.Coordinate;
import objects.common.GlobalMapItem;
import objects.common.GlobalTile;
import objects.common.Region;


import java.util.ArrayList;
import java.util.Random;

public class GlobalMapGenerator{ 

protected GlobalTile[][] globalmap; 
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
	globalmap =  new GlobalTile[worldSize][worldSize];
	for (int i=0; i < worldregion.length; i++)
	{
		worldregion[i] = new Region();
	}
	
	
	for (int i=0; i < globalmap.length; i++)
	{
		for (int j = 0; j < globalmap[0].length; j++)
		{
			globalmap[i][j] = new GlobalTile();			
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
				worldregion[i].addToRegion(globalmap[x][y]);
				System.out.println ("Region "+i+" has center at "+x+","+y);
				}
			else {i = i -1;}
		}
	    //populating regions
		for (int i = 0; i < worldSize; i++){
			for(int j = 0; j < worldSize; j++){
				
				//System.out.println("Doing tile : " + i + "," + j);
				
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
	    	int x = worldregion[iter].getCenter().getX();
	    	int y = worldregion[iter].getCenter().getY();
	    	int elev;
	    	if (Math.sqrt(Math.pow((worldSize/2 - x),2) + Math.pow(worldSize/2 - y, 2)) > worldSize*0.4 - (random.nextGaussian()*worldSize*0.1 + worldSize*0.1)){
	    		elev = random.nextInt(90)-100;
	    	} else {elev = random.nextInt(180) - 30;}
			//System.out.println("Region " + iter + " has size "+ worldregion[iter].getRegion().size() );
	    	if (Math.abs(elev) < 10) {elev = elev*10;}
		    for (int i = 0; i < worldregion[iter].getRegion().size(); i++){
		    	worldregion[iter].getRegion().get(i).setElevation((int) Math.round(elev));
		    }
	     }


  }
public GlobalTile getGlobalTile (int i, int j){
	return globalmap[i][j];
}

public int getWorldSize (){
	return this.worldSize;
}

public Region[] getWorldRegion(){
	return this.worldregion;
}

//smoothing out the map using inverse distance weighting with exponent p.
  public void smoothMap(double p){
	Interpolation smoother = new Interpolation();
    ArrayList<Coordinate> coorddata = new ArrayList<Coordinate>();
    ArrayList<Double> resultdata = new ArrayList<Double>();
	System.out.println(worldregion.length-1);
	for (int iter = 1; iter < worldregion.length; iter++){
		coorddata.add(iter - 1,worldregion[iter].getCenter());
		resultdata.add(iter - 1, (double) worldregion[iter].getRegion().get(0).getElevation());
	}
	
	//smoothing out the edges:
    for (int i = 0; i < worldregion[0].getRegion().size(); i++){
    	double cutoff = random.nextDouble();
    	if (cutoff < 0.01)
    	{
    	coorddata.add(worldregion[0].getRegion().get(i).getPosition());
    	resultdata.add((double) worldregion[0].getRegion().get(i).getElevation());
    	}
    	
    }
    //adding left coast and right coast to smoothing
    for (int i= worldSize/30; i < worldSize/10; i++)
	  {
		for (int j = worldSize/30; j < worldSize - worldSize/30; j++)
		{
			double cutoff = random.nextDouble();
			if (cutoff < 0.001 ){
				coorddata.add(globalmap[i][j].getPosition());
				resultdata.add((double) globalmap[i][j].getElevation());
			}
			cutoff = random.nextDouble();
			if (cutoff < 0.001 ){
				coorddata.add(globalmap[worldSize - i][j].getPosition());
				resultdata.add((double) globalmap[worldSize - i][j].getElevation());
			}
		}
	
     
      }
    //adding top and bottom coast to smoothing
    for (int i= worldSize/30; i < worldSize - worldSize/30; i++)
	  {
		for (int j = worldSize/30; j < worldSize/10; j++)
		{
			double cutoff = random.nextDouble();
			if (cutoff < 0.01 ){
				coorddata.add(globalmap[i][j].getPosition());
				resultdata.add((double) globalmap[i][j].getElevation());
			}
			cutoff = random.nextDouble();
			if (cutoff < 0.01 ){
				coorddata.add(globalmap[i][worldSize - j].getPosition());
				resultdata.add((double) globalmap[i][worldSize - j].getElevation());
			}
		}
	  }
    Coordinate[] coordarray = coorddata.toArray(new Coordinate[coorddata.size()]);
    Double[] resultarray = resultdata.toArray(new Double[resultdata.size()]);
    for (int i= worldSize/30; i < globalmap.length - worldSize/30; i++)
	  {
		for (int j = worldSize/30; j < globalmap[0].length - worldSize/30; j++)
		{
			System.out.println("Smoothing tile "+i+","+j);
			globalmap[i][j].setElevation((int)Math.round(smoother.inverseDistanceWeighting(coordarray, resultarray, globalmap[i][j].getPosition(), p, worldSize)));
		}
	  }
    
  }
  public void smoothen(double p){
	  Interpolation smoother = new Interpolation();
      
	  for (int i = 0; i < worldSize; i++)
	  {
		  for (int j = 0; j < worldSize; j++)
		  {
			  System.out.println("Smoothing tile "+i+","+j);
			  globalmap[i][j].setElevation(smoother.inverseDistanceWeighting(this, this.getGlobalTile(i, j), p));
		  }
	  }
  }
  public void erosion(){
	  Interpolation smoother = new Interpolation();
	  for (int i = 0; i < worldSize; i++)
	  {
		  for (int j = 0; j < worldSize; j++)
		  {
			  System.out.println("Eroding tile "+i+","+j);
			  globalmap[i][j].setElevation(smoother.nearestNeighbour(this,this.getGlobalTile(i, j)));
		  }
	  }
  }
}