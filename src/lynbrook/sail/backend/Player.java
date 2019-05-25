package lynbrook.sail.backend;

import java.io.OutputStream;
import java.io.PrintWriter;

import lynbrook.sail.actor.King;
import lynbrook.sail.actor.Pirate;
import lynbrook.sail.backend.Role.RoleName;
import lynbrook.sail.gui.IslandMap;

public class Player
{
	private Role role;
	// private UIClass ui;
	private GameEngine gameEngine; // running on socket server side

	// process received messages.
	// this player class runs on both computers
	// Socket Client sends message as output, socket Server receives the same
	// message as input
	// When socket server sends a msg as output, socket client receives it as input
	// Socket is only used to convey message to the other computer:
	// 1. react
	// 2. update UI
	public void processSocketMessage(String message, PrintWriter output)
	{
		// if message in format of "client:choose:<role>:<timestamp>"
		// parse timestamp
		long clientChoiceTimeStamp = 2349239;
		// strip message to "client:choose:<role>"
		switch (message)
		{
		case "player:choose:role": // this message is sent by GameEngine or GameRule class from SocketServer
			// Socket client process this message
			// ui.displayRoleChoices()
		case "client:choose:pirate":
			// After socket client's computer make a role choice, it sends msg to socket
			// server
			gameEngine.takeClientChoice(RoleName.pirate, clientChoiceTimeStamp, this);
			break;
		case "client:choose:king":
			gameEngine.takeClientChoice(RoleName.king, clientChoiceTimeStamp, this);
			break;
		case "client:becomes:pirate":
			// TODO:
			role = new Pirate(new IslandMap(40));
			// UI draws island (client side)
			break;
		case "client:becomes:king":
			// TODO:
			role = new King(new IslandMap(40));
			// UI draws island (client side)
			break;
		case "pirate:attack:island-1": // Format: <RoleName>:<action>:<details>
			// king player computer will show "surrend" or "accept" choices.
			// ui.displayPopupWithTwoChoices()
			break;
		case "king:accept:battle:request":
			// pirate player computer will show ship and castle
			// ui.displayShipAndCastle()
			break;
		case "king:surrend":
			// ui.displayEntireMap()
			break;

		case "pirate:moveship:location:co-ord":
			// ui.updateShipLocation(co-ord)
			break;
		case "pirate:bullet:location:co-ord":
			// ui.updateBulletLocation(pirate, co-ord)
			break;
		case "king:bullet:location:co-ord":
			// ui.updateBulletLocation(pirate, co-ord)
			break;
		}

		return;

	}

	public void assignRole(Role.RoleName roleName)
	{
		if (roleName == Role.RoleName.king)
		{
			// TODO:
			role = new King(new IslandMap(40));
		} else if (roleName == Role.RoleName.pirate)
		{
			// TODO:
			role = new Pirate(new IslandMap(40));
		} else
		{
			throw new IllegalArgumentException();
		}
	}

}
