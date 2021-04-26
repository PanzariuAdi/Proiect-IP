package DatabaseTest;

import DAOClasses.DAO;
import DAOClasses.Database;
import DAOClasses.Asistent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class AsistentTest {
    private Database db = Database.getInstance();
    private DAO p = new Asistent();
    private List<String> actual = new ArrayList<>();
    private List<String> expected = new ArrayList<>();

    @Test
    @DisplayName("Should insert an asistent document into the DB")
    void asistentInsert() throws Exception {
        db.connectToDatabase();

        //TEST FOR DATA INSERTION
        p.insertIntoDB("1990505000000", "neuro", "test");
        expected.add("neuro");
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
    void asistentUpdate() throws Exception {
        db.connectToDatabase();

        //TEST FOR DATA UPDATE
        p.updateInDB("1990505000000", "sectie", "junior");
        expected.add("junior");
        expected.add("test");

        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }
        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should remove a document")
    void asistentRemove() throws Exception {
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
    void asistentGetDocByField() throws Exception {
        db.connectToDatabase();

        for (Map<String, Object> map : p.getDocumentByField("sectie", "junior")) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        expected.add("junior");
        expected.add("test");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should get a document by ID")
    void asistentGetDocByID() throws Exception {
        db.connectToDatabase();

        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }

        expected.add("junior");
        expected.add("test");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should get a collection")
    void asistentGetCollection() throws Exception {
        db.connectToDatabase();

        for (Map<String, Object> map : p.getCollection()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        expected.add("junior");
        expected.add("test");

        expected.add("neurologie");
        expected.add(" test");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }
}