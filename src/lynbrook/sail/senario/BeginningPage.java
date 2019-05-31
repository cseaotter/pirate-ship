package lynbrook.sail.senario;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import lynbrook.sail.controller.GameController;
import lynbrook.sail.data.Constants;


public class BeginningPage extends Scenario
{
    private BufferedImage mFrontPage;

    private BufferedImage mInstructionPage;


    public BeginningPage( GameController controller )
    {
        super( controller );
        init();
    }


    private void init()
    {
        try
        {
            mFrontPage = ImageIO
                .read( getClass().getResourceAsStream( Constants.RESOURCE_FRONT_PAGE_SCENARIO ) );

            mInstructionPage = ImageIO.read(
                getClass().getResourceAsStream( Constants.RESOURCE_INSTRUCTION_PAGE_SCENARIO ) );

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
        g.drawImage( mFrontPage, 0, 0, Constants.WIDTH, Constants.HEIGHT, null );
    }


    @Override
    public void handleKeyEvents()
    {

    }


    @Override
    public void handleMouseClicked( MouseEvent e )
    {
        if ( mFrontPage.equals( mInstructionPage ) )
        {
            mController.switchScenario( Constants.SCENARIO_ISLAND );
        }
        else
        {
            if ( e.getX() >= 640 )
            {
                mController.setCurrentRole( Constants.ROLE_KING );
            }
            else
            {
                mController.setCurrentRole( Constants.ROLE_PIRATE );
            }

            mFrontPage = mInstructionPage;
        }

    }

}
