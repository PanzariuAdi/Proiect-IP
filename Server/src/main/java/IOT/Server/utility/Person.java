package IOT.Server.utility;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Person {

    private String cnp;
    private ArrayList<Float> heartMeasures = new ArrayList<Float>();
    private int varsta;

    public Person(String cnp) { this.cnp = cnp; }


    public String getCnp() { return cnp; }
    public void setCnp(String cnp) { this.cnp = cnp; }

    public ArrayList<Float> getHeartMeasures() { return heartMeasures; }
    public void setHeartMeasures(ArrayList<Float> heartMeasures) { this.heartMeasures = heartMeasures; }

    public void setVarsta(String varsta) {
        this.varsta = getAgeInYears(varsta);
    }

    public int getVarsta() {
        return varsta;
    }

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

    public String addHeartMeasure(float value) {
        this.heartMeasures.add(value);

        if (standardDeviation() > 20) {
            return "Value too different from previous measurements !";
        }

        return checkHeartRate(value);
    }

    private int getAgeInYears(String varsta) {
//        String dateString = cnp.substring(1, 7);
//        int birthYear = Integer.parseInt(cnp.substring(1, 3));
//        if (cnp.charAt(0) == '5' || cnp.charAt(0) == '6') birthYear += 100;
//        Date date = new Date();
//        return date.getYear() - birthYear
        String year = varsta.substring(6,10);
        Integer currentYear = LocalDate.now().getYear();
        return currentYear- Integer.parseInt(year);
    }

    private String checkHeartRate(float value) {
        int personAge = varsta;
        if (personAge <= 1) {
            if (!(value >= 80 &&  value <= 160))
                return showMessage();
        } else if (personAge <= 2){
            if(!(value >= 80 && value <= 130))
                return showMessage();
        } else if (personAge <= 10) {
            if(!(value >= 70 && value <= 110))
                return showMessage();
        } else {
            if (!(value >= 60 && value <= 100))
                return showMessage();
        }
        return "Normal";
    }

    private String  showMessage() {
        return "Heart rate inappropriate for this age !";
    }

}

