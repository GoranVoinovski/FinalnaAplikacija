package com.mkdingo.goran.finalnaaplikacija;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mkdingo.goran.finalnaaplikacija.adapter.SlikiAdapter;
import com.mkdingo.goran.finalnaaplikacija.manager.RestoranPreferences;
import com.mkdingo.goran.finalnaaplikacija.models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.models.RestoraniModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AktivitiFragmenti extends AppCompatActivity {

    public @BindView(R.id.pager) ViewPager mojPager;
    public     Restorani restoranOdbran;
               RestoraniModel restorani;
    public     SlikiAdapter adapter;
    public int position;
    public int position1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivitifragmenti);

        ButterKnife.bind(this);

        restorani = RestoranPreferences.getRestoran(this);
        Intent pozicijarestoran = getIntent();
        position = pozicijarestoran.getIntExtra("Position", 0);
        position1 = pozicijarestoran.getIntExtra("Position1", 0);

        restoranOdbran = restorani.restaurants.get(position);

        adapter = new SlikiAdapter(this.getSupportFragmentManager());

        adapter.addSliki(restoranOdbran.menu);
        mojPager.setAdapter(adapter);
        mojPager.setCurrentItem(position1);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("Refresh","Refresh");
        setResult(RESULT_OK,intent);
        finish();
    }
}