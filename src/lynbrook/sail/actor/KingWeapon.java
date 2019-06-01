package lynbrook.sail.actor;

import lynbrook.sail.data.Constants;

/**
 * King Weapon, the weapon for the king player
 *
 * @author Elaine
 * @version May 31, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: none
 */
public class KingWeapon extends Weapon
{

	private static final long serialVersionUID = 1L;

	/**
	 * Contructs a king weapon
	 */

	public KingWeapon()
	{
		super(Constants.KING_HEALTH);
	}

}
