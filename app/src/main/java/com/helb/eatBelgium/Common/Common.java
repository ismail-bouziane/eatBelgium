package com.helb.eatBelgium.Common;

import android.widget.ArrayAdapter;

import com.helb.eatBelgium.model.Product;
import com.helb.eatBelgium.model.User;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public static User currentUser;
    public static Product productInList;
    public static ArrayList<String> listCommandes = new ArrayList<>();
    public static ArrayList<Integer> listPrix = new ArrayList<>();
    public static int prixTotal=0;


    public void calcule() {

        for (Integer i : listPrix) {
            prixTotal += i;
        }

    }


}

