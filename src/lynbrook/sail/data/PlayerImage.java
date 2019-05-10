package lynbrook.sail.data;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class PlayerImage
{
    BufferedImage[][] mImages;


    public PlayerImage()
    {
        try
        {
            BufferedImage image = ImageIO
                .read( PlayerImage.class.getResourceAsStream( "/ocean_block.jpg" ) );
            int width = image.getWidth() / Constants.PLAYER_DIMENTION;
            int height = image.getHeight() / Constants.PLAYER_DIMENTION;
            mImages = new BufferedImage[height][width];
            for ( int i = 0; i < height; i++ )
            {
                for ( int j = 0; j < width; j++ )
                {
                    mImages[i][j] = image.getSubimage( j * Constants.PLAYER_DIMENTION,
                        i * Constants.PLAYER_DIMENTION,
                        Constants.PLAYER_DIMENTION,
                        Constants.PLAYER_DIMENTION );
                }
            }
        }
        catch ( Exception e )
        {
            System.exit( 0 );
        }
    }


    public BufferedImage[] getPlayerImage( int type )
    {
        return mImages[type];
    }

}
