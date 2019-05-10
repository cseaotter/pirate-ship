package lynbrook.sail.actor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import lynbrook.sail.data.Constants;
import lynbrook.sail.data.PlayerImage;
import lynbrook.sail.gui.IslandMap;


public class Player extends Actor
{

    private final int DOWN = 0;

    private final int LEFT = 1;

    private final int RIGHT = 2;

    private final int UP = 3;

    private long ticks;

    private PlayerImage mPlayerImage;


    public Player( IslandMap tm )
    {

        super( tm );

        width = Constants.PLAYER_DIMENTION;
        height = Constants.PLAYER_DIMENTION;
        moveSpeed = Constants.PLAYER_MOVE_SPEED;
        mPlayerImage = new PlayerImage();
        animation.setFrames( mPlayerImage.getPlayerImage( DOWN ) );
        animation.setDelay( 10 );
    }


    private void setAnimation( int i, BufferedImage[] bi, int d )
    {
        currentAnimation = i;
        animation.setFrames( bi );
        animation.setDelay( d );
    }


    public long getTicks()
    {
        return ticks;
    }


    public void setDown()
    {
        super.setDown();
    }


    public void setLeft()
    {
        super.setLeft();
    }


    public void setRight()
    {
        super.setRight();
    }


    public void setUp()
    {
        super.setUp();
    }


    public void setAction()
    {
    }


    public void update()
    {

        ticks++;

        if ( down && currentAnimation != DOWN )
        {
            setAnimation( DOWN, mPlayerImage.getPlayerImage( DOWN ), 10 );
        }

        if ( left && currentAnimation != LEFT )
        {
            setAnimation( LEFT, mPlayerImage.getPlayerImage( LEFT ), 10 );
        }

        if ( right && currentAnimation != RIGHT )
        {
            setAnimation( RIGHT, mPlayerImage.getPlayerImage( RIGHT ), 10 );
        }

        if ( up && currentAnimation != UP )
        {
            setAnimation( UP, mPlayerImage.getPlayerImage( UP ), 10 );
        }

        super.update();

    }


    public void draw( Graphics2D g )
    {
        super.draw( g );
    }
}
