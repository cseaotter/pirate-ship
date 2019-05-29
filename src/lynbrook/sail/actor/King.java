package lynbrook.sail.actor;

import lynbrook.sail.data.Constants;
import lynbrook.sail.gui.IslandMap;

public class King extends Player
{
    public King( IslandMap tm )
    {
        super( tm , Constants.ROLE_KING);
        this.setTilePosition( 22, 15);
    }
}
