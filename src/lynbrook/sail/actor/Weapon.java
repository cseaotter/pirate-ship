package lynbrook.sail.actor;

import java.awt.Point;
import java.io.Serializable;

import lynbrook.sail.data.Constants;


/**
 * Weapon class stores all of the related information to the weapon
 *
 * @author yinin
 * @version May 31, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: none
 */
public class Weapon implements Serializable
{
    
    private static final long serialVersionUID = 1L;

    private Point loc;

    private Point bulletLoc;

    private int health;

    private int remoteHealth;

    private int angle;

    private boolean bulletActive;

    private boolean bulletExplosion;

    private int explosionFrame;


    /**
     * Constructs the weapon
     * 
     * @param health
     *            the health
     */
    public Weapon( int health )
    {
        setLoc( new Point() );
        setBulletLoc( new Point() );
        this.health = health;
        setAngle( 0 );
        remoteHealth = -1;
    }



    /**
     * Returns the location
     * 
     * @return loc the location
     */

    public Point getLoc()
    {
        return loc;
    }


    /**
     * Set location
     * 
     * @param loc
     *            the point of the location
     */
    public void setLoc( Point loc )
    {
        this.loc = loc;
    }


    /**
     * Get the BulletLocation
     * 
     * @return bulletLoc Point
     */
    public Point getBulletLoc()
    {
        return bulletLoc;
    }


    /**
     * Sets the bullet location.
     * 
     * @param bulletLoc
     *            the point to set
     */
    public void setBulletLoc( Point bulletLoc )
    {
        this.bulletLoc = bulletLoc;
    }


    /**
     * Returns health
     * 
     * @return health the health
     */
    public int getHealth()
    {
        return health;
    }


    /**
     * Sets health
     * 
     * @param health
     *            the health to set
     */
    public void setHealth( int health )
    {
        this.health = health;
    }


    /**
     * Returns the angle
     * 
     * @return angle the angle
     */
    public int getAngle()
    {
        return angle;
    }


    /**
     * Sets the angle
     * 
     * @param angle
     *            the angle to set
     */
    public void setAngle( int angle )
    {
        this.angle = angle;
    }


    /**
     * Returns if bullet is active
     * 
     * @return bulletActive if it is active it is true
     */
    public boolean isBulletActive()
    {
        return bulletActive;
    }


    /**
     * Sets the BulletActive variable
     * 
     * @param bulletActive
     *            the boolean to set
     */
    public void setBulletActive( boolean bulletActive )
    {
        this.bulletActive = bulletActive;
    }


    /**
     * Sets the angle based on the turn of the canon
     * 
     * @param action
     *            the turning of the canon
     */
    public void handleKeyEvents( int action )
    {
        if ( action == Constants.UP )
        {
            angle -= 5;
        }
        if ( action == Constants.DOWN )
        {
            angle += 5;
        }
        angle = ( angle + 360 ) % 360;
    }


    /**
     * Returns the remote health
     * 
     * @return remoteHealth the health of the weapon
     */
    public int getRemoteHealth()
    {
        return remoteHealth;
    }


    /**
     * Sets the remote health
     * 
     * @param remoteHealth
     *            the health to set
     */
    public void setRemoteHealth( int remoteHealth )
    {
        this.remoteHealth = remoteHealth;
    }


    /**
     * Returns if bulletExplodes
     * 
     * @return bulletExplosion boolean
     */
    public boolean isBulletExplosion()
    {
        return bulletExplosion;
    }


    /**
     * Sets the bulletExplosion
     * 
     * @param bulletExplosion
     *            true if there is
     */
    public void setBulletExplosion( boolean bulletExplosion )
    {
        this.bulletExplosion = bulletExplosion;
    }


    /**
     * Gets the explosionFrame
     * 
     * @return explosionFrame the explosion frame
     */
    public int getExplosionFrame()
    {
        return explosionFrame;
    }


    /**
     * Sets the explosion frame so that different pictures could be set
     * 
     * @param explosionFrame
     */
    public void setExplosionFrame( int explosionFrame )
    {
        this.explosionFrame = explosionFrame;
    }

}