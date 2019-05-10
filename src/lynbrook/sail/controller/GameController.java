package lynbrook.sail.controller;

import java.awt.Graphics2D;
import lynbrook.sail.senario.Scenario;
import lynbrook.sail.senario.IslandScenario;


public class GameController
{
    public static final int SCENARIO_BEGIN = 0;

    public static final int SCENARIO_ISLAND = 1;

    private Scenario mScenario;


    public GameController()
    {
        switchScenario( SCENARIO_ISLAND );
    }


    public void switchScenario( int scenario )
    {

        if ( mScenario != null )
        {
            mScenario = null;
        }

        switch ( scenario )
        {
            case SCENARIO_BEGIN: // storyline
                break;
            case SCENARIO_ISLAND: // map
                mScenario = new IslandScenario( this );
                mScenario.init();
                break;
            // to do: fighting scenario
        }

    }


    public void update()
    {
        if ( mScenario != null )
        {
            mScenario.update();
        }
    }


    public void draw( Graphics2D g )
    {
        if ( mScenario != null )
        {
            mScenario.draw( g );
        }
    }
}
