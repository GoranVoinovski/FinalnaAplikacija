package com.mkdingo.goran.finalnaaplikacija;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.google.gson.Gson;
import com.mkdingo.goran.finalnaaplikacija.Adapter.SlikiAdapter;
import com.mkdingo.goran.finalnaaplikacija.Models.Menu;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoranPreferences;
import com.mkdingo.goran.finalnaaplikacija.Models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoraniModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main4Activity extends AppCompatActivity {

    @BindView(R.id.pager)
    ViewPager mojPager;
    @BindView(R.id.vnesihrana)
    Button menu;
    Restorani meni;
    Menu meninovo;
    SlikiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ButterKnife.bind(this);
        Intent pozicija = getIntent();
        meni = (Restorani) pozicija.getSerializableExtra("Restoran");
        int position = pozicija.getIntExtra("Position", 0);

        adapter = new SlikiAdapter(this.getSupportFragmentManager());

        adapter.addSliki(meni.menu);
        mojPager.setAdapter(adapter);
        mojPager.setCurrentItem(position);



    }




}