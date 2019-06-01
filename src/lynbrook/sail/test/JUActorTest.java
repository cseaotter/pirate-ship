package lynbrook.sail.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Point;

import org.junit.Test;

import lynbrook.sail.actor.Actor;
import lynbrook.sail.actor.King;
import lynbrook.sail.actor.KingWeapon;
import lynbrook.sail.actor.Pirate;
import lynbrook.sail.actor.PirateWeapon;
import lynbrook.sail.actor.Weapon;
import lynbrook.sail.data.Constants;
import lynbrook.sail.gui.IslandMap;


/**
 * @author carol
 *
 */
public class JUActorTest
{
    // Test Actor class
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


    // Test King Class
    @Test
    public void kingTest()
    {
        IslandMap im = new IslandMap( 9 );
        Actor ac = new King( im );
        assertEquals( ac.getPosition(), new Point( 22, 15 ) );
    }


    // Test KingWeapon class
    @Test
    public void kingWeaponTest()
    {
        Weapon we = new KingWeapon();
        assertNotNull( we );
    }

    // Test Pirate is already covered by other classes


    // Test PirateWeapon class
    @Test
    public void pirateWeaponTest()
    {
        Weapon we = new PirateWeapon( 9 );
        assertNotNull( we );
    }


    @Test
    public void handleKeyEventsTest()
    {
        Weapon we = new PirateWeapon( 9 );
        Point loc = we.getLoc();
        assertNotNull( loc );
        we.setLoc( new Point( -1, -1 ) );
        we.handleKeyEvents( Constants.LEFT );
        assertEquals( we.getLoc().x, 0 );

        we.setLoc( new Point( 6, 6 ) );
        we.handleKeyEvents( Constants.LEFT );
        assertEquals( we.getLoc().x, 1 );

        we.handleKeyEvents( Constants.RIGHT );
        assertEquals( we.getLoc().x, 6 );

        we.setLoc( new Point( 6, 6 ) );
        we.handleKeyEvents( Constants.RIGHT );
        assertEquals( we.getLoc().x, 9 );
    }

    // Test Player class is already covered (don't need to test junit for GUI)


    // Test Weapon Class
    @Test
    public void weaponClassTest()
    {
        Weapon we = new Weapon( 9 );
        we.getBulletLoc();
        we.setHealth( 10 );
        assertEquals( we.getHealth(), 10 );
        we.setBulletActive( true );
        assertEquals( we.isBulletActive(), true );
        we.setRemoteHealth( 10 );
        assertEquals( we.getRemoteHealth(), 10 );
        we.setBulletExplosion( true );
        assertEquals( we.isBulletExplosion(), true );
        we.setExplosionFrame( 10 );
        assertEquals( we.getExplosionFrame(), 10 );

        we.setAngle( 45 );
        we.handleKeyEvents( Constants.UP );
        assertEquals( we.getAngle(), 40 );
        we.handleKeyEvents( Constants.DOWN );
        assertEquals( we.getAngle(), 45 );
    }
}
