package com.mkdingo.goran.finalnaaplikacija;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.mkdingo.goran.finalnaaplikacija.Adapter.RVRestoraniAdapter;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoranPreferences;
import com.mkdingo.goran.finalnaaplikacija.Models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoraniModel;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoraniOnClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PocetnoMeni extends AppCompatActivity {
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
                Intent intent = new Intent(PocetnoMeni.this, RestoranAktiviti.class);
                intent.putExtra("Restoran",restoran);
                intent.putExtra("pozicija",position);
                startActivityForResult(intent,1000);
//                finish();
       }

            @Override
            public void onRestoranLongClick(Restorani restoran, final int position) {

                final AlertDialog.Builder myBuilder = new AlertDialog.Builder(PocetnoMeni.this);
                myBuilder.setTitle("Remove restaurant");
                myBuilder.setMessage("Are you sure you want this restaurant to be removed?");
                myBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        model.restaurants.remove(position);
                        RestoranPreferences.addRestoran(model,PocetnoMeni.this);
                        adapter.setItems(generateList());
                        adapter.notifyDataSetChanged();
                    }
                });
                myBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                    }
                });

                myBuilder.create().show();

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

        Intent intent = new Intent(PocetnoMeni.this,AddRestoran.class);
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