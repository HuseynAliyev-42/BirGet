package com.birget.xdrjve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private int a;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isConnected()) {
            setContentView(R.layout.activity_main2);
        } else {
            Toast.makeText(getApplicationContext(), "Internet yoxdur!", Toast.LENGTH_SHORT).show();
            buildDialog(MainActivity.this).show();
            return;
        }
        //setContentView(R.layout.activity_main2);
        //if(!isConnected(MainActivity2.this))
        // buildDialog(MainActivity2.this).show();
        // else {
        //   Toast.makeText(MainActivity2.this,"Welcome", Toast.LENGTH_SHORT).show();
        // }
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                //
                if (user == null) {
                    Intent mainIntent = new Intent(MainActivity.this, Activity_2.class);
                    finish();
                    startActivity(mainIntent);
                }
                else {
                    FirebaseAuth auth1 = FirebaseAuth.getInstance();
                    FirebaseUser user1 = auth1.getCurrentUser();
                    final String uid = user1.getUid();
                    DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                    db1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild("travel")) {

                            }else{

                                return;
                            }
                            String travel = dataSnapshot.child("travel").getValue().toString();
                            if(a == 5){
                                return;
                            }
                            if(travel.matches("no")){
                                DatabaseReference db3 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                                db3.child("drive").removeValue();
                                Intent i =new Intent(MainActivity.this,Activity_3.class);
                                startActivity(i);
                                finish();
                            }
                            if(dataSnapshot.hasChild("activity")){
                                Intent s =new Intent(MainActivity.this, Search_8.class);
                                startActivity(s);
                                finish();
                            }else{
                                if(dataSnapshot.hasChild("drive")){
                                    Calendar calendar = Calendar.getInstance();
                                    if(dataSnapshot.child("drive").hasChild("day")||dataSnapshot.child("drive").hasChild("month")||dataSnapshot.child("drive").hasChild("year")){
                                        String ElanDay = (String) dataSnapshot.child("drive").child("day").getValue();
                                        String ElanMonth = (String) dataSnapshot.child("drive").child("month").getValue();
                                        String ElanYear = (String) dataSnapshot.child("drive").child("year").getValue();
                                        if(ElanDay == null){
                                            return;
                                        }
                                        if(ElanMonth == null){
                                            return;
                                        }
                                        if(ElanYear == null){
                                            return;
                                        }
                                        int ElanDayInt = Integer.valueOf(ElanDay);
                                        int ElanMonthInt = Integer.valueOf(ElanMonth);
                                        int ElanYearInt = Integer.valueOf(ElanYear);
                                        int DayInt = calendar.get(Calendar.DAY_OF_MONTH);
                                        int MonthInt = calendar.get(Calendar.MONTH)+1;
                                        int YearInt = calendar.get(Calendar.YEAR);
                                        if(ElanYearInt > YearInt){
                                            DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                                            db2.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    if(dataSnapshot.hasChild("activity")){
                                                        Intent  i =new Intent(MainActivity.this,Search_8.class);
                                                        startActivity(i);
                                                        finish();
                                                        return;
                                                    }
                                                    if(dataSnapshot.hasChild("customer")){
                                                        if(dataSnapshot.hasChild("Message")){
                                                            Intent i =new Intent(MainActivity.this, Request.class);
                                                            i.putExtra("message","message");
                                                            startActivity(i);
                                                            a=5;
                                                            finish();
                                                            return;
                                                        }else{
                                                            Intent i =new Intent(MainActivity.this,Request.class);
                                                            finish();
                                                            startActivity(i);
                                                            a=5;
                                                            finish();
                                                            return;
                                                        }
                                                    }
                                                    if(dataSnapshot.hasChild("Message")){
                                                        if(dataSnapshot.hasChild("drive")){
                                                            Intent i =  new Intent(MainActivity.this, Message_1_2.class);
                                                            finish();
                                                            startActivity(i);
                                                            a=5;
                                                            finish();
                                                            return;
                                                        }else{
                                                            Intent  i =new Intent(MainActivity.this, Message_1.class);
                                                            finish();
                                                            startActivity(i);
                                                            a=5;
                                                            finish();
                                                            return;
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                            Intent  a = new Intent(MainActivity.this, Qoy_9.class);
                                            startActivity(a);
                                            finish();
                                            return;
                                        }
                                        if(ElanYearInt == YearInt && ElanMonthInt > MonthInt){
                                            DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                                            db2.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    if(dataSnapshot.hasChild("activity")){
                                                        Intent  i =new Intent(MainActivity.this,Search_8.class);
                                                        startActivity(i);
                                                        finish();
                                                        return;
                                                    }
                                                    if(dataSnapshot.hasChild("customer")){
                                                        if(dataSnapshot.hasChild("Message")){
                                                            Intent i =new Intent(MainActivity.this,Request.class);
                                                            i.putExtra("message","message");
                                                            finish();
                                                            startActivity(i);
                                                            a=5;
                                                            finish();
                                                            return;
                                                        }else{
                                                            Intent i =new Intent(MainActivity.this,Request.class);
                                                            finish();
                                                            startActivity(i);
                                                            a=5;
                                                            finish();
                                                            return;
                                                        }
                                                    }
                                                    if(dataSnapshot.hasChild("Message")){
                                                        if(dataSnapshot.hasChild("drive")){
                                                            Intent i =  new Intent(MainActivity.this,Message_1_2.class);
                                                            finish();
                                                            startActivity(i);
                                                            a=5;
                                                            finish();
                                                            return;
                                                        }else{
                                                            Intent  i =new Intent(MainActivity.this,Message_1.class);
                                                            finish();
                                                            startActivity(i);
                                                            a=5;
                                                            finish();
                                                            return;
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                            Intent b =new Intent(MainActivity.this,Qoy_9.class);
                                            startActivity(b);
                                            finish();
                                            return;
                                        }
                                        if(ElanYearInt == YearInt && ElanMonthInt == MonthInt && ElanDayInt >= DayInt){
                                            DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                                            db2.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    if(dataSnapshot.hasChild("activity")){
                                                        Intent  i =new Intent(MainActivity.this,Search_8.class);
                                                        startActivity(i);
                                                        finish();
                                                        return;
                                                    }
                                                    if(dataSnapshot.hasChild("customer")){
                                                        if(dataSnapshot.hasChild("Message")){
                                                            Intent i =new Intent(MainActivity.this,Request.class);
                                                            i.putExtra("message","message");
                                                            startActivity(i);
                                                            a=5;
                                                            finish();
                                                            return;
                                                        }else{
                                                            Intent i =new Intent(MainActivity.this,Request.class);
                                                            startActivity(i);
                                                            a=5;
                                                            finish();
                                                            return;
                                                        }
                                                    }
                                                    if(dataSnapshot.hasChild("Message")){
                                                        if(dataSnapshot.hasChild("drive")){
                                                            Intent i =  new Intent(MainActivity.this,Message_1_2.class);
                                                            startActivity(i);
                                                            a=5;
                                                            finish();
                                                            return;
                                                        }else{
                                                            Intent  i =new Intent(MainActivity.this,Message_1.class);
                                                            startActivity(i);
                                                            a=5;
                                                            finish();
                                                            return;
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                            Intent  c = new Intent(MainActivity.this,Qoy_9.class);
                                            startActivity(c);
                                            finish();
                                            return;
                                        }
                                        FirebaseUser user2 = FirebaseAuth.getInstance().getCurrentUser();
                                        String myUid2 = user2.getUid();
                                        DatabaseReference reference =FirebaseDatabase.getInstance().getReference().child("Users2").child(myUid2);
                                        reference.child("drive").removeValue();
                                        reference.child("customer").removeValue();
                                        reference.child("truecustomer").removeValue();
                                        reference.child("People").removeValue();
                                        reference.child("Message").removeValue();
                                        reference.child("travel").setValue("no");
                                        Intent i =new Intent(MainActivity.this,Activity_3.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }else {
                                    Intent i = new Intent(MainActivity.this, Activity_3.class);
                                    startActivity(i);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }
        }, 4000);
    }
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("İnternet Yoxdur!");
        builder.setMessage("Proqram internetsiz fəaliyyət göstərə bilmir. Sizin isə internetiniz yoxdur. İnternete qoşulun və tətbiqə yeniden daxil olun!");

        builder.setPositiveButton("Oldu", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }
}
