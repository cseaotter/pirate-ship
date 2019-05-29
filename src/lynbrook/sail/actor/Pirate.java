package lynbrook.sail.actor;

import lynbrook.sail.data.Constants;
import lynbrook.sail.gui.IslandMap;


public class Pirate extends Player
{

    public Pirate( IslandMap tm )
    {
        super( tm, Constants.ROLE_PIRATE );
        this.setTilePosition( 3, 3);
    }

}
