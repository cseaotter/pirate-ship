package lynbrook.sail.actor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import lynbrook.sail.backend.Role;
import lynbrook.sail.data.Constants;
import lynbrook.sail.data.PlayerImage;
import lynbrook.sail.gui.IslandMap;

public class Player extends Actor implements Role
{

	private final int DOWN = 2;

	private final int LEFT = 1;

	private final int RIGHT = 3;

	private final int UP = 0;

	private long mTicks;

	private PlayerImage mPlayerImage;

	public Player(IslandMap map)
	{

		super(map);

		width = Constants.PLAYER_DIMENTION;
		height = Constants.PLAYER_DIMENTION;
		moveSpeed = Constants.PLAYER_MOVE_SPEED;
		mPlayerImage = new PlayerImage();
		mAnimation.setFrames(mPlayerImage.getPlayerImage(DOWN));
		mAnimation.setDelay(10);
	}

	private void setAnimation(int frame, BufferedImage[] image, int delay)
	{
		mCurrentAnimation = frame;
		mAnimation.setFrames(image);
		mAnimation.setDelay(delay);
	}

	public long getTicks()
	{
		return mTicks;
	}

	public void update()
	{

		mTicks++;

		if (down && mCurrentAnimation != DOWN)
		{
			setAnimation(DOWN, mPlayerImage.getPlayerImage(DOWN), 10);
		}

		if (left && mCurrentAnimation != LEFT)
		{
			setAnimation(LEFT, mPlayerImage.getPlayerImage(LEFT), 10);
		}

		if (right && mCurrentAnimation != RIGHT)
		{
			setAnimation(RIGHT, mPlayerImage.getPlayerImage(RIGHT), 10);
		}

		if (up && mCurrentAnimation != UP)
		{
			setAnimation(UP, mPlayerImage.getPlayerImage(UP), 10);
		}

		super.update();

	}

	public void draw(Graphics2D g)
	{
		super.draw(g);
	}
}
