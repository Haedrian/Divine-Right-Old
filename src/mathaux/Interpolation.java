package mathaux;

import objects.common.Coordinate;

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
	public double inverseDistanceWeighting(Coordinate[] coorddata, double[] resultdata, Coordinate point, int p, int worldsize){
		
		double sum1 = 0;
		for (int i = 0; i < coorddata.length; i++)
		{
		    if (coorddata[i].displacement(point) < worldsize*worldsize)
		    {
			double sum2 = 0;
			for (int j=0; j < coorddata.length; j++)
			{			
		    if (coorddata[j].displacement(point) != 0)
		        {
		    	sum2 = sum2 + Math.pow(1/(coorddata[j].displacement(point)),p);
		    	}
			}
			if (coorddata[i].displacement(point) != 0) 
				{sum1 = sum1 + Math.pow(1/(coorddata[i].displacement(point)),p)*resultdata[i]/sum2;}
			else {sum1 = resultdata[i];}
		    } else {sum1 = resultdata[i];}
		} 				
		return sum1;
	}
}
