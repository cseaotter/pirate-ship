package lynbrook.sail.senario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import lynbrook.sail.controller.GameController;
import lynbrook.sail.data.Constants;


public class BattleField extends Scenario
{

    private BufferedImage mBoat;

    private BufferedImage mWater;

    private BufferedImage mCastleWall;

    private Point point;


    public BattleField( GameController controller )
    {
        super( controller );
        // TODO Auto-generated constructor stub
        init();

    }


    private void init()
    {
        try
        {
            // put all the images as constants
            mBoat = ImageIO.read( getClass().getResourceAsStream( Constants.RESOURCE_BOAT ) );
            mWater = ImageIO.read( getClass().getResourceAsStream( Constants.RESOURCE_WATER ) );

            mCastleWall = ImageIO
                .read( getClass().getResourceAsStream( Constants.RESOURCE_CASTLE_WALL ) );
            point = new Point( 300, Constants.HEIGHT / 2 - mBoat.getHeight() / 2 );

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
    public void draw( Graphics2D g2 )
    {
        g2.setColor( Color.WHITE );
        g2.fillRect( 0, 0, Constants.WIDTH, Constants.HEIGHT );
        g2.drawImage( mWater, 0, point.y, Constants.WIDTH, mWater.getHeight(), null );
        g2.drawImage( mBoat, point.x, point.y, null );
        g2.drawImage( mCastleWall, 0, 0, 1280, 639, null );
    }


    @Override
    public void handleKeyEvents()
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void handleMouseClicked( MouseEvent e )
    {

    }


    @Override
    public void handleMousePressed( MouseEvent e )
    {
        point.setLocation( e.getX(), e.getY() );
        if ( e.getX() < 100 && e.getY() < 100 )
        {
            mController.switchScenario( Constants.SCENARIO_RESULT );
        }
    }


    @Override
    public void handleMouseReleased( MouseEvent e )
    {

    }
}
