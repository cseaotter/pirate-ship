package lynbrook.sail.backend;

import java.io.OutputStream;

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
//this class runs in socketServer only
public class GameEngine
{
   
    private OutputStream output;
    
    private Role.RoleName clientRoleRequested;
    private long clientRoleTimestamp;
    private Role.RoleName serverRoleRequested;
    private long serverRoletimestamp;
    
    private String[] winner = new String[3];
    private String loc;
    
    
    public void showPirateKingRoleChoices() {
       //ui.displayroleChoicsScreen();
        ( output ).sendMessage("client:choose:role");
    }
    
   public void takeClientChoice(Role.RoleName roleNameRequested, long clientChoiceTimeStamp, SocketMessageService player ) {
       clientRoleRequested = roleNameRequested;
       clientRoleTimestamp = clientChoiceTimeStamp;
       if (canMakeDecision()) {
           makeRoleDecision(player);
       }
       
   }
   
   public void takeServerChoice(Role.RoleName roleNameRequested, long clientChoiceTimeStamp, SocketMessageService player) {
       //similar to above but from the UI
       clientRoleRequested = roleNameRequested;
       clientRoleTimestamp = clientChoiceTimeStamp;
       
       if (canMakeDecision()) {
           makeRoleDecision(player);
       }
       
   }
   
   private boolean canMakeDecision() {
      return clientRoleRequested != null && serverRoleRequested != null;
   }
   
   private void makeRoleDecision(PlayerData playerData) {
       //compare timestamp and request
       // make decision
       if (clientRoleRequested.equals( serverRoleRequested ))
       {
           if (clientRoleTimestamp > serverRoletimestamp)
           {
               playerData.setRoleString( clientRoleRequested.toString() );
               playerData.setMessage("client:becomes:" + clientRoleRequested);
               System.out.println( clientRoleRequested);
           }
           else
           {
               playerData.setRoleString( serverRoleRequested.toString() );
               playerData.setMessage("client:becomes:" + serverRoleRequested); 
               System.out.println( clientRoleRequested);

           }
           
       }
       else
       {
           playerData.setRoleString( serverRoleRequested.toString() );
           playerData.setMessage("client:becomes:" + clientRoleRequested); 
           System.out.println( clientRoleRequested);

       }
       //peice of code on server
       //draw map of island
       
       
       
       //ex.
       
   }
   
   public void whoWonBattle(String win, String loc)
   {
       if (loc.equals( "loc1" ))
       {
           winner[0] = win;
       }
       else if (loc.equals( "loc2" ))
       {
           winner[1] = win;
       }
       else
       {
           winner[2] = win;
       }
   }
   
   public boolean isWinner()
   {
       boolean isWinner = true;
       int x = 0;
       for (int i = 0; i < winner.length; i++)
       {
           if (winner[i] == null)
           {
               isWinner = false;
           }
       }
       return isWinner;
   }
   public String whoWonGame()
   {
       int k = 0;
       int p = 0;
       for (int i = 0; i < winner.length; i++)
       {
           if (winner.equals( "pirate" ))
           {
               p++;
           }
           else
           {
               k++;
           }
       }
       if (p > k)
       {
           return "pirate";
       }
       return "king";
   }
   
   public void whichLoc(String l)
   {
       loc = l;
   }
   public String getLoc()
   {
       return loc;
   }
   
   
}
