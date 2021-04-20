package com.example.Entity;

public class Pacient {
    private int id;
    private String name;
    private String situation;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getSituation() {
        return situation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public Pacient(int id, String name, String situation) {
        this.id = id;
        this.name = name;
        this.situation = situation;
    }
    public Pacient(){}
}
