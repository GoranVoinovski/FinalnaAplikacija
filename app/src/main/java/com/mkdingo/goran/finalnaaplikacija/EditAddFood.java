package com.mkdingo.goran.finalnaaplikacija;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mkdingo.goran.finalnaaplikacija.Models.Menu;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditAddFood extends AppCompatActivity {
    @BindView(R.id.opengallery)Button galerija;
    @BindView(R.id.btnaddmenu)Button addmenu;
    @BindView(R.id.articleimg)ImageView slikahrana;
    @BindView(R.id.name)TextView imehrana;
    @BindView(R.id.price)TextView cenahrana;
    @BindView(R.id.vegan)RadioButton veganska;
    @BindView(R.id.notvegan)RadioButton neveganska;
    Uri pickedImage;
    String slika;
    Menu meni;
    boolean veganskaE;
    String klik = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra("Meni")){
            klik = "edit";
         meni = (Menu)intent.getSerializableExtra("Meni");
         Picasso.with(this).load(meni.getLink()).centerInside().fit().into(slikahrana);
         slika = meni.getLink();
         imehrana.setText(meni.getFoodname());
         cenahrana.setText(meni.getPrice());
         veganskaE = meni.isIsveg();
         veganska.setChecked(false);
         neveganska.setChecked(false);
         if (veganskaE){
             veganska.setChecked(true);

         }else{
             neveganska.setChecked(true);}
        }
    }


    @OnClick(R.id.btnaddmenu)
    public void Add(){
            boolean isVegan = true;
            if (veganska.isChecked()) {
                isVegan = true;
            } else {
                isVegan = false;
            }
            String ime = imehrana.getText().toString();
            String cena = cenahrana.getText().toString();
            Menu menu = new Menu(slika, cena, ime, isVegan);
            Intent intentnovmeni = new Intent();
            intentnovmeni.putExtra("NovoMeni", menu);
            setResult(RESULT_OK, intentnovmeni);
            finish();
        }

    @OnClick(R.id.opengallery)
    public void OpenGalerry(){
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 2222);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 2222){
            pickedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            slika = pickedImage.toString();
            Picasso.with(this).load(pickedImage).centerInside().fit().into(slikahrana);





        }
    }
}
