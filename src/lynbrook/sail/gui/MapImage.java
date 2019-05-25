package lynbrook.sail.gui;

import java.awt.image.BufferedImage;


public class MapImage
{

    private BufferedImage image;

    private int type;

    public static final int NORMAL = 0;

    public static final int BLOCKED = 1;


    public MapImage( BufferedImage image, int type )
    {
        this.image = image;
        this.type = type;
    }


    public BufferedImage getImage()
    {
        return image;
    }


    public int getType()
    {
        return type;
    }


    public void setType( int type )
    {
        this.type = type;
    }
}
