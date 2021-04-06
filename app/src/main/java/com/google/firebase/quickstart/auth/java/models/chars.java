package com.google.firebase.quickstart.auth.java.models;

public class chars {

    private String nom;
    private String ref;


    public chars() { }

    public chars(String nom, String ref) {
        this.nom = nom;
        this.ref = ref;
    }

    // --- GETTERS ---
    public String getNom() { return nom; }
    public String getRef() { return ref; }

    // --- SETTERS ---
    public void setNom(String nom) { this.nom = nom; }
    public void setRef(String ref) { this.ref = ref; }
}