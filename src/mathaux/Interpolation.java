package mathaux;

import generators.GlobalMapGenerator;

import java.util.ArrayList;
import java.util.Random;

import objects.common.Coordinate;
import objects.common.GlobalTile;

/**
 * Various methods of interpolation.
 * Different methods can be more appropriate for certain applications than others.
 * @author Byron
 *
 */
public class Interpolation {


    public Interpolation(){
	
	}
	
	/**
	 * 
	 * @param coordinates of data, data, point X to be interpolated, exponent p
	 * @return interpolated value at the point X
	 */
	public double inverseDistanceWeighting(Coordinate[] coorddata, Double[] resultdata, Coordinate point, double p, int worldsize){
		double sum1 = 0;
    	double sum2 = 0;
    	for (int j=0; j < coorddata.length; j++)
    	   {
    		if (point.displacement(coorddata[j]) < worldsize*0.1){
    		if (coorddata[j].displacement(point) != 0)
                 {
    			   sum2 = sum2 + Math.pow(1/(coorddata[j].displacement(point)),p);
    	         }
	       }
    	   }
		for (int i = 0; i < coorddata.length; i++)
		{
		    if (point.displacement(coorddata[i]) < worldsize*0.13)
		        {

		    	if (coorddata[i].displacement(point) != 0) {sum1 = sum1 + Math.pow(1/(coorddata[i].displacement(point)),p)*resultdata[i]/sum2;}
		    	else {sum1 = resultdata[i];}
		    } 
		}

		return sum1;
	}
	

	public int inverseDistanceWeighting(GlobalMapGenerator worldMap, GlobalTile point, double p)
	{
		Random random = new Random();
		int sample = 50;
    	int radius = 50;
    	int x = 0;
    	int y = 0;
    	Coordinate[] coorddata= new Coordinate[sample];
    	Double[] resultdata = new Double[sample];
    	for (int i = 0; i < sample; i++)
    	{
    	  x  = point.getPosition().getX() + random.nextInt(radius*2)-radius;
    	  y = point.getPosition().getY() + random.nextInt(radius*2)-radius;
    	  if (x < 0) {x = 2;}
    	  if (x >= worldMap.getWorldSize()) {x = worldMap.getWorldSize()- 2;}
    	  if (y < 0) {y = 2;};
    	  if (y >= worldMap.getWorldSize()) {y = worldMap.getWorldSize() - 2;}
          coorddata[i] = new Coordinate(x,y,0);
    	  resultdata[i] = (double) worldMap.getGlobalTile(x,y).getElevation();   	  
    	}
    	double sum1 = 0;
    	double sum2 = 0;
    	for (int j=0; j < coorddata.length; j++)
    	   {
    		if (coorddata[j].displacement(point.getPosition()) != 0)                 
    			   sum2 = sum2 + Math.pow(1/(coorddata[j].displacement(point.getPosition())),p);    	         
	       }
		for (int i = 0; i < coorddata.length; i++)
		{
		    
		    	if (coorddata[i].displacement(point.getPosition()) != 0) {sum1 = sum1 + Math.pow(1/(coorddata[i].displacement(point.getPosition())),p)*resultdata[i]/sum2;}
		    	else {sum1 = resultdata[i];}
		}


      return (int) sum1;
	}
    	  
 public int nearestNeighbour (GlobalMapGenerator worldMap, GlobalTile point){
	 int ret =0;
	 ArrayList<GlobalTile> neighbourhood = new ArrayList<GlobalTile>();
	 int worldSize = worldMap.getWorldSize();
	 int x = point.getPosition().getX();
	 int y = point.getPosition().getY();
	 if ((x - 1) >= 0) {neighbourhood.add(worldMap.getGlobalTile((x-1),y));}
	 if ((x + 1) < worldSize) {neighbourhood.add(worldMap.getGlobalTile((x+1),y));}
	 if ((y - 1) >= 0) {neighbourhood.add(worldMap.getGlobalTile(x, y-1));}
	 if ((y + 1) < worldSize) {neighbourhood.add(worldMap.getGlobalTile(x, y+1));}

	 if (((x - 1) >= 0) && ((y - 1) >= 0)) {neighbourhood.add(worldMap.getGlobalTile(x-1,y-1));}
	 if (((x - 1) >= 0) && ((y + 1) < worldSize)) {neighbourhood.add(worldMap.getGlobalTile(x-1,y+1));}
	 if (((x + 1) < worldSize) && ((y - 1) >= 0)) {neighbourhood.add(worldMap.getGlobalTile(x+1,y-1));}
	 if (((x + 1) < worldSize) && ((y + 1) < worldSize)) {neighbourhood.add(worldMap.getGlobalTile(x+1,y+1));}
     for (int i = 0; i < neighbourhood.size(); i++)
     {
    	 ret = ret + neighbourhood.get(i).getElevation();
     }
     ret = ret/neighbourhood.size();
     return ret;

 }
}
