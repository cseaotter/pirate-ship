package lynbrook.sail.network;

import java.io.*;
import java.net.*;

public class Server
{
	private int totalPlayer;
	private ServerSocket server;
	private boolean fullPlayer;

	public Server()
	{
		System.out.print("SERVER");
		totalPlayer = 0;
		fullPlayer = true;
		try
		{
			server = new ServerSocket(123456);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			System.out.println("IOEXception server");
		}
	}

	public void connecting()
	{
		System.out.println("connecting...");
		if (fullPlayer)
		{

			System.out.println("No more PLAYERS!!!");
		}
		if (!fullPlayer)
		{
			while (totalPlayer < 2)
			{
				try
				{
					Socket temp = server.accept();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					System.out.println("IOEXception server");
				}
			}
		}
		totalPlayer++;
	}

}
