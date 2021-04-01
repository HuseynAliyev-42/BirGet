package com.birget.xdrjve;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomerDetails extends AppCompatActivity {
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
        setContentView(R.layout.activity_customer_details);
        nav1 = (BottomNavigationView) findViewById(R.id.navigation);
        nav1.getMenu().getItem(4).setChecked(true);
        nav1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        Intent i = new Intent(CustomerDetails.this, Qoy_9.class);
                        startActivity(i);
                        break;
                    case R.id.myinfo:
                        Intent b = new Intent(CustomerDetails.this, Info_2.class);
                        startActivity(b);
                        break;
                    case R.id.message:
                        Intent c = new Intent(CustomerDetails.this, Message_1_2.class);
                        startActivity(c);
                        break;
                    case R.id.customer:
                        Intent d = new Intent(CustomerDetails.this, Customer.class);
                        startActivity(d);
                        break;
                    case R.id.rel1:
                        Intent e = new Intent(CustomerDetails.this, Request.class);
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
        ReceiverUid = getIntent().getStringExtra("uid");
        Name = getIntent().getStringExtra("name");
        Phone = getIntent().getStringExtra("phone");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        MyUid = user.getUid();
        SetName();
        Accept = (Button) findViewById(R.id.accept);
        Decline = (Button) findViewById(R.id.cancel);
        ReceiverUid = getIntent().getStringExtra("uid");
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
        Toast.makeText(CustomerDetails.this,ReceiverUid,Toast.LENGTH_LONG).show();
        Intent i =new Intent(CustomerDetails.this,Message_2.class);
        i.putExtra("uid",ReceiverUid);
        startActivity(i);
    }
    private void Decline(){
//whatever you want just you have to launch overhere.
        DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("Users2").child(ReceiverUid);
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String phoneNumber = dataSnapshot.child("phone").getValue().toString();
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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