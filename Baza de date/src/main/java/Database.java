import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.FirebaseOptions.Builder;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Database {
    //Database related vars
    private FileInputStream serviceAccount;
    private FirebaseOptions options;
    private Firestore db;
    private final Map<String, Object> dataToInsert = new HashMap<>();
    private ApiFuture<WriteResult> insertData;
    private DocumentReference getData;
    private DocumentSnapshot documentData;

    //Unique ID for each person in the database
    private StringBuilder personID = new StringBuilder();
    private int personIDNumber = 0;

    //Constructor
    public Database() {
        personID.append("#");
    }

    //Methods
    public void connectToDatabase() {
        //Setting db .json file
        try {
            serviceAccount = new FileInputStream("iot-in-medical-domain-firebase-adminsdk-zw2pe-2497e947b5.json");
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
    public void insertIntoDB(String table, String ... args) {
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
        System.out.println("Data inserted in collection " + table + "!");
    }
    private void insertPersoana(String ... args) throws InvalidNrOfArgsException {
        if (args.length != 4)
            throw new InvalidNrOfArgsException("Number of parameters is 4.");

        dataToInsert.put("user", args[0]);
        dataToInsert.put("nume", args[1]);
        dataToInsert.put("prenume", args[2]);
        dataToInsert.put("sex", args[3]);

        personID.replace(1, personID.length(), String.valueOf(personIDNumber));
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

        personID.replace(1, personID.length(), String.valueOf(personIDNumber));
        personIDNumber++;
        insertData = db.collection("pacient").document(personID.toString()).set(dataToInsert);

        dataToInsert.clear();
    }
    private void insertDoctor(String ... args) throws InvalidNrOfArgsException {
        if (args.length != 4)
            throw new InvalidNrOfArgsException("Number of parameters is 4.");

        dataToInsert.put("grad", args[0]);
        dataToInsert.put("specializare", args[1]);
        dataToInsert.put("spital", args[2]);
        dataToInsert.put("birou", args[3]);

        personID.replace(1, personID.length(), String.valueOf(personIDNumber));
        personIDNumber++;

        insertData = db.collection("doctori").document(personID.toString()).set(dataToInsert);

        dataToInsert.clear();
    }
    private void insertAsistent(String ... args) throws InvalidNrOfArgsException {
        if (args.length != 2)
            throw new InvalidNrOfArgsException("Number of parameters is 2.");

        dataToInsert.put("grad", args[0]);
        dataToInsert.put("spital", args[1]);

        personID.replace(1, personID.length(), String.valueOf(personIDNumber));
        personIDNumber++;

        insertData = db.collection("asistenti").document(personID.toString()).set(dataToInsert);

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

        personID.replace(1, personID.length(), String.valueOf(personIDNumber));
        personIDNumber++;

        insertData = db.collection("date_pacient").document(personID.toString()).set(dataToInsert);

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

        personID.replace(1, personID.length(), String.valueOf(personIDNumber));
        personIDNumber++;

        insertData = db.collection("istoric_pacient").document(personID.toString()).set(dataToInsert);

        dataToInsert.clear();
    }
}
