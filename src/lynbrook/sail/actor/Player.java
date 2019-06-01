package lynbrook.sail.actor;

import java.awt.Graphics2D;
import lynbrook.sail.data.Constants;
import lynbrook.sail.gui.IslandMap;

/**
 * Player is an Actor Sets role and draws actor
 *
 * @author Elaine
 * @version May 31, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: https://github.com/zequnyu/Diamond-Hunter
 */
public class Player extends Actor
{
	/**
	 * Constructs player
	 * 
	 * @param map  the Island Map
	 * @param role the role
	 */
	public Player(IslandMap map, int role)
	{
		super(map, role);

		width = Constants.PLAYER_DIMENTION;
		height = Constants.PLAYER_DIMENTION;
		moveSpeed = Constants.PLAYER_MOVE_SPEED;

	}

	/**
	 * Draws the graphics2D
	 * 
	 * @param g Graphics2D
	 */
	public void draw(Graphics2D g)
	{
		super.draw(g);
	}
}
