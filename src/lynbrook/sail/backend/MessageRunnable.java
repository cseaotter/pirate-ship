package lynbrook.sail.backend;

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

public class MessageRunnable implements Runnable
{

	private PlayerDataService playerDataService;

	//private Map<Integer, PlayerData> playerMap;

	private static Socket socketClient = null;

	private static ServerSocket server = null;

	private int role;

	private String address;
	
	boolean isSocketServer;

	public MessageRunnable(String address, PlayerDataService dataUpdate, boolean isSocketServer)
	{
	    
		this.isSocketServer = isSocketServer;
		this.address = address;
		this.playerDataService = dataUpdate;
		//playerMap = new TreeMap<>();

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
		if (isSocketServer)
		{
			server = new ServerSocket(Constants.PIRATE_VS_KING_PORT);
			socketClient = server.accept();
		} else
		{
			socketClient = new Socket(address, Constants.PIRATE_VS_KING_PORT);

		}
		PlayerData currentPlayerData = new PlayerData();
		
		
		while (socketClient.isConnected())
		{
			initCurrentRoleIfNecessary();
			long start = System.currentTimeMillis();
			ObjectOutputStream outputStream = new ObjectOutputStream(socketClient.getOutputStream());
			Point point = playerDataService.getCurrentLocation();
			
			currentPlayerData.setPoint(point);
			outputStream.writeObject(currentPlayerData);
			outputStream = null;
			ObjectInputStream inputStream = new ObjectInputStream(socketClient.getInputStream());
			PlayerData remotePlayerData = (PlayerData) inputStream.readObject();
			inputStream = null;
			//playerMap.put(remotePlayerData.getRole(), remotePlayerData);
			playerDataService.onUpdateData(currentPlayerData, remotePlayerData);
			
			waitIfNecessary(start);
		}
	}

	private void initCurrentRoleIfNecessary()
	{
	    /*
		if (playerDataService == null || playerMap.containsKey(role))
		{
			return;
		}
		PlayerData playerData = new PlayerData(playerDataService.getCurrentLocation(), "test ", role);
		playerMap.put(role, playerData);
		*/
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
