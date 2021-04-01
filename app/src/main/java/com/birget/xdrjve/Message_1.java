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

import com.birget.xdrjve.R;
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

public class Message_1 extends AppCompatActivity {
    private BottomNavigationView nav1;
    private ListView recyclerView;
    private List<com.birget.xdrjve.Message_1_adapter> model;
    private Button Message;
    private Button Notifications;
    private CardView Card1,Card2;
    private ImageView image1,image2;
    private TextView title,title2,detal1,detal2;
    private RelativeLayout rel1;
    private LinearLayout ScaleMessage,ScaleNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_1);
        final ProgressDialog csprogress=new ProgressDialog(Message_1.this);
        csprogress.setMessage("Yüklənir...");
        csprogress.show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                csprogress.dismiss();
//whatever you want just you have to launch overhere.
            }
        }, 3000);
        nav1 = (BottomNavigationView)findViewById(R.id.navigation);
        nav1.getMenu().getItem(3).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        Intent i =new Intent(Message_1.this, Activity_3.class);
                        startActivity(i);
                        break;
                    case R.id.contact:
                        Intent b = new Intent(Message_1.this, Search_2.class);
                        startActivity(b);
                        break;
                    case R.id.info:
                        Intent c = new Intent(Message_1.this, Qoy_1.class);
                        startActivity(c);
                        break;
                    case R.id.message:
                        Intent d = new Intent(Message_1.this,Message_1.class);
                        startActivity(d);
                        break;
                    case R.id.profilim:
                        Intent e = new Intent(Message_1.this, Info.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
        firstproces();
    }

    private void firstproces(){
        ScaleMessage = (LinearLayout)findViewById(R.id.scalemessage);
        ScaleNotification = (LinearLayout)findViewById(R.id.scalenotification);
        Card1 = (CardView)findViewById(R.id.card1);
        rel1 = (RelativeLayout)findViewById(R.id.rec2);
        Card2 = (CardView)findViewById(R.id.card2);
        image1 =(ImageView)findViewById(R.id.image1);
        image2 = (ImageView)findViewById(R.id.image_2_1);
        detal1 = (TextView)findViewById(R.id.detal);
        detal2 = (TextView)findViewById(R.id.detal_2_1);
        recyclerView = (ListView)findViewById(R.id.rec1);
        ScaleMessage.setVisibility(View.VISIBLE);
        ScaleNotification.setVisibility(View.GONE);
        final TextView Empty = (TextView)findViewById(R.id.empty);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        final String uid = user.getUid();
        model = new ArrayList<>();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("People");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                model.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Message_1_adapter Model = snapshot.getValue(Message_1_adapter.class);
                    model.add(Model);
                }
                Message_1_ada_1 adap = new Message_1_ada_1(Message_1.this,model);
                recyclerView.setAdapter(adap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("People")){
                    Empty.setVisibility(View.INVISIBLE);
                }else {
                    recyclerView.setVisibility(View.INVISIBLE);
                    Empty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Message = (Button)findViewById(R.id.masaj);
        Notifications = (Button)findViewById(R.id.notifications);
        Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.VISIBLE);
                rel1.setVisibility(View.INVISIBLE);
                ScaleMessage.setVisibility(View.VISIBLE);
                ScaleNotification.setVisibility(View.INVISIBLE);
            }
        });
        Notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleMessage.setVisibility(View.INVISIBLE);
                ScaleNotification.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid).child("drive");
                db1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("detal")){
                            rel1.setVisibility(View.VISIBLE);
                            Card1.setVisibility(View.GONE);
                            Card2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i =new Intent(Message_1.this,Info.class);
                                    startActivity(i);
                                    finish();
                                }
                            });
                        }else{
                            rel1.setVisibility(View.VISIBLE);
                            Card2.setVisibility(View.VISIBLE);
                            Card1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i =new Intent(Message_1.this,Qoy_1.class);
                                    startActivity(i);
                                }
                            });
                            Card2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(Message_1.this,Info.class);
                                    startActivity(i);
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        if(recyclerView.getVisibility() == View.INVISIBLE){

        }
        if(recyclerView.getVisibility() == View.VISIBLE){

        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
