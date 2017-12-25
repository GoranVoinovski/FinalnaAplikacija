package com.mkdingo.goran.finalnaaplikacija;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mkdingo.goran.finalnaaplikacija.adapter.RVMeniAdapter;
import com.mkdingo.goran.finalnaaplikacija.manager.PorackiPreferences;
import com.mkdingo.goran.finalnaaplikacija.models.Menu;
import com.mkdingo.goran.finalnaaplikacija.models.OnImageClickListener;
import com.mkdingo.goran.finalnaaplikacija.models.Orders;
import com.mkdingo.goran.finalnaaplikacija.manager.RestoranPreferences;
import com.mkdingo.goran.finalnaaplikacija.models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.models.RestoraniModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RestoranAktiviti extends AppCompatActivity {
    @BindView(R.id.imerestoran)TextView imeNaKlikNaRestoran;
    @BindView(R.id.slikarestoran)ImageView slikaNaKlikNaRestoran;
    @BindView(R.id.location)TextView lokacijaNaKlikNaRestoran;
    @BindView(R.id.rejtingrestoran)RatingBar rejtingNaKlikNaRestoran;
    @BindView(R.id.rvmenu)RecyclerView rvMeni;
    @BindView(R.id.editrestoran) Button popUpMenu;
    @BindView(R.id.checkout) Button cart;
    RestoraniModel restorani;
    Menu meninovo;
    Menu menu;
    Restorani restoranOdbran;
    RVMeniAdapter adapter;
    Orders order;
    Orders orders;
    int pozicija = 0;
    String web = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restoranaktiviti);
        ButterKnife.bind(this);

        restorani = RestoranPreferences.getRestoran(this);
        cart.setVisibility(View.INVISIBLE);
        cart.setText("Checkout");
        Intent intent = getIntent();
        if (intent.hasExtra("pozicija")){
            pozicija = intent.getIntExtra("pozicija",0);
            order = (Orders) intent.getSerializableExtra("order");

                restoranOdbran = restorani.restaurants.get(pozicija);
                imeNaKlikNaRestoran.setText(restoranOdbran.getName());
                Picasso.with(this).load(restoranOdbran.getLogo()).centerInside().fit().into(slikaNaKlikNaRestoran);
                lokacijaNaKlikNaRestoran.setPaintFlags(lokacijaNaKlikNaRestoran.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                lokacijaNaKlikNaRestoran.setText("Location: " + restoranOdbran.getCity());
                float rejting = Float.parseFloat(restoranOdbran.getRating());
                rejtingNaKlikNaRestoran.setRating(rejting);

                orders = PorackiPreferences.getPoracki(this);
            if (orders == null){
                ArrayList<Menu> naracki = new ArrayList<>();
                ArrayList<String> restorants = new ArrayList<>();
                orders = new Orders(order.getTelnumber(),order.getUsername(),naracki,restorants);}


               adapter = new RVMeniAdapter(this, new OnImageClickListener() {
                @Override
                public void onImageClick(Menu meni, int position) {
                    Intent intent = new Intent(RestoranAktiviti.this, AktivitiFragmenti.class);
                    intent.putExtra("Position", pozicija);
                    intent.putExtra("Position1", position);
                    startActivityForResult(intent,1111);
                }

                @Override
                public void onImageLongClick(Menu meni, int position) {
                    order.getNaracki().add(meni);
                    orders.naracki.add(meni);
                    Collections.reverse(orders.naracki);
                    PorackiPreferences.addPoracki(orders,RestoranAktiviti.this);
                    cart.setVisibility(View.VISIBLE);
                    menu = meni;
                    String longclicktekst = "You added "+ meni.getFoodname() + " in your cart";
                    Toast.makeText(RestoranAktiviti.this,longclicktekst,Toast.LENGTH_LONG).show();
                    adapter.notifyDataSetChanged();
                }
               });
                    adapter.setItems(restoranOdbran.menu);
                    rvMeni.setHasFixedSize(true);
                    rvMeni.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
                    rvMeni.setAdapter(adapter);
                }else {}

                    Intent intent2 = getIntent();
                if (intent2.hasExtra("Refresh")){
                    adapter.setItems(restoranOdbran.menu);
                    adapter.notifyDataSetChanged();}
    }

                    @OnClick(R.id.checkout)
                    public void Checkout(){
                    Intent intent = new Intent(RestoranAktiviti.this,Checkout.class);
                    intent.putExtra("Order",order);
                    intent.putExtra("orders",orders);
                    intent.putExtra("pozicija",pozicija);
                    intent.putExtra("meni",menu);
                    startActivityForResult(intent,1000);


    }

    @OnClick(R.id.editrestoran)
    public void AddMenu(){

        PopupMenu popup = new PopupMenu(RestoranAktiviti.this, popUpMenu);
        popup.getMenuInflater().inflate(R.menu.popupedit, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.one:
                        Intent add = new Intent(RestoranAktiviti.this,EditAddFood.class);
                        startActivityForResult(add,1111);
                        break;
                    case R.id.two:
                        Intent edit = new Intent(RestoranAktiviti.this,EditRestoran.class);
                        edit.putExtra("pozicija",pozicija);
                        startActivityForResult(edit,1111);
                        break;

                    case R.id.three:
                        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(restoranOdbran.getWeb()));
                        startActivity(webIntent);
                        break;
                }
                return true;
            }
        });
        popup.show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1111){
            if (data.hasExtra("NovoMeni")) {
                meninovo = (Menu) data.getSerializableExtra("NovoMeni");
                restoranOdbran.menu.add(meninovo);
                restorani.restaurants.remove(pozicija);
                restorani.restaurants.add(pozicija, restoranOdbran);
                RestoranPreferences.addRestoran(restorani, this);
                adapter.notifyDataSetChanged();
                adapter.setItems(restoranOdbran.menu);
                rvMeni.setAdapter(adapter);
            }else if (data.hasExtra("Restoran")){
                Restorani restorance = (Restorani) data.getSerializableExtra("Restoran");
                imeNaKlikNaRestoran.setText(restorance.getName());
                Picasso.with(this).load(restorance.getLogo()).centerInside().fit().into(slikaNaKlikNaRestoran);
                lokacijaNaKlikNaRestoran.setPaintFlags(lokacijaNaKlikNaRestoran.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                lokacijaNaKlikNaRestoran.setText("Location: " + restorance.getCity());
                float rejting = Float.parseFloat(restorance.getRating());
                rejtingNaKlikNaRestoran.setRating(rejting);
                restorani.restaurants.remove(pozicija);
                restorani.restaurants.add(pozicija, restorance);
                RestoranPreferences.addRestoran(restorani, this);
                rvMeni.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }else if (data.hasExtra("Refresh")){
                restorani = RestoranPreferences.getRestoran(this);
                restoranOdbran = restorani.restaurants.get(pozicija);
                adapter.setItems(restoranOdbran.menu);
                rvMeni.setAdapter(adapter);}

        }else if (resultCode == RESULT_OK && requestCode == 0000){

        }

}

    @OnClick(R.id.location)
        public void Maps(){
    String grad = lokacijaNaKlikNaRestoran.getText().toString();
    Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(grad));
    Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
    mapIntent.setPackage("com.google.android.apps.maps");
    startActivity(mapIntent);



}

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}
