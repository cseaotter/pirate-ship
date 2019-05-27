package lynbrook.sail.senario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import lynbrook.sail.actor.Item;
import lynbrook.sail.actor.Player;
import lynbrook.sail.backend.PlayerData;
import lynbrook.sail.controller.GameController;
import lynbrook.sail.data.Constants;
import lynbrook.sail.data.PathOfMoving;
import lynbrook.sail.gui.IslandMap;
import lynbrook.sail.gui.MapImage;


public class IslandScenario extends Scenario
{

    public IslandScenario( GameController controller )
    {
        super( controller );
        init();
    }

    private Map<Integer, Player> mPlayers;

    private IslandMap mMap;

    private ArrayList<Item> mItems;

    private LinkedList<Point> mPath;


    private void init()
    {

        mItems = new ArrayList<Item>();
        mMap = new IslandMap( Constants.ITEM_DIMENTION );
        mMap.loadIcons( Constants.RESOURCE_ISLAND_SCENARIO_MAP_ICONS );
        mMap.loadMap( Constants.RESOURCE_ISLAND_SCENARIO_MAP );
        mMap.loadCastleMap( Constants.RESOURCE_ISLAND_SCENARIO_CASTLE_MAP );

        mPath = new LinkedList<>();
        mPlayers = new TreeMap<>();

        Player currentPlayer = new Player( mMap );
        currentPlayer.setTilePosition( Constants.PLAYER_INITIAL_POSITION,
            Constants.PLAYER_INITIAL_POSITION );
        mPlayers.put( mController.getCurrentRole(), currentPlayer );
    }


    public void update()
    {

        handleKeyEvents();

        handleMouseEvent();
        mMap.update();

        getCurrentPlayer().update();

        if ( mController.getPlayerDataMap().size() == 2 )
        {
            PlayerData data = mController.getPlayerDataMap().get( remoteRole() );
            Point point = data.getPoit();
            if ( !mPlayers.containsKey( remoteRole() ) )
            {
                mPlayers.put( remoteRole(), new Player( mMap ) );
            }
            Player remotePlayer = mPlayers.get( remoteRole() );
            remotePlayer.setTilePosition( point.x, point.y );
        }
    }


    public void draw( Graphics2D g )
    {
        mMap.draw( g );
        getCurrentPlayer().draw( g );

        if ( mPlayers.containsKey( remoteRole() ) )
        {
            getRemotePlayer().draw( g );
        }
        for ( Item i : mItems )
        {
            i.draw( g );
        }

        g.setColor( Color.BLACK );

    }


    @Override
    public Player getCurrentPlayer()
    {
        return mPlayers.get( mController.getCurrentRole() );
    }


    private int remoteRole()
    {
        return ( mController.getCurrentRole() + 1 ) % 2;
    }


    private Player getRemotePlayer()
    {
        return mPlayers.get( remoteRole() );
    }


    public void handleKeyEvents()
    {
        Player player = getCurrentPlayer();
        if ( mController.isDown( KeyEvent.VK_LEFT ) )
        {
            player.setLeft();
        }
        if ( mController.isDown( KeyEvent.VK_RIGHT ) )
        {
            player.setRight();
        }
        if ( mController.isDown( KeyEvent.VK_UP ) )
        {
            player.setUp();
        }
        if ( mController.isDown( KeyEvent.VK_DOWN ) )
        {
            player.setDown();
        }

    }


    private void handleMouseEvent()
    {
        if ( !mPath.isEmpty() )
        {
            Point point = mPath.poll();
            getCurrentPlayer().setTilePosition( point.x, point.y );
        }
    }


    @Override
    public void handleMouseClicked( MouseEvent e )
    {
        if ( !mPath.isEmpty() )
        {
            return;
        }
        Point to = getCurrentPlayer().toPosition( e.getX(), e.getY() );

        PathOfMoving result = findPath( to );
        if ( result != null )
        {
            PathOfMoving head = result;
            while ( head != null )
            {
                mPath.add( head.getPoint() );
                head = head.getNext();
            }

        }
        else
        {
            mPath.clear();
        }

    }


    private PathOfMoving findPath( Point fromPoint )
    {
        if ( mMap.getType( fromPoint.y, fromPoint.x ) == MapImage.BLOCKED )
        {
            return null;
        }
        PathOfMoving result = null;
        PathOfMoving from = new PathOfMoving( fromPoint, null );
        Point target = getCurrentPlayer().getPosition();
        LinkedList<PathOfMoving> q = new LinkedList<>();
        q.add( from );
        int[][] moves = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        int[][] map = mMap.getMap();
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[from.getPoint().y][from.getPoint().x] = true;

        while ( !q.isEmpty() )
        {
            PathOfMoving p = q.poll();
            if ( p.getPoint().equals( target ) )
            {
                result = p;
                break;
            }
            for ( int[] m : moves )
            {
                Point next = new Point( p.getPoint() );

                next.translate( m[0], m[1] );
                if ( inside( map, next ) && !visited[next.y][next.x]
                    && mMap.getType( next.y, next.x ) != MapImage.BLOCKED )
                {
                    q.add( new PathOfMoving( next, p ) );
                    visited[next.y][next.x] = true;
                }
            }

        }

        return result;
    }


    private boolean inside( int[][] map, Point p )
    {
        return p.x >= 0 && p.x < map[0].length && p.y >= 0 && p.y < map.length;
    }

}
