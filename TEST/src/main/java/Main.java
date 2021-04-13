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
import java.util.HashMap;
import java.util.Map;

/*
Pentru a rula FILE-> INVALIDATE CACHES/RESTART APOI BUILD->REBUILD PROJECT DUPA CARE RULATI
 */


/*
    La rulare, cel mai probabil va fi afisata o eroare in consola.
    Nu am reusit sa scap de ea, dar din cate am observat, aplicatia functioneaza cum ar trebui.
    Oops :(
*/

/*
    Pentru a ne conecta la Firebase si a o folosi, am creat proiectul folosind Maven.
    Am adaugat apoi in fisierul pom.xml urmatoarele (le-am pus dupa tag-ul "properties", desi nu cred ca are vreo relevanta):

    <dependencies>
        <dependency>
            <groupId>com.google.firebase</groupId>
            <artifactId>firebase-admin</artifactId>
            <version>7.1.1</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.30</version>
        </dependency>
    </dependencies>

    Fisierul .json cu numele "iot-in-medical-domain-..." este pus in directorul mare, path-ul relativ catre acesta fiind
    chiar "./iot-in-medical-domain-firebase-adminsdk-zw2pe-2497e947b5.json".

    CAND SETATI CREDENTIALELE PENTRU BD LA LINIA 49, NU MAI SCRIETI SI "./".
*/

public class Main {
    public static void main(String[] args) throws Exception {
        //Conectarea la baza de date
        FileInputStream serviceAccount =
                new FileInputStream("iot-in-medical-domain-firebase-adminsdk-zw2pe-2497e947b5.json");

        FirebaseOptions options = new Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://iot-in-medical-domain-default-rtdb.europe-west1.firebasedatabase.app")
                .build();

        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();


        //Avem nevoie de unde map ce contine String-uri drept chei si obiecte drept valori pentru a putea insera intr-un
        //anumit document valori.

        //In cazul nostru, cheia este numele coloanei din "tabel", iar obiectul, care e tot String, specifica valoarea coloanei.
        Map<String, Object> docData = new HashMap<>();
        docData.put("user", "wahr");
        docData.put("pass", "hrwa");
        docData.put("rol", "hrwa");


        //ATENTIE! In Firebase, nu exista tabele!
        //Avem colectii, care sunt asemanatoare tabelelor din SQL, si documente, asemanatoare liniilor dintr-un tabel!
        //Documentele nu au o structura predefinita, ca in cazul crearii tabelelor in SQL. Putem insera absolut orice,
        //fara a respecta o anumita structura.
        //Pentru a stabili totusi o anumita structura, trebuie facute verificari din cod, putand avea nevoie de exceptii.


        //Aici inseram in colectia conturi, in documentul "pacient1" urmatoarele:
        //user = wahr
        //pass = hrwa
        //rol = hrwa
        ApiFuture<WriteResult> future = db.collection("conturi").document("pacient1").set(docData);
        System.out.println("Update time : " + future.get().getUpdateTime()); //afisam data si ora la care s-a facut update-ul.

        //Preluam datele din colectia "conturi", documentul "pacient1", pe care tocmai l-am inserat.
        DocumentReference docRef = db.collection("conturi").document("pacient1");
        ApiFuture<DocumentSnapshot> future2 = docRef.get();
        DocumentSnapshot document = future2.get();

        //Daca documentul exista, afisam datele din BD (metoda getData() returneaza un Map<String, Object>)
        if (document.exists()) {
            System.out.println("Document data: " + document.getData());
        } else {
            System.out.println("No such document!");
        }
    }
}
