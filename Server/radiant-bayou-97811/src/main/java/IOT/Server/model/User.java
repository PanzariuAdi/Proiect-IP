package IOT.Server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private final String name;
    private final String password;

    public User(@JsonProperty("name") String name,
                @JsonProperty("password") String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}