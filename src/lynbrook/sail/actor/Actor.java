package lynbrook.sail.actor;

import java.awt.Graphics2D;
import java.awt.Point;
import lynbrook.sail.data.PlayerImage;
import lynbrook.sail.gui.IslandMap;


public abstract class Actor
{
    // dimensions
    protected int width;

    protected int height;

    // position
    protected int x;

    protected int y;

    protected int xdest;

    protected int ydest;

    protected int rowTile;

    protected int colTile;


    // attributes
    protected int moveSpeed;

    protected IslandMap mMap;

    protected int itemSize;

    protected int role;

    protected PlayerImage playerImage;


    public Actor( IslandMap map, int role )
    {
        mMap = map;
        itemSize = mMap.getTileSize();
        this.role = role;
        playerImage = new PlayerImage( role );
    }


    public void setPosition( int i1, int i2 )
    {
        x = i1;
        y = i2;
        xdest = x;
        ydest = y;
    }


    public void setTilePosition( int i1, int i2 )
    {
        x = i1 * itemSize + itemSize / 2;
        y = i2 * itemSize + itemSize / 2;
        xdest = x;
        ydest = y;
    }


    public Point getPosition()
    {
        return new Point( x / itemSize, y / itemSize );
    }


    public Point toPosition( int x, int y )
    {
        return new Point( x / itemSize, y / itemSize );
    }

    public void draw( Graphics2D g )
    {
        g.drawImage( playerImage.getCurrentImage(), x - width / 2, y - height / 2, null );
    }

}
