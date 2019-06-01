package lynbrook.sail.data;

/**
 * The different Constants that the program uses throughout its classes
 *
 * @author Carol
 * @version May 31, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: none
 */
public class Constants
{
	public static final int SCENARIO_BEGIN = 0;

	public static final int SCENARIO_ISLAND = 1;

	public static final int SCENARIO_BATTLE_FIELD = 2;

	public static final int SCENARIO_RESULT = 3;

	public static final int DOWN = 2;

	public static final int LEFT = 1;

	public static final int RIGHT = 3;

	public static final int UP = 0;

	public static final int WATER = 0;

	public static final int LAND = 1;

	public static final int MIXED = 8;

	public static final int CASTLE = 9;

	public static final int ROLE_KING = 0;

	public static final int ROLE_PIRATE = 1;

	public static final int ITEM_DIMENTION = 32;

	public static final int PLAYER_DIMENTION = 32;

	public static final int PLAYER_MOVE_SPEED = 8;

	public static final int PLAYER_INITIAL_POSITION = 16;

	public static final int WIDTH = 1280;

	public static final int HEIGHT = 1280;

	public static final int KING_HEALTH = 500;

	public static final int PIRATE_HEALTH = 100;

	public static final int BOMB_DAMAGE = 20;

	public static final int FPS = 30;

	public static final int TARGET_TIME = 1000 / FPS;

	public static final long SERIAL_VERSION_NUMBER = 100L;

	public static final int DEFAULT_ANIMATION_DELAY_FRAMES = 2;

	public static final int PIRATE_VS_KING_PORT = 3456;

	// the server's ip address for the pirate to connect. the pirate needs to
	// temporarily change this.
	public static final String PIRATE_VS_KING_IP_ADDRESS = "192.168.0.73";

	public static final String RESOURCE_ISLAND_SCENARIO_MAP = "/island_map.txt";

	public static final String RESOURCE_FRONT_PAGE_SCENARIO = "/front_page.png";

	public static final String RESOURCE_KING = "/king.png";

	public static final String RESOURCE_PIRATE = "/pirate.png";

	public static final String RESOURCE_BOAT = "/boat.png";

	public static final String RESOUCE_BOMB_SOUND = "/bomb.wav";

	public static final String RESOURCE_CANNON = "/cannon.png";

	public static final String RESOURCE_CANNON_BALL = "/cannon_ball.png";

	public static final String RESOURCE_EXPLOSION = "/explosion.png";

	public static final String RESOURCE_WATER = "/ocean_water.png";

	public static final String RESOURCE_CASTLE_WALL = "/castle_wall.png";

	public static final String RESOURCE_INSTRUCTION_PAGE_SCENARIO = "/instruction_page.png";

	public static final String RESOURCE_ISLAND_SCENARIO_MAP_ICONS = "/map_icons.jpg";

	public static final String RESOURCE_ISLAND_SCENARIO_CASTLE = "/castle_land.png";

	public static final String RESOURCE_ISLAND_SCENARIO_CASTLE_MAP = "/island_map.jpg";

	public static final String RESOURCE_RESULT_WON = "/won.png";

	public static final String RESOURCE_RESULT_LOST = "/lost.png";
}