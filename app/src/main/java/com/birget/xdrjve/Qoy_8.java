package com.birget.xdrjve;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Qoy_8 extends AppCompatActivity {
    private EditText CarNumber;
    private Button Submit;
    private AdView mAdView;
    private AdView mAdView2;
    private AdView mAdView3,mAdView4,mAdView5,mAdView6,mAdView7,mAdView8,mAdView9,mAdView10,mAdView11,mAdView12,mAdView13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qoy_8);
        final BottomNavigationView nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        nav1.getMenu().getItem(2).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent i =new Intent(Qoy_8.this,Activity_3.class);
                        startActivity(i);
                        break;
                    case R.id.contact:
                        Intent b = new Intent(Qoy_8.this,Search_2.class);
                        startActivity(b);
                        break;
                    case R.id.info:
                        Intent c = new Intent(Qoy_8.this,Qoy_1.class);
                        startActivity(c);
                        break;
                    case R.id.message:
                        Intent d = new Intent(Qoy_8.this,Message_1.class);
                        startActivity(d);
                        break;
                    case R.id.profilim:
                        Intent e = new Intent(Qoy_8.this,Info.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
        Submit = (Button)findViewById(R.id.submit);
        CarNumber = (EditText)findViewById(R.id.carNumber);
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
                String CarYear = getIntent().getStringExtra("CarYear");
                String FromPlace = getIntent().getStringExtra("FromPlace");
                String ToPlace = getIntent().getStringExtra("ToPlace");
                String Time = getIntent().getStringExtra("Time");
                String Month = getIntent().getStringExtra("Month");
                String Year = getIntent().getStringExtra("Year");
                String Day = getIntent().getStringExtra("Day");
                String MaxYer = getIntent().getStringExtra("MaxYer");
                String CarBrand = getIntent().getStringExtra("CarBrand");
                String CarNumberText = CarNumber.getText().toString();
                if(CarNumberText.matches("")){
                    Toast.makeText(Qoy_8.this,"Zəhmət olmasa, maşının nömrəsini qeyd edin!",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i =new Intent(Qoy_8.this, Qoy_8_2_2.class);
                i.putExtra("CarYear",CarYear);
                i.putExtra("FromPlace",FromPlace);
                i.putExtra("ToPlace",ToPlace);
                i.putExtra("Time",Time);
                i.putExtra("Month",Month);
                i.putExtra("Day",Day);
                i.putExtra("Type",getIntent().getStringExtra("Type"));
                i.putExtra("Year",Year);
                i.putExtra("MaxYer",MaxYer);
                i.putExtra("CarBrand",CarBrand);
                i.putExtra("CarNumber",CarNumberText);
                startActivity(i);
            }
        });
    }
}
