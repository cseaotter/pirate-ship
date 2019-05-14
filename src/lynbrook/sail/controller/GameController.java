package lynbrook.sail.controller;

import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.TreeMap;

import lynbrook.sail.senario.Scenario;
import lynbrook.sail.senario.IslandScenario;


public class GameController extends KeyAdapter implements MouseListener
{
    public static final int SCENARIO_BEGIN = 0;

    public static final int SCENARIO_ISLAND = 1;

    private Map<Integer, Boolean> mKeyEventMap;

    private Scenario mScenario;


    public GameController()
    {
        mKeyEventMap = new TreeMap<>();
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
            case SCENARIO_BEGIN: // story line
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


    public void keyPressed( KeyEvent key )
    {
        mKeyEventMap.put( key.getKeyCode(), true );
    }


    public void keyReleased( KeyEvent key )
    {
        mKeyEventMap.put( key.getKeyCode(), false );
    }


    public boolean isDown( int keyCode )
    {
        return mKeyEventMap.containsKey( keyCode ) && mKeyEventMap.get( keyCode );
    }


    @Override
    public void mouseClicked( MouseEvent e )
    {
        // TODO Auto-generated method stub
        if ( mScenario != null )
        {
            mScenario.handleMouseClicked( e );
        }

    }


    @Override
    public void mousePressed( MouseEvent e )
    {
        // TODO Auto-generated method stub
    }


    @Override
    public void mouseReleased( MouseEvent e )
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void mouseEntered( MouseEvent e )
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void mouseExited( MouseEvent e )
    {
        // TODO Auto-generated method stub
    }

}
