package lynbrook.sail.network;

import java.awt.Point;
import java.io.Serializable;

import lynbrook.sail.actor.Weapon;
import lynbrook.sail.data.Constants;


public class PlayerData implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int role;

    private int scenario;

    private Point loc;

    private Weapon weapon;


    public PlayerData()
    {
        role = Constants.ROLE_KING;
        loc = new Point();
        setWeapon( new Weapon( 0 ) );
    }


    public PlayerData( Point point, int role )
    {
        loc = point;
        this.role = role;
        setWeapon( new Weapon( 0 ) );
    }


    public boolean equals( PlayerData o )
    {
        if ( o == null )
        {
            return false;
        }
        return this == o || role == o.role && scenario == o.scenario
            && ( loc != null && loc.equals( o.loc ) || loc == null && o.loc == null )
            && ( weapon != null && weapon.equals( o.weapon )
                || weapon == null && o.weapon == null );
    }


    public void setPoint( Point point )
    {
        loc = point;
    }


    public void setRole( int role )
    {
        this.role = role;
    }


    public Point getPoit()
    {
        return loc;
    }


    public int getRole()
    {
        return role;
    }


    public Weapon getWeapon()
    {
        return weapon;
    }


    public void setWeapon( Weapon weapon )
    {
        this.weapon = weapon;
    }


    public int getScenario()
    {
        return scenario;
    }


    public void setScenario( int scenario )
    {
        this.scenario = scenario;
    }

}
