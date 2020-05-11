package com.helb.eatBelgium.model;

import java.util.List;

public class Orders {
    private String currentUser;
    private String date;
    private List<String> listOrders;


    public Orders(String currentUser,  String date,List<String> listOrders) {
        this.currentUser = currentUser;
        this.date = date;
        this.listOrders = listOrders;

    }

    public Orders() {
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public List<String> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<String> listOrders) {
        this.listOrders = listOrders;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
