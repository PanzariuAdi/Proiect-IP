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

public class Main {
    public static void main(String[] args) throws Exception {
        DAO persoana = new Persoana();
        DAO pacient = new Pacient();
        DAO doctor = new Doctor();
        DAO asistent = new Asistent();
        DAO datePacient = new DatePacient();
        DAO istoricPacient = new IstoricPacient();

        doctor.insertIntoDB("test", "test", "test", "test");
    }
}