package lynbrook.sail.data;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class PlayerImage
{
    BufferedImage[] mImages;

    BufferedImage mBoat;

    BufferedImage mKing;

    BufferedImage mPirate;

    int type;

    private int role;


    public PlayerImage( int role )
    {
        this.role = role;
        try
        {
            BufferedImage image = ImageIO
                .read( PlayerImage.class.getResourceAsStream( "/ocean_block.jpg" ) );
            int rows = image.getHeight() / Constants.PLAYER_DIMENTION;
            mImages = new BufferedImage[rows];
            for ( int i = 0; i < rows; i++ )
            {
                mImages[i] = image.getSubimage( 0,
                    i * Constants.PLAYER_DIMENTION,
                    Constants.PLAYER_DIMENTION,
                    Constants.PLAYER_DIMENTION );
            }
            mBoat = ImageIO.read( getClass().getResourceAsStream( Constants.RESOURCE_BOAT ) );
            mKing = ImageIO.read( getClass().getResourceAsStream( Constants.RESOURCE_KING ) );
            mPirate = ImageIO.read( getClass().getResourceAsStream( Constants.RESOURCE_PIRATE ) );

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


    public void setPlayerImage( int type )
    {
        this.type = type;
    }

}
