package com.helb.eatBelgium.model;

import java.util.List;

public class Panier {
    private List<String> listPanier;
    private String currentUser;
    private String date;

    public Panier(List<String> listPanier, String currentUser, String date) {
        this.listPanier = listPanier;
        this.currentUser = currentUser;
        this.date= date;
    }

    public Panier() {
    }

    public List<String> getListPanier() {
        return listPanier;
    }

    public void setListPanier(List<String> listPanier) {
        this.listPanier = listPanier;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
