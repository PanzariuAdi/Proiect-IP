package IOT.Server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    String username;
    String time;
    String puls;
    String calorii;
    String nr_pasi;
    String nivel_oxigen;
    String calitate_somn;
    public Data(
            @JsonProperty("username")String username,
            @JsonProperty("time") String time,
            @JsonProperty("puls") String puls,
            @JsonProperty("calorii") String calorii,
            @JsonProperty("nr_pasi") String nr_pasi,
            @JsonProperty("nivel_oxigen") String nivel_oxigen,
            @JsonProperty("calitate_somn") String calitate_somn
    ){
      this.username = username;
      this.time = time;
      this.puls = puls;
      this.calitate_somn = calitate_somn;
      this.calorii = calorii;
      this.nivel_oxigen = nivel_oxigen;
      this.nr_pasi = nr_pasi;
    }

    public String getCalitate_somn() {
        return calitate_somn;
    }

    public String getCalorii() {
        return calorii;
    }

    public String getNivel_oxigen() {
        return nivel_oxigen;
    }

    public String getNr_pasi() {
        return nr_pasi;
    }

    public String getPuls() {
        return puls;
    }

    public String getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public void setNivel_oxigen(String nivel_oxigen) {
        this.nivel_oxigen = nivel_oxigen;
    }
}
