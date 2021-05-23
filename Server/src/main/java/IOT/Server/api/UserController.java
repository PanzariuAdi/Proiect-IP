package IOT.Server.api;

import IOT.Server.dao.Conturi;
import IOT.Server.dao.InvalidNrOfArgsException;
import IOT.Server.model.Data;
import IOT.Server.model.Token;
import IOT.Server.model.User;
import IOT.Server.service.UserService;
import IOT.Server.utility.CrateJSON;
import IOT.Server.utility.CustomException;
import IOT.Server.utility.SendNotification;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
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
        Map<String, Object> objectMap = null;
        try {
            if(new Conturi().checkIfUserExists(username) == false)
                throw new CustomException("User doesn't exist!");
            String cnp = new Conturi().getCNP(username);
            objectMap = userService.getPacient(cnp);
        } catch (Exception e){
            objectMap = new HashMap<>();
            objectMap.put("null","There is no such data!");
        }
        return CrateJSON.fromMap(objectMap);
    }
    @GetMapping(path = "getPacientByCNP/{cnp}")
    public String getPacientByCNP(@PathVariable("cnp") String cnp){
        Map<String, Object> objectMap = userService.getPacient(cnp);
        return CrateJSON.fromMap(objectMap);
    }
    @GetMapping(path = "login/{usr}/{pass}")
    public String login(@PathVariable("usr") String username, @PathVariable("pass") String password){

        Map<String, Object> objectList = UserService.login(   new User(username,password));
        return CrateJSON.fromMap(objectList);
    }
    @GetMapping(path = "pacient/istoric/{usr}")
    public String getIstoric(@PathVariable("usr") String user){
        List<Map<String, Object>> objectList = null;
        try{
            if(new Conturi().checkIfUserExists(user) == false)
                throw new CustomException("User doesn't exist!");
            String cnp = new Conturi().getCNP(user);
            objectList = userService.getIstoric(cnp);
        }catch (Exception e){
            objectList = new LinkedList<>();
            Map<String,Object> errorMap = new HashMap<>();
            errorMap.put("null","There is no such data!");
        }
        return CrateJSON.fromListOfMapsWithKey(objectList);
    }
    @GetMapping(path = "pacient/istoricByCNP/{cnp}")
    public String getIstoricByCNP(@PathVariable("cnp")String cnp){
        List<Map<String, Object>> objectList = userService.getIstoric(cnp);
        return CrateJSON.fromListOfMapsWithKey(objectList);
    }
    @GetMapping(path = "pacient/diagnostic/{usr}")
    public String getDiagnostic(@PathVariable("usr") String username){
        Map<String, Object> objectMap = null;
        try {
            if(new Conturi().checkIfUserExists(username) == false)
                throw new CustomException("User doesn't exist!");
            String cnp = new Conturi().getCNP(username);
            objectMap = userService.getDiagnostic(cnp);
        } catch (Exception e){
            objectMap = new HashMap<>();
            objectMap.put("null","There is no such data!");
        }
        return CrateJSON.fromMap(objectMap);
    }
    @GetMapping(path = "pacient/diagnosticByCNP/{cnp}")
    public String getDiagnosticByCNP(@PathVariable("cnp")String cnp){
        Map<String, Object> objectMap = userService.getDiagnostic(cnp);
        return CrateJSON.fromMap(objectMap);
    }

    @GetMapping(path = "getRol/{username}")
    public String getRol(@PathVariable("username")String username){
        Map<String, Object> objectMap = userService.getRol(username);
        return CrateJSON.fromMap(objectMap);
    }

    @PostMapping(path ="token/insert")
    public void insertToken(@RequestBody Token token) throws ExecutionException, InterruptedException, InvalidNrOfArgsException {
        System.out.println("token received!" + token.getToken());
        userService.insertToken(token);
    }
    @PostMapping(path = "test")
    public void test() throws ExecutionException, InterruptedException {
        SendNotification.send("test","user");
    }

}