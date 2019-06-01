package lynbrook.sail.data;

import java.awt.Point;

/**
 * 
 * Stores the where the point is, and where it is going to be
 *
 * @author Carol
 * @version May 31, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: none
 */
public class PathOfMoving
{
	private Point point;

	private PathOfMoving next;

	/**
	 * Constucts the "path" point to next one
	 * 
	 * @param point the point
	 * @param next  the next one
	 */
	public PathOfMoving(Point point, PathOfMoving next)
	{
		this.point = point;
		this.next = next;
	}

	/**
	 * Returns the point
	 * 
	 * @return point the point
	 */
	public Point getPoint()
	{
		return point;
	}

	/**
	 * Returns the next one
	 * 
	 * @return next the next point
	 */
	public PathOfMoving getNext()
	{
		return next;
	}

}
