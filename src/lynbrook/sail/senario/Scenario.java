package lynbrook.sail.senario;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import lynbrook.sail.actor.Player;
import lynbrook.sail.controller.GameController;


public abstract class Scenario
{
    protected GameController mController;


    public Scenario( GameController controller )
    {
        mController = controller;
    }


    public abstract void update();


    public abstract void draw( Graphics2D g );


    public abstract void handleKeyEvents();


    public Player getCurrentPlayer()
    {
        return null;
    }


    public abstract void handleMouseClicked( MouseEvent e );


    public void handleMousePressed( MouseEvent e )
    {

    }


    public void handleMouseReleased( MouseEvent e )
    {

    }
}
