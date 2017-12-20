package com.mkdingo.goran.finalnaaplikacija;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mkdingo.goran.finalnaaplikacija.Adapter.RVMeniAdapter;
import com.mkdingo.goran.finalnaaplikacija.Models.Menu;
import com.mkdingo.goran.finalnaaplikacija.Models.OnImageClickListener;
import com.mkdingo.goran.finalnaaplikacija.Models.Restorani;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main3Activity extends AppCompatActivity {
    @BindView(R.id.imerestoran)TextView imenakliknatrestoran;
    @BindView(R.id.slikarestoran)ImageView slikanakliknatrestoran;
    @BindView(R.id.location)TextView lokacijanakliknatrestoran;
    @BindView(R.id.rejtingrestoran)RatingBar rejtingnakliknatrestoran;
    @BindView(R.id.rvmenu)RecyclerView rvmeni;
    Restorani restoranodbran;
    RVMeniAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra("Restoran")){
          restoranodbran = (Restorani) intent.getSerializableExtra("Restoran");
          imenakliknatrestoran.setText(restoranodbran.getName());
          Picasso.with(this).load(restoranodbran.getLogo()).centerInside().fit().into(slikanakliknatrestoran);
          lokacijanakliknatrestoran.setText(restoranodbran.getCity());
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
}
