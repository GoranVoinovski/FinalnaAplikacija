package com.mkdingo.goran.finalnaaplikacija.manager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.mkdingo.goran.finalnaaplikacija.models.Orders;
import com.mkdingo.goran.finalnaaplikacija.models.RestoraniModel;

/**
 * Created by goran on 25.12.17.
 */

public class OrderPreferences {
    private static SharedPreferences getPreferences(Context c){
        return c.getApplicationContext().getSharedPreferences("MyOrders", Activity.MODE_PRIVATE);
    }

    public static void addOrders (Orders order, Context c){

        Gson gson = new Gson();
        String mapString = gson.toJson(order);
        getPreferences(c).edit().putString("Order", mapString).apply();


    }
    public static Orders getOrders (Context c){

        return new Gson().fromJson(getPreferences(c).getString("Order", ""), Orders.class);
    }

}
