package lynbrook.sail.actor;

import java.awt.Graphics2D;
import lynbrook.sail.data.Constants;
import lynbrook.sail.gui.IslandMap;


public class Player extends Actor
{

    public Player( IslandMap map, int role )
    {

        super( map, role );

        width = Constants.PLAYER_DIMENTION;
        height = Constants.PLAYER_DIMENTION;
        moveSpeed = Constants.PLAYER_MOVE_SPEED;

    }


    public void update()
    {

        if ( down )
        {
            playerImage.setPlayerImage( Constants.DOWN );
        }

        if ( left )
        {
            playerImage.setPlayerImage( Constants.LEFT );
        }

        if ( right )
        {
            playerImage.setPlayerImage( Constants.RIGHT );
        }

        if ( up )
        {
            playerImage.setPlayerImage( Constants.UP );
        }

        super.update();

    }


    public void draw( Graphics2D g )
    {
        super.draw( g );
    }
}
