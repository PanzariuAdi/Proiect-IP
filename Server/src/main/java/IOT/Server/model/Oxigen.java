package IOT.Server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Oxigen {
    String nivel_oxigen;
    String username;
    Oxigen(@JsonProperty("nivel_oxigen")String oxigen,
           @JsonProperty("username")String username){
        this.nivel_oxigen = oxigen;
        this.username=username;
    }


    public String getNivel_oxigen() {
        return nivel_oxigen;
    }

    public String getUsername() {
        return username;
    }
}
