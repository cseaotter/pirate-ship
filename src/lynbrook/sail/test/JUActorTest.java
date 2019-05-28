package lynbrook.sail.test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

import lynbrook.sail.actor.Actor;
import lynbrook.sail.actor.Pirate;
import lynbrook.sail.controller.GameController;
import lynbrook.sail.data.Constants;
import lynbrook.sail.gui.IslandMap;

public class JUActorTest
{
	// Test Actor class by Carol Gao
	@Test
	public void setPositionTest()
	{
		IslandMap im = new IslandMap(9);
		Actor ac = new Pirate(im);
		ac.setPosition(9, 7);
		assertEquals(ac.getPosition(), new Point(9/9, 7/9));
	}
	
	@Test
	public void setMapPositionTest()
	{
		IslandMap im = new IslandMap(9);
		Actor ac = new Pirate(im);
		ac.setMapPosition();
		assertEquals(ac.getPosition(), new Point(im.getx(), im.gety()));
	}
	
	@Test
	public void setTilePositionTest()
	{
		IslandMap im = new IslandMap(9);
		Actor ac = new Pirate(im);
		ac.setTilePosition(9, 7);
		int size = im.getTileSize();
		assertEquals(ac.getPosition(), new Point((7*size + size/2)/9, (9*size + size/2)/9));
	}
	
	@Test
	public void setLeftTest()
	{
		IslandMap im = new IslandMap(9);
		im = new IslandMap(Constants.ITEM_DIMENTION);
		im.loadIcons(Constants.RESOURCE_ISLAND_SCENARIO_MAP_ICONS);
		im.loadMap(Constants.RESOURCE_ISLAND_SCENARIO_MAP);
		im.loadCastleMap(Constants.RESOURCE_ISLAND_SCENARIO_CASTLE_MAP);
		Actor ac = new Pirate(im);
		ac.setTilePosition(16, 16);
		GameController gc = new GameController();
		gc.update();
		assertEquals(ac.getMoving(), false);
		ac.setLeft();
		assertEquals(ac.getLeft(), true);
		ac.setLeft();
		assertEquals(ac.getMoving(), true);
		gc.update();
		assertEquals(ac.getMoving(), false);		
		ac.setTilePosition(16, 1);
		ac.setLeft();
		assertEquals(ac.getLeft(), true);
	}
	
	@Test
	public void setRightTest()
	{
		IslandMap im = new IslandMap(9);
		im = new IslandMap(Constants.ITEM_DIMENTION);
		im.loadIcons(Constants.RESOURCE_ISLAND_SCENARIO_MAP_ICONS);
		im.loadMap(Constants.RESOURCE_ISLAND_SCENARIO_MAP);
		im.loadCastleMap(Constants.RESOURCE_ISLAND_SCENARIO_CASTLE_MAP);
		Actor ac = new Pirate(im);
		ac.setTilePosition(16, 16);
		GameController gc = new GameController();
		gc.update();
		assertEquals(ac.getMoving(), false);
		ac.setRight();
		assertEquals(ac.getRight(), true);
		ac.setRight();
		assertEquals(ac.getMoving(), true);
	}
	
	@Test
	public void setUpTest()
	{
		IslandMap im = new IslandMap(9);
		im = new IslandMap(Constants.ITEM_DIMENTION);
		im.loadIcons(Constants.RESOURCE_ISLAND_SCENARIO_MAP_ICONS);
		im.loadMap(Constants.RESOURCE_ISLAND_SCENARIO_MAP);
		im.loadCastleMap(Constants.RESOURCE_ISLAND_SCENARIO_CASTLE_MAP);
		Actor ac = new Pirate(im);
		ac.setTilePosition(16, 16);
		GameController gc = new GameController();
		gc.update();
		assertEquals(ac.getMoving(), false);
		ac.setUp();
		assertEquals(ac.getUp(), true);
		ac.setUp();
		assertEquals(ac.getMoving(), true);
	}
	
	@Test
	public void setDownTest()
	{
		IslandMap im = new IslandMap(9);
		im = new IslandMap(Constants.ITEM_DIMENTION);
		im.loadIcons(Constants.RESOURCE_ISLAND_SCENARIO_MAP_ICONS);
		im.loadMap(Constants.RESOURCE_ISLAND_SCENARIO_MAP);
		im.loadCastleMap(Constants.RESOURCE_ISLAND_SCENARIO_CASTLE_MAP);
		Actor ac = new Pirate(im);
		ac.setTilePosition(16, 16);
		GameController gc = new GameController();
		gc.update();
		assertEquals(ac.getMoving(), false);
		ac.setDown();
		assertEquals(ac.getDown(), true);
		ac.setDown();
		assertEquals(ac.getMoving(), true);
	}
	
	@Test
	public void toPositionTest()
	{
		IslandMap im = new IslandMap(9);
		Actor ac = new Pirate(im);
		assertEquals(ac.toPosition(7, 9), new Point( 7 / 9, 9 / 9 ));
	}
	
	@Test
	public void validateNextPositionTest()
	{
		IslandMap im = new IslandMap(9);
		im = new IslandMap(Constants.ITEM_DIMENTION);
		im.loadIcons(Constants.RESOURCE_ISLAND_SCENARIO_MAP_ICONS);
		im.loadMap(Constants.RESOURCE_ISLAND_SCENARIO_MAP);
		im.loadCastleMap(Constants.RESOURCE_ISLAND_SCENARIO_CASTLE_MAP);
		Actor ac = new Pirate(im);
		ac.setTilePosition(16, 1);
		GameController gc = new GameController();
		//assertEquals(ac.getLeft(), new Point( 7 / 9, 9 / 9 ));
	}
}
