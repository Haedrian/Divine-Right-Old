package objects.common.enums;
/***
 * Represents the type of the Map Item. For use with the MapItem Factory.
 * @author Llama
 *
 */
public enum MapItemType 
{
	NOTHING;
	///TODO: The rest

	public MapItemType getMapItemTypeFromInt(int value)
	{
		switch (value)
		{
			case 0:
				return NOTHING;
		
			default: return NOTHING;
		}
		
	}
}
