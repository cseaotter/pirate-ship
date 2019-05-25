package lynbrook.sail.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Server implements Runnable
{
	private Socket s;
	private DataInputStream is;
	private DataOutputStream os;
	private int playerNum;

	public Server(Socket so, int pN)
	{
		s = so;
		playerNum = pN;

		try
		{
			is = new DataInputStream(s.getInputStream());

			os = new DataOutputStream(s.getOutputStream());

		} catch (IOException x)
		{
			System.out.println("Server constructor IOException");

		}
	}

	public void run()
	{

		try
		{
			os.writeInt(playerNum);
			os.flush();
		} catch (IOException k)
		{
			System.out.println("Server run method IOException");

		}
	}

	public static void main(String argv[])
	{
		PlayerServer ps = new PlayerServer();
		ps.connecting();

	}

}
