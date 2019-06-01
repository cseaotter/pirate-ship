package lynbrook.sail.test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import lynbrook.sail.data.Constants;
import lynbrook.sail.data.PathOfMoving;
import lynbrook.sail.data.PlayerImage;

public class JUDataTest
{
	//Constants have only constant fields so they don't need to be tested
	
	// Test PathOfMoving class
	@Test
	public void pathOfMovingClassTest()
	{
		PathOfMoving po = new PathOfMoving(new Point(9,9), null);
		assertEquals(po.getPoint(), new Point(9,9));
		assertEquals(po.getNext(), null);
	}
	
	// Test PlayerImage class
	@Test
	public void playerImageClassTest()
	{
		PlayerImage pi = new PlayerImage(Constants.ROLE_KING);
		// catch exception can't be tested
	}
}
