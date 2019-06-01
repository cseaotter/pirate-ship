
package lynbrook.sail.actor;

import java.awt.Graphics2D;
import java.awt.Point;
import lynbrook.sail.data.PlayerImage;
import lynbrook.sail.gui.IslandMap;

/**
 * The actor class stores its information to be used on the screen.
 *
 * @author Elaine
 * @version May 31, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: https://github.com/zequnyu/Diamond-Hunter
 */
public abstract class Actor
{
	// dimensions
	protected int width;

	protected int height;

	// position
	protected int x;

	protected int y;

	protected int xdest;

	protected int ydest;

	protected int rowTile;

	protected int colTile;

	// attributes
	protected int moveSpeed;

	protected IslandMap mMap;

	protected int itemSize;

	protected int role;

	protected PlayerImage playerImage;

	/**
	 * Constructs the actor, and creates a map to get information for map
	 * 
	 * @param map  the map of the island
	 * @param role whether it is king or pirate depends on the number (0 or 1)
	 */
	public Actor(IslandMap map, int role)

	{
		mMap = map;
		itemSize = mMap.getTileSize();
		this.role = role;
		playerImage = new PlayerImage(role);
	}

	/**
	 * Sets the position according to the coordinates given
	 * 
	 * @param i1 the x coordinate
	 * @param i2 the y coordinate
	 */
	public void setPosition(int i1, int i2)
	{
		x = i1;
		y = i2;
		xdest = x;
		ydest = y;
	}

	/**
	 * Sets the position of the tile the actor is
	 * 
	 * @param i1 first coordinate
	 * @param i2 second coordinate
	 */
	public void setTilePosition(int i1, int i2)
	{
		x = i1 * itemSize + itemSize / 2;
		y = i2 * itemSize + itemSize / 2;
		xdest = x;
		ydest = y;
	}

	/**
	 * Gets point where the actor is
	 * 
	 * @return point where item is
	 */

	public Point getPosition()
	{
		return new Point(x / itemSize, y / itemSize);
	}

	/**
	 * Sets the point where the actor is going to be
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return new Point the point which actor is going to be
	 */
	public Point toPosition(int x, int y)
	{
		return new Point(x / itemSize, y / itemSize);
	}

	/**
	 * Draws actor on the screen
	 * 
	 * @param g the graphics parameter
	 */

	public void draw(Graphics2D g)
	{
		g.drawImage(playerImage.getCurrentImage(), x - width / 2, y - height / 2, null);
	}

}
