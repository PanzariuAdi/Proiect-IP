package client;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientCommunicationHandlerTest {
    private static JSONObject expectedPacient;
    private static JSONObject expectedIstoric;
    private static JSONObject expectedDiagnostic;
    private static JSONObject expectedAsistent;
    private static JSONObject expectedPacientList;
    private static JSONObject expectedDoctor;
    @BeforeAll()
    static void createObjects(){
        expectedPacient=new JSONObject();
        expectedPacient.append("data_nastere","test");
        expectedPacient.append("cnp","cnp");
        expectedPacient.append("greutate","test");
        expectedPacient.append("sex","test");
        expectedPacient.append("adresa","test");
        expectedPacient.append("prenume","test");
        expectedPacient.append("nume","test");
        expectedPacient.append("inaltime","test");
        expectedPacient.append("diagnostic","raceala");
        //System.out.println("before");

        expectedIstoric=new JSONObject();
        expectedIstoric.append("data_externare2","test");
        expectedIstoric.append("data_externare1","alsoidk");
        expectedIstoric.append("data_externare0","test");
        expectedIstoric.append("size","3");
        expectedIstoric.append("spital0","test");
        expectedIstoric.append("diagnostic1","gripa");
        expectedIstoric.append("cnp_doctor0","test");
        expectedIstoric.append("diagnostic2","test");
        expectedIstoric.append("spital2","test");
        expectedIstoric.append("diagnostic0","test");
        expectedIstoric.append("spital1","spitalu' de urgente Darabani");
        expectedIstoric.append("cnp_doctor1","idk");
        expectedIstoric.append("cnp_doctor2","test");

        expectedDiagnostic=new JSONObject();
        expectedDiagnostic.append("i0","Pastile");
        expectedDiagnostic.append("i1","descriere");
        expectedDiagnostic.append("i2","Sa stai in pat");
        expectedDiagnostic.append("csize","2");
        expectedDiagnostic.append("c0","descriereTest");
        expectedDiagnostic.append("c1","apa");
        expectedDiagnostic.append("isize","3");
        expectedDiagnostic.append("medicament1","nume_medicament");
        expectedDiagnostic.append("medicament0","medicament1");
        expectedDiagnostic.append("diagnostic","gripa");
        expectedDiagnostic.append("msize","2");
        expectedDiagnostic.append("descriere1","oral");
        expectedDiagnostic.append("descriere0","mod_administrare");

        expectedAsistent=new JSONObject();
        expectedAsistent.append("data_nastere","test");
        expectedAsistent.append("sectie","neurologie");
        expectedAsistent.append("nume","test");
        expectedAsistent.append("prenume","test");
        expectedAsistent.append("spital"," test");
        expectedAsistent.append("adresa","test");
        expectedAsistent.append("sex","test");

        expectedPacientList=new JSONObject();
        expectedPacientList.append("p0","321");
        expectedPacientList.append("p1","cnp");
        expectedPacientList.append("psize","2");

        expectedDoctor=new JSONObject();
        expectedDoctor.append("data_nastere","test");
        expectedDoctor.append("specializare","test");
        expectedDoctor.append("prenume","test");
        expectedDoctor.append("sex","test");
        expectedDoctor.append("birou","test");
        expectedDoctor.append("adresa","test");
        expectedDoctor.append("spital","test");
        expectedDoctor.append("nume","test");
        expectedDoctor.append("grad","test");
    }
    @Test
    void testLoginNegativ() {
        assertNotEquals(1, ClientCommunicationHandler.login("user","password"));
        assertNotEquals(1, ClientCommunicationHandler.login("Andrei","aaa"));
    }

    @Test
    void testGetPacientPozitivDataNastere() {
        JSONObject result= ClientCommunicationHandler.getPacient("user");
        assertEquals(expectedPacient.get("data_nastere").toString(),"[\""+result.get("data_nastere")+"\"]");
    }

    @Test
    void testGetPacientPozitivCnp(){
        JSONObject result= ClientCommunicationHandler.getPacient("user");
        assertEquals(expectedPacient.get("cnp").toString(),"[\""+result.get("cnp")+"\"]");
    }

    @Test
    void testGetPacientPozitivGreutate(){
        JSONObject result= ClientCommunicationHandler.getPacient("user");
        assertEquals(expectedPacient.get("greutate").toString(),"[\""+result.get("greutate")+"\"]");
    }

    @Test
    void testGetPacientPozitivSex(){
        JSONObject result= ClientCommunicationHandler.getPacient("user");
        assertEquals(expectedPacient.get("sex").toString(),"[\""+result.get("sex")+"\"]");
    }

    @Test
    void testGetPacientPozitivAdresa(){
        JSONObject result= ClientCommunicationHandler.getPacient("user");
        assertEquals(expectedPacient.get("adresa").toString(),"[\""+result.get("adresa")+"\"]");
    }

    @Test
    void testGetPacientPozitivNume(){
        JSONObject result= ClientCommunicationHandler.getPacient("user");
        assertEquals(expectedPacient.get("nume").toString(),"[\""+result.get("nume")+"\"]");
    }
    @Test
    void testGetPacientPozitivPrenume(){
        JSONObject result= ClientCommunicationHandler.getPacient("user");
        assertEquals(expectedPacient.get("prenume").toString(),"[\""+result.get("prenume")+"\"]");
    }
    @Test
    void testGetPacientPozitivInaltime(){
        JSONObject result= ClientCommunicationHandler.getPacient("user");
        assertEquals(expectedPacient.get("inaltime").toString(),"[\""+result.get("inaltime")+"\"]");
    }

    @Test
    void testGetPacientNegativDiagnostic(){
        JSONObject result= ClientCommunicationHandler.getPacient("user");
        assertNotEquals(expectedPacient.get("diagnostic").toString(),"[\""+result.get("diagnostic")+"\"]");
    }
    @Test
    void testGetPacientByCnpPozitivDataNastere() {
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("cnp");
        assertEquals(expectedPacient.get("data_nastere").toString(),"[\""+result.get("data_nastere")+"\"]");
    }


    @Test
    void testGetPacientByCnpPozitivCnp(){
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("cnp");
        assertEquals(expectedPacient.get("cnp").toString(),"[\""+result.get("cnp")+"\"]");
    }

    @Test
    void testGetPacientByCnpPozitivGreutate(){
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("cnp");
        assertEquals(expectedPacient.get("greutate").toString(),"[\""+result.get("greutate")+"\"]");
    }

    @Test
    void testGetPacientByCnpPozitivSex(){
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("cnp");
        assertEquals(expectedPacient.get("sex").toString(),"[\""+result.get("sex")+"\"]");
    }

    @Test
    void testGetPacientByCnpPozitivAdresa(){
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("cnp");
        assertEquals(expectedPacient.get("adresa").toString(),"[\""+result.get("adresa")+"\"]");
    }

    @Test
    void testGetPacientByCnpPozitivNume(){
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("cnp");
        assertEquals(expectedPacient.get("nume").toString(),"[\""+result.get("nume")+"\"]");
    }
    @Test
    void testGetPacientByCnpPozitivPrenume(){
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("cnp");
        assertEquals(expectedPacient.get("prenume").toString(),"[\""+result.get("prenume")+"\"]");
    }
    @Test
    void testGetPacientByCnpPozitivInaltime(){
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("cnp");
        assertEquals(expectedPacient.get("inaltime").toString(),"[\""+result.get("inaltime")+"\"]");
    }

    @Test
    void testGetPacientByCnpNegativDiagnostic(){
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("cnp");
        assertNotEquals(expectedPacient.get("diagnostic").toString(),"[\""+result.get("diagnostic")+"\"]");
    }
    @Test
    void testGetIstoricPozitivDataExternare2() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertEquals(expectedIstoric.get("data_externare2").toString(),"[\""+result.get("data_externare2")+"\"]");
    }

    @Test
    void testGetIstoricPozitivDataExternare1() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertEquals(expectedIstoric.get("data_externare1").toString(),"[\""+result.get("data_externare1")+"\"]");
    }
    @Test
    void testGetIstoricPozitivDataExternare0() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertEquals(expectedIstoric.get("data_externare0").toString(),"[\""+result.get("data_externare0")+"\"]");
    }
    @Test
    void testGetIstoricPozitivSize() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertEquals(expectedIstoric.get("size").toString(),"[\""+result.get("size")+"\"]");
    }
    @Test
    void testGetIstoricPozitivSpital0() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertEquals(expectedIstoric.get("spital0").toString(),"[\""+result.get("spital0")+"\"]");
    }
    @Test
    void testGetIstoricNegativDiagnostic1() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertNotEquals(expectedIstoric.get("diagnostic1").toString(),"[\""+result.get("diagnostic1")+"\"]");
    }
    @Test
    void testGetIstoricPozitivCnpDoctor0() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertEquals(expectedIstoric.get("cnp_doctor0").toString(),"[\""+result.get("cnp_doctor0")+"\"]");
    }
    @Test
    void testGetIstoricPozitivDiagnostic2() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertEquals(expectedIstoric.get("diagnostic2").toString(),"[\""+result.get("diagnostic2")+"\"]");
    }
    @Test
    void testGetIstoricPozitivSpital2() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertEquals(expectedIstoric.get("spital2").toString(),"[\""+result.get("spital2")+"\"]");
    }
    @Test
    void testGetIstoricPozitivDiagnostic0() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertEquals(expectedIstoric.get("diagnostic0").toString(),"[\""+result.get("diagnostic0")+"\"]");
    }
    @Test
    void testGetIstoricPozitivSpital1() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertEquals(expectedIstoric.get("spital1").toString(),"[\""+result.get("spital1")+"\"]");
    }
    @Test
    void testGetIstoricPozitivCnpDoctor1() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertEquals(expectedIstoric.get("cnp_doctor1").toString(),"[\""+result.get("cnp_doctor1")+"\"]");
    }
    @Test
    void testGetIstoricPozitivCnpDoctor2() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertEquals(expectedIstoric.get("cnp_doctor2").toString(),"[\""+result.get("cnp_doctor2")+"\"]");
    }


    @Test
    void testGetIstoricByCnpPozitivDataExternare2() {
        JSONObject result= ClientCommunicationHandler.getIstoricByCNP("cnp");
        assertEquals(expectedIstoric.get("data_externare2").toString(),"[\""+result.get("data_externare2")+"\"]");
    }

    @Test
    void testGetIstoricByCnpPozitivDiagnostic1() {
        JSONObject result= ClientCommunicationHandler.getIstoricByCNP("cnp");
        assertNotEquals(expectedIstoric.get("diagnostic1").toString(),"[\""+result.get("diagnostic1")+"\"]");
    }

    @Test
    void testGetDiagnosticNegativI0() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertNotEquals(expectedDiagnostic.get("i0").toString(),"[\""+result.get("i0")+"\"]");
    }

    @Test
    void testGetDiagnosticPozitivI1() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertEquals(expectedDiagnostic.get("i1").toString(),"[\""+result.get("i1")+"\"]");
    }

    @Test
    void testGetDiagnosticPozitivI2() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertEquals(expectedDiagnostic.get("i2").toString(),"[\""+result.get("i2")+"\"]");
    }
    @Test
    void testGetDiagnosticPozitivCsize() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertEquals(expectedDiagnostic.get("csize").toString(),"[\""+result.get("csize")+"\"]");
    }
    @Test
    void testGetDiagnosticPozitivC0() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertEquals(expectedDiagnostic.get("c0").toString(),"[\""+result.get("c0")+"\"]");
    }
    @Test
    void testGetDiagnosticNegativC1() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertNotEquals(expectedDiagnostic.get("c1").toString(),"[\""+result.get("c1")+"\"]");
    }
    @Test
    void testGetDiagnosticPozitivIsize() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertEquals(expectedDiagnostic.get("isize").toString(),"[\""+result.get("isize")+"\"]");
    }
    @Test
    void testGetDiagnosticPozitivMedicament1() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertEquals(expectedDiagnostic.get("medicament1").toString(),"[\""+result.get("medicament1")+"\"]");
    }
    @Test
    void testGetDiagnosticPozitivMedicament0() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertEquals(expectedDiagnostic.get("medicament0").toString(),"[\""+result.get("medicament0")+"\"]");
    }
    @Test
    void testGetDiagnosticNegativDiagnostic() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertNotEquals(expectedDiagnostic.get("diagnostic").toString(),"[\""+result.get("diagnostic")+"\"]");
    }
    @Test
    void testGetDiagnosticPozitivMsize() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertEquals(expectedDiagnostic.get("msize").toString(),"[\""+result.get("msize")+"\"]");
    }
    @Test
    void testGetDiagnosticPozitivDescriere1() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertEquals(expectedDiagnostic.get("descriere1").toString(),"[\""+result.get("descriere1")+"\"]");
    }
    @Test
    void testGetDiagnosticPozitivDescriere0() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertEquals(expectedDiagnostic.get("descriere0").toString(),"[\""+result.get("descriere0")+"\"]");
    }

    @Test
    void testGetDiagnosticByCnpPozitivDescriere0() {
        JSONObject result= ClientCommunicationHandler.getDiagnosticByCNP("cnp");
        assertEquals(expectedDiagnostic.get("descriere0").toString(),"[\""+result.get("descriere0")+"\"]");
    }
    @Test
    void testGetDiagnosticByCnpNegativDiagnostic() {
        JSONObject result= ClientCommunicationHandler.getDiagnosticByCNP("cnp");
        assertNotEquals(expectedDiagnostic.get("diagnostic").toString(),"[\""+result.get("diagnostic")+"\"]");
    }

    @Test
    void testGetAsistentPozitivDataNastere() {
        JSONObject result= ClientCommunicationHandler.getAsistent("user");
        assertEquals(expectedAsistent.get("data_nastere").toString(),"[\""+result.get("data_nastere")+"\"]");
    }
    @Test
    void testGetAsistentPozitivSectie() {
        JSONObject result= ClientCommunicationHandler.getAsistent("user");
        assertEquals(expectedAsistent.get("sectie").toString(),"[\""+result.get("sectie")+"\"]");
    }
    @Test
    void testGetAsistentPozitivNume() {
        JSONObject result= ClientCommunicationHandler.getAsistent("user");
        assertEquals(expectedAsistent.get("nume").toString(),"[\""+result.get("nume")+"\"]");
    }
    @Test
    void testGetAsistentPozitivPrenume() {
        JSONObject result= ClientCommunicationHandler.getAsistent("user");
        assertEquals(expectedAsistent.get("prenume").toString(),"[\""+result.get("prenume")+"\"]");
    }
    @Test
    void testGetAsistentPozitivSex() {
        JSONObject result= ClientCommunicationHandler.getAsistent("user");
        assertEquals(expectedAsistent.get("sex").toString(),"[\""+result.get("sex")+"\"]");
    }
    @Test
    void testGetAsistentPozitivSpital() {
        JSONObject result= ClientCommunicationHandler.getAsistent("user");
        assertEquals(expectedAsistent.get("spital").toString(),"[\""+result.get("spital")+"\"]");
    }

    @Test
    void testGetAsistentPozitivAdresa() {
        JSONObject result= ClientCommunicationHandler.getAsistent("user");
        assertEquals(expectedAsistent.get("adresa").toString(),"[\""+result.get("adresa")+"\"]");
    }
    @Test
    void testGetPacientListPozitivP0() {
        JSONObject result= ClientCommunicationHandler.getPacientList();
        assertEquals(expectedPacientList.get("p0").toString(),"[\""+result.get("p0")+"\"]");
        System.out.println(result.get("p0"));
        System.out.println(expectedPacientList.get("p0"));
    }
    @Test
    void testGetPacientListPozitivP1() {
        JSONObject result= ClientCommunicationHandler.getPacientList();
        assertEquals(expectedPacientList.get("p1").toString(),"[\""+result.get("p1")+"\"]");
    }
    @Test
    void testGetPacientListPozitivPsize() {
        JSONObject result= ClientCommunicationHandler.getPacientList();
        assertEquals(expectedPacientList.get("psize").toString(),"[\""+result.get("psize")+"\"]");
    }

    @Test
    void testGetDoctorPozitivDataNastere() {
        JSONObject result= ClientCommunicationHandler.getDoctor("user");
        assertEquals(expectedDoctor.get("data_nastere").toString(),"[\""+result.get("data_nastere")+"\"]");
    }

    @Test
    void testGetDoctorPozitivSpecializare() {
        JSONObject result= ClientCommunicationHandler.getDoctor("user");
        assertEquals(expectedDoctor.get("specializare").toString(),"[\""+result.get("specializare")+"\"]");
    }
    @Test
    void testGetDoctorPozitivPrenume() {
        JSONObject result= ClientCommunicationHandler.getDoctor("user");
        assertEquals(expectedDoctor.get("prenume").toString(),"[\""+result.get("prenume")+"\"]");
    }
    @Test
    void testGetDoctorPozitivNume() {
        JSONObject result= ClientCommunicationHandler.getDoctor("user");
        assertEquals(expectedDoctor.get("nume").toString(),"[\""+result.get("nume")+"\"]");
    }
    @Test
    void testGetDoctorPozitivSex() {
        JSONObject result= ClientCommunicationHandler.getDoctor("user");
        assertEquals(expectedDoctor.get("sex").toString(),"[\""+result.get("sex")+"\"]");
    }
    @Test
    void testGetDoctorPozitivBirou() {
        JSONObject result= ClientCommunicationHandler.getDoctor("user");
        assertEquals(expectedDoctor.get("birou").toString(),"[\""+result.get("birou")+"\"]");
    }
    @Test
    void testGetDoctorPozitivAdresa() {
        JSONObject result= ClientCommunicationHandler.getDoctor("user");
        assertEquals(expectedDoctor.get("adresa").toString(),"[\""+result.get("adresa")+"\"]");
    }
    @Test
    void testGetDoctorPozitivSpital() {
        JSONObject result= ClientCommunicationHandler.getDoctor("user");
        assertEquals(expectedDoctor.get("spital").toString(),"[\""+result.get("spital")+"\"]");
    }
    @Test
    void testGetDoctorPozitivGrad() {
        JSONObject result= ClientCommunicationHandler.getDoctor("user");
        assertEquals(expectedDoctor.get("grad").toString(),"[\""+result.get("grad")+"\"]");
    }


    @Test
    void testGetDoctorByCnpPozitivSpital() {
        JSONObject result= ClientCommunicationHandler.getDoctorByCNP("cnp");
        assertEquals(expectedDoctor.get("spital").toString(),"[\""+result.get("spital")+"\"]");
    }
    @Test
    void testGetDoctorByCnpPozitivSpecializare() {
        JSONObject result= ClientCommunicationHandler.getDoctorByCNP("cnp");
        assertEquals(expectedDoctor.get("specializare").toString(),"[\""+result.get("specializare")+"\"]");
    }

    @Test
    void testSendData() {
        String host ="https://radiant-bayou-97811.herokuapp.com";
        String tempurl = host+ "/api/data/import/bigData";
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("username","user2");
        jsonObject.append("puls","70");
        jsonObject.append("calorii","3000");
        jsonObject.append("nr_pasi","10000");
        ClientCommunicationHandler.postRequest(tempurl,jsonObject);
        ClientCommunicationHandler.sendData("user2","70","3000","10000");
    }

    @Test
    void testSendOxigen() {
        String host ="https://radiant-bayou-97811.herokuapp.com";
        String tempurl = host+ "/api/data/import/oxigen";
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("username","user2");
        jsonObject.append("nivel_oxigen","99");
        ClientCommunicationHandler.postRequest(tempurl,jsonObject);
        ClientCommunicationHandler.sendOxigen("user2","99");
    }

    @Test
    void testSendCalitateSomn() {
        String host ="https://radiant-bayou-97811.herokuapp.com";
        String tempurl = host+ "/api/data/import/calitate_somn";
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("username","user2");
        jsonObject.append("calitate_somn","10");
        ClientCommunicationHandler.postRequest(tempurl,jsonObject);
        ClientCommunicationHandler.sendCalitateSomn("user2","10");
    }

}