package lynbrook.sail.senario;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import lynbrook.sail.backend.PlayerData;
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
        // TODO Auto-generated method stub

    }


    @Override
    public void draw( Graphics2D g )
    {
        // TODO Auto-generated method stub
        g.drawImage( mFrontPage, 0, 0, Constants.WIDTH, Constants.HEIGHT, null );
    }


    @Override
    public void handleKeyEvents()
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void handleMouseClicked( MouseEvent e )
    {
        // TODO Auto-generated method stub
        if ( mFrontPage.equals( mInstructionPage ) )
        {
            mController.switchScenario( GameController.SCENARIO_ISLAND );
        }
        else
        {
            // TODO:
            if ( e.getX() >= 640 )
            {
                mController.setCurrentRole( PlayerData.ROLE_KING );
            }
            else
            {
                mController.setCurrentRole( PlayerData.ROLE_PIRATE );
            }

            System.out.println( "role selected : " + mController.getCurrentRole() );
            mFrontPage = mInstructionPage;
        }

    }

}
