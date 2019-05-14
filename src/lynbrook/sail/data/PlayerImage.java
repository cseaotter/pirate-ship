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
            int rows = image.getHeight() / Constants.PLAYER_DIMENTION;
            int cols = image.getWidth() / Constants.PLAYER_DIMENTION;
            mImages = new BufferedImage[rows][cols];
            for ( int i = 0; i < rows; i++ )
            {
                for ( int j = 0; j < cols; j++ )
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
