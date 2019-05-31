package lynbrook.sail.test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import lynbrook.sail.actor.Actor;
import lynbrook.sail.actor.Pirate;
import lynbrook.sail.controller.GameController;
import lynbrook.sail.data.Constants;
import lynbrook.sail.gui.IslandMap;


public class JUActorTest
{
    // Test Actor class by Carol Gao
    @Test
    public void setPositionTest()
    {
        IslandMap im = new IslandMap( 9 );
        Actor ac = new Pirate( im );
        ac.setPosition( 3, 3 );
        assertEquals( ac.getPosition(), new Point( 0, 0 ) );
    }


    @Test
    public void setTilePositionTest()
    {
        IslandMap im = new IslandMap( 9 );
        Actor ac = new Pirate( im );
        ac.setTilePosition( 3, 3 );
        int size = im.getTileSize();
        assertEquals( ac.getPosition(),
            new Point( ( 3 * size + size / 2 ) / 9, ( 3 * size + size / 2 ) / 9 ) );
    }

    @Test
    public void toPositionTest()
    {
        IslandMap im = new IslandMap( 9 );
        Actor ac = new Pirate( im );
        assertEquals( ac.toPosition( 7, 9 ), new Point( 7 / 9, 9 / 9 ) );
    }


    @Test
    public void validateNextPositionTest()
    {
        IslandMap im = new IslandMap( 9 );
        im = new IslandMap( Constants.ITEM_DIMENTION );
        im.loadMap( Constants.RESOURCE_ISLAND_SCENARIO_MAP );
        im.loadCastleMap( Constants.RESOURCE_ISLAND_SCENARIO_CASTLE_MAP,
            Constants.RESOURCE_ISLAND_SCENARIO_CASTLE );
        Actor ac = new Pirate( im );
        ac.setTilePosition( 16, 1 );
        GameController gc = new GameController();
        // assertEquals(ac.getLeft(), new Point( 7 / 9, 9 / 9 ));
    }
}
