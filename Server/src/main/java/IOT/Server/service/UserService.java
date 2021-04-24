package IOT.Server.service;

import IOT.Server.dao.*;
import IOT.Server.model.User;
import IOT.Server.utility.JoinMaps;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Component
public class UserService {

    public static Map<String,Object> login(User user){
        Map<String,Object> contMap = null;
        Map<String,Object> result = new HashMap<>();
        try{
            contMap = new Conturi().getDocumentByID(user.getName());
            if(!(contMap.isEmpty()) || contMap != null){
                if(contMap.get("password").equals(user.getPassword()))
                    result.put("login","true");
                else
                    result.put("login","false");

            }
            else{
                result.put("login","false");
            }
        } catch (Exception e) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("Error!!","Error on the server side!!");
        errorMap.put("error",e.getMessage());
        return errorMap;
    }
        finally {
        if(result == null || result.isEmpty()){
            result = new HashMap<>();
            result.put("null","There is no such data!");
        }
        return result;
    }
    }


    public Map<String ,Object> getPacient(String username)  {
        Map<String ,Object> objectMap = null;
        Map<String ,Object> contMap = null;
        Map<String ,Object> personMap = null;
        Map<String ,Object> result = null;
        try {
            contMap = new Conturi().getDocumentByID(username);
            objectMap =  new Pacient().getDocumentByID((String) contMap.get("cnp"));
            personMap = new Persoana().getDocumentByID((String) contMap.get("cnp"));

            result =  new HashMap<>();
            result = JoinMaps.joinMaps(result,personMap);
            result = JoinMaps.joinMaps(result,objectMap);
            result.put("rol",contMap.get("rol"));
            result.put("cnp",contMap.get("cnp"));
        } catch (Exception e) {
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("Error!!","Error on the server side!!");
            errorMap.put("error",e.getMessage());
            return errorMap;
        }
        finally {
            if(result == null || result.isEmpty()){
                result = new HashMap<>();
                result.put("null","There is no such data!");
            }
            return result;
        }
    }


    public List<Map<String, Object>> getIstoric(String user){
        Map<String ,Object> contMap = null;
        List<Map<String ,Object>> result = null;
        try {
            contMap = new Conturi().getDocumentByID(user);
            result = new IstoricPacient().getCollection((String) contMap.get("cnp"));
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


    public static Map<String, Object> getDiagnostic(String username){
        Map<String ,Object> contMap = null;
        Map<String,Object> pacientMap = null;
        Map<String ,Object> result = null;
        Map<String ,Object> medicamente = null;
        List<String> indicatii= null;
        List<String> contraindicatii = null;

        try {
            contMap = new Conturi().getDocumentByID(username);

            indicatii = new Indicatii().getCollection((String) contMap.get("cnp"));
            contraindicatii = new Contraindicatii().getCollection((String) contMap.get("cnp"));

            pacientMap = new Pacient().getDocumentByID((String) contMap.get("cnp"));

            medicamente = new Medicamente().getCollection((String) contMap.get("cnp"));
            result = new HashMap<>();
            result.put("isize",indicatii.size());
            result.put("csize",contraindicatii.size());

            int count = 0;
            for(String s : indicatii){

                result.put(new String("i" + count),s);
                count++;
            }

            count = 0;
            for(String s : contraindicatii){
                result.put(new String("c" + count),s);
                count++;
            }

            result.put("diagnostic", pacientMap.get("diagnostic"));

            count =0;
            for(String key : medicamente.keySet()){
                result.put(new String("medicament" + count),key);
                result.put(new String( "descriere" + count),medicamente.get(key));
                count++;
            }
            result.put("msize",count);

        } catch (Exception e) {
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("Error!!","Error on the server side!!");
            errorMap.put("error",e.getMessage());
            return errorMap;
        }
        finally {
            if(result == null || result.isEmpty()){
                result = new HashMap<>();
                result.put("null","There is no such data!");
            }
            return result;
        }
    }


    public void importData(String username, String time ,String puls,String calorii, String nr_pasi, String nivel_oxigen, String calitate_somn) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {
        Map<String,Object> contMap = new Conturi().getDocumentByID(username);
        new DatePacient().insertIntoDB2((String) contMap.get("cnp"),time,puls,calorii,nr_pasi,nivel_oxigen,calitate_somn);
    }
}
