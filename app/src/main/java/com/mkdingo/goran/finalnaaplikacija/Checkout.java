package com.mkdingo.goran.finalnaaplikacija;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mkdingo.goran.finalnaaplikacija.adapter.CheckoutAdapter;
import com.mkdingo.goran.finalnaaplikacija.manager.FavoritePreferences;
import com.mkdingo.goran.finalnaaplikacija.manager.OrderPreferences;
import com.mkdingo.goran.finalnaaplikacija.manager.PorackiPreferences;
import com.mkdingo.goran.finalnaaplikacija.manager.RestoranPreferences;
import com.mkdingo.goran.finalnaaplikacija.models.FavoriteFood;
import com.mkdingo.goran.finalnaaplikacija.models.Menu;
import com.mkdingo.goran.finalnaaplikacija.models.Orders;
import com.mkdingo.goran.finalnaaplikacija.models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.models.RestoraniModel;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Checkout extends AppCompatActivity {
 @BindView(R.id.rvorders)RecyclerView orersRV;
 @BindView(R.id.overall)TextView smetka;
      CheckoutAdapter checkoutAdapter;
      FavoriteFood foodfav;
      Menu meni;
      Orders order;
      Orders orders;
      int pozicija;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);
        ButterKnife.bind(this);
        foodfav = FavoritePreferences.getFav(this);
        order = OrderPreferences.getOrders(this);
        orders = PorackiPreferences.getPoracki(this);
        Intent intent = getIntent();
        if (intent.hasExtra("Order")){
           order = (Orders) intent.getSerializableExtra("Order");
           orders = (Orders) intent.getSerializableExtra("orders");
        }else if (intent.hasExtra("pozicijaFavHrana")){
            int position = intent.getIntExtra("pozicijaFavHrana",0);
            meni = foodfav.favorites.get(position);
            order.getNaracki().add(meni);
            if (orders == null)
            {
                ArrayList<Menu> naracki = new ArrayList<>();
                ArrayList<String> restorants = new ArrayList<>();
                orders = new Orders(order.getTelnumber(),order.getUsername(),naracki,restorants);
                orders.naracki.add(meni);
            }else{
            orders.naracki.add(meni);}
            Collections.reverse(orders.naracki);
            PorackiPreferences.addPoracki(orders,this);

        }

        smetka.setText(order.getUsername() + " your total receipt is: "+ Smetka(order.getNaracki()));
        checkoutAdapter = new CheckoutAdapter();

        checkoutAdapter.setItems(order.getNaracki());
        orersRV.setHasFixedSize(true);
        orersRV.setLayoutManager(new LinearLayoutManager(this));
        orersRV.setAdapter(checkoutAdapter);

    }

    @OnClick(R.id.plati)
    public void Plati (){
        Intent intent = new Intent(Checkout.this,RestoraniProfit.class);
        intent.putExtra("orders",orders);
        startActivity(intent);

    }


    public int Smetka(ArrayList<Menu>meni){
        int smetka = 0;
        for (Menu menu:meni) {

            int cena = Integer.parseInt(menu.getPrice());
            smetka = smetka + cena;
        }
        return smetka;}
}
