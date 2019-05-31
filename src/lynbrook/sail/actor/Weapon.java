package lynbrook.sail.actor;

import java.awt.Point;
import lynbrook.sail.data.Constants;
import lynbrook.sail.network.BattleData;


public class Weapon
{
    private Point loc;

    private Point bulletLoc;

    private int health;

    private int remoteHealth;

    private int angle;

    private boolean bulletActive;

    private boolean bulletExplosion;

    private int explosionFrame;


    public Weapon( int health )
    {
        setLoc( new Point() );
        setBulletLoc( new Point() );
        this.health = health;
        setAngle( 0 );
        remoteHealth = -1;
    }


    public void update( BattleData data )
    {
        health = data.getHealth();
        angle = data.getAngle();
        loc = data.getLoc();
        bulletLoc = data.getBulletLoc();
        remoteHealth = data.getRemoteHealth();
        bulletActive = data.isBulletActive();
        bulletExplosion = data.isBulletExplosion();
        explosionFrame = data.getExplosionFrame();
    }


    public Point getLoc()
    {
        return loc;
    }


    public void setLoc( Point loc )
    {
        this.loc = loc;
    }


    public Point getBulletLoc()
    {
        return bulletLoc;
    }


    public void setBulletLoc( Point bulletLoc )
    {
        this.bulletLoc = bulletLoc;
    }


    public int getHealth()
    {
        return health;
    }


    public void setHealth( int health )
    {
        this.health = health;
    }


    public int getAngle()
    {
        return angle;
    }


    public void setAngle( int angle )
    {
        this.angle = angle;
    }


    public boolean isBulletActive()
    {
        return bulletActive;
    }


    public void setBulletActive( boolean bulletActive )
    {
        this.bulletActive = bulletActive;
    }


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


    public int getRemoteHealth()
    {
        return remoteHealth;
    }


    public void setRemoteHealth( int remoteHealth )
    {
        this.remoteHealth = remoteHealth;
    }


    public boolean isBulletExplosion()
    {
        return bulletExplosion;
    }


    public void setBulletExplosion( boolean bulletExplosion )
    {
        this.bulletExplosion = bulletExplosion;
    }


    public int getExplosionFrame()
    {
        return explosionFrame;
    }


    public void setExplosionFrame( int explosionFrame )
    {
        this.explosionFrame = explosionFrame;
    }

}
