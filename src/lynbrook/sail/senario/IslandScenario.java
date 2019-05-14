package lynbrook.sail.senario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import lynbrook.sail.actor.Item;
import lynbrook.sail.actor.Player;
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
    }

    private Player mPlayer;

    private IslandMap mMap;

    private ArrayList<Item> mItems;

    private LinkedList<Point> mPath;


    public void init()
    {

        mItems = new ArrayList<Item>();
        mMap = new IslandMap( Constants.ITEM_DIMENTION );
        mMap.loadIcons( Constants.RESOURCE_ISLAND_SCENARIO_MAP_ICONS );
        mMap.loadMap( Constants.RESOURCE_ISLAND_SCENARIO_MAP );
        mMap.loadCastleMap( Constants.RESOURCE_ISLAND_SCENARIO_CASTLE_MAP );

        mPath = new LinkedList<>();
        mPlayer = new Player( mMap );

        mPlayer.setTilePosition( Constants.PLAYER_INITIAL_POSITION,
            Constants.PLAYER_INITIAL_POSITION );
    }


    public void update()
    {

        handleKeyEvents();

        handleMouseEvent();
        mMap.update();

        mPlayer.update();
    }


    public void draw( Graphics2D g )
    {
        mMap.draw( g );
        mPlayer.draw( g );
        for ( Item i : mItems )
        {
            i.draw( g );
        }

        g.setColor( Color.BLACK );

    }


    public void handleKeyEvents()
    {
        if ( mController.isDown( KeyEvent.VK_LEFT ) )
        {
            mPlayer.setLeft();
        }
        if ( mController.isDown( KeyEvent.VK_RIGHT ) )
        {
            mPlayer.setRight();
        }
        if ( mController.isDown( KeyEvent.VK_UP ) )
        {
            mPlayer.setUp();
        }
        if ( mController.isDown( KeyEvent.VK_DOWN ) )
        {
            mPlayer.setDown();
        }

    }


    private void handleMouseEvent()
    {
        if ( !mPath.isEmpty() )
        {
            Point point = mPath.poll();
            mPlayer.setTilePosition( point.y, point.x );
        }
    }


    @Override
    public void handleMouseClicked( MouseEvent e )
    {
        if ( !mPath.isEmpty() )
        {
            return;
        }
        Point to = mPlayer.toPosition( e.getX(), e.getY() );

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
        PathOfMoving result = null;
        PathOfMoving from = new PathOfMoving( fromPoint, null );
        Point target = mPlayer.getPosition();
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
