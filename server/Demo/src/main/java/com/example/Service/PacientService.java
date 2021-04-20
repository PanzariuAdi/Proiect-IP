package com.example.Service;

import com.example.Dao.PacientDao;
import com.example.Entity.Pacient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PacientService {

    @Autowired
    private PacientDao pacientDao;
    public Collection<Pacient> getAllPacients(){
        return  pacientDao.getAllPacients();
    }
    public Pacient getPacientById(int id){
        return this.pacientDao.getPacientById(id);
    }

    public void removePacientById(int id) {
        this.pacientDao.removePacientById(id);
    }

    public void updatePacient(Pacient pacient){
        this.pacientDao.updatePacient(pacient);
    }

    public void insertPacient(Pacient pacient) {
        this.pacientDao.insertPacientToDB(pacient);
    }
}
