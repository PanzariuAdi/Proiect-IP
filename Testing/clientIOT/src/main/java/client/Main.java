package client;

import org.json.JSONObject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
     System.out.println(ClientCommunicationHandler.getRol("maxim.marius76"));
     //System.out.println(ClientCommunicationHandler.getDiagnostic("user"));
     //System.out.println(ClientCommunicationHandler.getPacientByCNP("321"));
     System.out.println(ClientCommunicationHandler.getAsistent("acatrinei.ioana80"));
     System.out.println(ClientCommunicationHandler.getPacientList());
     System.out.println(ClientCommunicationHandler.getDoctor("maxim.marius76"));
     System.out.println(ClientCommunicationHandler.getCalitateSomn("321"));

//        System.out.println(CCH.getDiagnostic("user"));
//        JSONObject jsonObject=  CCH.getDiagnostic("user");
//        System.out.println(jsonObject);
//        System.out.println(jsonObject.get("diagnostic"));
//        System.out.println(CCH.getPacient("user"));

    }
}
