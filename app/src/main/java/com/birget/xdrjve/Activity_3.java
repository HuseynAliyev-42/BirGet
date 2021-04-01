package com.birget.xdrjve;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class Activity_3 extends AppCompatActivity {
    private BottomNavigationView nav1;
    private Button Qoy,Axtar;
    private CardView Vast,History;
    private RelativeLayout VastRel,HistoryRel;
    private List<Message_1_adapter> model;
    private TextView Text1,Text2;
    private TextView LogOut;
    private AdView mAdView;
    private TextFragment fragment;
    private LinearLayout ScaleVast,ScaleHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String FromPlace = getIntent().getStringExtra("FromPlace");
        final String ToPlace = getIntent().getStringExtra("ToPlace");
        final String TyPE  = getIntent().getStringExtra("Type");
        if(FromPlace == null || ToPlace == null || TyPE == null){
            if(FromPlace != null || TyPE!=null){
              if(TyPE.matches("Qoy1")){
                  Intent  i =new Intent(Activity_3.this,Qoy_2.class);
                  i.putExtra("FromPlace",FromPlace);
                  startActivity(i);
                  finish();
              }
              if(TyPE.matches("Axtar1")){
                  Intent i =new Intent(Activity_3.this,Search_3.class);
                  i.putExtra("FromPlace",FromPlace);
                  startActivity(i);
                  finish();
              }
            }
        }else{
            if(TyPE.matches("Qoy")){
                Intent i = new Intent(Activity_3.this,Qoy_3.class);
                i.putExtra("FromPlace",getIntent().getStringExtra("FromPlace"));
                i.putExtra("ToPlace",getIntent().getStringExtra("ToPlace"));
                startActivity(i);
                finish();
            }
            if(TyPE.matches("Axtar")){
                Intent i =new Intent(Activity_3.this,Search_4.class);
                i.putExtra("FromPlace",FromPlace);
                i.putExtra("ToPlace",ToPlace);
                startActivity(i);
                finish();
            }
        }
        setContentView(R.layout.activity_3);
        MobileAds.initialize(this, "ca-app-pub-4810068176185955~8190980825");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-4810068176185955/4151663464");
        //
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("MyNotifications","MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        nav1.getMenu().getItem(0).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent i =new Intent(Activity_3.this,Activity_3.class);
                        startActivity(i);
                        break;
                    case R.id.contact:
                        Intent b = new Intent(Activity_3.this,Search_2.class);
                        startActivity(b);
                        break;
                    case R.id.info:
                        Intent c = new Intent(Activity_3.this,Qoy_1.class);
                        startActivity(c);
                        break;
                    case R.id.message:
                        Intent d = new Intent(Activity_3.this,Message_1.class);
                        startActivity(d);
                        break;
                    case R.id.profilim:
                        Intent e = new Intent(Activity_3.this,Info.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
        ScaleVast = (LinearLayout)findViewById(R.id.scalevast);
        ScaleHistory = (LinearLayout)findViewById(R.id.scalehistory);
        Vast = (CardView)findViewById(R.id.vast);
        History = (CardView)findViewById(R.id.history);
        VastRel  = (RelativeLayout)findViewById(R.id.request);
        HistoryRel = (RelativeLayout)findViewById(R.id.rel2);
        Text1 =(TextView)findViewById(R.id.text1);
        Text2 = (TextView)findViewById(R.id.text2);
        Vast();
        Vast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VastRel.setVisibility(View.VISIBLE);
                HistoryRel.setVisibility(View.GONE);
                Vast();
            }
        });
        History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VastRel.setVisibility(View.GONE);
                HistoryRel.setVisibility(View.VISIBLE);
                History();
            }
        });
        Vast = (CardView)findViewById(R.id.vast);
        History = (CardView)findViewById(R.id.history);
        VastRel  = (RelativeLayout)findViewById(R.id.request);
        HistoryRel = (RelativeLayout)findViewById(R.id.rel2);
        Text1 =(TextView)findViewById(R.id.text1);
        Text2 = (TextView)findViewById(R.id.text2);
        Vast();
        Vast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VastRel.setVisibility(View.VISIBLE);
                HistoryRel.setVisibility(View.GONE);
                Vast();
            }
        });
        History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VastRel.setVisibility(View.GONE);
                HistoryRel.setVisibility(View.VISIBLE);
                History();
            }
        });

    }
    private void Vast(){
        ScaleVast.setVisibility(View.VISIBLE);
        ScaleHistory.setVisibility(View.GONE);
        LogOut = (TextView)findViewById(R.id.logout);
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                Intent i =new Intent(Activity_3.this, Activity_2.class);
                startActivity(i);
            }
        });
        Qoy = (Button)findViewById(R.id.qoy);
        Axtar = (Button)findViewById(R.id.axtar);
        Qoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Activity_3.this,Qoy_1.class);
                startActivity(i);
            }
        });
        Axtar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity_3.this,Search_2.class);
                startActivity(i);
            }
        });
    }
    private void History(){
        ScaleHistory.setVisibility(View.VISIBLE);
        ScaleVast.setVisibility(View.GONE);
        Button Put = (Button)findViewById(R.id.put);
        Button Reserve = (Button)findViewById(R.id.rezerv);
        Put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Activity_3.this,ElanQoy.class);
                startActivity(i);
            }
        });
        Reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_3.this,RezervElan.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
