package IOT.Server.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Conturi implements DAO{
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
        DocumentReference getData = database.db.collection("conturi").document(documentID);
        ApiFuture<DocumentSnapshot> getDataApi = getData.get();
        DocumentSnapshot documentData = getDataApi.get();

        Map<String, Object> resultData;
        if (documentData.exists()) {
            resultData = getSortedMap(documentData.getData());
            //database.disconnectFromDatabase();
            return resultData;
        }

        return null;
    }

    @Override
    public List<Map<String, Object>> getCollection() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Map<String, Object> getSortedMap(Map<String, Object> map) {
        Map<String, Object> resultData = new LinkedHashMap<>();
        resultData.put("cnp",map.get("cnp"));
        resultData.put("password",map.get("password"));
        resultData.put("rol",map.get("rol"));
        return resultData;
    }
}
