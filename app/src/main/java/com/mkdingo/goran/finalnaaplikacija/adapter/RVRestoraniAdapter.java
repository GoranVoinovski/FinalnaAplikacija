package com.mkdingo.goran.finalnaaplikacija.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mkdingo.goran.finalnaaplikacija.models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.models.RestoraniModel;
import com.mkdingo.goran.finalnaaplikacija.models.RestoraniOnClickListener;
import com.mkdingo.goran.finalnaaplikacija.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by goran on 18.12.17.
 */

public class RVRestoraniAdapter extends RecyclerView.Adapter<RVRestoraniAdapter.ViewHolder> {
    RestoraniModel model = new RestoraniModel();
    Context context;
    RestoraniOnClickListener restoraniOnClickListener;

    public void setItems(ArrayList<Restorani>restaurants){model.restaurants = restaurants;}

    public RVRestoraniAdapter(Context context, RestoraniOnClickListener _restoraniOnClickListener) {
        this.context = context;
        this.restoraniOnClickListener = _restoraniOnClickListener;
    }

    @Override
    public RVRestoraniAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           Context context = parent.getContext();
           LayoutInflater inflater = LayoutInflater.from(context);
           View view = inflater.inflate(R.layout.restoran, parent, false);

           ViewHolder holder = new ViewHolder(view);

           return holder;
    }

    @Override
    public void onBindViewHolder(RVRestoraniAdapter.ViewHolder holder, final int position) {
        final Restorani restoran = model.restaurants.get(position);
        Picasso.with(context).load(restoran.getLogo()).centerInside().fit().into(holder.slikaRestoran);
        holder.imeNaRestoran.setText(restoran.getName());
        holder.rejtingRestoran.setText(restoran.getRating());
        holder.restoranilay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restoraniOnClickListener.onRestoranClick(restoran,position);
            }
        });

        holder.restoranilay.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                restoraniOnClickListener.onRestoranLongClick(restoran,position);

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imgrestaurants)ImageView slikaRestoran;
        @BindView(R.id.imerestoran)TextView imeNaRestoran;
        @BindView(R.id.rejting)TextView rejtingRestoran;
        @BindView(R.id.restoranilayout)RelativeLayout restoranilay;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
