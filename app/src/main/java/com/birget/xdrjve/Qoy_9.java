package com.birget.xdrjve;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.birget.xdrjve.Message_1_adapter;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Qoy_9 extends AppCompatActivity {
    private BottomNavigationView nav1;
    private LinearLayout ScaleHistory, ScaleInfo;
    private TextView Name;
    private TextView Cigar;
    private TextView Talk;
    private TextView Music;
    private String time, day, month, fromplace, toplace;
    private TextView Pet;
    private TextView CarName;
    private TextView CarNumber;
    private TextView CarYear;
    private String uid, Number;
    private CardView Info, History;
    private RelativeLayout Rel1, Rel2;
    private ListView recycleView, recycleView2;
    private TextView Text1, Text2;
    private List<Message_1_adapter> model;
    private TextView LogOut;
    private ImageView ProfilImage;
    private String Url;
    private String Gender_2;
    private Button Put;
    private Button Reserve;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String FromPlace = getIntent().getStringExtra("FromPlace");
        String ToPlace = getIntent().getStringExtra("ToPlace");
        String TyPE = getIntent().getStringExtra("Type");
        if (FromPlace == null || ToPlace == null || TyPE == null) {
            if (FromPlace == null || TyPE == null || FromPlace.equals("") || TyPE.equals("")) {
            } else {
                if (TyPE.matches("Qoy1")) {
                    Intent i = new Intent(Qoy_9.this, Qoy_2.class);
                    i.putExtra("FromPlace", FromPlace);
                    startActivity(i);
                    finish();
                }
                if (TyPE.matches("Axtar1")) {
                    Intent i = new Intent(Qoy_9.this, Search_3.class);
                    i.putExtra("FromPlace", FromPlace);
                    startActivity(i);
                    finish();
                }
            }
        } else {
            if (TyPE.matches("Axtar")) {
                Intent i = new Intent(Qoy_9.this, Search_4.class);
                i.putExtra("FromPlace", FromPlace);
                i.putExtra("ToPlace", ToPlace);
                startActivity(i);
                finish();
            }
        }
        setContentView(R.layout.activity_qoy_9);

        nav1 = (BottomNavigationView) findViewById(R.id.navigation);
        nav1.getMenu().getItem(0).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        Intent i = new Intent(Qoy_9.this, Qoy_9.class);
                        startActivity(i);
                        break;
                    case R.id.myinfo:
                        Intent b = new Intent(Qoy_9.this, Info_2.class);
                        startActivity(b);
                        break;
                    case R.id.message:
                        Intent c = new Intent(Qoy_9.this, Message_1_2.class);
                        startActivity(c);
                        break;
                    case R.id.customer:
                        Intent d = new Intent(Qoy_9.this, Search_2.class);
                        startActivity(d);
                        break;
                    case R.id.rel1:
                        Intent e = new Intent(Qoy_9.this, Request.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
        ScaleInfo = (LinearLayout) findViewById(R.id.scaleinfo);
        ScaleHistory = (LinearLayout) findViewById(R.id.scalehistory);
        Name = (TextView) findViewById(R.id.name);
        ProfilImage = (ImageView) findViewById(R.id.profilimage);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Url = dataSnapshot.child("profil").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                Name.setText(name);
                Picasso.get().load(Url).into(ProfilImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Info = (CardView) findViewById(R.id.info1);
        History = (CardView) findViewById(R.id.history);
        Text1 = (TextView) findViewById(R.id.text1);
        Text2 = (TextView) findViewById(R.id.text2);
        Rel1 = (RelativeLayout) findViewById(R.id.request);
        Rel2 = (RelativeLayout) findViewById(R.id.rel2);
        Info();
        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rel1.setVisibility(View.VISIBLE);
                Rel2.setVisibility(View.GONE);
                Info();
            }
        });
        History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                History();
                Rel1.setVisibility(View.GONE);
                Rel2.setVisibility(View.VISIBLE);
            }
        });

    }

    private void Info() {
        ScaleInfo.setVisibility(View.VISIBLE);
        ScaleHistory.setVisibility(View.GONE);
        LogOut = (TextView) findViewById(R.id.logout);
        Cigar = (TextView) findViewById(R.id.cigartext);
        Talk = (TextView) findViewById(R.id.talktext);
        Music = (TextView) findViewById(R.id.musictext);
        Pet = (TextView) findViewById(R.id.pettext);
        CarNumber = (TextView) findViewById(R.id.carNumber);
        CarYear = (TextView) findViewById(R.id.carYear);
        CarName = (TextView) findViewById(R.id.carName);
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user1 = auth.getCurrentUser();
        uid = user1.getUid();
        Number = getIntent().getStringExtra("Number");
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth1 = FirebaseAuth.getInstance();
                auth1.signOut();
                Intent i = new Intent(Qoy_9.this, Activity_2.class);
                startActivity(i);
            }
        });
        if (Number == null) {
            //Name,Surname,Date,From,To,Time,MaxYer,Gender,CarName,CarNumber,CarYear
            DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
            db1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String NameText = dataSnapshot.child("name").getValue().toString();
                    String SurnameText = dataSnapshot.child("surname").getValue().toString();
                    if (dataSnapshot.hasChild("drive") || dataSnapshot.child("drive").hasChild("carbrand") || dataSnapshot.child("drive").hasChild("carnumber") || dataSnapshot.child("drive").hasChild("caryear") || dataSnapshot.child("drive").hasChild("pet") || dataSnapshot.child("drive").hasChild("cigar") || dataSnapshot.child("drive").hasChild("music") || dataSnapshot.child("drive").hasChild("talk")) {
                        String CarNameText = dataSnapshot.child("drive").child("carbrand").getValue().toString();
                        String CarNumberText = dataSnapshot.child("drive").child("carnumber").getValue().toString();
                        String CarYearText = dataSnapshot.child("drive").child("caryear").getValue().toString();
                        String cigar = dataSnapshot.child("drive").child("cigar").getValue().toString();
                        String talk = dataSnapshot.child("drive").child("talk").getValue().toString();
                        String pet = dataSnapshot.child("drive").child("pet").getValue().toString();
                        String music = dataSnapshot.child("drive").child("music").getValue().toString();
                        CarName.setText(CarNameText);
                        CarNumber.setText(CarNumberText);
                        CarYear.setText(CarYearText);
                        Cigar.setText(cigar);
                        Talk.setText(talk);
                        Pet.setText(pet);
                        Music.setText(music);
                        SetElanDay();
                        SetElanMonth();
                        SetElanYear();
                        SetElanTime();
                        SetElanPrice();
                        SetSeat();
                        SetFromPlace();
                        SetToPlace();
                        SetGender();
                        SetRating();
                    } else {

                    }
                    return;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            if (Number.matches("0")) {
                String Talk = getIntent().getStringExtra("Talk");
                String Detal = getIntent().getStringExtra("Detal");
                String Pet = getIntent().getStringExtra("Pet");
                String Music = getIntent().getStringExtra("Music");
                String PredictTime = getIntent().getStringExtra("PredictTime");
                String Cigar = getIntent().getStringExtra("Cigar");
                String CarYear = getIntent().getStringExtra("CarYear");
                String Price = getIntent().getStringExtra("Price");
                String FromPlace = getIntent().getStringExtra("FromPlace");
                String ToPlace = getIntent().getStringExtra("ToPlace");
                String Time = getIntent().getStringExtra("Time");
                String Day = getIntent().getStringExtra("Day");
                String Year = getIntent().getStringExtra("Year");
                String Month = getIntent().getStringExtra("Month");
                String MaxYer = getIntent().getStringExtra("MaxYer");
                String CarNumber = getIntent().getStringExtra("CarNumber");
                String CarBrand = getIntent().getStringExtra("CarBrand");
                String Gender = getIntent().getStringExtra("Gender");
                if (Gender.matches("Yalnız qadın")) {
                    Gender_2 = "woman";
                }
                if (Gender.matches("Yalnız kişi")) {
                    Gender_2 = "man";
                }
                if (Gender.matches("Fərqi yoxdur")) {
                    Gender_2 = "manwoman";
                }
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                db1.child("Ratings").child("total").setValue("0.0");
                db1.child("tra").setValue(FromPlace + ToPlace + Month + Day + Gender_2);
                db1.child("drive").child("gender").setValue(Gender);
                db1.child("drive").child("talk").setValue(Talk);
                db1.child("drive").child("cigar").setValue(Cigar);
                db1.child("drive").child("music").setValue(Music);
                db1.child("drive").child("caryear").setValue(CarYear);
                db1.child("drive").child("predicttime").setValue(PredictTime);
                db1.child("predicttime").setValue(PredictTime);
                db1.child("drive").child("pet").setValue(Pet);
                db1.child("drive").child("detal").setValue(Detal);
                db1.child("drive").child("price").setValue(Price);
                db1.child("drive").child("fromplace").setValue(FromPlace);
                db1.child("drive").child("toplace").setValue(ToPlace);
                db1.child("drive").child("time").setValue(Time);
                db1.child("drive").child("month").setValue(Month);
                db1.child("drive").child("day").setValue(Day);
                db1.child("drive").child("year").setValue(Year);
                db1.child("drive").child("maxyer").setValue(MaxYer);
                db1.child("drive").child("carnumber").setValue(CarNumber);
                db1.child("drive").child("carbrand").setValue(CarBrand);
                db1.child("drive").child("uid").setValue(uid);
                FirebaseAuth auth2 = FirebaseAuth.getInstance();
                FirebaseUser user = auth2.getCurrentUser();
                String uid2 = user.getUid();
                DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid2);
                db2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child("drive").hasChild("time") || dataSnapshot.child("drive").hasChild("day") || dataSnapshot.child("drive").hasChild("month") || dataSnapshot.child("drive").hasChild("fromplace") || dataSnapshot.child("drive").hasChild("toplace")) {
                            time = dataSnapshot.child("drive").child("time").getValue().toString();
                            day = dataSnapshot.child("drive").child("day").getValue().toString();
                            month = dataSnapshot.child("drive").child("month").getValue().toString();
                            fromplace = dataSnapshot.child("drive").child("fromplace").getValue().toString();
                            toplace = dataSnapshot.child("drive").child("toplace").getValue().toString();
                            FirebaseUser auth1 = FirebaseAuth.getInstance().getCurrentUser();
                            String UID = auth1.getUid();
                            DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2").child(UID);
                            db3.child("PutOrders").child(String.valueOf(getTaskId())).child("time").setValue(time);
                            db3.child("PutOrders").child(String.valueOf(getTaskId())).child("day").setValue(day);
                            db3.child("PutOrders").child(String.valueOf(getTaskId())).child("month").setValue(month);
                            db3.child("PutOrders").child(String.valueOf(getTaskId())).child("fromplace").setValue(fromplace);
                            db3.child("PutOrders").child(String.valueOf(getTaskId())).child("toplace").setValue(toplace);
                        } else {

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                db1.child("fromplace").setValue(FromPlace);
                db1.child("toplace").setValue(ToPlace);
                db1.child("profil").setValue("https://firebasestorage.googleapis.com/v0/b/xdrive-22228.appspot.com/o/prof.png?alt=media&token=143a9f2f-85a2-4d7a-8d8d-078f2f381c37");
                db1.child("time").setValue(Time);
                db1.child("price").setValue(Price);
                db1.child("month").setValue(Month);
                db1.child("day").setValue(Day);
                db1.child("travel").setValue(FromPlace + ToPlace + Time + Day + Month + Year + Gender_2);
            }
            if (Number.matches("1")) {
                String Talk = getIntent().getStringExtra("Talk");
                String Cigar = getIntent().getStringExtra("Cigar");
                String Detal = getIntent().getStringExtra("Detal");
                String Pet = getIntent().getStringExtra("Pet");
                String Music = getIntent().getStringExtra("Music");
                String PredictTime = getIntent().getStringExtra("PredictTime");
                String CarYear = getIntent().getStringExtra("CarYear");
                String Price = getIntent().getStringExtra("Price");
                String FromPlace = getIntent().getStringExtra("FromPlace");
                String ToPlace = getIntent().getStringExtra("ToPlace");
                String Time = getIntent().getStringExtra("Time");
                String Day = getIntent().getStringExtra("Day");
                String Year = getIntent().getStringExtra("Year");
                String Month = getIntent().getStringExtra("Month");
                String MaxYer = getIntent().getStringExtra("MaxYer");
                String CarNumber = getIntent().getStringExtra("CarNumber");
                String CarBrand = getIntent().getStringExtra("CarBrand");
                String Place1 = getIntent().getStringExtra("Place1");
                String Gender = getIntent().getStringExtra("Gender");
                if (Gender.matches("Yalnız qadın")) {
                    Gender_2 = "woman";
                }
                if (Gender.matches("Yalnız kişi")) {
                    Gender_2 = "man";
                }
                if (Gender.matches("Fərqi yoxdur")) {
                    Gender_2 = "manwoman";
                }
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                db1.child("Ratings").child("total").setValue("0.0");
                db1.child("drive").child("gender").setValue(Gender);
                db1.child("drive").child("talk").setValue(Talk);
                db1.child("drive").child("cigar").setValue(Cigar);
                db1.child("drive").child("music").setValue(Music);
                db1.child("drive").child("predicttime").setValue(PredictTime);
                db1.child("drive").child("pet").setValue(Pet);
                db1.child("drive").child("detal").setValue(Detal);
                db1.child("drive").child("caryear").setValue(CarYear);
                db1.child("drive").child("price").setValue(Price);
                db1.child("drive").child("fromplace").setValue(FromPlace);
                db1.child("drive").child("toplace").setValue(ToPlace);
                db1.child("drive").child("time").setValue(Time);
                db1.child("drive").child("month").setValue(Month);
                db1.child("drive").child("day").setValue(Day);
                db1.child("drive").child("year").setValue(Year);
                db1.child("drive").child("maxyer").setValue(MaxYer);
                db1.child("drive").child("carnumber").setValue(CarNumber);
                db1.child("drive").child("carbrand").setValue(CarBrand);
                db1.child("drive").child("place1").setValue(Place1);
                db1.child("drive").child("uid").setValue(uid);
                db1.child("fromplace").setValue(FromPlace);
                db1.child("toplace").setValue(ToPlace);
                db1.child("profil").setValue("https://firebasestorage.googleapis.com/v0/b/xdrive-22228.appspot.com/o/prof.png?alt=media&token=143a9f2f-85a2-4d7a-8d8d-078f2f381c37");
                db1.child("time").setValue(Time);
                db1.child("price").setValue(Price);
                db1.child("travel").setValue(FromPlace + ToPlace + Time + Day + Month + Year + Gender_2);
            }
            if (Number.matches("2")) {
                String Talk = getIntent().getStringExtra("Talk");
                String Cigar = getIntent().getStringExtra("Cigar");
                String Detal = getIntent().getStringExtra("Detal");
                String Music = getIntent().getStringExtra("Music");
                String Pet = getIntent().getStringExtra("Pet");
                String PredictTime = getIntent().getStringExtra("PredictTime");
                String CarYear = getIntent().getStringExtra("CarYear");
                String Price = getIntent().getStringExtra("Price");
                String FromPlace = getIntent().getStringExtra("FromPlace");
                String ToPlace = getIntent().getStringExtra("ToPlace");
                String Time = getIntent().getStringExtra("Time");
                String Day = getIntent().getStringExtra("Day");
                String Year = getIntent().getStringExtra("Year");
                String Month = getIntent().getStringExtra("Month");
                String MaxYer = getIntent().getStringExtra("MaxYer");
                String CarNumber = getIntent().getStringExtra("CarNumber");
                String CarBrand = getIntent().getStringExtra("CarBrand");
                String Place1 = getIntent().getStringExtra("Place1");
                String Place2 = getIntent().getStringExtra("Place2");
                String Gender = getIntent().getStringExtra("Gender");
                if (Gender.matches("Yalnız qadın")) {
                    Gender_2 = "woman";
                }
                if (Gender.matches("Yalnız kişi")) {
                    Gender_2 = "man";
                }
                if (Gender.matches("Fərqi yoxdur")) {
                    Gender_2 = "manwoman";
                }
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                db1.child("Ratings").child("total").setValue("0.0");
                db1.child("drive").child("gender").setValue(Gender);
                db1.child("drive").child("talk").setValue(Talk);
                db1.child("drive").child("predicttime").setValue(PredictTime);
                db1.child("drive").child("pet").setValue(Pet);
                db1.child("drive").child("music").setValue(Music);
                db1.child("drive").child("detal").setValue(Detal);
                db1.child("drive").child("cigar").setValue(Cigar);
                db1.child("drive").child("caryear").setValue(CarYear);
                db1.child("drive").child("price").setValue(Price);
                db1.child("drive").child("fromplace").setValue(FromPlace);
                db1.child("drive").child("toplace").setValue(ToPlace);
                db1.child("drive").child("time").setValue(Time);
                db1.child("drive").child("month").setValue(Month);
                db1.child("drive").child("day").setValue(Day);
                db1.child("drive").child("year").setValue(Year);
                db1.child("drive").child("maxyer").setValue(MaxYer);
                db1.child("drive").child("carnumber").setValue(CarNumber);
                db1.child("drive").child("carbrand").setValue(CarBrand);
                db1.child("drive").child("place1").setValue(Place1);
                db1.child("drive").child("place2").setValue(Place2);
                db1.child("drive").child("uid").setValue(uid);
                db1.child("fromplace").setValue(FromPlace);
                db1.child("toplace").setValue(ToPlace);
                db1.child("profil").setValue("https://firebasestorage.googleapis.com/v0/b/xdrive-22228.appspot.com/o/prof.png?alt=media&token=143a9f2f-85a2-4d7a-8d8d-078f2f381c37");
                db1.child("time").setValue(Time);
                db1.child("price").setValue(Price);
                db1.child("travel").setValue(FromPlace + ToPlace + Time + Day + Month + Year + Gender_2);
            }
            if (Number.matches("3")) {
                String Talk = getIntent().getStringExtra("Talk");
                String Cigar = getIntent().getStringExtra("Cigar");
                String CarYear = getIntent().getStringExtra("CarYear");
                String Detal = getIntent().getStringExtra("Detal");
                String Music = getIntent().getStringExtra("Music");
                String Pet = getIntent().getStringExtra("Pet");
                String PredictTime = getIntent().getStringExtra("PredictTime");
                String Price = getIntent().getStringExtra("Price");
                String FromPlace = getIntent().getStringExtra("FromPlace");
                String ToPlace = getIntent().getStringExtra("ToPlace");
                String Time = getIntent().getStringExtra("Time");
                String Day = getIntent().getStringExtra("Day");
                String Year = getIntent().getStringExtra("Year");
                String Month = getIntent().getStringExtra("Month");
                String MaxYer = getIntent().getStringExtra("MaxYer");
                String CarNumber = getIntent().getStringExtra("CarNumber");
                String CarBrand = getIntent().getStringExtra("CarBrand");
                String Place1 = getIntent().getStringExtra("Place1");
                String Place2 = getIntent().getStringExtra("Place2");
                String Place3 = getIntent().getStringExtra("Place3");
                String Gender = getIntent().getStringExtra("Gender");
                if (Gender.matches("Yalnız qadın")) {
                    Gender_2 = "woman";
                }
                if (Gender.matches("Yalnız kişi")) {
                    Gender_2 = "man";
                }
                if (Gender.matches("Fərqi yoxdur")) {
                    Gender_2 = "manwoman";
                }
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                db1.child("Ratings").child("total").setValue("0.0");
                db1.child("drive").child("gender").setValue(Gender);
                db1.child("drive").child("talk").setValue(Talk);
                db1.child("drive").child("cigar").setValue(Cigar);
                db1.child("drive").child("predicttime").setValue(PredictTime);
                db1.child("drive").child("pet").setValue(Pet);
                db1.child("drive").child("music").setValue(Music);
                db1.child("drive").child("detal").setValue(Detal);
                db1.child("drive").child("caryear").setValue(CarYear);
                db1.child("drive").child("price").setValue(Price);
                db1.child("drive").child("fromplace").setValue(FromPlace);
                db1.child("drive").child("toplace").setValue(ToPlace);
                db1.child("drive").child("time").setValue(Time);
                db1.child("drive").child("month").setValue(Month);
                db1.child("drive").child("day").setValue(Day);
                db1.child("drive").child("year").setValue(Year);
                db1.child("drive").child("maxyer").setValue(MaxYer);
                db1.child("drive").child("carnumber").setValue(CarNumber);
                db1.child("drive").child("carbrand").setValue(CarBrand);
                db1.child("drive").child("place1").setValue(Place1);
                db1.child("drive").child("place2").setValue(Place2);
                db1.child("drive").child("place3").setValue(Place3);
                db1.child("drive").child("uid").setValue(uid);
                db1.child("fromplace").setValue(FromPlace);
                db1.child("toplace").setValue(ToPlace);
                db1.child("time").setValue(Time);
                db1.child("profil").setValue("https://firebasestorage.googleapis.com/v0/b/xdrive-22228.appspot.com/o/prof.png?alt=media&token=143a9f2f-85a2-4d7a-8d8d-078f2f381c37");
                db1.child("price").setValue(Price);
                db1.child("travel").setValue(FromPlace + ToPlace + Time + Day + Month + Year + Gender_2);
            }
            if (Number.matches("4")) {
                String Talk = getIntent().getStringExtra("Talk");
                String Cigar = getIntent().getStringExtra("Cigar");
                String CarYear = getIntent().getStringExtra("CarYear");
                String Price = getIntent().getStringExtra("Price");
                String Detal = getIntent().getStringExtra("Detal");
                String Music = getIntent().getStringExtra("Music");
                String Pet = getIntent().getStringExtra("Pet");
                String PredictTime = getIntent().getStringExtra("PredictTime");
                String FromPlace = getIntent().getStringExtra("FromPlace");
                String ToPlace = getIntent().getStringExtra("ToPlace");
                String Time = getIntent().getStringExtra("Time");
                String Day = getIntent().getStringExtra("Day");
                String Year = getIntent().getStringExtra("Year");
                String Month = getIntent().getStringExtra("Month");
                String MaxYer = getIntent().getStringExtra("MaxYer");
                String CarNumber = getIntent().getStringExtra("CarNumber");
                String CarBrand = getIntent().getStringExtra("CarBrand");
                String Place1 = getIntent().getStringExtra("Place1");
                String Place2 = getIntent().getStringExtra("Place2");
                String Place3 = getIntent().getStringExtra("Place3");
                String Place4 = getIntent().getStringExtra("Place4");
                String Gender = getIntent().getStringExtra("Gender");
                if (Gender.matches("Yalnız qadın")) {
                    Gender_2 = "woman";
                }
                if (Gender.matches("Yalnız kişi")) {
                    Gender_2 = "man";
                }
                if (Gender.matches("Fərqi yoxdur")) {
                    Gender_2 = "manwoman";
                }
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                db1.child("Ratings").child("total").setValue("0.0");
                db1.child("drive").child("gender").setValue(Gender);
                db1.child("drive").child("talk").setValue(Talk);
                db1.child("drive").child("predicttime").setValue(PredictTime);
                db1.child("drive").child("pet").setValue(Pet);
                db1.child("drive").child("music").setValue(Music);
                db1.child("drive").child("detal").setValue(Detal);
                db1.child("drive").child("cigar").setValue(Cigar);
                db1.child("drive").child("caryear").setValue(CarYear);
                db1.child("drive").child("price").setValue(Price);
                db1.child("drive").child("fromplace").setValue(FromPlace);
                db1.child("drive").child("toplace").setValue(ToPlace);
                db1.child("drive").child("time").setValue(Time);
                db1.child("drive").child("month").setValue(Month);
                db1.child("drive").child("day").setValue(Day);
                db1.child("drive").child("year").setValue(Year);
                db1.child("drive").child("maxyer").setValue(MaxYer);
                db1.child("drive").child("carnumber").setValue(CarNumber);
                db1.child("drive").child("carbrand").setValue(CarBrand);
                db1.child("drive").child("place1").setValue(Place1);
                db1.child("drive").child("place2").setValue(Place2);
                db1.child("drive").child("place3").setValue(Place3);
                db1.child("drive").child("place4").setValue(Place4);
                db1.child("drive").child("uid").setValue(uid);
                db1.child("fromplace").setValue(FromPlace);
                db1.child("toplace").setValue(ToPlace);
                db1.child("profil").setValue("https://firebasestorage.googleapis.com/v0/b/xdrive-22228.appspot.com/o/prof.png?alt=media&token=143a9f2f-85a2-4d7a-8d8d-078f2f381c37");
                db1.child("time").setValue(Time);
                db1.child("price").setValue(Price);
                db1.child("travel").setValue(FromPlace + ToPlace + Time + Day + Month + Year + Gender_2);
            }
            if (Number.matches("5")) {
                String Talk = getIntent().getStringExtra("Talk");
                String Cigar = getIntent().getStringExtra("Cigar");
                String CarYear = getIntent().getStringExtra("CarYear");
                String Price = getIntent().getStringExtra("Price");
                String Detal = getIntent().getStringExtra("Detal");
                String Music = getIntent().getStringExtra("Music");
                String Pet = getIntent().getStringExtra("Pet");
                String PredictTime = getIntent().getStringExtra("PredictTime");
                String FromPlace = getIntent().getStringExtra("FromPlace");
                String ToPlace = getIntent().getStringExtra("ToPlace");
                String Time = getIntent().getStringExtra("Time");
                String Day = getIntent().getStringExtra("Day");
                String Year = getIntent().getStringExtra("Year");
                String Month = getIntent().getStringExtra("Month");
                String MaxYer = getIntent().getStringExtra("MaxYer");
                String CarNumber = getIntent().getStringExtra("CarNumber");
                String CarBrand = getIntent().getStringExtra("CarBrand");
                String Place1 = getIntent().getStringExtra("Place1");
                String Place2 = getIntent().getStringExtra("Place2");
                String Place3 = getIntent().getStringExtra("Place3");
                String Place4 = getIntent().getStringExtra("Place4");
                String Place5 = getIntent().getStringExtra("Place5");
                String Gender = getIntent().getStringExtra("Gender");
                if (Gender.matches("Yalnız qadın")) {
                    Gender_2 = "woman";
                }
                if (Gender.matches("Yalnız kişi")) {
                    Gender_2 = "man";
                }
                if (Gender.matches("Fərqi yoxdur")) {
                    Gender_2 = "manwoman";
                }
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                db1.child("Ratings").child("total").setValue("0.0");
                db1.child("drive").child("gender").setValue(Gender);
                db1.child("drive").child("talk").setValue(Talk);
                db1.child("drive").child("cigar").setValue(Cigar);
                db1.child("drive").child("caryear").setValue(CarYear);
                db1.child("drive").child("price").setValue(Price);
                db1.child("drive").child("predicttime").setValue(PredictTime);
                db1.child("drive").child("pet").setValue(Pet);
                db1.child("drive").child("music").setValue(Music);
                db1.child("drive").child("detal").setValue(Detal);
                db1.child("drive").child("fromplace").setValue(FromPlace);
                db1.child("drive").child("toplace").setValue(ToPlace);
                db1.child("drive").child("time").setValue(Time);
                db1.child("drive").child("month").setValue(Month);
                db1.child("drive").child("day").setValue(Day);
                db1.child("drive").child("year").setValue(Year);
                db1.child("drive").child("maxyer").setValue(MaxYer);
                db1.child("drive").child("carnumber").setValue(CarNumber);
                db1.child("drive").child("carbrand").setValue(CarBrand);
                db1.child("drive").child("place1").setValue(Place1);
                db1.child("drive").child("place2").setValue(Place2);
                db1.child("drive").child("place3").setValue(Place3);
                db1.child("drive").child("place4").setValue(Place4);
                db1.child("drive").child("place5").setValue(Place5);
                db1.child("drive").child("uid").setValue(uid);
                db1.child("fromplace").setValue(FromPlace);
                db1.child("profil").setValue("https://firebasestorage.googleapis.com/v0/b/xdrive-22228.appspot.com/o/prof.png?alt=media&token=143a9f2f-85a2-4d7a-8d8d-078f2f381c37");
                db1.child("toplace").setValue(ToPlace);
                db1.child("time").setValue(Time);
                db1.child("price").setValue(Price);
                db1.child("travel").setValue(FromPlace + ToPlace + Time + Day + Month + Year + Gender_2);
            }
        }
    }

    private void History() {
        ScaleHistory.setVisibility(View.VISIBLE);
        ScaleInfo.setVisibility(View.GONE);
        Put = (Button) findViewById(R.id.put);
        Reserve = (Button) findViewById(R.id.rezerv);
        Put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Qoy_9.this, ElanQoy.class);
                startActivity(i);
            }
        });
        Reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Qoy_9.this, RezervElan.class);
                startActivity(i);
            }
        });
    }

    private void SetElanYear() {
        final TextView ElanYear = (TextView) findViewById(R.id.elanyear);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String MyUid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ElanYearString;
                String FinalElanYear;
                if (dataSnapshot.child("drive").hasChild("year")) {
                    ElanYearString = dataSnapshot.child("drive").child("year").getValue().toString();
                    FinalElanYear = "Elanın ili:" + ElanYearString;
                    ElanYear.setText(FinalElanYear);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SetElanMonth() {
        final TextView ElanMonth = (TextView) findViewById(R.id.elanmonth);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String MyUid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ElanYearString;
                String FinalElanYear;
                if (dataSnapshot.child("drive").hasChild("month")) {
                    ElanYearString = dataSnapshot.child("drive").child("month").getValue().toString();
                    FinalElanYear = "Elanın ayı:" + ElanYearString;
                    ElanMonth.setText(FinalElanYear);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SetElanDay() {
        final TextView ElanYear = (TextView) findViewById(R.id.elanday);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String MyUid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ElanYearString;
                String FinalElanYear;
                if (dataSnapshot.child("drive").hasChild("day")) {
                    ElanYearString = dataSnapshot.child("drive").child("day").getValue().toString();
                    FinalElanYear = "Elanın günü:" + ElanYearString;
                    ElanYear.setText(FinalElanYear);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SetElanTime() {
        final TextView ElanYear = (TextView) findViewById(R.id.elantime);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String MyUid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ElanYearString;
                String FinalElanYear;
                if (dataSnapshot.child("drive").hasChild("time")) {
                    ElanYearString = dataSnapshot.child("drive").child("time").getValue().toString();
                    FinalElanYear = "Elanın vaxtı:" + ElanYearString;
                    ElanYear.setText(FinalElanYear);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SetElanPrice() {
        final TextView ElanYear = (TextView) findViewById(R.id.price);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String MyUid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ElanYearString;
                String FinalElanYear;
                if (dataSnapshot.child("drive").hasChild("price")) {
                    ElanYearString = dataSnapshot.child("drive").child("price").getValue().toString();
                    FinalElanYear = "Elanın qiyməti:" + ElanYearString;
                    ElanYear.setText(FinalElanYear);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SetFromPlace() {
        final TextView ElanYear = (TextView) findViewById(R.id.fromplace);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String MyUid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ElanYearString;
                String FinalElanYear;
                if (dataSnapshot.hasChild("fromplace")) {
                    ElanYearString = dataSnapshot.child("fromplace").getValue().toString();
                    FinalElanYear = "Çıxacağınız məntəqə:" + ElanYearString;
                    ElanYear.setText(FinalElanYear);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SetToPlace() {
        final TextView ElanYear = (TextView) findViewById(R.id.toplace);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String MyUid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ElanYearString;
                String FinalElanYear;
                if (dataSnapshot.hasChild("toplace")) {
                    ElanYearString = dataSnapshot.child("toplace").getValue().toString();
                    FinalElanYear = "Çatacağınız məntəqə:" + ElanYearString;
                    ElanYear.setText(FinalElanYear);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SetGender() {
        final TextView ElanYear = (TextView) findViewById(R.id.gender);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String MyUid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ElanYearString;
                String FinalElanYear;
                if (dataSnapshot.child("drive").hasChild("gender")) {
                    ElanYearString = dataSnapshot.child("drive").child("gender").getValue().toString();
                    FinalElanYear = "Cins tələbiniz:" + ElanYearString;
                    ElanYear.setText(FinalElanYear);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SetRating() {
        final TextView ElanYear = (TextView) findViewById(R.id.rating);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String MyUid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ElanYearString;
                String FinalElanYear;
                ElanYearString = dataSnapshot.child("Ratings").child("total").getValue().toString();
                FinalElanYear = "Portalda dəyərləndirmə balınız:" + ElanYearString;
                ElanYear.setText(FinalElanYear);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SetSeat() {
        final TextView Seat = (TextView) findViewById(R.id.seat);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String Uid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("maxyer")) {
                    String fromplace = dataSnapshot.child("maxyer").getValue().toString();
                    final int maxYerint = Integer.valueOf(fromplace);
                    DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid);
                    db1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int nowyerint = (int) dataSnapshot.child("truecustomer").getChildrenCount();
                            if (dataSnapshot.hasChild("truecustomer")) {
                                if (maxYerint - nowyerint == 0) {
                                    Seat.setText("Elanda yer sayı:0");
                                } else {
                                    int a = maxYerint - nowyerint;
                                    String ab = "Elanda yer sayı:" + String.valueOf(a);
                                    Seat.setText(ab);
                                }
                            } else {
                                Seat.setText("Elanda yer sayı:" + String.valueOf(maxYerint));
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onBackPressed() {
        finishAffinity();
    }
}
