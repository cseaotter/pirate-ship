package lynbrook.sail.network;

import java.awt.Point;
import java.io.Serializable;

import lynbrook.sail.actor.Weapon;
import lynbrook.sail.data.Constants;

/**
 * Stores the data for the player
 * 
 * @author Lucy
 * @version May 31, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: none
 */
public class PlayerData implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int role;

	private int scenario;

	private Point loc;

	private Weapon weapon;

	/**
	 * Constructs the playerData by assigning default name and role and giving it
	 * new BattleData
	 */
	public PlayerData()
	{
		role = Constants.ROLE_KING;
		loc = new Point();
		setWeapon(new Weapon(0));
	}

	/**
	 * the other constructor is assigning name and role and coordinates based on
	 * parameters given
	 * 
	 * @param point the coordinates
	 * @param role  the role of the player
	 */
	public PlayerData(Point point, int role)

	{
		loc = point;
		this.role = role;
		setWeapon(new Weapon(0));
	}

	/**
	 * Tests if Playerdata equals to the other playerdata
	 * 
	 * @param o the other player data
	 * @return true or false based on equality
	 */
	public boolean equals(PlayerData o)
	{
		if (o == null)
		{
			return false;
		}
		return this == o || role == o.role && scenario == o.scenario
				&& (loc != null && loc.equals(o.loc) || loc == null && o.loc == null)
				&& (weapon != null && weapon.equals(o.weapon) || weapon == null && o.weapon == null);
	}

	/**
	 * Sets the point
	 * 
	 * @param point the point to set
	 */
	public void setPoint(Point point)
	{
		loc = point;

	}

	/**
	 * Sets the role
	 * 
	 * @param role the role to set
	 */
	public void setRole(int role)
	{
		this.role = role;
	}

	/**
	 * Returns the point
	 * 
	 * @return the loc
	 */
	public Point getPoint()
	{
		return loc;
	}

	/**
	 * Returns the role
	 * 
	 * @return the role of the player
	 */
	public int getRole()
	{
		return role;
	}

	/**
	 * gets the weapon
	 * 
	 * @return weapon the weapon
	 */
	public Weapon getWeapon()

	{
		return weapon;
	}

	/**
	 * Sets weapon
	 * 
	 * @param weapon the weapon
	 */
	public void setWeapon(Weapon weapon)

	{
		this.weapon = weapon;
	}

	/**
	 * Returns scenario
	 * 
	 * @return scenario the scenario
	 */
	public int getScenario()
	{
		return scenario;
	}

	/**
	 * Sets the scenario.
	 * 
	 * @param scenario the scenario
	 */
	public void setScenario(int scenario)
	{
		this.scenario = scenario;
	}

}
