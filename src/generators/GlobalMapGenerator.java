package generators;

import objects.common.GlobalMapItem;

public class GlobalMapGenerator{ 

private GlobalMapItem[][] globalmap = new GlobalMapItem[1000][1000];

/**
 * Inialises world map with an elevation of 0 everywhere. The elevation of the edges is set to -100
 * @author Byron
 */
public GlobalMapGenerator (){
	for (GlobalMapItem[] mapItemRow : globalmap )
	{
		for (GlobalMapItem mapItem : mapItemRow)
		{
			mapItem = new GlobalMapItem();
			mapItem.setElevation(0);
			//TODO set climate parameters later
		}
		
	}

	for (int i=0; i < globalmap[0].length; i++)
	{
		globalmap[0][i].setElevation(-100);
		globalmap[globalmap[0].length-1][i].setElevation(-100);
	}
	
	for (int i=0; i < globalmap[0].length; i++)
	{
		globalmap[i][0].setElevation(-100);
		globalmap[i][globalmap[i].length-1].setElevation(-100);
	}
	
}

}