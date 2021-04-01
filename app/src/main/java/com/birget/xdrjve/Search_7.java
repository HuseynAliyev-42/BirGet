package com.birget.xdrjve;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Search_7 extends AppCompatActivity {
    String Uid;
    TextView Date;
    ListView List1;
    String c;
    TextView FromPlace;
    TextView ToPlace;
    TextView Time;
    private AdView mAdView;
    TextView PredictTime;
    TextView Seat;
    TextView Price;
    TextView Name;
    TextView Rating;
    TextView Detal;
    TextView CarName;
    TextView CarNumber;
    TextView CarYear;
    TextView Music;
    TextView Pet;
    TextView Talk;
    String phone,name;
    private ListView listViewArtist;
    TextView Cigar;
    TextView RatingScore;
    private TextView Telephone;
    private List<ShowShare> artistList;
    Button Submit;
    private  TextView SendMessage;
    private int a;
    private TextView ListCondition;
    private int b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_7);
        ListCondition = (TextView)findViewById(R.id.list1condition);
        Uid = getIntent().getStringExtra("uid");
        if(Uid.matches("")){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid =user.getUid();
            DatabaseReference d1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
            d1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild("drive")){
                        Intent i = new Intent(Search_7.this, Qoy_9.class);
                        startActivity(i);
                        finish();
                    }else{
                        Intent i = new Intent(Search_7.this, Activity_3.class);
                        startActivity(i);
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            return;
        }
        if(Uid == null){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid =user.getUid();
            DatabaseReference d1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
            d1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild("drive")){
                        Intent i = new Intent(Search_7.this,Qoy_9.class);
                        startActivity(i);
                        finish();
                    }else{
                        Intent i = new Intent(Search_7.this,Activity_3.class);
                        startActivity(i);
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            return;
        }
        SendMessage = (TextView)findViewById(R.id.sendmessage);
        Date = (TextView)findViewById(R.id.date);
        FromPlace = (TextView)findViewById(R.id.fromplace);
        ToPlace = (TextView)findViewById(R.id.toplace);
        Time = (TextView)findViewById(R.id.time);
        PredictTime = (TextView)findViewById(R.id.predicttime);
        Seat = (TextView)findViewById(R.id.seat);
        Price = (TextView)findViewById(R.id.price);
        Name = (TextView)findViewById(R.id.name);
        Rating = (TextView)findViewById(R.id.rating);
        Detal = (TextView)findViewById(R.id.detal);
        CarName = (TextView)findViewById(R.id.carName);
        CarNumber = (TextView)findViewById(R.id.carNumber);
        CarYear = (TextView)findViewById(R.id.carYear);
        Cigar = (TextView)findViewById(R.id.cigartext);
        Talk = (TextView)findViewById(R.id.talktext);
        Music = (TextView)findViewById(R.id.musictext);
        Pet = (TextView)findViewById(R.id.pettext);
        List1 = (ListView)findViewById(R.id.list1);
        RatingScore = (TextView)findViewById(R.id.ratingscore);
        Submit = (Button)findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//whatever you want just you have to launch overhere.
//whatever you want just you have to launch overhere.
                       Intent i =new Intent(Search_7.this, Search_9_2.class);
                       i.putExtra("uid",Uid);
                       startActivity(i);
                       finish();
                       // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                       // String uid =user.getUid();
                       // DatabaseReference d1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                       // d1.addValueEventListener(new ValueEventListener() {
                       //     @Override
                       //     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       //         if(dataSnapshot.hasChild("drive")){
                       //             Intent i = new Intent(Search_7.this,Qoy_9.class);
                       //             startActivity(i);
                       //             finish();
                       //         }else{
                       //             Intent i = new Intent(Search_7.this,Activity_3.class);
                       //             startActivity(i);
                       //             finish();
                       //         }
                       //     }

                       //     @Override
                       //     public void onCancelled(@NonNull DatabaseError databaseError) {

                       //     }
                       // });
            }
        });
        SendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Search_7.this, Message_2.class);
                i.putExtra("uid",Uid);
                startActivity(i);
            }
        });
        MobileAds.initialize(this, "ca-app-pub-4810068176185955~3577363686");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        //
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //
        setTelephone();
        setDate();
        setFromPlace();
        setToPlace();
        setTime();
        setPredictTime();
        setSeat();
        setPrice();
        setName();
        setDetal();
        setCarName();
        setCarNumber();
        setCarYear();
        setCigar();
        setTalk();
        setMusic();
        setPet();
        setCustomer();
        setRating();
        if(b==5) {
            int a = List1.getCount();
            if (a == 0) {
                List1.setVisibility(View.INVISIBLE);
                ListCondition.setVisibility(View.VISIBLE);
            }
        }
    }
    private void setTelephone(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String telephonetext = dataSnapshot.child("phone").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setDate(){
        final DatabaseReference data1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid);
        data1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String a = dataSnapshot.child("drive").child("day").getValue().toString();
                String b = dataSnapshot.child("drive").child("month").getValue().toString();
                if(b.matches("1")){
                     c = "Yanvar";
                }
                if(b.matches("2")){
                     c = "Fevral";
                }
                if(b.matches("3")){
                     c = "Mart";
                }
                if(b.matches("4")){
                    c = "Aprel";
                }
                if(b.matches("5")){
                    c = "May";
                }
                if(b.matches("6")){
                    c = "Iyun";
                }
                if(b.matches("7")){
                    c = "Iyul";
                }
                if(b.matches("8")){
                    c = "Avqust";
                }
                if(b.matches("9")){
                    c= "Sentyabr";
                }
                if(b.matches("10")){
                    c = "Oktyabr";
                }
                if(b.matches("11")){
                    c = "Noyabr";
                }
                if(b.matches("12")){
                    c = "Dekabr";
                }
                String d = a +""+c;
                Date.setText(d);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setFromPlace(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                  if(dataSnapshot.hasChild("fromplace")){
                        String fromplace = dataSnapshot.child("fromplace").getValue().toString();
                        FromPlace.setText(fromplace);
                    }
                }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setToPlace(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("toplace")){
                    String toplace = dataSnapshot.child("toplace").getValue().toString();
                    ToPlace.setText(toplace);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setTime(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("time")){
                    String fromplace = dataSnapshot.child("time").getValue().toString();
                    Time.setText(fromplace);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //firebasestorage.googleapis.com/v0/b/xdrive-22228.appspot.com/o/prof.png?alt=media&token=143a9f2f-85a2-4d7a-8d8d-078f2f381c37
    private void setPredictTime(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("predicttime")){
                    String fromplace = dataSnapshot.child("predicttime").getValue().toString();
                    PredictTime.setText(fromplace);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setRating(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("Ratings");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String RatingText = dataSnapshot.child("total").getValue().toString();
                Rating.setText(RatingText);
                if(dataSnapshot.hasChild("users")){
                    int RatingScoreText = (int)dataSnapshot.child("users").getChildrenCount();
                    RatingScore.setText(String.valueOf(RatingScoreText));
                }else{
                    RatingScore.setText("0");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setSeat(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fromplace = dataSnapshot.child("maxyer").getValue().toString();
                final int maxYerint = Integer.valueOf(fromplace);
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid);
                db1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       int nowyerint = (int)dataSnapshot.child("truecustomer").getChildrenCount();
                       if (dataSnapshot.hasChild("truecustomer")){
                           if(maxYerint - nowyerint==0){
                               Seat.setText("0");
                           }else {
                               int a = maxYerint - nowyerint;
                               String ab = String.valueOf(a);
                               Seat.setText(ab);
                           }
                       }else {
                           Seat.setText(String.valueOf(maxYerint));
                       }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setPrice(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("price")){
                    String fromplace = dataSnapshot.child("price").getValue().toString();
                    Price.setText(fromplace);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setName(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("name")){
                    String fromplace = dataSnapshot.child("name").getValue().toString();
                    Name.setText(fromplace);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setDetal(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("detal")){
                    String fromplace = dataSnapshot.child("detal").getValue().toString();
                    Detal.setText(fromplace);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setCarName(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("carbrand")){
                    String fromplace = dataSnapshot.child("carbrand").getValue().toString();
                    CarName.setText(fromplace);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setCarNumber(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("carnumber")){
                    String fromplace = dataSnapshot.child("carnumber").getValue().toString();
                    CarNumber.setText(fromplace);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setCarYear(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("caryear")){
                    String fromplace = dataSnapshot.child("caryear").getValue().toString();
                    CarYear.setText(fromplace);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setCigar(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("cigar")) {
                    String fromplace = dataSnapshot.child("cigar").getValue().toString();
                    Cigar.setText(fromplace);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setTalk(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("talk")) {
                    String fromplace = dataSnapshot.child("talk").getValue().toString();
                    Talk.setText(fromplace);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setMusic(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("music")) {
                    String fromplace = dataSnapshot.child("music").getValue().toString();
                    Music.setText(fromplace);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setPet(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("pet")) {
                    String fromplace = dataSnapshot.child("pet").getValue().toString();
                    Pet.setText(fromplace);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void setCustomer(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(Uid).child("truecustomer");
        artistList = new ArrayList<>();
        listViewArtist = (ListView)findViewById(R.id.list1);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                artistList.clear();
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    ShowShare customerRequest = artistSnapshot.getValue(ShowShare.class);
                    artistList.add(customerRequest);
                }
                Adapter adapter = new Adapter(Search_7.this,artistList);
                listViewArtist.setAdapter(adapter);
                b=5;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
