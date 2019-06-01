package lynbrook.sail.network;

import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;
import java.util.TreeMap;

import lynbrook.sail.data.Constants;

/**
 * 
 * Running the networking
 *
 * @author Lucy
 * @version May 31, 2019
 * @author Period: 3
 * @author Assignment: pirateShip
 *
 * @author Sources: none
 */
public class NetworkRunnable implements Runnable
{

	private DataUpdate dataUpdate;

	private Map<Integer, PlayerData> playerMap;

	private static Socket client = null;

	private static ServerSocket server = null;

	private int role;

	private String address;

	/**
	 * Constructs NetworkRunnable and sets the role, address, and dataUpdate
	 * 
	 * @param role       the role
	 * @param address    the address
	 * @param dataUpdate the dataUpdate
	 */
	public NetworkRunnable(int role, String address, DataUpdate dataUpdate)
	{
		this.role = role;
		this.address = address;
		this.dataUpdate = dataUpdate;
		playerMap = new TreeMap<>();

	}

	/**
	 * runs the loop(another method) and catches exceptions
	 */
	@Override
	public void run()
	{
		try
		{
			loop();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * Facilitates the connecting of the client and server Gets the objects which
	 * are sent from the socket
	 * 
	 * @throws IOException            the IOException
	 * @throws ClassNotFoundException the ClassNotFoundException
	 * @throws SocketException        the SocketException
	 */
	private void loop() throws IOException, ClassNotFoundException, SocketException
	{
		if (role == Constants.ROLE_KING)
		{
			server = new ServerSocket(Constants.PIRATE_VS_KING_PORT);
			client = server.accept();
		} else
		{
			client = new Socket(address, Constants.PIRATE_VS_KING_PORT);

		}

		while (client.isConnected())
		{
			initCurrentRoleIfNecessary();
			long start = System.currentTimeMillis();
			ObjectOutputStream sendObj = new ObjectOutputStream(client.getOutputStream());

			PlayerData player = playerMap.get(role);
			int scenario = dataUpdate.getScenario();
			if (player != null)
			{

				player.setScenario(scenario);
				if (scenario == Constants.SCENARIO_ISLAND)
				{
					Point point = dataUpdate.getCurrentLocation();
					player.setPoint(point);
				} else if (scenario >= Constants.SCENARIO_BATTLE_FIELD)
				{
					player.setWeapon(dataUpdate.getBattleData());
				}
			}
			sendObj.writeObject(playerMap.get(role));
			sendObj = null;
			ObjectInputStream getObj = new ObjectInputStream(client.getInputStream());
			PlayerData remotePlayer = (PlayerData) getObj.readObject();
			getObj = null;
			boolean needUpdate = false;
			if (remotePlayer != null)
			{
				if (!remotePlayer.equals(playerMap.get(remotePlayer.getRole())))
				{
					playerMap.put(remotePlayer.getRole(), remotePlayer);
					needUpdate = true;
				}
			}
			if (needUpdate)
			{
				dataUpdate.onUpdateData(playerMap);
			}
			waitIfNecessary(start);
		}
	}

	/**
	 * Adds the playerData into the map if data update is not null or playerMap
	 * already contains the key
	 */
	private void initCurrentRoleIfNecessary()
	{
		if (dataUpdate == null || playerMap.containsKey(role))
		{
			return;
		}
		PlayerData playerData = new PlayerData(dataUpdate.getCurrentLocation(), role);
		playerMap.put(role, playerData);
	}

	/**
	 * Waits to save some CPU
	 * 
	 * @param start the time when it started to wait
	 */
	private void waitIfNecessary(long start)
	{

		long elapsed = System.currentTimeMillis() - start;

		long wait = Constants.TARGET_TIME - elapsed / 1000;
		if (wait < 0)
			wait = Constants.TARGET_TIME;

		try
		{
			Thread.sleep(wait);
		} catch (Exception e)
		{

		}
	}

}
