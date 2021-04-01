package com.birget.xdrjve;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import java.util.List;

public class Search_6 extends AppCompatActivity {
    private String Day;
    private String Month;
    private String Year;
    private String Time;
    private String FromPlace;
    private String ToPlace;
    private ListView listViewArtist;
    private RelativeLayout Line2;
    private LinearLayout Linear1;
    private AdView mAdView;
    private AdView mAdView2;
    private DatabaseReference db1;
    private List<com.birget.xdrjve.Search_6_model> artistList;
    private List<com.birget.xdrjve.Search_6_model> artistList2, artistList3, artistList4, artistList5, artistList6, artistList7, artistList8, artistList9, artistList10, artistList11;
    private BottomNavigationView nav1;
    private String Gender;
    private String Gender_2;
    private TextView Condition;
    private int month, day;
    private ListView List2;
    private Button More;
    private int a,b;
    private int day2, day3, day4, day5, day6, day7, day8, day9, day10, day11,day12,day13,day14,day15,day16,day17,day18,day19,day20,day21;
    private int month2, month3, month4, month5, month6, month7, month8, month9, month10, month11,month12,month13,month14,month15,month16,month17,month18,month19,month20,month21;
    private Button All;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_6);
        final BottomNavigationView nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        final BottomNavigationView nav2 = (BottomNavigationView)findViewById(R.id.navigation2);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String MyUid = user.getUid();
        DatabaseReference db100 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
        db100.addValueEventListener(new ValueEventListener() {
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
                                    Intent i =new Intent(Search_6.this,Qoy_9.class);
                                    startActivity(i);
                                    break;
                                case R.id.myinfo:
                                    Intent b = new Intent(Search_6.this, Info_2.class);
                                    startActivity(b);
                                    break;
                                case R.id.message:
                                    Intent c = new Intent(Search_6.this, Message_1_2.class);
                                    startActivity(c);
                                    break;
                                case R.id.customer:
                                    Intent d = new Intent(Search_6.this, Search_2.class);
                                    startActivity(d);
                                    break;
                                case R.id.rel1:
                                    Intent e = new Intent(Search_6.this, Request.class);
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
                                    Intent i =new Intent(Search_6.this,Activity_3.class);
                                    startActivity(i);
                                    break;
                                case R.id.contact:
                                    Intent b = new Intent(Search_6.this,Search_2.class);
                                    startActivity(b);
                                    break;
                                case R.id.info:
                                    Intent c = new Intent(Search_6.this,Qoy_1.class);
                                    startActivity(c);
                                    break;
                                case R.id.message:
                                    Intent d = new Intent(Search_6.this,Message_1.class);
                                    startActivity(d);
                                    break;
                                case R.id.profilim:
                                    Intent e = new Intent(Search_6.this,Info.class);
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
        final ProgressDialog csprogress=new ProgressDialog(Search_6.this);
        csprogress.setMessage("Yüklənir...");
        csprogress.show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                csprogress.dismiss();
//whatever you want just you have to launch overhere.
            }
        }, 10000);
        b = 0;
        a = 0;
        All = (Button)findViewById(R.id.all);
        More = (Button)findViewById(R.id.more);
        List2 = (ListView) findViewById(R.id.list2);
        Line2 = (RelativeLayout) findViewById(R.id.line2);
        Condition = (TextView) findViewById(R.id.condition);
        Day = getIntent().getStringExtra("Day");
        Month = getIntent().getStringExtra("Month");
        Year = getIntent().getStringExtra("Year");
        Time = getIntent().getStringExtra("Time");
        FromPlace = getIntent().getStringExtra("FromPlace");
        ToPlace = getIntent().getStringExtra("ToPlace");
        Gender = getIntent().getStringExtra("Gender");
        MobileAds.initialize(this, "ca-app-pub-4810068176185955~8190980825");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-4810068176185955/4151663464");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView2 = findViewById(R.id.adView2);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest1);
        db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        artistList = new ArrayList<>();
        artistList2 = new ArrayList<>();
        listViewArtist = (ListView) findViewById(R.id.list1);
        if (Gender.matches("Yalnız qadın")) {
            Gender_2 = "woman";
        }
        if (Gender.matches("Yalnız kişi")) {
            Gender_2 = "man";
        }
        if (Gender.matches("Fərqi yoxdur")) {
            Gender_2 = "manwoman";
        }
        db1.orderByChild("travel").equalTo(FromPlace + ToPlace + Time + Day + Month + Year + Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                artistList.clear();
                Toast.makeText(Search_6.this, FromPlace + ToPlace + Time + Day + Month + Year + Gender_2, Toast.LENGTH_LONG).show();
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {
                    String uid = artistSnapshot.child("uid").getValue().toString();
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String myUid = user.getUid();
                    if (uid.matches(myUid)) {
                        return;
                    }
                    Search_6_model model = artistSnapshot.getValue(Search_6_model.class);
                    artistList.add(model);
                }
                Search_6_adapter adapter = new Search_6_adapter(Search_6.this, artistList);
                listViewArtist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewArtist.setVisibility(View.INVISIBLE);
                Condition.setVisibility(View.INVISIBLE);
                Line2.setVisibility(View.VISIBLE);
                More.setVisibility(View.INVISIBLE);
                All.setVisibility(View.INVISIBLE);
                birgun();
                ikigun();
                ucgun();
                dordgun();
                besgun();
                altigun();
                yeddigun();
                seqqizgun();
                doqquzgun();
                ongun();
            }
        });
        All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewArtist.setVisibility(View.INVISIBLE);
                Condition.setVisibility(View.INVISIBLE);
                Line2.setVisibility(View.VISIBLE);
                More.setVisibility(View.INVISIBLE);
                All.setVisibility(View.INVISIBLE);
                DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2");
                db3.orderByChild("fromplace").equalTo(FromPlace).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        artistList2.clear();
                        for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                            String uid = dataSnapshot1.child("uid").getValue().toString();
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String myUid = user.getUid();
                            if (uid.matches(myUid)) {
                                return;
                            }
                            Search_6_model customerRequest = dataSnapshot1.getValue(Search_6_model.class);
                            artistList2.add(customerRequest);
                            Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                            List2.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        if (Time.matches("00:00")) {
            birinyarisi();
            bir();
            birotuz();
            iki();
            ikiotuz();
            uc();
            ucotuz();
            dord();
            dordotuz();
            bes();
            besotuz();
            alti();
            altiotuz();
            yeddi();
            yeddiotuz();
            seqqiz();
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("00:30")) {
            bir();
            birotuz();
            iki();
            ikiotuz();
            uc();
            ucotuz();
            dord();
            dordotuz();
            bes();
            besotuz();
            alti();
            altiotuz();
            yeddi();
            yeddiotuz();
            seqqiz();
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();

        }
        if (Time.matches("01:00")) {
            birotuz();
            iki();
            ikiotuz();
            uc();
            ucotuz();
            dord();
            dordotuz();
            bes();
            besotuz();
            alti();
            altiotuz();
            yeddi();
            yeddiotuz();
            seqqiz();
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("01:30")) {
            iki();
            ikiotuz();
            uc();
            ucotuz();
            dord();
            dordotuz();
            bes();
            besotuz();
            alti();
            altiotuz();
            yeddi();
            yeddiotuz();
            seqqiz();
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("02:00")) {
            ikiotuz();
            uc();
            ucotuz();
            dord();
            dordotuz();
            bes();
            besotuz();
            alti();
            altiotuz();
            yeddi();
            yeddiotuz();
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("02:30")) {
            uc();
            ucotuz();
            dord();
            dordotuz();
            bes();
            besotuz();
            alti();
            altiotuz();
            yeddi();
            yeddiotuz();
            seqqiz();
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("03:00")) {
            ucotuz();
            dord();
            dordotuz();
            bes();
            besotuz();
            alti();
            altiotuz();
            yeddi();
            yeddiotuz();
            seqqiz();
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("03:30")) {
            dord();
            dordotuz();
            bes();
            besotuz();
            alti();
            altiotuz();
            yeddi();
            yeddiotuz();
            seqqiz();
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("04:00")) {
            dordotuz();
            bes();
            besotuz();
            alti();
            altiotuz();
            yeddi();
            yeddiotuz();
            seqqiz();
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("04:30")) {
            bes();
            besotuz();
            alti();
            altiotuz();
            yeddi();
            yeddiotuz();
            seqqiz();
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("05:00")) {
            besotuz();
            alti();
            altiotuz();
            yeddi();
            yeddiotuz();
            seqqiz();
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("05:30")) {
            alti();
            altiotuz();
            yeddi();
            yeddiotuz();
            seqqiz();
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("06:00")) {
            altiotuz();
            yeddi();
            yeddiotuz();
            seqqiz();
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("06:30")) {
            yeddi();
            yeddiotuz();
            seqqiz();
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("07:00")) {
            yeddiotuz();
            seqqiz();
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("07:30")) {
            seqqiz();
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("08:00")) {
            seqqizotuz();
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("08:30")) {
            doqquz();
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("09:00")) {
            doqquzotuz();
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("09:30")) {
            on();
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("10:00")) {
            onotuz();
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("10:30")) {
            onbir();
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("11:00")) {
            onbirotuz();
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("11:30")) {
            oniki();
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("12:00")) {
            onikiotuz();
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("12:30")) {
            onuc();
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("13:00")) {
            onucotuz();
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("13:30")) {
            ondord();
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("14:00")) {
            ondordotuz();
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("14:30")) {
            onbes();
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("15:00")) {
            onbesotuz();
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("15:30")) {
            onalti();
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("16:00")) {
            onaltiotuz();
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("16:30")) {
            onyeddi();
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("17:00")) {
            onyeddiotuz();
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("17:30")) {
            onseqqiz();
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("18:00")) {
            onseqqizotuz();
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("18:30")) {
            ondoqquz();
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("19:00")) {
            ondoqquzotuz();
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("19:30")) {
            iyirmi();
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("20:00")) {
            iyirmiotuz();
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("20:30")) {
            iyirmizbir();
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("21:00")) {
            iyirmibirotuz();
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("21:30")) {
            iyirmiiki();
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("22:00")) {
            iyirmiikiotuz();
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("22:30")) {
            iyirmiuc();
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("23:00")) {
            iyirmiucotuz();
            iyirmidord();
        }
        if (Time.matches("23:30")) {
            iyirmidord();
        }
    }
    private void birgun(){
        day  = Integer.valueOf(Day);
        month = Integer.valueOf(Month);
        if(month == 1 || month == 3 || month ==5 || month == 7 || month == 9 || month == 11 ){
            if(day == 31){
                day2 = 1;
                month2 = month+1;
            }else{
                day2 = day+1;
                month2 = month;
            }
        }
        if(month == 4 || month == 6 || month == 8 || month == 10 || month == 12 ){
            if(day == 30){
                day2 = 1;
                month2 = month+1;
            }else{
                day2 = day+1;
                month2 = month;
            }
        }
        if(month == 2){
            if(day == 30){
                day2 = 1;
                month2 = month+1;
            }else{
                day2 = day+1;
                month2 = month;
            }
        }
        DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db3.orderByChild("tra").equalTo(FromPlace+ToPlace+month2+day2+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                artistList2.clear();
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Toast.makeText(Search_6.this,FromPlace+ToPlace+month2+day2+Gender_2,Toast.LENGTH_LONG).show();
                    Search_6_model customerRequest = artistSnapshot.getValue(Search_6_model.class);
                    artistList2.add(customerRequest);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    List2.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void ikigun(){
        if(month2 == 1 || month2 == 3 || month2 ==5 || month2 == 7 || month2 == 9 || month2 == 11 ){
            if(day2 == 31){
                day3 = 1;
                month3 = month2+1;
            }else{
                day3 = day2+1;
                month3 = month2;
            }
        }
        if(month2 == 4 || month2 == 6 || month2 == 8 || month2 == 10 || month2 == 12 ){
            if(day2 == 30){
                day3 = 1;
                month3 = month2+1;
            }else{
                day3 = day2+1;
                month3 = month2;
            }
        }
        if(month2 == 2){
            if(day2 == 30){
                day3 = 1;
                month3 = month2+1;
            }else{
                day3 = day2+1;
                month3 = month2;
            }
        }
        DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db3.orderByChild("tra").equalTo(FromPlace+ToPlace+month3+day3+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Search_6_model customerRequest = artistSnapshot.getValue(Search_6_model.class);
                    artistList2.add(customerRequest);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    List2.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void ucgun(){
        if(month3 == 1 || month3 == 3 || month3 ==5 || month3 == 7 || month3 == 9 || month3 == 11 ){
            if(day3 == 31){
                day4 = 1;
                month4 = month3+1;
            }else{
                day4 = day3+1;
                month4 = month3;
            }
        }
        if(month3 == 4 || month3 == 6 || month3 == 8 || month3 == 10 || month3 == 12 ){
            if(day3 == 30){
                day4 = 1;
                month4 = month3+1;
            }else{
                day4 = day3+1;
                month4 = month3;
            }
        }
        if(month3 == 2){
            if(day3 == 30){
                day4 = 1;
                month4 = month3+1;
            }else{
                day4 = day3+1;
                month4 = month3;
            }
        }
        DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db3.orderByChild("tra").equalTo(FromPlace+ToPlace+month4+day4+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Search_6_model customerRequest = artistSnapshot.getValue(Search_6_model.class);
                    artistList2.add(customerRequest);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    List2.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void dordgun(){
        if(month4 == 1 || month4 == 3 || month4 ==5 || month4 == 7 || month4 == 9 || month4 == 11 ){
            if(day4 == 31){
                day5 = 1;
                month5 = month4+1;
            }else{
                day5 = day4+1;
                month5 = month4;
            }
        }
        if(month4 == 4 || month4 == 6 || month4 == 8 || month4 == 10 || month4 == 12 ){
            if(day4 == 30){
                day5 = 1;
                month5 = month4+1;
            }else{
                day5 = day4+1;
                month5 = month4;
            }
        }
        if(month4 == 2){
            if(day4 == 30){
                day5 = 1;
                month5 = month4+1;
            }else{
                day5 = day4+1;
                month5 = month4;
            }
        }
        DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db3.orderByChild("tra").equalTo(FromPlace+ToPlace+month5+day5+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Search_6_model customerRequest = artistSnapshot.getValue(Search_6_model.class);
                    artistList2.add(customerRequest);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    List2.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void besgun(){
        if(month5 == 1 || month5 == 3 || month5 ==5 || month5 == 7 || month5 == 9 || month5 == 11 ){
            if(day5 == 31){
                day6 = 1;
                month6 = month5+1;
            }else{
                day6 = day5+1;
                month6 = month5;
            }
        }
        if(month5 == 4 || month5 == 6 || month5 == 8 || month5 == 10 || month5 == 12 ){
            if(day5 == 30){
                day6 = 1;
                month6 = month5+1;
            }else{
                day6 = day5+1;
                month6 = month5;
            }
        }
        if(month5 == 2){
            if(day5 == 30){
                day6 = 1;
                month6 = month5+1;
            }else{
                day6 = day5+1;
                month6 = month5;
            }
        }
        DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db3.orderByChild("tra").equalTo(FromPlace+ToPlace+month6+day6+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Search_6_model customerRequest = artistSnapshot.getValue(Search_6_model.class);
                    artistList2.add(customerRequest);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    List2.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void altigun(){
        if(month6 == 1 || month6 == 3 || month6 ==5 || month6 == 7 || month6 == 9 || month6 == 11 ){
            if(day6 == 31){
                day7 = 1;
                month7 = month6+1;
            }else{
                day7 = day6+1;
                month7 = month6;
            }
        }
        if(month6 == 4 || month6 == 6 || month6 == 8 || month6 == 10 || month6 == 12 ){
            if(day6 == 30){
                day7 = 1;
                month7 = month6+1;
            }else{
                day7 = day6+1;
                month7 = month6;
            }
        }
        if(month6 == 2){
            if(day6 == 30){
                day7 = 1;
                month7 = month6+1;
            }else{
                day7 = day6+1;
                month7 = month6;
            }
        }
        DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db3.orderByChild("tra").equalTo(FromPlace+ToPlace+month7+day7+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Search_6_model customerRequest = artistSnapshot.getValue(Search_6_model.class);
                    artistList2.add(customerRequest);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    List2.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void yeddigun(){
        if(month7 == 1 || month7 == 3 || month7 ==5 || month7 == 7 || month7 == 9 || month7 == 11 ){
            if(day7 == 31){
                day8 = 1;
                month8 = month7+1;
            }else{
                day8 = day7+1;
                month8 = month7;
            }
        }
        if(month7 == 4 || month7 == 6 || month7 == 8 || month7 == 10 || month7 == 12 ){
            if(day7 == 30){
                day8 = 1;
                month8 = month7+1;
            }else{
                day8 = day7+1;
                month8 = month7;
            }
        }
        if(month7 == 2){
            if(day7 == 30){
                day8 = 1;
                month8 = month7+1;
            }else{
                day8 = day7+1;
                month8 = month7;
            }
        }
        DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db3.orderByChild("tra").equalTo(FromPlace+ToPlace+month8+day8+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Search_6_model customerRequest = artistSnapshot.getValue(Search_6_model.class);
                    artistList2.add(customerRequest);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    List2.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void seqqizgun(){
        if(month8 == 1 || month8 == 3 || month8 ==5 || month8 == 7 || month8 == 9 || month8 == 11 ){
            if(day8 == 31){
                day9 = 1;
                month9 = month8+1;
            }else{
                day9 = day8+1;
                month9 = month8;
            }
        }
        if(month8 == 4 || month8 == 6 || month8 == 8 || month8 == 10 || month8 == 12 ){
            if(day8 == 30){
                day9 = 1;
                month9 = month8+1;
            }else{
                day9 = day8+1;
                month9 = month8;
            }
        }
        if(month8 == 2){
            if(day8 == 30){
                day9 = 1;
                month9 = month8+1;
            }else{
                day9 = day8+1;
                month9 = month8;
            }
        }
        DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db3.orderByChild("tra").equalTo(FromPlace+ToPlace+month9+day9+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Search_6_model customerRequest = artistSnapshot.getValue(Search_6_model.class);
                    artistList2.add(customerRequest);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    List2.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void doqquzgun(){
        if(month9 == 1 || month9 == 3 || month9 ==5 || month9 == 7 || month9 == 9 || month9 == 11 ){
            if(day9 == 31){
                day10 = 1;
                month10 = month9+1;
            }else{
                day10 = day9+1;
                month10 = month9;
            }
        }
        if(month9 == 4 || month9 == 6 || month9 == 8 || month9 == 10 || month9 == 12 ){
            if(day9 == 30){
                day10 = 1;
                month10 = month9+1;
            }else{
                day10 = day9+1;
                month10 = month9;
            }
        }
        if(month9 == 2){
            if(day9 == 30){
                day10 = 1;
                month10 = month9+1;
            }else{
                day10 = day9+1;
                month10 = month9;
            }
        }
        DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db3.orderByChild("tra").equalTo(FromPlace+ToPlace+month10+day10+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Search_6_model customerRequest = artistSnapshot.getValue(Search_6_model.class);
                    artistList2.add(customerRequest);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    List2.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void ongun(){
        if(month10 == 1 || month10 == 3 || month10 ==5 || month10 == 7 || month10 == 9 || month10 == 11 ){
            if(day10 == 31){
                day11 = 1;
                month11 = month10+1;
            }else{
                day11 = day10+1;
                month11 = month10;
            }
        }
        if(month10 == 4 || month10 == 6 || month10 == 8 || month10 == 10 || month10 == 12 ){
            if(day10 == 30){
                day11 = 1;
                month11 = month10+1;
            }else{
                day11 = day10+1;
                month11 = month10;
            }
        }
        if(month10 == 2){
            if(day10 == 30){
                day11 = 1;
                month11 = month10+1;
            }else{
                day11 = day10+1;
                month11 = month10;
            }
        }
        DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db3.orderByChild("tra").equalTo(FromPlace+ToPlace+month11+day11+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Toast.makeText(Search_6.this,FromPlace+ToPlace+month11+day11+Gender_2,Toast.LENGTH_LONG).show();
                    Search_6_model customerRequest = artistSnapshot.getValue(Search_6_model.class);
                    artistList2.add(customerRequest);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    List2.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void seqqizotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"08:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void doqquz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"09:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void doqquzotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"09:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void on(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"10:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void onotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"10:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void onbir(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"11:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void onbirotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"11:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void oniki(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"12:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void onikiotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"12:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void onuc(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"13:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void onucotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"13:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void ondord(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"14:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void ondordotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"14:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void onbes(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"15:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void onbesotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"15:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void onalti(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"16:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void onaltiotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"16:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void onyeddi(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"17:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void onyeddiotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"17:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void onseqqiz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"18:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void onseqqizotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"18:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void ondoqquz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"19:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void ondoqquzotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"19:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void iyirmi(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"20:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void iyirmiotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"20:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void iyirmizbir(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"21:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void iyirmibirotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"21:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void iyirmiiki(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"22:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void iyirmiikiotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"22:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void iyirmiuc(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"23:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void iyirmiucotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"23:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void iyirmidord(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"00:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void birinyarisi(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"00:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void bir(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"01:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void birotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"01:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void iki(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"02:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void ikiotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"02:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                }
                Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                listViewArtist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void uc(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"03:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void ucotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"03:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void dord(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"04:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void dordotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"04:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void bes(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"05:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void besotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"05:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void alti(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"06:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void altiotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"06:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void yeddi(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"07:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void yeddiotuz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"07:30"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void seqqiz(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2");
        db1.orderByChild("travel").equalTo(FromPlace+ToPlace+"08:00"+Day+Month+Year+Gender_2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Search_6_model model = dataSnapshot1.getValue(Search_6_model.class);
                    artistList2.add(model);
                    Search_6_adapter adapter = new Search_6_adapter(Search_6.this,artistList2);
                    listViewArtist.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
