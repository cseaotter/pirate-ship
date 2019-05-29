package lynbrook.sail.senario;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import lynbrook.sail.controller.GameController;
import lynbrook.sail.data.Constants;


public class ResultPage extends Scenario
{

    private BufferedImage won;

    private BufferedImage lost;


    public ResultPage( GameController controller )
    {
        super( controller );
        init();
    }


    private void init()
    {
        try
        {
            // put all the images as constants
            won = ImageIO.read( getClass().getResourceAsStream( Constants.RESOURCE_RESULT_WON ) );

            lost = ImageIO.read( getClass().getResourceAsStream( Constants.RESOURCE_RESULT_LOST ) );

        }
        catch ( Exception e )
        {

        }
    }


    @Override
    public void update()
    {

    }


    @Override
    public void draw( Graphics2D g )
    {
        BufferedImage image = mController.getResult() ? won : lost;
        int x = Constants.WIDTH / 2 - image.getWidth() / 2;
        int y = Constants.HEIGHT / 2 - image.getHeight() / 2;
        g.drawImage( image, x, y, null );
    }


    @Override
    public void handleKeyEvents()
    {

    }


    @Override
    public void handleMouseClicked( MouseEvent e )
    {

    }

}
