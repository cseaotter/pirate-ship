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

    private int scenario;

    private BattleData battleData;


    public PlayerData()
    {

        name = "";
        role = Constants.ROLE_KING;
        setBattleData( new BattleData() );
    }


    public PlayerData( Point point, String name, int role )
    {
        x = point.x;
        y = point.y;
        this.name = name;
        this.role = role;
        setBattleData( new BattleData() );
    }


    public boolean equals( PlayerData o )
    {
        if ( o == null )
        {
            return false;
        }
        return this == o || x == o.x && y == o.y && role == o.role && scenario == o.scenario
            && battleData.equals( o.battleData );
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


    public BattleData getBattleData()
    {
        return battleData;
    }


    public void setBattleData( BattleData battleData )
    {
        this.battleData = battleData;
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
