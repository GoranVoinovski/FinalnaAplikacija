package com.mkdingo.goran.finalnaaplikacija;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mkdingo.goran.finalnaaplikacija.adapter.PorackiAdapter;
import com.mkdingo.goran.finalnaaplikacija.models.Menu;
import com.mkdingo.goran.finalnaaplikacija.models.Orders;
import com.mkdingo.goran.finalnaaplikacija.manager.RestoranPreferences;
import com.mkdingo.goran.finalnaaplikacija.models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.models.RestoraniModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestoraniProfit extends AppCompatActivity {
    public @BindView(R.id.imenarestoran)TextView restoranIme;
    @BindView(R.id.pprofitRV)RecyclerView profitRView;
    PorackiAdapter adapter;
    Orders poracki;
    Menu meni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restorani_profit);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        poracki = (Orders) intent.getSerializableExtra("orders");
        meni = (Menu) intent.getSerializableExtra("meni");

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
