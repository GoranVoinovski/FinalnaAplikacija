package com.mkdingo.goran.finalnaaplikacija.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mkdingo.goran.finalnaaplikacija.models.Menu;
import com.mkdingo.goran.finalnaaplikacija.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by goran on 21.12.17.
 */

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {

   ArrayList<Menu>prices = new ArrayList<>();

    public void setItems(ArrayList<Menu>ceni){prices = ceni;}

    @Override
    public CheckoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           Context context = parent.getContext();
           LayoutInflater inflater = LayoutInflater.from(context);
           View view = inflater.inflate(R.layout.naracki, parent, false);
           ViewHolder holder = new ViewHolder(view);

           return holder;
    }

    @Override
    public void onBindViewHolder(CheckoutAdapter.ViewHolder holder, int position) {
        Menu menu = prices.get(position);
        holder.article.setText(menu.getFoodname());
        holder.articleprice.setText(menu.getPrice());
    }

    @Override
    public int getItemCount() {
        return prices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.proizvod)TextView article;
        @BindView(R.id.cena)TextView articleprice;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
