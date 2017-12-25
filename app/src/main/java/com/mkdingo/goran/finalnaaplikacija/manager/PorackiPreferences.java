package com.mkdingo.goran.finalnaaplikacija.manager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.mkdingo.goran.finalnaaplikacija.models.Orders;

/**
 * Created by goran on 25.12.17.
 */

public class PorackiPreferences {

        private static SharedPreferences getPreferences(Context c){
            return c.getApplicationContext().getSharedPreferences("ListaPoracki", Activity.MODE_PRIVATE);
        }

        public static void addPoracki (Orders order, Context c){

            Gson gson = new Gson();
            String mapString = gson.toJson(order);
            getPreferences(c).edit().putString("Poracka", mapString).apply();


        }
        public static Orders getPoracki (Context c){

            return new Gson().fromJson(getPreferences(c).getString("Poracka", ""), Orders.class);
        }

    public static void removePoracka(Context c){

        getPreferences(c).edit().remove("Poracka").apply();

    }
}
