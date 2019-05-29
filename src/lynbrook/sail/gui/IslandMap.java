package lynbrook.sail.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import lynbrook.sail.data.Constants;


public class IslandMap
{
    // position
    private int x;

    private int y;

    private int xDest;

    private int yDest;

    private int speed;

    // bounds
    private int xMin;

    private int yMin;

    private int xMax;

    private int yMax;

    // map
    private int[][] mMap;

    private int itemSize;

    private int mMapRows;

    private int mMapCols;

    private int mWidth;

    private int mHeight;

    private BufferedImage mIsland;

    private BufferedImage mCastle;


    public IslandMap( int itemSize )
    {
        this.itemSize = itemSize;
        speed = 4;
    }


    public void loadCastleMap( String island, String castle )
    {
        try
        {
            mIsland = ImageIO.read( getClass().getResourceAsStream( island ) );
            mCastle = ImageIO.read( getClass().getResourceAsStream( castle ) );
        }
        catch ( Exception e )
        {

        }
    }


    public void loadMap( String s )
    {

        try
        {
            InputStream in = getClass().getResourceAsStream( s );
            BufferedReader br = new BufferedReader( new InputStreamReader( in ) );

            mMapCols = Integer.parseInt( br.readLine() );
            mMapRows = Integer.parseInt( br.readLine() );
            mMap = new int[mMapRows][mMapCols];
            mWidth = mMapCols * itemSize;
            mHeight = mMapRows * itemSize;

            xMin = -mWidth;
            xMax = 0;
            yMin = -mHeight;
            yMax = 0;

            String delims = "\\s+";
            for ( int row = 0; row < mMapRows; row++ )
            {
                String line = br.readLine();
                String[] oneRowItems = line.split( delims );
                for ( int col = 0; col < mMapCols; col++ )
                {
                    mMap[row][col] = Integer.parseInt( oneRowItems[col] );
                }
            }

        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

    }


    public int getTileSize()
    {
        return itemSize;
    }


    public int getx()
    {
        return x;
    }


    public int gety()
    {
        return y;
    }


    public int[][] getMap()
    {
        return mMap;
    }


    public int getNumRows()
    {
        return mMapRows;
    }


    public int getNumCols()
    {
        return mMapCols;
    }


    public boolean isBlocked( int role, int x, int y )
    {
        int rc = mMap[y][x];
        if ( rc == Constants.MIXED || rc == Constants.CASTLE )
        {
            return false;
        }

        if ( role == Constants.ROLE_KING )
        {
            return rc != Constants.LAND;
        }
        else
        {
            return rc != Constants.WATER;
        }
    }


    public int getIndex( int row, int col )
    {
        return mMap[row][col];
    }


    private void checkBound()
    {
        if ( x < xMin )
        {
            x = xMin;
        }
        if ( y < yMin )
        {
            y = yMin;
        }
        if ( x > xMax )
        {
            x = xMax;
        }
        if ( y > yMax )
        {
            y = yMax;
        }
    }


    public void update()
    {
        if ( x < xDest )
        {
            x += speed;
            if ( x > xDest )
            {
                x = xDest;
            }
        }
        if ( x > xDest )
        {
            x -= speed;
            if ( x < xDest )
            {
                x = xDest;
            }
        }
        if ( y < yDest )
        {
            y += speed;
            if ( y > yDest )
            {
                y = yDest;
            }
        }
        if ( y > yDest )
        {
            y -= speed;
            if ( y < yDest )
            {
                y = yDest;
            }
        }

        checkBound();

    }


    public void draw( Graphics2D g )
    {
        g.drawImage( mIsland, 0, 0, null );
        g.drawImage( mCastle, 540, 254, null );
    }

}
