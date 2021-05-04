package IOT.Server.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class Conturi {
    private final Database database = Database.getInstance();

    public boolean checkIfUserExists(String user) throws InterruptedException, ExecutionException {
        DocumentReference getData = database.db.collection("conturi").document(user);
        ApiFuture<DocumentSnapshot> getDataApi = getData.get();
        DocumentSnapshot documentData = getDataApi.get();

        if (documentData.exists())
            return true;

        return false;
    }
    public String getPassword(String user) throws InterruptedException, ExecutionException {
        DocumentReference getData = database.db.collection("conturi").document(user);
        ApiFuture<DocumentSnapshot> getDataApi = getData.get();
        DocumentSnapshot documentData = getDataApi.get();
        if (documentData.getData() != null)
            return documentData.getData().get("password").toString();

        return null;
    }
    public String getRol(String user) throws InterruptedException, ExecutionException {
        DocumentReference getData = database.db.collection("conturi").document(user);
        ApiFuture<DocumentSnapshot> getDataApi = getData.get();
        DocumentSnapshot documentData = getDataApi.get();

        if (documentData.exists())
            return documentData.getData().get("rol").toString();

        return null;
    }
    public String getCNP(String user) throws InterruptedException, ExecutionException {
        DocumentReference getData = database.db.collection("conturi").document(user);
        ApiFuture<DocumentSnapshot> getDataApi = getData.get();
        DocumentSnapshot documentData = getDataApi.get();

        if (documentData.exists())
            return documentData.getData().get("cnp").toString();

        return null;
    }
}