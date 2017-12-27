package com.mkdingo.goran.finalnaaplikacija;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.mkdingo.goran.finalnaaplikacija.adapter.RVRestoraniAdapter;
import com.mkdingo.goran.finalnaaplikacija.manager.FavoritePreferences;
import com.mkdingo.goran.finalnaaplikacija.manager.OrderPreferences;
import com.mkdingo.goran.finalnaaplikacija.manager.PorackiPreferences;
import com.mkdingo.goran.finalnaaplikacija.models.Orders;
import com.mkdingo.goran.finalnaaplikacija.manager.RestoranPreferences;
import com.mkdingo.goran.finalnaaplikacija.models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.models.RestoraniModel;
import com.mkdingo.goran.finalnaaplikacija.Listeners.RestoraniOnClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PocetnoMeni extends AppCompatActivity {
    @BindView(R.id.addbtn) Button addBtn;
    @BindView(R.id.addorder) Button poracka;
    @BindView(R.id.rView)RecyclerView moeRView;
    RestoraniModel model = new RestoraniModel();
    RVRestoraniAdapter adapter;
    Orders order;
    String sign = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pocetnomeni);
        ButterKnife.bind(this);

        Orders ordersnimen = OrderPreferences.getOrders(this);

        if (ordersnimen!=null){
        order = new Orders(ordersnimen.getTelnumber(),ordersnimen.getUsername(),ordersnimen.getNaracki(),ordersnimen.restorants);
        poracka.setText(order.getUsername() + "\nSign Out");
        sign = "Order";
        }else {poracka.setText("Sign in");
        }

        model.restaurants = new ArrayList<>();
        adapter = new RVRestoraniAdapter(this, new RestoraniOnClickListener() {
            @Override
            public void
            onRestoranClick(Restorani restoran, int position) {
                if (order != null){
                Intent intent = new Intent(PocetnoMeni.this, RestoranAktiviti.class);
                intent.putExtra("pozicija", position);
                intent.putExtra("order", order);
                startActivityForResult(intent, 1000);}
                else {
                Toast.makeText(PocetnoMeni.this, "Please sign in", Toast.LENGTH_SHORT).show();
                }
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
                        RestoranPreferences.addRestoran(model, PocetnoMeni.this);
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

        ArrayList<Restorani> generateList() {model = RestoranPreferences.getRestoran(this);
            return model.restaurants;}

    @OnClick(R.id.addbtn)
    public void AddRestoran() {
        Intent intent = new Intent(PocetnoMeni.this, AddRestoran.class);
        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1000) {
            adapter.setItems(generateList());
            adapter.notifyDataSetChanged();
        }else if (resultCode == RESULT_OK && requestCode == 1500){
            Orders ordersnimen = (Orders) data.getSerializableExtra("OrderNum");
            order = new Orders(ordersnimen.getTelnumber(),ordersnimen.getUsername(),ordersnimen.getNaracki(),ordersnimen.restorants);
            OrderPreferences.addOrders(order,this);
            recreate();
        }
    }

    @OnClick(R.id.addorder)
    public void Naracka(){
      if (sign.equals("Order")){

          PopupMenu popup = new PopupMenu(PocetnoMeni.this, poracka);
          popup.getMenuInflater().inflate(R.menu.userpopup, popup.getMenu());
          popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
              @Override
              public boolean onMenuItemClick(MenuItem item) {
                  switch (item.getItemId()){
                      case R.id.one:
                          Intent add = new Intent(PocetnoMeni.this,FavoriteFoods.class);
                          startActivityForResult(add,1000);
                          break;
                      case R.id.two:
                          Intent edit = new Intent(PocetnoMeni.this,RegistracijaZaNaracka.class);
                          edit.putExtra("EditUser","EditUser");
                          startActivityForResult(edit,1500);
                          break;

                      case R.id.three:
                          OrderPreferences.removeOrders(PocetnoMeni.this);
                          PorackiPreferences.removePoracka(PocetnoMeni.this);
                          FavoritePreferences.removeFavorites(PocetnoMeni.this);
                          recreate();
                  }
                  return true;
              }
          });
          popup.show();
      }else {
         Intent intent = new Intent(PocetnoMeni.this, RegistracijaZaNaracka.class);
         startActivityForResult(intent, 1500);
     }
    }
}
