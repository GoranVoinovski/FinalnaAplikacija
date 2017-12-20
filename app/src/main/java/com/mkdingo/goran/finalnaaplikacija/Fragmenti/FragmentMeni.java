package com.mkdingo.goran.finalnaaplikacija.Fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mkdingo.goran.finalnaaplikacija.Models.Menu;
import com.mkdingo.goran.finalnaaplikacija.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by goran on 19.12.17.
 */

public class FragmentMeni extends Fragment {

    @BindView(R.id.slika)ImageView pic;
    @BindView(R.id.namefood)TextView franame;
    @BindView(R.id.nameprice)TextView fracena;
    private Unbinder mUnbind;
    String link;
    Menu meni;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_meni,null);

        mUnbind = ButterKnife.bind(this, view);

        link =  getArguments().getString("link");

        Picasso.with(getActivity()).load(link).centerInside().fit().into(pic);
        franame.setText(getArguments().getString("price"));
        fracena.setText(getArguments().getString("foodname"));

        return view;


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbind.unbind();
    }

}
