package lynbrook.sail.senario;

import java.awt.Graphics2D;

import lynbrook.sail.controller.GameController;


public abstract class Scenario
{
    protected GameController mController;


    public Scenario( GameController controller )
    {
        mController = controller;
    }


    public abstract void init();


    public abstract void update();


    public abstract void draw( Graphics2D g );


    public abstract void handleInput();
}
