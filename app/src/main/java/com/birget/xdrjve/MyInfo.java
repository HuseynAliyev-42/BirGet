package com.birget.xdrjve;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyInfo extends AppCompatActivity {
    private TextView Talk;
    private TextView Cigar;
    private TextView Mail;
    private TextView PhoneNumber;
    private TextView Place1;
    private TextView Place2;
    private TextView Place3;
    private TextView Place4;
    private TextView Place5;
    private BottomNavigationView nav1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent i =new Intent(MyInfo.this, Qoy_9.class);
                        startActivity(i);
                        break;
                    case R.id.myinfo:
                        Intent b = new Intent(MyInfo.this,MyInfo.class);
                        startActivity(b);
                        break;
                    case R.id.masaj:
                        Intent c = new Intent(MyInfo.this, Message_1.class);
                        startActivity(c);
                        break;
                    case R.id.customer:
                        Intent d = new Intent(MyInfo.this, Customer.class);
                        startActivity(d);
                        break;
                    case R.id.rel1:
                        Intent e = new Intent(MyInfo.this, Request.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
        Talk = (TextView)findViewById(R.id.talk);
        Cigar = (TextView)findViewById(R.id.cigar);
        Mail = (TextView)findViewById(R.id.mail);
        PhoneNumber = (TextView)findViewById(R.id.phoneNumber);
        Place1 = (TextView)findViewById(R.id.place1);
        Place2 = (TextView)findViewById(R.id.place2);
        Place3 = (TextView)findViewById(R.id.place3);
        Place4 = (TextView)findViewById(R.id.place4);
        Place5 = (TextView)findViewById(R.id.place5);
        Talk();
        Cigar();
        Mail();
        PhoneNumber();
        Place1();
        Place2();
        Place3();
        Place4();
        Place5();
    }
    private void Talk(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               String TalkText = dataSnapshot.child("talk").getValue().toString();
               if(TalkText.matches("Bəli,danışmağı sevirəm.")){
                   Talk.setText("Danışmadan olmaz!");
               }
               if (TalkText.matches("Yox, danisqan deyiləm.")){
                   Talk.setText("Danışqan deyiləm!");
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void Cigar(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String CigarText = dataSnapshot.child("cigar").getValue().toString();
                if (CigarText.matches("Xeyr, icazə vermərəm.")){
                    Cigar.setText("Maşınımda siqaret çəkmək olar!");
                }
                if(CigarText.matches("Bəli, icazə verərəm.")){
                    Cigar.setText("Maşınımda siqaret çəkmək olmaz!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void Mail(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String MailText = dataSnapshot.child("email").getValue().toString();
                Mail.setText(MailText);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void PhoneNumber(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();
        DatabaseReference db1  = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String PhoneText = dataSnapshot.child("phone").getValue().toString();
                String Phone2 = "(+994)"+PhoneText;
                PhoneNumber.setText(Phone2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void Place1(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("place1")){
                    String place1 = dataSnapshot.child("place1").getValue().toString();
                    Place1.setText(place1);
                }
                else {
                    Place1.setText("Dayanacaq yoxdur!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void Place2(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("place2")){
                    String Place2Text = dataSnapshot.child("place2").getValue().toString();
                    Place2.setText(Place2Text);
                }else{
                    Place2.setText("Dayanacaq yoxdur!");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void Place3(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("place3")){
                    String Place3Text = dataSnapshot.child("place3").getValue().toString();
                    Place3.setText(Place3Text);
                }else {
                    Place3.setText("Dayanacaq yoxdur!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void Place4(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("place4")){
                    String Place4Text = dataSnapshot.child("place4").getValue().toString();
                    Place4.setText(Place4Text);
                }else{
                    Place4.setText("Dayanacaq yoxdur!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void Place5(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("drive");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("place5")){
                    String Place5Text = dataSnapshot.child("place5").getValue().toString();
                    Place5.setText(Place5Text);
                }
                else {
                    Place5.setText("Dayanacaq yoxdur!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
