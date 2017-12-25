package com.mkdingo.goran.finalnaaplikacija.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.mkdingo.goran.finalnaaplikacija.manager.RestoranPreferences;
import com.mkdingo.goran.finalnaaplikacija.models.Menu;
import com.mkdingo.goran.finalnaaplikacija.models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.R;
import com.mkdingo.goran.finalnaaplikacija.RestoraniProfit;
import com.mkdingo.goran.finalnaaplikacija.models.RestoraniModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by goran on 21.12.17.
 */

public class PorackiAdapter extends RecyclerView.Adapter<PorackiAdapter.ViewHolder> {

   ArrayList<Menu>prices = new ArrayList<>();
   RestoraniModel restoraniModel;
   Context context;

   public void setItems(ArrayList<Menu>ceni){prices = ceni;}

    public PorackiAdapter(Context context) {
        this.context = context;
    }

    @Override
    public PorackiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.naracki2, parent, false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(PorackiAdapter.ViewHolder holder, int position) {
        Menu menu = prices.get(position);
        restoraniModel = RestoranPreferences.getRestoran(context);
        holder.article.setText(menu.getFoodname());
        holder.articleprice.setText(menu.getPrice());
        ((RestoraniProfit)context).restoranIme.setText("Total orders balance: " + Smetka(prices));
        for (Restorani restorani:restoraniModel.restaurants){
            for (Menu meni:restorani.menu){
                if (menu.getFoodname().equals(meni.getFoodname()))
                    holder.user.setText(restorani.getName());
            }
        }
    }

    @Override
    public int getItemCount() {
        return prices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.proizvod)TextView article;
        @BindView(R.id.cena)TextView articleprice;
        @BindView(R.id.customer)TextView user;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public int Smetka(ArrayList<Menu>meni){
        int smetka = 0;
        for (Menu menu:meni) {

        int cena = Integer.parseInt(menu.getPrice());
            smetka = smetka + cena;
        }
        return smetka;
    }
}
