package com.birget.xdrjve;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Signİn extends AppCompatActivity {
    private EditText Email,Password;
    private Button Bitir;
    private ImageView ShowPassword;
    private String travel;
    private int a;
    private AdView mAdView1,mAdView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Email= (EditText)findViewById(R.id.email);
        Password =(EditText)findViewById(R.id.password);
        Bitir = (Button)findViewById(R.id.bitir);
        ShowPassword = (ImageView)findViewById(R.id.showpassword);
        ShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                Password.setSelection(Password.length());
                ShowPassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        Password.setSelection(Password.length());
                        ShowPassword.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                                Password.setSelection(Password.length());
                                ShowPassword.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                        Password.setSelection(Password.length());
                                        ShowPassword.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                                                Password.setSelection(Password.length());
                                                ShowPassword.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                                        Password.setSelection(Password.length());
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
        MobileAds.initialize(this, "ca-app-pub-4810068176185955~8190980825");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-4810068176185955/4151663464");
        //
        mAdView1 = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest);
        //
        mAdView2 = findViewById(R.id.adView2);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest2);
        Bitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog csprogress=new ProgressDialog(Signİn.this);
                csprogress.setMessage("Yüklənir...");
                csprogress.show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        csprogress.dismiss();
//whatever you want just you have to launch overhere.
                        String EmailText = Email.getText().toString();
                        String PasswordText = Password.getText().toString();
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        auth.signInWithEmailAndPassword(EmailText,PasswordText)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            FirebaseAuth auth = FirebaseAuth.getInstance();
                                            FirebaseUser user = auth.getCurrentUser();
                                            final String uid = user.getUid();
                                            DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                                            db1.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    if(dataSnapshot.hasChild("activity")){
                                                        Intent i =new Intent(Signİn.this, Search_8.class);
                                                        startActivity(i);
                                                    }
                                                    if(dataSnapshot.hasChild("customer")){
                                                        if(dataSnapshot.hasChild("Message")){
                                                            Intent i =new Intent(Signİn.this, Request.class);
                                                            i.putExtra("message","message");
                                                            a=5;
                                                            finish();
                                                            startActivity(i);
                                                            return;
                                                        }else{
                                                            a=5;
                                                            Intent i =new Intent(Signİn.this,Request.class);
                                                            finish();
                                                            startActivity(i);
                                                            return;
                                                        }
                                                    }
                                                    if(dataSnapshot.hasChild("Message")){
                                                        if(dataSnapshot.hasChild("drive")){
                                                            a=5;
                                                            Intent i =  new Intent(Signİn.this, Message_1_2.class);
                                                            finish();
                                                            startActivity(i);
                                                            return;
                                                        }else{
                                                            a=5;
                                                            Intent  i =new Intent(Signİn.this, Message_1.class);
                                                            finish();
                                                            startActivity(i);
                                                            return;
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                            DatabaseReference db2 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                                            db2.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    if(a==5){
                                                        return;
                                                    }
                                                    if(dataSnapshot.hasChild("activity")){
                                                        Intent b =new Intent(Signİn.this,Search_8.class);
                                                        startActivity(b);
                                                        return;
                                                    }
                                                    if(dataSnapshot.hasChild("drive")){
                                                        if(dataSnapshot.hasChild("travel")){
                                                            travel = dataSnapshot.child("travel").getValue().toString();
                                                        }
                                                        if(travel.matches("no")){
                                                            Intent i =new Intent(Signİn.this, Activity_3.class);
                                                            startActivity(i);
                                                        }else{
                                                            Calendar calendar = Calendar.getInstance();
                                                            if(dataSnapshot.child("drive").hasChild("day")) {
                                                                if (dataSnapshot.child("drive").hasChild("year")) {
                                                                    int day = Integer.valueOf(dataSnapshot.child("drive").child("day").getValue().toString());
                                                                    int month = Integer.valueOf(dataSnapshot.child("drive").child("month").getValue().toString());
                                                                    int year = Integer.valueOf(dataSnapshot.child("drive").child("year").getValue().toString());
                                                                    int nowday = calendar.get(Calendar.DAY_OF_MONTH);
                                                                    int nowmonth = 1 + calendar.get(Calendar.MONTH);
                                                                    int nowyear = calendar.get(Calendar.YEAR);
                                                                    if (year < nowyear) {
                                                                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                                                                        ref.child("drive").removeValue();
                                                                        ref.child("People").removeValue();
                                                                        ref.child("customer").removeValue();
                                                                        ref.child("travel").removeValue();
                                                                        ref.child("truecustomer").removeValue();
                                                                        ref.child("active").setValue("no");
                                                                        Intent i = new Intent(Signİn.this, Activity_3.class);
                                                                        startActivity(i);
                                                                    }
                                                                    if (year == nowyear) {
                                                                        if (month < nowmonth) {
                                                                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                                                                            ref.child("drive").removeValue();
                                                                            ref.child("People").removeValue();
                                                                            ref.child("customer").removeValue();
                                                                            ref.child("travel").removeValue();
                                                                            ref.child("truecustomer").removeValue();
                                                                            ref.child("active").setValue("no");
                                                                            Intent i = new Intent(Signİn.this, Activity_3.class);
                                                                            startActivity(i);
                                                                        }
                                                                    }
                                                                    if (year == nowyear) {
                                                                        if (month == nowmonth) {
                                                                            if (day < nowday) {
                                                                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
                                                                                ref.child("drive").removeValue();
                                                                                ref.child("People").removeValue();
                                                                                ref.child("customer").removeValue();
                                                                                ref.child("travel").removeValue();
                                                                                ref.child("truecustomer").removeValue();
                                                                                ref.child("active").setValue("no");
                                                                                Intent i = new Intent(Signİn.this, Activity_3.class);
                                                                                startActivity(i);
                                                                                return;
                                                                            }
                                                                        }
                                                                    }
                                                                    Intent i = new Intent(Signİn.this, Qoy_9.class);
                                                                    startActivity(i);
                                                                }
                                                            }else {
                                                                Intent i = new Intent(Signİn.this,Qoy_9.class);
                                                                startActivity(i);
                                                            }
                                                        }
                                                    }else{
                                                        Intent i =new Intent(Signİn.this,Activity_3.class);
                                                        startActivity(i);
                                                    }

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });
                                        }
                                        else {
                                            Toast.makeText(Signİn.this,"Bu adda və ya emaildə bir üzv yoxdur!",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }, 10000);
            }
        });
    }
}
