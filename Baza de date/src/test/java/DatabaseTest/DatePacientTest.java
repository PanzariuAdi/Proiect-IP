package DatabaseTest;

import DAOClasses.DAO;
import DAOClasses.Database;
import DAOClasses.DatePacient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class DatePacientTest {
    private Database db = Database.getInstance();
    private DAO p = new DatePacient();
    private List<String> actual = new ArrayList<>();
    private List<String> expected = new ArrayList<>();

    @Test
    @DisplayName("Should insert a doctor document into the DB")
    void datePacientInsert() throws Exception {
        db.connectToDatabase();

        //TEST FOR DATA INSERTION
        p.insertIntoDB("1990505000000", "user", "100", "101", "102", "103", "somn");
        expected.add("user");
        expected.add("100");
        expected.add("101.0");
        expected.add("102");
        expected.add("103.0");
        expected.add("somn");

        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }
        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should update a document")
    void datePacientUpdate() throws Exception {
        db.connectToDatabase();

        //TEST FOR DATA UPDATE
        p.updateInDB("1990505000000", "nivel_oxigen", "103.5");
        expected.add("user");
        expected.add("100");
        expected.add("101.0");
        expected.add("102");
        expected.add("103.5");
        expected.add("somn");

        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }
        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should remove a document")
    void datePacientRemove() throws Exception {
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
    void datePacientGetDocByField() throws Exception {
        db.connectToDatabase();

        for (Map<String, Object> map : p.getDocumentByField("calitate_somn", "somn")) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        expected.add("user");
        expected.add("100");
        expected.add("101.0");
        expected.add("102");
        expected.add("103.5");
        expected.add("somn");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should get a document by ID")
    void datePacientGetDocByID() throws Exception {
        db.connectToDatabase();

        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }

        expected.add("user");
        expected.add("100");
        expected.add("101.0");
        expected.add("102");
        expected.add("103.5");
        expected.add("somn");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should get a collection")
    void datePacientGetCollection() throws Exception {
        db.connectToDatabase();

        for (Map<String, Object> map : p.getCollection()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        expected.add("user");
        expected.add("100");
        expected.add("101.0");
        expected.add("102");
        expected.add("103.0");
        expected.add("somn");

        expected.add("user");
        expected.add("100");
        expected.add("101.0");
        expected.add("102");
        expected.add("103.5");
        expected.add("somn");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }
}