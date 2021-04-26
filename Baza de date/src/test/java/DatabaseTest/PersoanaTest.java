package DatabaseTest;

import DAOClasses.DAO;
import DAOClasses.Database;
import DAOClasses.Persoana;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class PersoanaTest {
    private Database db = Database.getInstance();
    private DAO p = new Persoana();
    private List<String> actual = new ArrayList<>();
    private List<String> expected = new ArrayList<>();

    @Test
    @DisplayName("Should insert a person document into the DB")
    void persoanaInsert() throws Exception {
        db.connectToDatabase();

        //TEST FOR DATA INSERTION
        p.insertIntoDB("1990505000000", "test", "test", "test", "test", "test");
        expected.add("test");
        expected.add("test");
        expected.add("test");
        expected.add("test");
        expected.add("test");

        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }
        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should update a document")
    void persoanaUpdate() throws Exception {
        db.connectToDatabase();

        //TEST FOR DATA UPDATE
        p.updateInDB("1990505000000", "adresa", "adresa");
        expected.add("test");
        expected.add("test");
        expected.add("test");
        expected.add("test");
        expected.add("adresa");

        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }
        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should remove a document")
    void persoanaRemove() throws Exception {
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
    void persoanaGetDocByField() throws Exception {
        db.connectToDatabase();

        for (Map<String, Object> map : p.getDocumentByField("adresa", "adresa")) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        expected.add("test");
        expected.add("test");
        expected.add("test");
        expected.add("test");
        expected.add("adresa");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should get a document by ID")
    void persoanaGetDocByID() throws Exception {
        db.connectToDatabase();

        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }

        expected.add("test");
        expected.add("test");
        expected.add("test");
        expected.add("test");
        expected.add("adresa");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should get a collection")
    void persoanaGetCollection() throws Exception {
        db.connectToDatabase();

        for (Map<String, Object> map : p.getCollection()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        expected.add("test");
        expected.add("test");
        expected.add("test");
        expected.add("test");
        expected.add("adresa");

        expected.add("test");
        expected.add("test");
        expected.add("test");
        expected.add("test");
        expected.add("test");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }
}