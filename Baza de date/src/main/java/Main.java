import java.util.List;
import java.util.Map;

public class Main extends Database {
    public static void main(String[] args) throws Exception {
        Database db = new Database();
        db.connectToDatabase();

//        //Se insereaza cate un document pentru fiecare colectie
//        db.insertIntoDB("persoana", "test1", "nume", "prenume", "M");
//        db.insertIntoDB("doctori", "gradTEST", "Neurolog", "Spital1", "C031");
//        db.insertIntoDB("asistenti", "gradTEST", "Spital1");
//        db.insertIntoDB("pacient", "test4", "nume", "prenume", "M", "01-01-2000", "67", "178");
//        db.insertIntoDB("date_pacient", "test5", "100", "513", "300", "245.55", "prost");
//        db.insertIntoDB("istoric_pacient", "test6", "TBC", "test2", "12-04-2020", "30-04-2020", "Spital1");
//        System.out.println("Done inserting!");
//
//        //Se sterge din colectia "asistenti" documentul cu ID-ul 3#1
//        db.removeFromDB("asistenti", "3#1");
//        System.out.println("Done removing!");
//
//        //Se updateaza in colectia "persoana" numele din documentul 0#1
//        db.updateInDB("persoana", "0#1", "nume", "nume_nou");
//        System.out.println("Done updating!");

        List<Map<String, Object>> doctor = db.getDocumentByField("doctori", "birou", "C031");
        System.out.println(doctor);
        System.out.println("----------------------------------------------");

        Map<String, Object> doctorByID = db.getDocumentByID("doctori", "2#1");
        System.out.println(doctorByID);
        System.out.println("----------------------------------------------");

        List<Map<String, Object>> doctors = db.getCollection("doctori");
        System.out.println(doctors);

        //TODO
        /*
        - make insert methods work (DONE)
        - write data collection methods (working on it)
        - write data removal methods(DONE)
        */
    }
}