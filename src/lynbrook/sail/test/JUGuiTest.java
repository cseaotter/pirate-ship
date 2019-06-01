package lynbrook.sail.test;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;

import org.junit.Test;

import lynbrook.sail.data.Constants;
import lynbrook.sail.gui.IslandMap;
import lynbrook.sail.gui.MapImage;

/**
 * @author carol
 *
 */
public class JUGuiTest
{
	// GamePanel class has only GUI and private methods, thus don't need to write
	// junit for it

	// Test IslandMap class
	@Test
	public void islandMapClassTest()
	{
		IslandMap im = new IslandMap(Constants.ITEM_DIMENTION);
		im.loadMap(Constants.RESOURCE_ISLAND_SCENARIO_MAP);
		im.loadCastleMap(Constants.RESOURCE_ISLAND_SCENARIO_CASTLE_MAP, Constants.RESOURCE_ISLAND_SCENARIO_CASTLE);
		assertEquals(im.getTileSize(), 32);

		assertEquals(im.isBlocked(Constants.ROLE_KING, 11, 5), true);
		assertEquals(im.isBlocked(Constants.ROLE_KING, 5, 11), false);
		assertEquals(im.isBlocked(Constants.ROLE_KING, 18, 11), false);
		int[][] map = im.getMap();
		System.out.println("map 11, 5=" + map[5][11]);
		assertEquals(im.getIndex(im.getx(), im.gety()) != Constants.LAND, (im.isBlocked(Constants.ROLE_KING, 11, 5)));

		System.out.println("map 7, 14=" + map[14][7]);
		assertEquals(im.getIndex(im.getx(), im.gety()) == Constants.LAND, (im.isBlocked(Constants.ROLE_KING, 7, 14)));

		System.out.println("map 9, 9=" + map[9][9]);
		im.isBlocked(Constants.ROLE_PIRATE, 9, 9);
		assertEquals(im.getIndex(im.getx(), im.gety()) == Constants.WATER, (im.isBlocked(Constants.ROLE_KING, 9, 9)));

		System.out.println("map 7, 14=" + map[14][7]);
		assertEquals(im.getIndex(im.getx(), im.gety()) == Constants.WATER,
				(im.isBlocked(Constants.ROLE_PIRATE, 7, 14)));

		im.update();
		im.getNumRows();
		im.getNumCols();
		
		// TODO: update
	}

	// Test MapImage class
	@Test
	public void mapImageClassTest()
	{
		BufferedImage bi = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, 1);
		MapImage mi = new MapImage(bi, 0);
		mi.getImage();
		mi.setType(1);
		assertEquals(mi.getType(), 1);
	}
	
	//main class PirateVsKing doesn't need to be tested since it's GUI
}
