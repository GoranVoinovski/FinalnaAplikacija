package com.mkdingo.goran.finalnaaplikacija;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mkdingo.goran.finalnaaplikacija.Listeners.OnImageClickListener;
import com.mkdingo.goran.finalnaaplikacija.adapter.FavoriteAdapter;
import com.mkdingo.goran.finalnaaplikacija.manager.FavoritePreferences;
import com.mkdingo.goran.finalnaaplikacija.models.FavoriteFood;
import com.mkdingo.goran.finalnaaplikacija.models.Menu;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteFoods extends AppCompatActivity {
  @BindView(R.id.favoriteRV)RecyclerView favoritesRV;
  FavoriteAdapter adapter;
  FavoriteFood favoriteFoods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_foods);
        ButterKnife.bind(this);

        favoriteFoods = FavoritePreferences.getFav(FavoriteFoods.this);
        adapter = new FavoriteAdapter(this, new OnImageClickListener() {
            @Override
            public void onImageClick(Menu meni, int position) {
                Intent intent = new Intent(FavoriteFoods.this,Checkout.class);
                intent.putExtra("pozicijaFavHrana",position);
                startActivity(intent);

            }

            @Override
            public void onImageLongClick(Menu meni, final int position) {
                final AlertDialog.Builder myBuilder = new AlertDialog.Builder(FavoriteFoods.this);
                myBuilder.setTitle("Remove favorite");
                myBuilder.setMessage("Are you sure you want this favorites to be removed?");
                myBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        favoriteFoods.favorites.remove(position);
                        FavoritePreferences.addFav(favoriteFoods, FavoriteFoods.this);
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

        if (favoriteFoods == null){
            Toast.makeText(this, "No favorite foods in your list", Toast.LENGTH_SHORT).show();
        }else{
        adapter.setItems(favoriteFoods.favorites);
        favoritesRV.setHasFixedSize(true);
        favoritesRV.setLayoutManager(new LinearLayoutManager(this));
        favoritesRV.setAdapter(adapter);}

    }
}
