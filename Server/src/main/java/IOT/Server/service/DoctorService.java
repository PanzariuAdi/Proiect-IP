package IOT.Server.service;

import IOT.Server.dao.Asistent;
import IOT.Server.dao.Conturi;
import IOT.Server.dao.Doctor;
import IOT.Server.dao.Persoana;
import IOT.Server.utility.JoinMaps;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DoctorService {

        public static Map<String,Object> getDoctor(String cnp){
            Map<String,Object> result = null;
            Map<String,Object> personMap = null;
            Map<String,Object> doctorMap = null;
            try{
                personMap = new Persoana().getDocumentByID(cnp);
                doctorMap = new Doctor().getDocumentByID(cnp);
                result = personMap;
                result = JoinMaps.joinMaps(result,doctorMap);
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
}
