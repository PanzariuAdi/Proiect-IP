package IOT.Server.api;

import IOT.Server.dao.InvalidNrOfArgsException;
import IOT.Server.model.Data;
import IOT.Server.model.User;
import IOT.Server.service.UserService;
import IOT.Server.utility.CrateJSON;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RequestMapping("api/user")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "getPacient/{usr}")
    public String getPacient(@PathVariable("usr") String username) {
        Map<String, Object> objectMap = userService.getPacient(username);
        return CrateJSON.fromMap(objectMap);
    }
    @GetMapping(path = "login/{usr}/{pass}")
    public String login(@PathVariable("usr") String username, @PathVariable("pass") String password){

        Map<String, Object> objectList = UserService.login(   new User(username,password));
        return CrateJSON.fromMap(objectList);
    }
    @GetMapping(path = "pacient/istoric/{usr}")
    public String getIstoric(@PathVariable("usr") String user){
        List<Map<String, Object>> objectList = userService.getIstoric(user);
        return CrateJSON.fromListOfMaps(objectList);
    }
    @GetMapping(path = "pacient/diagnostic/{usr}")
    public String getDiagnostic(@PathVariable("usr") String username){
        Map<String, Object> objectMap = userService.getDiagnostic(username);
        System.out.println(objectMap);
        return CrateJSON.fromMap(objectMap);
    }
//,@JsonProperty("puls") String puls

    @PostMapping(path = "pacient/importData")
    public void importData(@RequestBody Data data
    ) throws InvalidNrOfArgsException, InterruptedException, ExecutionException {
        userService.importData(data.getUsername(),data.getTime(), data.getPuls(), data.getCalorii(), data.getNr_pasi(), data.getNivel_oxigen(), data.getCalitate_somn());
    }
}