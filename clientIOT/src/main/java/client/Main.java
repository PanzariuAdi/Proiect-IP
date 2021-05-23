package client;

import org.json.JSONObject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
     ClientCommunicationHandler.sendData("user","89","4213","3222");



//        System.out.println(CCH.getDiagnostic("user"));
//        JSONObject jsonObject=  CCH.getDiagnostic("user");
//        System.out.println(jsonObject);
//        System.out.println(jsonObject.get("diagnostic"));
//        System.out.println(CCH.getPacient("user"));

    }
}
