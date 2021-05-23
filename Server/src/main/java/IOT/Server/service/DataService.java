package IOT.Server.service;

import IOT.Server.dao.*;
import IOT.Server.utility.Person;
import IOT.Server.utility.SendNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Component
public class DataService {
    public void importData(String username ,String puls,String calorii, String nr_pasi) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {
        String cnp = new Conturi().getCNP(username);
        String response = null;
        Person temp = new Person(cnp);

        List<Map<String,Object>> mapList = new Pacient().getCollectionByName(cnp,"date");
        ArrayList<Float> arrayList = new ArrayList<>();
        for(Map<String,Object> map : mapList){
            arrayList.add(Float.parseFloat((String) map.get("puls")));
        }
        temp.setHeartMeasures(arrayList);
        temp.setVarsta((String)new Persoana().getDocumentByID(cnp).get("data_nastere"));
        String msg = temp.addHeartMeasure(Float.parseFloat(puls));
        new Pacient().insertCollectionByName(cnp,"date",puls,calorii,nr_pasi);
        var person = new Persoana().getDocumentByID(cnp);
        var doctori =  new Doctor().getCollection();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(person.get("nume"));
        stringBuilder.append(" ");
        stringBuilder.append(person.get("prenume"));
        stringBuilder.append(" : ");
        stringBuilder.append(msg);
        for(var doctor : doctori) {
            String dcnp = (String) doctor.get("cnp");
            String dusr = new Conturi().getUsernameByCNP(dcnp);
            System.out.println(dusr);
            if(dusr!=null)
                SendNotification.send(stringBuilder.toString(), dusr);
        }
        SendNotification.send(stringBuilder.toString(), username);
    }

    public void importOxigen(String username, String oxigen) throws ExecutionException, InterruptedException {
        new Pacient().insertCollectionByName(new Conturi().getCNP(username),"nivel_oxigen",oxigen);
    }
    public void importCalitateSomn(String username, String calitate_somn) throws ExecutionException, InterruptedException {
        new Pacient().insertCollectionByName(new Conturi().getCNP(username),"calitate_somn",calitate_somn);
    }

    public List<Map<String, Object>> getCalorii(String cnp) {
        List<Map<String, Object>> result = null;
        try{
            result = new Pacient().getCollectionByName(cnp,"date");
            List<Map<String, Object>> tempList = new LinkedList<>();
            for(Map<String,Object> map : result){
                Map<String,Object> tempMap = new HashMap<>();
                tempMap.put("date",map.get("date"));
                tempMap.put("calorii",map.get("calorii"));
                tempList.add(tempMap);
            }
            result = tempList;
        } catch (Exception e) {
            List<Map<String, Object>> ErrorList = new LinkedList<>();
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("Error!!","Error on the server side!!");
            errorMap.put("error",e.getMessage());
            ErrorList.add(errorMap);
            return ErrorList;
        }
        finally {
            if(result == null || result.isEmpty()){
                result = new LinkedList<>();
                Map<String,Object> temp = new HashMap<>();
                temp.put("null","There is no such data!");
                result.add(temp);
            }
            return result;
        }
    }

    public List<Map<String, Object>> getPasi(String cnp) {
        List<Map<String, Object>> result = null;
        try{
            result = new Pacient().getCollectionByName(cnp,"date");
            List<Map<String, Object>> tempList = new LinkedList<>();
            for(Map<String,Object> map : result){
                Map<String,Object> tempMap = new HashMap<>();
                tempMap.put("date",map.get("date"));
                tempMap.put("nr_pasi",map.get("nr_pasi"));
                tempList.add(tempMap);
            }
            result = tempList;
        } catch (Exception e) {
            List<Map<String, Object>> ErrorList = new LinkedList<>();
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("Error!!","Error on the server side!!");
            errorMap.put("error",e.getMessage());
            ErrorList.add(errorMap);
            return ErrorList;
        }
        finally {
            if(result == null || result.isEmpty()){
                result = new LinkedList<>();
                Map<String,Object> temp = new HashMap<>();
                temp.put("null","There is no such data!");
                result.add(temp);
            }
            return result;
        }
    }
    public List<Map<String, Object>> getPuls(String cnp) {
        List<Map<String, Object>> result = null;
        try{
            result = new Pacient().getCollectionByName(cnp,"date");
            List<Map<String, Object>> tempList = new LinkedList<>();
            for(Map<String,Object> map : result){
                Map<String,Object> tempMap = new HashMap<>();
                tempMap.put("date",map.get("date"));
                tempMap.put("puls",map.get("puls"));
                tempList.add(tempMap);
            }
            result = tempList;
        } catch (Exception e) {
            List<Map<String, Object>> ErrorList = new LinkedList<>();
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("Error!!","Error on the server side!!");
            errorMap.put("error",e.getMessage());
            ErrorList.add(errorMap);
            return ErrorList;
        }
        finally {
            if(result == null || result.isEmpty()){
                result = new LinkedList<>();
                Map<String,Object> temp = new HashMap<>();
                temp.put("null","There is no such data!");
                result.add(temp);
            }
            return result;
        }
    }
    public List<Map<String, Object>> getNivelOxigen(String cnp) {
        List<Map<String, Object>> result = null;
        try{
            result = new Pacient().getCollectionByName(cnp,"nivel_oxigen");

        } catch (Exception e) {
            List<Map<String, Object>> ErrorList = new LinkedList<>();
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("Error!!","Error on the server side!!");
            errorMap.put("error",e.getMessage());
            ErrorList.add(errorMap);
            return ErrorList;
        }
        finally {
            if(result == null || result.isEmpty()){
                result = new LinkedList<>();
                Map<String,Object> temp = new HashMap<>();
                temp.put("null","There is no such data!");
                result.add(temp);
            }
            return result;
        }
    }
    public List<Map<String, Object>> getCalitateSomn(String cnp) {
        List<Map<String, Object>> result = null;
        try{
            result = new Pacient().getCollectionByName(cnp,"calitate_somn");

        } catch (Exception e) {
            List<Map<String, Object>> ErrorList = new LinkedList<>();
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("Error!!","Error on the server side!!");
            errorMap.put("error",e.getMessage());
            ErrorList.add(errorMap);
            return ErrorList;
        }
        finally {
            if(result == null || result.isEmpty()){
                result = new LinkedList<>();
                Map<String,Object> temp = new HashMap<>();
                temp.put("null","There is no such data!");
                result.add(temp);
            }
            return result;
        }
    }
}
