package com.google.firebase.quickstart.auth.java.models;

public class users {
    private String password;
    private String surnom;
    private int admin;
    private int nb_echec_solo;
    private int nb_success_solo;
    private int nb_echec_multi;
    private int nb_success_multi;
    




    public users() { }

    public users(String password, String surnom, int admin, int nb_echec_solo, int nb_success_solo, int nb_echec_multi, int nb_success_multi) {
        this.password = password;
        this.surnom = surnom;
        this.admin = admin;
        this.nb_echec_solo = nb_echec_solo;
        this.nb_success_solo = nb_success_solo;
        this.nb_echec_multi = nb_echec_multi;
        this.nb_success_multi = nb_success_multi;


    }

    // --- GETTERS ---
    public String getSurnom() { return surnom; }
    public String getPassword() { return password; }
    public int getAdmin() { return admin; }
    public int getNb_echec_solo() { return nb_echec_solo; }
    public int getNb_success_solo() { return nb_success_solo; }
    public int getNb_echec_multi() { return nb_echec_multi; }
    public int getNb_success_multi() { return nb_success_multi; }


    // --- SETTERS ---
    public void setSurnom(String surnom) { this.surnom = surnom; }
    public void setPassword(String password) { this.password = password; }
    public void setAdmin(int admin) { this.admin = admin; }
    public void setNb_echec_solo(int nb_echec_solo) { this.nb_echec_solo = nb_echec_solo; }
    public void setNb_success_solo(int nb_success_solo) { this.nb_success_solo = nb_success_solo; }
    public void setNb_echec_multi(int nb_echec_multi) { this.nb_echec_multi = nb_echec_multi; }
    public void setNb_success_multi(int nb_success_multi) { this.nb_success_multi = nb_success_multi; }


}
