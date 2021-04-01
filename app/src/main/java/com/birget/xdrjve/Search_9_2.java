package com.birget.xdrjve;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Search_9_2 extends AppCompatActivity {
    private Button Submit;
    private int a;
    private String ReceiverUid;
    private String name;
    private String phone;
    private String myUid;
    private String Name, Time, Day, Month, FromPlace, ToPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_9_2);
        Submit = (Button) findViewById(R.id.submit);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        myUid = user.getUid();
        ReceiverUid = getIntent().getStringExtra("uid");
        DatabaseReference ReceiverDB = FirebaseDatabase.getInstance().getReference().child("Users2").child(ReceiverUid);
        ReceiverDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Name = (String)dataSnapshot.child("name").getValue().toString();
                Time = (String)dataSnapshot.child("time").getValue().toString();
                Day = (String)dataSnapshot.child("drive").child("day").getValue().toString();
                Month = (String)dataSnapshot.child("drive").child("month").getValue().toString();
                FromPlace = (String)dataSnapshot.child("drive").child("fromplace").getValue().toString();
                ToPlace = (String)dataSnapshot.child("drive").child("toplace").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final DatabaseReference MyDB = FirebaseDatabase.getInstance().getReference().child("Users2").child(myUid);
        MyDB.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = (String) dataSnapshot.child("name").getValue();
                phone = (String)dataSnapshot.child("phone").getValue();
                DatabaseReference ReceiverDB = FirebaseDatabase.getInstance().getReference().child("Users2").child(ReceiverUid);
                ReceiverDB.child("customer").child(myUid).child("name").setValue(name);
                ReceiverDB.child("customer").child(myUid).child("phone").setValue(phone);
                ReceiverDB.child("customer").child(myUid).child("uid").setValue(myUid);
                MyDB.child("ReserveOrders").child(String.valueOf(getTaskId())).child("id").setValue(ReceiverUid);
                String abc = String.valueOf(getTaskId());
                MyDB.child("ReserveOrders").child(abc).child("id").setValue(ReceiverUid);
                MyDB.child("ReserveOrders").child(abc).child("name").setValue(Name);
                MyDB.child("ReserveOrders").child(abc).child("time").setValue(Time);
                MyDB.child("ReserveOrders").child(abc).child("day").setValue(Day);
                MyDB.child("ReserveOrders").child(abc).child("month").setValue(Month);
                MyDB.child("ReserveOrders").child(abc).child("fromplace").setValue(FromPlace);
                MyDB.child("ReserveOrders").child(abc).child("toplace").setValue(ToPlace);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final ProgressDialog csprogress=new ProgressDialog(Search_9_2.this);
        csprogress.setMessage("Yüklənir...");
        csprogress.show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                csprogress.dismiss();
                FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                String udi = user1.getUid();
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(udi);
                db1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//whatever you want just you have to launch overhere.
                        if (dataSnapshot.hasChild("drive")) {
                            Intent i = new Intent(Search_9_2.this, Qoy_9.class);
                            startActivity(i);
                            finish();
                            return;
                        } else {
                            Intent i = new Intent(Search_9_2.this, Activity_3.class);
                            startActivity(i);
                            finish();
                            return;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
//whatever you want just you have to launch overhere.
            }
        }, 7000);
    //    FirebaseUser user2 = FirebaseAuth.getInstance().getCurrentUser();
    //    String udis = user2.getUid();
    //    DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(udis);
    //    db2.addValueEventListener(new ValueEventListener() {
    //        @Override
    //        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
    //            if(dataSnapshot.hasChild("drive")){
    //                Intent i = new Intent(Search_9_2.this,Qoy_9.class);
    //                startActivity(i);
    //                finish();
    //                return;
    //            }else{
    //                Intent i = new Intent(Search_9_2.this,Activity_3.class);
    //                startActivity(i);
    //                finish();
    //                return;
    //            }
    //        }

    //        @Override
    //        public void onCancelled(@NonNull DatabaseError databaseError) {

    //        }
    //    });
    }
}