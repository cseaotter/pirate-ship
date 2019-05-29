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

    // movement
    protected boolean isMoving;

    protected boolean left;

    protected boolean right;

    protected boolean up;

    protected boolean down;

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


    public void setLeft()
    {
        if ( isMoving )
        {
            return;
        }
        left = true;
        isMoving = checkNextPosition();
    }


    public void setRight()
    {
        if ( isMoving )
        {
            return;
        }
        right = true;
        isMoving = checkNextPosition();
    }


    public void setUp()
    {
        if ( isMoving )
        {
            return;
        }
        up = true;
        isMoving = checkNextPosition();
    }


    public void setDown()
    {
        if ( isMoving )
        {
            return;
        }
        down = true;
        isMoving = checkNextPosition();
    }


    public Point getPosition()
    {
        return new Point( x / itemSize, y / itemSize );
    }


    public Point toPosition( int x, int y )
    {
        return new Point( x / itemSize, y / itemSize );
    }


    private boolean checkNextPosition()
    {

        if ( isMoving )
        {
            return true;
        }

        rowTile = y / itemSize;
        colTile = x / itemSize;

        if ( left )
        {
            if ( colTile == 0 || mMap.isBlocked( role, colTile - 1, rowTile ) )
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
            if ( colTile == mMap.getNumCols() || mMap.isBlocked( role, colTile + 1, rowTile ) )
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
            if ( rowTile == 0 || mMap.isBlocked( role, colTile, rowTile - 1 ) )
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
            if ( rowTile == mMap.getNumRows() - 1 || mMap.isBlocked( role, colTile, rowTile + 1 ) )
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


    private void getNextPosition()
    {

        if ( left && x > xdest )
        {
            x -= moveSpeed;
        }
        else
        {
            left = false;
        }
        if ( left && x < xdest )
        {
            x = xdest;
        }

        if ( right && x < xdest )
        {
            x += moveSpeed;
        }
        else
        {
            right = false;
        }
        if ( right && x > xdest )
        {
            x = xdest;
        }

        if ( up && y > ydest )
        {
            y -= moveSpeed;
        }
        else
        {
            up = false;
        }
        if ( up && y < ydest )
        {
            y = ydest;
        }

        if ( down && y < ydest )
        {
            y += moveSpeed;
        }
        else
        {
            down = false;
        }
        if ( down && y > ydest )
        {
            y = ydest;
        }

    }


    public void update()
    {
        if ( isMoving )
        {
            getNextPosition();
        }

        if ( x == xdest && y == ydest )
        {
            left = right = up = down = isMoving = false;
            rowTile = y / itemSize;
            colTile = x / itemSize;
        }

    }


    public void draw( Graphics2D g )
    {
        g.drawImage( playerImage.getCurrentImage(), x - width / 2, y - height / 2, null );
    }


    public boolean getMoving()
    {
        return isMoving;
    }


    public boolean getLeft()
    {
        return left;
    }


    public boolean getRight()
    {
        return right;
    }


    public boolean getUp()
    {
        return up;
    }


    public boolean getDown()
    {
        return down;
    }
}
