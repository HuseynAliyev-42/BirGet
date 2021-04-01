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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Qoy_8_2_2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner Price;
    private Button Submit;
    private AdView mAdView;
    private AdView mAdView2;
    private AdView mAdView3,mAdView4,mAdView5,mAdView6,mAdView7,mAdView8,mAdView9,mAdView10,mAdView11,mAdView12,mAdView13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qoy_8_2_2);
        final BottomNavigationView nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        nav1.getMenu().getItem(2).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent i =new Intent(Qoy_8_2_2.this,Activity_3.class);
                        startActivity(i);
                        break;
                    case R.id.contact:
                        Intent b = new Intent(Qoy_8_2_2.this,Search_2.class);
                        startActivity(b);
                        break;
                    case R.id.info:
                        Intent c = new Intent(Qoy_8_2_2.this,Qoy_1.class);
                        startActivity(c);
                        break;
                    case R.id.message:
                        Intent d = new Intent(Qoy_8_2_2.this,Message_1.class);
                        startActivity(d);
                        break;
                    case R.id.profilim:
                        Intent e = new Intent(Qoy_8_2_2.this,Info.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
        Submit = (Button)findViewById(R.id.submit);
        ArrayList<String> priceOptions = new ArrayList<String>();
        priceOptions.add("Hər bir şəxs üçün qiyməti seçin!");
        priceOptions.add("0");
        priceOptions.add("1");
        priceOptions.add("2");
        priceOptions.add("3");
        priceOptions.add("4");
        priceOptions.add("5");
        priceOptions.add("6");
        priceOptions.add("7");
        priceOptions.add("8");
        priceOptions.add("9");
        priceOptions.add("10");
        priceOptions.add("11");
        priceOptions.add("12");
        priceOptions.add("13");
        priceOptions.add("14");
        priceOptions.add("15");
        priceOptions.add("16");
        priceOptions.add("17");
        priceOptions.add("18");
        priceOptions.add("19");
        priceOptions.add("20");
        priceOptions.add("21");
        priceOptions.add("22");
        priceOptions.add("23");
        priceOptions.add("24");
        priceOptions.add("25");
        priceOptions.add("26");
        priceOptions.add("27");
        priceOptions.add("28");
        priceOptions.add("29");
        priceOptions.add("30");
        ArrayAdapter<String> priceAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,priceOptions);
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Price = (Spinner)findViewById(R.id.price);
        Price.setOnItemSelectedListener(this);
        Price.setAdapter(priceAdapter);
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
                String priceText = Price.getSelectedItem().toString();
                if(priceText.matches("Hər bir şəxs üçün qiyməti seçin!")){
                    Toast.makeText(Qoy_8_2_2.this,"Zəhmət olmasa, qiyməti seçin!",Toast.LENGTH_SHORT).show();
                    return;
                }
                String CarYear = getIntent().getStringExtra("CarYear");
                String FromPlace = getIntent().getStringExtra("FromPlace");
                String ToPlace = getIntent().getStringExtra("ToPlace");
                String Time = getIntent().getStringExtra("Time");
                String Month = getIntent().getStringExtra("Month");
                String Year = getIntent().getStringExtra("Year");
                String Day = getIntent().getStringExtra("Day");
                String MaxYer = getIntent().getStringExtra("MaxYer");
                String CarBrand = getIntent().getStringExtra("CarBrand");
                String CarNumberText = getIntent().getStringExtra("CarNumber");
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                String uid = user.getUid();
                Intent i =new Intent(Qoy_8_2_2.this, Qoy_8_3_2.class);
                i.putExtra("CarYear",CarYear);
                i.putExtra("Type",getIntent().getStringExtra("Type"));
                i.putExtra("Price",priceText);
                i.putExtra("FromPlace",FromPlace);
                i.putExtra("ToPlace",ToPlace);
                i.putExtra("Time",Time);
                i.putExtra("Month",Month);
                i.putExtra("Year",Year);
                i.putExtra("Day",Day);
                i.putExtra("MaxYer",MaxYer);
                i.putExtra("CarBrand",CarBrand);
                i.putExtra("CarNumber",CarNumberText);
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
