package lynbrook.sail.senario;

import java.awt.Graphics2D;
import java.util.ArrayList;

import lynbrook.sail.actor.Item;
import lynbrook.sail.actor.Player;
import lynbrook.sail.controller.GameController;
import lynbrook.sail.controller.Keys;
import lynbrook.sail.data.Constants;
import lynbrook.sail.gui.IslandMap;


public class IslandScenario extends Scenario
{

    public IslandScenario( GameController controller )
    {
        super( controller );
    }

    private Player player;

    private IslandMap tileMap;

    // items
    private ArrayList<Item> items;

    private int xsector;

    private int ysector;

    private int sectorSize;


    public void init()
    {

        items = new ArrayList<Item>();

        // load map
        tileMap = new IslandMap( Constants.ITEM_DIMENTION );
        tileMap.loadTiles( "/map_icons.jpg" );
        tileMap.loadMap( "/island_map.txt" );

        // create player
        player = new Player( tileMap );

        // initialize player
        player.setTilePosition( Constants.PLAYER_INITIAL_POSITION,
            Constants.PLAYER_INITIAL_POSITION );

        // set up camera position
        sectorSize = Constants.WIDTH;
        xsector = player.getx() / sectorSize;
        ysector = player.gety() / sectorSize;
        tileMap.setPositionImmediately( -xsector * sectorSize, -ysector * sectorSize );
    }


    public void update()
    {

        // check keys
        handleInput();

        xsector = player.getx() / sectorSize;
        ysector = player.gety() / sectorSize;
        tileMap.setPosition( -xsector * sectorSize, -ysector * sectorSize );
        tileMap.update();

        if ( tileMap.isMoving() )
            return;

        player.update();
    }


    public void draw( Graphics2D g )
    {
        tileMap.draw( g );
        player.draw( g );
        for ( Item i : items )
        {
            i.draw( g );
        }

        g.setColor( java.awt.Color.BLACK );

    }


    public void handleInput()
    {
        if ( Keys.isPressed( Keys.ESCAPE ) )
        {

        }

        if ( Keys.isDown( Keys.LEFT ) )
            player.setLeft();
        if ( Keys.isDown( Keys.RIGHT ) )
            player.setRight();
        if ( Keys.isDown( Keys.UP ) )
            player.setUp();
        if ( Keys.isDown( Keys.DOWN ) )
            player.setDown();
        if ( Keys.isPressed( Keys.SPACE ) )
            player.setAction();
    }

}
