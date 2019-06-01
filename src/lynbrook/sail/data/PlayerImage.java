package lynbrook.sail.data;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


/**
 * The playerImage assigns the images to the variables
 *
 * @author yinin
 * @version May 31, 2019
 * @author Period: TODO
 * @author Assignment: pirateShip
 *
 * @author Sources: TODO
 */
public class PlayerImage
{
    private BufferedImage mBoat;

    private BufferedImage mKing;

    private int role;


    /**
     * Assigns the mBoat and mKing to images
     * 
     * @param role
     *            the role id
     */
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


    /**
     * Returns the correct image
     * 
     * @return the buffered image based on the role
     */
    public BufferedImage getCurrentImage()
    {

        return ( role == Constants.ROLE_KING ) ? mKing : mBoat;
    }

}