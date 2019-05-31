package lynbrook.sail.actor;

import java.awt.Point;
import lynbrook.sail.data.Constants;


public class PirateWeapon extends Weapon
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int maxRight = 0;


    public PirateWeapon( int maxRight )
    {
        super( Constants.PIRATE_HEALTH );
        this.maxRight = maxRight;
    }


    @Override
    public void handleKeyEvents( int action )
    {
        super.handleKeyEvents( action );
        Point point = getLoc();
        if ( action == Constants.LEFT )
        {
            point.x -= 5;
            if ( point.x < 0 )
            {
                point.x = 0;
            }
        }
        if ( action == Constants.RIGHT )
        {
            point.x += 5;
            if ( point.x >= maxRight )
            {
                point.x = maxRight;
            }
        }
    }
}
