package lynbrook.sail.test;

import java.awt.Button;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;

import org.junit.Test;

import lynbrook.sail.actor.KingWeapon;
import lynbrook.sail.actor.PirateWeapon;
import lynbrook.sail.actor.Weapon;
import lynbrook.sail.controller.GameController;
import lynbrook.sail.data.Constants;
import lynbrook.sail.network.PlayerData;
import lynbrook.sail.scenario.BattleField;
import lynbrook.sail.scenario.BeginningPage;
import lynbrook.sail.scenario.IslandScenario;
import lynbrook.sail.scenario.ResultPage;

/**
 * @author carol
 *
 */
public class JUScenarioTest
{

	@Test
	public void beginningPagePirateTest()
	{
		GameController gc = new GameController();
		BeginningPage bp = new BeginningPage(gc);
		bp.update();
		JButton jb = new JButton("Press!");
		MouseEvent me = new MouseEvent(jb, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 10, 10, 1, false);
		bp.handleMouseClicked(me);
		MouseEvent me2 = new MouseEvent(jb, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 710, 10, 1, false);
		bp.handleMouseClicked(me2);
	}

	@Test
	public void beginningPageKingTest()
	{
		GameController gc = new GameController();
		BeginningPage bp = new BeginningPage(gc);
		bp.update();
		JButton jb = new JButton("Press!");
		MouseEvent me = new MouseEvent(jb, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 710, 10, 1, false);
		bp.handleMouseClicked(me);
		bp.handleKeyEvents();

	}

	@Test
	public void drawTest()
	{

		BufferedImage bi = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, 1);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		GameController gc = new GameController();
		BeginningPage bp = new BeginningPage(gc);
		bp.draw(g);
	}

	@Test
	public void islandScenarioUpdateTest()
	{
		GameController gc = new GameController();
		IslandScenario is = new IslandScenario(gc);

		Map<Integer, PlayerData> playerMap = new TreeMap<>();
		playerMap.put(Constants.ROLE_KING, new PlayerData());
		PlayerData pd = new PlayerData();
		pd.setPoint(new Point(18, 11));
		playerMap.put(Constants.ROLE_PIRATE, pd);
		gc.onUpdateData(playerMap);

		is.update();
		BufferedImage bi = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, 1);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		is.draw(g);
	}

	@Test
	public void islandScenarioUpdateSwitchTest()
	{
		GameController gc = new GameController();
		IslandScenario is = new IslandScenario(gc);

		Map<Integer, PlayerData> playerMap = new TreeMap<>();
		playerMap.put(Constants.ROLE_KING, new PlayerData());
		PlayerData pd = new PlayerData();
		pd.setPoint(new Point(1, 11));
		pd.setScenario(Constants.SCENARIO_BATTLE_FIELD);
		playerMap.put(Constants.ROLE_PIRATE, pd);
		gc.onUpdateData(playerMap);

		is.update();
		BufferedImage bi = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, 1);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		is.draw(g);
	}

	@Test
	public void IslandMouseClickedBlockedTest()
	{
		GameController gc = new GameController();
		IslandScenario is = new IslandScenario(gc);
		JButton jb = new JButton("Press!");
		MouseEvent me = new MouseEvent(jb, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 710, 10, 1, false);
		is.handleMouseClicked(me);
	}

	@Test
	public void IslandMouseClickedTest()
	{
		GameController gc = new GameController();
		IslandScenario is = new IslandScenario(gc);
		JButton jb = new JButton("Press!");
		MouseEvent me = new MouseEvent(jb, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 864, 480, 1, false);
		is.handleMouseClicked(me);
		is.handleKeyEvents();
		is.handleMouseClicked(me);
		is.handleKeyEvents();
		is.update();
		gc.setCurrentRole(Constants.ROLE_PIRATE);
	}

	@Test
	public void IslandMouseClickedPirateTest()
	{
		GameController gc = new GameController();
		gc.setCurrentRole(Constants.ROLE_PIRATE);
		IslandScenario is = new IslandScenario(gc);
		JButton jb = new JButton("Press!");
		MouseEvent me = new MouseEvent(jb, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 864, 480, 1, false);
		is.handleMouseClicked(me);
		is.handleKeyEvents();
		is.handleMouseClicked(me);
		is.handleKeyEvents();
		is.getCurrentPlayer().setTilePosition(18, 11);
		is.update();
	}

	@Test
	public void ResultPageTest()
	{
		GameController gc = new GameController();
		ResultPage rp = new ResultPage(gc);
		rp.update();
		BufferedImage bi = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, 1);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		rp.draw(g);
		rp.handleKeyEvents();
		JButton jb = new JButton("Press!");
		MouseEvent me = new MouseEvent(jb, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 864, 480, 1, false);
		rp.handleMouseClicked(me);
		rp.getCurrentWeapon();
		rp.handleMousePressed(me);
		rp.handleMouseReleased(me);

	}

	@Test
	public void battleFieldTest()
	{
		GameController gc = new GameController();
		BattleField bf = new BattleField(gc);

		Map<Integer, PlayerData> playerMap = new TreeMap<>();
		PlayerData k = new PlayerData();
		Weapon kw = new KingWeapon();
		kw.setRemoteHealth(0);
		k.setWeapon(kw);
		playerMap.put(Constants.ROLE_KING, k);
		PlayerData pd = new PlayerData();
		pd.setPoint(new Point(1, 11));
		pd.setScenario(Constants.SCENARIO_BATTLE_FIELD);
		Weapon wp = new PirateWeapon(800);
		wp.setRemoteHealth(0);
		wp.setBulletExplosion(true);
		wp.setExplosionFrame(0);
		wp.setBulletActive(true);
		wp.setBulletLoc(new Point(100, 100));
		pd.setWeapon(wp);
		pd.setScenario(Constants.SCENARIO_RESULT);
		playerMap.put(Constants.ROLE_PIRATE, pd);
		gc.onUpdateData(playerMap);

		bf.update();
		BufferedImage bi = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, 1);
		Graphics2D g = (Graphics2D) bi.getGraphics();

		JButton jb = new JButton("Press!");
		MouseEvent me = new MouseEvent(jb, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 864, 480, 1, false);
		bf.handleMouseClicked(me);
		bf.getCurrentWeapon();
		bf.handleMousePressed(me);
		bf.handleMouseReleased(me);

		bf.draw(g);

		Button a = new Button("click");
		KeyEvent e = new KeyEvent(a, 1, 20, 1, KeyEvent.VK_UP, 'a');
		gc.keyPressed(e);
		bf.handleKeyEvents();
		gc.keyReleased(e);
		e.setKeyCode(KeyEvent.VK_DOWN);
		gc.keyPressed(e);
		bf.handleKeyEvents();
		gc.keyReleased(e);
		e.setKeyCode(KeyEvent.VK_LEFT);
		gc.keyPressed(e);
		bf.handleKeyEvents();
		gc.keyReleased(e);
		e.setKeyCode(KeyEvent.VK_RIGHT);
		gc.keyPressed(e);
		bf.handleKeyEvents();
		gc.keyReleased(e);
	}

	@Test
	public void battleFieldPirateTest()
	{
		GameController gc = new GameController();
		gc.setCurrentRole(Constants.ROLE_PIRATE);
		BattleField bf = new BattleField(gc);

		Map<Integer, PlayerData> playerMap = new TreeMap<>();
		PlayerData k = new PlayerData();
		Weapon kw = new KingWeapon();
		kw.setRemoteHealth(0);
		k.setWeapon(kw);
		playerMap.put(Constants.ROLE_KING, k);
		PlayerData pd = new PlayerData();
		pd.setPoint(new Point(1, 11));
		pd.setScenario(Constants.SCENARIO_BATTLE_FIELD);
		Weapon wp = new PirateWeapon(1200);
		wp.setRemoteHealth(0);
		wp.setLoc(new Point(1000, 600));
		wp.setBulletActive(true);
		pd.setWeapon(wp);
		pd.setScenario(Constants.SCENARIO_RESULT);
		playerMap.put(Constants.ROLE_PIRATE, pd);
		gc.onUpdateData(playerMap);
		bf.getCurrentWeapon().setLoc(new Point(1000, 600));
		bf.update();
		BufferedImage bi = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, 1);
		Graphics2D g = (Graphics2D) bi.getGraphics();

		JButton jb = new JButton("Press!");
		MouseEvent me = new MouseEvent(jb, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 1000, 480, 1,
				false);
		bf.handleMouseClicked(me);
		bf.getCurrentWeapon();
		bf.handleMousePressed(me);
		bf.handleMouseReleased(me);

		bf.draw(g);

	}
}
