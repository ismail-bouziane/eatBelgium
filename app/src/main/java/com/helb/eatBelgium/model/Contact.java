package com.helb.eatBelgium.model;

public class Contact {
    private String numUtilisateur;
    private String message;

    public Contact(String numUtilisateur, String message) {
        this.numUtilisateur = numUtilisateur;
        this.message = message;
    }

    public String getNumUtilisateur() {
        return numUtilisateur;
    }

    public void setNumUtilisateur(String numUtilisateur) {
        this.numUtilisateur = numUtilisateur;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

