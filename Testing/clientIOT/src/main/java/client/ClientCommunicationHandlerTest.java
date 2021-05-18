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
    private static JSONObject expectedCalorii;
    private static JSONObject expectedPuls;
    private static JSONObject expectedPasi;
    private static JSONObject expectedNivelOxigen;
    private static JSONObject expectedCalitateSomn;
    private static JSONObject expectedRol;
    @BeforeAll()
    static void createObjects(){
        expectedPacient=new JSONObject();
        expectedPacient.append("data_nastere","10-05-1984");
        expectedPacient.append("cnp","321");
        expectedPacient.append("greutate","76");
        expectedPacient.append("sex","M");
        expectedPacient.append("adresa","Str. Florilor 5");
        expectedPacient.append("prenume","Ion");
        expectedPacient.append("nume","Popescu");
        expectedPacient.append("inaltime","185");
        expectedPacient.append("diagnostic","raceala");
        //System.out.println("before");

        expectedIstoric=new JSONObject();
        expectedIstoric.append("data_externare0","11-12-2020");
        expectedIstoric.append("size","0");
        expectedIstoric.append("spital0","Spitalul Militar Iasi");
        expectedIstoric.append("cnp_doctor0","1840510999999");
        expectedIstoric.append("diagnostic0","test");

        expectedDiagnostic=new JSONObject();
        expectedDiagnostic.append("i0","Sa bea multa apa");
        expectedDiagnostic.append("csize","0");
        expectedDiagnostic.append("c0","Sa nu faca efort fizic");
        expectedDiagnostic.append("isize","0");
        expectedDiagnostic.append("medicament0","medicament");
        expectedDiagnostic.append("diagnostic","gripa");
        expectedDiagnostic.append("msize","0");
        expectedDiagnostic.append("descriere0","oral");

        expectedAsistent=new JSONObject();
        expectedAsistent.append("data_nastere","03-04-1980");
        expectedAsistent.append("sectie","Pediatrie");
        expectedAsistent.append("nume","Acatrinei");
        expectedAsistent.append("prenume","Ioana");
        expectedAsistent.append("spital","Spitalul Militar Iasi");
        expectedAsistent.append("adresa","Str. George Cosbuc, nr. 3");
        expectedAsistent.append("sex","F");

        expectedPacientList=new JSONObject();
        expectedPacientList.append("p0","321");
        expectedPacientList.append("psize","0");

        expectedDoctor=new JSONObject();
        expectedDoctor.append("data_nastere","07-06-1976");
        expectedDoctor.append("specializare","Neurochirurg");
        expectedDoctor.append("prenume","Marius");
        expectedDoctor.append("sex","M");
        expectedDoctor.append("birou","B143");
        expectedDoctor.append("adresa","Str. Florilor, nr. 12");
        expectedDoctor.append("spital","Spitalul Militar Constanta");
        expectedDoctor.append("nume","Maxim");
        expectedDoctor.append("grad","Sef de sectie");

        expectedCalorii=new JSONObject();
        expectedCalorii.append("calorii0","1931");
        expectedCalorii.append("size","1");
        expectedCalorii.append("calorii1","1931");
        expectedCalorii.append("calorii2","1931");
        expectedCalorii.append("date0","17 May 2021 19:52");
        expectedCalorii.append("date1","17 May 2021 19:55");
        expectedCalorii.append("date2","17 May 2021 19:56");

        expectedPuls=new JSONObject();
        expectedPuls.append("puls0","99");
        expectedPuls.append("puls1","100");
        expectedPuls.append("puls2","100");
        expectedPuls.append("size","0");
        expectedPuls.append("date0","17 May 2021 19:52");
        expectedPuls.append("date1","17 May 2021 19:55");
        expectedPuls.append("date2","17 May 2021 19:56");

        expectedPasi=new JSONObject();
        expectedPasi.append("nr_pasi0","5621");
        expectedPasi.append("nr_pasi1","5621");
        expectedPasi.append("nr_pasi2","5621");
        expectedPasi.append("size","0");
        expectedPasi.append("date0","17 May 2021 19:52");
        expectedPasi.append("date1","17 May 2021 19:55");
        expectedPasi.append("date2","17 May 2021 19:56");

        expectedNivelOxigen=new JSONObject();
        expectedNivelOxigen.append("value0","44");
        expectedNivelOxigen.append("size","0");
        expectedNivelOxigen.append("date0","17 May 2021 13:33");

        expectedCalitateSomn=new JSONObject();
        expectedCalitateSomn.append("calitate0","78");
        expectedCalitateSomn.append("calitate1","99");
        expectedCalitateSomn.append("size","0");
        expectedCalitateSomn.append("date0","17 May 2021 13:33");
        expectedCalitateSomn.append("date1","17 May 2021 13:34");

        expectedRol=new JSONObject();
        expectedRol.append("rol","doctor");
    }
    @Test
    void testLoginPozitiv(){
        assertEquals(1,ClientCommunicationHandler.login("maxim.marius76","neurochirurg"));
    }
    @Test
    void testLoginNegativ() {
        assertNotEquals(1, ClientCommunicationHandler.login("Andrei","1234"));
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
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("321");
        assertEquals(expectedPacient.get("data_nastere").toString(),"[\""+result.get("data_nastere")+"\"]");
    }


    @Test
    void testGetPacientByCnpPozitivCnp(){
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("321");
        assertEquals(expectedPacient.get("cnp").toString(),"[\""+result.get("cnp")+"\"]");
    }

    @Test
    void testGetPacientByCnpPozitivGreutate(){
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("321");
        assertEquals(expectedPacient.get("greutate").toString(),"[\""+result.get("greutate")+"\"]");
    }

    @Test
    void testGetPacientByCnpPozitivSex(){
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("321");
        assertEquals(expectedPacient.get("sex").toString(),"[\""+result.get("sex")+"\"]");
    }

    @Test
    void testGetPacientByCnpPozitivAdresa(){
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("321");
        assertEquals(expectedPacient.get("adresa").toString(),"[\""+result.get("adresa")+"\"]");
    }

    @Test
    void testGetPacientByCnpPozitivNume(){
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("321");
        assertEquals(expectedPacient.get("nume").toString(),"[\""+result.get("nume")+"\"]");
    }
    @Test
    void testGetPacientByCnpPozitivPrenume(){
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("321");
        assertEquals(expectedPacient.get("prenume").toString(),"[\""+result.get("prenume")+"\"]");
    }
    @Test
    void testGetPacientByCnpPozitivInaltime(){
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("321");
        assertEquals(expectedPacient.get("inaltime").toString(),"[\""+result.get("inaltime")+"\"]");
    }

    @Test
    void testGetPacientByCnpNegativDiagnostic(){
        JSONObject result= ClientCommunicationHandler.getPacientByCNP("321");
        assertNotEquals(expectedPacient.get("diagnostic").toString(),"[\""+result.get("diagnostic")+"\"]");
    }

    @Test
    void testGetIstoricPozitivDataExternare0() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertEquals(expectedIstoric.get("data_externare0").toString(),"[\""+result.get("data_externare0")+"\"]");
    }
    @Test
    void testGetIstoricNegativSize() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertNotEquals(expectedIstoric.get("size").toString(),"[\""+result.get("size")+"\"]");
    }
    @Test
    void testGetIstoricPozitivSpital0() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertEquals(expectedIstoric.get("spital0").toString(),"[\""+result.get("spital0")+"\"]");
    }
    @Test
    void testGetIstoricPozitivCnpDoctor0() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertEquals(expectedIstoric.get("cnp_doctor0").toString(),"[\""+result.get("cnp_doctor0")+"\"]");
    }
    @Test
    void testGetIstoricNegativDiagnostic0() {
        JSONObject result= ClientCommunicationHandler.getIstoric("user");
        assertNotEquals(expectedIstoric.get("diagnostic0").toString(),"[\""+result.get("diagnostic0")+"\"]");
    }

    @Test
    void testGetIstoricByCnpNegativDiagnostic0(){
        JSONObject result= ClientCommunicationHandler.getIstoricByCNP("321");
        assertNotEquals(expectedIstoric.get("diagnostic0").toString(),"[\""+result.get("diagnostic0")+"\"]");
    }

    @Test
    void testGetIstoricByCnpPozitivCnpDoctor0(){
        JSONObject result= ClientCommunicationHandler.getIstoricByCNP("321");
        assertEquals(expectedIstoric.get("cnp_doctor0").toString(),"[\""+result.get("cnp_doctor0")+"\"]");
    }
    @Test
    void testGetDiagnosticPozitivI0() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertEquals(expectedDiagnostic.get("i0").toString(),"[\""+result.get("i0")+"\"]");
    }


    @Test
    void testGetDiagnosticNegativCsize() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertNotEquals(expectedDiagnostic.get("csize").toString(),"[\""+result.get("csize")+"\"]");
    }
    @Test
    void testGetDiagnosticPozitivC0() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertEquals(expectedDiagnostic.get("c0").toString(),"[\""+result.get("c0")+"\"]");
    }

    @Test
    void testGetDiagnosticNegativIsize() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertNotEquals(expectedDiagnostic.get("isize").toString(),"[\""+result.get("isize")+"\"]");
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
    void testGetDiagnosticNegativMsize() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertNotEquals(expectedDiagnostic.get("msize").toString(),"[\""+result.get("msize")+"\"]");
    }

    @Test
    void testGetDiagnosticPozitivDescriere0() {
        JSONObject result= ClientCommunicationHandler.getDiagnostic("user");
        assertEquals(expectedDiagnostic.get("descriere0").toString(),"[\""+result.get("descriere0")+"\"]");
    }

    @Test
    void testGetDiagnosticByCnpPozitivDescriere0() {
        JSONObject result= ClientCommunicationHandler.getDiagnosticByCNP("321");
        assertEquals(expectedDiagnostic.get("descriere0").toString(),"[\""+result.get("descriere0")+"\"]");
    }
    @Test
    void testGetDiagnosticByCnpNegativDiagnostic() {
        JSONObject result= ClientCommunicationHandler.getDiagnosticByCNP("321");
        assertNotEquals(expectedDiagnostic.get("diagnostic").toString(),"[\""+result.get("diagnostic")+"\"]");
    }

    @Test
    void testGetAsistentPozitivDataNastere() {
        JSONObject result= ClientCommunicationHandler.getAsistent("acatrinei.ioana80");
        assertEquals(expectedAsistent.get("data_nastere").toString(),"[\""+result.get("data_nastere")+"\"]");
    }
    @Test
    void testGetAsistentPozitivSectie() {
        JSONObject result= ClientCommunicationHandler.getAsistent("acatrinei.ioana80");
        assertEquals(expectedAsistent.get("sectie").toString(),"[\""+result.get("sectie")+"\"]");
    }
    @Test
    void testGetAsistentPozitivNume() {
        JSONObject result= ClientCommunicationHandler.getAsistent("acatrinei.ioana80");
        assertEquals(expectedAsistent.get("nume").toString(),"[\""+result.get("nume")+"\"]");
    }
    @Test
    void testGetAsistentPozitivPrenume() {
        JSONObject result= ClientCommunicationHandler.getAsistent("acatrinei.ioana80");
        assertEquals(expectedAsistent.get("prenume").toString(),"[\""+result.get("prenume")+"\"]");
    }
    @Test
    void testGetAsistentPozitivSex() {
        JSONObject result= ClientCommunicationHandler.getAsistent("acatrinei.ioana80");
        assertEquals(expectedAsistent.get("sex").toString(),"[\""+result.get("sex")+"\"]");
    }
    @Test
    void testGetAsistentPozitivSpital() {
        JSONObject result= ClientCommunicationHandler.getAsistent("acatrinei.ioana80");
        assertEquals(expectedAsistent.get("spital").toString(),"[\""+result.get("spital")+"\"]");
    }

    @Test
    void testGetAsistentPozitivAdresa() {
        JSONObject result= ClientCommunicationHandler.getAsistent("acatrinei.ioana80");
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
    void testGetPacientListNegativPsize() {
        JSONObject result= ClientCommunicationHandler.getPacientList();
        assertNotEquals(expectedPacientList.get("psize").toString(),"[\""+result.get("psize")+"\"]");
    }

    @Test
    void testGetDoctorPozitivDataNastere() {
        JSONObject result= ClientCommunicationHandler.getDoctor("maxim.marius76");
        assertEquals(expectedDoctor.get("data_nastere").toString(),"[\""+result.get("data_nastere")+"\"]");
    }

    @Test
    void testGetDoctorPozitivSpecializare() {
        JSONObject result= ClientCommunicationHandler.getDoctor("maxim.marius76");
        assertEquals(expectedDoctor.get("specializare").toString(),"[\""+result.get("specializare")+"\"]");
    }
    @Test
    void testGetDoctorPozitivPrenume() {
        JSONObject result= ClientCommunicationHandler.getDoctor("maxim.marius76");
        assertEquals(expectedDoctor.get("prenume").toString(),"[\""+result.get("prenume")+"\"]");
    }
    @Test
    void testGetDoctorPozitivNume() {
        JSONObject result= ClientCommunicationHandler.getDoctor("maxim.marius76");
        assertEquals(expectedDoctor.get("nume").toString(),"[\""+result.get("nume")+"\"]");
    }
    @Test
    void testGetDoctorPozitivSex() {
        JSONObject result= ClientCommunicationHandler.getDoctor("maxim.marius76");
        assertEquals(expectedDoctor.get("sex").toString(),"[\""+result.get("sex")+"\"]");
    }
    @Test
    void testGetDoctorPozitivBirou() {
        JSONObject result= ClientCommunicationHandler.getDoctor("maxim.marius76");
        assertEquals(expectedDoctor.get("birou").toString(),"[\""+result.get("birou")+"\"]");
    }
    @Test
    void testGetDoctorPozitivAdresa() {
        JSONObject result= ClientCommunicationHandler.getDoctor("maxim.marius76");
        assertEquals(expectedDoctor.get("adresa").toString(),"[\""+result.get("adresa")+"\"]");
    }
    @Test
    void testGetDoctorPozitivSpital() {
        JSONObject result= ClientCommunicationHandler.getDoctor("maxim.marius76");
        assertEquals(expectedDoctor.get("spital").toString(),"[\""+result.get("spital")+"\"]");
    }
    @Test
    void testGetDoctorPozitivGrad() {
        JSONObject result= ClientCommunicationHandler.getDoctor("maxim.marius76");
        assertEquals(expectedDoctor.get("grad").toString(),"[\""+result.get("grad")+"\"]");
    }


    @Test
    void testGetDoctorByCnpPozitivSpital() {
        JSONObject result= ClientCommunicationHandler.getDoctorByCNP("1760607123456");
        assertEquals(expectedDoctor.get("spital").toString(),"[\""+result.get("spital")+"\"]");
    }
    @Test
    void testGetDoctorByCnpPozitivSpecializare() {
        JSONObject result= ClientCommunicationHandler.getDoctorByCNP("1760607123456");
        assertEquals(expectedDoctor.get("specializare").toString(),"[\""+result.get("specializare")+"\"]");
    }

    @Test
    void testGetCaloriiPozitivCalorii0(){
        JSONObject result= ClientCommunicationHandler.getCalorii("321");
        assertEquals(expectedCalorii.get("calorii0").toString(),"[\""+result.get("calorii0")+"\"]");
    }
    @Test
    void testGetCaloriiPozitivCalorii1(){
        JSONObject result= ClientCommunicationHandler.getCalorii("321");
        assertEquals(expectedCalorii.get("calorii1").toString(),"[\""+result.get("calorii1")+"\"]");
    }
    @Test
    void testGetCaloriiPozitivCalorii2(){
        JSONObject result= ClientCommunicationHandler.getCalorii("321");
        assertEquals(expectedCalorii.get("calorii2").toString(),"[\""+result.get("calorii2")+"\"]");
    }

    @Test
    void testGetCaloriiNegativSize(){
        JSONObject result= ClientCommunicationHandler.getCalorii("321");
        assertNotEquals(expectedCalorii.get("size").toString(),"[\""+result.get("size")+"\"]");
    }

    @Test
    void testGetCaloriiPozitivDate0(){
        JSONObject result= ClientCommunicationHandler.getCalorii("321");
        assertEquals(expectedCalorii.get("date0").toString(),"[\""+result.get("date0")+"\"]");
    }

    @Test
    void testGetCaloriiPozitivDate1(){
        JSONObject result= ClientCommunicationHandler.getCalorii("321");
        assertEquals(expectedCalorii.get("date1").toString(),"[\""+result.get("date1")+"\"]");
    }

    @Test
    void testGetCaloriiPozitivDate2(){
        JSONObject result= ClientCommunicationHandler.getCalorii("321");
        assertEquals(expectedCalorii.get("date2").toString(),"[\""+result.get("date2")+"\"]");
    }

    @Test
    void testGetPulsPozitivDate2(){
        JSONObject result= ClientCommunicationHandler.getPuls("321");
        assertEquals(expectedPuls.get("date2").toString(),"[\""+result.get("date2")+"\"]");
    }
    @Test
    void testGetPulsPozitivDate1(){
        JSONObject result= ClientCommunicationHandler.getPuls("321");
        assertEquals(expectedPuls.get("date1").toString(),"[\""+result.get("date1")+"\"]");
    }
    @Test
    void testGetPulsPozitivDate0(){
        JSONObject result= ClientCommunicationHandler.getPuls("321");
        assertEquals(expectedPuls.get("date0").toString(),"[\""+result.get("date0")+"\"]");
    }
    @Test
    void testGetPulsNegativSize(){
        JSONObject result= ClientCommunicationHandler.getPuls("321");
        assertNotEquals(expectedPuls.get("size").toString(),"[\""+result.get("size")+"\"]");
    }
    @Test
    void testGetPulsPozitivPuls0(){
        JSONObject result= ClientCommunicationHandler.getPuls("321");
        assertEquals(expectedPuls.get("puls0").toString(),"[\""+result.get("puls0")+"\"]");
    }
    @Test
    void testGetPulsPozitivPuls1(){
        JSONObject result= ClientCommunicationHandler.getPuls("321");
        assertEquals(expectedPuls.get("puls1").toString(),"[\""+result.get("puls1")+"\"]");
    }
    @Test
    void testGetPulsPozitivPuls2(){
        JSONObject result= ClientCommunicationHandler.getPuls("321");
        assertEquals(expectedPuls.get("puls2").toString(),"[\""+result.get("puls2")+"\"]");
    }
    @Test
    void testGetPasiPozitivNrPasi0(){
        JSONObject result= ClientCommunicationHandler.getPasi("321");
        assertEquals(expectedPasi.get("nr_pasi0").toString(),"[\""+result.get("nr_pasi0")+"\"]");
    }
    @Test
    void testGetPasiPozitivNrPasi1(){
        JSONObject result= ClientCommunicationHandler.getPasi("321");
        assertEquals(expectedPasi.get("nr_pasi1").toString(),"[\""+result.get("nr_pasi1")+"\"]");
    }
    @Test
    void testGetPasiPozitivNrPasi2(){
        JSONObject result= ClientCommunicationHandler.getPasi("321");
        assertEquals(expectedPasi.get("nr_pasi2").toString(),"[\""+result.get("nr_pasi2")+"\"]");
    }
    @Test
    void testGetPasiNegativSize(){
        JSONObject result= ClientCommunicationHandler.getPasi("321");
        assertNotEquals(expectedPasi.get("size").toString(),"[\""+result.get("size")+"\"]");
    }
    @Test
    void testGetPasiPozitivDate0(){
        JSONObject result= ClientCommunicationHandler.getPasi("321");
        assertEquals(expectedPasi.get("date0").toString(),"[\""+result.get("date0")+"\"]");
    }
    @Test
    void testGetPasiPozitivDate1(){
        JSONObject result= ClientCommunicationHandler.getPasi("321");
        assertEquals(expectedPasi.get("date1").toString(),"[\""+result.get("date1")+"\"]");
    }
    @Test
    void testGetPasiPozitivDate2(){
        JSONObject result= ClientCommunicationHandler.getPasi("321");
        assertEquals(expectedPasi.get("date2").toString(),"[\""+result.get("date2")+"\"]");
    }
    @Test
    void testGetNivelOxigenPozitivValue0(){
        JSONObject result= ClientCommunicationHandler.getNivelOxigen("321");
        assertEquals(expectedNivelOxigen.get("value0").toString(),"[\""+result.get("value0")+"\"]");
    }
    @Test
    void testGetNivelOxigenNegativSize(){
        JSONObject result= ClientCommunicationHandler.getNivelOxigen("321");
        assertNotEquals(expectedNivelOxigen.get("size").toString(),"[\""+result.get("size")+"\"]");
    }
    @Test
    void testGetNivelOxigenPozitivDate0(){
        JSONObject result= ClientCommunicationHandler.getNivelOxigen("321");
        assertEquals(expectedNivelOxigen.get("date0").toString(),"[\""+result.get("date0")+"\"]");
    }
    @Test
    void testGetCalitateSomnPozitivDate0(){
        JSONObject result= ClientCommunicationHandler.getCalitateSomn("321");
        assertEquals(expectedCalitateSomn.get("date0").toString(),"[\""+result.get("date0")+"\"]");
    }
    @Test
    void testGetCalitateSomnPozitivDate1(){
        JSONObject result= ClientCommunicationHandler.getCalitateSomn("321");
        assertEquals(expectedCalitateSomn.get("date1").toString(),"[\""+result.get("date1")+"\"]");
    }
    @Test
    void testGetCalitateSomnNegativSize(){
        JSONObject result= ClientCommunicationHandler.getCalitateSomn("321");
        assertNotEquals(expectedCalitateSomn.get("size").toString(),"[\""+result.get("size")+"\"]");
    }
    @Test
    void testGetCalitateSomnPozitivCalitate0(){
        JSONObject result= ClientCommunicationHandler.getCalitateSomn("321");
        assertEquals(expectedCalitateSomn.get("calitate0").toString(),"[\""+result.get("calitate0")+"\"]");
    }
    @Test
    void testGetCalitateSomnPozitivCalitate1(){
        JSONObject result= ClientCommunicationHandler.getCalitateSomn("321");
        assertEquals(expectedCalitateSomn.get("calitate1").toString(),"[\""+result.get("calitate1")+"\"]");
    }

    @Test
    void testGetRolPozitiv(){
        JSONObject result=ClientCommunicationHandler.getRol("maxim.marius76");
        assertEquals(expectedRol.get("rol").toString(),"[\""+result.get("rol")+"\"]");
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
        jsonObject.append("nivel_oxigen","90");
        ClientCommunicationHandler.postRequest(tempurl,jsonObject);
        ClientCommunicationHandler.sendOxigen("user2","91");
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