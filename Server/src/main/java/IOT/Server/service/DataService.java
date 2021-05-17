package IOT.Server.service;

import IOT.Server.dao.Conturi;
import IOT.Server.dao.InvalidNrOfArgsException;
import IOT.Server.dao.Pacient;
import IOT.Server.dao.Persoana;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class DataService {
    public void importData(String username ,String puls,String calorii, String nr_pasi) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {
        new Pacient().insertCollectionByName(new Conturi().getCNP(username),"date",puls,calorii,nr_pasi);
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
