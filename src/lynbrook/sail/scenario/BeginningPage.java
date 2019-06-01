package lynbrook.sail.scenario;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import lynbrook.sail.controller.GameController;
import lynbrook.sail.data.Constants;

/**
 * 
 * scenario of the beginning page of the whole game
 * 
 * @author Carol and Lucy
 * @version May 31, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: none
 */
public class BeginningPage extends Scenario
{
	private BufferedImage mFrontPage;

	private BufferedImage mInstructionPage;

	/**
	 * The beginning page is a scenario
	 * 
	 * @param controller the game controller
	 */
	public BeginningPage(GameController controller)
	{
		super(controller);
		init();
	}

	/**
	 * Initializes the Front page image and instruction page image
	 */
	private void init()
	{
		try
		{
			mFrontPage = ImageIO.read(getClass().getResourceAsStream(Constants.RESOURCE_FRONT_PAGE_SCENARIO));

			mInstructionPage = ImageIO
					.read(getClass().getResourceAsStream(Constants.RESOURCE_INSTRUCTION_PAGE_SCENARIO));

		} catch (Exception e)
		{

		}
	}

	/**
	 * Update, implementation in Scenario
	 */
	@Override
	public void update()
	{

	}

	/**
	 * Draws the beginning page
	 * 
	 * @param g graphics
	 */
	@Override
	public void draw(Graphics2D g)
	{
		g.drawImage(mFrontPage, 0, 0, Constants.WIDTH, Constants.HEIGHT, null);
	}

	/**
	 * handles key events
	 */
	@Override
	public void handleKeyEvents()
	{

	}

	/**
	 * handles mouse events
	 * 
	 * @param e mouseevent
	 */
	@Override
	public void handleMouseClicked(MouseEvent e)
	{
		if (mFrontPage.equals(mInstructionPage))
		{
			mController.switchScenario(Constants.SCENARIO_ISLAND);
		} else
		{
			if (e.getX() >= 640)
			{
				mController.setCurrentRole(Constants.ROLE_KING);
			} else
			{
				mController.setCurrentRole(Constants.ROLE_PIRATE);
			}

			mFrontPage = mInstructionPage;
		}

	}

}
