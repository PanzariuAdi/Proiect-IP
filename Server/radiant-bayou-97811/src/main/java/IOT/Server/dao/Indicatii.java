package IOT.Server.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Indicatii implements DAO{
    private final Database database = Database.getInstance();
    @Override
    public void insertIntoDB(String CNP, String... args) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {

    }

    @Override
    public void removeFromDB(String documentID) throws InterruptedException, ExecutionException {

    }

    @Override
    public void updateInDB(String documentID, String field, String value) throws InterruptedException, ExecutionException {

    }

    @Override
    public List<Map<String, Object>> getDocumentByField(String field, String value) throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Map<String, Object> getDocumentByID(String documentID) throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public List<Map<String, Object>> getCollection() throws InterruptedException, ExecutionException {
        return null;
    }
    public List<String> getCollection(String cnp) throws InterruptedException, ExecutionException {
        List<String> result = new ArrayList<>();
        Iterable<DocumentReference> collection = database.db.collection("pacient").document(cnp).collection("indicatii").listDocuments();
        for (DocumentReference docRef : collection) {
            ApiFuture<DocumentSnapshot> getDataApi = docRef.get();
            DocumentSnapshot documentData = getDataApi.get();
            result.add((String) documentData.getData().get("descriere"));
        }
        return result;
    }

    @Override
    public Map<String, Object> getSortedMap(Map<String, Object> map) {
        return null;
    }
}
