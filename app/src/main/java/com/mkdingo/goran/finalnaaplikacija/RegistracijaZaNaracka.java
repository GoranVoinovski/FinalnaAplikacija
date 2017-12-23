package com.mkdingo.goran.finalnaaplikacija;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.mkdingo.goran.finalnaaplikacija.Models.Menu;
import com.mkdingo.goran.finalnaaplikacija.Models.Orders;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistracijaZaNaracka extends AppCompatActivity {
    @BindView(R.id.telephone)EditText telBroj;
    @BindView(R.id.username)EditText user;
    @BindView(R.id.savetelnumber)Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registracija_za_naracka);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.savetelnumber)
    public void SaveNum(){
        int tel = Integer.parseInt(telBroj.getText().toString());
        String imeNaUser = user.getText().toString();
        ArrayList<Menu> naracki = new ArrayList<>();
        Orders orders = new Orders(tel,imeNaUser,naracki);
        Intent intent = new Intent();
        intent.putExtra("OrderNum",orders);
        setResult(RESULT_OK,intent);
        finish();


    }

}
