package lynbrook.sail.network;

import java.awt.Point;
import java.io.Serializable;
import lynbrook.sail.actor.Weapon;


public class BattleData implements Serializable
{

    private static final long serialVersionUID = 1L;

    private int health;

    private int angle;

    private int remoteHealth;

    private Point loc;

    private Point bulletLoc;

    private boolean bulletActive;

    private boolean bulletExplosion;

    private int explosionFrame;


    public BattleData()
    {
        remoteHealth = -1;
    }


    public boolean equals( BattleData o )
    {
        if ( o == null )
        {
            return false;
        }
        return this == o || health == o.health && angle == o.angle && remoteHealth == o.remoteHealth
            && ( loc != null && loc.equals( o.loc ) || loc == null && o.loc == null )
            && ( bulletLoc != null && bulletLoc.equals( o.bulletLoc )
                || bulletLoc == null && o.bulletLoc == null )
            && bulletActive == o.bulletActive && bulletExplosion == o.bulletExplosion
            && explosionFrame == o.explosionFrame;

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


    public void fromWeapon( Weapon weapon )
    {
        if ( weapon == null )
        {
            return;
        }
        health = weapon.getHealth();
        angle = weapon.getAngle();
        bulletLoc = weapon.getBulletLoc();
        loc = weapon.getLoc();
        remoteHealth = weapon.getRemoteHealth();
        bulletActive = weapon.isBulletActive();
        bulletExplosion = weapon.isBulletExplosion();
        explosionFrame = weapon.getExplosionFrame();
    }


    public int getRemoteHealth()
    {
        return remoteHealth;
    }


    public void setRemoteHealth( int remoteHealth )
    {
        this.remoteHealth = remoteHealth;
    }


    public boolean isBulletActive()
    {
        return bulletActive;
    }


    public void setBulletActive( boolean bulletActive )
    {
        this.bulletActive = bulletActive;
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
