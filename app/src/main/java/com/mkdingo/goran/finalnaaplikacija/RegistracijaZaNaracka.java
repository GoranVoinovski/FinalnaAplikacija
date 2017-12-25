package com.mkdingo.goran.finalnaaplikacija;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.mkdingo.goran.finalnaaplikacija.models.Menu;
import com.mkdingo.goran.finalnaaplikacija.models.Orders;

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

        if (telBroj.length() > 0 && user.length() >  0){
        int tel = Integer.parseInt(telBroj.getText().toString());
        String imeNaUser = user.getText().toString();
        ArrayList<Menu> naracki = new ArrayList<>();
        ArrayList<String> restornts = new ArrayList<>();
        Orders orders = new Orders(tel,imeNaUser,naracki,restornts);
        Intent intent = new Intent();
        intent.putExtra("OrderNum",orders);
        setResult(RESULT_OK,intent);
        finish();}else {
            if (telBroj.length() == 0){
                telBroj.setError("Enter your phone number to continue");
            }else {user.setError("Enter your name to continue");}

        }
        }


    }


