package DatabaseTest;

import DAOClasses.DAO;
import DAOClasses.Database;
import DAOClasses.Pacient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class PacientTest {
    private Database db = Database.getInstance();
    private DAO p = new Pacient();
    private List<String> actual = new ArrayList<>();
    private List<String> expected = new ArrayList<>();

    @Test
    @DisplayName("Should insert a pacient document into the DB")
    void pacientInsert() throws Exception {
        db.connectToDatabase();

        //TEST FOR DATA INSERTION
        p.insertIntoDB("1990505000000", "user", "nume", "prenume", "sex", "01/01/2000", "100", "100");
        expected.add("user");
        expected.add("nume");
        expected.add("prenume");
        expected.add("sex");
        expected.add("01/01/2000");
        expected.add("100");
        expected.add("100");
        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }
        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should update a document")
    void pacientUpdate() throws Exception {
        db.connectToDatabase();

        //TEST FOR DATA UPDATE
        p.updateInDB("1990505000000", "greutate", "200");
        expected.add("user");
        expected.add("nume");
        expected.add("prenume");
        expected.add("sex");
        expected.add("01/01/2000");
        expected.add("200");
        expected.add("100");

        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }
        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should remove a document")
    void pacientRemove() throws Exception {
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
    void pacientGetDocByField() throws Exception {
        db.connectToDatabase();

        for (Map<String, Object> map : p.getDocumentByField("data_nastere", "01/01/2000")) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        expected.add("user");
        expected.add("nume");
        expected.add("prenume");
        expected.add("sex");
        expected.add("01/01/2000");
        expected.add("100");
        expected.add("100");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should get a document by ID")
    void pacientGetDocByID() throws Exception {
        db.connectToDatabase();

        for (Map.Entry<String, Object> entry : p.getDocumentByID("1990505000000").entrySet()) {
            actual.add(entry.getValue().toString());
        }

        expected.add("user");
        expected.add("nume");
        expected.add("prenume");
        expected.add("sex");
        expected.add("01/01/2000");
        expected.add("100");
        expected.add("100");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should get a collection")
    void pacientGetCollection() throws Exception {
        db.connectToDatabase();

        for (Map<String, Object> map : p.getCollection()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        expected.add("test4");
        expected.add("nume");
        expected.add("prenume");
        expected.add("M");
        expected.add("01-01-2000");
        expected.add("67");
        expected.add("178");

        expected.add("user");
        expected.add("nume");
        expected.add("prenume");
        expected.add("sex");
        expected.add("01/01/2000");
        expected.add("100");
        expected.add("100");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }
}
