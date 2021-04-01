package com.company;
import java.net.*;
public class Server {
    public static void main(String arg[]){
        try {
            ServerSocket server = new ServerSocket(3000);
            Socket s = server.accept();

            System.out.println("Connected");
        }catch(Exception e){}
    }
}
