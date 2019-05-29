package lynbrook.sail.backend;

import java.awt.Point;
import java.io.OutputStream;
import java.io.PrintWriter;

import lynbrook.sail.backend.Role.RoleName;
import lynbrook.sail.controller.GameController;


/**
 * 
 *  TODO Write a one-sentence summary of your class here.
 *  TODO Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 *
 *  @author  Elaine Ye
 *  @version May 24, 2019
 *  @author  Period: 3
 *  @author  Assignment: Island Battle
 *
 *  @author  Sources: none
 */
public class SocketMessageService implements PlayerDataService
{
    //private Role role;
    private UI ui;
    private GameEngine gameEngine; //running on socket server side
    private PrintWriter out;
    private GameController gameController;
    
    // process received messages.
    // this player class runs on both computers
    // Socket Client sends message as output,  socket Server receives the same message as input
    // When socket server sends a msg as output, socket client receives it as input
    // Socket is only used to convey message to the other computer:
    //  1. react
    //  2. update UI
    public void onUpdateData( PlayerData localPlayerData, PlayerData remotePlayerData ) {
        //if message in format of "client:choose:<role>:<timestamp>"
        // parse timestamp
        long clientChoiceTimeStamp = 2349239;
        //strip message to "client:choose:<role>"
       switch (remotePlayerData.getMessage()) {
           case "client:choose:role" : // this message is sent by GameEngine or GameRule class from SocketServer
             //  Socket client process this message
               ui.displayChooseRole(); // integrating UI   
               gameController.switchScenario( GameController.SCENARIO_BEGIN );
               sendMessage(localPlayerData, "client:choose:pirate"); //this is only for testing
               break;
           case "client:choose:pirate" :
             // After socket client's computer make a role choice, it sends msg to socket server
             gameEngine.takeClientChoice(RoleName.pirate, clientChoiceTimeStamp, this);
             break;
           case "client:choose:king" :
               gameEngine.takeClientChoice(RoleName.king, clientChoiceTimeStamp, this);
               break;
           case "client:becomes:pirate" :
               remotePlayerData.setRole( PlayerData.ROLE_PIRATE );
               //UI draws island (client side)
               break;
           case "client:becomes:king" :
               remotePlayerData.setRole( PlayerData.ROLE_KING);
               //UI draws island (client side)
               break;
               /*
           case "king:lost":
               //ui.gameOverScreen();
               //ui.displayIslandMap();
               break;
           case "pirate:lost":
               //ui.gameOverScreen();
               //ui.displayIslandMap();
               break;
           case "pirate:attack:island-1":   //Format: <RoleName>:<action>:<details>
             // king player computer will show "surrend" or "accept" choices.  
             //  ui.displayPopupWithTwoChoices()
             break;
             */
           case "king:accept":
             // pirate player computer will show ship and castle
             // ui.displayShipAndCastle()
             break;
           
           case "king:surrend":
              // ui.displayIslandMap() 
             break;
           /*
           case "pirate:moveship:co-ord" :
              //ui.updateShipLocation(co-ord)
               break;
           case "pirate:bullet:co-ord" :     
               //ui.updateBulletLocation(pirate, co-ord)
               break;
           case "king:bullet:co-ord" :
               //ui.updateBulletLocation(pirate, co-ord)
               break;
               */
           default:
               defaultprocess(localPlayerData, remotePlayerData, remotePlayerData.getMessage());
               break;
               
       }
       
       
           
       return;
        
    }
    
    public void defaultprocess(PlayerData localPlayerData, PlayerData remotePlayerData, String msg)
    {
        //getting roles, what the message said it did, and details
        
        String action = "";
        int firstcolon = msg.indexOf( ":" );
        String playerRole = msg.substring( 0, firstcolon );
        int playerRoleInt = 0;
        if (playerRole.equals( "pirate" ))
        {
            playerRoleInt = PlayerData.ROLE_PIRATE;
        }
        else
        {
            playerRoleInt = PlayerData.ROLE_KING;
        }
        msg = msg.substring( firstcolon+1 );
        int secondcolon = msg.indexOf( ":" );
        if (secondcolon != -1)
        {
            action = msg.substring( firstcolon, secondcolon );
            msg = msg.substring( secondcolon );
        }
        else
        {
            action = msg;
        }
        

        // testing what to do with what message said
        //updating what happened on the other screen
        if (action.equals("shoot") && localPlayerData.getRole() == playerRoleInt ) // pirate shoot x,y
        {
            String strx = msg.substring( 0, msg.indexOf( "," )); //from message
            String stry = msg.substring( msg.indexOf( "," ) + 1); //from message
            
            int x = Integer.parseInt(strx);   
            int y = Integer.parseInt(stry);
            
            ui.drawExplosion(x, y);
            //calcAndUpdateHealth(x, y); TODO
            
            sendMessage(remotePlayerData, playerRole + ":updatehealth:" + localPlayerData.getHealth());
            if (localPlayerData.getHealth() < 0)
            {
                if (gameEngine == null)//client
                {
                    sendMessage(remotePlayerData, localPlayerData.getRoleString() + ":lostloc"); 
                    
                }
                else
                {
                    gameEngine.whoWonBattle(playerRole, msg);
                    if (gameEngine.isWinner())
                    {
                        ui.displayGameFinished(gameEngine.whoWonGame() );
                        sendMessage(remotePlayerData, localPlayerData.getRoleString()  + ":won"); 
                    }
                    sendMessage(remotePlayerData, localPlayerData.getRoleString()  + ":lostBattle"); 
                }
                ui.displayIslandMap( msg );
            }
            
        }
        else if(action.equals("updatehealth") && localPlayerData.getRole() == playerRoleInt) // pirate updatehealth point
        {
            ui.updateHealth( 5, localPlayerData.getRoleString() );
            
        }
        else if (action.equals( "attack" ) && localPlayerData.getRoleString().equals("pirate")) //message should be something like pirate:attack:loc1
        {
            gameEngine.whichLoc(msg);
            ui.displayPopupWithTwoChoices();
        }
        
        else if(action.equals("moveship") && localPlayerData.getRoleString().equals( "pirate" ))
        {
            String strx = msg.substring( 0, msg.indexOf( "," )); //from message
            String stry = msg.substring( msg.indexOf( "," ) + 1); //from message
            
            int x = Integer.parseInt(strx);   
            int y = Integer.parseInt(stry);
            
            ui.updateShipLocation(x, y);
        }
        else if (action.equals("lostloc"))
        {
            
            gameEngine.whoWonBattle(playerRole, gameEngine.getLoc());
            if (gameEngine.isWinner())
            {
                ui.displayGameFinished(gameEngine.whoWonGame() );
                sendMessage(remotePlayerData, localPlayerData.getRoleString() + ":won"); 
            }
            else
            {
                ui.displayIslandMap( msg );
            }
            
            
        }
        else if (action.equals( "lostBattle" ) && !localPlayerData.getRoleString().equals( playerRole ))
        {
            ui.displayIslandMap( msg );
        }
        else if (action.equals( "won" ) )
        {
            ui.displayGameFinished(gameEngine.whoWonGame() );
        }
        //Ui has to send message to socket server for shooting and "attack" the location
      
        
        
        /*
        else if(action.equals("bullet"))
        {
            String strx = msg.substring( 0, msg.indexOf( "," )); //from message
            String stry = msg.substring( msg.indexOf( "," ) + 1); //from message
            
            int x = Integer.parseInt(strx);   
            int y = Integer.parseInt(stry);

            ui.updateBulletLocation( x, y );
            
            
        }
        */
        
    }
    
    public GameEngine getGameEngine()
    {
        return gameEngine;
    }

    

    public void sendMessage(PlayerData localPlayerData, String str)
    {
        System.out.println("sending message: " + str); // this is for testing
        localPlayerData.setMessage( str );
        
        //UI method calls this method and the parameter is "pirate:shoot:(coordinates)" or moveShip e.t.c...
    }
    
    public void set(PrintWriter output)
    {
        out = output;
    }
    
    

    @Override
    public Point getCurrentLocation()
    {
        // TODO Auto-generated method stub
        return null;
    }

 
 
    
    
}
