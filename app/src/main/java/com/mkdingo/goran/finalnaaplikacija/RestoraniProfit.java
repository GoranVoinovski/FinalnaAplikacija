package com.mkdingo.goran.finalnaaplikacija;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mkdingo.goran.finalnaaplikacija.Adapter.PorackiAdapter;
import com.mkdingo.goran.finalnaaplikacija.Models.Orders;
import com.mkdingo.goran.finalnaaplikacija.Models.Restorani;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestoraniProfit extends AppCompatActivity {
    public @BindView(R.id.imenarestoran)TextView restoranIme;
    @BindView(R.id.pprofitRV)RecyclerView profitRView;
    PorackiAdapter adapter;
    public Orders poracki;
    public Restorani restoran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restorani_profit);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        poracki = (Orders) intent.getSerializableExtra("orders");


        adapter = new PorackiAdapter(this);
        adapter.setItems(poracki.naracki);
        profitRView.setHasFixedSize(true);
        profitRView.setLayoutManager(new LinearLayoutManager(this));
        profitRView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();

    }
}
