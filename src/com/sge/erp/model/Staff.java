package com.sge.erp.model;

public class Staff {
    private String dni;
    private String name;
    private String surname;
    private String job;

    public Staff(String dni, String name, String surname, String job) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.job = job;
    }

    public Staff(String dni, String name, String surname) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
    }

    public Staff(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
