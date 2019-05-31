package lynbrook.sail.controller;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import lynbrook.sail.senario.Scenario;
import lynbrook.sail.data.Constants;
import lynbrook.sail.network.BattleData;
import lynbrook.sail.network.DataUpdate;
import lynbrook.sail.network.NetworkRunnable;
import lynbrook.sail.network.PlayerData;
import lynbrook.sail.senario.BattleField;
import lynbrook.sail.senario.BeginningPage;
import lynbrook.sail.senario.IslandScenario;
import lynbrook.sail.senario.ResultPage;


public class GameController extends KeyAdapter implements MouseListener, DataUpdate
{

    private Map<Integer, Boolean> mKeyEventMap;

    private Map<Integer, PlayerData> mPlayerDataMap;

    private Scenario mScenario;

    private Point lastLoc;

    private BattleData lastBattleData;

    private int role;

    private int scenario;

    private boolean won;

    Thread messageThread;


    public GameController()
    {
        mKeyEventMap = new TreeMap<>();
        mPlayerDataMap = new TreeMap<>();
        lastLoc = new Point();
        lastBattleData = new BattleData();
        role = Constants.ROLE_KING;
        scenario = Constants.SCENARIO_BEGIN;
        switchScenario( scenario );
    }


    public Map<Integer, PlayerData> getPlayerDataMap()
    {
        return mPlayerDataMap;
    }


    public void setCurrentRole( int role )
    {
        this.role = role;
    }


    public int getCurrentRole()
    {
        return role;
    }


    public boolean getResult()
    {
        return won;
    }


    public void setResult( boolean won )
    {
        this.won = won;
    }


    public void switchScenario( int scenario )
    {

        if ( mScenario != null )
        {
            if ( mScenario.getCurrentPlayer() != null )
            {
                lastLoc = mScenario.getCurrentPlayer().getPosition();
            }

            if ( this.scenario == Constants.SCENARIO_BATTLE_FIELD )
            {
                lastBattleData.fromWeapon( mScenario.getCurrentWeapon() );
            }
            mScenario = null;
        }
        this.scenario = scenario;
        switch ( scenario )
        {

            case Constants.SCENARIO_BEGIN: // beginning page
                mScenario = new BeginningPage( this );
                break;
            case Constants.SCENARIO_ISLAND: // map
                mScenario = new IslandScenario( this );
                messageThread = new Thread(
                    new NetworkRunnable( role, Constants.PIRATE_VS_KING_IP_ADDRESS, this ) );
                messageThread.start();
                break;
            case Constants.SCENARIO_BATTLE_FIELD:

                mScenario = new BattleField( this );
                break;

            case Constants.SCENARIO_RESULT:
                mScenario = new ResultPage( this );
                break;
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


    @Override
    public void keyPressed( KeyEvent key )
    {
        mKeyEventMap.put( key.getKeyCode(), true );
    }


    @Override
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
        if ( mScenario != null )
        {
            mScenario.handleMouseClicked( e );
        }

    }


    @Override
    public void mousePressed( MouseEvent e )
    {
        if ( mScenario != null )
        {
            mScenario.handleMousePressed( e );
        }
    }


    @Override
    public void mouseReleased( MouseEvent e )
    {
        if ( mScenario != null )
        {
            mScenario.handleMouseReleased( e );
        }

    }


    @Override
    public void mouseEntered( MouseEvent e )
    {

    }


    @Override
    public void mouseExited( MouseEvent e )
    {

    }


    @Override
    public void onUpdateData( Map<Integer, PlayerData> playerMap )
    {
        for ( Map.Entry<Integer, PlayerData> entry : playerMap.entrySet() )
        {
            mPlayerDataMap.put( entry.getKey(), entry.getValue() );
        }

    }


    @Override
    public Point getCurrentLocation()
    {
        return mScenario != null ? mScenario.getCurrentPlayer().getPosition() : lastLoc;
    }


    @Override
    public int getScenario()
    {
        return scenario;
    }


    @Override
    public BattleData getBattleData()
    {
        BattleData data = new BattleData();
        if ( mScenario != null )
        {
            data.fromWeapon( mScenario.getCurrentWeapon() );
        }

        return scenario == Constants.SCENARIO_BATTLE_FIELD ? data : lastBattleData;
    }


    public void playBombSound()
    {
        try
        {
            InputStream is = getClass().getResourceAsStream( Constants.RESOUCE_BOMB_SOUND );
            Clip clip = AudioSystem.getClip();
            clip.open( AudioSystem.getAudioInputStream( is ) );
            clip.start();
        }
        catch ( Exception e )
        {
        }
    }
}
