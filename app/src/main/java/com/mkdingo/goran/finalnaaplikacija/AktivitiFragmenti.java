package com.mkdingo.goran.finalnaaplikacija;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mkdingo.goran.finalnaaplikacija.Adapter.SlikiAdapter;
import com.mkdingo.goran.finalnaaplikacija.Models.Menu;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoranPreferences;
import com.mkdingo.goran.finalnaaplikacija.Models.Restorani;
import com.mkdingo.goran.finalnaaplikacija.Models.RestoraniModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AktivitiFragmenti extends AppCompatActivity {

    public @BindView(R.id.pager)
    ViewPager mojPager;
    public Restorani meni;
    Menu meninovo;
    public SlikiAdapter adapter;
    public int position;
    public int position1;
    RestoraniModel restorani;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivitifragmenti);

        ButterKnife.bind(this);

        restorani = RestoranPreferences.getRestoran(this);
        Intent pozicijarestoran = getIntent();
        Restorani meni = (Restorani) pozicijarestoran.getSerializableExtra("Restoran");
        position = pozicijarestoran.getIntExtra("Position", 0);
        position1 = pozicijarestoran.getIntExtra("Position1", 0);

        adapter = new SlikiAdapter(this.getSupportFragmentManager());

        adapter.addSliki(meni.menu);
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