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
    private Pacient p = new Pacient();
    private List<String> actual = new ArrayList<>();
    private List<String> expected = new ArrayList<>();

    @Test
    @DisplayName("Should insert a pacient document into the DB")
    void pacientInsert() throws Exception {
        db.connectToDatabase();

        //TEST FOR DATA INSERTION
        p.insertIntoDB("321", "cancer de ficat", "420", "190");
        expected.add("cancer de ficat");
        expected.add("420");
        expected.add("190");

        for (Map.Entry<String, Object> entry : p.getDocumentByID("321").entrySet()) {
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
        p.updateInDB("321", "diagnostic", "kanker");
        expected.add("kanker");
        expected.add("420");
        expected.add("190");


        for (Map.Entry<String, Object> entry : p.getDocumentByID("321").entrySet()) {
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
        p.removeFromDB("321");
        for (Map.Entry<String, Object> entry : p.getDocumentByID("321").entrySet()) {
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

        for (Map<String, Object> map : p.getDocumentByField("diagnostic", "kanker")) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        expected.add("kanker");
        expected.add("420");
        expected.add("190");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Should get a document by ID")
    void pacientGetDocByID() throws Exception {
        db.connectToDatabase();

        for (Map.Entry<String, Object> entry : p.getDocumentByID("321").entrySet()) {
            actual.add(entry.getValue().toString());
        }

        expected.add("kanker");
        expected.add("420");
        expected.add("190");

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

        expected.add("kanker");
        expected.add("420");
        expected.add("190");

        expected.add("i-a cazut urechea");
        expected.add("test");
        expected.add("test");

        Assertions.assertLinesMatch(expected, actual);
        actual.clear();
        expected.clear();
    }

    /*
    @Test
    @DisplayName("Insert contraindicatii")
    void pacientInsertContraindicatii() throws Exception {
        db.connectToDatabase();

        p.insertContraindicatii("321", "contra", "indicatie");

        expected.add(("indicatie"));

        for (Map<String, Object> map : p.getContraindicatii("321")) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        Assertions.assertLinesMatch(expected, actual);

        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Insert indicatii")
    void pacientInsertIndicatii() throws Exception {
        db.connectToDatabase();

        p.insertIndicatii("321", "indicatie", "descriere");

        expected.add("descriere");

        for (Map<String, Object> map : p.getIndicatii("321")) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        Assertions.assertLinesMatch(expected, actual);

        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Insert istoric")
    void pacientInsertIstoric() throws Exception {
        db.connectToDatabase();

        p.insertIstoric("321", "data_internare", "test", "test", "test", "test");

        expected.add("test");
        expected.add("test");
        expected.add("test");
        expected.add("test");

        for (Map<String, Object> map : p.getIstoric("321")) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        Assertions.assertLinesMatch(expected, actual);

        actual.clear();
        expected.clear();
    }

    @Test
    @DisplayName("Insert medicamente")
    void pacientMedicamente() throws Exception {
        db.connectToDatabase();

        p.insertMedicamente("321", "medicament", "oral");

        expected.add("oral");

        for (Map<String, Object> map : p.getMedicamente("321")) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                actual.add(entry.getValue().toString());
            }
        }

        Assertions.assertLinesMatch(expected, actual);

        actual.clear();
        expected.clear();
    }
     */
}
