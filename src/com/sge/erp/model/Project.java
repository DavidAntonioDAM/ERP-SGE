package com.sge.erp.model;

public class Project {
    private int id_project;
    private String nif_client;
    private String name;
    private String description;
    private String deliver_date;

    public Project(int id_project, String nif_client, String name, String description, String deliver_date) {
        this.id_project = id_project;
        this.nif_client = nif_client;
        this.name = name;
        this.description = description;
        this.deliver_date = deliver_date;
    }

    public Project(String nif_client, String name, String description, String deliver_date) {
        this.nif_client = nif_client;
        this.name = name;
        this.description = description;
        this.deliver_date = deliver_date;
    }

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
    }

    public String getNif_client() {
        return nif_client;
    }

    public void setNif_client(String nif_client) {
        this.nif_client = nif_client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeliver_date() {
        return deliver_date;
    }

    public void setDeliver_date(String deliver_date) {
        this.deliver_date = deliver_date;
    }
}
