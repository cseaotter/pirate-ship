package lynbrook.sail.actor;

import java.awt.Point;
import lynbrook.sail.data.Constants;


/**
 * The PirateWeapon class is for pirate player
 *
 * @author yinin
 * @version May 31, 2019
 * @author Period: TODO
 * @author Assignment: pirateShip
 *
 * @author Sources: TODO
 */
public class PirateWeapon extends Weapon
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int maxRight = 0;


    /**
     * Constructs a pirate weapon
     * 
     * @param maxRight
     *            length to the right
     */
    public PirateWeapon( int maxRight )
    {
        super( Constants.PIRATE_HEALTH );
        this.maxRight = maxRight;
    }


    /**
     * Handles key events for going left and right Updates the coordinates
     */
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
