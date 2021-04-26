package DAOClasses;

/*
Pentru a rula FILE-> INVALIDATE CACHES/RESTART APOI BUILD->REBUILD PROJECT DUPA CARE RULATI
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
*/

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        //Mai intai stabilim conexiunea cu baza de date
        Database db = Database.getInstance();
        db.connectToDatabase();

        //Apoi instantiem obiecte DAO pentru fiecare colectie
        DAO persoana = new Persoana();
        Pacient pacient = new Pacient();
        DAO doctor = new Doctor();
        DAO asistent = new Asistent();
        Conturi conturi = new Conturi();

        //pacient.insertContraindicatii("cnp", "contraTest", "descriereTest");
        //pacient.insertDate("cnp", "80", "123", "200", "300", "bun");
        //pacient.insertIndicatii("cnp", "indicatie random", "descriere");
        //pacient.insertIstoric("cnp", "data_internare3", "test", "test", "test", "test");
        //pacient.insertMedicamente("cnp", "nume_medicament", "oral");

        //La sfarsit, ne deconectam de la baza de date
        db.disconnectFromDatabase();
    }
}