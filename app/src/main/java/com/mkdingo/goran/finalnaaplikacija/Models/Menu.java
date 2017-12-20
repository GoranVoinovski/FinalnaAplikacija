package com.mkdingo.goran.finalnaaplikacija.Models;

import java.io.Serializable;

/**
 * Created by goran on 18.12.17.
 */

public class Menu implements Serializable{

    String link;
    String price;
    String foodname;
    boolean isveg;

    public Menu(String link, String price, String foodname, boolean isveg) {
        this.link = link;
        this.price = price;
        this.foodname = foodname;
        this.isveg = isveg;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public boolean isIsveg() {
        return isveg;
    }

    public void setIsveg(boolean isveg) {
        this.isveg = isveg;
    }
}
