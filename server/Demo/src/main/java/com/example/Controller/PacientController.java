package com.example.Controller;

import com.example.Entity.Pacient;
import com.example.Service.PacientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Collection;

@RestController
@RequestMapping("/pacients")
public class PacientController {

    @Autowired
    private PacientService pacientService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Pacient> getAllPacient(){
        return  pacientService.getAllPacients();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Pacient getPacientById(@PathVariable("id") int id){
        return pacientService.getPacientById(id);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public void deletePacientById(@PathVariable("id") int id){
        pacientService.removePacientById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePacient(@RequestBody Pacient pacient){
        pacientService.updatePacient(pacient);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertPacient(@RequestBody Pacient pacient){
        pacientService.insertPacient(pacient);
    }
}
