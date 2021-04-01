package com.company;
import java.net.*;
public class Client {
    public static void main(String arg[]){
        try {
            Socket s = new Socket("127.0.0.1", 3000);
            System.out.println("Connected");
        }catch (Exception e){}
    }
}
