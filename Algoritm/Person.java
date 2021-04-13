package lab07;

import java.util.ArrayList;

public class Person {
    private String firstName;
    private String secondName;
    private String cnp;
    private String address;
    private ArrayList<Float> heartMeasures = new ArrayList<Float>();

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getSecondName() { return secondName; }
    public void setSecondName(String secondName) { this.secondName = secondName; }
    public String getCnp() { return cnp; }
    public void setCnp(String cnp) { this.cnp = cnp; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public ArrayList<Float> getHeartMeasures() { return heartMeasures; }
    public void setHeartMeasures(ArrayList<Float> heartMeasures) { this.heartMeasures = heartMeasures; }

    private float standardDeviation() { //Standard deviation
        float sum = heartMeasures.stream().reduce((float) 0, Float::sum);
        float media = sum / heartMeasures.size();
        float sd = 0;
        for (float i : heartMeasures) {
            sd += Math.pow((i - media), 2);
        }
        sd /= heartMeasures.size() - 1;
        sd = (float) Math.sqrt(sd);
        return sd;
    }

    public void addHeartMeasure(float value) {
        this.heartMeasures.add(value);
        if (standardDeviation() > 20) {
            System.out.println("Something IS NOT OK!");
        }
    }
}
