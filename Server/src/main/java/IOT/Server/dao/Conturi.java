package IOT.Server.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.*;
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
    public String getToken(String user) throws InterruptedException, ExecutionException {
        DocumentReference getData = database.db.collection("conturi").document(user);
        ApiFuture<DocumentSnapshot> getDataApi = getData.get();
        DocumentSnapshot documentData = getDataApi.get();

        if (documentData.exists())
            return documentData.getData().get("token").toString();

        return null;
    }

    public void insertToken(String username, String token) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {
        Map<String, Object> dataToInsert = new HashMap<>();

        dataToInsert.put("token", token);

        ApiFuture<WriteResult> insertData = database.db.collection("conturi").document(username).update("token",token);

        dataToInsert.clear();

        insertData.get();
        System.out.println("Token Inserted!");
    }
    public String getUsernameByCNP(String cnp) throws ExecutionException, InterruptedException {
        CollectionReference collection = database.db.collection("conturi");
        Query query = collection.whereEqualTo("cnp", cnp);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            return document.getId();
        }
        return null;
    }
}