package com.birget.xdrjve;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class Home extends AppCompatActivity {
    private Button Send;
    private EditText Title,Comment;
    private String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
                    nav2.getMenu().getItem(2).setChecked(true);
                    nav2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.home:
                                    Intent i =new Intent(Home.this,Qoy_9.class);
                                    startActivity(i);
                                    break;
                                case R.id.myinfo:
                                    Intent b = new Intent(Home.this, Info_2.class);
                                    startActivity(b);
                                    break;
                                case R.id.message:
                                    Intent c = new Intent(Home.this, Message_1_2.class);
                                    startActivity(c);
                                    break;
                                case R.id.customer:
                                    Intent d = new Intent(Home.this, Search_2.class);
                                    startActivity(d);
                                    break;
                                case R.id.rel1:
                                    Intent e = new Intent(Home.this, Request.class);
                                    startActivity(e);
                                    break;
                            }
                            return true;
                        }
                    });
                }else{
                    nav1.setVisibility(View.VISIBLE);
                    nav2.setVisibility(View.INVISIBLE);
                    nav1.getMenu().getItem(4).setChecked(true);
                    nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.home:
                                    Intent i =new Intent(Home.this,Activity_3.class);
                                    startActivity(i);
                                    break;
                                case R.id.contact:
                                    Intent b = new Intent(Home.this,Search_2.class);
                                    startActivity(b);
                                    break;
                                case R.id.info:
                                    Intent c = new Intent(Home.this,Qoy_1.class);
                                    startActivity(c);
                                    break;
                                case R.id.message:
                                    Intent d = new Intent(Home.this,Message_1.class);
                                    startActivity(d);
                                    break;
                                case R.id.profilim:
                                    Intent e = new Intent(Home.this,Info.class);
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
        FirebaseUser us = FirebaseAuth.getInstance().getCurrentUser();
        String uid = us.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mail = dataSnapshot.child("email").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Send = (Button)findViewById(R.id.send);
        Title = (EditText) findViewById(R.id.title1);
        Comment = (EditText)findViewById(R.id.comment);
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//whatever you want just you have to launch overhere.
                        String TitleText = Title.getText().toString();
                        String CommentText = Comment.getText().toString();
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        FirebaseUser user = auth.getCurrentUser();
                        String uid = user.getUid();
                        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Comments").child(uid);
                        db1.child("Title").setValue(TitleText);
                        db1.child("Comment").setValue(CommentText);
                        db1.child("Mail").setValue(mail);
                        DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                        db2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChild("drive")){
                                    Intent i =new Intent(Home.this, Qoy_9.class);
                                    startActivity(i);
                                    finish();
                                }else{
                                    Intent i =new Intent(Home.this, Activity_3.class);
                                    startActivity(i);
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
