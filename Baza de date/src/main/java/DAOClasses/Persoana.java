package DAOClasses;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class Persoana implements DAO {
    private final Database database = Database.getInstance();

    public Persoana() {}

    public void insertIntoDB(String CNP, String ... args) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {
        Map<String, Object> dataToInsert = new HashMap<>();

        if (args.length != 5)
            throw new InvalidNrOfArgsException("Number of parameters is 5.");

        dataToInsert.put("nume", args[0]);
        dataToInsert.put("prenume", args[1]);
        dataToInsert.put("sex", args[2]);
        dataToInsert.put("data_nastere", args[3]);
        dataToInsert.put("adresa", args[4]);

        ApiFuture<WriteResult> insertData = database.db.collection("persoana").document(CNP).set(dataToInsert);

        dataToInsert.clear();

        insertData.get();
        System.out.println("Data inserted in collection persoana!");
    }
    public void removeFromDB(String documentID) throws InterruptedException, ExecutionException {
        ApiFuture<WriteResult> removeData = database.db.collection("persoana").document(documentID).delete();
        removeData.get();
    }
    public void updateInDB(String documentID, String field, String value) throws InterruptedException, ExecutionException {
        DocumentReference updateRef = database.db.collection("persoana").document(documentID);
        ApiFuture<WriteResult> updateData = updateRef.update(field, value);
        updateData.get();
    }
    public List<Map<String, Object>> getDocumentByField(String field, String value) throws InterruptedException, ExecutionException {
        CollectionReference collection = database.db.collection("persoana");
        Query query = collection.whereEqualTo(field, value);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<Map<String, Object>> documents = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            documents.add(getSortedMap(document.getData()));
        }

        return documents;
    }
    public Map<String, Object> getDocumentByID(String documentID) throws InterruptedException, ExecutionException {
        DocumentReference getData = database.db.collection("persoana").document(documentID);
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
        Iterable<DocumentReference> collection = database.db.collection("persoana").listDocuments();

        for (DocumentReference docRef : collection) {
            ApiFuture<DocumentSnapshot> getDataApi = docRef.get();
            DocumentSnapshot documentData = getDataApi.get();
            result.add(getSortedMap(documentData.getData()));
        }

        return result;
    }
    public Map<String, Object> getSortedMap(Map<String, Object> map) {
        Map<String, Object> resultData = new LinkedHashMap<>();

        resultData.put("nume", map.get("nume"));
        resultData.put("prenume", map.get("prenume"));
        resultData.put("sex", map.get("sex"));
        resultData.put("data_nastere", map.get("data_nastere"));
        resultData.put("adresa", map.get("adresa"));

        return resultData;
    }
}
