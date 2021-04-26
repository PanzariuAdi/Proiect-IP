package DatabaseTest;

import DAOClasses.DAO;
import DAOClasses.Database;
import DAOClasses.Doctor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class DoctorTest {
    private Database db = Database.getInstance();
    private DAO p = new Doctor();
    private List<String> actual = new ArrayList<>();
    private List<String> expected = new ArrayList<>();

    @Test
    @DisplayName("Should insert a doctor document into the DB")
    void doctorInsert() throws Exception {
        db.connectToDatabase();

        //TEST FOR DATA INSERTION
        p.insertIntoDB("1990505000000", "grad", "spec", "spital", "birou");
        expected.add("grad");
        expected.add("spec");
        expected.add("spital");
        expected.add("birou");

        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }
        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should update a document")
    void doctorUpdate() throws Exception {
        db.connectToDatabase();

        //TEST FOR DATA UPDATE
        p.updateInDB("1990505000000", "birou", "birouNew");
        expected.add("grad");
        expected.add("spec");
        expected.add("spital");
        expected.add("birouNew");

        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }
        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should remove a document")
    void doctorRemove() throws Exception {
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
    void doctorGetDocByField() throws Exception {
        db.connectToDatabase();

        for (Map<String, Object> map : p.getDocumentByField("specializare", "spec")) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        expected.add("grad");
        expected.add("spec");
        expected.add("spital");
        expected.add("birouNew");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should get a document by ID")
    void doctorGetDocByID() throws Exception {
        db.connectToDatabase();

        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }

        expected.add("grad");
        expected.add("spec");
        expected.add("spital");
        expected.add("birouNew");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should get a collection")
    void doctorGetCollection() throws Exception {
        db.connectToDatabase();

        for (Map<String, Object> map : p.getCollection()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        expected.add("grad");
        expected.add("spec");
        expected.add("spital");
        expected.add("birouNew");

        expected.add("test");
        expected.add("test");
        expected.add("test");
        expected.add("test");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }
}