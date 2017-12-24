package com.mkdingo.goran.finalnaaplikacija;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mkdingo.goran.finalnaaplikacija.Adapter.RVRestoraniAdapter;
import com.mkdingo.goran.finalnaaplikacija.Models.Orders;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoranPreferences;
import com.mkdingo.goran.finalnaaplikacija.Models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoraniModel;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoraniOnClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PocetnoMeni extends AppCompatActivity {
    @BindView(R.id.addbtn)
    Button addBtn;
    @BindView(R.id.addorder)
    Button poracka;
    @BindView(R.id.rView)RecyclerView moeRView;
    SharedPreferences preferences;
    RestoraniModel model = new RestoraniModel();
    RVRestoraniAdapter adapter;
    Orders order;
    String sign = "";
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pocetnomeni);
        ButterKnife.bind(this);
        int number = 0;
        preferences = getSharedPreferences("MyOrders",MODE_PRIVATE);

        if (preferences.contains("Order")){
        sign = "Order";
        gson = new Gson();
        Orders ordersnimen = gson.fromJson(preferences.getString("Order", ""), Orders.class);
        order = new Orders(ordersnimen.getTelnumber(),ordersnimen.getUsername(),ordersnimen.getNaracki(),ordersnimen.restorants);
        poracka.setText(order.getUsername() + "\nSign Out");
        }else {
            poracka.setText("Sign in");
        }

        model.restaurants = new ArrayList<>();
        adapter = new RVRestoraniAdapter(this, new RestoraniOnClickListener() {
            @Override
            public void
            onRestoranClick(Restorani restoran, int position) {
                if (order != null){
                Intent intent = new Intent(PocetnoMeni.this, RestoranAktiviti.class);
                intent.putExtra("Restoran", restoran);
                intent.putExtra("pozicija", position);
                intent.putExtra("order", order);
                startActivityForResult(intent, 1000);}else {
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

    ArrayList<Restorani> generateList() {

        model = RestoranPreferences.getRestoran(this);

        return model.restaurants;
    }

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
            gson = new Gson();
            String mapString = gson.toJson(order);
            preferences.edit().putString("Order", mapString).apply();
            recreate();
        }
    }

    @OnClick(R.id.addorder)
    public void Naracka(){
     if (sign.equals("Order")){

         preferences.edit().remove("Order").apply();
         recreate();

     }else {
         Intent intent = new Intent(PocetnoMeni.this, RegistracijaZaNaracka.class);
         startActivityForResult(intent, 1500);
     }

    }
}
