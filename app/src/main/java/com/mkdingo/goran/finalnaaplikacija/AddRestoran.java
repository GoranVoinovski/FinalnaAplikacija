package com.mkdingo.goran.finalnaaplikacija;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mkdingo.goran.finalnaaplikacija.Models.Menu;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoranPreferences;
import com.mkdingo.goran.finalnaaplikacija.Models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoraniModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddRestoran extends AppCompatActivity {
    @BindView(R.id.opengallery)Button gallery;
    @BindView(R.id.ratingnum)TextView brojrejting;
    @BindView(R.id.logo)ImageView edtlogo;
    @BindView(R.id.city)EditText edtcity;
    @BindView(R.id.name)EditText edtname;
    @BindView(R.id.rating)RatingBar edtrating;
    ArrayList<Menu> meni = new ArrayList<>();
    RestoraniModel restorani;
    Restorani restoran;
    String slika = "";
    Uri pickedImage;
    String rejting;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        restorani = RestoranPreferences.getRestoran(this);

        edtrating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBar.setRating(rating);
                brojrejting.setText(rating + "");
                rejting = String.valueOf(rating);
            }
        });
    }

    @OnClick(R.id.opengallery)
    public void Gallery(){

        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 1111);


    }

    @OnClick(R.id.saverestaurant)
    public void Save (){
        if (slika.isEmpty()){
           slika = "https://i.pinimg.com/736x/b5/5a/e8/b55ae88f3043f90addb5ef028e644325--restaurant-logo.jpg";
        }else {}
        restoran = new Restorani(slika,edtcity.getText().toString(),edtname.getText().toString(),rejting,meni);
        restorani.restaurants.add(restoran);
        RestoranPreferences.addRestoran(restorani,AddRestoran.this);
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1111){

            pickedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            slika = pickedImage.toString();
            Picasso.with(this).load(pickedImage).centerInside().fit().into(edtlogo);





        }
    }
}
