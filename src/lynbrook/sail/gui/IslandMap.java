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

    private int xdest;

    private int ydest;

    private int speed;

    private boolean moving;

    // bounds
    private int xmin;

    private int ymin;

    private int xmax;

    private int ymax;

    // map
    private int[][] map;

    private int itemSize;

    private int numRows;

    private int numCols;

    private int width;

    private int height;

    private BufferedImage images;

    private int numItemPerRow;

    private MapImage[][] tiles;

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


    public void loadTiles( String s )
    {

        try
        {

            images = ImageIO.read( getClass().getResourceAsStream( s ) );
            numItemPerRow = images.getWidth() / itemSize;
            tiles = new MapImage[2][numItemPerRow];

            BufferedImage subimage;
            for ( int col = 0; col < numItemPerRow; col++ )
            {
                subimage = images.getSubimage( col * itemSize, 0, itemSize, itemSize );
                tiles[0][col] = new MapImage( subimage, MapImage.BLOCKED );
                subimage = images.getSubimage( col * itemSize, itemSize, itemSize, itemSize );
                tiles[1][col] = new MapImage( subimage, MapImage.BLOCKED );
            }
            // meadow;
            tiles[0][2].setType( MapImage.NORMAL );
            // Crown
            tiles[0][3].setType( MapImage.NORMAL );
            // castle
            tiles[1][2].setType( MapImage.NORMAL );

        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

    }


    public void loadMap( String s )
    {

        try
        {

            InputStream in = getClass().getResourceAsStream( s );
            BufferedReader br = new BufferedReader( new InputStreamReader( in ) );

            numCols = Integer.parseInt( br.readLine() );
            numRows = Integer.parseInt( br.readLine() );
            map = new int[numRows][numCols];
            width = numCols * itemSize;
            height = numRows * itemSize;

            xmin = Constants.WIDTH - width;
            xmin = -width;
            xmax = 0;
            ymin = Constants.HEIGHT - height;
            ymin = -height;
            ymax = 0;

            String delims = "\\s+";
            for ( int row = 0; row < numRows; row++ )
            {
                String line = br.readLine();
                String[] tokens = line.split( delims );
                for ( int col = 0; col < numCols; col++ )
                {
                    map[row][col] = Integer.parseInt( tokens[col] );
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


    public int getWidth()
    {
        return width;
    }


    public int getHeight()
    {
        return height;
    }


    public int getNumRows()
    {
        return numRows;
    }


    public int getNumCols()
    {
        return numCols;
    }


    public int getType( int row, int col )
    {
        int rc = map[row][col];
        int r = rc / numItemPerRow;
        int c = rc % numItemPerRow;
        return tiles[r][c].getType();
    }


    public int getIndex( int row, int col )
    {
        return map[row][col];
    }


    public boolean isMoving()
    {
        return moving;
    }


    public void setTile( int row, int col, int index )
    {
        map[row][col] = index;
    }


    public void replace( int i1, int i2 )
    {
        for ( int row = 0; row < numRows; row++ )
        {
            for ( int col = 0; col < numCols; col++ )
            {
                if ( map[row][col] == i1 )
                    map[row][col] = i2;
            }
        }
    }


    public void setPosition( int x, int y )
    {
        xdest = x;
        ydest = y;
    }


    public void setPositionImmediately( int x, int y )
    {
        this.x = x;
        this.y = y;
    }


    public void fixBounds()
    {
        if ( x < xmin )
            x = xmin;
        if ( y < ymin )
            y = ymin;
        if ( x > xmax )
            x = xmax;
        if ( y > ymax )
            y = ymax;
    }


    public void update()
    {
        if ( x < xdest )
        {
            x += speed;
            if ( x > xdest )
            {
                x = xdest;
            }
        }
        if ( x > xdest )
        {
            x -= speed;
            if ( x < xdest )
            {
                x = xdest;
            }
        }
        if ( y < ydest )
        {
            y += speed;
            if ( y > ydest )
            {
                y = ydest;
            }
        }
        if ( y > ydest )
        {
            y -= speed;
            if ( y < ydest )
            {
                y = ydest;
            }
        }

        fixBounds();

        colOffset = -this.x / itemSize;
        rowOffset = -this.y / itemSize;

        if ( x != xdest || y != ydest )
            moving = true;
        else
            moving = false;

    }


    public void draw( Graphics2D g )
    {

        for ( int row = rowOffset; row < rowOffset + numRowsToDraw; row++ )
        {

            if ( row >= numRows )
                break;

            for ( int col = colOffset; col < colOffset + numColsToDraw; col++ )
            {

                if ( col >= numCols )
                    break;

                int rc = map[row][col];
                int r = rc / numItemPerRow;
                int c = rc % numItemPerRow;

                g.drawImage( tiles[r][c].getImage(), x + col * itemSize, y + row * itemSize, null );

            }

        }

    }

}
