package com.example.Dao;

import com.example.Entity.Pacient;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PacientDao {

    private static Map<Integer, Pacient> pacients;
    static{
        pacients =new HashMap<Integer, Pacient>(){
            {
                put(1, new Pacient(1,"Alex","Normal"));
                put(2, new Pacient(2,"Adi","Normal"));
            }
        };
    }
    public Collection<Pacient> getAllPacients(){
        return  this.pacients.values();
    }

    public Pacient getPacientById(int id){
        return this.pacients.get(id);
    }

    public void removePacientById(int id) {
        this.pacients.remove(id);
    }

    public void updatePacient(Pacient pacient){
        Pacient p= pacients.get(pacient.getId());
        p.setSituation(pacient.getSituation());
        p.setName(pacient.getName());
        pacients.put(pacient.getId(),p);
    }

    public void insertPacientToDB(Pacient pacient) {
        this.pacients.put(pacient.getId(),pacient);
    }
}
