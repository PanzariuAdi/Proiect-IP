package IOT.Server.service;

import IOT.Server.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    public String login(User user){
        return "Succesfully logged in!";
    }
}
