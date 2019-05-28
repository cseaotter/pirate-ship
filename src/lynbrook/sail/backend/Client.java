package lynbrook.sail.backend;

import java.io.*;
import java.net.*;
public class Client {
    private Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;


    public Client(){
        System.out.println("     < Client >     ");
        try{
            s= new Socket("Lucys-macbook-air-3.local", 8767);
            dis=new DataInputStream(s.getInputStream());
            dos= new DataOutputStream(s.getOutputStream());
            System.out.println("success");
        } catch (IOException x){
            x.printStackTrace();
            // System.out.println("client IOexception ");

        }
    }
    public static void main (String argv []){
        Client c= new Client();

    }

}

