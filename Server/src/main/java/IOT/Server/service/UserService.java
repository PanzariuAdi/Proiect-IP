package IOT.Server.service;

import IOT.Server.dao.*;
import IOT.Server.model.Token;
import IOT.Server.model.User;
import IOT.Server.utility.JoinMaps;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Component
public class UserService {

    public static Map<String,Object> login(User user){
        Map<String,Object> result = new HashMap<>();
        try{
            if(new Conturi().checkIfUserExists(user.getName())){
                if(new Conturi().getPassword(user.getName()).equals(user.getPassword()))
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


    public Map<String ,Object> getPacient(String cnp)  {
        Map<String ,Object> pacientMap = null;
        Map<String ,Object> personMap = null;
        Map<String ,Object> result = null;
        try {
            pacientMap =  new Pacient().getDocumentByID(cnp);
            personMap = new Persoana().getDocumentByID(cnp);

            result =  new HashMap<>();
            result = JoinMaps.joinMaps(result,personMap);
            result = JoinMaps.joinMaps(result,pacientMap);
            result.put("cnp",cnp);
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


    public List<Map<String, Object>> getIstoric(String cnp){
        List<Map<String ,Object>> result = null;
        try {
            result = new Pacient().getCollectionByName(cnp,"istoric");
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

    public static Map<String, Object> getDiagnostic(String cnp){
        Map<String,Object> pacientMap = null;
        Map<String ,Object> result = null;
        List<Map<String ,Object>> medicamente = null;
        List<Map<String,Object>> indicatii= null;
        List<Map<String,Object>> contraindicatii = null;

        try {
            Pacient p = new Pacient();
            indicatii = p.getCollectionByName(cnp,"indicatii");
            contraindicatii = p.getCollectionByName(cnp,"contraindicatii");

            pacientMap = p.getDocumentByID(cnp);

            medicamente = p.getCollectionByName(cnp,"medicamente");
            result = new HashMap<>();
            result.put("isize",indicatii.size());
            result.put("csize",contraindicatii.size());

            int count = 0;
            for(Map<String,Object> indicatiiMap: indicatii){
                for(String s : indicatiiMap.keySet()) {
                    result.put(new String("i" + count), indicatiiMap.get(s));
                    count++;
                }
            }
            count = 0;
            for(Map<String,Object> contraindicatiiMap: contraindicatii) {
                for (String s : contraindicatiiMap.keySet()) {
                    result.put(new String("c" + count), contraindicatiiMap.get(s));
                    count++;
                }
            }

            result.put("diagnostic", pacientMap.get("diagnostic"));

            count =0;
            for(Map<String,Object> medicamenteMap: medicamente) {
                for (String key : medicamenteMap.keySet()) {
                    result.put(new String("medicament" + count),key);
                    result.put(new String("descriere" + count), medicamenteMap.get(key));
                    count++;
                }
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


    public Map<String, Object> getRol(String username) {
        Map<String ,Object> result = null;
        try {
            String rol = new Conturi().getRol(username);
            result = new HashMap<>();
            result.put("rol",rol);
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

    public void insertToken(Token token) throws ExecutionException, InterruptedException, InvalidNrOfArgsException {
        new Conturi().insertToken(token.getUsername(), token.getToken());
    }
}
