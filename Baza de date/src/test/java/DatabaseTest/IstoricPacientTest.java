package DatabaseTest;

import DAOClasses.DAO;
import DAOClasses.Database;
import DAOClasses.IstoricPacient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class IstoricPacientTest {
    private Database db = Database.getInstance();
    private DAO p = new IstoricPacient();
    private List<String> actual = new ArrayList<>();
    private List<String> expected = new ArrayList<>();

    @Test
    @DisplayName("Should insert a doctor document into the DB")
    void istoricPacientInsert() throws Exception {
        db.connectToDatabase();

        //TEST FOR DATA INSERTION
        p.insertIntoDB("1990505000000", "user_pacient", "diagnostic", "user_doctor", "01/01/2000", "02/01/2000", "spital");
        expected.add("user_pacient");
        expected.add("diagnostic");
        expected.add("user_doctor");
        expected.add("01/01/2000");
        expected.add("02/01/2000");
        expected.add("spital");

        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }
        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should update a document")
    void istoricPacientUpdate() throws Exception {
        db.connectToDatabase();

        //TEST FOR DATA UPDATE
        p.updateInDB("1990505000000", "user_doctor", "user_doctorNew");
        expected.add("user_pacient");
        expected.add("diagnostic");
        expected.add("user_doctorNew");
        expected.add("01/01/2000");
        expected.add("02/01/2000");
        expected.add("spital");

        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }
        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should remove a document")
    void istoricPacientRemove() throws Exception {
        db.connectToDatabase();

        //TEST FOR DATA REMOVAL
        p.removeFromDB("1990505000000");
        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }
        Assertions.assertLinesMatch(new ArrayList<String>(), actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should get a document by field")
    void istoricPacientGetDocByField() throws Exception {
        db.connectToDatabase();

        for (Map<String, Object> map : p.getDocumentByField("diagnostic", "diagnostic")) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        expected.add("user");
        expected.add("diagnostic");
        expected.add("user_doctor");
        expected.add("01/01/2000");
        expected.add("01/01/2000");
        expected.add("spital");

        expected.add("user_pacient");
        expected.add("diagnostic");
        expected.add("user_doctorNew");
        expected.add("01/01/2000");
        expected.add("02/01/2000");
        expected.add("spital");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should get a document by ID")
    void istoricPacientGetDocByID() throws Exception {
        db.connectToDatabase();

        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }

        expected.add("user_pacient");
        expected.add("diagnostic");
        expected.add("user_doctorNew");
        expected.add("01/01/2000");
        expected.add("02/01/2000");
        expected.add("spital");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should get a collection")
    void istoricPacientGetCollection() throws Exception {
        db.connectToDatabase();

        for (Map<String, Object> map : p.getCollection()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        expected.add("user");
        expected.add("diagnostic");
        expected.add("user_doctor");
        expected.add("01/01/2000");
        expected.add("01/01/2000");
        expected.add("spital");

        expected.add("user_pacient");
        expected.add("diagnostic");
        expected.add("user_doctorNew");
        expected.add("01/01/2000");
        expected.add("02/01/2000");
        expected.add("spital");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }
}