package com.mkdingo.goran.finalnaaplikacija.manager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.mkdingo.goran.finalnaaplikacija.models.FavoriteFood;
import com.mkdingo.goran.finalnaaplikacija.models.Menu;

/**
 * Created by goran on 25.12.17.
 */

public class FavoritePreferences {

    private static SharedPreferences getPreferences(Context c){
        return c.getApplicationContext().getSharedPreferences("MyFavorites", Activity.MODE_PRIVATE);
    }

    public static void addFav (FavoriteFood foodmenu, Context c){

        Gson gson = new Gson();
        String mapString = gson.toJson(foodmenu);
        getPreferences(c).edit().putString("Favorite", mapString).apply();


    }
    public static FavoriteFood getFav (Context c){

        return new Gson().fromJson(getPreferences(c).getString("Favorite", ""), FavoriteFood.class);
    }

    public static void removeFavorites(Context c){

        getPreferences(c).edit().remove("Favorite").apply();

    }
}
