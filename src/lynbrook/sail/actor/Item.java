package lynbrook.sail.actor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import lynbrook.sail.data.Constants;
import lynbrook.sail.gui.IslandMap;


public class Item extends Actor
{
    private BufferedImage image;


    public Item( IslandMap tm )
    {
        super( tm );

        width = Constants.ITEM_DIMENTION;
        height = Constants.ITEM_DIMENTION;

    }


    public void draw( Graphics2D g )
    {
        g.drawImage( image, x + xmap - width / 2, y + ymap - height / 2, null );
    }

}
