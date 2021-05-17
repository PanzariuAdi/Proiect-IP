package client;

import org.json.JSONObject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
     System.out.println(ClientCommunicationHandler.getCalorii("321"));
        System.out.println(ClientCommunicationHandler.getPasi("321"));
        System.out.println(ClientCommunicationHandler.getPuls("321"));
        System.out.println(ClientCommunicationHandler.getCalitateSomn("321"));
        System.out.println(ClientCommunicationHandler.getNivelOxigen("321"));



//        System.out.println(CCH.getDiagnostic("user"));
//        JSONObject jsonObject=  CCH.getDiagnostic("user");
//        System.out.println(jsonObject);
//        System.out.println(jsonObject.get("diagnostic"));
//        System.out.println(CCH.getPacient("user"));

    }
}
