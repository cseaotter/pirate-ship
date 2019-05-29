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
    
    public static final String ROLE_KING_STRING = "king";

    public static final String ROLE_PIRATE_STRING = "pirate";

    private static final long serialVersionUID = 1L;

    private int x;

    private int y;

    private String name;

    private int role;

    private String roleString;
    
    private String message;

    private int health;
    
    
    
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String msg)
    {
        message = msg;
    }
    
    public PlayerData()
    {

        name = "";
        role = ROLE_KING;
    }


    public PlayerData( Point point, String name, int role, String roleString )
    {
        x = point.x;
        y = point.y;
        this.name = name;
        this.role = role;
        this.roleString = roleString;
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
    public int getHealth()
    {
        return health;
    }
    public void setHealth( int health )
    {
        this.health = health;
    }
    public String getRoleString()
    {
        return roleString;
    }
    public void setRoleString( String roleString )
    {
        this.roleString = roleString;
    }

}
