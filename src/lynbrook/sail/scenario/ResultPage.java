package lynbrook.sail.scenario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import lynbrook.sail.controller.GameController;
import lynbrook.sail.data.Constants;

/**
 * 
 * The result page initialization and drawing and this class is a Scenario
 *
 * @author Carol and Lucy
 * @version May 31, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: none
 */
public class ResultPage extends Scenario
{

	private BufferedImage won;

	private BufferedImage lost;

	/**
	 * The result page constructor
	 * 
	 * @param controller GameController
	 */
	public ResultPage(GameController controller)
	{
		super(controller);
		init();
	}

	/**
	 * initializes the image for the Result page
	 */
	private void init()
	{
		try
		{
			won = ImageIO.read(getClass().getResourceAsStream(Constants.RESOURCE_RESULT_WON));

			lost = ImageIO.read(getClass().getResourceAsStream(Constants.RESOURCE_RESULT_LOST));

		} catch (Exception e)
		{

		}
	}

	/**
	 * Update from Scenario
	 */
	@Override
	public void update()
	{

	}

	/**
	 * Draws the result page
	 * 
	 * @param g Graphics2D
	 */
	@Override
	public void draw(Graphics2D g)
	{
		BufferedImage image = mController.getResult() ? won : lost;
		int x = Constants.WIDTH / 2 - image.getWidth() / 2;
		int y = Constants.HEIGHT / 2 - image.getHeight() / 2;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
		g.drawImage(image, x, y, null);
	}

	/**
	 * handling key events from Scenario
	 */
	@Override
	public void handleKeyEvents()
	{

	}

	/**
	 * handling mouse clicked events from Scenario
	 * 
	 * @param e MouseEvent
	 */
	@Override
	public void handleMouseClicked(MouseEvent e)
	{

	}

}
