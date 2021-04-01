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

public class Qoy_5 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button Submit;
    private Spinner MaxYer;
    private AdView mAdView;
    private AdView mAdView2;
    private AdView mAdView3,mAdView4,mAdView5,mAdView6,mAdView7,mAdView8,mAdView9,mAdView10,mAdView11,mAdView12,mAdView13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qoy_5);
        final BottomNavigationView nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        nav1.getMenu().getItem(2).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent i =new Intent(Qoy_5.this,Activity_3.class);
                        startActivity(i);
                        break;
                    case R.id.contact:
                        Intent b = new Intent(Qoy_5.this,Search_2.class);
                        startActivity(b);
                        break;
                    case R.id.info:
                        Intent c = new Intent(Qoy_5.this,Qoy_1.class);
                        startActivity(c);
                        break;
                    case R.id.message:
                        Intent d = new Intent(Qoy_5.this,Message_1.class);
                        startActivity(d);
                        break;
                    case R.id.profilim:
                        Intent e = new Intent(Qoy_5.this,Info.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
        Submit = (Button)findViewById(R.id.submit);
        ArrayList<String> yer = new ArrayList<String>();
        yer.add("Maşınınızdakı şərnişinlər üçün olan yer sayını qeyd edin!");
        yer.add("1");
        yer.add("2");
        yer.add("3");
        yer.add("4");
        yer.add("5");
        yer.add("6");
        yer.add("7");
        yer.add("8");
        yer.add("9");
        yer.add("10");
        yer.add("11");
        yer.add("12");
        yer.add("13");
        yer.add("14");
        yer.add("15");
        yer.add("16");
        yer.add("17");
        yer.add("18");
        yer.add("19");
        yer.add("20");
        yer.add("21");
        yer.add("22");
        yer.add("23");
        yer.add("24");
        yer.add("25");
        yer.add("26");
        yer.add("27");
        yer.add("28");
        yer.add("29");
        yer.add("30");
        yer.add("31");
        yer.add("32");
        yer.add("33");
        yer.add("34");
        yer.add("35");
        yer.add("36");
        yer.add("37");
        yer.add("38");
        yer.add("39");
        yer.add("40");
        ArrayAdapter<String> placeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,yer);
        placeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
        MaxYer = (Spinner)findViewById(R.id.maxYer);
        MaxYer.setOnItemSelectedListener(this);
        MaxYer.setAdapter(placeAdapter);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MaxYerText = MaxYer.getSelectedItem().toString();
                if(MaxYerText.matches("Zəhmət olmasa, maşınınızdakı şərnişinlər üçün olan yer sayını qeyd edin!")){
                    Toast.makeText(Qoy_5.this,"Zəhmət olmasa, maşınızdakı yer sayını qeyd edin!",Toast.LENGTH_SHORT).show();
                }
                String FromPlace = getIntent().getStringExtra("FromPlace");
                String ToPlace = getIntent().getStringExtra("ToPlace");
                String Time = getIntent().getStringExtra("Time");
                String Month = getIntent().getStringExtra("Month");
                String Year = getIntent().getStringExtra("Year");
                String Day = getIntent().getStringExtra("Day");
                Intent i =new Intent(Qoy_5.this, Qoy_6.class);
                i.putExtra("Day",Day);
                i.putExtra("Year",Year);
                i.putExtra("Month",Month);
                i.putExtra("Time",Time);
                i.putExtra("ToPlace",ToPlace);
                i.putExtra("FromPlace",FromPlace);
                i.putExtra("MaxYer",MaxYerText);
                i.putExtra("Type",getIntent().getStringExtra("Type"));
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
