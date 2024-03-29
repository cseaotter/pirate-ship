package lynbrook.sail.scenario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import lynbrook.sail.actor.King;
import lynbrook.sail.actor.Pirate;
import lynbrook.sail.actor.Player;
import lynbrook.sail.controller.GameController;
import lynbrook.sail.data.Constants;
import lynbrook.sail.data.PathOfMoving;
import lynbrook.sail.gui.IslandMap;
import lynbrook.sail.network.PlayerData;

/**
 * 
 * The scenario of the island screen
 *
 * @author Carol
 * @version May 31, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: https://github.com/zequnyu/Diamond-Hunter
 */
public class IslandScenario extends Scenario
{
	private Map<Integer, Player> mPlayers;

	private IslandMap mMap;

	private LinkedList<Point> mPath;

	/**
	 * Island Scenario constructor
	 * 
	 * @param controller the controller
	 */
	public IslandScenario(GameController controller)
	{
		super(controller);
		init();
	}

	/**
	 * Initializes the map and castle and variables about the players
	 */
	private void init()
	{

		int role = mController.getCurrentRole();
		mMap = new IslandMap(Constants.ITEM_DIMENTION);
		mMap.loadMap(Constants.RESOURCE_ISLAND_SCENARIO_MAP);
		mMap.loadCastleMap(Constants.RESOURCE_ISLAND_SCENARIO_CASTLE_MAP, Constants.RESOURCE_ISLAND_SCENARIO_CASTLE);

		mPath = new LinkedList<>();
		mPlayers = new TreeMap<>();

		Player currentPlayer;
		if (role == Constants.ROLE_KING)
		{
			currentPlayer = new King(mMap);
		} else
		{
			currentPlayer = new Pirate(mMap);
		}

		mPlayers.put(role, currentPlayer);
	}

	/**
	 * Updates the scenario switching and putting players into the map
	 */
	public void update()
	{

		handleKeyEvents();

		handleMouseEvent();

		if (mController.getPlayerDataMap().size() == 2)
		{
			PlayerData data = mController.getPlayerDataMap().get(remoteRole());
			Point point = data.getPoint();
			if (!mPlayers.containsKey(remoteRole()))
			{
				mPlayers.put(remoteRole(), new Player(mMap, remoteRole()));
			}
			Player remotePlayer = mPlayers.get(remoteRole());
			remotePlayer.setTilePosition(point.x, point.y);
		}

		Point p = getCurrentPlayer().getPosition();
		int role = mController.getCurrentRole();
		if (role == Constants.ROLE_PIRATE && mMap.getIndex(p.y, p.x) == Constants.CASTLE)
		{
			mController.switchScenario(Constants.SCENARIO_BATTLE_FIELD);
		}
		Player remotePlayer = getRemotePlayer();
		if (remotePlayer != null)
		{
			Point p1 = remotePlayer.getPosition();
			PlayerData data = mController.getPlayerDataMap().get(remoteRole());
			if (remoteRole() == Constants.ROLE_PIRATE && mMap.getIndex(p1.y, p1.x) == Constants.CASTLE
					|| data.getScenario() == Constants.SCENARIO_BATTLE_FIELD)
			{
				mController.switchScenario(Constants.SCENARIO_BATTLE_FIELD);
			}

		}

	}

	/**
	 * Draws the current player and the remote player
	 * 
	 * @param g graphics
	 */
	public void draw(Graphics2D g)
	{
		mMap.draw(g);
		getCurrentPlayer().draw(g);

		if (mPlayers.containsKey(remoteRole()))
		{
			getRemotePlayer().draw(g);
		}

		g.setColor(Color.BLACK);

	}

	/**
	 * returns player
	 * 
	 * @return remote role
	 */
	@Override
	public Player getCurrentPlayer()
	{
		return mPlayers.get(mController.getCurrentRole());
	}

	/**
	 * returns the remote role
	 * 
	 * @return role the remote role
	 */
	private int remoteRole()
	{
		return (mController.getCurrentRole() + 1) % 2;
	}

	/**
	 * Return remote player
	 * 
	 * @return the remote player
	 */
	private Player getRemotePlayer()
	{
		return mPlayers.get(remoteRole());
	}

	/**
	 * handles key events for left, right, up, down
	 */
	public void handleKeyEvents()
	{

	}

	/**
	 * handles mouse events
	 */
	private void handleMouseEvent()
	{
		if (!mPath.isEmpty())
		{
			Point point = mPath.poll();
			getCurrentPlayer().setTilePosition(point.x, point.y);
		}
	}

	/**
	 * handles mouse clicked
	 * 
	 * @param e mouse event
	 */
	@Override
	public void handleMouseClicked(MouseEvent e)
	{
		if (!mPath.isEmpty())
		{
			return;
		}
		Point to = getCurrentPlayer().toPosition(e.getX(), e.getY());
		PathOfMoving result = findPath(to);
		if (result != null)
		{
			PathOfMoving head = result;
			while (head != null)
			{
				mPath.add(head.getPoint());
				head = head.getNext();
			}

		} else
		{
			mPath.clear();
		}

	}

	/**
	 * Finds the path of point from the point given
	 * 
	 * @param fromPoint the point given
	 * @return result path of moving
	 */
	private PathOfMoving findPath(Point fromPoint)
	{
		if (isBlocked(fromPoint))
		{
			return null;
		}
		PathOfMoving result = null;
		PathOfMoving from = new PathOfMoving(fromPoint, null);
		Point target = getCurrentPlayer().getPosition();
		LinkedList<PathOfMoving> q = new LinkedList<>();
		q.add(from);
		int[][] moves =
		{
				{ -1, 0 },
				{ 1, 0 },
				{ 0, -1 },
				{ 0, 1 } };
		int[][] map = mMap.getMap();
		boolean[][] visited = new boolean[map.length][map[0].length];
		visited[from.getPoint().y][from.getPoint().x] = true;

		while (!q.isEmpty())
		{
			PathOfMoving p = q.poll();
			if (p.getPoint().equals(target))
			{
				result = p;
				break;
			}
			for (int[] m : moves)
			{
				Point next = new Point(p.getPoint());

				next.translate(m[0], m[1]);
				if (inside(map, next) && !visited[next.y][next.x] && !isBlocked(next))
				{
					q.add(new PathOfMoving(next, p));
					visited[next.y][next.x] = true;
				}
			}

		}

		return result;
	}

	/**
	 * Returns true or false based on if map is blocked at that point
	 * 
	 * @param point the point
	 * @return true or false like above
	 */
	private boolean isBlocked(Point point)
	{
		return mMap.isBlocked(mController.getCurrentRole(), point.x, point.y);
	}

	/**
	 * True or false given the point, see if it is in the map
	 * 
	 * @param map the array map
	 * @param p   the point
	 * @return true or false like above
	 */
	private boolean inside(int[][] map, Point p)
	{
		return p.x >= 0 && p.x < map[0].length && p.y >= 0 && p.y < map.length;
	}

}
