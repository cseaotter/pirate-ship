package lynbrook.sail.network;

import java.awt.Point;
import java.util.Map;

import lynbrook.sail.actor.Weapon;

/**
 * The Data update interface gets location, battle data, scenario, and updates
 * playerMap
 *
 * @author Lucy
 * @version May 30, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: none
 */

public interface DataUpdate
{
	/**
	 * method for getting the location
	 * 
	 * @return point the point
	 */
	Point getCurrentLocation();

	/**
	 * Gets battle data, to be implemented later
	 * 
	 * @return Weapon the weapon
	 */
	Weapon getBattleData();

	/**
	 * return the scenario
	 * 
	 * @return scenario the scenario
	 */
	int getScenario();

	/**
	 * method has parameter of hashmap of playerdata, and updates this hashmap
	 * 
	 * @param playerMap hashmap
	 */
	void onUpdateData(Map<Integer, PlayerData> playerMap);
}
