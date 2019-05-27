package lynbrook.sail.backend;

import java.awt.Point;
import java.io.Serializable;


public class PlayerData implements Serializable
{
    /**
     * 
     */

    public static final int ROLE_KING = 0;

    public static final int ROLE_PIRATE = 1;

    private static final long serialVersionUID = 1L;

    private int x;

    private int y;

    private String name;

    private int role;


    public PlayerData()
    {

        name = "";
        role = ROLE_KING;
    }


    public PlayerData( Point point, String name, int role )
    {
        x = point.x;
        y = point.y;
        this.name = name;
        this.role = role;
    }


    public void setPoint( Point point )
    {
        x = point.x;
        y = point.y;
    }


    public void setName( String name )
    {
        this.name = name;
    }


    public void setRole( int role )
    {
        this.role = role;
    }


    public Point getPoit()
    {
        return new Point( x, y );
    }


    public String getName()
    {
        return name;
    }


    public int getRole()
    {
        return role;
    }

}
