package objects.common;

import objects.common.managers.LocalMapManager;

import java.util.HashMap;
import java.util.List;

/**
 * Represents a Coordinate for MapItem, Actor - NOT Tile;
 * @author Llama
 *
 */
public class Coordinate 
{
	private int x;
	private int y;
	private int z;

    public Coordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

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
	 * Returns the DISPLACEMENT in x,y and z between this coordinate and the other coordinate
	 * @param c2 The coordinate to compare
	 * @return
	 */
	public double displacement(Coordinate c2)
	{
		int deltaX = Math.abs(this.getX() - c2.getX());
		int deltaY = Math.abs(this.getY() - c2.getY());
		int deltaZ = Math.abs(this.getX() - c2.getX());
		
		return Math.sqrt((deltaX*deltaX) + (deltaY*deltaY) + (deltaZ*deltaZ));
	}

    public int hashCode() {
        return hashCode(x,y,z);
    }
    
    protected int hashCode(int x, int y, int z) {
        return x + 1000*y + 100000*z;
    }
}
