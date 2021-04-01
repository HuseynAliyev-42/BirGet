package com.birget.xdrjve;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class RequestCustomerDetails extends AppCompatActivity {
    private String ReceiverUid;
    private String MyUid;
    private BottomNavigationView nav1;
    private String Name;
    private String Phone;
    private Button Accept;
    private Button Decline;
    private TextView NameText;
    private TextView SurnameText;
    private TextView PhoneText;
    private String name, surname, phone, year, gender, url;
    private TextView YearText;
    private TextView GenderText;
    private CircleImageView ProfilImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_customer_details);
        nav1 = (BottomNavigationView) findViewById(R.id.navigation);
        nav1.getMenu().getItem(4).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        Intent i = new Intent(RequestCustomerDetails.this, Qoy_9.class);
                        startActivity(i);
                        break;
                    case R.id.myinfo:
                        Intent b = new Intent(RequestCustomerDetails.this, Info_2.class);
                        startActivity(b);
                        break;
                    case R.id.message:
                        Intent c = new Intent(RequestCustomerDetails.this, Message_1_2.class);
                        startActivity(c);
                        break;
                    case R.id.customer:
                        Intent d = new Intent(RequestCustomerDetails.this, Customer.class);
                        startActivity(d);
                        break;
                    case R.id.rel1:
                        Intent e = new Intent(RequestCustomerDetails.this, Request.class);
                        startActivity(e);
                        break;
                }
                return true;
            }
        });
        NameText = (TextView) findViewById(R.id.name);
        SurnameText = (TextView) findViewById(R.id.surname);
        PhoneText = (TextView) findViewById(R.id.phone);
        YearText = (TextView) findViewById(R.id.year);
        GenderText = (TextView) findViewById(R.id.gender);
        ProfilImage = (CircleImageView) findViewById(R.id.profil);
        ReceiverUid = getIntent().getStringExtra("Uid");
        Name = getIntent().getStringExtra("name");
        Phone = getIntent().getStringExtra("phone");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        MyUid = user.getUid();
        SetName();
        Accept = (Button) findViewById(R.id.accept);
        Decline = (Button) findViewById(R.id.cancel);
        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Accept();
            }
        });
        Decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Decline();
            }
        });
    }

    private void Accept() {
//whatever you want just you have to launch overhere.
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        MyUid = user.getUid();
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
        db1.child("truecustomer").child(ReceiverUid).child("name").setValue(Name);
        db1.child("truecustomer").child(ReceiverUid).child("phone").setValue(Phone);
        db1.child("truecustomer").child(ReceiverUid).child("uid").setValue(ReceiverUid);
        db1.child("customer").child(ReceiverUid).removeValue();
        Intent i = new Intent(RequestCustomerDetails.this, Customer.class);
        startActivity(i);
        finish();
        String uid = user.getUid();
        DatabaseReference d1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(uid);
        d1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("drive")) {
                    Intent i = new Intent(RequestCustomerDetails.this, Qoy_9.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(RequestCustomerDetails.this, Activity_3.class);
                    startActivity(i);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void Decline(){
//whatever you want just you have to launch overhere.
                Name = getIntent().getStringExtra("name");
                Phone = getIntent().getStringExtra("phone");
                ReceiverUid = getIntent().getStringExtra("Uid");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                MyUid = user.getUid();
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(MyUid);
                db1.child("falsecustomer").child(ReceiverUid).child("name").setValue(Name);
                db1.child("falsecustomer").child(ReceiverUid).child("phone").setValue(Phone);
                db1.child("falsecustomer").child(ReceiverUid).child("uid").setValue(ReceiverUid);
                db1.child("customer").child(ReceiverUid).removeValue();
                Intent i =new Intent(RequestCustomerDetails.this,Qoy_9.class);
                startActivity(i);
                finish();
    }
    private void SetName(){
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(ReceiverUid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.child("name").getValue().toString();
                phone  = dataSnapshot.child("phone").getValue().toString();
                surname = dataSnapshot.child("surname").getValue().toString();
                year = dataSnapshot.child("birthyear").getValue().toString();
                gender = dataSnapshot.child("gender").getValue().toString();
                url = dataSnapshot.child("profil").getValue().toString();
                NameText.setText(name);
                PhoneText.setText(phone);
                SurnameText.setText(surname);
                YearText.setText(year);
                GenderText.setText(gender);
                Picasso.get().load(url).into(ProfilImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
