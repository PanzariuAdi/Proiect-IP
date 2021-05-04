package IOT.Server.api;

import IOT.Server.service.AsistentService;
import IOT.Server.utility.CrateJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("api/asistent")
@RestController
public class AsistentController {
    private final AsistentService asistentService;

    @Autowired
    public AsistentController(AsistentService asistentService) {
        this.asistentService = asistentService;
    }

    @GetMapping(path="getAsistent/{usr}")
    public String getAsistent(@PathVariable("usr")String username){
        Map<String, Object> objectMap = asistentService.getAsistent(username);
        return CrateJSON.fromMap(objectMap);
    }
    @GetMapping(path="getPacientList")
    public String getAsistentList(){
        Map<String,Object> pacientList = asistentService.getPacientList();
        return CrateJSON.fromMap(pacientList);
    }
}
