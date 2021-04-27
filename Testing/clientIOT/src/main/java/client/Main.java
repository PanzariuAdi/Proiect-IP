package client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
      CCH.sendData("user2", "2 March 2021 17:00","70","3000","6000","4.5","45");
        System.out.println(CCH.getDiagnostic("user"));
        //System.out.println(CCH.login("usr","pass"));

    }
}
