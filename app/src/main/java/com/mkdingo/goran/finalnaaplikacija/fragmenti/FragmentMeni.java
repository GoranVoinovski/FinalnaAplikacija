package com.mkdingo.goran.finalnaaplikacija.fragmenti;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mkdingo.goran.finalnaaplikacija.AktivitiFragmenti;
import com.mkdingo.goran.finalnaaplikacija.EditAddFood;
import com.mkdingo.goran.finalnaaplikacija.models.Menu;
import com.mkdingo.goran.finalnaaplikacija.manager.RestoranPreferences;
import com.mkdingo.goran.finalnaaplikacija.models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.models.RestoraniModel;
import com.mkdingo.goran.finalnaaplikacija.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by goran on 19.12.17.
 */

public class FragmentMeni extends Fragment {

    @BindView(R.id.slika)ImageView pic;
    @BindView(R.id.veganfra)ImageView veganska;
    @BindView(R.id.namefood)TextView franame;
    @BindView(R.id.nameprice)TextView fracena;
    @BindView(R.id.editfood)Button edithrana;
    private Unbinder mUnbind;
    String link;
    Menu meni;
    Menu meni2;
    int pozicija = 0;
    int pos = 0;
    Restorani restoranodbran;
    RestoraniModel restorani;
    boolean veganskahrana;
    String ime;
    String cena;
    String slika;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meni,null);
        mUnbind = ButterKnife.bind(this, view);
        ime = franame.getText().toString();
        cena = fracena.getText().toString();
        restorani = RestoranPreferences.getRestoran(getActivity());

        Intent pozicijarestoran = getActivity().getIntent();
        restoranodbran = (Restorani) pozicijarestoran.getSerializableExtra("Restoran");
        pos = pozicijarestoran.getIntExtra("Position", 0);

        link =  getArguments().getString("link");
        pozicija = getArguments().getInt("pozicija");
        ime = getArguments().getString("foodname");
        cena = getArguments().getString("price");
        veganskahrana = getArguments().getBoolean("veganska");
        meni = new Menu(link,cena,ime,veganskahrana);
        Picasso.with(getActivity()).load(link).centerInside().fit().into(pic);
        Picasso.with(getActivity()).load(R.drawable.vegan).centerInside().fit().into(veganska);
        franame.setText(meni.getFoodname());
        fracena.setText("Price: " + meni.getPrice());

        veganska.setVisibility(View.INVISIBLE);
        if (veganskahrana){veganska.setVisibility(View.VISIBLE);}else {veganska.setVisibility(View.INVISIBLE);}

        return view;
    }

    @OnClick(R.id.editfood)
    public void Edit(){
        slika = link;
        meni.setPrice(cena);
        meni.setFoodname(ime);
        meni.setLink(slika);
        meni.setIsveg(veganskahrana);
        Intent intent = new Intent(getActivity(), EditAddFood.class);
        intent.putExtra("Meni",meni);
        startActivityForResult(intent,2000);


    }

    @Override
    public void onDestroy() {super.onDestroy();mUnbind.unbind();}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == getActivity().RESULT_OK && requestCode == 2000) {
            if (data.hasExtra("NovoMeni")) {
                meni2 = (Menu) data.getSerializableExtra("NovoMeni");
                Picasso.with(getActivity()).load(meni2.getLink()).centerInside().fit().into(pic);
                fracena.setText("Price: " + meni2.getPrice());
                franame.setText(meni2.getFoodname());
                veganska.setVisibility(View.INVISIBLE);
                veganskahrana = meni2.isIsveg();
                if (veganskahrana){veganska.setVisibility(View.VISIBLE);}else {veganska.setVisibility(View.INVISIBLE);}
                restoranodbran.menu.remove(pozicija);
                restoranodbran.menu.add(pozicija,meni2);
                restorani.restaurants.remove(pos);
                restorani.restaurants.add(pos, restoranodbran);
                RestoranPreferences.addRestoran(restorani,getActivity());
                ((AktivitiFragmenti)getActivity()).adapter.notifyDataSetChanged();
            }
        }
    }
}
