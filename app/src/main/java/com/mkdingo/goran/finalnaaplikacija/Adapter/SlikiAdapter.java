package com.mkdingo.goran.finalnaaplikacija.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mkdingo.goran.finalnaaplikacija.Fragmenti.FragmentMeni;
import com.mkdingo.goran.finalnaaplikacija.Models.Menu;
import com.mkdingo.goran.finalnaaplikacija.Models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoraniModel;

import java.util.ArrayList;

/**
 * Created by goran on 15.12.17.
 */

public class SlikiAdapter extends FragmentPagerAdapter{

    ArrayList<Menu> menu = new ArrayList<>();

    public void addSliki(ArrayList<Menu> meni){

       menu = meni;


    }



    public SlikiAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
            Bundle args = new Bundle();
            args.putString("link", menu.get(position).getLink());
            args.putString("price", menu.get(position).getPrice());
            args.putString("foodname", menu.get(position).getFoodname());
            FragmentMeni fragment = new FragmentMeni();
            fragment.setArguments(args);
            return fragment;
    }

    @Override
    public int getCount() {
        return menu.size();
    }

}
