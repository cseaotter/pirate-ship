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

import lynbrook.sail.backend.DataUpdate;
import lynbrook.sail.data.Constants;

public class MessageRunnable implements Runnable
{

	private DataUpdate dataUpdate;

	private Map<Integer, PlayerData> playerMap;

	private static Socket client = null;

	private static ServerSocket server = null;

	private int role;

	private String address;

	public MessageRunnable(int role, String address, DataUpdate dataUpdate)
	{
		this.role = role;
		this.address = address;
		this.dataUpdate = dataUpdate;
		playerMap = new TreeMap<>();

	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		try
		{
			loop();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void loop() throws IOException, ClassNotFoundException, SocketException
	{
		if (role == PlayerData.ROLE_KING)
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
			Point point = dataUpdate.getCurrentLocation();
			PlayerData player = playerMap.get(role);
			player.setPoint(point);
			sendObj.writeObject(playerMap.get(role));
			sendObj = null;
			ObjectInputStream getObj = new ObjectInputStream(client.getInputStream());
			PlayerData remotePlayer = (PlayerData) getObj.readObject();
			getObj = null;
			playerMap.put(remotePlayer.getRole(), remotePlayer);
			dataUpdate.onUpdateData(playerMap);
			waitIfNecessary(start);
		}
	}

	private void initCurrentRoleIfNecessary()
	{
		if (dataUpdate == null || playerMap.containsKey(role))
		{
			return;
		}
		PlayerData playerData = new PlayerData(dataUpdate.getCurrentLocation(), "test ", role);
		playerMap.put(role, playerData);
	}

	private void waitIfNecessary(long start)
	{

		long elapsed = System.nanoTime() - start;

		long wait = Constants.TARGET_TIME - elapsed / 1000000;
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
