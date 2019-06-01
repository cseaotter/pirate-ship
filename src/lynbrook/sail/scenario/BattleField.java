package lynbrook.sail.scenario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.imageio.ImageIO;

import lynbrook.sail.actor.KingWeapon;
import lynbrook.sail.actor.PirateWeapon;
import lynbrook.sail.actor.Weapon;
import lynbrook.sail.controller.GameController;
import lynbrook.sail.data.Constants;
import lynbrook.sail.network.PlayerData;

/**
 * 
 * this class is for the scenario in the battle field (fighting scene). It
 * includes the canons and the health bar. It handles key events and updates
 * information.
 *
 * @author Carol
 * @version May 31, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: none
 */
public class BattleField extends Scenario
{

	private BufferedImage mBoat;

	private BufferedImage mWater;

	private BufferedImage mCastleWall;

	private BufferedImage mCannon;

	private BufferedImage[] mExplosion;

	private Weapon weapon;

	private Weapon remoteWeapon;

	private BufferedImage mBullet;

	private long start;

	private double vx;

	private double vy;

	private double x;

	private double y;

	private double x1;

	private double y1;

	private boolean shoot;

	private int explosion;

	/**
	 * Constructs a battlefield
	 * 
	 * @param controller the gamecontroller
	 */
	public BattleField(GameController controller)
	{
		super(controller);
		init();
	}

	/**
	 * Initializes the images variables and catches exception just in case
	 */
	private void init()
	{
		try
		{
			mBoat = ImageIO.read(getClass().getResourceAsStream(Constants.RESOURCE_BOAT));
			mWater = ImageIO.read(getClass().getResourceAsStream(Constants.RESOURCE_WATER));

			mCastleWall = ImageIO.read(getClass().getResourceAsStream(Constants.RESOURCE_CASTLE_WALL));

			mCannon = ImageIO.read(getClass().getResourceAsStream(Constants.RESOURCE_CANNON));
			mBullet = ImageIO.read(getClass().getResourceAsStream(Constants.RESOURCE_CANNON_BALL));

			BufferedImage bi = ImageIO.read(getClass().getResourceAsStream(Constants.RESOURCE_EXPLOSION));
			mExplosion = new BufferedImage[8];
			for (int i = 0; i < 8; i++)
			{
				mExplosion[i] = bi.getSubimage(0, i * 120, 160, 120);
			}
			explosion = 8;
		} catch (Exception e)
		{

		}

		int role = mController.getCurrentRole();
		if (role == Constants.ROLE_KING)
		{
			weapon = new KingWeapon();
			weapon.setLoc(new Point(Constants.WIDTH - mCastleWall.getWidth() + 250, 500));
		} else
		{
			weapon = new PirateWeapon(Constants.WIDTH - mCastleWall.getWidth() + 200 - mCannon.getWidth());
			weapon.setLoc(new Point(300, Constants.HEIGHT / 2 - mBoat.getHeight() / 2));
		}
	}

	/**
	 * Updates the data on the battlefield
	 */
	@Override
	public void update()
	{
		handleKeyEvents();

		Map<Integer, PlayerData> dataMap = mController.getPlayerDataMap();
		if (mController.getPlayerDataMap().size() == 2)
		{
			int remoteRole = remoteRole();
			PlayerData remotePlayerData = dataMap.get(remoteRole);
			Weapon battleData = remotePlayerData.getWeapon();
			if (remoteWeapon == null)
			{
				if (remoteRole == Constants.ROLE_KING)
				{
					remoteWeapon = new KingWeapon();
				} else
				{
					remoteWeapon = new PirateWeapon(
							Constants.WIDTH - mCastleWall.getWidth() + 200 - mCannon.getWidth());

				}
			}

			if (battleData != null)
			{
				remoteWeapon = battleData;
			}

			if (remoteWeapon.getRemoteHealth() != -1 || remotePlayerData.getScenario() == Constants.SCENARIO_RESULT)
			{
				weapon.setHealth(remoteWeapon.getRemoteHealth());
				if (weapon.getHealth() <= 0)
				{
					mController.setResult(false);
					mController.switchScenario(Constants.SCENARIO_RESULT);
				}
			}

			if (weapon.getRemoteHealth() != -1 && weapon.getRemoteHealth() <= 0)
			{
				mController.setResult(true);
				mController.switchScenario(Constants.SCENARIO_RESULT);
			}

		}
	}

	/**
	 * Draws the battlefield, and battlefield's pirate and castle and the weapon
	 * explosion
	 * 
	 * @param g2 Graphics2D
	 */
	@Override
	public void draw(Graphics2D g2)
	{

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
		g2.drawImage(mWater, 0, Constants.HEIGHT - mWater.getHeight() - 250, null);

		g2.drawImage(mCastleWall, Constants.WIDTH - mCastleWall.getWidth() + 200,
				Constants.HEIGHT - mCastleWall.getHeight() - mWater.getHeight(), null);

		drawPirate(g2);
		drawCannonOnCastle(g2);
		if (shoot)
		{
			long current = System.currentTimeMillis();
			float t = (current - start) / 1000.0f;
			x = x1 + vx * t;
			y = y1 + vy * t + 98 * t * t / 2;
			g2.drawImage(mBullet, (int) x, (int) y, null);
			weapon.setBulletLoc(new Point((int) x, (int) y));
		}

		int role = mController.getCurrentRole();
		if (explosion != 8 || shoot)
		{
			if (role == Constants.ROLE_PIRATE && y >= 200 && y < 800
					&& x >= Constants.WIDTH - mCastleWall.getWidth() + 200 || hitPirate((int) x, (int) y))
			{

				if (shoot)
				{
					if (remoteWeapon != null)
					{
						int health = remoteWeapon.getHealth() - Constants.BOMB_DAMAGE;
						weapon.setRemoteHealth(health);
					}
					weapon.setBulletExplosion(true);
					explosion = 0;
					mController.playBombSound();
				}
				shoot = false;

				weapon.setExplosionFrame(explosion);
				g2.drawImage(mExplosion[explosion++], (int) x, (int) y, null);
				if (explosion == 8)
				{
					weapon.setBulletActive(false);
					weapon.setBulletExplosion(false);
				}
			}
		}

		if (shoot)
		{
			weapon.setBulletExplosion(false);
		}

		if (x < 0 || x > Constants.WIDTH || y < 0 || y > Constants.HEIGHT)
		{
			weapon.setBulletActive(false);
		}
		drawRemoteBullet(g2);
		drawHealthBar(g2);

	}

	/**
	 * Draw the remote bullet image
	 * 
	 * @param g2 Graphics 2D g2;
	 */
	private void drawRemoteBullet(Graphics2D g2)
	{
		if (remoteWeapon != null && remoteWeapon.isBulletActive())
		{
			Point bulletLoc = remoteWeapon.getBulletLoc();
			if (bulletLoc != null && !(bulletLoc.x == 0 && bulletLoc.y == 0))
			{
				g2.drawImage(mBullet, bulletLoc.x, bulletLoc.y, null);
			}

			if (remoteWeapon.isBulletExplosion())
			{
				g2.drawImage(mExplosion[remoteWeapon.getExplosionFrame()], bulletLoc.x, bulletLoc.y, null);
				if (remoteWeapon.getExplosionFrame() == 0)
				{
					mController.playBombSound();
				}
			}
		}
	}

	/**
	 * True if hit pirate false otherwise
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @return boolean like above
	 */
	private boolean hitPirate(int x, int y)
	{
		int role = mController.getCurrentRole();
		if (role != Constants.ROLE_KING || remoteWeapon == null)
		{
			return false;
		}

		Point point = remoteWeapon.getLoc();
		return y >= point.y && y <= point.y + mBoat.getHeight() && x >= point.x && x <= point.x + mBoat.getWidth();

	}

	/**
	 * Returns weapon
	 */
	@Override
	public Weapon getCurrentWeapon()
	{
		return weapon;
	}

	/**
	 * Draws the pirate image
	 * 
	 * @param g2 graphics
	 */
	private void drawPirate(Graphics2D g2)
	{
		Weapon wp = weapon;
		if (remoteRole() == Constants.ROLE_PIRATE)
		{
			wp = remoteWeapon;
		}
		if (wp == null)
		{
			return;
		}
		Point point = wp.getLoc();
		if (point == null)
		{
			return;
		}
		g2.drawImage(mBoat, point.x, point.y, null);
		double rotationRequired = Math.toRadians(wp.getAngle());
		double locationX = mCannon.getWidth() / 2;
		double locationY = mCannon.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		g2.drawImage(op.filter(mCannon, null), point.x, point.y, null);
	}

	/**
	 * Draws the cannon that is on the castle
	 * 
	 * @param g2 graphics
	 */
	private void drawCannonOnCastle(Graphics2D g2)
	{
		Weapon wp = weapon;
		if (remoteRole() == Constants.ROLE_KING)
		{
			wp = remoteWeapon;
		}
		if (wp == null)
		{
			return;
		}
		double rotationRequired = Math.toRadians(wp.getAngle());
		double locationX = mCannon.getWidth() / 2;
		double locationY = mCannon.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		BufferedImage image = op.filter(mCannon, null);
		tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(null), 0);
		op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		g2.drawImage(op.filter(image, null), Constants.WIDTH - mCastleWall.getWidth() + 250, 500, null);
	}

	/**
	 * Draws the health bar
	 * 
	 * @param g2 graphics
	 */
	private void drawHealthBar(Graphics2D g2)
	{

		Weapon king = getKing();
		if (king != null)
		{
			g2.setColor(Color.YELLOW);
			g2.fill3DRect(Constants.WIDTH - mCastleWall.getWidth() + 200,
					Constants.HEIGHT - mCastleWall.getHeight() - mWater.getHeight() - 100, 300, 50, true);

			g2.setColor(Color.RED);
			g2.fill3DRect(Constants.WIDTH - mCastleWall.getWidth() + 200,
					Constants.HEIGHT - mCastleWall.getHeight() - mWater.getHeight() - 100,
					king.getHealth() * 300 / Constants.KING_HEALTH, 50, true);
		}

		Weapon pirate = getPirate();
		if (pirate != null)
		{
			Point point = pirate.getLoc();
			if (point == null)
			{
				return;
			}
			g2.setColor(Color.YELLOW);
			g2.fill3DRect(point.x, point.y - 50, 100, 20, true);

			g2.setColor(Color.RED);
			g2.fill3DRect(point.x, point.y - 50, pirate.getHealth(), 20, true);
		}
	}

	/**
	 * returns weapon for pirate
	 * 
	 * @return remoteWeapon or weapon
	 */
	private Weapon getPirate()
	{
		if (remoteRole() == Constants.ROLE_PIRATE)
		{
			return remoteWeapon;
		} else
		{
			return weapon;
		}
	}

	/**
	 * returns weapon for king
	 * 
	 * @return remoteWeapon or weapon
	 */
	private Weapon getKing()
	{
		if (remoteRole() == Constants.ROLE_KING)
		{
			return remoteWeapon;
		} else
		{
			return weapon;
		}
	}

	/**
	 * Handles the events of the keys
	 */
	@Override
	public void handleKeyEvents()
	{
		int action = -1;
		if (mController.isDown(KeyEvent.VK_LEFT))
		{
			action = Constants.LEFT;
		} else if (mController.isDown(KeyEvent.VK_RIGHT))
		{
			action = Constants.RIGHT;
		} else if (mController.isDown(KeyEvent.VK_UP))
		{
			action = Constants.UP;
		} else if (mController.isDown(KeyEvent.VK_DOWN))
		{
			action = Constants.DOWN;
		}
		if (action != -1)
		{
			weapon.handleKeyEvents(action);
		}
	}

	/**
	 * Handles the mouse clicks
	 */
	@Override
	public void handleMouseClicked(MouseEvent e)
	{
		shoot = true;
		weapon.setBulletActive(true);
		start = System.currentTimeMillis();
		Point point = weapon.getLoc();
		x1 = point.x + mCannon.getWidth();
		y1 = point.y;
		int angle = weapon.getAngle();
		if (mController.getCurrentRole() == Constants.ROLE_KING)
		{
			angle = (180 - angle + 360) % 360;
			x1 = point.getX();
		}
		vx = 350 * Math.cos(Math.toRadians(angle));
		vy = 350 * Math.sin(Math.toRadians(angle));
	}

	/**
	 * Returns what the other role is
	 * 
	 * @return the remote role
	 */
	private int remoteRole()
	{
		return (mController.getCurrentRole() + 1) % 2;
	}

	/**
	 * handles mouse pressed
	 * 
	 * @param e MouseEvent
	 */
	@Override
	public void handleMousePressed(MouseEvent e)
	{

	}

	/**
	 * handles mouse release
	 * 
	 * @param e MouseEvent
	 */
	@Override
	public void handleMouseReleased(MouseEvent e)
	{

	}
}
