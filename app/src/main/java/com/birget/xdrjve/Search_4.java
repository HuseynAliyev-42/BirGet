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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search_4 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button Submit;
    private Spinner Time;
    private AdView mAdView;
    private AdView mAdView2;
    private AdView mAdView3,mAdView4,mAdView5,mAdView6,mAdView7,mAdView8,mAdView9,mAdView10,mAdView11,mAdView12,mAdView13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_4);
        Submit = (Button)findViewById(R.id.submit);
        ArrayList<String> timeOption = new ArrayList<String>();
        timeOption.add("Gedəcəyiniz saatı yazın!");
        timeOption.add("00:30");
        timeOption.add("01:00");
        timeOption.add("01:30");
        timeOption.add("02:00");
        timeOption.add("02:30");
        timeOption.add("03:00");
        timeOption.add("03:30");
        timeOption.add("04:00");
        timeOption.add("04:30");
        timeOption.add("05:00");
        timeOption.add("05:30");
        timeOption.add("06:00");
        timeOption.add("06:30");
        timeOption.add("07:00");
        timeOption.add("07:30");
        timeOption.add("08:00");
        timeOption.add("08:30");
        timeOption.add("09:00");
        timeOption.add("09:30");
        timeOption.add("10:00");
        timeOption.add("10:30");
        timeOption.add("11:00");
        timeOption.add("11:30");
        timeOption.add("12:00");
        timeOption.add("12:30");
        timeOption.add("13:00");
        timeOption.add("13:30");
        timeOption.add("14:00");
        timeOption.add("14:30");
        timeOption.add("15:00");
        timeOption.add("15:30");
        timeOption.add("16:00");
        timeOption.add("16:30");
        timeOption.add("17:00");
        timeOption.add("17:30");
        timeOption.add("18:00");
        timeOption.add("18:30");
        timeOption.add("19:00");
        timeOption.add("19:30");
        timeOption.add("20:00");
        timeOption.add("20:30");
        timeOption.add("21:00");
        timeOption.add("21:30");
        timeOption.add("22:00");
        timeOption.add("22:30");
        timeOption.add("23:00");
        timeOption.add("23:30");
        timeOption.add("00:00");
        Time = (Spinner)findViewById(R.id.time);
        Time.setOnItemSelectedListener(this);
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,timeOption);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Time.setAdapter(timeAdapter);
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
                                    Intent i =new Intent(Search_4.this,Qoy_9.class);
                                    startActivity(i);
                                    break;
                                case R.id.myinfo:
                                    Intent b = new Intent(Search_4.this, Info_2.class);
                                    startActivity(b);
                                    break;
                                case R.id.message:
                                    Intent c = new Intent(Search_4.this, Message_1_2.class);
                                    startActivity(c);
                                    break;
                                case R.id.customer:
                                    Intent d = new Intent(Search_4.this, Search_2.class);
                                    startActivity(d);
                                    break;
                                case R.id.rel1:
                                    Intent e = new Intent(Search_4.this, Request.class);
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
                                    Intent i =new Intent(Search_4.this,Activity_3.class);
                                    startActivity(i);
                                    break;
                                case R.id.contact:
                                    Intent b = new Intent(Search_4.this,Search_2.class);
                                    startActivity(b);
                                    break;
                                case R.id.info:
                                    Intent c = new Intent(Search_4.this,Qoy_1.class);
                                    startActivity(c);
                                    break;
                                case R.id.message:
                                    Intent d = new Intent(Search_4.this,Message_1.class);
                                    startActivity(d);
                                    break;
                                case R.id.profilim:
                                    Intent e = new Intent(Search_4.this,Info.class);
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
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TimeText = Time.getSelectedItem().toString();
                if(TimeText.matches("Zəhmət olmasa, gedəcəyiniz saatı yazın!")){
                    Toast.makeText(Search_4.this,"Zəhmət olmasa, vaxtı müəyyənləşdirin!",Toast.LENGTH_SHORT).show();
                    return;
                }
                String ToPlaceText = getIntent().getStringExtra("ToPlace");
                String FromPlaceText = getIntent().getStringExtra("FromPlace");
                Intent i =new Intent(Search_4.this, Search_5.class);
                i.putExtra("FromPlace",FromPlaceText);
                i.putExtra("ToPlace",ToPlaceText);
                i.putExtra("Time",TimeText);
                startActivity(i);
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
