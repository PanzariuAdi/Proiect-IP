package IOT.Server.api;

import IOT.Server.dao.Conturi;
import IOT.Server.service.DoctorService;
import IOT.Server.utility.CrateJSON;
import IOT.Server.utility.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("api/doctor")
@RestController
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("getDoctor/{usr}")
    public String getDoctor(@PathVariable("usr")String username){
        Map<String, Object> objectMap = null;
        try {
            if(new Conturi().checkIfUserExists(username) == false)
                throw new CustomException("User doesn't exist!");
            String cnp = new Conturi().getCNP(username);
            objectMap = doctorService.getDoctor(cnp);
            System.out.println(objectMap);
        } catch (Exception e){
            objectMap = new HashMap<>();
            objectMap.put("null","There is no such data!");
        }
        return CrateJSON.fromMap(objectMap);
    }

    @GetMapping("getDoctorByID/{id}")
    public String getDoctorById(@PathVariable("id")String id){
        Map<String, Object> objectMap = doctorService.getDoctor(id);
        return CrateJSON.fromMap(objectMap);
    }
}
