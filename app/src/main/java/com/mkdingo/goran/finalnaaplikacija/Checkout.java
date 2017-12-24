package com.mkdingo.goran.finalnaaplikacija;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mkdingo.goran.finalnaaplikacija.Adapter.CheckoutAdapter;
import com.mkdingo.goran.finalnaaplikacija.Models.Menu;
import com.mkdingo.goran.finalnaaplikacija.Models.Orders;
import com.mkdingo.goran.finalnaaplikacija.Models.Restorani;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Checkout extends AppCompatActivity {
    Orders order;
    public Orders orders;
    Menu meni;
    Restorani restoran;
    int pozicija;

    Menu menu;
    @BindView(R.id.rvorders)RecyclerView orersRV;
    @BindView(R.id.overall)TextView smetka;
    CheckoutAdapter checkoutAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra("Order")){
           order = (Orders) intent.getSerializableExtra("Order");
           orders = (Orders) intent.getSerializableExtra("orders");
           restoran = (Restorani) intent.getSerializableExtra("restoran");
           meni =  (Menu) intent.getSerializableExtra("meni");
           pozicija = intent.getIntExtra("pozzicijaRestoran",0);
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
        intent.putExtra("restoran",restoran);
        intent.putExtra("meni",meni);
        startActivity(intent);

    }


    public int Smetka(ArrayList<Menu>meni){
        int smetka = 0;
        for (Menu menu:meni) {

            int cena = Integer.parseInt(menu.getPrice());
            smetka = smetka + cena;

        }

        return smetka;
    }


}
