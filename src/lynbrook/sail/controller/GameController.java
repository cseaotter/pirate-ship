
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

import lynbrook.sail.actor.Weapon;
import lynbrook.sail.data.Constants;
import lynbrook.sail.network.DataUpdate;
import lynbrook.sail.network.NetworkRunnable;
import lynbrook.sail.network.PlayerData;
import lynbrook.sail.scenario.BattleField;
import lynbrook.sail.scenario.BeginningPage;
import lynbrook.sail.scenario.IslandScenario;
import lynbrook.sail.scenario.ResultPage;
import lynbrook.sail.scenario.Scenario;

/**
 * 
 * GameController switches scenarios and stores information about the running
 * game
 *
 * @author Carol
 * @version May 31, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: none
 */
public class GameController extends KeyAdapter implements MouseListener, DataUpdate
{

	private Map<Integer, Boolean> mKeyEventMap;

	private Map<Integer, PlayerData> mPlayerDataMap;

	private Scenario mScenario;

	private Point lastLoc;

	private Weapon lastWp;

	private int role;

	private int scenario;

	private boolean won;

	Thread messageThread;

	/**
	 * Constructs the game controller Initializes the variables
	 */
	public GameController()
	{
		mKeyEventMap = new TreeMap<>();
		mPlayerDataMap = new TreeMap<>();
		lastLoc = new Point();
		lastWp = new Weapon(0);
		role = Constants.ROLE_KING;
		scenario = Constants.SCENARIO_BEGIN;
		switchScenario(scenario);
	}

	/**
	 * The map of the playerData
	 * 
	 * @return mPlayerDataMap the map
	 */
	public Map<Integer, PlayerData> getPlayerDataMap()
	{
		return mPlayerDataMap;
	}

	/**
	 * Sets the currentRole of the player
	 * 
	 * @param role the role
	 */
	public void setCurrentRole(int role)
	{
		this.role = role;
	}

	/**
	 * Returns the currentRole
	 * 
	 * @return role the role
	 */
	public int getCurrentRole()
	{
		return role;
	}

	/**
	 * Returns the result of the game
	 * 
	 * @return won the result
	 */
	public boolean getResult()
	{
		return won;
	}

	/**
	 * Sets the result
	 * 
	 * @param won if won or not
	 */
	public void setResult(boolean won)
	{
		this.won = won;
	}

	/**
	 * The scenarios for battlefields and switch cases to handle each scenario
	 * 
	 * @param scenario the scenario id
	 */
	public void switchScenario(int scenario)
	{

		if (mScenario != null)
		{
			if (mScenario.getCurrentPlayer() != null)
			{
				lastLoc = mScenario.getCurrentPlayer().getPosition();
			}

			if (this.scenario == Constants.SCENARIO_BATTLE_FIELD)
			{
				lastWp = mScenario.getCurrentWeapon();
			}
			mScenario = null;
		}
		this.scenario = scenario;
		switch (scenario)
		{

		case Constants.SCENARIO_BEGIN: // beginning page
			mScenario = new BeginningPage(this);
			break;
		case Constants.SCENARIO_ISLAND: // map
			mScenario = new IslandScenario(this);
			messageThread = new Thread(new NetworkRunnable(role, Constants.PIRATE_VS_KING_IP_ADDRESS, this));
			messageThread.start();
			break;
		case Constants.SCENARIO_BATTLE_FIELD:

			mScenario = new BattleField(this);
			break;

		case Constants.SCENARIO_RESULT:
			mScenario = new ResultPage(this);
			break;
		}

	}

	/**
	 * If the scenario is not null, the mScenario updates
	 */
	public void update()
	{
		if (mScenario != null)
		{
			mScenario.update();
		}
	}

	/**
	 * Draws this scenario
	 * 
	 * @param g Graphics
	 */
	public void draw(Graphics2D g)
	{
		if (mScenario != null)
		{
			mScenario.draw(g);
		}
	}

	/**
	 * Takes in keyevent and puts it into the map
	 */
	@Override
	public void keyPressed(KeyEvent key)
	{
		mKeyEventMap.put(key.getKeyCode(), true);
	}

	/**
	 * Takes in keyevent and puts it into the map
	 */
	@Override
	public void keyReleased(KeyEvent key)
	{
		mKeyEventMap.put(key.getKeyCode(), false);
	}

	/**
	 * returns if the key is in the map and if the key is the keycode passedin
	 * 
	 * @param keyCode the keyCode id
	 * @return true or false based on the above specs
	 */
	public boolean isDown(int keyCode)
	{
		return mKeyEventMap.containsKey(keyCode) && mKeyEventMap.get(keyCode);
	}

	/**
	 * if not null handles the mouse clicked event
	 * 
	 * @param e MouseEvent
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (mScenario != null)
		{
			mScenario.handleMouseClicked(e);
		}

	}

	/**
	 * if not null handles the mouse pressed event
	 * 
	 * @param e MouseEvent
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		if (mScenario != null)
		{
			mScenario.handleMousePressed(e);
		}
	}

	/**
	 * if not null handles the mouse released event
	 * 
	 * @param e the event of the mouse movement
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{
		if (mScenario != null)
		{
			mScenario.handleMouseReleased(e);
		}

	}

	/**
	 * mouseEntered event
	 * 
	 * @param e event
	 */
	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	/**
	 * mouseExited event
	 * 
	 * @param e event
	 */
	@Override
	public void mouseExited(MouseEvent e)
	{

	}

	/**
	 * update the playerData map
	 * 
	 * @param playerMap the map with all the playerData
	 */
	@Override
	public void onUpdateData(Map<Integer, PlayerData> playerMap)
	{
		for (Map.Entry<Integer, PlayerData> entry : playerMap.entrySet())
		{
			mPlayerDataMap.put(entry.getKey(), entry.getValue());
		}

	}

	/**
	 * Get current location
	 */
	@Override
	public Point getCurrentLocation()
	{
		return mScenario != null ? mScenario.getCurrentPlayer().getPosition() : lastLoc;
	}

	/**
	 * Returns scenario
	 */
	@Override
	public int getScenario()
	{
		return scenario;
	}

	/**
	 * Get battleData
	 */
	@Override
	public Weapon getBattleData()
	{
		Weapon weapon = null;
		if (mScenario != null)
		{
			weapon = mScenario.getCurrentWeapon();
		}

		return scenario == Constants.SCENARIO_BATTLE_FIELD ? weapon : lastWp;
	}

	/**
	 * Play the bomb sound form the clip.
	 */
	public void playBombSound()
	{
		try
		{
			InputStream is = getClass().getResourceAsStream(Constants.RESOUCE_BOMB_SOUND);
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(is));
			clip.start();
		} catch (Exception e)
		{
		}
	}
}