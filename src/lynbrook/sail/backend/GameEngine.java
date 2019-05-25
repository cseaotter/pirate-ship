package lynbrook.sail.backend;

import java.io.IOException;
import java.io.OutputStream;

//this class runs in socketServer only
public class GameEngine
{

	private OutputStream output;

	private Role.RoleName clientRoleRequested;
	private long clientRoleTimestamp;
	private Role.RoleName serverRoleRequested;
	private long serverRoletimestamp;

	public void showPirateKingRoleChoices()
	{
		// ui.displayroleChoicsScreen();
		sendMessage("client:choose:role");
	}

	public void takeClientChoice(Role.RoleName roleNameRequested, long clientChoiceTimeStamp, Player player)
	{
		clientRoleRequested = roleNameRequested;
		clientRoleTimestamp = clientChoiceTimeStamp;
		if (canMakeDecision())
		{
			makeRoleDecision(player);
		}

	}

	public void takeServerChoice(Role.RoleName roleNameRequested, long clientChoiceTimeStamp, Player player)
	{
		// similar to above but from the UI
		clientRoleRequested = roleNameRequested;
		clientRoleTimestamp = clientChoiceTimeStamp;
		if (canMakeDecision())
		{
			makeRoleDecision(player);
		}

	}

	private boolean canMakeDecision()
	{
		return clientRoleRequested != null && serverRoleRequested != null;
	}

	private void makeRoleDecision(Player player)
	{
		// compare timestamp and request
		// make decision
		if (clientRoleRequested.equals(serverRoleRequested))
		{
			if (clientRoleTimestamp > serverRoletimestamp)
			{
				player.assignRole(clientRoleRequested);
				sendMessage("client:becomes:" + clientRoleRequested);
			} else
			{
				player.assignRole(serverRoleRequested);
				sendMessage("client:becomes:" + serverRoleRequested);
			}

		} else
		{
			player.assignRole(serverRoleRequested);
			sendMessage("client:becomes:" + clientRoleRequested);
		}
		// piece of code on server
		// draw map of island

		// ex.

		player.assignRole(Role.RoleName.king);

	}

	private void sendMessage(String msg)
	{
		try
		{
			output.write(msg.getBytes());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
