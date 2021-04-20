package lab07;

import java.util.ArrayList;
import java.util.Date;

public class Person {
    private String firstName;
    private String secondName;
    private String cnp;
    private String address;

    private ArrayList<Float> heartMeasures = new ArrayList<Float>();

    public Person(String cnp) { this.cnp = cnp; }

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
            System.out.println("Value too different from previous measurements !");
        }

        checkHeartRate(value);
    }

    private int getAgeInYears() {
        String dateString = cnp.substring(1, 7);
        int birthYear = Integer.parseInt(cnp.substring(1, 3));
        if (cnp.charAt(0) == '5' || cnp.charAt(0) == '6') birthYear += 100;
        Date date = new Date();
        return date.getYear() - birthYear;
    }

    private void checkHeartRate(float value) {
        int personAge = getAgeInYears();
        if (personAge <= 1) {
            if (!(value >= 80 &&  value <= 160))
                showMessage();
        } else if (personAge <= 2){
            if(!(value >= 80 && value <= 130))
                showMessage();
        } else if (personAge <= 10) {
            if(!(value >= 70 && value <= 110))
                showMessage();
        } else {
            if (!(value >= 60 && value <= 100))
                showMessage();
        }
    }

    private void showMessage() {
        System.out.println("Heart rate inappropriate for this age !");
    }

}
