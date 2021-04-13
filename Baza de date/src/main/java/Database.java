import com.google.api.client.util.ArrayMap;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.rpc.Help;

import javax.swing.text.Document;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class Database {
    //Database related vars
    private FileInputStream serviceAccount;
    private FirebaseOptions options;
    private Firestore db;
    private Map<String, Object> dataToInsert = new LinkedHashMap<>();
    private ApiFuture<WriteResult> insertData;
    private ApiFuture<WriteResult> removeData;
    private DocumentReference getData;
    private ApiFuture<DocumentSnapshot> getDataApi;
    private DocumentSnapshot documentData;

    //Person ID
    private StringBuilder personID = new StringBuilder();
    private int personIDNumber = 1;

    //Pacient ID
    private StringBuilder pacientID = new StringBuilder();
    private int pacientIDNumber = 1;

    //Doctor ID
    private StringBuilder doctorID = new StringBuilder();
    private int doctorIDNumber = 1;

    //Asistent ID
    private StringBuilder asistentID = new StringBuilder();
    private int asistentIDNumber = 1;

    //Constructor
    public Database() {
        personID.append("0#");
        pacientID.append("1#");
        doctorID.append("2#");
        asistentID.append("3#");
    }

    //Connect to DB
    public void connectToDatabase() {
        //Setting db .json file
        try {
            serviceAccount = new FileInputStream("iot-in-medical-domain-firebase-adminsdk-zw2pe-dcf8137ac2.json");
        }
        catch (FileNotFoundException e) {
            System.out.println("Database .json file could not be found or does not exist!");
        }

        //Setting credentials and database URL
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://iot-in-medical-domain-default-rtdb.europe-west1.firebasedatabase.app")
                    .build();
        }
        catch (IOException e) {
            System.out.println("Database .json file was not set up!");
        }

        //Connecting to the database
        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();

        System.out.println("Connection to database successful!");
    }

    //Insert into db
    public void insertIntoDB(String table, String ... args) throws InterruptedException, ExecutionException{
        switch (table) {
            case "persoana":
                try {
                    insertPersoana(args);
                }
                catch (InvalidNrOfArgsException e) {
                    e.printStackTrace();
                }
                break;
            case "pacient":
                try {
                    insertPacient(args);
                }
                catch (InvalidNrOfArgsException e) {
                    e.printStackTrace();
                }
                break;
            case "doctori":
                try {
                    insertDoctor(args);
                }
                catch (InvalidNrOfArgsException e) {
                    e.printStackTrace();
                }
                break;
            case "asistenti":
                try {
                    insertAsistent(args);
                }
                catch (InvalidNrOfArgsException e) {
                    e.printStackTrace();
                }
                break;
            case "date_pacient":
                try {
                    insertDatePacient(args);
                }
                catch (InvalidNrOfArgsException e) {
                    e.printStackTrace();
                }
                break;
            case "istoric_pacient":
                try {
                    insertIstoricPacient(args);
                }
                catch (InvalidNrOfArgsException e) {
                    e.printStackTrace();
                }
                break;
        }

        //"Commit"
        insertData.get();
        System.out.println("Data inserted in collection " + table + "!");
    }
    private void insertPersoana(String ... args) throws InvalidNrOfArgsException {
        if (args.length != 4)
            throw new InvalidNrOfArgsException("Number of parameters is 4.");

        dataToInsert.put("user", args[0]);
        dataToInsert.put("nume", args[1]);
        dataToInsert.put("prenume", args[2]);
        dataToInsert.put("sex", args[3]);

        personID.replace(2, personID.length(), String.valueOf(personIDNumber));
        personIDNumber++;
        insertData = db.collection("persoana").document(personID.toString()).set(dataToInsert);

        dataToInsert.clear();
    }
    private void insertPacient(String ... args) throws InvalidNrOfArgsException {
        if (args.length != 7)
            throw new InvalidNrOfArgsException("Number of parameters is 7.");

        dataToInsert.put("user", args[0]);
        dataToInsert.put("nume", args[1]);
        dataToInsert.put("prenume", args[2]);
        dataToInsert.put("sex", args[3]);
        dataToInsert.put("data_nastere", args[4]);
        dataToInsert.put("greutate", Integer.parseInt(args[5]));
        dataToInsert.put("inaltime", Integer.parseInt(args[6]));

        pacientID.replace(2, pacientID.length(), String.valueOf(pacientIDNumber));
        pacientIDNumber++;
        insertData = db.collection("pacient").document(pacientID.toString()).set(dataToInsert);

        dataToInsert.clear();
    }
    private void insertDoctor(String ... args) throws InvalidNrOfArgsException {
        if (args.length != 4)
            throw new InvalidNrOfArgsException("Number of parameters is 4.");

        dataToInsert.put("grad", args[0]);
        dataToInsert.put("specializare", args[1]);
        dataToInsert.put("spital", args[2]);
        dataToInsert.put("birou", args[3]);

        doctorID.replace(2, doctorID.length(), String.valueOf(doctorIDNumber));
        doctorIDNumber++;

        insertData = db.collection("doctori").document(doctorID.toString()).set(dataToInsert);

        dataToInsert.clear();
    }
    private void insertAsistent(String ... args) throws InvalidNrOfArgsException {
        if (args.length != 2)
            throw new InvalidNrOfArgsException("Number of parameters is 2.");

        dataToInsert.put("grad", args[0]);
        dataToInsert.put("spital", args[1]);

        asistentID.replace(2, asistentID.length(), String.valueOf(asistentIDNumber));
        asistentIDNumber++;

        insertData = db.collection("asistenti").document(asistentID.toString()).set(dataToInsert);

        dataToInsert.clear();
    }
    private void insertDatePacient(String ... args) throws InvalidNrOfArgsException {
        if (args.length != 6)
            throw new InvalidNrOfArgsException("Number of parameters is 6.");

        dataToInsert.put("user", args[0]);
        dataToInsert.put("puls", args[1]);
        dataToInsert.put("calorii", Double.parseDouble(args[2]));
        dataToInsert.put("nr_pasi", args[3]);
        dataToInsert.put("nivel_oxigen", Double.parseDouble(args[4]));
        dataToInsert.put("calitate_somn", args[5]);

        pacientID.replace(2, pacientID.length(), String.valueOf(pacientIDNumber));
        pacientIDNumber++;

        insertData = db.collection("date_pacient").document(pacientID.toString()).set(dataToInsert);

        dataToInsert.clear();
    }
    private void insertIstoricPacient(String ... args) throws InvalidNrOfArgsException {
        if (args.length != 6)
            throw new InvalidNrOfArgsException("Number of parameters is 6.");

        dataToInsert.put("user", args[0]);
        dataToInsert.put("diagnostic", args[1]);
        dataToInsert.put("user_doctor", args[2]);
        dataToInsert.put("data_internare", args[3]);
        dataToInsert.put("data_externare", args[4]);
        dataToInsert.put("spital", args[5]);

        pacientID.replace(2, pacientID.length(), String.valueOf(pacientIDNumber));
        pacientIDNumber++;

        insertData = db.collection("istoric_pacient").document(pacientID.toString()).set(dataToInsert);

        dataToInsert.clear();
    }

    //Remove from db
    public void removeFromDB(String table, String ID) throws InterruptedException, ExecutionException {
        removeData = db.collection(table).document(ID).delete();
        removeData.get();
    }

    //Find document by parameter
    public List<LinkedHashMap<String, Object>> getDocument(String table, String field, String value) throws InterruptedException, ExecutionException {
        CollectionReference collection = db.collection(table);
        Query query = collection.whereEqualTo(field, value);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<LinkedHashMap<String, Object>> documents = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            documents.add((LinkedHashMap<String, Object>) document.getData());
        }
        return documents;
    }
    public void test() throws InterruptedException, ExecutionException {
        /*getData = db.collection("asistenti").document("#0");
        getDataApi = getData.get();
        try {
            documentData = getDataApi.get();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (documentData.exists())
            System.out.println(documentData.getData());
        else
            System.out.println("No such document!");*/
        //System.out.println("Update time: " + insertData.get().getUpdateTime());
    }
}
