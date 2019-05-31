package lynbrook.sail.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Button;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;

import org.junit.Test;

import lynbrook.sail.controller.GameController;
import lynbrook.sail.data.Constants;
import lynbrook.sail.network.PlayerData;


public class JUControllerTest
{

    @Test
    public void getPlayerDataMapTest()
    {
        GameController gc = new GameController();
        assertNotNull( gc.getPlayerDataMap() );
        assertEquals( gc.getCurrentRole(), Constants.ROLE_KING );
        gc.setCurrentRole( Constants.ROLE_PIRATE );
        assertEquals( gc.getCurrentRole(), Constants.ROLE_PIRATE );
        gc.setResult( true );
        assertEquals( gc.getResult(), true );
    }


    @Test
    public void switchScenarioTest()
    {
        GameController gc = new GameController();
        gc.switchScenario( Constants.SCENARIO_ISLAND );
        assertEquals( gc.getScenario(), Constants.SCENARIO_ISLAND );
        assertNotNull( gc.getCurrentLocation() );
        gc.switchScenario( Constants.SCENARIO_BATTLE_FIELD );
        assertNotNull( gc.getBattleData() );
        Button a = new Button( "click" );
        KeyEvent e;
        e = new KeyEvent( a, 1, 20, 1, KeyEvent.VK_UP, 'a' );

        gc.keyPressed( e );
        assertTrue( gc.isDown( KeyEvent.VK_UP ) );
        assertFalse( gc.isDown( KeyEvent.VK_DOWN ) );
        gc.keyReleased( e );
        JButton jb = new JButton( "Press!" );
        MouseEvent me = new MouseEvent( jb,
            MouseEvent.MOUSE_CLICKED,
            System.currentTimeMillis(),
            0,
            10,
            10,
            1,
            false );
        gc.mouseClicked( me );
        gc.mousePressed( me );
        gc.mouseReleased( me );
        gc.update();
        gc.mouseEntered( me );
        gc.mouseExited( me );
        gc.playBombSound();
        gc.switchScenario( Constants.SCENARIO_RESULT );
        assertNotNull( gc.getBattleData() );

    }


    @Test
    public void drawTest()
    {
        BufferedImage bi = new BufferedImage( Constants.WIDTH, Constants.HEIGHT, 1 );
        Graphics2D g = (Graphics2D)bi.getGraphics();
        GameController gc = new GameController();
        gc.draw( g );
    }


    @Test
    public void onUpdateDataTest()
    {
        GameController gc = new GameController();
        gc.switchScenario( Constants.SCENARIO_ISLAND );
        assertEquals( gc.getScenario(), Constants.SCENARIO_ISLAND );
        Map<Integer, PlayerData> playerMap = new TreeMap<>();
        playerMap.put( Constants.ROLE_KING, new PlayerData() );
        gc.onUpdateData( playerMap );

    }

}
