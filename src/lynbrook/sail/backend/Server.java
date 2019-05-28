package lynbrook.sail.backend;
import java.io.*;
import java.net.*;

public class Server
{
    private int totalPlayer;
    private ServerSocket server;
    private boolean fullPlayer;
    public static final int PORT = 8080;
    
    public Server() {
        System.out.print( "SERVER" );
        totalPlayer=0;
        fullPlayer=true;
        try
        {
            server= new ServerSocket(123456);
        }
        catch ( IOException e )
        {
            // TODO Auto-generated catch block
            System.out.println("IOEXception server") ;
        }
    }
    
    public void connecting() {
        System.out.println("connecting...");
        if(fullPlayer) {
    
            System.out.println("No more PLAYERS!!!");
        }
        if(!fullPlayer) {
            while(totalPlayer<2) {
                try
                {
                    Socket temp=server.accept();
                }
                catch ( IOException e )
                {
                    // TODO Auto-generated catch block
                    System.out.println("IOEXception server") ;
                }
            }
                }
                totalPlayer++;
        }
    
    
    public void run() {
        
    }
      
    public static void main(String[] args)
         throws IOException{
             ServerSocket s = new ServerSocket(PORT);
             System.out.println( "Started: " + s );
             try {
                 Socket socket = s.accept();
                 try {
                     System.out.println( "Connection accepted: "+ socket );
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true));
                     Player player = new Player();
                     player.set( out );
                     while(true)
                     {
                         String str = in.readLine();
                         
                         
                         player.processSocketMessage(str, out);
                     }
                     
                 }finally {
                     System.out.println( "closing..." );
                     socket.close();
                 }
                 
                 
             
         }finally {
            s.close();
         }
         
    }
    

    

}
