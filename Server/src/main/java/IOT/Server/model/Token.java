package IOT.Server.model;

import com.fasterxml.jackson.annotation.JsonProperty;




public class Token {
    String username;
    String token;
    public Token(@JsonProperty("username")String username,
                 @JsonProperty("token")String token){
        this.username=username;
        this.token=token;
    }
    public Token(){}

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Token{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
