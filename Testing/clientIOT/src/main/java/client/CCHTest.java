package client;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CCHTest {


    @Test
    void login() {
        assertEquals(0,CCH.login("user","password"));
        assertEquals(0,CCH.login("Andrei","aaa"));
    }


    @Test
    void getPacient() {
        JSONObject expected=new JSONObject();
        expected.append("data_nastere","test");
        expected.append("cnp","cnp");
        expected.append("greutate","test");
        expected.append("sex","test");
        expected.append("adresa","test");

        JSONObject result=CCH.getPacient("user");
        assertEquals(expected.get("data_nastere").toString(),"[\""+result.get("data_nastere")+"\"]");
        assertEquals(expected.get("cnp").toString(),"[\""+result.get("cnp")+"\"]");
        assertEquals(expected.get("greutate").toString(),"[\""+result.get("greutate")+"\"]");
        assertEquals(expected.get("sex").toString(),"[\""+result.get("sex")+"\"]");
        assertEquals(expected.get("adresa").toString(),"[\""+result.get("adresa")+"\"]");

    }

    @Test
    void getIstoric() {
        JSONObject expected=new JSONObject();
        expected.append("data_externare2","test");
        expected.append("data_externare0","test");
        expected.append("diagnostic2","test");
        expected.append("spital1","spitalu' de urgente Darabani");
        expected.append("cnp_doctor2","test");

        JSONObject result=CCH.getIstoric("user");
        assertEquals(expected.get("data_externare0").toString(),"[\""+result.get("data_externare0")+"\"]");
        assertEquals(expected.get("data_externare2").toString(),"[\""+result.get("data_externare2")+"\"]");
        assertEquals(expected.get("diagnostic2").toString(),"[\""+result.get("diagnostic2")+"\"]");
        assertEquals(expected.get("spital1").toString(),"[\""+result.get("spital1")+"\"]");
        assertEquals(expected.get("cnp_doctor2").toString(),"[\""+result.get("cnp_doctor2")+"\"]");

    }

    @Test
    void getDiagnostic() {
        JSONObject expected=new JSONObject();
        expected.append("i1","descriere");
        expected.append("medicament0","nume_medicament");
        expected.append("msize","1");
        expected.append("csize2","2");
        expected.append("c0","descriereTest");

        JSONObject result=CCH.getIstoric("user");
        assertEquals(expected.get("i1").toString(),"[\""+result.get("i1")+"\"]");
        assertEquals(expected.get("medicament0").toString(),"[\""+result.get("medicament0")+"\"]");
        assertEquals(expected.get("msize").toString(),"[\""+result.get("1")+"\"]");
        assertEquals(expected.get("csize").toString(),"[\""+result.get("2")+"\"]");
        assertEquals(expected.get("c0").toString(),"[\""+result.get("c0")+"\"]");

    }

    @Test
    void sendData() {

        CCH.sendData("user2", "10 January 2021 10:00","70","3000","10000","4.5","45");
        //actualizare nr
    }

    @Test
    void getRequest() {
        String url="https://radiant-bayou-97811.herokuapp.com//api/user/getPacient/user";
        JSONObject result= CCH.getRequest(url);
        assertEquals("test",result.get("data_nastere"));
    }

    @Test
    void postRequest() {
        String url="https://radiant-bayou-97811.herokuapp.com//api/user/pacient/importData";
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("username","user2");
        jsonObject.append("time","15 April 2021 12:00");
        CCH.postRequest(url,jsonObject);
    }

}