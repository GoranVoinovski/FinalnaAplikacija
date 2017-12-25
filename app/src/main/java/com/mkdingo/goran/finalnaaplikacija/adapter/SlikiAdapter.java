package com.mkdingo.goran.finalnaaplikacija.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mkdingo.goran.finalnaaplikacija.fragmenti.FragmentMeni;
import com.mkdingo.goran.finalnaaplikacija.models.Menu;

import java.util.ArrayList;

/**
 * Created by goran on 15.12.17.
 */

public class SlikiAdapter extends FragmentPagerAdapter{

    ArrayList<Menu> menu = new ArrayList<>();

    public void addSliki(ArrayList<Menu> meni){menu = meni;}

    public SlikiAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
            Bundle args = new Bundle();
            args.putString("link", menu.get(position).getLink());
            args.putString("price", menu.get(position).getPrice());
            args.putString("foodname", menu.get(position).getFoodname());
            args.putBoolean("veganska", menu.get(position).isIsveg());
            args.putInt("pozicija",position);
            FragmentMeni fragment = new FragmentMeni();
            fragment.setArguments(args);
            return fragment;
    }

    @Override
    public int getCount() {
        return menu.size();
    }
}
