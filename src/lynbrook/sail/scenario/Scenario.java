package lynbrook.sail.scenario;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import lynbrook.sail.actor.Player;
import lynbrook.sail.actor.Weapon;
import lynbrook.sail.controller.GameController;

/**
 * Abstract class for scenario's
 *
 * @author Carol
 * @version May 31, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: none
 */
public abstract class Scenario
{
	protected GameController mController;

	/**
	 * The scenario constructor takes in a GameController
	 * 
	 * @param controller GameController
	 */
	public Scenario(GameController controller)
	{
		mController = controller;
	}

	/**
	 * updates the info
	 */
	public abstract void update();

	/**
	 * Updates the screen
	 * 
	 * @param g the Graphics2D
	 */
	public abstract void draw(Graphics2D g);

	/**
	 * Handles key events
	 */
	public abstract void handleKeyEvents();

	/**
	 * Returns current player
	 * 
	 * @return null player
	 */
	public Player getCurrentPlayer()
	{
		return null;
	}

	/**
	 * Returns current weapon
	 * 
	 * @return null weapon
	 */
	public Weapon getCurrentWeapon()
	{
		return null;
	}

	/**
	 * Handles mouseclicked
	 * 
	 * @param e mouseevent
	 */
	public abstract void handleMouseClicked(MouseEvent e);

	/**
	 * Handles mousepressed
	 * 
	 * @param e mouseevent
	 */
	public void handleMousePressed(MouseEvent e)
	{

	}

	/**
	 * Handles mousereleased
	 * 
	 * @param e mouseevent
	 */
	public void handleMouseReleased(MouseEvent e)
	{

	}
}