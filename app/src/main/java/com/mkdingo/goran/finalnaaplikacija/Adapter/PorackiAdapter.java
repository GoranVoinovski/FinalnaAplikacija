package com.mkdingo.goran.finalnaaplikacija.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mkdingo.goran.finalnaaplikacija.Models.Menu;
import com.mkdingo.goran.finalnaaplikacija.Models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoraniModel;
import com.mkdingo.goran.finalnaaplikacija.R;
import com.mkdingo.goran.finalnaaplikacija.RestoranAktiviti;
import com.mkdingo.goran.finalnaaplikacija.RestoraniProfit;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by goran on 21.12.17.
 */

public class PorackiAdapter extends RecyclerView.Adapter<PorackiAdapter.ViewHolder> {

   ArrayList<Menu>prices = new ArrayList<>();
    Context context;
    Restorani restorani;

    public void setItems(ArrayList<Menu>ceni){

        prices = ceni;
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

        holder.article.setText(menu.getFoodname());
        holder.articleprice.setText(menu.getPrice());
        String restoran = ((RestoranAktiviti)context).restoran;
        holder.user.setText(restoran);

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
}
