package lynbrook.sail.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.awt.Point;

import org.junit.Test;

import lynbrook.sail.actor.Weapon;
import lynbrook.sail.data.Constants;
import lynbrook.sail.network.PlayerData;

public class JUNetworkTest
{
	// Test PlayerData class
	@Test
	public void playerDataClassTest()
	{
		PlayerData pd = new PlayerData();
		PlayerData pd1 = new PlayerData(new Point(9, 9), Constants.ROLE_KING);
		PlayerData pd2 = null;
		assertFalse(pd.equals(pd1));
		assertFalse(pd.equals(pd2));

		pd.setPoint(new Point(9, 9));
		assertEquals(pd.getPoint(), new Point(9, 9));

		pd.setRole(Constants.ROLE_KING);
		assertEquals(pd.getRole(), Constants.ROLE_KING);

		pd.getWeapon();
		
		pd.setScenario(9);
		assertEquals(pd.getScenario(),9);
	}
}
