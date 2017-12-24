package com.mkdingo.goran.finalnaaplikacija.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by goran on 21.12.17.
 */

public class Orders implements Serializable {

    int telnumber;
    String username;
    public ArrayList<Menu>naracki;
    public ArrayList<String>restorants;

    public Orders(int telnumber,String username, ArrayList<Menu> naracki,ArrayList<String>restorants) {
        this.telnumber = telnumber;
        this.username = username;
        this.naracki = naracki;
        this.restorants = restorants;
    }

    public int getTelnumber() {
        return telnumber;
    }

    public void setTelnumber(int telnumber) {
        this.telnumber = telnumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Menu> getNaracki() {
        return naracki;
    }

    public void setNaracki(ArrayList<Menu> naracki) {
        this.naracki = naracki;
    }

    public ArrayList<String> getRestorants() {
        return restorants;
    }

    public void setRestorants(ArrayList<String> restorants) {
        this.restorants = restorants;
    }
}
