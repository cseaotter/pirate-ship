package lynbrook.sail.actor;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import lynbrook.sail.gui.MapImage;
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

    // movement
    protected boolean moving;

    protected boolean left;

    protected boolean right;

    protected boolean up;

    protected boolean down;

    // attributes
    protected int moveSpeed;

    protected IslandMap map;

    protected int itemSize;

    protected int xmap;

    protected int ymap;

    // animation
    protected Animation animation;

    protected int currentAnimation;


    public Actor( IslandMap tm )
    {
        map = tm;
        itemSize = map.getTileSize();
        animation = new Animation();
    }


    public int getx()
    {
        return x;
    }


    public int gety()
    {
        return y;
    }


    public int getRow()
    {
        return rowTile;
    }


    public int getCol()
    {
        return colTile;
    }


    public void setPosition( int i1, int i2 )
    {
        x = i1;
        y = i2;
        xdest = x;
        ydest = y;
    }


    public void setMapPosition()
    {
        xmap = map.getx();
        ymap = map.gety();
    }


    public void setTilePosition( int i1, int i2 )
    {
        y = i1 * itemSize + itemSize / 2;
        x = i2 * itemSize + itemSize / 2;
        xdest = x;
        ydest = y;
    }


    public void setLeft()
    {
        if ( moving )
            return;
        left = true;
        moving = validateNextPosition();
    }


    public void setRight()
    {
        if ( moving )
            return;
        right = true;
        moving = validateNextPosition();
    }


    public void setUp()
    {
        if ( moving )
            return;
        up = true;
        moving = validateNextPosition();
    }


    public void setDown()
    {
        if ( moving )
            return;
        down = true;
        moving = validateNextPosition();
    }


    public boolean validateNextPosition()
    {

        if ( moving )
            return true;

        rowTile = y / itemSize;
        colTile = x / itemSize;

        if ( left )
        {
            if ( colTile == 0 || map.getType( rowTile, colTile - 1 ) == MapImage.BLOCKED )
            {
                return false;
            }
            else
            {
                xdest = x - itemSize;
            }
        }
        if ( right )
        {
            if ( colTile == map.getNumCols()
                || map.getType( rowTile, colTile + 1 ) == MapImage.BLOCKED )
            {
                return false;
            }
            else
            {
                xdest = x + itemSize;
            }
        }
        if ( up )
        {
            if ( rowTile == 0 || map.getType( rowTile - 1, colTile ) == MapImage.BLOCKED )
            {
                return false;
            }
            else
            {
                ydest = y - itemSize;
            }
        }
        if ( down )
        {
            if ( rowTile == map.getNumRows() - 1
                || map.getType( rowTile + 1, colTile ) == MapImage.BLOCKED )
            {
                return false;
            }
            else
            {
                ydest = y + itemSize;
            }
        }

        return true;

    }


    public void getNextPosition()
    {

        if ( left && x > xdest )
            x -= moveSpeed;
        else
            left = false;
        if ( left && x < xdest )
            x = xdest;

        if ( right && x < xdest )
            x += moveSpeed;
        else
            right = false;
        if ( right && x > xdest )
            x = xdest;

        if ( up && y > ydest )
            y -= moveSpeed;
        else
            up = false;
        if ( up && y < ydest )
            y = ydest;

        if ( down && y < ydest )
            y += moveSpeed;
        else
            down = false;
        if ( down && y > ydest )
            y = ydest;

    }


    public void update()
    {
        if ( moving )
            getNextPosition();

        if ( x == xdest && y == ydest )
        {
            left = right = up = down = moving = false;
            rowTile = y / itemSize;
            colTile = x / itemSize;
        }

        animation.update();

    }


    public void draw( Graphics2D g )
    {
        setMapPosition();
        g.drawImage( animation.getImage(), x + xmap - width / 2, y + ymap - height / 2, null );
    }
}
