package com.birget.xdrjve;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search_5_2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner Cigar;
    private Button Submit;
    private String Day,Month,Year,FromPlace,ToPlace,Time;
    private AdView mAdView;
    private AdView mAdView2;
    private AdView mAdView3,mAdView4,mAdView5,mAdView6,mAdView7,mAdView8,mAdView9,mAdView10,mAdView11,mAdView12,mAdView13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_5_2);
        Submit = (Button) findViewById(R.id.submit);
        final BottomNavigationView nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        final BottomNavigationView nav2 = (BottomNavigationView)findViewById(R.id.navigation2);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String MyUid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                if(dataSnapshot.hasChild("drive")){
                    nav1.setVisibility(View.INVISIBLE);
                    nav2.setVisibility(View.VISIBLE);
                    nav2.getMenu().getItem(3).setChecked(true);
                    nav2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.home:
                                    Intent i =new Intent(Search_5_2.this,Qoy_9.class);
                                    startActivity(i);
                                    break;
                                case R.id.myinfo:
                                    Intent b = new Intent(Search_5_2.this, Info_2.class);
                                    startActivity(b);
                                    break;
                                case R.id.message:
                                    Intent c = new Intent(Search_5_2.this, Message_1_2.class);
                                    startActivity(c);
                                    break;
                                case R.id.customer:
                                    Intent d = new Intent(Search_5_2.this, Search_2.class);
                                    startActivity(d);
                                    break;
                                case R.id.rel1:
                                    Intent e = new Intent(Search_5_2.this, Request.class);
                                    startActivity(e);
                                    break;
                            }
                            return true;
                        }
                    });
                }else{
                    nav1.setVisibility(View.VISIBLE);
                    nav2.setVisibility(View.INVISIBLE);
                    nav1.getMenu().getItem(1).setChecked(true);
                    nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.home:
                                    Intent i =new Intent(Search_5_2.this,Activity_3.class);
                                    startActivity(i);
                                    break;
                                case R.id.contact:
                                    Intent b = new Intent(Search_5_2.this,Search_2.class);
                                    startActivity(b);
                                    break;
                                case R.id.info:
                                    Intent c = new Intent(Search_5_2.this,Qoy_1.class);
                                    startActivity(c);
                                    break;
                                case R.id.message:
                                    Intent d = new Intent(Search_5_2.this,Message_1.class);
                                    startActivity(d);
                                    break;
                                case R.id.profilim:
                                    Intent e = new Intent(Search_5_2.this,Info.class);
                                    startActivity(e);
                                    break;
                            }
                            return true;
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){

            }
        });
        ArrayList<String> cigarOptions = new ArrayList<String>();
        cigarOptions.add("Sərnişinlərə cinsiyyət tələbiniz var?");
        cigarOptions.add("Fərqi yoxdur");
        cigarOptions.add("Yalnız qadın");
        cigarOptions.add("Yalnız kişi");
        ArrayAdapter<String> cigarAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cigarOptions);
        cigarAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Cigar = (Spinner) findViewById(R.id.cigar);
        Cigar.setOnItemSelectedListener(this);
        Cigar.setAdapter(cigarAdapter);
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
        Day = getIntent().getStringExtra("Day");
        Month = getIntent().getStringExtra("Month");
        Year = getIntent().getStringExtra("Year");
        FromPlace = getIntent().getStringExtra("FromPlace");
        ToPlace = getIntent().getStringExtra("ToPlace");
        Time = getIntent().getStringExtra("Time");
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//whatever you want just you have to launch overhere.
                        String gender = Cigar.getSelectedItem().toString();
                        if(gender.matches("Sərnişinlərə cinsiyyət tələbiniz var?")) {
                            Toast.makeText(Search_5_2.this, "Zəhmət olmasa, boşluqları doldurun!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent i = new Intent(Search_5_2.this, Search_6.class);
                        i.putExtra("Day",Day);
                        i.putExtra("Month",Month);
                        i.putExtra("Year",Year);
                        i.putExtra("FromPlace",FromPlace);
                        i.putExtra("ToPlace",ToPlace);
                        i.putExtra("Time",Time);
                        i.putExtra("Gender",gender);
                        startActivity(i);
                        finish();
                    }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
