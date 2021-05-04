package IOT.Server.service;

import IOT.Server.dao.Asistent;
import IOT.Server.dao.Conturi;
import IOT.Server.dao.Pacient;
import IOT.Server.dao.Persoana;
import IOT.Server.utility.JoinMaps;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AsistentService {
    public static Map<String,Object> getAsistent(String username){
        Map<String,Object> result = null;
        Map<String,Object> personMap = null;
        Map<String,Object> asistentMap = null;
        String cnp= null;
        try{
            cnp = new Conturi().getCNP(username);
            personMap = new Persoana().getDocumentByID(cnp);
            asistentMap = new Asistent().getDocumentByID(cnp);
            result = personMap;
            result = JoinMaps.joinMaps(result,asistentMap);
        }catch (Exception e){
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

   public static Map<String,Object> getPacientList() {
       Map<String, Object> result = new HashMap<>();
       List<Map<String, Object>> pacientList = null;
       try {
            pacientList = new Pacient().getCollection();

            int count = 0;
            for(Map<String, Object> pacient : pacientList){
                result.put("p" + count,pacient.get("cnp"));
                count ++;
            }
            result.put("psize",count);
       } catch (Exception e) {
           Map<String, Object> errorMap = new HashMap<>();
           errorMap.put("Error!!", "Error on the server side!!");
           errorMap.put("error", e.getMessage());
           return errorMap;
       } finally {
           if (result == null || result.isEmpty()) {
               result = new HashMap<>();
               result.put("null", "There is no such data!");
           }
           return result;
       }
   }
}
