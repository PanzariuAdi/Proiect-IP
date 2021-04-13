public class Main extends Database {
    public static void main(String[] args) throws Exception {
        Database db = new Database();
        db.connectToDatabase();

        db.insertIntoDB("persoana", "test1", "nume", "prenume", "M");
        db.insertIntoDB("doctori", "gradTEST", "Neurolog", "Spital1", "C031");
        db.insertIntoDB("asistenti", "gradTEST", "Spital1");
        db.insertIntoDB("pacient", "test4", "nume", "prenume", "M", "01-01-2000", "67", "178");
        db.insertIntoDB("date_pacient", "test5", "100", "513", "300", "245.55", "prost");
        db.insertIntoDB("istoric_pacient", "test6", "TBC", "test2", "12-04-2020", "30-04-2020", "Spital1");
        System.out.println("Done inserting!");

        db.removeFromDB("asistenti", "3#1");
        System.out.println("Done removing!");

        //TODO
        /*
        - make insert methods work (DONE)
        - write data collection methods (working on it)
        - write data removal methods(PROBABLY DONE)
        */
    }
}