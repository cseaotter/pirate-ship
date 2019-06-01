package lynbrook.sail.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import lynbrook.sail.data.Constants;


/**
 * 
 * TODO Write a one-sentence summary of your class here. TODO Follow it with
 * additional details about its purpose, what abstraction it represents, and how
 * to use it.
 *
 * @author yinin
 * @version May 31, 2019
 * @author Period: TODO
 * @author Assignment: pirateShip
 *
 * @author Sources: TODO
 */
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


    /**
     * Constructs an island map
     * 
     * @param itemSize
     *            the size of the item
     */
    public IslandMap( int itemSize )
    {
        this.itemSize = itemSize;
        speed = 4;
    }


    /**
     * Loads the castlemap
     * 
     * @param island
     *            the image island
     * @param castle
     *            the image castle
     */
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


    /**
     * Loads the map of the island
     * 
     * @param s
     *            the image string
     */
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


    /**
     * Returns the tilesize
     * 
     * @return itemSize the tilesize
     */
    public int getTileSize()
    {
        return itemSize;
    }


    /**
     * Return the x coordinate
     * 
     * @return x the x coordinate
     */
    public int getx()
    {
        return x;
    }


    /**
     * Return the y coordinate
     * 
     * @return y the y coordinate
     */
    public int gety()
    {
        return y;
    }


    /**
     * Return the array of the mMapp
     * 
     * @return mMap the map array
     */
    public int[][] getMap()
    {
        return mMap;
    }


    /**
     * The number of rows
     * 
     * @return mMapRows number of rows
     */
    public int getNumRows()
    {
        return mMapRows;
    }


    /**
     * The number of columns
     * 
     * @return mMapCols number of cols
     */
    public int getNumCols()
    {
        return mMapCols;
    }


    /**
     * Return if the land is blocked at the coordinate for the role
     * 
     * @param role
     *            the role of the player
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @return true or false like above
     */
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


    /**
     * Returns the index at the row/col
     * 
     * @param row
     *            the row
     * @param col
     *            the col
     * @return the index there
     */
    public int getIndex( int row, int col )
    {
        return mMap[row][col];
    }


    /**
     * checks that the coordinate is within the bounds
     */
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


    /**
     * Updates the coordinates based on the variables of the destination
     */
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


    /**
     * Draws the Island, and the castle
     * 
     * @param g
     *            graphics2D
     */
    public void draw( Graphics2D g )
    {
        g.drawImage( mIsland, 0, 0, null );
        g.drawImage( mCastle, 540, 254, null );
    }

}