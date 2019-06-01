package lynbrook.sail.gui;

import java.awt.image.BufferedImage;

/**
 * The image of the map
 *
 * @author Carol
 * @version May 31, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: none
 */
public class MapImage
{

	private BufferedImage image;

	private int type;

	public static final int NORMAL = 0;

	public static final int BLOCKED = 1;

	/**
	 * Constructs the image
	 * 
	 * @param image the bufferedimage
	 * @param type  the type
	 */
	public MapImage(BufferedImage image, int type)
	{
		this.image = image;
		this.type = type;
	}

	/**
	 * Return the image
	 * 
	 * @return image the BufferedImage
	 */
	public BufferedImage getImage()
	{
		return image;
	}

	/**
	 * Returns the type
	 * 
	 * @return type the type
	 */
	public int getType()
	{
		return type;
	}

	/**
	 * sets the type
	 * 
	 * @param type whether it's blocked or unblocked
	 */
	public void setType(int type)
	{
		this.type = type;
	}
}
