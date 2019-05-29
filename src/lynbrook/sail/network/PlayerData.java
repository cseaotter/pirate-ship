package lynbrook.sail.network;

import java.awt.Point;
import java.io.Serializable;

import lynbrook.sail.data.Constants;


public class PlayerData implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int x;

    private int y;

    private String name;

    private int role;


    public PlayerData()
    {

        name = "";
        role = Constants.ROLE_KING;
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
