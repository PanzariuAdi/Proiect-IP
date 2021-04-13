public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        db.connectToDatabase();

        db.insertIntoDB("persoana", "test1", "nume", "prenume", "M");
        db.insertIntoDB("doctori", "gradTEST", "Neurolog", "Spital1", "C031");
        db.insertIntoDB("asistenti", "gradTEST", "Spital1");
        db.insertIntoDB("pacient", "test4", "nume", "prenume", "M", "01-01-2000", "67", "178");
        db.insertIntoDB("date_pacient", "test5", "100", "513", "300", "245.55", "prost");
        db.insertIntoDB("istoric_pacient", "test6", "TBC", "test2", "12-04-2020", "30-04-2020", "Spital1");

        System.out.println("Done!");

        //TODO
        /*
        - make insert methods work (working on it)
        - write data collection methods
        - write data removal methods
        */
    }
}
