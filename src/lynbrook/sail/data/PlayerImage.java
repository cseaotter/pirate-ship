package lynbrook.sail.data;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class PlayerImage
{
    private BufferedImage mBoat;

    private BufferedImage mKing;

    private int role;


    public PlayerImage( int role )
    {
        this.role = role;
        try
        {
            mBoat = ImageIO.read( getClass().getResourceAsStream( Constants.RESOURCE_BOAT ) );
            mKing = ImageIO.read( getClass().getResourceAsStream( Constants.RESOURCE_KING ) );
        }
        catch ( Exception e )
        {
            System.exit( 0 );
        }
    }


    public BufferedImage getCurrentImage()
    {

        return ( role == Constants.ROLE_KING ) ? mKing : mBoat;
    }

}
