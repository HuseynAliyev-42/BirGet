package com.birget.xdrjve;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.birget.xdrjve.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Qoy_7 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button Submit;
    private Spinner CarYear;
    private AdView mAdView;
    private AdView mAdView2;
    private AdView mAdView3,mAdView4,mAdView5,mAdView6,mAdView7,mAdView8,mAdView9,mAdView10,mAdView11,mAdView12,mAdView13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qoy_7);
        final BottomNavigationView nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        nav1.getMenu().getItem(2).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent i =new Intent(Qoy_7.this,Activity_3.class);
                        startActivity(i);
                        break;
                    case R.id.contact:
                        Intent b = new Intent(Qoy_7.this,Search_2.class);
                        startActivity(b);
                        break;
                    case R.id.info:
                        Intent c = new Intent(Qoy_7.this,Qoy_1.class);
                        startActivity(c);
                        break;
                    case R.id.message:
                        Intent d = new Intent(Qoy_7.this,Message_1.class);
                        startActivity(d);
                        break;
                    case R.id.profilim:
                        Intent e = new Intent(Qoy_7.this,Info.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
        Submit = (Button)findViewById(R.id.submit);
        ArrayList<String> year = new ArrayList<String>();
        year.add("Maşınınızın ilini seçin!");
        year.add("2019");
        year.add("2018");
        year.add("2017");
        year.add("2016");
        year.add("2015");
        year.add("2014");
        year.add("2013");
        year.add("2012");
        year.add("2011");
        year.add("2010");
        year.add("2009");
        year.add("2008");
        year.add("2007");
        year.add("2006");
        year.add("2005");
        year.add("2004");
        year.add("2003");
        year.add("2002");
        year.add("2001");
        year.add("2000");
        year.add("1999");
        year.add("1998");
        year.add("1997");
        year.add("1996");
        year.add("1995");
        year.add("1994");
        year.add("1993");
        year.add("1992");
        year.add("1991");
        year.add("1990");
        year.add("1989");
        year.add("1988");
        year.add("1987");
        year.add("1986");
        year.add("1985");
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,year);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CarYear = (Spinner)findViewById(R.id.carYear);
        CarYear.setOnItemSelectedListener(this);
        CarYear.setAdapter(yearAdapter);
        MobileAds.initialize(this, "ca-app-pub-4810068176185955~8190980825");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-4810068176185955/4151663464");
        //
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //
        mAdView2 = findViewById(R.id.adView2);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest1);
        //
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String carYear = CarYear.getSelectedItem().toString();
                if(carYear.matches("Zəhmət olmasa, maşınınızın ilini seçin!")){
                    Toast.makeText(Qoy_7.this,"Zəhmət olmasa,maşının ilini seçin!",Toast.LENGTH_SHORT).show();
                    return;
                }
                String FromPlace = getIntent().getStringExtra("FromPlace");
                String ToPlace = getIntent().getStringExtra("ToPlace");
                String Time = getIntent().getStringExtra("Time");
                String Month = getIntent().getStringExtra("Month");
                String Year = getIntent().getStringExtra("Year");
                String Day = getIntent().getStringExtra("Day");
                String MaxYer = getIntent().getStringExtra("MaxYer");
                String CarBrand = getIntent().getStringExtra("CarBrand");
                Intent i =new Intent(Qoy_7.this, Qoy_8.class);
                i.putExtra("CarYear",carYear);
                i.putExtra("FromPlace",FromPlace);
                i.putExtra("ToPlace",ToPlace);
                i.putExtra("Time",Time);
                i.putExtra("Month",Month);
                i.putExtra("Year",Year);
                i.putExtra("Day",Day);
                i.putExtra("Type",getIntent().getStringExtra("Type"));
                i.putExtra("MaxYer",MaxYer);
                i.putExtra("CarBrand",CarBrand);
                startActivity(i);

            }
        });
     }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
