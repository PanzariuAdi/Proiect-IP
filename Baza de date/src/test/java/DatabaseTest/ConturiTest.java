package DatabaseTest;

import DAOClasses.Conturi;
import DAOClasses.Database;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConturiTest {
    private Database db = Database.getInstance();
    private Conturi p = new Conturi();

    @Test
    @DisplayName("Should check if user exists")
    public void checkIfUserExists() throws Exception {
        db.connectToDatabase();

        Assertions.assertTrue(p.checkIfUserExists("user"));
        Assertions.assertFalse(p.checkIfUserExists("none"));
    }

    @Test
    @DisplayName("Should check if password matches with user")
    public void getPassword() throws Exception {
        db.connectToDatabase();

        Assertions.assertEquals("test", p.getPassword("user"), "Password is ok");
    }

    @Test
    @DisplayName("Should check if role matches with user")
    public void getRol() throws Exception {
        db.connectToDatabase();

        Assertions.assertEquals("test", p.getRol("user"), "Rol is ok");
    }

    @Test
    @DisplayName("Should check if CNP matches with user")
    public void getCNP() throws Exception {
        db.connectToDatabase();

        Assertions.assertEquals("cnp", p.getCNP("user"));
    }
}
