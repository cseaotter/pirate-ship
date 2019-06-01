package lynbrook.sail.actor;

import lynbrook.sail.data.Constants;


/**
 * King Weapon, the weapon for the king player
 *
 * @author yinin
 * @version May 31, 2019
 * @author Period: TODO
 * @author Assignment: pirateShip
 *
 * @author Sources: TODO
 */
public class KingWeapon extends Weapon
{
    
    private static final long serialVersionUID = 1L;

    /**
     * Contructs a king weapon
     */

    public KingWeapon()
    {
        super( Constants.KING_HEALTH );
    }

}
