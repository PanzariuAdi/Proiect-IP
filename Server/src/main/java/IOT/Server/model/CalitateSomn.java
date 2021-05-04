package IOT.Server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CalitateSomn {

    String calitate_somn;
    String username;

    CalitateSomn(@JsonProperty("calitate_somn")String calitate_somn,
                 @JsonProperty("username")String username){
        this.calitate_somn = calitate_somn;
        this.username = username;
    }
    public String getCalitate_somn() {
        return calitate_somn;
    }

    public String getUsername() {
        return username;
    }
}
