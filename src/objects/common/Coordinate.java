package objects.common;

/**
 * Represents a Coordinate
 * @author Llama
 *
 */
public class Coordinate 
{
	private int x;
	private int y;
	private int z;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	
	/***
	 * Returns the DISPLACEMENT in x and y between this coordinate and the other coordinate
	 * @param c2 The coordinate to compare
	 * @return
	 */
	public double displacement(Coordinate c2)
	{
		int deltaX = Math.abs(this.getX() - c2.getX());
		int deltaY = Math.abs(this.getY() - c2.getY());
		
		return Math.sqrt((deltaX*deltaX) + (deltaY*deltaY));
		
	}
}
