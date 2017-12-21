package com.mkdingo.goran.finalnaaplikacija.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by goran on 21.12.17.
 */

public class Orders implements Serializable {

    int telnumber;
    ArrayList<Menu>naracki;

    public Orders(int telnumber, ArrayList<Menu> naracki) {
        this.telnumber = telnumber;
        this.naracki = naracki;
    }

    public int getTelnumber() {
        return telnumber;
    }

    public void setTelnumber(int telnumber) {
        this.telnumber = telnumber;
    }

    public ArrayList<Menu> getNaracki() {
        return naracki;
    }

    public void setNaracki(ArrayList<Menu> naracki) {
        this.naracki = naracki;
    }
}
