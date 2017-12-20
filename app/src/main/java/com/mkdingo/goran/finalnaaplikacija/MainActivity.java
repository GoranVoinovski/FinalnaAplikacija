package com.mkdingo.goran.finalnaaplikacija;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mkdingo.goran.finalnaaplikacija.Adapter.RVRestoraniAdapter;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoranPreferences;
import com.mkdingo.goran.finalnaaplikacija.Models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoraniModel;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoraniOnClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.addbtn)Button addBtn;
    @BindView(R.id.rView)RecyclerView moeRView;
    RestoraniModel model = new RestoraniModel();

    Restorani restoran;
    RVRestoraniAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        model.restaurants = new ArrayList<>();
        adapter = new RVRestoraniAdapter(this, new RestoraniOnClickListener() {
            @Override
            public void
            onRestoranClick(Restorani restoran, int position) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("Restoran",restoran);
                startActivity(intent);
            }

            @Override
            public void onRestoranLongClick(Restorani restoran, int position) {
                model.restaurants.remove(position);
                RestoranPreferences.addRestoran(model,MainActivity.this);
                adapter.setItems(generateList());
                adapter.notifyDataSetChanged();
            }
        });


        adapter.setItems(generateList());
        moeRView.setHasFixedSize(true);
        moeRView.setLayoutManager(new LinearLayoutManager(this));
        moeRView.setAdapter(adapter);



    }


    ArrayList<Restorani> generateList() {

       model = RestoranPreferences.getRestoran(this);

        return model.restaurants;
    }

    @OnClick(R.id.addbtn)
   public void AddRestoran(){

        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        startActivityForResult(intent,1000);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1000){

            adapter.setItems(generateList());
            adapter.notifyDataSetChanged();

        }
    }


}
