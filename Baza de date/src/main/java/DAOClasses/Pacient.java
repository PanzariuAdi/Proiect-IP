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
    //Adaugare contraindicatii
    public void insertContraindicatii(String CNP, String numeContraindicatie, String descriere) throws InterruptedException, ExecutionException {
        Map<String, Object> dataToInsert = new HashMap<>();

        dataToInsert.put("descriere", descriere);

        ApiFuture<WriteResult> insertData = database.db.collection("pacient")
                                            .document(CNP)
                                            .collection("contraindicatii")
                                            .document(numeContraindicatie)
                                            .set(dataToInsert);

        dataToInsert.clear();

        insertData.get();
        System.out.println("Contraindicatie inserted in collection pacient!");
    }
    //Inserare puls, calorii, calitate somn etc dupa data curenta
    public void insertDate(String CNP, String ... args) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {
        Map<String, Object> dataToInsert = new HashMap<>();

        if (args.length != 5)
            throw new InvalidNrOfArgsException("Number of parameters is 5.");

        dataToInsert.put("puls", args[0]);
        dataToInsert.put("calorii", Double.parseDouble(args[1]));
        dataToInsert.put("nr_pasi", args[2]);
        dataToInsert.put("nivel_oxigen", Double.parseDouble(args[3]));
        dataToInsert.put("calitate_somn", args[4]);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);

        ApiFuture<WriteResult> insertData = database.db.collection("pacient")
                                            .document(CNP).collection("date")
                                            .document(currentDate)
                                            .set(dataToInsert);

        dataToInsert.clear();

        insertData.get();
        System.out.println("Data inserted in collection pacient!");
    }
    //Adaugare indicatii
    public void insertIndicatii(String CNP, String numeIndicatie, String descriere) throws InterruptedException, ExecutionException {
        Map<String, Object> dataToInsert = new HashMap<>();

        dataToInsert.put("descriere", descriere);

        ApiFuture<WriteResult> insertData = database.db.collection("pacient")
                .document(CNP)
                .collection("indicatii")
                .document(numeIndicatie)
                .set(dataToInsert);

        dataToInsert.clear();

        insertData.get();
        System.out.println("Indicatie inserted in collection pacient!");
    }
    //Adaugare istoric pacient
    public void insertIstoric(String CNP, String dataInternare, String ... args) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {
        Map<String, Object> dataToInsert = new HashMap<>();

        if (args.length != 4)
            throw new InvalidNrOfArgsException("Number of parameters is 4.");

        dataToInsert.put("cnp_doctor", args[0]);
        dataToInsert.put("data_externare", args[1]);
        dataToInsert.put("diagnostic", args[2]);
        dataToInsert.put("spital", args[3]);

        ApiFuture<WriteResult> insertData = database.db.collection("pacient")
                .document(CNP)
                .collection("istoric")
                .document(dataInternare)
                .set(dataToInsert);

        dataToInsert.clear();

        insertData.get();
        System.out.println("Istoric inserted in collection pacient!");
    }
    //Adaugare medicamente
    public void insertMedicamente(String CNP, String numeMedicament, String modDeAdministrare) throws InterruptedException, ExecutionException {
        Map<String, Object> dataToInsert = new HashMap<>();

        dataToInsert.put("mod_de_administrare", modDeAdministrare);

        ApiFuture<WriteResult> insertData = database.db.collection("pacient")
                .document(CNP)
                .collection("medicamente")
                .document(numeMedicament)
                .set(dataToInsert);

        dataToInsert.clear();

        insertData.get();
        System.out.println("Medicament inserted in collection pacient!");
    }

    //Getters
    public List<Map<String, Object>> getContraindicatii(String CNP) throws InterruptedException, ExecutionException {
        List<Map<String, Object>> result = new ArrayList<>();
        Iterable<DocumentReference> collection = database.db.collection("pacient").document(CNP).collection("contraindicatii").listDocuments();

        for (DocumentReference docRef : collection) {
            ApiFuture<DocumentSnapshot> getDataApi = docRef.get();
            DocumentSnapshot documentData = getDataApi.get();
            result.add(sortMap("contraindicatii", documentData.getData()));
        }

        return result;
    }
    public List<Map<String, Object>> getDatePacient(String CNP) throws InterruptedException, ExecutionException {
        List<Map<String, Object>> result = new ArrayList<>();
        Iterable<DocumentReference> collection = database.db.collection("pacient").document(CNP).collection("date").listDocuments();

        for (DocumentReference docRef : collection) {
            ApiFuture<DocumentSnapshot> getDataApi = docRef.get();
            DocumentSnapshot documentData = getDataApi.get();
            result.add(sortMap("date", documentData.getData()));
        }

        return result;
    }
    public List<Map<String, Object>> getIndicatii(String CNP) throws InterruptedException, ExecutionException {
        List<Map<String, Object>> result = new ArrayList<>();
        Iterable<DocumentReference> collection = database.db.collection("pacient").document(CNP).collection("indicatii").listDocuments();

        for (DocumentReference docRef : collection) {
            ApiFuture<DocumentSnapshot> getDataApi = docRef.get();
            DocumentSnapshot documentData = getDataApi.get();
            result.add(sortMap("indicatii", documentData.getData()));
        }

        return result;
    }
    public List<Map<String, Object>> getIstoric(String CNP) throws InterruptedException, ExecutionException {
        List<Map<String, Object>> result = new ArrayList<>();
        Iterable<DocumentReference> collection = database.db.collection("pacient").document(CNP).collection("istoric").listDocuments();

        for (DocumentReference docRef : collection) {
            ApiFuture<DocumentSnapshot> getDataApi = docRef.get();
            DocumentSnapshot documentData = getDataApi.get();
            result.add(sortMap("istoric", documentData.getData()));
        }

        return result;
    }
    public List<Map<String, Object>> getMedicamente(String CNP) throws InterruptedException, ExecutionException {
        List<Map<String, Object>> result = new ArrayList<>();
        Iterable<DocumentReference> collection = database.db.collection("pacient").document(CNP).collection("medicamente").listDocuments();

        for (DocumentReference docRef : collection) {
            ApiFuture<DocumentSnapshot> getDataApi = docRef.get();
            DocumentSnapshot documentData = getDataApi.get();
            result.add(sortMap("medicamente", documentData.getData()));
        }

        return result;
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
            case "indicatii", "contraindicatii" -> resultData.put("descriere", map.get("descriere"));
            case "istoric" -> {
                resultData.put("cnp_doctor", map.get("cnp_doctor"));
                resultData.put("data_externare", map.get("data_externare"));
                resultData.put("diagnostic", map.get("diagnostic"));
                resultData.put("spital", map.get("spital"));
            }
            case "medicamente" -> resultData.put("mod_de_administrare", map.get("mod_de_administrare"));
        }

        return resultData;
    }
}
