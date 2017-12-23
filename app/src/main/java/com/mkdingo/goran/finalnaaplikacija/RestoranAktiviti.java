package com.mkdingo.goran.finalnaaplikacija;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.mkdingo.goran.finalnaaplikacija.Adapter.RVMeniAdapter;
import com.mkdingo.goran.finalnaaplikacija.Models.Menu;
import com.mkdingo.goran.finalnaaplikacija.Models.OnImageClickListener;
import com.mkdingo.goran.finalnaaplikacija.Models.Orders;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoranPreferences;
import com.mkdingo.goran.finalnaaplikacija.Models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoraniModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RestoranAktiviti extends AppCompatActivity {
    @BindView(R.id.imerestoran)TextView imenakliknatrestoran;
    @BindView(R.id.slikarestoran)ImageView slikanakliknatrestoran;
    @BindView(R.id.location)TextView lokacijanakliknatrestoran;
    @BindView(R.id.rejtingrestoran)RatingBar rejtingnakliknatrestoran;
    @BindView(R.id.rvmenu)RecyclerView rvmeni;
    @BindView(R.id.editrestoran) Button popupmenu;
    @BindView(R.id.checkout) Button cart;
    RestoraniModel restorani;
    Menu meninovo;
    Restorani restoranodbran;
    SharedPreferences preferences;
    public RVMeniAdapter adapter;
    int pozicija = 0;
    public static String restoran;
    Orders order;
    Orders orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restoranaktiviti);
        ButterKnife.bind(this);
        preferences = getSharedPreferences("ListaPoracki",MODE_PRIVATE);

        restorani = RestoranPreferences.getRestoran(this);
        cart.setVisibility(View.INVISIBLE);
        cart.setText("Checkout");
        Intent intent = getIntent();
        if (intent.hasExtra("Restoran")){
          pozicija = intent.getIntExtra("pozicija",0);
          restoranodbran = (Restorani) intent.getSerializableExtra("Restoran");
          order = (Orders) intent.getSerializableExtra("order");
          imenakliknatrestoran.setText(restoranodbran.getName());
          Picasso.with(this).load(restoranodbran.getLogo()).centerInside().fit().into(slikanakliknatrestoran);
          lokacijanakliknatrestoran.setText("Location: " + restoranodbran.getCity());
          float rejting = Float.parseFloat(restoranodbran.getRating());
          rejtingnakliknatrestoran.setRating(rejting);
            Gson gson2 = new Gson();
            if (preferences.contains("Poracka")){
                orders = gson2.fromJson(preferences.getString("Poracka",""),Orders.class);
            }
            else{
                ArrayList<Menu> naracki = new ArrayList<>();
                orders = new Orders(order.getTelnumber(),order.getUsername(),naracki);

            }
            adapter = new RVMeniAdapter(this, new OnImageClickListener() {
                @Override
                public void onImageClick(Menu meni, int position) {
                    Intent intent = new Intent(RestoranAktiviti.this, AktivitiFragmenti.class);
                    intent.putExtra("Restoran",restoranodbran);
                    intent.putExtra("Position", pozicija);
                    intent.putExtra("Position1", position);
                    startActivityForResult(intent,1111);

                }

                @Override
                public void onImageLongClick(Menu meni, int position) {
                    order.getNaracki().add(meni);
                    orders.naracki.add(meni);
                    Gson gson = new Gson();
                    String map = gson.toJson(orders);
                    preferences.edit().putString("Poracka",map).apply();
                    restoran = restoranodbran.getName();
                    cart.setVisibility(View.VISIBLE);
                    String longclicktekst = "You added "+ meni.getFoodname() + " in your cart";
                    Toast.makeText(RestoranAktiviti.this,longclicktekst,Toast.LENGTH_LONG).show();
                    adapter.notifyDataSetChanged();
                }

            });


            adapter.setItems(restoranodbran.menu);
            rvmeni.setHasFixedSize(true);
            rvmeni.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            rvmeni.setAdapter(adapter);
        }else {}



        Intent intent2 = getIntent();
        if (intent2.hasExtra("Refresh")){
            adapter.setItems(restoranodbran.menu);
            adapter.notifyDataSetChanged();
        }

    }

    @OnClick(R.id.checkout)
    public void Checkout(){

        Intent intent = new Intent(RestoranAktiviti.this,Checkout.class);
        intent.putExtra("Order",order);
        intent.putExtra("orders",orders);
        intent.putExtra("restoran",restoranodbran);
        startActivityForResult(intent,1000);


    }

    @OnClick(R.id.editrestoran)
    public void AddMenu(){

        PopupMenu popup = new PopupMenu(RestoranAktiviti.this, popupmenu);
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
                        edit.putExtra("restaurant",restoranodbran);
                        startActivityForResult(edit,1111);
                        break;

                    case R.id.three:
                        Intent check = new Intent(RestoranAktiviti.this,RestoraniProfit.class);
                        check.putExtra("restaurant",restoranodbran);
                        check.putExtra("orders",order);
                        startActivityForResult(check,1111);
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
                restoranodbran.menu.add(meninovo);
                restorani.restaurants.remove(pozicija);
                restorani.restaurants.add(pozicija, restoranodbran);
                RestoranPreferences.addRestoran(restorani, this);
                adapter.notifyDataSetChanged();
            }else if (data.hasExtra("Restoran")){
                Restorani restorance = (Restorani) data.getSerializableExtra("Restoran");
                imenakliknatrestoran.setText(restorance.getName());
                Picasso.with(this).load(restorance.getLogo()).centerInside().fit().into(slikanakliknatrestoran);
                lokacijanakliknatrestoran.setText("Location: " + restorance.getCity());
                float rejting = Float.parseFloat(restorance.getRating());
                rejtingnakliknatrestoran.setRating(rejting);
                restorani.restaurants.remove(pozicija);
                restorani.restaurants.add(pozicija, restorance);
                RestoranPreferences.addRestoran(restorani, this);
                rvmeni.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }else if (data.hasExtra("Refresh")){
                restorani = RestoranPreferences.getRestoran(this);
                restoranodbran = restorani.restaurants.get(pozicija);
                adapter.setItems(restoranodbran.menu);
                rvmeni.setAdapter(adapter);}

        }else if (resultCode == RESULT_OK && requestCode == 0000){

        }

}

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(RestoranAktiviti.this,PocetnoMeni.class);
//        startActivity(intent);
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}
