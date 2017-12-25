package com.mkdingo.goran.finalnaaplikacija.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mkdingo.goran.finalnaaplikacija.models.Menu;
import com.mkdingo.goran.finalnaaplikacija.Listeners.OnImageClickListener;
import com.mkdingo.goran.finalnaaplikacija.models.RestoraniModel;
import com.mkdingo.goran.finalnaaplikacija.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by goran on 18.12.17.
 */

public class RVMeniAdapter extends RecyclerView.Adapter<RVMeniAdapter.ViewHolder> {

    RestoraniModel meni = new RestoraniModel();
    Context context;
    OnImageClickListener onImageClickListener;

    public RVMeniAdapter(Context context, OnImageClickListener _onImageClickListener) {
        this.context = context;
        this.onImageClickListener = _onImageClickListener;
    }

    public void setItems(ArrayList<Menu>menija){meni.menu = menija;}

    @Override
    public RVMeniAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           Context context = parent.getContext();
           LayoutInflater inflater = LayoutInflater.from(context);
           View view = inflater.inflate(R.layout.hrana, parent, false);

           ViewHolder holder = new ViewHolder(view);

           return holder;
    }

    @Override
    public void onBindViewHolder(RVMeniAdapter.ViewHolder holder, final int position) {
        final Menu mennu = meni.menu.get(position);

        Picasso.with(context).load(R.drawable.vegan).centerInside().fit().into(holder.veganhrana);
        boolean  eVegan = mennu.isIsveg();
        holder.veganhrana.setVisibility(View.INVISIBLE);
        if (eVegan){holder.veganhrana.setVisibility(View.VISIBLE);}else {holder.veganhrana.setVisibility(View.INVISIBLE);}
        Picasso.with(context).load(mennu.getLink()).centerInside().fit().into(holder.hranaimg);

        holder.hrananame.setText(mennu.getFoodname());
        holder.cena.setText("Price: " + mennu.getPrice());
        holder.hranaimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImageClickListener.onImageClick(mennu,position);
            }
        });
        holder.hranaimg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onImageClickListener.onImageLongClick(mennu,position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return meni.menu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.slikahrana)ImageView hranaimg;
        @BindView(R.id.nazivhrana)TextView hrananame;
        @BindView(R.id.cenahrana)TextView cena;
        @BindView(R.id.vegan)ImageView veganhrana;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
