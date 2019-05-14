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

    private BufferedImage images;

    private BufferedImage mCastleMap;

    private int numItemPerRow;

    private MapImage[][] mIcons;

    // drawing
    private int rowOffset;

    private int colOffset;

    private int numRowsToDraw;

    private int numColsToDraw;


    public IslandMap( int itemSize )
    {
        this.itemSize = itemSize;
        numRowsToDraw = Constants.HEIGHT / itemSize + 2;
        numColsToDraw = Constants.WIDTH / itemSize + 2;
        speed = 4;
    }


    public void loadIcons( String s )
    {

        try
        {

            images = ImageIO.read( getClass().getResourceAsStream( s ) );
            numItemPerRow = images.getWidth() / itemSize;
            mIcons = new MapImage[2][numItemPerRow];

            BufferedImage subimage;
            for ( int col = 0; col < numItemPerRow; col++ )
            {
                subimage = images.getSubimage( col * itemSize, 0, itemSize, itemSize );
                mIcons[0][col] = new MapImage( subimage, MapImage.BLOCKED );
                subimage = images.getSubimage( col * itemSize, itemSize, itemSize, itemSize );
                mIcons[1][col] = new MapImage( subimage, MapImage.BLOCKED );
            }
            // meadow;
            mIcons[0][2].setType( MapImage.NORMAL );
            // Crown
            mIcons[0][3].setType( MapImage.NORMAL );
            // castle
            mIcons[1][2].setType( MapImage.NORMAL );

        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

    }


    public void loadCastleMap( String s )
    {
        try
        {
            mCastleMap = ImageIO.read( getClass().getResourceAsStream( s ) );

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


    public int getType( int row, int col )
    {
        int rc = mMap[row][col];
        int r = rc / numItemPerRow;
        int c = rc % numItemPerRow;
        return mIcons[r][c].getType();
    }


    public int getIndex( int row, int col )
    {
        return mMap[row][col];
    }


    public void setTile( int row, int col, int index )
    {
        mMap[row][col] = index;
    }


    public void replace( int x, int y )
    {
        for ( int row = 0; row < mMapRows; row++ )
        {
            for ( int col = 0; col < mMapCols; col++ )
            {
                if ( mMap[row][col] == x )
                    mMap[row][col] = y;
            }
        }
    }


    public void setPosition( int x, int y )
    {
        xDest = x;
        yDest = y;
    }


    public void checkBound()
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

        colOffset = -this.x / itemSize;
        rowOffset = -this.y / itemSize;

    }


    public void drawCastle( Graphics2D g )
    {
        g.drawImage( mCastleMap, 0, 0, null );
    }


    public void draw( Graphics2D g )
    {
        for ( int row = rowOffset; row < rowOffset + numRowsToDraw; row++ )
        {

            if ( row >= mMapRows )
                break;

            for ( int col = colOffset; col < colOffset + numColsToDraw; col++ )
            {

                if ( col >= mMapCols )
                    break;

                int rc = mMap[row][col];
                int r = rc / numItemPerRow;
                int c = rc % numItemPerRow;

                g.drawImage( mIcons[r][c].getImage(),
                    x + col * itemSize,
                    y + row * itemSize,
                    null );

            }

        }

    }

}
