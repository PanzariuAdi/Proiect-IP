package DAOClasses;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class Pacient implements DAO {
    private final Database database = Database.getInstance();

    public Pacient() {}

    public void insertIntoDB(String CNP, String ... args) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {
        Map<String, Object> dataToInsert = new HashMap<>();

        if (args.length != 3)
            throw new InvalidNrOfArgsException("Number of parameters is 3.");

        dataToInsert.put("diagnostic", args[0]);
        dataToInsert.put("greutate", Integer.parseInt(args[1]));
        dataToInsert.put("inaltime", Integer.parseInt(args[2]));

        ApiFuture<WriteResult> insertData = database.db.collection("pacient").document(CNP).set(dataToInsert);

        dataToInsert.clear();

        insertData.get();
        System.out.println("Data inserted in collection pacient!");
    }
    public void insertCollectionByName(String CNP, String collectionName, String ... args) throws InterruptedException, ExecutionException {
        Map<String, Object> dataToInsert = new HashMap<>();
        ApiFuture<WriteResult> insertData = null;

        switch (collectionName) {
            case "contraindicatii" -> {
                dataToInsert.put("nume_contraindicatie", args[0]);
                dataToInsert.put("descriere", args[1]);
                insertData = database.db.collection("pacient")
                        .document(CNP)
                        .collection("contraindicatii")
                        .document()
                        .set(dataToInsert);
            }
            case "date" -> {
                dataToInsert.put("puls", args[0]);
                dataToInsert.put("calorii", Double.parseDouble(args[1]));
                dataToInsert.put("nr_pasi", args[2]);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
                LocalDateTime now = LocalDateTime.now();
                String currentDate = dtf.format(now);
                insertData = database.db.collection("pacient")
                        .document(CNP).collection("date")
                        .document(currentDate)
                        .set(dataToInsert);
            }
            case "indicatii" -> {
                dataToInsert.put("nume_indicatie", args[0]);
                dataToInsert.put("descriere", args[1]);
                insertData = database.db.collection("pacient")
                        .document(CNP)
                        .collection("indicatii")
                        .document()
                        .set(dataToInsert);
            }
            case "istoric" -> {
                dataToInsert.put("cnp_doctor", args[1]);
                dataToInsert.put("data_externare", args[2]);
                dataToInsert.put("diagnostic", args[3]);
                dataToInsert.put("spital", args[4]);
                insertData = database.db.collection("pacient")
                        .document(CNP)
                        .collection("istoric")
                        .document(args[0])
                        .set(dataToInsert);
            }
            case "medicamente" -> {
                dataToInsert.put("nume_medicament", args[0]);
                dataToInsert.put("mod_de_administrare", args[1]);
                insertData = database.db.collection("pacient")
                        .document(CNP)
                        .collection("medicamente")
                        .document()
                        .set(dataToInsert);
            }
            case "calitate_somn" -> {
                dataToInsert.put("calitate", args[0]);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
                LocalDateTime now = LocalDateTime.now();
                String currentDate = dtf.format(now);
                insertData = database.db.collection("pacient")
                        .document(CNP).collection("calitate_somn")
                        .document(currentDate)
                        .set(dataToInsert);
            }
            case "nivel_oxigen" -> {
                dataToInsert.put("value", args[0]);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
                LocalDateTime now = LocalDateTime.now();
                String currentDate = dtf.format(now);
                insertData = database.db.collection("pacient")
                        .document(CNP).collection("nivel_oxigen")
                        .document(currentDate)
                        .set(dataToInsert);
            }
        }

        dataToInsert.clear();

        insertData.get();
        System.out.println("Data inserted in collection pacient!");
    }
    public List<Map<String, Object>> getCollectionByName(String CNP, String collectionName) throws InterruptedException, ExecutionException {
        List<Map<String, Object>> result = new ArrayList<>();
        Iterable<DocumentReference> collection = null;


        switch (collectionName) {
            case "contraindicatii" -> {
                collection = database.db.collection("pacient").document(CNP).collection("contraindicatii").listDocuments();
                for (DocumentReference docRef : collection) {
                    ApiFuture<DocumentSnapshot> getDataApi = docRef.get();
                    DocumentSnapshot documentData = getDataApi.get();
                    result.add(sortMap("contraindicatii", documentData.getData()));
                }
                return result;
            }
            case "date" -> {
                collection = database.db.collection("pacient").document(CNP).collection("date").listDocuments();
                for (DocumentReference docRef : collection) {
                    ApiFuture<DocumentSnapshot> getDataApi = docRef.get();
                    DocumentSnapshot documentData = getDataApi.get();
                    result.add(sortMap("date", documentData.getData()));
                }
                return result;
            }
            case "indicatii" -> {
                collection = database.db.collection("pacient").document(CNP).collection("indicatii").listDocuments();
                for (DocumentReference docRef : collection) {
                    ApiFuture<DocumentSnapshot> getDataApi = docRef.get();
                    DocumentSnapshot documentData = getDataApi.get();
                    result.add(sortMap("indicatii", documentData.getData()));
                }
                return result;
            }
            case "istoric" -> {
                collection = database.db.collection("pacient").document(CNP).collection("istoric").listDocuments();
                for (DocumentReference docRef : collection) {
                    ApiFuture<DocumentSnapshot> getDataApi = docRef.get();
                    DocumentSnapshot documentData = getDataApi.get();
                    result.add(sortMap("istoric", documentData.getData()));
                }
                return result;
            }
            case "medicamente" -> {
                collection = database.db.collection("pacient").document(CNP).collection("medicamente").listDocuments();
                for (DocumentReference docRef : collection) {
                    ApiFuture<DocumentSnapshot> getDataApi = docRef.get();
                    DocumentSnapshot documentData = getDataApi.get();
                    result.add(sortMap("medicamente", documentData.getData()));
                }
                return result;
            }
            case "calitate_somn" -> {
                collection = database.db.collection("pacient").document(CNP).collection("calitate_somn").listDocuments();
                for (DocumentReference docRef : collection) {
                    ApiFuture<DocumentSnapshot> getDataApi = docRef.get();
                    DocumentSnapshot documentData = getDataApi.get();
                    result.add(sortMap("calitate_somn", documentData.getData()));
                }
                return result;
            }
            case "nivel_oxigen" -> {
                collection = database.db.collection("pacient").document(CNP).collection("nivel_oxigen").listDocuments();
                for (DocumentReference docRef : collection) {
                    ApiFuture<DocumentSnapshot> getDataApi = docRef.get();
                    DocumentSnapshot documentData = getDataApi.get();
                    result.add(sortMap("nivel_oxigen", documentData.getData()));
                }
                return result;
            }
        }

        return null;
    }

    //Other methods
    public void removeFromDB(String documentID) throws InterruptedException, ExecutionException {
        ApiFuture<WriteResult> removeData = database.db.collection("pacient").document(documentID).delete();
        removeData.get();
    }
    public void updateInDB(String documentID, String field, String value) throws InterruptedException, ExecutionException {
        DocumentReference updateRef = database.db.collection("pacient").document(documentID);
        ApiFuture<WriteResult> updateData = updateRef.update(field, value);
        updateData.get();
    }
    public List<Map<String, Object>> getDocumentByField(String field, String value) throws InterruptedException, ExecutionException {
        CollectionReference collection = database.db.collection("pacient");
        Query query = collection.whereEqualTo(field, value);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<Map<String, Object>> documents = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            documents.add(sortMap("main", document.getData()));
        }

        return documents;
    }
    public Map<String, Object> getDocumentByID(String documentID) throws InterruptedException, ExecutionException {
        DocumentReference getData = database.db.collection("pacient").document(documentID);
        ApiFuture<DocumentSnapshot> getDataApi = getData.get();
        DocumentSnapshot documentData = getDataApi.get();

        Map<String, Object> resultData = new LinkedHashMap<>();
        if (documentData.exists()) {
            resultData = sortMap("main", documentData.getData());
            return resultData;
        }

        return resultData;
    }
    public List<Map<String, Object>> getCollection() throws InterruptedException, ExecutionException {
        List<Map<String, Object>> result = new ArrayList<>();
        Iterable<DocumentReference> collection = database.db.collection("pacient").listDocuments();

        for (DocumentReference docRef : collection) {
            ApiFuture<DocumentSnapshot> getDataApi = docRef.get();
            DocumentSnapshot documentData = getDataApi.get();
            result.add(sortMap("main", documentData.getData()));
        }

        return result;
    }
    @Deprecated
    public Map<String, Object> getSortedMap(Map<String, Object> map) {
        Map<String, Object> resultData = new LinkedHashMap<>();

        resultData.put("user", map.get("user"));
        resultData.put("nume", map.get("nume"));
        resultData.put("prenume", map.get("prenume"));
        resultData.put("sex", map.get("sex"));
        resultData.put("data_nastere", map.get("data_nastere"));
        resultData.put("greutate", map.get("greutate"));
        resultData.put("inaltime", map.get("inaltime"));

        return resultData;
    }
    
    //Final map sorting method
    public Map<String, Object> sortMap(String collection, Map<String, Object> map) {
        Map<String, Object> resultData = new LinkedHashMap<>();

        switch (collection) {
            case "main" -> {
                resultData.put("diagnostic", map.get("diagnostic"));
                resultData.put("greutate", map.get("greutate"));
                resultData.put("inaltime", map.get("inaltime"));
            }
            case "date" -> {
                resultData.put("puls", map.get("puls"));
                resultData.put("calorii", map.get("calorii"));
                resultData.put("nr_pasi", map.get("nr_pasi"));
                resultData.put("nivel_oxigen", map.get("nivel_oxigen"));
                resultData.put("calitate_somn", map.get("calitate_somn"));
            }
            case "indicatii" -> {
                resultData.put("nume_indicatie", map.get("nume_indicatie"));
                resultData.put("descriere", map.get("descriere"));
            }
            case "contraindicatii" -> {
                resultData.put("nume_contraindicatie", map.get("nume_contraindicatie"));
                resultData.put("descriere", map.get("descriere"));
            }
            case "istoric" -> {
                resultData.put("cnp_doctor", map.get("cnp_doctor"));
                resultData.put("data_externare", map.get("data_externare"));
                resultData.put("diagnostic", map.get("diagnostic"));
                resultData.put("spital", map.get("spital"));
            }
            case "medicamente" -> {
                resultData.put("nume_medicament", map.get("nume_medicament"));
                resultData.put("mod_de_administrare", map.get("mod_de_administrare"));
            }
            case "calitate_somn" -> resultData.put("calitate", map.get("calitate"));
            case "nivel_oxigen" -> resultData.put("value", map.get("value"));
        }

        return resultData;
    }
}
