package com.birget.xdrjve;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.List;

public class ScoreShow extends AppCompatActivity {
    private ListView Rec1;
    private TextView Text1;
    private List<ScoreShow_model> model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_show);
        final BottomNavigationView nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        final BottomNavigationView nav2 = (BottomNavigationView)findViewById(R.id.navigation2);
        FirebaseUser user10 = FirebaseAuth.getInstance().getCurrentUser();
        String MyUid = user10.getUid();
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
                                    Intent i =new Intent(ScoreShow.this,Qoy_9.class);
                                    startActivity(i);
                                    break;
                                case R.id.myinfo:
                                    Intent b = new Intent(ScoreShow.this, Info_2.class);
                                    startActivity(b);
                                    break;
                                case R.id.message:
                                    Intent c = new Intent(ScoreShow.this, Message_1_2.class);
                                    startActivity(c);
                                    break;
                                case R.id.customer:
                                    Intent d = new Intent(ScoreShow.this, Search_2.class);
                                    startActivity(d);
                                    break;
                                case R.id.rel1:
                                    Intent e = new Intent(ScoreShow.this, Request.class);
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
                                    Intent i =new Intent(ScoreShow.this,Activity_3.class);
                                    startActivity(i);
                                    break;
                                case R.id.contact:
                                    Intent b = new Intent(ScoreShow.this,Search_2.class);
                                    startActivity(b);
                                    break;
                                case R.id.info:
                                    Intent c = new Intent(ScoreShow.this,Qoy_1.class);
                                    startActivity(c);
                                    break;
                                case R.id.message:
                                    Intent d = new Intent(ScoreShow.this,Message_1.class);
                                    startActivity(d);
                                    break;
                                case R.id.profilim:
                                    Intent e = new Intent(ScoreShow.this,Info.class);
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
        Rec1 =(ListView)findViewById(R.id.rec1);
        Text1 = (TextView)findViewById(R.id.text);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        final String uid = user.getUid();
        model = new ArrayList<>();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("SendRatings")){
                    Rec1.setVisibility(View.VISIBLE);
                    Text1.setVisibility(View.GONE);
                    DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("SendRatings");
                    db2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            model.clear();
                            for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                                ScoreShow_model Model = snapshot.getValue(ScoreShow_model.class);
                                model.add(Model);
                            }
                            ScoreShow_adapter adap = new ScoreShow_adapter(ScoreShow.this,model);
                            Rec1.setAdapter(adap);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }else{
                    Rec1.setVisibility(View.GONE);
                    Text1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
