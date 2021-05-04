package IOT.Server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    String username;

    String puls;
    String calorii;
    String nr_pasi;
    public Data(
            @JsonProperty("username")String username,
            @JsonProperty("puls") String puls,
            @JsonProperty("calorii") String calorii,
            @JsonProperty("nr_pasi") String nr_pasi
    ){
      this.username = username;
      this.puls = puls;
      this.calorii = calorii;

      this.nr_pasi = nr_pasi;
    }



    public String getCalorii() {
        return calorii;
    }



    public String getNr_pasi() {
        return nr_pasi;
    }

    public String getPuls() {
        return puls;
    }



    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Data{" +
                "username='" + username + '\'' +
                ", puls='" + puls + '\'' +
                ", calorii='" + calorii + '\'' +
                ", nr_pasi='" + nr_pasi + '\'' +
                '}';
    }
}
