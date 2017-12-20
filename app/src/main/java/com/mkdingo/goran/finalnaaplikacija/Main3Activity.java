package com.mkdingo.goran.finalnaaplikacija;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mkdingo.goran.finalnaaplikacija.Adapter.RVMeniAdapter;
import com.mkdingo.goran.finalnaaplikacija.Models.Menu;
import com.mkdingo.goran.finalnaaplikacija.Models.OnImageClickListener;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoranPreferences;
import com.mkdingo.goran.finalnaaplikacija.Models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoraniModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends AppCompatActivity {
    @BindView(R.id.imerestoran)TextView imenakliknatrestoran;
    @BindView(R.id.slikarestoran)ImageView slikanakliknatrestoran;
    @BindView(R.id.location)TextView lokacijanakliknatrestoran;
    @BindView(R.id.rejtingrestoran)RatingBar rejtingnakliknatrestoran;
    @BindView(R.id.rvmenu)RecyclerView rvmeni;
    @BindView(R.id.vnesihrana)
    Button menu;
    Restorani meni;
    RestoraniModel restorani;
    Menu meninovo;
    Restorani restoranodbran;
    RVMeniAdapter adapter;
    int pozicija = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        restorani = RestoranPreferences.getRestoran(this);

        Intent intent = getIntent();
        if (intent.hasExtra("Restoran")){
          pozicija = intent.getIntExtra("pozicija",0);
          restoranodbran = (Restorani) intent.getSerializableExtra("Restoran");
          imenakliknatrestoran.setText(restoranodbran.getName());
          Picasso.with(this).load(restoranodbran.getLogo()).centerInside().fit().into(slikanakliknatrestoran);
          lokacijanakliknatrestoran.setText("Location: " + restoranodbran.getCity());
          float rejting = Float.parseFloat(restoranodbran.getRating());
          rejtingnakliknatrestoran.setRating(rejting);
            adapter = new RVMeniAdapter(this, new OnImageClickListener() {
                @Override
                public void onImageClick(Menu meni, int position) {
                    Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
                    intent.putExtra("Restoran",restoranodbran);
                    intent.putExtra("Position", position);
                    startActivity(intent);

                }

            });

            adapter.setItems(restoranodbran.menu);
            rvmeni.setHasFixedSize(true);
            rvmeni.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            rvmeni.setAdapter(adapter);
        }





    }

    @OnClick(R.id.vnesihrana)
    public void AddMenu(){

        Intent addmenu = new Intent(Main3Activity.this,Main5Activity.class);
        startActivityForResult(addmenu,1111);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1111);
        meninovo = (Menu) data.getSerializableExtra("NovoMeni");
        restoranodbran.menu.add(meninovo);
        restorani.restaurants.remove(pozicija);
        restorani.restaurants.add(pozicija,restoranodbran);
        RestoranPreferences.addRestoran(restorani,this);
        adapter.notifyDataSetChanged();


    }
}
