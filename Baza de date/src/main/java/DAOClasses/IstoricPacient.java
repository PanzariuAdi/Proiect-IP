package DAOClasses;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class IstoricPacient implements DAO {
    private final Database database = Database.getInstance();

    public IstoricPacient() {}

    public void insertIntoDB(String CNP, String ... args) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {
        Map<String, Object> dataToInsert = new HashMap<>();

        if (args.length != 6)
            throw new InvalidNrOfArgsException("Number of parameters is 6.");

        dataToInsert.put("user", args[0]);
        dataToInsert.put("diagnostic", args[1]);
        dataToInsert.put("user_doctor", args[2]);
        dataToInsert.put("data_internare", args[3]);
        dataToInsert.put("data_externare", args[4]);
        dataToInsert.put("spital", args[5]);

        ApiFuture<WriteResult> insertData = database.db.collection("istoric_pacient").document(CNP).set(dataToInsert);

        dataToInsert.clear();

        insertData.get();
        System.out.println("Data inserted in collection date_pacient!");
    }
    public void removeFromDB(String documentID) throws InterruptedException, ExecutionException {
        ApiFuture<WriteResult> removeData = database.db.collection("istoric_pacient").document(documentID).delete();
        removeData.get();
    }
    public void updateInDB(String documentID, String field, String value) throws InterruptedException, ExecutionException {
        DocumentReference updateRef = database.db.collection("istoric_pacient").document(documentID);
        ApiFuture<WriteResult> updateData = updateRef.update(field, value);
        updateData.get();
    }
    public List<Map<String, Object>> getDocumentByField(String field, String value) throws InterruptedException, ExecutionException {
        CollectionReference collection = database.db.collection("istoric_pacient");
        Query query = collection.whereEqualTo(field, value);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<Map<String, Object>> documents = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            documents.add(getSortedMap(document.getData()));
        }

        return documents;
    }
    public Map<String, Object> getDocumentByID(String documentID) throws InterruptedException, ExecutionException {
        DocumentReference getData = database.db.collection("istoric_pacient").document(documentID);
        ApiFuture<DocumentSnapshot> getDataApi = getData.get();
        DocumentSnapshot documentData = getDataApi.get();

        Map<String, Object> resultData = new LinkedHashMap<>();
        if (documentData.exists()) {
            resultData = getSortedMap(documentData.getData());
            return resultData;
        }

        return resultData;
    }
    public List<Map<String, Object>> getCollection() throws InterruptedException, ExecutionException {
        List<Map<String, Object>> result = new ArrayList<>();
        Iterable<DocumentReference> collection = database.db.collection("istoric_pacient").listDocuments();

        for (DocumentReference docRef : collection) {
            ApiFuture<DocumentSnapshot> getDataApi = docRef.get();
            DocumentSnapshot documentData = getDataApi.get();
            result.add(getSortedMap(documentData.getData()));
        }

        return result;
    }
    public Map<String, Object> getSortedMap(Map<String, Object> map) {
        Map<String, Object> resultData = new LinkedHashMap<>();

        resultData.put("user", map.get("user"));
        resultData.put("diagnostic", map.get("diagnostic"));
        resultData.put("user_doctor", map.get("user_doctor"));
        resultData.put("data_internare", map.get("data_internare"));
        resultData.put("data_externare", map.get("data_externare"));
        resultData.put("spital", map.get("spital"));

        return resultData;
    }
}
