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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Checkout extends AppCompatActivity {
    Orders order;
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
        }

        smetka.setText("Overall price: "+ Smetka(order.getNaracki()));
        checkoutAdapter = new CheckoutAdapter();

        checkoutAdapter.setItems(order.getNaracki());
        orersRV.setHasFixedSize(true);
        orersRV.setLayoutManager(new LinearLayoutManager(this));
        orersRV.setAdapter(checkoutAdapter);

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
