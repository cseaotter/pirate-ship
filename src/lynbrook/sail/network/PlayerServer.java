package lynbrook.sail.network;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class PlayerServer
{
	private ServerSocket s;
	private int numPlayer;
	private int portNum = 8767;
	private Server player1;
	private Server player2;

	public PlayerServer()
	{
		System.out.println("     < Player Server >     ");
		numPlayer = 0;
		try
		{
			s = new ServerSocket(portNum);
			System.out.println("success");

		} catch (IOException x)
		{
			x.printStackTrace();
			// System.out.println("Gameserver IOExcpetion ");
		}
	}

	public void connecting()
	{
		try
		{
			System.out.println("waiting for new player......");

			while (numPlayer < 2)
			{
				Socket socket = s.accept();
				numPlayer++;
				System.out.println("player" + numPlayer + " connected");
				Server se = new Server(socket, numPlayer);
				if (numPlayer == 1)
				{
					player1 = se;
				} else
				{
					player2 = se;
				}
				Thread temp = new Thread(se);
				temp.start();
			}
			System.out.println("let's start the game");
		} catch (IOException x)
		{
			System.out.println("IOexcpetion from connecting");
		}
	}
}
