package lynbrook.sail.actor;

import lynbrook.sail.data.Constants;
import lynbrook.sail.gui.IslandMap;


/**
 * 
 * A pirate is one of the players, and uses Player methods
 *
 * @author Carol Gao
 * @version May 30, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: none
 */
public class Pirate extends Player
{
    /**
     * Constructs a pirate
     * 
     * @param tm
     *            the island map
     */
    public Pirate( IslandMap tm )
    {
        super( tm, Constants.ROLE_PIRATE );
        this.setTilePosition( 3, 3 );
    }

}
