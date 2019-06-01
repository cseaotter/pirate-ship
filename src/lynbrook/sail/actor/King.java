package lynbrook.sail.actor;

import lynbrook.sail.data.Constants;
import lynbrook.sail.gui.IslandMap;

/**
 * 
 * The king class, a player
 *
 * @author Elaine Ye
 * @version May 30, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: none
 */
public class King extends Player
{
	/**
	 * Constructs a king, calls super class
	 * 
	 * @param tm the island map
	 */
	public King(IslandMap tm)
	{
		super(tm, Constants.ROLE_KING);
		this.setTilePosition(22, 15);
	}
}
